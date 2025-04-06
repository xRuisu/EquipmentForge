package xruisu.project.equipmentforge;

import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.print.PageLayout;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class Controller extends FXMLVariables {

    public int count = 0;

    public StringBuilder setOneBuilder = new StringBuilder();
    public StringBuilder setTwoBuilder = new StringBuilder();
    public StringBuilder setThreeBuilder = new StringBuilder();
    public StringBuilder setFourBuilder = new StringBuilder();
    public StringBuilder consoleBuilder = new StringBuilder();

    StringBuffer firstHeading = new StringBuffer();

    private int setNum = 0;
    private Map<Integer, String> cmd = new HashMap<>();

    public String manager;
    public String org;
    public String sortDate;

    @FXML
    public void initialize() {
        onEnable();
        handleFieldSetters();

        input.setOnAction(actionEvent -> {
            if (input.getText().isEmpty()) {
                console.getChildren().add(new Text("Invalid input, please try again. \n"));
            }
            handleCommands();
            handleSetCmds();
            input.clear();
        });
    }

    private void onEnable() {
        System.out.println("Application booting...");
        setCommands();
        console.getChildren().clear();
        console.getChildren().add(new Text(" Start scanning to get started. \n For additional help, use /help. \n"));
        input.requestFocus();
        input.setPromptText("Input command or start scanning...");

    }

    private void handleSetCmds() {
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
                console.getChildren()
                        .add(new Text("ENTERED " + input.getText() + " TO " + paneOne.getText() + "\n"));
                setOneFlow.getChildren().add(new Text(input.getText() + "\n"));
                setOneBuilder.append(paneOne.getText() + " " + input.getText()
                        + "__  EMPLOYEE:____________RETURNED:___COMMENTS_____________" + "\n");
            }
        } else if (setNum == 2) {
            if (!input.getText().isEmpty()) {
                count++;
                totalCount.setText(Integer.toString(count));
                console.getChildren()
                        .add(new Text("ENTERED " + input.getText() + " TO " + paneTwo.getText() + "\n"));
                setTwoFlow.getChildren().add(new Text(input.getText() + "\n"));
                setTwoBuilder.append(paneTwo.getText() + " " + input.getText()
                        + "__  EMPLOYEE:____________RETURNED:___COMMENTS_____________" + "\n");
            }
        } else if (setNum == 3) {
            if (!input.getText().isEmpty()) {
                count++;
                totalCount.setText(Integer.toString(count));
                console.getChildren()
                        .add(new Text("ENTERED " + input.getText() + " TO " + paneThree.getText() + "\n"));
                setThreeFlow.getChildren().add(new Text(input.getText() + "\n"));
                setThreeBuilder.append(paneThree.getText() + " " + input.getText()
                        + "__  EMPLOYEE:____________RETURNED:___COMMENTS_____________" + "\n");
            }
        } else if (setNum == 4) {
            if (!input.getText().isEmpty()) {
                count++;
                totalCount.setText(Integer.toString(count));
                console.getChildren()
                        .add(new Text("ENTERED " + input.getText() + " TO " + paneFour.getText() + "\n"));
                setFourFlow.getChildren().add(new Text(input.getText() + "\n"));
                setFourBuilder.append(paneFour.getText() + " " + input.getText()
                        + "__  EMPLOYEE:____________RETURNED:___COMMENTS_____________" + "\n");
            }
        }
    }

    private void handleCommands() {
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
                setDocumentHeading();
                printReport();
                // System.out.println();
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
            } else if (input.getText().equals("/" + cmd.get(6))) {
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
                input.clear();
                setFourBuilder.setLength(0);
                setFourFlow.getChildren().clear();
            }
        }
    }

    private void handleFieldSetters() {
        setOneField.setOnKeyReleased(actionEvent -> {
            paneOne.setText(setOneField.getText().toUpperCase());
            setOneField.getText().toUpperCase();
        });
        setTwoField.setOnKeyReleased(actionEvent -> {
            paneTwo.setText(setTwoField.getText().toUpperCase());
            setTwoField.getText().toUpperCase();
        });
        setThreeField.setOnKeyReleased(actionEvent -> {
            paneThree.setText(setThreeField.getText().toUpperCase());
            setThreeField.getText().toUpperCase();
        });
        setFourField.setOnKeyReleased(actionEvent -> {
            paneFour.setText(setFourField.getText().toUpperCase());
            setFourField.getText().toUpperCase();
        });
        setManagerField.setOnKeyReleased(actionEvent -> {
            manager = setManagerField.getText().toUpperCase();
            setManagerField.getText().toUpperCase();
        });
        setOrgField.setOnKeyReleased(actionEvent -> {
            org = setOrgField.getText().toUpperCase();
            setOrgField.getText().toUpperCase();
        });
    }

    private void setDocumentHeading() {
        // Pane document = new Pane();

        firstHeading.setLength(0);

        Text manager = new Text("           MANAGER: " + this.manager + "   ");
        Text org = new Text("   ORG: " + this.org + "   ");
        Text date = new Text("  DATE: " + DateTimeManager.getCurrentTimeAndDate());
        firstHeading.append(manager.getText().toUpperCase()).append(org.getText().toUpperCase())
                .append(date.getText() + "\n");
    }

    private void printReport() {

        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if (printerJob == null) {
            callWarningDialog();
            return;
        }
        String barLine = "\n▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰";
        final StringBuffer BUFFER = new StringBuffer();
        BUFFER
                .append(firstHeading).append(barLine).append(setOneBuilder.append(barLine)
                        .append(setTwoBuilder.append(barLine)
                                .append(setThreeBuilder.append(barLine).append(setFourBuilder.append(barLine)))));

        Text documentText = new Text(BUFFER.toString());
        documentText.setStyle("font-family: consolas; font-size: 25; background-color: whitesmoke; color: black;");
        String[] lines = BUFFER.toString().split("\n");

        PageLayout pageLayout = printerJob.getJobSettings().getPageLayout();
        double printableWidth = pageLayout.getPrintableWidth();
        double printableHeight = pageLayout.getPrintableHeight();

        TextFlow textFlow = new TextFlow();

        textFlow.setPrefWidth(printableWidth);

        if (printerJob.showPrintDialog(scene.getScene().getWindow())) {
            boolean success = true;
            int currentPageStart = 0;

            while (currentPageStart < lines.length && success) {
                double currentHeight = 0;
                int lineIndex = currentPageStart;

                while (lineIndex < lines.length) {

                    String linesGot = lines[lineIndex].trim() + "\n";
                    Text line = new Text(linesGot);

                    textFlow.getChildren().add(line);
                    textFlow.applyCss();
                    textFlow.layout();

                    currentHeight = textFlow.getBoundsInParent().getHeight();

                    if (currentHeight > printableHeight) {
                        textFlow.getChildren().remove(line);
                        break;
                    }
                    lineIndex++;
                }

                success = printerJob.printPage(textFlow);
                currentPageStart = lineIndex;
            }
            System.out.println("Printing...");
            if (success) {
                printerJob.endJob();
                System.out.println("Printing completed successfully");
            } else {
                System.out.println("Printing failed.");
            }
        }
    }

    private void callWarningDialog() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("No Available Document Found");
        alert.setHeaderText("Status: Missing Document");
        alert.setContentText(
                " No document was generated or opened❗\n Please generate a document before trying to print.");
        alert.showAndWait();
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