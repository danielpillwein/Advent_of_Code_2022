package Year_2022.Day_2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

public class day2_part2 {

    public static void main(String[] args) throws IOException {
        String received = RockPaperScissorsScore("src/Year_2022/Day_2/source.txt")+"";
        copyToClipboard(received);
        System.out.println("----------------------------------------------------");
        System.out.println("FINAL RESULT: "+received);
    }

    public static long RockPaperScissorsScore(String path) throws IOException {
        String[] file = Files.readAllLines(Path.of(path)).toArray(new String[0]);
        long score = 0;
        for (int i = 0; i < file.length; i++) {
            String game = file[i].replaceAll(" ","").replaceAll("[A|X]","1").replaceAll("[B|Y]","2").replaceAll("[C|Z]","3");
            int opponent = Integer.parseInt(game.charAt(0)+"");
            int me = Integer.parseInt(game.charAt(1)+"");
            switch (me){
                case 1:
                    score += lose(opponent);
                    break;
                case 2:
                    score += opponent+3;
                    break;
                case 3:
                    score += win(opponent)+6;
                    break;
            }
            System.out.println(opponent+":"+me+" "+score);
        }
        return score;
    }

    public static int win(int input){
        switch (input){
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 1;
        }
        return 0;
    }

    public static int lose(int input){
        switch (input){
            case 1:
                return 3;
            case 2:
                return 1;
            case 3:
                return 2;
        }
        return 0;
    }

    public static void copyToClipboard(String received) {
        StringSelection stringSelection = new StringSelection(received);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
}