#!/bin/bash
echo "=== Compiling Student Management System ==="
javac -cp .:lib/mysql-connector-j-8.4.0.jar src/StudentManagementSystem.java
if [ $? -eq 0 ]; then
    echo "✅ Compilation successful!"
    echo "Class files created in src/ directory"
else
    echo "❌ Compilation failed!"
    echo "Please check the error messages above"
fi
