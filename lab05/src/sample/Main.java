package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane myPane = (Pane)FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Lab 05 - Solutions");
        Scene myScene = new Scene(myPane);
        primaryStage.setScene(myScene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
