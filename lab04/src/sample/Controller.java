package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Controller {
    @FXML
    private Text actiontarget;

    @FXML
    private TextField FullNameField;

    @FXML
    private TextField EmailField;

    @FXML
    private TextField PhoneNumberField;

    @FXML
    private DatePicker DateField;

    public void handleSubmitButtonAction(ActionEvent actionEvent) {
        System.out.println("Full Name: " + FullNameField.getText() );
        System.out.println("Email: " + EmailField.getText() );
        System.out.println("Phone Number: " + PhoneNumberField.getText() );
        System.out.println("Date of Birth: " + DateField.getValue());
        actiontarget.setText("Successfully Registered.");
    }
}
