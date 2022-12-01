package Year_2022.Day_1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.util.ArrayList;

public class day1_part2 {

    public static void main(String[] args) throws IOException {
        String received = maxCalories3Elves("src/Year_2021/Day_1/source.txt")+"";
        copyToClipboard(received);
        System.out.println("----------------------------------------------------");
        System.out.println("FINAL RESULT: "+received);
    }

    public static long maxCalories3Elves(String path) throws IOException {
        String[] file = Files.readAllLines(Path.of(path)).toArray(new String[0]);
        ArrayList<Long> elvesList = new ArrayList<Long>();
        long counter = 0;
        for (int i = 0; i < file.length; i++) {
            if (file[i].length()>0){
                counter += Integer.parseInt(file[i]);
            }else{
                elvesList.add(counter);
                counter = 0;
            }
        }
        elvesList.stream().sorted().forEach(System.out::println);
        return 0;
    }

    public static void copyToClipboard(String received) {
        StringSelection stringSelection = new StringSelection(received);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
}