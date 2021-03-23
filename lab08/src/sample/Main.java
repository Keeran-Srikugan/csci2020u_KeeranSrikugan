package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public Parent root;


    @Override
    public void start(Stage primaryStage) throws Exception{

        //loading scene fxml
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("sample.fxml"));

        root = loader.load();

        Controller ctrl = (Controller)(loader.getController());
        primaryStage.setTitle("Lab 08");




        //File menu
        Menu menu = new Menu("File");


        //Menu Items:
        MenuItem menuitem1 = new MenuItem("new");
        menu.getItems().add(menuitem1);

        MenuItem menuitem2 = new MenuItem("Open");
        menu.getItems().add(menuitem2);

        MenuItem menuitem3 = new MenuItem("Save");
        menu.getItems().add(menuitem3);

        MenuItem menuitem4 = new MenuItem("Save As");
        menu.getItems().add(menuitem4);

        MenuItem menuitem5 = new MenuItem("Exit");
        menu.getItems().add(menuitem5);



        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menu);

        BorderPane layout = new BorderPane();
        layout.setTop(menuBar);
        layout.setCenter(root);

        menuitem1.setOnAction(e->{
            ctrl.newFile();
        });

        menuitem3.setOnAction(e->{
            try {
                ctrl.save();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        menuitem4.setOnAction(e->{
            try {
                ctrl.saveAs(primaryStage);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        menuitem5.setOnAction(e->{
            try {
                ctrl.exit();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        primaryStage.setScene(new Scene(layout, 485, 600));
        primaryStage.show();



    }





    public static void main(String[] args) {
        launch(args);
    }
}
