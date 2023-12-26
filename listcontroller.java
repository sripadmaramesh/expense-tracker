package com.example.trackerexpense;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;







public class listcontroller {


    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/expense_tracker";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Akash@2004";

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML



    protected void backtohome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();





    }

    @FXML
    private TableView<String> tableView;


    @FXML
    public  void showAllTables() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});

            ObservableList<String> tableNames = FXCollections.observableArrayList();


            System.out.println("Tables in the database:");
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                tableNames.add(tableName);

            }


            TableColumn<String, String> columnName = new TableColumn<>("Tables");
            columnName.setCellValueFactory(new PropertyValueFactory<>(""));
            tableView.getColumns().setAll(columnName);
            tableView.setItems(tableNames);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}






