module com.example.curd_sql {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.curd_sql to javafx.fxml;
    exports com.example.curd_sql;
}