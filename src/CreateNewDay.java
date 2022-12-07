import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class CreateNewDay {
    public static void main(String[] args) throws IOException {
        createNewDay(8);
    }

    public static void createNewDay(int ... dayOfMonth) throws IOException {
        int day = 0;
        if (dayOfMonth.length==0){
            day = LocalDateTime.now().getDayOfMonth();
        }
        if (dayOfMonth.length>2){
            System.out.println("Ungültige Parameter-Anzahl");
            return;
        }
        if (dayOfMonth.length==1){
            day = dayOfMonth[0];
        }
        File newPackage = new File("src/Year_2022/Day_"+day);
        File newSourceFile = new File("src/Year_2022/Day_"+day+"/source.txt");
        File newJavaClass = new File("src/Year_2022/Day_"+day+"/day"+day+"_part1.java");
        File newJavaClass2 = new File("src/Year_2022/Day_"+day+"/day"+day+"_part2.java");
        String defaultMethod =
                "package Year_2022.Day_XX;\n" +
                        "\n" +
                        "import java.io.IOException;\n" +
                        "import java.nio.file.Files;\n" +
                        "import java.nio.file.Path;\n" +
                        "import java.awt.datatransfer.StringSelection;\n" +
                        "import java.awt.Toolkit;\n" +
                        "import java.awt.datatransfer.Clipboard;\n" +
                        "\n" +
                        "public class dayXX_part1 {\n" +
                        "\n" +
                        "    public static void main(String[] args) throws IOException {\n" +
                        "        String received = methodName(\"src/Year_2022/Day_XX/source.txt\")+\"\";\n" +
                        "        copyToClipboard(received);\n" +
                        "        System.out.println(\"----------------------------------------------------\");\n" +
                        "        System.out.println(\"FINAL RESULT: \"+received);\n" +
                        "    }\n" +
                        "\n" +
                        "    public static long methodName(String path) throws IOException {\n" +
                        "        String[] file = Files.readAllLines(Path.of(path)).toArray(new String[0]);\n" +
                        "        for (int i = 0; i < file.length; i++) {\n" +
                        "            System.out.println(file[i]);\n" +
                        "        }\n" +
                        "        return 0;\n" +
                        "    }\n" +
                        "\n" +
                        "    public static void copyToClipboard(String received) {\n" +
                        "        StringSelection stringSelection = new StringSelection(received);\n" +
                        "        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();\n" +
                        "        clipboard.setContents(stringSelection, null);\n" +
                        "    }\n" +
                        "}";
        String defaultMethod2 =
                "package Year_2022.Day_XX;\n" +
                        "\n" +
                        "import java.io.IOException;\n" +
                        "import java.nio.file.Files;\n" +
                        "import java.nio.file.Path;\n" +
                        "import java.awt.datatransfer.StringSelection;\n" +
                        "import java.awt.Toolkit;\n" +
                        "import java.awt.datatransfer.Clipboard;\n" +
                        "\n" +
                        "public class dayXX_part2 {\n" +
                        "\n" +
                        "    public static void main(String[] args) throws IOException {\n" +
                        "        String received = methodName(\"src/Year_2022/Day_XX/source.txt\")+\"\";\n" +
                        "        copyToClipboard(received);\n" +
                        "        System.out.println(\"----------------------------------------------------\");\n" +
                        "        System.out.println(\"FINAL RESULT: \"+received);\n" +
                        "    }\n" +
                        "\n" +
                        "    public static long methodName(String path) throws IOException {\n" +
                        "        String[] file = Files.readAllLines(Path.of(path)).toArray(new String[0]);\n" +
                        "        for (int i = 0; i < file.length; i++) {\n" +
                        "            System.out.println(file[i]);\n" +
                        "        }\n" +
                        "        return 0;\n" +
                        "    }\n" +
                        "\n" +
                        "    public static void copyToClipboard(String received) {\n" +
                        "        StringSelection stringSelection = new StringSelection(received);\n" +
                        "        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();\n" +
                        "        clipboard.setContents(stringSelection, null);\n" +
                        "    }\n" +
                        "}";
        if (!newPackage.exists()){
            System.out.println("Ordner \""+newPackage.getPath()+"\" wurde erstellt");
            newPackage.mkdirs();
        }
        if (!newSourceFile.exists()){
            System.out.println("Datei \""+newSourceFile.getPath()+"\" wurde erstellt");
            newSourceFile.createNewFile();
        }
        if (!newJavaClass.exists()){
            System.out.println("Datei \""+newJavaClass.getPath()+"\" wurde erstellt");
            newJavaClass.createNewFile();
            try {
                FileWriter myWriter = new FileWriter(newJavaClass);
                myWriter.write(defaultMethod.replace("XX",day+""));
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        if (!newJavaClass2.exists()){
            System.out.println("Datei \""+newJavaClass2.getPath()+"\" wurde erstellt");
            newJavaClass2.createNewFile();
            try {
                FileWriter myWriter = new FileWriter(newJavaClass2);
                myWriter.write(defaultMethod2.replace("XX",day+""));
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }
}
