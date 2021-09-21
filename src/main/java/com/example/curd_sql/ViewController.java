package com.example.curd_sql;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ViewController {
    @FXML
    private Label GetSDT;

    @FXML
    private Label GetCMND;

    @FXML
    private Label GetHoTen;

    @FXML
    private Label GetMSSV;

    @FXML
    private Label GetNgaySinh;

    @FXML
    private Label GetEmail;

    @FXML
    private Label GetDiaChi;

    @FXML
    private Button BtnTroVe;

    public void viewStudent(Student student){
        GetHoTen.setText(String.valueOf(student.getHoTen()));
        GetMSSV.setText(String.valueOf(student.getMSSV()));
        GetDiaChi.setText(String.valueOf(student.getDiaChi()));
        GetEmail.setText(String.valueOf(student.getEmail()));
        GetSDT.setText(String.valueOf(student.getDienThoai()));
        GetNgaySinh.setText(String.valueOf(student.getDate()));
        GetCMND.setText(String.valueOf(student.getCMND()));
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
