package sample;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;
import java.util.Scanner;


//Made by: Keeran Srikugan
//Date: March 23, 2021
//Note: the open function and add function are not fully implemented, but everything else works

public class Controller {
    @FXML private TableView myTable;
    public String fileName = "dataFile.txt";


    //Below these are the variables that are associated to the colums int he sample.fxml file
    @FXML private TableColumn<Object, Object> SID;
    @FXML private TableColumn<Object, Object> midterm;
    @FXML private TableColumn<Object, Object> assignments;
    @FXML private TableColumn<Object, Object> finalExam;
    @FXML private TableColumn<Object, Object> finalMark;
    @FXML private TableColumn<Object, Object> letterGrade;

    @FXML private TextField sid;
    @FXML private TextField assignment;
    @FXML private TextField midterms;
    @FXML private TextField finalExams;

    public void initialize(){

        //The code below associates the data from the class to their respective columns
        //This is the youtube video I used to help with associating the data to the chart(For future reference):https://www.youtube.com/watch?v=4RNhPZJ84P0&t=415s
        SID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        midterm.setCellValueFactory(new PropertyValueFactory<>("midterm"));
        assignments.setCellValueFactory(new PropertyValueFactory<>("assignments"));
        finalExam.setCellValueFactory(new PropertyValueFactory<>("finalExam"));
        letterGrade.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));
        finalMark.setCellValueFactory(new PropertyValueFactory<>("finalMark"));

        //This is the line of code that sets the items by calling the getAllMarks() function from DataSource.java file
        myTable.setItems(DataSource.getAllMarks());
    }


    //This function saves the values from the table into a file
    public void save() throws IOException {
        int length = DataSource.getAllMarks().size();
        try (BufferedWriter bw = new BufferedWriter(new PrintWriter(fileName))) {
            for (int i = 0; i < length; i++) {
                StudentRecord student = (StudentRecord) myTable.getItems().get(i);
                bw.write(student.getStudentID() + "," + student.getAssignments() + "," + student.getMidterm() + "," + student.getFinalExam());
                bw.newLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //This function chooses the file destination, then the save function is called
    public void saveAs(Stage currentStage) throws IOException {
        Stage mainStage = currentStage;

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose csv file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(mainStage);
        if(selectedFile != null){
            fileName = selectedFile.getName();
            save();
        }

    }

    //This clears the entire table
    public void newFile(){
        myTable.getItems().clear();
    }

    //This is the exit function
    public void exit() throws IOException {
        System.exit(0);
    }

    //Here I load the values from a file to a table
    public void load(){

    }


    //This is where I set the values into the table
    public void handleAddButtonAction(ActionEvent actionEvent) {

    }
}