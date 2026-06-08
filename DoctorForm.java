
import javax.swing.*;
import java.sql.*;

public class DoctorForm extends JFrame {

    JTextField txtId, txtName, txtSpecialist, txtRoom;

    JButton btnAdd, btnUpdate, btnDelete, btnSearch;

    public DoctorForm() {

        setTitle("Doctor Management");

        setSize(500,400);

        setLayout(null);

        JLabel l1 = new JLabel("Doctor ID");
        JLabel l2 = new JLabel("Doctor Name");
        JLabel l3 = new JLabel("Specialist");
        JLabel l4 = new JLabel("Room No");

        txtId = new JTextField();
        txtName = new JTextField();
        txtSpecialist = new JTextField();
        txtRoom = new JTextField();

        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnSearch = new JButton("Search");

        l1.setBounds(50,40,100,25);
        txtId.setBounds(180,40,200,25);

        l2.setBounds(50,80,100,25);
        txtName.setBounds(180,80,200,25);

        l3.setBounds(50,120,100,25);
        txtSpecialist.setBounds(180,120,200,25);

        l4.setBounds(50,160,100,25);
        txtRoom.setBounds(180,160,200,25);

        btnAdd.setBounds(40,250,90,30);
        btnUpdate.setBounds(140,250,90,30);
        btnDelete.setBounds(240,250,90,30);
        btnSearch.setBounds(340,250,90,30);

        add(l1); add(txtId);
        add(l2); add(txtName);
        add(l3); add(txtSpecialist);
        add(l4); add(txtRoom);

        add(btnAdd);
        add(btnUpdate);
        add(btnDelete);
        add(btnSearch);

        btnAdd.addActionListener(e -> addDoctor());
        btnUpdate.addActionListener(e -> updateDoctor());
        btnDelete.addActionListener(e -> deleteDoctor());
        btnSearch.addActionListener(e -> searchDoctor());

        setVisible(true);
    }

    void addDoctor() {
        try {

            Connection con =
                    new DBConnection().getConnection();

            String sql =
                    "INSERT INTO doctor(doctor_name,specialist,room_no) VALUES(?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, txtName.getText());
            ps.setString(2, txtSpecialist.getText());
            ps.setInt(3, Integer.parseInt(txtRoom.getText()));

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Doctor Added Successfully");

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    void updateDoctor() {
        try {

            Connection con =
                    new DBConnection().getConnection();

            String sql =
                    "UPDATE doctor SET doctor_name=?,specialist=?,room_no=? WHERE doctor_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, txtName.getText());
            ps.setString(2, txtSpecialist.getText());
            ps.setInt(3, Integer.parseInt(txtRoom.getText()));
            ps.setInt(4, Integer.parseInt(txtId.getText()));

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Doctor Updated");

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    void deleteDoctor() {
        try {

            Connection con =
                    new DBConnection().getConnection();

            String sql =
                    "DELETE FROM doctor WHERE doctor_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(txtId.getText()));

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Doctor Deleted");

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    void searchDoctor() {
        try {

            Connection con =
                    new DBConnection().getConnection();

            String sql =
                    "SELECT * FROM doctor WHERE doctor_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(txtId.getText()));

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                txtName.setText(
                        rs.getString("doctor_name"));

                txtSpecialist.setText(
                        rs.getString("specialist"));

                txtRoom.setText(
                        rs.getString("room_no"));

            } else {

                JOptionPane.showMessageDialog(this,
                        "Doctor Not Found");
            }

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}