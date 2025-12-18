#!/bin/bash
echo "=== Starting Student Management System ==="
echo "Make sure MySQL is running on localhost:3306"
echo "Database: student, User: root, Password: Root@12345"
echo ""
java -cp .:lib/mysql-connector-j-8.4.0.jar:src StudentManagementSystem
