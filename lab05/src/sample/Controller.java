package sample;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class Controller {
    @FXML private TableView myTable;

    //Below these are the values that will be inputted into their respective columns
    @FXML private TableColumn SID;
    @FXML private TableColumn midterm;
    @FXML private TableColumn assignments;
    @FXML private TableColumn finalExam;
    @FXML private TableColumn finalMark;
    @FXML private TableColumn letterGrade;

    public void initialize(){

        //The code below associates the data from the class
        SID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        midterm.setCellValueFactory(new PropertyValueFactory<>("midterm"));
        assignments.setCellValueFactory(new PropertyValueFactory<>("assignments"));
        finalExam.setCellValueFactory(new PropertyValueFactory<>("finalExam"));
        letterGrade.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));
        finalMark.setCellValueFactory(new PropertyValueFactory<>("finalMark"));

        //This is the line of code that sets the items by calling the getAllMarks() function from DataSource.java
        myTable.setItems(DataSource.getAllMarks());
    }
}