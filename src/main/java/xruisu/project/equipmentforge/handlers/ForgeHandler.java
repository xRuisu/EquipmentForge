package xruisu.project.equipmentforge.handlers;

import javafx.animation.RotateTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.print.PageLayout;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import xruisu.project.equipmentforge.utility.DateTimeManager;
import xruisu.project.equipmentforge.utility.FXMLVariables;

public class ForgeHandler extends FXMLVariables {

    private static Font CINZEL_REGULAR;
    private int setNum = 0;

    @FXML
    public void initialize() {
        System.out.println("[Equipment Forge] Application starting...");
        input.requestFocus();

        console.setStyle("-fx-background-color: lightgray; ");
        console().add(new Text("To get started, select a set to enter into."));
        console().add(new Text("\n" + "For command help, use /help \n"));

        input.setOnAction(actionEvent -> {
            switch (input.getText().trim().toLowerCase()) {
                case "/help":
                    showHelp();
                    break;
                case "/":
                    console().add(new Text("Invalid command, for help use /help.\n"));
                    break;
                case "/print":
                    handlePrint();
                    break;
                case "/clear":
                    console().clear();
                    input.clear();
                    break;
                case "/format":
                    format();
                    break;
                case "/listview":
                    input.clear();
                    console().clear();
                    console().add(
                            new Text("\n" + "Equipment List: " + "\n" + DocHandler.getConsoleDocument().toString()));
                    break;
                default:
                    inputHandler();
                    break;
            }
            input.clear();
        });

        setOneField.setOnAction(actionEvent -> {
            setOneField.setText(setOneField.getText().toUpperCase());
            paneOne.setText(setOneField.getText().toUpperCase());
        });
        setTwoField.setOnAction(actionEvent -> {
            setTwoField.setText(setTwoField.getText().toUpperCase());
            paneTwo.setText(setTwoField.getText().toUpperCase());
        });
        setThreeField.setOnAction(actionEvent -> {
            setThreeField.setText(setThreeField.getText().toUpperCase());
            paneThree.setText(setThreeField.getText().toUpperCase());
        });
        setFourField.setOnAction(actionEvent -> {
            setFourField.setText(setFourField.getText().toUpperCase());
            paneFour.setText(setFourField.getText().toUpperCase());
        });

        importFonts();
        setTitleFont();
        setFonts();
        handleDate();
        handleManagerOrg();
        handleSetOne();
        handleSetTwo();
        handleSetThree();
        handleSetFour();
    }

    private void setTitleFont() {
        title.setText("Equipment Forge");
        title.setFont(CINZEL_REGULAR);
        title.setFont(Font.font(CINZEL_REGULAR.getName(), 24));
    }

    private void importFonts() {
        CINZEL_REGULAR = Font.loadFont(getClass().getResource("/fonts/CinzelDecorative-Regular.ttf").toExternalForm(),
                15);
        if (CINZEL_REGULAR != null) {
            System.out.println(
                    "[Equipment Forge] Loaded font: " +
                            CINZEL_REGULAR.getName());
        } else {
            System.out.println("[Equipment Forge] Failed to load font");
        }
    }

    private void inputHandler() {
        input.setFont(Font.font(CINZEL_REGULAR.getName(), 12));
        String userScanInput = input.getText().toUpperCase();
        input.setPromptText("Input command or start scanning...");
        if (setNum == 0) {
            if (input.getText().isEmpty()) {
                console().add(new Text("Invalid input. Please try again.\nNeed help? Use: /help"));
            } else {
                animateHammer();
                DocHandler.totalCount++;
                totalCount.setText(Integer.toString(DocHandler.totalCount));
                console().add(new Text(" 🛠 " + userScanInput + "\n"));
                DocHandler.appendToConsole(" 🛠 " + userScanInput + "\n");
            }
            input.clear();
        } else if (setNum == 1) {
            animateHammer();
            DocHandler.totalCount++;
            DocHandler.setOneCount++;
            totalCount.setText(Integer.toString(DocHandler.totalCount));
            setOneCount.setText(Integer.toString(DocHandler.setOneCount));
            console().add(new Text(" 🛠 " + userScanInput + " ➛ " + paneOne.getText() + "\n"));
            setOneFlow.getChildren().add(new Text(userScanInput + "\n"));
            DocHandler.appendToConsole(" 🛠 " + userScanInput + "\n");
            DocHandler.getSetOneList().append(" 🛠 " + userScanInput + "\n");
            DocHandler.appendToFirstSet("【 " + paneOne.getText() + "】 🛠" + userScanInput
                    + "__ Employee:____________ Returned:___ Comments:_____________" + "\n");
        } else if (setNum == 2) {
            animateHammer();
            DocHandler.totalCount++;
            DocHandler.setTwoCount++;
            totalCount.setText(Integer.toString(DocHandler.totalCount));
            setTwoCount.setText(Integer.toString(DocHandler.setTwoCount));
            console().add(new Text(" 🛠 " + userScanInput + " ➛ " + paneTwo.getText() + "\n"));
            DocHandler.appendToConsole(" 🛠 " + userScanInput + "\n");
            DocHandler.getSetTwoList().append(" 🛠 " + userScanInput + "\n");
            setTwoFlow.getChildren().add(new Text(userScanInput + "\n"));
            DocHandler.appendToSecondSet("【 " + paneTwo.getText() + "】 🛠" + userScanInput
                    + "__ Employee:____________ Returned:___ Comments:_____________" + "\n");
        } else if (setNum == 3) {
            animateHammer();
            DocHandler.totalCount++;
            DocHandler.setThreeCount++;
            totalCount.setText(Integer.toString(DocHandler.totalCount));
            setThreeCount.setText(Integer.toString(DocHandler.setThreeCount));
            console().add(new Text(" 🛠 " + userScanInput + " ➛ " + paneThree.getText() + "\n"));
            DocHandler.appendToConsole(" 🛠 " + userScanInput + "\n");
            DocHandler.getSetThreeList().append(" 🛠 " + userScanInput + "\n");
            setThreeFlow.getChildren().add(new Text(userScanInput + "\n"));
            DocHandler.appendToThirdSet("【 " + paneThree.getText() + "】 🛠" + userScanInput
                    + "__ Employee:____________ Returned:___ Comments:_____________" + "\n");
        } else if (setNum == 4) {
            animateHammer();
            DocHandler.totalCount++;
            DocHandler.setFourCount++;
            totalCount.setText(Integer.toString(DocHandler.totalCount));
            setFourCount.setText(Integer.toString(DocHandler.setFourCount));
            console().add(new Text(" 🛠 " + userScanInput + " ➛ " + paneFour.getText() + "\n"));
            DocHandler.appendToConsole(" 🛠 " + userScanInput + "\n");
            DocHandler.getSetFourList().append(" 🛠 " + userScanInput + "\n");
            setFourFlow.getChildren().add(new Text(userScanInput + "\n"));
            DocHandler.appendToFourthSet("【 " + paneFour.getText() + "】 🛠" + userScanInput
                    + "__ Employee:____________ Returned:___ Comments:_____________" + "\n");
        }
    }

    private void animateHammer() {
        RotateTransition hammerSwing = new RotateTransition(Duration.millis(150), hammer);
        hammerSwing.setFromAngle(0);
        hammerSwing.setToAngle(50);
        hammerSwing.setAutoReverse(true);
        hammerSwing.setCycleCount(2);
        hammerSwing.play();
    }

    private void format() {
        input.clear();
        console().clear();
        DocHandler.getDocument().setLength(0);
        DocHandler.getConsoleDocument().setLength(0);
        DocHandler.getSetOneDocument().setLength(0);
        DocHandler.getSetTwoDocument().setLength(0);
        DocHandler.getSetThreeDocument().setLength(0);
        DocHandler.getSetFourDocument().setLength(0);
        DocHandler.getSetOneList().setLength(0);
        DocHandler.getSetTwoList().setLength(0);
        DocHandler.getSetThreeList().setLength(0);
        DocHandler.getSetFourList().setLength(0);
        DocHandler.getHeading().setLength(0);

        setOneField.clear();
        setTwoField.clear();
        setThreeField.clear();
        setFourField.clear();

        paneOne.setText("SET 1");
        paneTwo.setText("SET 2");
        paneThree.setText("SET 3");
        paneFour.setText("SET 4");

        managerValue.setText("...");
        orgValue.setText("...");
        setManagerField.clear();
        setOrgField.clear();
        setOneFlow.getChildren().clear();
        setTwoFlow.getChildren().clear();
        setThreeFlow.getChildren().clear();
        setFourFlow.getChildren().clear();

        totalCount.setText("0");
        setOneCount.setText("0");
        setTwoCount.setText("0");
        setThreeCount.setText("0");
        setFourCount.setText("0");

        setNum = 0;

        console().add(new Text("Document data cleared.\n"));
    }

    private void showHelp() {
        input.clear();
        console().clear();
        console().add(new Text("List of commands: \n"));

        console().addAll(
                new Text(" /help - Displays this help menu. \n"),
                new Text(" /print - Prints the scanned barcode(s). \n"),
                new Text(" /clear - Clears the display console. \n"),
                new Text(" /listview - Displays all entered barcode(s). \n"),
                new Text(" /format - Resets all entered data."));
    }

    private void handleDate() {
        System.out.println("[Equipment Forge] Applied [" + DateTimeManager.getCurrentDate() + "] as the current date.");
        date.setFont(Font.font(CINZEL_REGULAR.getName(), 14));
        date.setText(DateTimeManager.getCurrentDate());
    }

    private void handleManagerOrg() {
        setManagerField.setStyle(
                "-fx-background-color: BLACK; -fx-text-fill: WHITE; -fx-border-color: WHITE; -fx-border-width: 0 1 0 1; -fx-border-radius: 100;");
        setOrgField.setStyle(
                "-fx-background-color: BLACK; -fx-text-fill: WHITE; -fx-border-color: WHITE; -fx-border-width: 0 1 0 1; -fx-border-radius: 100;");
        setManagerField.setFont(Font.font(CINZEL_REGULAR.getName(), 12));
        managerValue.setFont(Font.font(CINZEL_REGULAR.getName(), 12));

        setManagerField.setOnAction(actionEvent -> {
            managerValue.setText(setManagerField.getText().toUpperCase() + "    ");
            DocHandler.appendToHeading("MANAGER: " + managerValue.getText());
            input.clear();
        });

        setOrgField.setFont(Font.font(CINZEL_REGULAR.getName(), 12));
        orgValue.setFont(Font.font(CINZEL_REGULAR.getName(), 12));

        setOrgField.setOnAction(actionEvent -> {
            orgValue.setText(setOrgField.getText().toUpperCase());
            DocHandler.appendToHeading(
                    "ORG: " + orgValue.getText() + "   DATE: " + DateTimeManager.getCurrentDate().toString() + "\n");
            input.clear();
        });
    }

    private ObservableList<Node> console() {
        return console.getChildren();
    }

    private void handleSetOne() {
        setOneButton.setOnAction(actionEvent -> {
            input.requestFocus();
            setNum = 1;
            console().clear();
            console().add(
                    new Text("\n" + "▰▰ SET 1 SELECTED ▰▰" + "\n" + DocHandler.getSetOneList()));
            paneOne.setExpanded(true);
            input.clear();
        });
    }

    private void handleSetTwo() {
        setTwoButton.setOnAction(actionEvent -> {
            input.requestFocus();
            setNum = 2;
            console().clear();
            console().add(
                    new Text("\n" + "▰▰ SET 2 SELECTED ▰▰" + "\n" + DocHandler.getSetTwoList()));
            paneTwo.setExpanded(true);
            input.clear();
        });
    }

    private void handleSetThree() {
        setThreeButton.setOnAction(actionEvent -> {
            input.requestFocus();
            setNum = 3;
            console().clear();
            console().add(
                    new Text("\n" + "▰▰ SET 3 SELECTED ▰▰=" + "\n" + DocHandler.getSetThreeList()));
            paneThree.setExpanded(true);
            input.clear();
        });
    }

    private void handleSetFour() {
        setFourButton.setOnAction(actionEvent -> {
            input.requestFocus();
            setNum = 4;
            console().clear();
            console().add(
                    new Text("\n" + "▰▰ SET 4 SELECTED ▰▰" + "\n" + DocHandler.getSetFourList()));
            paneFour.setExpanded(true);
            input.clear();
        });
    }

    private String barline() {
        String barLine = "▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰\n";
        if (DocHandler.getHeading().length() == 0 || DocHandler.getSetOneDocument().length() == 0
                || DocHandler.getSetTwoDocument().length() == 0 || DocHandler.getSetThreeDocument().length() == 0
                || DocHandler.getSetFourDocument().length() == 0) {
            return "";
        }
        return barLine;
    }

    private void handlePrint() {
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if (printerJob == null) {
            callWarningDialog();
            return;
        }

        DocHandler.getDocument()
                .append(DocHandler.getHeading().append(barline())
                        .append(DocHandler.getSetOneDocument().append(barline()))
                        .append(DocHandler.getSetTwoDocument().append(barline()))
                        .append(DocHandler.getSetThreeDocument().append(barline())
                                .append(DocHandler.getSetFourDocument().append(barline()))
                                .append("\nEQUIPMENT SIGN OFF:_______________________")));

        String[] lines = DocHandler.getDocument().toString().split("\n");

        PageLayout pageLayout = printerJob.getJobSettings().getPageLayout();
        double printableWidth = pageLayout.getPrintableWidth();
        double printableHeight = pageLayout.getPrintableHeight();

        if (printerJob.showPrintDialog(scene.getScene().getWindow())) {
            boolean success = true;
            int currentPageStart = 0;

            while (currentPageStart < lines.length && success) {
                TextFlow textFlow = new TextFlow();
                textFlow.setPrefWidth(printableWidth);

                double currentHeight = 0;
                int lineIndex = currentPageStart;

                while (lineIndex < lines.length) {
                    String linesGot = lines[lineIndex].trim() + "\n";
                    Text line = new Text(linesGot);
                    line.setFont(Font.font("Times New Roman", 10));

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
                System.out.println("[Equipment Forge] Printing completed successfully");
            } else {
                System.out.println("[Equipment Forge] Printing failed.");
            }
        }
        format();
    }

    private void callWarningDialog() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("No Available Document Found");
        alert.setHeaderText("Status: Missing Document");
        alert.setContentText(
                " No document was generated or found❗\n Please generate a document before trying to print.");
        alert.showAndWait();
    }

    private void setFonts() {
        setTitles.setFont(Font.font(CINZEL_REGULAR.getName(), 12));
        setTotals.setFont(Font.font(CINZEL_REGULAR.getName(), 12));

        setOneLabel.setFont(Font.font(CINZEL_REGULAR.getName(), 12));
        setTwoLabel.setFont(Font.font(CINZEL_REGULAR.getName(), 12));
        setThreeLabel.setFont(Font.font(CINZEL_REGULAR.getName(), 12));
        setFourLabel.setFont(Font.font(CINZEL_REGULAR.getName(), 12));

        setOneButton.setFont(Font.font(CINZEL_REGULAR.getName(), 10));
        setTwoButton.setFont(Font.font(CINZEL_REGULAR.getName(), 10));
        setThreeButton.setFont(Font.font(CINZEL_REGULAR.getName(), 10));
        setFourButton.setFont(Font.font(CINZEL_REGULAR.getName(), 10));

        setTotals.setFont(Font.font(CINZEL_REGULAR.getName(), 12));

        setOneCount.setFont(Font.font(CINZEL_REGULAR.getName(), 12));
        setTwoCount.setFont(Font.font(CINZEL_REGULAR.getName(), 12));
        setThreeCount.setFont(Font.font(CINZEL_REGULAR.getName(), 12));
        setFourCount.setFont(Font.font(CINZEL_REGULAR.getName(), 12));

        setTotalsLabel.setFont(Font.font(CINZEL_REGULAR.getName(), 12));
        managerLabel.setFont(Font.font(CINZEL_REGULAR.getName(), 12));
        orgLabel.setFont(Font.font(CINZEL_REGULAR.getName(), 12));
    }

}
