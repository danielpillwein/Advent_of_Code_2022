package Year_2022.Day_10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.util.Arrays;

public class day10_part2 {

    public static void main(String[] args) throws IOException {
        String received = fixDisplay("src/Year_2022/Day_10/source.txt")+"";
        copyToClipboard(received);
        System.out.println("----------------------------------------------------");
        System.out.println("FINAL RESULT: "+received);
    }

    public static long fixDisplay(String path) throws IOException {
        String[] file = Files.readAllLines(Path.of(path)).toArray(new String[0]);
        long cycle = 0;
        long x = 1;
        long[][] screen = init();
        for (int i = 0; i < file.length; i++) {
            if (!file[i].equals("noop")){
                screen[(int) (cycle/40)][(int) (cycle%40)] = cycle%40>=x-1 && cycle%40<=x+1?1:0;
                cycle++;
                screen[(int) (cycle/40)][(int) (cycle%40)] = cycle%40>=x-1 && cycle%40<=x+1?1:0;
                cycle++;
                x += Long.parseLong(file[i].split(" ")[1]);
            }else {
                screen[(int) (cycle/40)][(int) (cycle%40)] = cycle%40>=x-1 && cycle%40<=x+1?1:0;
                cycle++;
            }
        }
        for (long[] row : screen) {
            for (int i = 0; i < row.length; i++) {
                if (row[i]==1) System.out.print("# ");
                else System.out.print(". ");
            }
            System.out.println();
        }
        return 0;
    }

    public static long[][] init(){
        long[][] erg = new long[6][40];
        for (long[] row : erg) {
            Arrays.fill(row,0);
        }
        return erg;
    }

    public static void copyToClipboard(String received) {
        StringSelection stringSelection = new StringSelection(received);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
}