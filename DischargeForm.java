
import javax.swing.*;
import java.sql.*;

public class DischargeForm extends JFrame {

    JTextField txtPatientId;
    JTextField txtPatientName;
    JTextField txtDisease;
    JTextField txtAdmissionDate;
    JTextField txtDischargeDate;

    JButton btnDischarge;

    public DischargeForm() {

        setTitle("Patient Discharge");
        setSize(500,400);
        setLayout(null);

        JLabel l1 = new JLabel("Patient ID");
        JLabel l2 = new JLabel("Patient Name");
        JLabel l3 = new JLabel("Disease");
        JLabel l4 = new JLabel("Admission Date");
        JLabel l5 = new JLabel("Discharge Date");

        txtPatientId = new JTextField();
        txtPatientName = new JTextField();
        txtDisease = new JTextField();
        txtAdmissionDate = new JTextField();
        txtDischargeDate = new JTextField();

        btnDischarge = new JButton("Discharge");

        l1.setBounds(50,40,120,25);
        txtPatientId.setBounds(200,40,180,25);

        l2.setBounds(50,80,120,25);
        txtPatientName.setBounds(200,80,180,25);

        l3.setBounds(50,120,120,25);
        txtDisease.setBounds(200,120,180,25);

        l4.setBounds(50,160,120,25);
        txtAdmissionDate.setBounds(200,160,180,25);

        l5.setBounds(50,200,120,25);
        txtDischargeDate.setBounds(200,200,180,25);

        btnDischarge.setBounds(150,280,150,35);

        add(l1); add(txtPatientId);
        add(l2); add(txtPatientName);
        add(l3); add(txtDisease);
        add(l4); add(txtAdmissionDate);
        add(l5); add(txtDischargeDate);

        add(btnDischarge);

        btnDischarge.addActionListener(e -> dischargePatient());

        setVisible(true);
    }

    void dischargePatient() {

        try {

            Connection con =
                new DBConnection().getConnection();

            String sql =
            "INSERT INTO discharge(patient_id,patient_name,disease,admission_date,discharge_date) VALUES(?,?,?,?,?)";

            PreparedStatement ps =
                con.prepareStatement(sql);

            ps.setInt(1,
                Integer.parseInt(txtPatientId.getText()));

            ps.setString(2,
                txtPatientName.getText());

            ps.setString(3,
                txtDisease.getText());

            ps.setString(4,
                txtAdmissionDate.getText());

            ps.setString(5,
                txtDischargeDate.getText());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(
                this,
                "Patient Discharged Successfully");

        } catch(Exception e) {

            JOptionPane.showMessageDialog(this,e);
        }
    }
}
