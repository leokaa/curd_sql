package com.example.curd_sql;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Student {
    private Student student;

    private int STT;
    private String MSSV;
    private String DiaChi;
    private String HoTen;
    private String Email;
    private String DienThoai;

    private String Date;
    private String CMND;

    private String Khoa;

    private Button BtnDle;
    private Button BtnEdit;
    private Button BtnView;

    String tempStr = "";
    int temp=0;

    public Student(int STT,String MSSV, String hoTen,String diaChi, String email, String dienThoai, String date, String CMND,String Khoa) {
        this.STT = STT;
        this.MSSV = MSSV;
        this.DiaChi = diaChi;
        this.HoTen = hoTen;
        this.Email = email;
        this.DienThoai = dienThoai;
        this.Date = date;
        this.CMND = CMND;
        this.Khoa = Khoa;
        if(Khoa.equals("CN")){
            this.Khoa = new String("Công Nghệ");
        }
        if(Khoa.equals("CNTT")){
            this.Khoa = new String("Công Nghệ Thông Tin");
        }
        if(Khoa.equals("NN")){
            this.Khoa = new String("Nông Nghiệp");
        }


    }

    public Student() {

    }

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public String getKhoa() {
        return Khoa;
    }

    public void setKhoa(String khoa) {
        Khoa = khoa;
    }

    public String getMSSV() {
        return MSSV;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDienThoai() {
        return DienThoai;
    }

    public void setDienThoai(String dienThoai) {
        DienThoai = dienThoai;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public Button getBtnDle() {
        return BtnDle;
    }

    public void setBtnDle(Button btnDle) {
        BtnDle = btnDle;
    }

    public Button getBtnEdit() {
        return BtnEdit;
    }

    public void setBtnEdit(Button btnEdit) {
        BtnEdit = btnEdit;
    }

    public Button getBtnView() {
        return BtnView;
    }

    public void setBtnView(Button btnView) {
        BtnView = btnView;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Student{" +
                "student=" + student +
                ", STT='" + STT + '\'' +
                ", MSSV='" + MSSV + '\'' +
                ", DiaChi='" + DiaChi + '\'' +
                ", HoTen='" + HoTen + '\'' +
                ", Email='" + Email + '\'' +
                ", DienThoai='" + DienThoai + '\'' +
                ", Date='" + Date + '\'' +
                ", CMND='" + CMND + '\'' +
                ", BtnDle=" + BtnDle +
                ", BtnEdit=" + BtnEdit +
                ", BtnView=" + BtnView +
                '}';
    }
}
