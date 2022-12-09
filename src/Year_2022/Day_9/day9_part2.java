package Year_2022.Day_9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class day9_part2 {

    public static void main(String[] args) throws IOException {
        String received = methodName("src/Year_2022/Day_9/source.txt")+"";
        copyToClipboard(received);
        System.out.println("----------------------------------------------------");
        System.out.println("FINAL RESULT: "+received);
    }

    public static long methodName(String path) throws IOException {
        String[] file = Files.readAllLines(Path.of(path)).toArray(new String[0]);
        int[][] snake = initSnake();
        List<String> positionsVisited = new LinkedList<>();

        for (String s : file) {
            char direction = s.charAt(0);
            long steps = Long.parseLong(s.split(" ")[1]);
            for (long step = 0; step < steps; step++) {
                switch (direction) {
                    case 'U' -> snake[0][1]++;
                    case 'R' -> snake[0][0]++;
                    case 'D' -> snake[0][1]--;
                    case 'L' -> snake[0][0]--;
                }
                for (int ii = 0; ii < snake.length - 1; ii++) {
                    updateTail(snake[ii], snake[ii + 1]);
                }
                if (!positionsVisited.contains(snake[9][0] + ":" + snake[9][1]))
                    positionsVisited.add(snake[9][0] + ":" + snake[9][1]);
            }
        }
        return positionsVisited.size();
    }

    public static int[][] initSnake(){
        int[][] erg = new int[10][2];
        for (int i = 0; i < erg.length; i++) {
            Arrays.fill(erg[i], 0);
        }
        return erg;
    }

    public static int[] updateTail(int[] head, int[] tail) {
        long xDiff = Math.abs(head[0] - tail[0]);
        long yDiff = Math.abs(head[1] - tail[1]);
        if (Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2)) > Math.sqrt(2)) {
            if (xDiff == 0) {
                tail[1] += head[1] > tail[1] ? 1 : -1;
            }
            else if (yDiff == 0) {
                tail[0] += head[0] > tail[0] ? 1 : -1;
            }
            else if (head[0]<tail[0] && head[1]<tail[1]){tail[0]--;tail[1]--;}
            else if (head[0]>tail[0] && head[1]<tail[1]){tail[0]++;tail[1]--;}
            else if (head[0]>tail[0] && head[1]>tail[1]){tail[0]++;tail[1]++;}
            else if (head[0]<tail[0] && head[1]>tail[1]){tail[0]--;tail[1]++;}
        }
        return tail;
    }

    public static void copyToClipboard(String received) {
        StringSelection stringSelection = new StringSelection(received);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
}