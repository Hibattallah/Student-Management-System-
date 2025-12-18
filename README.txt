==================================================
      STUDENT MANAGEMENT SYSTEM - GROUP 5
==================================================

PROJECT OVERVIEW
----------------
A Java Swing application with MySQL integration for managing student records.
The application provides full CRUD (Create, Read, Update, Delete) operations
with data validation and persistent storage.

TEAM INFORMATION
----------------
- Course: [Insert Course Name Here]
- Group Number: 5
- Submission Date: $(date +"%B %d, %Y")
- Developed by: [Insert Your Name(s) Here]

FEATURES
--------
✓ Add new student records with form validation
✓ Display all records in a scrollable table
✓ Delete selected student records
✓ Search functionality by Student ID
✓ Form reset capability
✓ Gender selection via radio buttons
✓ MySQL database persistence
✓ Input validation (email, date format, required fields)

TECHNICAL REQUIREMENTS
----------------------
- Java Development Kit (JDK) 8 or higher
- MySQL Server 8.0 or higher
- MySQL Connector/J 8.4.0 (included)
- Minimum 2GB RAM
- 500MB free disk space

FILES INCLUDED
---------------
📁 src/
   └── StudentManagementSystem.java    # Main application source code

📁 lib/
   └── mysql-connector-j-8.4.0.jar     # MySQL JDBC driver

📁 screenshots/
   ├── 01_main_interface.png           # Main application window
   ├── 02_add_student.png              # Adding a new student
   ├── 03_search_function.png          # Search functionality demo
   ├── 04_delete_record.png            # Record deletion process
   └── 05_database_structure.png       # MySQL database schema

📄 compile.sh                          # Compilation script (Linux/Mac)
📄 run.sh                              # Execution script (Linux/Mac)
📄 README.txt                          # This documentation file

INSTALLATION INSTRUCTIONS
-------------------------

STEP 1: DATABASE SETUP
----------------------
1. Start MySQL server:
   sudo systemctl start mysql   # For Ubuntu/Linux
   # or start MySQL service via your system's method

2. Login to MySQL as root:
   mysql -u root -p

3. Create the database and table:
   CREATE DATABASE student;
   USE student;
   
   CREATE TABLE students (
       id INT AUTO_INCREMENT PRIMARY KEY,
       student_name VARCHAR(255),
       student_id VARCHAR(255) UNIQUE,
       student_grade VARCHAR(255),
       dob DATE,
       gender VARCHAR(10),
       contact VARCHAR(20),
       email VARCHAR(255)
   );

STEP 2: APPLICATION SETUP
-------------------------
1. Extract the project folder to your desired location

2. Open terminal/command prompt in the project directory

3. Make the scripts executable (Linux/Mac):
   chmod +x compile.sh run.sh

4. Compile the application:
   ./compile.sh
   # OR manually:
   javac -cp .:lib/mysql-connector-j-8.4.0.jar src/StudentManagementSystem.java

STEP 3: RUN THE APPLICATION
---------------------------
./run.sh
# OR manually:
java -cp .:lib/mysql-connector-j-8.4.0.jar:src StudentManagementSystem

USER GUIDE

ADDING A STUDENT:
1. Fill in all fields in the form:
   - Student Name
   - Student ID (unique)
   - Student Grade
   - Date of Birth (dd-mm-yyyy format)
   - Select Gender (Male/Female)
   - Contact Name
   - Email Address
2. Click "Add Student" button
3. Success message will appear upon saving

SEARCHING FOR A STUDENT:
1. Type the Student ID in the "Search by ID" field
2. Press Enter key
3. The matching record will be highlighted in the table

DELETING A RECORD:
1. Click on any row in the table to select it
2. Click "Delete Record" button
3. Confirm the deletion when prompted

RESETTING THE FORM:
Click "Reset" button to clear all input fields

VALIDATION RULES
----------------
- All fields are required
- Email must be valid format (user@domain.com)
- Date of Birth must be in dd-mm-yyyy format
- Student ID must be unique
- Contact Name should contain only numbers

SCREENSHOTS
-----------
Refer to the screenshots/ folder for visual documentation:
1. 01_main_interface.png - Clean, organized main interface
2. 02_add_student.png - Example of adding a student with validation
3. 03_search_function.png - Search feature demonstration
4. 04_delete_record.png - Record deletion process
5. 05_database_structure.png - MySQL table structure

TROUBLESHOOTING
---------------

COMMON ISSUES & SOLUTIONS:

1. "Class not found" or compilation error:
   - Ensure JDK is installed: java -version
   - Verify MySQL connector JAR is in lib/ folder
   - Check file permissions: chmod +x compile.sh

2. Database connection failed:
   - Ensure MySQL service is running
   - Verify database credentials in code
   - Check if 'student' database exists

3. Application starts but no data appears:
   - Check if students table exists in database
   - Verify table structure matches expected schema
   - Ensure data was inserted successfully

4. Buttons/fields not working:
   - Recompile the application
   - Check for error messages in terminal
   - Verify all required fields are filled

DEVELOPMENT NOTES
-----------------
- Built using Java Swing for GUI
- MySQL for persistent data storage
- JDBC for database connectivity
- MVC (Model-View-Controller) pattern followed
- Error handling implemented throughout
- User-friendly feedback messages

TESTING
-------
The application has been tested for:
✓ Form validation
✓ Database operations (CRUD)
✓ Search functionality
✓ Error handling
✓ User interface responsiveness

SUPPORT
-------
For technical support or questions about this project, contact:
- [Insert Your Email Here]
- [Insert Course Instructor Email Here]

ACKNOWLEDGMENTS
---------------
- University: [Insert University Name]
- Course Instructor: [Insert Instructor Name]
- Java Swing Documentation
- MySQL Official Documentation

==================================================
         END OF DOCUMENTATION
==================================================
