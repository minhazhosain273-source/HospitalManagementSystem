import javax.swing.*;
import java.sql.*;

public class AppointmentForm extends JFrame {

    JTextField txtId;
    JTextField txtPatientId;
    JTextField txtDoctorId;
    JTextField txtDate;
    JTextField txtStatus;

    JButton btnBook;
    JButton btnSearch;

    public AppointmentForm() {

        setTitle("Appointment Booking");

        setSize(500,400);

        setLayout(null);

        JLabel l1 = new JLabel("Appointment ID");
        JLabel l2 = new JLabel("Patient ID");
        JLabel l3 = new JLabel("Doctor ID");
        JLabel l4 = new JLabel("Date");
        JLabel l5 = new JLabel("Status");

        txtId = new JTextField();
        txtPatientId = new JTextField();
        txtDoctorId = new JTextField();
        txtDate = new JTextField();
        txtStatus = new JTextField();

        btnBook = new JButton("Book");
        btnSearch = new JButton("Search");

        l1.setBounds(50,40,120,25);
        txtId.setBounds(180,40,200,25);

        l2.setBounds(50,80,120,25);
        txtPatientId.setBounds(180,80,200,25);

        l3.setBounds(50,120,120,25);
        txtDoctorId.setBounds(180,120,200,25);

        l4.setBounds(50,160,120,25);
        txtDate.setBounds(180,160,200,25);

        l5.setBounds(50,200,120,25);
        txtStatus.setBounds(180,200,200,25);

        btnBook.setBounds(100,280,100,30);
        btnSearch.setBounds(240,280,100,30);

        add(l1); add(txtId);
        add(l2); add(txtPatientId);
        add(l3); add(txtDoctorId);
        add(l4); add(txtDate);
        add(l5); add(txtStatus);

        add(btnBook);
        add(btnSearch);

        btnBook.addActionListener(e -> bookAppointment());

        btnSearch.addActionListener(e -> searchAppointment());

        setVisible(true);
    }

    void bookAppointment() {

        try {

            Connection con =
                    new DBConnection().getConnection();

            String sql =
                    "INSERT INTO appointment(patient_id,doctor_id,appointment_date,status) VALUES(?,?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1,
                    Integer.parseInt(txtPatientId.getText()));

            ps.setInt(2,
                    Integer.parseInt(txtDoctorId.getText()));

            ps.setString(3,
                    txtDate.getText());

            ps.setString(4,
                    txtStatus.getText());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Appointment Booked Successfully");

        } catch(Exception e){

            JOptionPane.showMessageDialog(
                    this,
                    e);
        }
    }

    void searchAppointment() {

        try {

            Connection con =
                    new DBConnection().getConnection();

            String sql =
                    "SELECT * FROM appointment WHERE appointment_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1,
                    Integer.parseInt(txtId.getText()));

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                txtPatientId.setText(
                        rs.getString("patient_id"));

                txtDoctorId.setText(
                        rs.getString("doctor_id"));

                txtDate.setText(
                        rs.getString("appointment_date"));

                txtStatus.setText(
                        rs.getString("status"));

            }

        } catch(Exception e){

            JOptionPane.showMessageDialog(
                    this,
                    e);
        }
    }
}
