package Year_2022.Day_7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class day7_part1 {

    public static void main(String[] args) throws IOException {
        String received = totalDirSize("src/Year_2022/Day_7/source.txt")+"";
        copyToClipboard(received);
        System.out.println("----------------------------------------------------");
        System.out.println("FINAL RESULT: "+received);
    }

    public static long totalDirSize(String path) throws IOException {
        String[] file = Files.readAllLines(Path.of(path)).toArray(new String[0]);
        List<String> pathComponents = new LinkedList<>();
        Map<String,Long> directories = new HashMap<>();
        directories.put("/",0L);

        for (int i = 0; i < file.length; i++) {
            if (file[i].charAt(0) == '$' && file[i].contains("cd")){
                    String directory = file[i].split(" ")[2];
                    switch (directory){
                        case "..":
                            pathComponents.remove(pathComponents.size()-1);
                            break;
                        case "/":
                            pathComponents = new LinkedList<>();
                            pathComponents.add("/");
                            break;
                        default:
                            pathComponents.add(directory);
                            directories.put(String.join("", pathComponents),0L);
                            break;
                    }
            }else if (file[i].charAt(0) != '$' && !file[i].contains("dir")){
                for (int pathIndex = 0; pathIndex < pathComponents.size(); pathIndex++) {
                    String currentPath = pathComponents.stream()
                            .limit(pathIndex + 1)
                            .collect(Collectors.joining());
                    long fileSize = Long.parseLong(file[i].split(" ")[0]);
                    long dirSize = directories.get(currentPath);

                    directories.put(currentPath,dirSize+fileSize);
                }
            }
        }
        return directories.values().stream()
                .mapToLong(l -> l)
                .filter(x -> x < 100000L).sum();
    }

    public static void copyToClipboard(String received) {
        StringSelection stringSelection = new StringSelection(received);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
}