package xruisu.project.equipmentforge;

public class Formatter extends Controller {

    private static StringBuilder builder = new StringBuilder();

    // public String getSetOne() {
    // return "[" + paneOne.getText() + "]" + setOneBuilder.toString();
    // }

    public String getSetTwo() {
        return setTwoBuilder.toString();
    }

    public String getSetThree() {
        return setThreeBuilder.toString();
    }

    public String getSetFour() {
        return setFourBuilder.toString();
    }

    public String getConsole() {
        return consoleBuilder.toString();
    }

    public String getHeader() {
        return "";
    }

    public String getFooter() {
        return "";
    }

    public static StringBuilder getDocument() {
        return Formatter.builder;
    }
}
