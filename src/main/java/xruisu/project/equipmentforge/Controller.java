package xruisu.project.equipmentforge;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class Controller {

    private StringBuilder consoleBuilder = new StringBuilder();
    private StringBuilder setOneBuilder = new StringBuilder();

    private String[] commands = { "help", "list", "clear", "reset", "format", "listview", "set 1" };

    @FXML
    VBox printerList;
    @FXML
    TextField input;
    @FXML
    TextFlow console;
    @FXML
    TextFlow printerTextFlow;
    @FXML
    Label totalCount;

    @FXML
    TitledPane paneOne;
    @FXML
    TitledPane paneTwo;
    @FXML
    TitledPane paneThree;
    @FXML
    TitledPane paneFour;

    @FXML
    AnchorPane setOne;
    @FXML
    AnchorPane setTwo;
    @FXML
    AnchorPane setThree;
    @FXML
    AnchorPane setFour;

    private int count = 0;

    @FXML
    public void initialize() {
        console.getChildren().clear();
        console.getChildren().add(new Text(" Start scanning to get started. \n For additional help, use /help. \n"));
        input.requestFocus();
        input.setPromptText("Input command or start scanning...");
        System.out.println("Application booting...");

        input.setOnAction(actionEvent -> {
            String scannerInput = input.getText();

            if (scannerInput.isEmpty()) {
                console.getChildren().add(new Text("Invalid input, please try again."));
            }
            if (input.getText().contains("/")) {
                if (input.getText().equals("/" + commands[0])) {
                    console.getChildren().clear();
                    console.getChildren().add(new Text("List of commands: \n"));
                    console.getChildren().addAll(
                            new Text(" /help - Displays this help menu." + "\n"),
                            new Text(" /print - Prints the scanned barcode(s)." + "\n"),
                            new Text(" /clear - Clears the display console." + "\n"),
                            new Text(" /reset - Removes all scanned barcode(s)." + "\n"),
                            new Text("/set 1 - Sets scanning to the first group."),
                            new Text(" /format - Formats the current document."));
                } else if (input.getText().equals("/" + commands[1])) {
                    // TO DO PRINTER METHOD
                } else if (input.getText().equals("/" + commands[2])) {
                    console.getChildren().clear();
                    count = 0;
                    totalCount.setText("0");
                } else if (input.getText().equals("/" + commands[3])) {
                    console.getChildren().clear();
                } else if (input.getText().equals("/" + commands[4])) {
                    consoleBuilder.setLength(0);
                    console.getChildren().add(new Text("Document data has been cleared."));
                } else if (input.getText().equals("/" + commands[5])) {
                    console.getChildren().add(new Text("\n" + "Total Scanned: " + "\n" + consoleBuilder.toString()));
                }

                // else if (input.getText().equals("/" + commands[6])) {
                // console.getChildren().add(new Text("\n The first set has been selected."));
                // input.clear();
                // if (input.getText().isEmpty()) {
                // console.getChildren()
                // .add(new Text("Please name your set: \n"));
                // } else
                // paneOne.setText(input.getText());
                // console.getChildren()
                // .add(new Text("Group set successfully.\n when complete with this set type
                // /return.\n"));
                // if (input.getText().equals("/" + commands[6])) {
                // console.getChildren().clear();
                // console.getChildren().add(new Text("Scan added " + scannerInput + " \n"));
                // setOne.getChildren().add(new Text("Scan added " + scannerInput + " \n"));
                // setOneBuilder.append(scannerInput + "\n");
                // }
                // }

            } else {
                count++;
                totalCount.setText(Integer.toString(count));
                console.getChildren().add(new Text("Scan added " + scannerInput + " \n"));
                consoleBuilder.append(scannerInput + "\n");
            }
            input.clear();
        });
    }
}