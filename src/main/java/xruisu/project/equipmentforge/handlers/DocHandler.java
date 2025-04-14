package xruisu.project.equipmentforge.handlers;

public class DocHandler {

    private static StringBuilder docHeading = new StringBuilder();
    private static StringBuffer document = new StringBuffer();

    private static StringBuilder setOneBuilder = new StringBuilder();
    private static StringBuilder setTwoBuilder = new StringBuilder();
    private static StringBuilder setThreeBuilder = new StringBuilder();
    private static StringBuilder setFourBuilder = new StringBuilder();
    private static StringBuilder consoleBuilder = new StringBuilder();

    private static StringBuilder setOneList = new StringBuilder();
    private static StringBuilder setTwoList = new StringBuilder();
    private static StringBuilder setThreeList = new StringBuilder();
    private static StringBuilder setFourList = new StringBuilder();

    public static int totalCount = 0;
    public static int setOneCount = 0;
    public static int setTwoCount = 0;
    public static int setThreeCount = 0;
    public static int setFourCount = 0;

    public static void handleDocument() {

    }

    public static void appendToHeading(String str) {
        DocHandler.docHeading.append(str);
    }

    public static StringBuilder getHeading() {
        return docHeading;
    }

    public static void appendToFirstSet(String str) {
        DocHandler.setOneBuilder.append(str);
    }

    public static void appendToSecondSet(String str) {
        DocHandler.setTwoBuilder.append(str);
    }

    public static void appendToThirdSet(String str) {
        DocHandler.setThreeBuilder.append(str);
    }

    public static void appendToFourthSet(String str) {
        DocHandler.setFourBuilder.append(str);
    }

    public static void appendToConsole(String str) {
        DocHandler.consoleBuilder.append(str);
    }

    public static StringBuilder getConsoleDocument() {
        return DocHandler.consoleBuilder;
    }

    public static StringBuilder getSetOneDocument() {
        return setOneBuilder;
    }

    public static StringBuilder getSetTwoDocument() {
        return setTwoBuilder;
    }

    public static StringBuilder getSetThreeDocument() {
        return setThreeBuilder;
    }

    public static StringBuilder getSetFourDocument() {
        return setFourBuilder;
    }

    public static StringBuffer getDocument() {
        return document;
    }

    public static StringBuilder getSetOneList() {
        return setOneList;
    }

    public static StringBuilder getSetTwoList() {
        return setTwoList;
    }

    public static StringBuilder getSetThreeList() {
        return setThreeList;
    }

    public static StringBuilder getSetFourList() {
        return setFourList;
    }
}
