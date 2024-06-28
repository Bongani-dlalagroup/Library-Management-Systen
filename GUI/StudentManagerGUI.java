package GUI;

import javax.swing.*;

import database.StudentDAO;
import model.Student;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class StudentManagerGUI extends JFrame {
    private JTextField idField;
    private JTextField nameField;
    private JTextField mobileField;
    private StudentDAO studentDAO;

    public StudentManagerGUI() {
        studentDAO = new StudentDAO();
        createUI();
    }

    private void createUI() {

        setTitle("Student Manager");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());


        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField(15);
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(15);
        JLabel mobileLabel = new JLabel("Mobile:");
        mobileField = new JTextField(15);
        JButton addButton = new JButton("Add");
        JButton viewButton = new JButton("View Stud");
        JButton searchButton = new JButton("Search");
        JButton deleteButton = new JButton("Delete");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(idLabel, gbc);
        
        gbc.gridx = 1;
        add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(nameLabel, gbc);

        gbc.gridx = 1;
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(mobileLabel, gbc);

        gbc.gridx = 1;
        add(mobileField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, gbc);

        // Button Action Listeners
        addButton.addActionListener(new AddAction());
        viewButton.addActionListener(new ViewAction());
        searchButton.addActionListener(new SearchAction());
        deleteButton.addActionListener(new DeleteAction());
    }

    private class AddAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                Student student = new Student(idField.getText(), nameField.getText(), mobileField.getText());
                studentDAO.addStudent(student);
                JOptionPane.showMessageDialog(StudentManagerGUI.this, "Student added successfully.");
                clearFields();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(StudentManagerGUI.this, "Failed to add student.");
            }
        }
    }

    private class ViewAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                List<Student> students = studentDAO.getAllStudents();
                if (students.isEmpty()) {
                    JOptionPane.showMessageDialog(StudentManagerGUI.this, "No students to display.");
                    return;
                }
                StringBuilder sb = new StringBuilder();
                for (Student student : students) {
                    sb.append(student).append("\n");
                }
                JOptionPane.showMessageDialog(StudentManagerGUI.this, sb.toString());
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(StudentManagerGUI.this, "Failed to retrieve students.");
            }
        }
    }

    private class SearchAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String id = idField.getText();
                Student student = studentDAO.getStudentById(id);
                if (student != null) {
                    JOptionPane.showMessageDialog(StudentManagerGUI.this,
                            "ID: " + student.getId() + "\nName: " + student.getName() + "\nMobile: " + student.getMobile());
                } else {
                    JOptionPane.showMessageDialog(StudentManagerGUI.this, "Student not found.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(StudentManagerGUI.this, "Failed to search for student.");
            }
        }
    }

    private class DeleteAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String id = idField.getText();
                studentDAO.deleteStudent(id);
                JOptionPane.showMessageDialog(StudentManagerGUI.this, "Student deleted successfully.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(StudentManagerGUI.this, "Failed to delete student.");
            }
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        mobileField.setText("");
    }
}

