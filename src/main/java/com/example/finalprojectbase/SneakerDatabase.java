package com.example.finalprojectbase;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.*;

/**
 * @author James Homan
 * The type Sneaker database.
 */
public class SneakerDatabase extends Application {
    private final TableView<Sneaker> table = new TableView<Sneaker>();
    private final ObservableList<Sneaker> sneakerData = FXCollections.observableArrayList();

    //Using the init() method to create the database and table if not already created
    @Override
    public void init() throws Exception {
        try (Connection connect = DriverManager.getConnection("jdbc:derby:SneakerDatabase; create = true");
             Statement state = connect.createStatement();
        ){
            System.out.println("Connected to database.");
            System.out.println("Statement created.");
            DatabaseMetaData dbm = connect.getMetaData();
            System.out.println("MetaData created.");
            ResultSet result = dbm.getTables(null, null, "SNEAKER_INFO", null);
            System.out.println("ResultSet created.");
            if (result.next()) {
                System.out.println("sneaker_info exists");
            } else {
                state.execute("create table sneaker_info(manufacturer varchar(100), type varchar(100), size double," +
                        "primaryColor varchar(100), secondaryColor varchar(100), clean boolean)");
                System.out.println("sneaker_info created");
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    //GUI components and event handlers for communicating with database
    @Override
    public void start(Stage primaryStage) throws Exception{
        //Header Label and settings
        Label title = new Label("Sneaker Information Form");
        title.setPrefWidth(850);
        title.setAlignment(Pos.CENTER);
        title.setPadding(new Insets(20, 0, 40, 0));

        // Form Labels
        Label lbManufacturer = new Label("Manufacturer's name");
        Label lbType = new Label("Sneaker type");
        Label lbSize = new Label("Sneaker size");
        Label lbPrimaryColor = new Label("Primary Color");
        Label lbSecondaryColor = new Label("Secondary Color");
        Label lbIsClean = new Label("Clean?");

        // Form TextFields
        TextField tfManufacturer = new TextField();
        TextField tfType = new TextField();
        TextField tfSize = new TextField();
        TextField tfPrimaryColor = new TextField();
        TextField tfSecondaryColor = new TextField();

        // Form RadioButtons
        RadioButton rbYes = new RadioButton("yes");
        RadioButton rbNo = new RadioButton("no");
        ToggleGroup tgVax = new ToggleGroup();
        rbNo.setToggleGroup(tgVax);
        rbYes.setToggleGroup(tgVax);

        // Form Buttons
        Button submit = new Button("Submit Record");
        Button view = new Button("View Table");
        Button notClean = new Button("Needs Clean");

        //Node font settings
        title.setFont(Font.font("Verdana", 28));

        lbManufacturer.setFont(Font.font("Verdana", 28));
        lbSize.setFont(Font.font("Verdana", 28));
        lbType.setFont(Font.font("Verdana", 28));
        lbPrimaryColor.setFont(Font.font("Verdana", 28));
        lbSecondaryColor.setFont(Font.font("Verdana", 28));
        lbIsClean.setFont(Font.font("Verdana", 28));

        tfManufacturer.setFont(Font.font("Verdana", 28));
        tfType.setFont(Font.font("Verdana", 28));
        tfSize.setFont(Font.font("Verdana", 28));
        tfPrimaryColor.setFont(Font.font("Verdana", 28));
        tfSecondaryColor.setFont(Font.font("Verdana", 28));

        rbNo.setFont(Font.font("Verdana", 28));
        rbYes.setFont(Font.font("Verdana", 28));

        submit.setFont(Font.font("Verdana", 28));
        view.setFont(Font.font("Verdana", 28));
        notClean.setFont(Font.font("Verdana", 28));

        //Form layout
        GridPane gp = new GridPane();

        gp.add(lbManufacturer, 0, 0);
        gp.add(tfManufacturer, 1, 0, 2, 1);
        gp.add(lbType, 0, 1);
        gp.add(tfType, 1, 1, 2, 1);
        gp.add(lbSize, 0, 2);
        gp.add(tfSize, 1, 2, 2, 1);
        gp.add(lbPrimaryColor, 0, 3);
        gp.add(tfPrimaryColor,1,3,2,1);
        gp.add(lbSecondaryColor, 0, 4);
        gp.add(tfSecondaryColor,1,4,2,1);
        gp.add(lbIsClean,0,5);
        gp.add(rbYes, 1, 5);
        gp.add(rbNo, 2, 5);
        gp.add(submit, 0, 6);
        gp.add(view, 1, 6);
        gp.add(notClean, 2, 6);
        gp.setVgap(30);
        gp.setHgap(20);

        //Main layout for header label and gridpane
        VBox mainLayout = new VBox(title, gp);
        mainLayout.setPadding(new Insets(20));

        //Configure tableview that will be displayed when user hits View button
        //Create each column with header text in quotes
        TableColumn<Sneaker, Object> manufacturerCol = new TableColumn<>("Manufacturer");
        TableColumn<Sneaker, Object> typeCol = new TableColumn<>("Type");
        TableColumn<Sneaker, Object> sizeCol = new TableColumn<>("Size");
        TableColumn<Sneaker, Object> primColorCol = new TableColumn<>("Primary Color");
        TableColumn<Sneaker, Object> secColorCol = new TableColumn<>("Secondary Color");
        TableColumn<Sneaker, Object> cleanCol = new TableColumn<>("Clean?");

        //Add TableColumns to the javafx TableView
        table.getColumns().add(manufacturerCol);
        table.getColumns().add(typeCol);
        table.getColumns().add(sizeCol);
        table.getColumns().add(primColorCol);
        table.getColumns().add(secColorCol);
        table.getColumns().add(cleanCol);

        table.setStyle("-fx-font-size: 30px; -fx-pref-width: 1300px");

        manufacturerCol.setStyle("-fx-font-size: 30px; -fx-pref-width: 250px");
        typeCol.setStyle("-fx-font-size: 30px; -fx-pref-width: 250px");
        sizeCol.setStyle("-fx-font-size: 30px; -fx-pref-width: 150px");
        primColorCol.setStyle("-fx-font-size: 30px; -fx-pref-width: 250px");
        secColorCol.setStyle("-fx-font-size: 30px; -fx-pref-width: 250px");
        cleanCol.setStyle("-fx-font-size: 30px; -fx-pref-width: 150px");

        Scene viewScene = new Scene(table);

        //Event handler for Submit button
        submit.setOnAction(e -> {
            try (Connection connect = DriverManager.getConnection("jdbc:derby:SneakerDatabase");
                 PreparedStatement addRecord = connect.prepareStatement("INSERT INTO sneaker_info VALUES(?, ?, ?, ?, ?, ?)");
            ){
                System.out.println("Connected to database.");
                System.out.println("Prepared Statement created.");
                String manufacturerName = tfManufacturer.getText();
                String sneakerType = tfType.getText();
                double sneakerSize = Double.parseDouble(tfSize.getText());
                String primColor = tfPrimaryColor.getText();
                String secColor = tfSecondaryColor.getText();
                boolean isClean = rbYes.isSelected();
                System.out.println("Data retrieved from controls.");
                addRecord.setString(1, manufacturerName);
                addRecord.setString(2, sneakerType);
                addRecord.setDouble(3, sneakerSize);
                addRecord.setString(4, primColor);
                addRecord.setString(5, secColor);
                addRecord.setBoolean(6, isClean);
                addRecord.executeUpdate();
                System.out.println("Added record to table");
                tfManufacturer.setText("");
                tfType.setText("");
                tfSize.setText("");
                tfPrimaryColor.setText("");
                tfSecondaryColor.setText("");
                rbYes.setSelected(true);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Successful Operation");
                alert.setHeaderText(null);
                alert.setContentText("Record Added.");
                alert.show();
            } catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        });

        view.setOnAction(e->{
            table.getItems().clear();
            try (Connection connect = DriverManager.getConnection("jdbc:derby:SneakerDatabase");
                 Statement state = connect.createStatement();
            ){
                //Connect to database and create statement for executing queries
                System.out.println("Connected to database.");
                System.out.println("Statement created.");
                //Using * will select all fields from the table
                ResultSet result = state.executeQuery("SELECT * FROM sneaker_info");
                System.out.println("ResultSet created.");
                //Loop through the results from the query and grab each value
                while (result.next()) {
                    String manufacturerName = result.getString("manufacturer");
                    String sneakerType = result.getString("type");
                    double size = result.getDouble("size");
                    String primColor = result.getString("primaryColor");
                    String secColor = result.getString("secondaryColor");
                    boolean cleanInfo = result.getBoolean("clean");
                    Sneaker currentSneaker = new Sneaker(manufacturerName,sneakerType,size,primColor,secColor,cleanInfo);
                    sneakerData.add(currentSneaker);
                    manufacturerCol.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
                    typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
                    sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
                    primColorCol.setCellValueFactory(new PropertyValueFactory<>("primaryColor"));
                    secColorCol.setCellValueFactory(new PropertyValueFactory<>("secondaryColor"));
                    cleanCol.setCellValueFactory(new PropertyValueFactory<>("clean"));
                    table.setItems(sneakerData);
                }
                System.out.println("Table populated.");
                Stage smallStage = new Stage();
                smallStage.setScene(viewScene);
                System.out.println("Scene set to stage.");
                smallStage.setTitle("All Records");
                smallStage.show();
            } catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        });

        notClean.setOnAction(e -> {
            try (Connection connect = DriverManager.getConnection("jdbc:derby:SneakerDatabase");
                 Statement state = connect.createStatement();
            ){
                System.out.println("Connected to database.");
                System.out.println("Statement created.");
                ResultSet result = state.executeQuery("SELECT * FROM sneaker_info WHERE clean = false");
                System.out.println("ResultSet created.");
                sneakerData.clear();
                //Loop through the results from the query and grab each value
                while (result.next()) {
                    String manufacturerName = result.getString("manufacturer");
                    String sneakerType = result.getString("type");
                    double size = result.getDouble("size");
                    String primColor = result.getString("primaryColor");
                    String secColor = result.getString("secondaryColor");
                    boolean cleanInfo = result.getBoolean("clean");
                    Sneaker currentSneaker = new Sneaker(manufacturerName,sneakerType,size,primColor,secColor,cleanInfo);
                    sneakerData.add(currentSneaker);
                    manufacturerCol.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
                    typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
                    sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
                    primColorCol.setCellValueFactory(new PropertyValueFactory<>("primaryColor"));
                    secColorCol.setCellValueFactory(new PropertyValueFactory<>("secondaryColor"));
                    cleanCol.setCellValueFactory(new PropertyValueFactory<>("clean"));
                    table.setItems(sneakerData);
                }
                System.out.println("Table populated.");
                Stage smallStage = new Stage();
                smallStage.setScene(viewScene);
                System.out.println("Scene set to stage.");
                smallStage.setTitle("Needs Cleaning Records");
                smallStage.show();
            } catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        });

        //Main scene and stage
        primaryStage.setTitle("Sneaker Information Entry Form");
        Scene scene = new Scene(mainLayout, 800, 675);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        try {
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
            super.stop();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
