package com.example.trackerexpense;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Addexpense {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/expense_tracker";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Akash@2004";
    @FXML
    private TextField categoryField;

    @FXML
    private TextField amountField;

    @FXML
    private Label expensestored;
    @FXML



    protected void backtohome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();





    }


    @FXML
    private void addexpense() {
        // Action for the "Add" button
        String category = categoryField.getText();
        String amount = amountField.getText();

        // Store data in the MySQL database
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String query = "INSERT INTO expenses(expenses,category) VALUES (?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, amount);
            preparedStatement.setString(2, category);
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Data inserted successfully!");
                expensestored.setVisible(true);
                amountField.clear();
                categoryField.clear();

            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }






}
