package xruisu.project.equipmentforge;

import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class Controller extends FXMLVariables {

    public int count = 0;

    public StringBuilder setOneBuilder = new StringBuilder();
    public StringBuilder setTwoBuilder = new StringBuilder();
    public StringBuilder setThreeBuilder = new StringBuilder();
    public StringBuilder setFourBuilder = new StringBuilder();
    public StringBuilder consoleBuilder = new StringBuilder();

    private int setNum = 0;
    private Map<Integer, String> cmd = new HashMap<>();

    public String manager;
    public String org;
    public String sortDate;

    @FXML
    public void initialize() {
        System.out.println("Application booting...");
        setCommands();
        console.getChildren().clear();
        console.getChildren().add(new Text(" Start scanning to get started. \n For additional help, use /help. \n"));
        input.requestFocus();
        input.setPromptText("Input command or start scanning...");

        setOneField.setOnAction(actionEvent -> {
            paneOne.setText(setOneField.getText().toUpperCase());
        });
        setTwoField.setOnAction(actionEvent -> {
            paneTwo.setText(setTwoField.getText().toUpperCase());
        });
        setThreeField.setOnAction(actionEvent -> {
            paneThree.setText(setThreeField.getText().toUpperCase());
        });
        setFourField.setOnAction(actionEvent -> {
            paneFour.setText(setFourField.getText().toUpperCase());
        });
        setManagerField.setOnAction(actionEvent -> {
            manager = setManagerField.getText().toUpperCase();
        });
        setOrgField.setOnAction(actionEvent -> {
            org = setOrgField.getText().toUpperCase();
        });
        setSortDateField.setOnAction(actionEvent -> {
            sortDate = setSortDateField.getText();
        });

        input.setOnAction(actionEvent -> {

            if (input.getText().isEmpty()) {
                console.getChildren().add(new Text("Invalid input, please try again. \n"));
            }

            if (input.getText().contains("/")) {
                if (input.getText().equals("/" + cmd.get(0))) {

                    input.clear();
                    console.getChildren().clear();
                    console.getChildren().add(new Text("List of commands: \n"));

                    console.getChildren().addAll(
                            new Text(" /help - Displays this help menu. \n"),
                            new Text(" /print - Prints the scanned barcode(s). \n"),
                            new Text(" /clear - Clears the display console. \n"),
                            new Text(" /reset - Removes all scanned barcode(s). \n"),
                            new Text(" /listview - Displays all entered barcode. \n"),
                            new Text(" /set 1 - Sets path to the first group. \n"),
                            new Text(" /set 2 - Sets path to the second group. \n"),
                            new Text(" /set 3 - Sets path to the third group. \n"),
                            new Text(" /set 4 - Sets path to the first group. \n"),
                            new Text(" /format - Formats the current document."));

                } else if (input.getText().equals("/" + cmd.get(1))) {
                    input.clear();
                    console.getChildren().clear();
                    System.out.println();
                } else if (input.getText().equals("/" + cmd.get(2))) {
                    input.clear();
                    console.getChildren().clear();
                    totalCount.setText("0");
                } else if (input.getText().equals("/" + cmd.get(3))) {
                    input.clear();
                    console.getChildren().clear();
                } else if (input.getText().equals("/" + cmd.get(4))) {
                    input.clear();
                    console.getChildren().clear();
                    consoleBuilder.setLength(0);
                    setOneBuilder.setLength(0);
                    setTwoBuilder.setLength(0);
                    setThreeBuilder.setLength(0);
                    setFourBuilder.setLength(0);
                    console.getChildren().add(new Text("Document data has been cleared.\n"));

                } else if (input.getText().equals("/" + cmd.get(5))) {
                    input.clear();
                    console.getChildren().clear();
                    console.getChildren().add(new Text("\n" + "Total Scanned: " + "\n" + consoleBuilder.toString()));
                }

                else if (input.getText().equals("/" + cmd.get(6))) {
                    setNum = 1;
                    console.getChildren().add(new Text("\n" + "Set 1 selected. " + "\n" + consoleBuilder.toString()));
                    input.clear();
                    setOneBuilder.setLength(0);
                    setOneFlow.getChildren().clear();
                } else if (input.getText().equals("/" + cmd.get(7))) {
                    setNum = 2;
                    console.getChildren().add(new Text("\n" + "Set 2 selected. " + "\n" + consoleBuilder.toString()));
                    input.clear();
                    setTwoBuilder.setLength(0);
                    setTwoFlow.getChildren().clear();
                } else if (input.getText().equals("/" + cmd.get(8))) {
                    setNum = 3;
                    console.getChildren().add(new Text("\n" + "Set 3 selected. " + "\n" + consoleBuilder.toString()));
                    input.clear();
                    setThreeBuilder.setLength(0);
                    setThreeFlow.getChildren().clear();
                } else if (input.getText().equals("/" + cmd.get(9))) {
                    setNum = 4;
                    console.getChildren().add(new Text("\n" + "Set 4 selected. " + "\n" + consoleBuilder.toString()));
                }
            }
            if (setNum == 0) {
                if (!input.getText().isEmpty()) {
                    count++;
                    totalCount.setText(Integer.toString(count));
                    console.getChildren().add(new Text("Scan added " + input.getText() + " \n"));
                    consoleBuilder.append(input.getText() + "\n");
                }
            } else if (setNum == 1) {
                if (!input.getText().isEmpty()) {
                    count++;
                    totalCount.setText(Integer.toString(count));
                    console.getChildren().add(new Text("Scan added to [SET 1] " + input.getText() + " \n"));
                    setOneFlow.getChildren().add(new Text(input.getText() + "\n"));
                    setOneBuilder.append(input.getText() + "\n");
                }
            } else if (setNum == 2) {
                if (!input.getText().isEmpty()) {
                    count++;
                    totalCount.setText(Integer.toString(count));
                    console.getChildren().add(new Text("Scan added to [SET 2] " + input.getText() + " \n"));
                    setTwoFlow.getChildren().add(new Text(input.getText() + "\n"));
                    setTwoBuilder.append(input.getText() + "\n");
                }
            } else if (setNum == 3) {
                if (!input.getText().isEmpty()) {
                    count++;
                    totalCount.setText(Integer.toString(count));
                    console.getChildren().add(new Text("Scan added to [SET 3] " + input.getText() + " \n"));
                    setThreeFlow.getChildren().add(new Text(input.getText() + "\n"));
                    setThreeBuilder.append(input.getText() + "\n");
                }
            } else if (setNum == 4) {
                if (!input.getText().isEmpty()) {
                    count++;
                    totalCount.setText(Integer.toString(count));
                    console.getChildren().add(new Text("Scan added to [SET 4] " + input.getText() + " \n"));
                    setFourFlow.getChildren().add(new Text(input.getText() + "\n"));
                    setFourBuilder.append(input.getText() + "\n");
                }
            }
            input.clear();
        });
    }

    private void setCommands() {
        cmd.put(0, "help");
        cmd.put(1, "print");
        cmd.put(2, "clear");
        cmd.put(3, "reset");
        cmd.put(4, "format");
        cmd.put(5, "listview");
        cmd.put(6, "set 1");
        cmd.put(7, "set 2");
        cmd.put(8, "set 3");
        cmd.put(9, "set 4");
    }
}