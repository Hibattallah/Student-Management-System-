import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StudentManagementSystem extends JFrame implements ActionListener {
    JLabel title, studentNameLabel, studentIDLabel, studentGradeLabel, dobLabel, genderLabel, contactLabel, emailLabel;
    JTextField studentNameField, studentIDField, studentGradeField, dobField, contactField, emailField, searchField;
    JRadioButton maleRadio, femaleRadio;
    ButtonGroup genderGroup;
    JButton addStudent, reset, deleteRecord;
    JTable studentTable;
    DefaultTableModel tableModel;

    // Database credentials - UPDATED FOR YOUR SYSTEM
    private static final String DB_URL = "jdbc:mysql://localhost:3306/student";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Root@12345"; // Updated to your password
    private Connection connection;

    public StudentManagementSystem() {
        // Frame setup - Fixed window title to match your image
        setTitle("Student Management System");
        setLayout(null);
        setSize(1000, 700); // Slightly taller for better spacing
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Title - matches your image exactly (no "by Group 5")
        title = new JLabel("STUDENT MANAGEMENT SYSTEM");
        title.setBounds(300, 20, 500, 40);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title);
        
        // Labels (exact labels from your image)
        studentNameLabel = new JLabel("Student Name");
        studentNameLabel.setBounds(50, 80, 150, 30);
        add(studentNameLabel);
        
        studentIDLabel = new JLabel("Student ID");
        studentIDLabel.setBounds(50, 130, 150, 30);
        add(studentIDLabel);
        
        studentGradeLabel = new JLabel("Student Grade");
        studentGradeLabel.setBounds(50, 180, 150, 30);
        add(studentGradeLabel);
        
        dobLabel = new JLabel("Date of Birth");
        dobLabel.setBounds(50, 230, 150, 30);
        add(dobLabel);
        
        genderLabel = new JLabel("Gender");
        genderLabel.setBounds(50, 280, 150, 30);
        add(genderLabel);
        
        contactLabel = new JLabel("Contact Name");
        contactLabel.setBounds(50, 330, 150, 30);
        add(contactLabel);
        
        emailLabel = new JLabel("Email");
        emailLabel.setBounds(50, 380, 150, 30);
        add(emailLabel);
        
        // Text fields
        studentNameField = new JTextField();
        studentNameField.setBounds(200, 80, 200, 30);
        add(studentNameField);
        
        studentIDField = new JTextField();
        studentIDField.setBounds(200, 130, 200, 30);
        add(studentIDField);
        
        studentGradeField = new JTextField();
        studentGradeField.setBounds(200, 180, 200, 30);
        add(studentGradeField);
        
        dobField = new JTextField();
        dobField.setBounds(200, 230, 200, 30);
        add(dobField);
        
        // Gender radio buttons (exact from image)
        maleRadio = new JRadioButton("Male");
        maleRadio.setBounds(200, 280, 80, 30);
        femaleRadio = new JRadioButton("Female");
        femaleRadio.setBounds(290, 280, 100, 30);
        
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        add(maleRadio);
        add(femaleRadio);
        
        contactField = new JTextField();
        contactField.setBounds(200, 330, 200, 30);
        add(contactField);
        
        emailField = new JTextField();
        emailField.setBounds(200, 380, 200, 30);
        add(emailField);
        
        // Buttons (exact labels from image)
        addStudent = new JButton("Add Student");
        addStudent.setBounds(650, 80, 150, 30);
        add(addStudent);
        
        reset = new JButton("Reset");
        reset.setBounds(650, 130, 150, 30);
        add(reset);
        
        deleteRecord = new JButton("Delete Record");
        deleteRecord.setBounds(650, 180, 150, 30);
        add(deleteRecord);
        
        // Search field and label
        JLabel searchLabel = new JLabel("Search by ID");
        searchLabel.setBounds(50, 430, 150, 30);
        add(searchLabel);
        
        searchField = new JTextField();
        searchField.setBounds(200, 430, 300, 30);
        add(searchField);
        
        // Table with exact column headers from your image
        String[] columnNames = {
            "Student Name", 
            "Student ID", 
            "Student Grade", 
            "Date of Birth", 
            "Gender", 
            "Contact Name", 
            "Email"
        };
        
        tableModel = new DefaultTableModel(columnNames, 0);
        studentTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setBounds(50, 480, 900, 150);
        add(scrollPane);
        
        // Add action listeners
        addStudent.addActionListener(this);
        reset.addActionListener(this);
        deleteRecord.addActionListener(this);
        searchField.addActionListener(this); // Search on enter
        
        // Connect to database
        connectToDatabase();
        loadStudentDataFromDatabase();
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addStudent) {
            addStudentAction();
        } else if (e.getSource() == reset) {
            resetAction();
        } else if (e.getSource() == deleteRecord) {
            deleteRecordAction();
        } else if (e.getSource() == searchField) {
            searchAction();
        }
    }
    
    private void addStudentAction() {
        String name = studentNameField.getText();
        String id = studentIDField.getText();
        String grade = studentGradeField.getText();
        String dob = dobField.getText();
        String contact = contactField.getText();
        String email = emailField.getText();
        String gender = maleRadio.isSelected() ? "Male" : 
                       femaleRadio.isSelected() ? "Female" : "";
        
        // Validation
        if (name.isEmpty() || id.isEmpty() || grade.isEmpty() || dob.isEmpty() || 
            contact.isEmpty() || email.isEmpty() || gender.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Invalid email address.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!isValidDate(dob)) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Use dd-MM-yyyy", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Add to table
        String[] rowData = {name, id, grade, dob, gender, contact, email};
        tableModel.addRow(rowData);
        
        // Insert into database
        insertStudentData(name, id, grade, dob, gender, contact, email);
        
        // Clear fields
        resetAction();
    }
    
    private void resetAction() {
        studentNameField.setText("");
        studentIDField.setText("");
        studentGradeField.setText("");
        dobField.setText("");
        genderGroup.clearSelection();
        contactField.setText("");
        emailField.setText("");
    }
    
    private void deleteRecordAction() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow >= 0) {
            String studentID = tableModel.getValueAt(selectedRow, 1).toString();
            tableModel.removeRow(selectedRow);
            deleteStudentData(studentID);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void searchAction() {
        String searchId = searchField.getText();
        if (searchId.isEmpty()) {
            return;
        }
        
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 1).toString().equals(searchId)) {
                studentTable.setRowSelectionInterval(i, i);
                studentTable.scrollRectToVisible(studentTable.getCellRect(i, 0, true));
                return;
            }
        }
        
        JOptionPane.showMessageDialog(this, "Student ID not found.", "Info", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Validation methods
    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
    
    private boolean isValidDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            sdf.setLenient(false);
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    
    // Database methods
    private void connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connected to database successfully!");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(this, 
                "Failed to connect to database: " + e.getMessage(), 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void insertStudentData(String name, String id, String grade, String dob, 
                                   String gender, String contact, String email) {
        String query = "INSERT INTO students (student_name, student_id, student_grade, dob, gender, contact, email) " +
                      "VALUES (?, ?, ?, STR_TO_DATE(?, '%d-%m-%Y'), ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, id);
            stmt.setString(3, grade);
            stmt.setString(4, dob);
            stmt.setString(5, gender);
            stmt.setString(6, contact);
            stmt.setString(7, email);
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Student added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { // Duplicate entry
                JOptionPane.showMessageDialog(this, "Student ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error adding student: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void loadStudentDataFromDatabase() {
        String query = "SELECT student_name, student_id, student_grade, DATE_FORMAT(dob, '%d-%m-%Y'), gender, contact, email FROM students";
        
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                String[] rowData = {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7)
                };
                tableModel.addRow(rowData);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading students: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteStudentData(String studentId) {
        String query = "DELETE FROM students WHERE student_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, studentId);
            int rows = stmt.executeUpdate();
            
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Student deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error deleting student: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentManagementSystem();
        });
    }
}
