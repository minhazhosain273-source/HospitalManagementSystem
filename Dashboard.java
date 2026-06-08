

import javax.swing.*;

public class Dashboard extends JFrame {

    JButton patientBtn;
    JButton doctorBtn;
    JButton appointmentBtn;
    JButton billBtn;
    JButton dischargeBtn;
    JButton medicineBtn;
    JButton logoutBtn;

    public Dashboard() {

        setTitle("Hospital Dashboard");

        setSize(700,600);

        setLayout(null);
        
        JLabel title = new JLabel("Hospital Management System");

        title.setBounds(250,10,300,30);

        add(title);

        patientBtn =
                new JButton("Patient Management");
        doctorBtn =
        new JButton("Doctor Management");
        appointmentBtn =
        new JButton("Appointment");
        billBtn = new JButton("Billing System");
        dischargeBtn =
        new JButton("Discharge Patient");
        medicineBtn =
        new JButton("Medicine Inventory");
        logoutBtn = new JButton("Logout");

        patientBtn.setBounds(230,40,220,40);
        
        doctorBtn.setBounds(230,100,220,40);
        
        appointmentBtn.setBounds(230,160,220,40);
        
        billBtn.setBounds(230,220,220,40);
        dischargeBtn.setBounds(230,280,220,40);
        medicineBtn.setBounds(230,340,220,40);
        logoutBtn.setBounds(230,460,220,40);







        add(patientBtn);
        add(doctorBtn);
        add(appointmentBtn);
        add(billBtn);
        add(dischargeBtn);
        add(medicineBtn);
        add(logoutBtn);
        

        patientBtn.addActionListener(e -> {

            new PatientForm();

        });
        doctorBtn.addActionListener(e -> {

    new DoctorForm();

});
        appointmentBtn.addActionListener(e -> {

    new AppointmentForm();

});
        billBtn.addActionListener(e -> {

    new BillForm();

});
        dischargeBtn.addActionListener(e -> {

    new DischargeForm();

});
        medicineBtn.addActionListener(e -> {

    new MedicineForm();

});
        logoutBtn.addActionListener(e -> {

    dispose();

    new Login();

});
        setLocationRelativeTo(null);
        
        setVisible(true);
    }
}
