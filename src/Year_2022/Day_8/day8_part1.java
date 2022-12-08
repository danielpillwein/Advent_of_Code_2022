package Year_2022.Day_8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

public class day8_part1 {

    public static void main(String[] args) throws IOException {
        String received = visibleTrees("src/Year_2022/Day_8/source.txt")+"";
        copyToClipboard(received);
        System.out.println("----------------------------------------------------");
        System.out.println("FINAL RESULT: "+received);
    }

    public static long visibleTrees(String path) throws IOException {
        String[] file = Files.readAllLines(Path.of(path)).toArray(new String[0]);
        int[][] input = readInput(file);
        long counter = 0;

        for (int i = 0; i < file.length; i++) {
            for (int ii = 0; ii < file[i].length(); ii++) {
                if (checkLeft(input,i,ii) || checkRight(input,i,ii) || checkTop(input,i,ii) || checkBottom(input,i,ii)) counter++;
            }
        }
        return counter;
    }

    public static boolean checkLeft(int[][] input, int row, int column){
        for (int i = 0; i < column; i++) {
            if (input[row][i]>=input[row][column]) return false;
        }
        return true;
    }
    public static boolean checkRight(int[][] input, int row, int column){
        for (int i = input[row].length-1; i > column; i--) {
            if (input[row][i]>=input[row][column]) return false;
        }
        return true;
    }

    public static boolean checkTop(int[][] input, int row, int column){
        for (int i = 0; i < row; i++) {
            if (input[i][column]>=input[row][column]) return false;
        }
        return true;
    }

    public static boolean checkBottom(int[][] input, int row, int column){
        for (int i = input.length-1; i > row; i--) {
            if (input[i][column]>=input[row][column]) return false;
        }
        return true;
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