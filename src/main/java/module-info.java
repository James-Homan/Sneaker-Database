module com.example.finalprojectbase {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.finalprojectbase to javafx.fxml;
    exports com.example.finalprojectbase;


}