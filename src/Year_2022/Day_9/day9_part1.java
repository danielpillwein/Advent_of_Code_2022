package Year_2022.Day_9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.util.LinkedList;
import java.util.List;

public class day9_part1 {

    public static void main(String[] args) throws IOException {
        String received = methodName("src/Year_2022/Day_9/source.txt") + "";
        copyToClipboard(received);
        System.out.println("----------------------------------------------------");
        System.out.println("FINAL RESULT: " + received);
    }

    public static long methodName(String path) throws IOException {
        String[] file = Files.readAllLines(Path.of(path)).toArray(new String[0]);
        int[] head = new int[]{0, 0};
        int[] tail = new int[]{0, 0};
        List<String> positionsVisited = new LinkedList<>();

        for (String s : file) {
            char direction = s.charAt(0);
            long steps = Long.parseLong(s.split(" ")[1]);
            for (long step = 0; step < steps; step++) {
                switch (direction) {
                    case 'U' -> head[1]++;
                    case 'R' -> head[0]++;
                    case 'D' -> head[1]--;
                    case 'L' -> head[0]--;
                }
                updateTail(head, tail);
                if (!positionsVisited.contains(tail[0] + ":" + tail[1])) positionsVisited.add(tail[0] + ":" + tail[1]);
            }
        }
        return positionsVisited.size();
    }

    public static int[] updateTail(int[] head, int[] tail) {
        long xDiff = Math.abs(head[0] - tail[0]);
        long yDiff = Math.abs(head[1] - tail[1]);
        if (Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2)) > Math.sqrt(2)) {
            if (xDiff == 0) {
                tail[1] += head[1] > tail[1] ? 1 : -1;
            } else if (yDiff == 0) {
                tail[0] += head[0] > tail[0] ? 1 : -1;
            } else if (head[0] < tail[0] && head[1] < tail[1]) {
                tail[0]--;
                tail[1]--;
            } else if (head[0] > tail[0] && head[1] < tail[1]) {
                tail[0]++;
                tail[1]--;
            } else if (head[0] > tail[0] && head[1] > tail[1]) {
                tail[0]++;
                tail[1]++;
            } else if (head[0] < tail[0] && head[1] > tail[1]) {
                tail[0]--;
                tail[1]++;
            }
        }
        return tail;
    }

    public static void copyToClipboard(String received) {
        StringSelection stringSelection = new StringSelection(received);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
}