
import javax.swing.*;
import java.sql.*;

public class MedicineForm extends JFrame {

    JTextField txtId;
    JTextField txtName;
    JTextField txtCompany;
    JTextField txtQuantity;
    JTextField txtPrice;

    JButton btnAdd;
    JButton btnSearch;

    public MedicineForm() {

        setTitle("Medicine Inventory");
        setSize(500,400);
        setLayout(null);

        JLabel l1 = new JLabel("Medicine ID");
        JLabel l2 = new JLabel("Medicine Name");
        JLabel l3 = new JLabel("Company Name");
        JLabel l4 = new JLabel("Quantity");
        JLabel l5 = new JLabel("Price");

        txtId = new JTextField();
        txtName = new JTextField();
        txtCompany = new JTextField();
        txtQuantity = new JTextField();
        txtPrice = new JTextField();

        btnAdd = new JButton("Add");
        btnSearch = new JButton("Search");

        l1.setBounds(50,40,120,25);
        txtId.setBounds(200,40,180,25);

        l2.setBounds(50,80,120,25);
        txtName.setBounds(200,80,180,25);

        l3.setBounds(50,120,120,25);
        txtCompany.setBounds(200,120,180,25);

        l4.setBounds(50,160,120,25);
        txtQuantity.setBounds(200,160,180,25);

        l5.setBounds(50,200,120,25);
        txtPrice.setBounds(200,200,180,25);

        btnAdd.setBounds(100,280,100,35);
        btnSearch.setBounds(230,280,100,35);

        add(l1); add(txtId);
        add(l2); add(txtName);
        add(l3); add(txtCompany);
        add(l4); add(txtQuantity);
        add(l5); add(txtPrice);

        add(btnAdd);
        add(btnSearch);

        btnAdd.addActionListener(e -> addMedicine());
        btnSearch.addActionListener(e -> searchMedicine());

        setVisible(true);
    }

    void addMedicine() {

        try {

            Connection con =
                    new DBConnection().getConnection();

            String sql =
                    "INSERT INTO medicine(medicine_name,company_name,quantity,price) VALUES(?,?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, txtName.getText());
            ps.setString(2, txtCompany.getText());
            ps.setInt(3, Integer.parseInt(txtQuantity.getText()));
            ps.setDouble(4, Double.parseDouble(txtPrice.getText()));

            ps.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Medicine Added Successfully");

        } catch(Exception e) {

            JOptionPane.showMessageDialog(this,e);

        }
    }

    void searchMedicine() {

        try {

            Connection con =
                    new DBConnection().getConnection();

            String sql =
                    "SELECT * FROM medicine WHERE medicine_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1,
                    Integer.parseInt(txtId.getText()));

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                txtName.setText(
                        rs.getString("medicine_name"));

                txtCompany.setText(
                        rs.getString("company_name"));

                txtQuantity.setText(
                        rs.getString("quantity"));

                txtPrice.setText(
                        rs.getString("price"));

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Medicine Not Found");
            }

        } catch(Exception e) {

            JOptionPane.showMessageDialog(this,e);

        }
    }
}