package com.example.curd_sql;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CreateController {
    @FXML
    private TextField GetSDT;

    @FXML
    private TextField GetCMND;

    @FXML
    private TextField GetHoTen;

    @FXML
    private TextField GetMSSV;

    @FXML
    private TextField GetNgaySinh;

    @FXML
    private TextField GetEmail;

    @FXML
    private TextField GetDiaChi;

    @FXML
    public void addStudent(ActionEvent event) throws IOException {
        String query = "INSERT INTO student (`MSSV`, `HoTen`, `DiaChi`, `Email`, `SoDienThoai`, `NgaySinh`, `CMND`, `MaKhoa`) " +
                "VALUES ('"+GetMSSV.getText()+"', '"+GetHoTen.getText()+"', '"+GetDiaChi.getText()+"', '"+GetEmail.getText()+"', '"+GetSDT.getText()+"', '"+GetNgaySinh.getText()+"', '"+GetCMND.getText()+"', 'CNTT');";

        DatabaseConnection connectionNow = new DatabaseConnection();
        Connection connectionDB = connectionNow.getConnection();
        Statement st;
        try{
            st = connectionDB.createStatement();
            st.executeUpdate(query);
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("table_view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}