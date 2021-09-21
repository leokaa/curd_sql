package com.example.curd_sql;

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
import java.sql.Statement;

public class EditController {
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
    private Label LabelSTT;

    @FXML
    private Button BtnTroVe;

    @FXML
    private Button BtnAdd;


    public void viewStudent(Student student){
        LabelSTT.setText(String.valueOf(Integer.valueOf(student.getSTT())));
        GetHoTen.setText(String.valueOf(student.getHoTen()));
        GetMSSV.setText(String.valueOf(student.getMSSV()));
        GetDiaChi.setText(String.valueOf(student.getDiaChi()));
        GetEmail.setText(String.valueOf(student.getEmail()));
        GetSDT.setText(String.valueOf(student.getDienThoai()));
        GetNgaySinh.setText(String.valueOf(student.getDate()));
        GetCMND.setText(String.valueOf(student.getCMND()));
    }




    @FXML
    public void editStudent(ActionEvent event) throws IOException {

        Student student = new Student();

        //student.setSTT(LabelSTT.getText());
        student.setHoTen(GetHoTen.getText());
        student.setMSSV(GetMSSV.getText());
        student.setDiaChi(GetDiaChi.getText());
        student.setEmail(GetEmail.getText());
        student.setDienThoai(GetSDT.getText());
        student.setDate(GetNgaySinh.getText());
        student.setCMND(GetCMND.getText());

        String query = "UPDATE `user`.`student` SET `MSSV`='"+GetMSSV.getText()+"',`HoTen`='"+GetHoTen.getText()+"',`Email`='"+GetEmail.getText()+"',`SoDienThoai`='"+GetSDT.getText()+"',`NgaySinh`='"+GetNgaySinh.getText()+"',`CMND`='"+GetCMND.getText()+"'  WHERE  `STT`="+LabelSTT.getText();

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

    public void TroVe(ActionEvent event) throws Exception{
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("table_view.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
        stage.show();
    }
}
