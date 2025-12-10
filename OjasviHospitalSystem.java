import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
import java.awt.BasicStroke;
import java.awt.FontMetrics;

// Base Person class demonstrating Inheritance
abstract class Person {
    protected String id;
    protected String name;
    protected String phone;
    protected String address;
    protected String gender;
     
    public Person(String id, String name, String phone, String address, String gender) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
    }
    
    public abstract String getRole();
    public String getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getGender() { return gender; }
}

// Patient class
class Patient extends Person {
    private String bloodGroup;
    private String disease;
    private LocalDate admissionDate;
    
    public Patient(String id, String name, String phone, String address, String gender, String bloodGroup, String disease) {
        super(id, name, phone, address, gender);
        this.bloodGroup = bloodGroup;
        this.disease = disease;
        this.admissionDate = LocalDate.now();
    }
    
    @Override
    public String getRole() { return "Patient"; }
    public String getBloodGroup() { return bloodGroup; }
    public String getDisease() { return disease; }
    public LocalDate getAdmissionDate() { return admissionDate; }
}

// Doctor class
class Doctor extends Person {
    private String specialization;
    private String qualification;
    private double consultationFee;
    
    public Doctor(String id, String name, String phone, String address, String gender, String specialization, String qualification, double fee) {
        super(id, name, phone, address, gender);
        this.specialization = specialization;
        this.qualification = qualification;
        this.consultationFee = fee;
    }
    
    @Override
    public String getRole() { return "Doctor"; }
    public String getSpecialization() { return specialization; }
    public String getQualification() { return qualification; }
    public double getConsultationFee() { return consultationFee; }
}

// Appointment class demonstrating Association
class Appointment {
    private String appointmentId;
    private Patient patient;
    private Doctor doctor;
    private LocalDate date;
    private String time;
    private String status;
    
    public Appointment(String id, Patient patient, Doctor doctor, LocalDate date, String time) {
        this.appointmentId = id;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
        this.status = "Scheduled";
    }
    
    public String getAppointmentId() { return appointmentId; }
    public Patient getPatient() { return patient; }
    public Doctor getDoctor() { return doctor; }
    public LocalDate getDate() { return date; }
    public String getTime() { return time; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

// Prescription class
class Prescription {
    private String prescriptionId;
    private Patient patient;
    private Doctor doctor;
    private String medicines;
    private String instructions;
    private LocalDate date;
    
    public Prescription(String id, Patient patient, Doctor doctor, String medicines, String instructions) {
        this.prescriptionId = id;
        this.patient = patient;
        this.doctor = doctor;
        this.medicines = medicines;
        this.instructions = instructions;
        this.date = LocalDate.now();
    }
    
    public String getPrescriptionId() { return prescriptionId; }
    public Patient getPatient() { return patient; }
    public Doctor getDoctor() { return doctor; }
    public String getMedicines() { return medicines; }
    public String getInstructions() { return instructions; }
    public LocalDate getDate() { return date; }
}

// Bill class demonstrating Composition
class Bill {
    private String billId;
    private Patient patient;
    private double consultationCharges;
    private double medicineCharges;
    private double roomCharges;
    private double totalAmount;
    private LocalDate billDate;
    private String paymentStatus;
    
    public Bill(String id, Patient patient, double consultation, double medicine, double room) {
        this.billId = id;
        this.patient = patient;
        this.consultationCharges = consultation;
        this.medicineCharges = medicine;
        this.roomCharges = room;
        this.totalAmount = consultation + medicine + room;
        this.billDate = LocalDate.now();
        this.paymentStatus = "Unpaid";
    }
    
    public String getBillId() { return billId; }
    public Patient getPatient() { return patient; }
    public double getTotalAmount() { return totalAmount; }
    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String status) { this.paymentStatus = status; }
    public LocalDate getBillDate() { return billDate; }
}

// Custom Exception
class HospitalException extends Exception {
    public HospitalException(String message) {
        super(message);
    }
}

// Main Hospital Management System
public class OjasviHospitalSystem extends JFrame {
    private ArrayList<Patient> patients = new ArrayList<>();
    private ArrayList<Doctor> doctors = new ArrayList<>();
    private ArrayList<Appointment> appointments = new ArrayList<>();
    private ArrayList<Prescription> prescriptions = new ArrayList<>();
    private ArrayList<Bill> bills = new ArrayList<>();
    
    private JTabbedPane tabbedPane;
    private int patientCounter = 1;
    private int doctorCounter = 1;
    private int appointmentCounter = 1;
    private int prescriptionCounter = 1;
    private int billCounter = 1;

    // Keep references to all patient combo boxes so we can refresh them on add
    private java.util.List<JComboBox<String>> patientComboBoxes = new ArrayList<>();
    
    public OjasviHospitalSystem() {
        setTitle("Ojasvi Hospital Management System");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initializeSampleData();
        createGUI();
    }
    
    private void initializeSampleData() {
        // Adding 7 Doctors
        doctors.add(new Doctor("D001", "Dr. Rajesh Sharma", "9876543210", "Delhi", "Male", "Cardiologist", "MBBS, MD", 800));
        doctors.add(new Doctor("D002", "Dr. Priya Patel", "9876543211", "Mumbai", "Female", "Neurologist", "MBBS, DM", 900));
        doctors.add(new Doctor("D003", "Dr. Amit Singh", "9876543212", "Bangalore", "Male", "Orthopedic", "MBBS, MS", 700));
        doctors.add(new Doctor("D004", "Dr. Sneha Reddy", "9876543213", "Hyderabad", "Female", "Pediatrician", "MBBS, MD", 600));
        doctors.add(new Doctor("D005", "Dr. Vikram Mehta", "9876543214", "Pune", "Male", "Dermatologist", "MBBS, MD", 650));
        doctors.add(new Doctor("D006", "Dr. Kavita Desai", "9876543215", "Chennai", "Female", "Gynecologist", "MBBS, MS", 750));
        doctors.add(new Doctor("D007", "Dr. Arun Kumar", "9876543216", "Kolkata", "Male", "General Physician", "MBBS", 500));
        doctorCounter = 8;
        
        // Adding 5 Patients
        patients.add(new Patient("P001", "Rahul Verma", "9123456780", "Dadri, UP", "Male", "O+", "Hypertension"));
        patients.add(new Patient("P002", "Anita Gupta", "9123456781", "Noida, UP", "Female", "A+", "Diabetes"));
        patients.add(new Patient("P003", "Suresh Kumar", "9123456782", "Greater Noida, UP", "Male", "B+", "Asthma"));
        patients.add(new Patient("P004", "Meera Sharma", "9123456783", "Ghaziabad, UP", "Female", "AB+", "Migraine"));
        patients.add(new Patient("P005", "Vikas Yadav", "9123456784", "Delhi NCR", "Male", "O-", "Fever"));
        patientCounter = 6;
    }
    
    private void createGUI() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(41, 128, 185));
        headerPanel.setPreferredSize(new Dimension(1000, 80));
        
        // Hospital Logo - generated image icon so it's always visible
        JLabel logoLabel = new JLabel(createLogoIcon());
        logoLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 10));
        
        // Hospital Name
        JLabel titleLabel = new JLabel("<html><div style='text-align: center;'><b>OJASVI HOSPITAL</b><br/><span style='font-size: 12px;'>Management System</span></div></html>");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        
        headerPanel.add(logoLabel, BorderLayout.WEST);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("🏠 Dashboard", createDashboardPanel());
        tabbedPane.addTab("👥 Patients", createPatientPanel());
        tabbedPane.addTab("👨‍⚕️ Doctors", createDoctorPanel());
        tabbedPane.addTab("📅 Appointments", createAppointmentPanel());
        tabbedPane.addTab("💊 Prescriptions", createPrescriptionPanel());
        tabbedPane.addTab("💰 Billing", createBillingPanel());
        
        add(headerPanel, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    // Create a simple generated logo icon (avoids emoji rendering issues)
    private ImageIcon createLogoIcon() {
        int size = 64;
        BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(new Color(52,152,219));
        g.fillRoundRect(0, 0, size, size, 14, 14);
        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(4f));
        // Cross
        g.drawLine(size/2, 12, size/2, size-12);
        g.drawLine(12, size/2, size-12, size/2);
        // Small "O" letter centered
        g.setFont(new Font("Arial", Font.BOLD, 20));
        FontMetrics fm = g.getFontMetrics();
        String s = "O";
        int x = (size - fm.stringWidth(s)) / 2;
        int y = (size - fm.getHeight()) / 2 + fm.getAscent();
        g.drawString(s, x, y);
        g.dispose();
        return new ImageIcon(img);
    }
    
    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 3, 20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.setBackground(new Color(236, 240, 241));
        
        panel.add(createStatCard("Total Patients", patients.size(), new Color(52, 152, 219)));
        panel.add(createStatCard("Total Doctors", doctors.size(), new Color(46, 204, 113)));
        panel.add(createStatCard("Appointments", appointments.size(), new Color(155, 89, 182)));
        panel.add(createStatCard("Prescriptions", prescriptions.size(), new Color(241, 196, 15)));
        panel.add(createStatCard("Total Bills", bills.size(), new Color(231, 76, 60)));
        panel.add(createStatCard("Pending Bills", getPendingBillsCount(), new Color(230, 126, 34)));
        
        return panel;
    }
    
    private JPanel createStatCard(String title, int value, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(color);
        card.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        
        JLabel valueLabel = new JLabel(String.valueOf(value), SwingConstants.CENTER);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 36));
        valueLabel.setForeground(Color.WHITE);
        
        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);
        
        return card;
    }
    
    private JPanel createPatientPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        JTextField nameField = new JTextField();
        JComboBox<String> genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        JTextField phoneField = new JTextField();
        JTextField addressField = new JTextField();
        JComboBox<String> bloodGroupBox = new JComboBox<>(new String[]{"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"});
        JTextField diseaseField = new JTextField();
        
        formPanel.add(new JLabel("Patient Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Gender:"));
        formPanel.add(genderBox);
        formPanel.add(new JLabel("Phone:"));
        formPanel.add(phoneField);
        formPanel.add(new JLabel("Address:"));
        formPanel.add(addressField);
        formPanel.add(new JLabel("Blood Group:"));
        formPanel.add(bloodGroupBox);
        formPanel.add(new JLabel("Disease:"));
        formPanel.add(diseaseField);
        
        JButton addButton = new JButton("Add Patient");
        addButton.setBackground(new Color(46, 204, 113));
        addButton.setForeground(Color.WHITE);
        
        String[] columns = {"ID", "Name", "Gender", "Phone", "Blood Group", "Disease", "Admission Date"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        
        // Load existing patients
        for (Patient patient : patients) {
            tableModel.addRow(new Object[]{patient.getId(), patient.getName(), patient.getGender(),
                    patient.getPhone(), patient.getBloodGroup(), patient.getDisease(), patient.getAdmissionDate()});
        }
        
        addButton.addActionListener(e -> {
            try {
                if (nameField.getText().trim().isEmpty() || phoneField.getText().trim().isEmpty()) {
                    throw new HospitalException("Name and Phone are required!");
                }
                String id = "P" + String.format("%03d", patientCounter++);
                Patient patient = new Patient(id, nameField.getText(), phoneField.getText(),
                        addressField.getText(), (String)genderBox.getSelectedItem(), 
                        (String)bloodGroupBox.getSelectedItem(), diseaseField.getText());
                patients.add(patient);
                tableModel.addRow(new Object[]{id, patient.getName(), patient.getGender(), patient.getPhone(),
                        patient.getBloodGroup(), patient.getDisease(), patient.getAdmissionDate()});
                clearFields(nameField, phoneField, addressField, diseaseField);
                JOptionPane.showMessageDialog(this, "Patient added successfully!");
                // refresh patient combo boxes in other panels
                refreshPatientComboBoxes();
                updateDashboard();
            } catch (HospitalException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        formPanel.add(new JLabel());
        formPanel.add(addButton);
        
        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createDoctorPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        String[] columns = {"ID", "Name", "Gender", "Phone", "Specialization", "Qualification", "Fee"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        
        for (Doctor doctor : doctors) {
            tableModel.addRow(new Object[]{doctor.getId(), doctor.getName(), doctor.getGender(), doctor.getPhone(),
                    doctor.getSpecialization(), doctor.getQualification(), doctor.getConsultationFee()});
        }
        
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createAppointmentPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        JComboBox<String> patientBox = new JComboBox<>();
        JComboBox<String> doctorBox = new JComboBox<>();
        JTextField dateField = new JTextField(LocalDate.now().toString());
        JTextField timeField = new JTextField("10:00 AM");
        
        // Refresh patient and doctor lists dynamically
        for (Patient p : patients) patientBox.addItem(p.getId() + " - " + p.getName());
        for (Doctor d : doctors) doctorBox.addItem(d.getId() + " - " + d.getName());
        
        // register this combo so it can be refreshed later
        patientComboBoxes.add(patientBox);
        
        formPanel.add(new JLabel("Select Patient:"));
        formPanel.add(patientBox);
        formPanel.add(new JLabel("Select Doctor:"));
        formPanel.add(doctorBox);
        formPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        formPanel.add(dateField);
        formPanel.add(new JLabel("Time:"));
        formPanel.add(timeField);
        
        JButton bookButton = new JButton("Book Appointment");
        bookButton.setBackground(new Color(155, 89, 182));
        bookButton.setForeground(Color.WHITE);
        
        String[] columns = {"ID", "Patient", "Doctor", "Date", "Time", "Status"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        
        // Load existing appointments
        for (Appointment appointment : appointments) {
            tableModel.addRow(new Object[]{appointment.getAppointmentId(), appointment.getPatient().getName(),
                    appointment.getDoctor().getName(), appointment.getDate(), appointment.getTime(), appointment.getStatus()});
        }
        
        bookButton.addActionListener(e -> {
            try {
                if (patients.isEmpty() || doctors.isEmpty()) {
                    throw new HospitalException("Please add patients and doctors first!");
                }
                String id = "A" + String.format("%03d", appointmentCounter++);
                int pIndex = patientBox.getSelectedIndex();
                int dIndex = doctorBox.getSelectedIndex();
                
                Appointment appointment = new Appointment(id, patients.get(pIndex), doctors.get(dIndex),
                        LocalDate.parse(dateField.getText()), timeField.getText());
                appointments.add(appointment);
                tableModel.addRow(new Object[]{id, appointment.getPatient().getName(),
                        appointment.getDoctor().getName(), appointment.getDate(), appointment.getTime(), appointment.getStatus()});
                JOptionPane.showMessageDialog(this, "Appointment booked successfully!");
                updateDashboard();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        formPanel.add(new JLabel());
        formPanel.add(bookButton);
        
        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createPrescriptionPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        JComboBox<String> patientBox = new JComboBox<>();
        JComboBox<String> doctorBox = new JComboBox<>();
        JTextArea medicinesArea = new JTextArea(3, 20);
        JTextArea instructionsArea = new JTextArea(3, 20);
        
        // Refresh patient and doctor lists dynamically
        for (Patient p : patients) patientBox.addItem(p.getId() + " - " + p.getName());
        for (Doctor d : doctors) doctorBox.addItem(d.getId() + " - " + d.getName());
        
        // register this combo so it can be refreshed later
        patientComboBoxes.add(patientBox);
        
        formPanel.add(new JLabel("Patient:"));
        formPanel.add(patientBox);
        formPanel.add(new JLabel("Doctor:"));
        formPanel.add(doctorBox);
        formPanel.add(new JLabel("Medicines:"));
        formPanel.add(new JScrollPane(medicinesArea));
        formPanel.add(new JLabel("Instructions:"));
        formPanel.add(new JScrollPane(instructionsArea));
        
        JButton addButton = new JButton("Add Prescription");
        addButton.setBackground(new Color(241, 196, 15));
        
        String[] columns = {"ID", "Patient", "Doctor", "Medicines", "Date"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        
        // Load existing prescriptions
        for (Prescription prescription : prescriptions) {
            tableModel.addRow(new Object[]{prescription.getPrescriptionId(), prescription.getPatient().getName(),
                    prescription.getDoctor().getName(), prescription.getMedicines(), prescription.getDate()});
        }
        
        addButton.addActionListener(e -> {
            try {
                if (patients.isEmpty() || doctors.isEmpty()) {
                    throw new HospitalException("Please add patients and doctors first!");
                }
                String id = "RX" + String.format("%03d", prescriptionCounter++);
                Prescription prescription = new Prescription(id, patients.get(patientBox.getSelectedIndex()),
                        doctors.get(doctorBox.getSelectedIndex()), medicinesArea.getText(), instructionsArea.getText());
                prescriptions.add(prescription);
                tableModel.addRow(new Object[]{id, prescription.getPatient().getName(),
                        prescription.getDoctor().getName(), prescription.getMedicines(), prescription.getDate()});
                medicinesArea.setText("");
                instructionsArea.setText("");
                JOptionPane.showMessageDialog(this, "Prescription added successfully!");
                updateDashboard();
            } catch (HospitalException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        formPanel.add(new JLabel());
        formPanel.add(addButton);
        
        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createBillingPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        JComboBox<String> patientBox = new JComboBox<>();
        JTextField consultationField = new JTextField();
        JTextField medicineField = new JTextField();
        JTextField roomField = new JTextField();
        
        // Refresh patient list dynamically
        for (Patient p : patients) patientBox.addItem(p.getId() + " - " + p.getName());
        
        // register this combo so it can be refreshed later
        patientComboBoxes.add(patientBox);
        
        formPanel.add(new JLabel("Patient:"));
        formPanel.add(patientBox);
        formPanel.add(new JLabel("Consultation Charges:"));
        formPanel.add(consultationField);
        formPanel.add(new JLabel("Medicine Charges:"));
        formPanel.add(medicineField);
        formPanel.add(new JLabel("Room Charges:"));
        formPanel.add(roomField);
        
        JButton generateButton = new JButton("Generate Bill");
        generateButton.setBackground(new Color(231, 76, 60));
        generateButton.setForeground(Color.WHITE);
        
        String[] columns = {"Bill ID", "Patient", "Total Amount", "Status", "Date", "Action"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5; // Only Action column editable
            }
        };
        JTable table = new JTable(tableModel);
        
        // Load existing bills
        for (Bill bill : bills) {
            tableModel.addRow(new Object[]{bill.getBillId(), bill.getPatient().getName(),
                    "₹" + bill.getTotalAmount(), bill.getPaymentStatus(), bill.getBillDate(), "Pay"});
        }
        
        // Add button column for payment using proper TableCellRenderer
        table.getColumn("Action").setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JButton btn = new JButton("Pay Bill");
                btn.setBackground(new Color(46, 204, 113));
                btn.setForeground(Color.WHITE);
                return btn;
            }
        });
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = table.getColumnModel().getColumnIndexAtX(e.getX());
                int row = e.getY() / table.getRowHeight();
                
                if (row < table.getRowCount() && row >= 0 && column == 5) {
                    String billId = (String) table.getValueAt(row, 0);
                    String currentStatus = (String) table.getValueAt(row, 3);
                    
                    if (currentStatus.equals("Paid")) {
                        JOptionPane.showMessageDialog(panel, "Bill already paid!");
                        return;
                    }
                    
                    // Find and update bill
                    for (Bill bill : bills) {
                        if (bill.getBillId().equals(billId)) {
                            bill.setPaymentStatus("Paid");
                            table.setValueAt("Paid", row, 3);
                            JOptionPane.showMessageDialog(panel, "Payment of ₹" + bill.getTotalAmount() + " received successfully!");
                            updateDashboard();
                            break;
                        }
                    }
                }
            }
        });
        
        generateButton.addActionListener(e -> {
            try {
                if (patients.isEmpty()) {
                    throw new HospitalException("Please add patients first!");
                }
                String id = "B" + String.format("%03d", billCounter++);
                double consultation = Double.parseDouble(consultationField.getText());
                double medicine = Double.parseDouble(medicineField.getText());
                double room = Double.parseDouble(roomField.getText());
                
                Bill bill = new Bill(id, patients.get(patientBox.getSelectedIndex()), consultation, medicine, room);
                bills.add(bill);
                tableModel.addRow(new Object[]{id, bill.getPatient().getName(),
                        "₹" + bill.getTotalAmount(), bill.getPaymentStatus(), bill.getBillDate(), "Pay"});
                clearFields(consultationField, medicineField, roomField);
                JOptionPane.showMessageDialog(this, "Bill generated! Total: ₹" + bill.getTotalAmount());
                updateDashboard();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        formPanel.add(new JLabel());
        formPanel.add(generateButton);
        
        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        
        return panel;
    }
    
    private void clearFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }
    
    // Refresh all registered patient combo boxes with current patient list
    private void refreshPatientComboBoxes() {
        for (JComboBox<String> cb : patientComboBoxes) {
            Object selected = cb.getSelectedItem(); // try to preserve selection
            cb.removeAllItems();
            for (Patient p : patients) {
                cb.addItem(p.getId() + " - " + p.getName());
            }
            if (selected != null) {
                cb.setSelectedItem(selected);
            }
        }
    }
    
    private int getPendingBillsCount() {
        int count = 0;
        for (Bill bill : bills) {
            if (bill.getPaymentStatus().equals("Unpaid")) count++;
        }
        return count;
    }
    
    private void updateDashboard() {
        tabbedPane.setComponentAt(0, createDashboardPanel());
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OjasviHospitalSystem system = new OjasviHospitalSystem();
            system.setVisible(true);
        });
    }
}
