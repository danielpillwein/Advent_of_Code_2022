package Year_2022.Day_4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.util.*;

public class day4_part1 {

    public static void main(String[] args) throws IOException {
        String received = containingSections("src/Year_2022/Day_4/source.txt")+"";
        copyToClipboard(received);
        System.out.println("----------------------------------------------------");
        System.out.println("FINAL RESULT: "+received);
    }

    public static long containingSections(String path) throws IOException {
        String[] file = Files.readAllLines(Path.of(path)).toArray(new String[0]);
        long counter = 0;
        for (int i = 0; i < file.length; i++) {
            List<Integer> elf1 = Arrays.stream(file[i].split(",")[0].split("-")).map((Integer::parseInt)).toList();
            List<Integer> elf2 = Arrays.stream(file[i].split(",")[1].split("-")).map((Integer::parseInt)).toList();
            List<Integer> allSections = Arrays.stream(file[i].replaceAll(",","-").split("-")).map(Integer::parseInt).toList();
            if (elf1.contains(Collections.min(allSections)) && elf1.contains(Collections.max(allSections))){
                counter ++;
            } else if (elf2.contains(Collections.min(allSections)) && elf2.contains(Collections.max(allSections))){
                counter ++;
            }
        }
        return counter;
    }

    public static void copyToClipboard(String received) {
        StringSelection stringSelection = new StringSelection(received);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
}