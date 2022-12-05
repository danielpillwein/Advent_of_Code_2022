package Year_2022.Day_5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.util.*;
import java.util.stream.Collectors;

public class day5_part1 {

    public static void main(String[] args) throws IOException {
        String received = moveCrate("src/Year_2022/Day_5/source.txt")+"";
        copyToClipboard(received);
        System.out.println("----------------------------------------------------");
        System.out.println("FINAL RESULT: "+received);
    }

    public static String moveCrate(String path) throws IOException {
        String[] file = Files.readAllLines(Path.of(path)).toArray(new String[0]);
        List<List<String>> input = getInput();
        for (int i = 0; i < file.length; i++) {
            Integer[] command = Arrays.stream(file[i].replace("move ", "").replace(" from ", ";").replace(" to ", ";").split(";")).map(Integer::parseInt).toArray(Integer[]::new);
            for (int move = 0; move < command[0]; move++) {
                String crate = input.get(command[1]-1).get(input.get(command[1]-1).size()-1);
                input.get(command[1]-1).remove(input.get(command[1]-1).size()-1);
                input.get(command[2]-1).add(crate);
            }
        }
        return input.stream().map(x -> x.get(x.size()-1)).collect(Collectors.joining());
    }

    public static List<List<String>> getInput(){
        List<List<String>> input = new LinkedList<>();
        input.add(new LinkedList<String>(Arrays.asList(("J,H,P,M,S,F,N,V").split(","))));
        input.add(new LinkedList<String>(Arrays.asList(("S,R,L,M,J,D,Q").split(","))));
        input.add(new LinkedList<String>(Arrays.asList(("N,Q,D,H,C,S,W,B").split(","))));
        input.add(new LinkedList<String>(Arrays.asList(("R,S,C,L").split(","))));
        input.add(new LinkedList<String>(Arrays.asList(("M,V,T,P,F,B").split(","))));
        input.add(new LinkedList<String>(Arrays.asList(("T,R,Q,N,C").split(","))));
        input.add(new LinkedList<String>(Arrays.asList(("G,V,R").split(","))));
        input.add(new LinkedList<String>(Arrays.asList(("C,Z,S,P,D,L,R").split(","))));
        input.add(new LinkedList<String>(Arrays.asList(("D,S,J,V,G,P,B,F").split(","))));
        return input;
    }

    public static void copyToClipboard(String received) {
        StringSelection stringSelection = new StringSelection(received);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
}