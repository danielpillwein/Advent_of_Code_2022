package Year_2022.Day_2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

public class day2_part1 {

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
            String game = file[i].replace(" ","").replace("A","1").replace("X","1").replace("B","2").replace("Y","2").replace("C","3").replace("Z","3");
            System.out.println(game);
            String me = game.charAt(1)+"";
            if ((game.charAt(0)+"").equals(me))score+=3+getValue(me);
            else if (game.equals("13") || game.equals("21") || game.equals("32"))score+=getValue(me);
            else if (game.equals("12") || game.equals("23") || game.equals("31"))score+=6+getValue(me);
        }
        return score;
    }

    public static long getValue(String input){
        return Long.parseLong(input);
    }

    public static void copyToClipboard(String received) {
        StringSelection stringSelection = new StringSelection(received);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
}