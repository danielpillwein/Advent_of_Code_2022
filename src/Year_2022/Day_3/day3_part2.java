package Year_2022.Day_3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

public class day3_part2 {

    public static void main(String[] args) throws IOException {
        String received = RucksackScore("src/Year_2022/Day_3/source.txt")+"";
        copyToClipboard(received);
        System.out.println("----------------------------------------------------");
        System.out.println("FINAL RESULT: "+received);
    }

    public static long RucksackScore(String path) throws IOException {
        String[] file = Files.readAllLines(Path.of(path)).toArray(new String[0]);
        long score = 0;
        for (int i = 0; i < file.length; i+=3) {
            String rucksack1 = file[i];
            String rucksack2 = file[i+1];
            String rucksack3 = file[i+2];
            for (int ii = 0; ii < rucksack1.length(); ii++) {
                if (rucksack2.contains(rucksack1.charAt(ii)+"") && rucksack3.contains(rucksack1.charAt(ii)+"")){
                    score += getPriority(rucksack1.charAt(ii));
                    break;
                }
            }

        }
        return score;
    }

    public static long getPriority(char input){
        if (Character.isLowerCase(input)) return (input+0)-96;
        else return (input+0)-38;
    }

    public static void copyToClipboard(String received) {
        StringSelection stringSelection = new StringSelection(received);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
}