package Year_2022.Day_6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

public class day6_part1 {

    public static void main(String[] args) throws IOException {
        String received = start_of_packet_marker("src/Year_2022/Day_6/source.txt")+"";
        copyToClipboard(received);
        System.out.println("----------------------------------------------------");
        System.out.println("FINAL RESULT: "+received);
    }

    public static long start_of_packet_marker(String path) throws IOException {
        String[] file = Files.readAllLines(Path.of(path)).toArray(new String[0]);
        long counter = 0;
        for (int i = 0; i < file.length; i++) {
            for (int ii = 0; ii < file[i].length()-3; ii++) {
                if (!containsDublicates(file[i].substring(ii,ii+4))){
                    counter += ii+4;
                    break;
                }
            }
        }
        return counter;
    }
    public static boolean containsDublicates(String input){
        for (int i = 0; i <input.length()-1; i++) {
            String s = input +"";
            if(s.replaceAll(input.charAt(i)+"","").length()<3) return true;
        }
        return false;
    }

    public static void copyToClipboard(String received) {
        StringSelection stringSelection = new StringSelection(received);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
}