package Year_2022.Day_4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class day4_part2 {

    public static void main(String[] args) throws IOException {
        String received = overlapingSections("src/Year_2022/Day_4/source.txt")+"";
        copyToClipboard(received);
        System.out.println("----------------------------------------------------");
        System.out.println("FINAL RESULT: "+received);
    }

    public static long overlapingSections(String path) throws IOException {
        String[] file = Files.readAllLines(Path.of(path)).toArray(new String[0]);
        long counter = 0;
        for (int i = 0; i < file.length; i++) {
            List<Integer> elf1 = Arrays.stream(file[i].split(",")[0].split("-")).map((Integer::parseInt)).toList();
            List<Integer> elf2 = Arrays.stream(file[i].split(",")[1].split("-")).map((Integer::parseInt)).toList();
            if (Collections.max(elf1)>=Collections.min(elf2) && Collections.min(elf1)<=Collections.min(elf2)){
                counter++;
            }else if (Collections.max(elf2)>=Collections.min(elf1) && Collections.min(elf2)<=Collections.min(elf1)){
                counter++;
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