package com.example.curd_sql;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TableViewController implements Initializable {
    @FXML
    private Label welcomeText;

    @FXML
    private Button BtnThem;

    @FXML
    private TableColumn<Student, String> EmailColumn;


    @FXML
    private TableColumn<Student, String> DiaChiColumn;

    @FXML
    private TableColumn<Student, Integer> STTColumn;

    //sssssss


    @FXML
    private TableView<Student> tableView;

    public TableView<Student> getTableView() {
        return tableView;
    }

    public void setTableView(TableView<Student> tableView) {
        this.tableView = tableView;
    }

    public TableView getTable(){
        return tableView;
    }

    @FXML
    private TableColumn<Student, String> HoTenColumn;

    @FXML
    private TableColumn<Student, String> MSSVColumn;


    @FXML
    private TableColumn<Student, String> DienThoaiColumn;

    @FXML
    private TableColumn<Student, String> KhoaColumn;

    @FXML
    private TableColumn<Student, String> ThucThiColumn;



    @FXML
    private TableColumn<Student, Button> BtnViewColumn;

    Student student = null ;
    PreparedStatement preparedStatement = null ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*ObservableList<Student> studentList;
        studentList = FXCollections.observableArrayList(
                new Student("B1807644","Linh","Can Tho","linh@gamil.com","3213213","2222","222","CN"),
                new Student("B1807644","Linh2","Can Tho","linh@gamil.com","3213213","2222","222","CNTT")
        );
        MSSVColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("MSSV"));
        HoTenColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("HoTen"));
        DiaChiColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("DiaChi"));
        EmailColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("Email"));
        DienThoaiColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("DienThoai"));
        KhoaColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("Khoa"));
        BtnDleColumn.setCellValueFactory(new PropertyValueFactory<Student,Button>("BtnDle"));
        BtnEditColumn.setCellValueFactory(new PropertyValueFactory<Student,Button>("BtnEdit"));
        BtnViewColumn.setCellValueFactory(new PropertyValueFactory<Student,Button>("BtnView"));

        tableView.setItems(studentList);*/

        showTable();



    }

    public ObservableList<Student> getStudent(){
        ObservableList<Student> studentList = FXCollections.observableArrayList();
        DatabaseConnection connectionNow = new DatabaseConnection();
        Connection connectionDB = connectionNow.getConnection();

        String query = "SELECT *FROM student;";
        Statement st;
        ResultSet rs;

        try{
            st = connectionDB.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()){
                student = new Student(rs.getInt("STT"),rs.getString("MSSV"),rs.getString("HoTen"),rs.getString("DiaChi"),rs.getString("Email"),rs.getString("SoDienThoai"),rs.getString("NgaySinh"),rs.getString("CMND"),rs.getString("MaKhoa"));
                studentList.add(student);
            }

        }catch (Exception exception){
            exception.printStackTrace();
            exception.getCause();
        }
        return studentList;

    }

    private void excuteQuery(String query){
        DatabaseConnection connectionNow = new DatabaseConnection();
        Connection connectionDB = connectionNow.getConnection();
        Statement st;
        try{
            st = connectionDB.createStatement();
            st.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


    public void showTable(){
        ObservableList<Student> studentList = getStudent();

        STTColumn.setCellValueFactory(new PropertyValueFactory<Student,Integer>("STT"));
        MSSVColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("MSSV"));
        HoTenColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("HoTen"));
        DiaChiColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("DiaChi"));
        EmailColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("Email"));
        DienThoaiColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("DienThoai"));
        KhoaColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("Khoa"));

        //BtnDleColumn.setCellValueFactory(new PropertyValueFactory<Student,Button>("BtnDle"));
       // BtnEditColumn.setCellValueFactory(new PropertyValueFactory<Student,Button>("BtnEdit"));
        //BtnViewColumn.setCellValueFactory(new PropertyValueFactory<Student,Button>("BtnView"));
        Callback<TableColumn<Student,String>, TableCell<Student,String>> cellFoctory = ( TableColumn<Student,String> param )-> {
            final TableCell<Student,String> cell = new TableCell<Student,String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty){
                        setGraphic(null);

                    }
                    else {
                        //TableCell<SinhVien, String> cell = new TableCell<>();
                        Button viewButton = new Button("View");
                        Button editButton = new Button("Edit");
                        Button deleteButton = new Button("Delete");
                        HBox manageButton = new HBox(viewButton, editButton, deleteButton);
                        manageButton.setStyle("-fx-alignment:center");
                        setGraphic(manageButton);

                        deleteButton.setOnMouseClicked((MouseEvent event) -> {
                            try {
                                student = tableView.getSelectionModel().getSelectedItem();
                                System.out.println(student);

                                Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("TEST ALERT");
                                alert.setHeaderText("Bạn có chắc muốn xóa sinh viên: "+student.getHoTen()+" ?");
                                ButtonType btT1 = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                                ButtonType btT2 = new ButtonType("No",ButtonBar.ButtonData.NO);

                                alert.getButtonTypes().setAll(btT1,btT2);
                                Optional<ButtonType> result = alert.showAndWait();

                                if(result.get().getButtonData()==ButtonBar.ButtonData.YES){
                                    //Student selectedItem = tableView.getSelectionModel().getSelectedItem();
                                    String query = "DELETE FROM `student` WHERE stt  = "+student.getSTT();
                                    DatabaseConnection connectionNow = new DatabaseConnection();
                                    Connection connectionDB = connectionNow.getConnection();
                                    preparedStatement = connectionDB.prepareStatement(query);
                                    preparedStatement.execute();
                                    showTable();
                                }

                            } catch (SQLException ex) {
                                Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });

                        viewButton.setOnMouseClicked((MouseEvent event) -> {
                            try {
                                student = tableView.getSelectionModel().getSelectedItem();
                                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("s_view.fxml"));
                                Parent sampleParent = loader.load();
                                ViewController controller = loader.getController();
                                controller.viewStudent(student);
                                Scene scene = new Scene(sampleParent);
                                stage.setScene(scene);
                                stage.show();

                            } catch (Exception ex) {
                               ex.printStackTrace();
                               ex.getCause();
                            }
                        });

                        editButton.setOnMouseClicked((MouseEvent event) -> {
                            try {
                                student = tableView.getSelectionModel().getSelectedItem();
                                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("edit.fxml"));
                                Parent sampleParent = loader.load();

                                EditController controller = loader.getController();
                                controller.viewStudent(student);
                                //controller.get(student);
                                Scene scene = new Scene(sampleParent);
                                stage.setScene(scene);
                                stage.show();

                            } catch (Exception ex) {
                                ex.printStackTrace();
                                ex.getCause();
                            }
                        });


                    }
                    setText(null);
                }
            };
            return cell;

        };
        ThucThiColumn.setCellFactory(cellFoctory);
        tableView.setItems(studentList);

        }






    public void sceneCreate(ActionEvent event) throws Exception{
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Trang them sinh vien");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("s_create.fxml"));
        Parent sampleParent = loader.load();
        //CreateController controller = loader.getController();
        //Student student = new Student();
        //controller.setStudent(student);

        //Map<Integer,Student> stu = new HashMap<Integer,Student>();
        //tableView.getItems().add( stu.put(1,student));

        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
        stage.show();
    }


}
