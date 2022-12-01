package Year_2022.Day_1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

public class day1_part1 {

    public static void main(String[] args) throws IOException {
        String received = maxCalories("src/Year_2021/Day_1/source.txt")+"";
        copyToClipboard(received);
        System.out.println("----------------------------------------------------");
        System.out.println("FINAL RESULT: "+received);
    }

    public static long maxCalories(String path) throws IOException {
        String[] file = Files.readAllLines(Path.of(path)).toArray(new String[0]);
        long max = 0;
        long counter = 0;
        for (int i = 0; i < file.length; i++) {
            if (file[i].length()>0){
                counter += Integer.parseInt(file[i]);
            }else{
                max = counter>max?counter:max;
                counter = 0;
            }
        }
        return max;
    }

    public static void copyToClipboard(String received) {
        StringSelection stringSelection = new StringSelection(received);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
}