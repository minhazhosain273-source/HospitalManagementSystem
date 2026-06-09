import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class PatientForm extends JFrame {

    JTextField txtId, txtName, txtAge, txtGender, txtPhone, txtDisease;

    JButton btnAdd, btnUpdate, btnDelete, btnSearch;

    public PatientForm() {

        setTitle("Patient Management");
        setSize(500, 500);
        setLayout(null);

        JLabel l1 = new JLabel("Patient ID");
        JLabel l2 = new JLabel("Name");
        JLabel l3 = new JLabel("Age");
        JLabel l4 = new JLabel("Gender");
        JLabel l5 = new JLabel("Phone");
        JLabel l6 = new JLabel("Disease");

        txtId = new JTextField();
        txtName = new JTextField();
        txtAge = new JTextField();
        txtGender = new JTextField();
        txtPhone = new JTextField();
        txtDisease = new JTextField();

        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnSearch = new JButton("Search");

        l1.setBounds(50,30,100,25);
        txtId.setBounds(180,30,200,25);

        l2.setBounds(50,70,100,25);
        txtName.setBounds(180,70,200,25);

        l3.setBounds(50,110,100,25);
        txtAge.setBounds(180,110,200,25);

        l4.setBounds(50,150,100,25);
        txtGender.setBounds(180,150,200,25);

        l5.setBounds(50,190,100,25);
        txtPhone.setBounds(180,190,200,25);

        l6.setBounds(50,230,100,25);
        txtDisease.setBounds(180,230,200,25);

        btnAdd.setBounds(40,300,90,30);
        btnUpdate.setBounds(140,300,90,30);
        btnDelete.setBounds(240,300,90,30);
        btnSearch.setBounds(340,300,90,30);

        add(l1); add(txtId);
        add(l2); add(txtName);
        add(l3); add(txtAge);
        add(l4); add(txtGender);
        add(l5); add(txtPhone);
        add(l6); add(txtDisease);

        add(btnAdd);
        add(btnUpdate);
        add(btnDelete);
        add(btnSearch);

        btnAdd.addActionListener(e -> addPatient());
        btnUpdate.addActionListener(e -> updatePatient());
        btnDelete.addActionListener(e -> deletePatient());
        btnSearch.addActionListener(e -> searchPatient());

        setVisible(true);
    }

    void addPatient() {
        try {
            Connection con = new DBConnection().getConnection();

            String sql = "INSERT INTO patient(name,age,gender,phone,disease) VALUES(?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, txtName.getText());
            ps.setInt(2, Integer.parseInt(txtAge.getText()));
            ps.setString(3, txtGender.getText());
            ps.setString(4, txtPhone.getText());
            ps.setString(5, txtDisease.getText());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,"Patient Added Successfully");

        } catch(Exception e){
            System.out.println(e);
        }
    }

    void updatePatient() {
        try {
            Connection con = new DBConnection().getConnection();

            String sql = "UPDATE patient SET name=?,age=?,gender=?,phone=?,disease=? WHERE patient_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, txtName.getText());
            ps.setInt(2, Integer.parseInt(txtAge.getText()));
            ps.setString(3, txtGender.getText());
            ps.setString(4, txtPhone.getText());
            ps.setString(5, txtDisease.getText());
            ps.setInt(6, Integer.parseInt(txtId.getText()));

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,"Patient Updated");

        } catch(Exception e){
            System.out.println(e);
        }
    }

    void deletePatient() {
        try {
            Connection con = new DBConnection().getConnection();

            String sql = "DELETE FROM patient WHERE patient_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(txtId.getText()));

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,"Patient Deleted");

        } catch(Exception e){
            System.out.println(e);
        }
    }

    void searchPatient() {
        try {
            Connection con = new DBConnection().getConnection();

            String sql = "SELECT * FROM patient WHERE patient_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(txtId.getText()));

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                txtName.setText(rs.getString("name"));
                txtAge.setText(rs.getString("age"));
                txtGender.setText(rs.getString("gender"));
                txtPhone.setText(rs.getString("phone"));
                txtDisease.setText(rs.getString("disease"));

            } else {

                JOptionPane.showMessageDialog(this,"Patient Not Found");
            }

        } catch(Exception e){
            System.out.println(e);
        }
    }
}