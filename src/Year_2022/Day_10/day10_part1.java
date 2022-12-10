package Year_2022.Day_10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.util.LinkedList;
import java.util.List;

public class day10_part1 {

    public static void main(String[] args) throws IOException {
        String received = registerValue("src/Year_2022/Day_10/source.txt")+"";
        copyToClipboard(received);
        System.out.println("----------------------------------------------------");
        System.out.println("FINAL RESULT: "+received);
    }

    public static long registerValue(String path) throws IOException {
        String[] file = Files.readAllLines(Path.of(path)).toArray(new String[0]);
        long cycle = 0;
        long x = 1;
        long nextLogCycle = 20;
        List<Long> signalValues = new LinkedList<>();
        for (int i = 0; i < file.length; i++) {
            if (!file[i].equals("noop")){
                cycle+=2;
                if (cycle>= nextLogCycle){
                    signalValues.add(x*nextLogCycle);
                    nextLogCycle+=40;
                }
                x += Long.parseLong(file[i].split(" ")[1]);

            }else cycle++;
        }
        return signalValues.stream().mapToLong(y->y).sum();
    }

    public static void copyToClipboard(String received) {
        StringSelection stringSelection = new StringSelection(received);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
}