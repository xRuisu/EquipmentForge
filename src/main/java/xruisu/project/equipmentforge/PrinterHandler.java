package xruisu.project.equipmentforge;

import javafx.print.PageLayout;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class PrinterHandler extends Controller {

    private void callWarningDialog() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("No Available Document Found");
        alert.setHeaderText("Status: Missing Document");
        alert.setContentText(
                " No document was generated or opened❗\n Please generate a document before trying to print.");
        alert.showAndWait();
    }

    private void printReport() {

        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if (printerJob == null) {
            callWarningDialog();
            return;
        }

        //StringBuil previousReport = DataManager.getPrevious().toString();

        final StringBuffer BUFFER = new StringBuffer();
        // BUFFER.append(ReportFormatting.getHeading()).append(header).append(previousReport).append("\n")
        // .append(ReportFormatting.getFooting()).append(ReportFormatting.getDisclaimer())
        // .append(ReportFormatting.getInfo());

        Text reportText = new Text(BUFFER.toString());
        String[] lines = Formatter.getDocument().toString().split("\n");

        PageLayout pageLayout = printerJob.getJobSettings().getPageLayout();
        double printableWidth = pageLayout.getPrintableWidth();
        double printableHeight = pageLayout.getPrintableHeight();

        TextFlow textFlow = new TextFlow();

        textFlow.setPrefWidth(printableWidth);

        if (printerJob.showPrintDialog(document.getScene().getWindow())) {
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
}
