package com.example.trackerexpense;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Setbudget{
    private Stage stage;
    private Scene scene;
    private Parent root;

    // Your database credentials
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/expense_tracker";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Akash@2004";

    @FXML
    private TextField budgetField;

    @FXML
    private TextField monthField;

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label successLabel;


    @FXML
    private void backtohome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    private void addBudget() {
        // Action for the "Add" button
        String budget = budgetField.getText();
        String month = monthField.getText();

        // Store data in the MySQL database
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String query = "INSERT INTO budget(budget, month) VALUES (?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, budget);
            preparedStatement.setString(2, month);
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Data inserted successfully!");
                successLabel.setVisible(true);
                budgetField.clear();
                monthField.clear();

            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
