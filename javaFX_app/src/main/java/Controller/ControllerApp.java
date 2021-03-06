package Controller;

import Example.Calculations;
import Example.LexicOrderArray;
import Example.NumberExpanded;
import IOFile.ReaderFromFile;
import IOFile.WriterInFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
/*
    Controller class FXML
 */
public class ControllerApp implements Initializable {

    @FXML
    private Button buttonSaveResult;

    @FXML
    private Button buttonLoadResult;

    @FXML
    private Button buttonClearScreen;

    @FXML
    private Button buttonCountExample;

    @FXML
    private TextArea fieldEnter;

    @FXML
    private TextArea fieldResult;

    @FXML
    private ComboBox<String> changeExample;

    Stage primaryStage;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public ComboBox<String> getChangeExample() {
        return (ComboBox<String>) changeExample;
    }

    public void setChangeExample(ComboBox<String> changeExample) {
        this.changeExample = changeExample;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {   }

    //Action button save
    public void saveResult(ActionEvent actionEvent) {
        WriterInFile.writeInFile(fieldEnter.getText(), primaryStage, changeExample);

    }

    //Action button load
    public void loadResult(ActionEvent actionEvent) {
        String rawData = ReaderFromFile.readFromFile(primaryStage);
        String data = "";
        if (rawData.contains("#NumberExpanded#\n")) {
            data = rawData.replace("#NumberExpanded#\n", "");
            changeExample.setValue("NumberExpanded");
            fieldEnter.setText(data);
            countExample(null);
        }
        if (rawData.contains("#LexicOrderArray#\n")) {
            data = rawData.replace("#LexicOrderArray#\n", "");
            changeExample.setValue("LexicOrderArray");
            fieldEnter.setText(data);
            countExample(null);
        }
    }

    //Clear textarea enter and result
    public void clearScreen(ActionEvent actionEvent) {
        fieldResult.clear();
        fieldEnter.clear();

    }

    public void chooseExample(ActionEvent actionEvent) {}

    //Action button calculating
    public void countExample(ActionEvent actionEvent) {
        if (!(changeExample.getValue() == null) && !(fieldEnter.getText().equals(""))) {
            if (changeExample.getValue().equals("NumberExpanded")) {
                Calculations calculations = new NumberExpanded(fieldEnter.getText());
                fieldResult.setText(calculations.run().toString());
            }
            if (changeExample.getValue().equals("LexicOrderArray")) {
                Calculations calculations = new LexicOrderArray(fieldEnter.getText());
                fieldResult.setText(calculations.run().toString());
            }
        } else {
            fieldResult.setText("Не выбрана задача или не введены данные");
        }

    }


}
