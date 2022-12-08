package Year_2022.Day_8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

public class day8_part2 {

    public static void main(String[] args) throws IOException {
        String received = highestScenicScore("src/Year_2022/Day_8/source.txt")+"";
        copyToClipboard(received);
        System.out.println("----------------------------------------------------");
        System.out.println("FINAL RESULT: "+received);
    }

    public static long highestScenicScore(String path) throws IOException {
        String[] file = Files.readAllLines(Path.of(path)).toArray(new String[0]);
        int[][] input = readInput(file);
        long counter = 0;
        
        for (int i = 0; i < file.length; i++) {
            for (int ii = 0; ii < file[i].length(); ii++) {
                long score = viewLeft(input,i,ii)*viewRight(input,i,ii)*viewTop(input,i,ii)*viewBottom(input,i,ii);
                counter = Math.max(score,counter);
            }
        }
        return counter;
    }

    public static long viewLeft(int[][] input, int row, int column){
        long viewDistance = 0;
        if (column-1<0) return 0;
        for (int i = column-1; i >= 0; i--) {
            if (input[row][i]<input[row][column]) viewDistance++;
            else if (input[row][i]>=input[row][column]) return ++viewDistance;
        }
        return viewDistance;
    }
    public static long viewRight(int[][] input, int row, int column){
        long viewDistance = 0;
        if (column+1>input[row].length) return 0;
        for (int i = column+1; i < input[row].length; i++) {
            if (input[row][i]<input[row][column]) viewDistance++;
            else if (input[row][i]>=input[row][column]) return ++viewDistance;
        }
        return viewDistance;
    }
    public static long viewTop(int[][] input, int row, int column){
        long viewDistance = 0;
        if (row-1<0) return 0;
        for (int i = row-1; i >= 0; i--) {
            if (input[i][column]<input[row][column]) viewDistance++;
            else if (input[i][column]>=input[row][column]) return ++viewDistance;
        }
        return viewDistance;
    }

    public static long viewBottom(int[][] input, int row, int column){
        long viewDistance = 0;
        if (row+1>input.length) return 0;
        for (int i = row+1; i < input.length; i++) {
            if (input[i][column]<input[row][column]) viewDistance++;
            else if (input[i][column]>=input[row][column]) return ++viewDistance;
        }
        return viewDistance;
    }
    public static int[][] readInput(String[] file){
        int[][] input = new int[file.length][file[0].length()];
        for (int i = 0; i < file.length; i++) {
            for (int ii = 0; ii < file[i].length(); ii++) {
                input[i][ii] = Integer.parseInt(file[i].charAt(ii)+"");
            }
        }
        return input;
    }


    public static void copyToClipboard(String received) {
        StringSelection stringSelection = new StringSelection(received);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
}