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
        System.out.println(FullNameField.getText() );
        System.out.println(EmailField.getText() );
        System.out.println(PhoneNumberField.getText() );
        System.out.println(DateField.getValue());
        actiontarget.setText("Successfully Registered.");
    }
}
