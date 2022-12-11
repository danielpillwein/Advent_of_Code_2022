package Year_2022.Day_11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.util.LinkedList;
import java.util.List;

public class day11_part1 {

    public static void main(String[] args) throws IOException {
        String received = monkeyBusiness20("src/Year_2022/Day_11/source.txt") + "";
        copyToClipboard(received);
        System.out.println("----------------------------------------------------");
        System.out.println("FINAL RESULT: " + received);
    }

    public static long monkeyBusiness20(String path) throws IOException {
        String[] file = Files.readAllLines(Path.of(path)).toArray(new String[0]);
        List<Monkey> monkeys = new LinkedList<>();
        List<Long> interactionCount = new LinkedList<>();
        for (int i = 1; i < file.length - 4; i += 7) {
            Monkey monkey = new Monkey(file[i].replace("Starting items:", "").replace(" ", ""),
                    file[i + 1],
                    file[i + 2],
                    file[i + 3],
                    file[i + 4]);
            monkeys.add(monkey);
            interactionCount.add(0L);
        }
        for (int round = 0; round < 20; round++) {
            for (int monkeyIndex = 0; monkeyIndex < monkeys.size(); monkeyIndex++) {
                Monkey monkey = monkeys.get(monkeyIndex);
                long itemCount = monkey.items.size();

                for (int i = 0; i < itemCount; i++) {
                    interactionCount.set(monkeyIndex,interactionCount.get(monkeyIndex)+1);
                    long item = monkey.items.get(0);
                    item = monkey.updateWorry(item);
                    int nextMonkey = monkey.throwTo(item);
                    monkeys.get(nextMonkey).items.add(item);
                }
            }
        }
        List<Long> mostInteractions = interactionCount.stream()
                .sorted((x, y) -> (int) (y - x)).
                limit(2).toList();
        return mostInteractions.get(0)*mostInteractions.get(1);
    }

    public static void copyToClipboard(String received) {
        StringSelection stringSelection = new StringSelection(received);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
}