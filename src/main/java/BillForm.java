

import javax.swing.*;
import java.sql.*;

public class BillForm extends JFrame {

    JTextField txtPatientId;
    JTextField txtPatientName;
    JTextField txtTreatment;
    JTextField txtMedicine;
    JTextField txtTotal;

    JButton btnGenerate;

    public BillForm() {

        setTitle("Billing System");
        setSize(500,400);
        setLayout(null);

        JLabel l1 = new JLabel("Patient ID");
        JLabel l2 = new JLabel("Patient Name");
        JLabel l3 = new JLabel("Treatment Cost");
        JLabel l4 = new JLabel("Medicine Cost");
        JLabel l5 = new JLabel("Total Cost");

        txtPatientId = new JTextField();
        txtPatientName = new JTextField();
        txtTreatment = new JTextField();
        txtMedicine = new JTextField();
        txtTotal = new JTextField();

        btnGenerate = new JButton("Generate Bill");

        l1.setBounds(50,40,120,25);
        txtPatientId.setBounds(200,40,180,25);

        l2.setBounds(50,80,120,25);
        txtPatientName.setBounds(200,80,180,25);

        l3.setBounds(50,120,120,25);
        txtTreatment.setBounds(200,120,180,25);

        l4.setBounds(50,160,120,25);
        txtMedicine.setBounds(200,160,180,25);

        l5.setBounds(50,200,120,25);
        txtTotal.setBounds(200,200,180,25);

        btnGenerate.setBounds(150,280,150,35);

        add(l1); add(txtPatientId);
        add(l2); add(txtPatientName);
        add(l3); add(txtTreatment);
        add(l4); add(txtMedicine);
        add(l5); add(txtTotal);

        add(btnGenerate);

        btnGenerate.addActionListener(e -> generateBill());

        setVisible(true);
    }

    void generateBill() {

        try {

            double treatment =
                Double.parseDouble(txtTreatment.getText());

            double medicine =
                Double.parseDouble(txtMedicine.getText());

            double total =
                treatment + medicine;

            txtTotal.setText(String.valueOf(total));

            Connection con =
                new DBConnection().getConnection();

            String sql =
            "INSERT INTO bill(patient_id,patient_name,treatment_cost,medicine_cost,total_cost) VALUES(?,?,?,?,?)";

            PreparedStatement ps =
                con.prepareStatement(sql);

            ps.setInt(1,
                Integer.parseInt(txtPatientId.getText()));

            ps.setString(2,
                txtPatientName.getText());

            ps.setDouble(3,treatment);
            ps.setDouble(4,medicine);
            ps.setDouble(5,total);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(
                this,
                "Bill Generated Successfully");

        } catch(Exception e) {

            JOptionPane.showMessageDialog(this,e);

        }
    }
}