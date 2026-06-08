import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class Login extends JFrame {

    JTextField txtUser;
    JPasswordField txtPass;

    JButton btnLogin;

    public Login(){

        setTitle("Hospital Login");

        setSize(500,300);
        
        setLocationRelativeTo(null);
        
        setLayout(null);

        JLabel l1 =
                new JLabel("Username");

        JLabel l2 =
                new JLabel("Password");

        txtUser =
                new JTextField();

        txtPass =
                new JPasswordField();

        btnLogin =
                new JButton("Login");

        l1.setBounds(50,40,100,30);
        l2.setBounds(50,90,100,30);

        txtUser.setBounds(150,40,120,30);
        txtPass.setBounds(150,90,120,30);

        btnLogin.setBounds(120,150,100,30);

        add(l1);
        add(l2);

        add(txtUser);
        add(txtPass);

        add(btnLogin);

        btnLogin.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){

                loginUser();
            }
        });

        setVisible(true);
    }

    void loginUser(){

        try{

            Connection con =
                    new DBConnection().getConnection();

            String sql =
                    "SELECT * FROM login WHERE username=? AND password=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1,
                    txtUser.getText());

            ps.setString(2,
                    txtPass.getText());

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()){

                JOptionPane.showMessageDialog(
                        this,
                        "Login Successful");
                new Dashboard();

    dispose();


            }else{

                JOptionPane.showMessageDialog(
                        this,
                        "Wrong Username or Password");
            }

        }catch(Exception ex){

            System.out.println(ex);
        }
    }

    public static void main(String[] args){

        new Login();
    }
}