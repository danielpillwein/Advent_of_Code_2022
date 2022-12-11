package Year_2022.Day_11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.util.LinkedList;
import java.util.List;

public class day11_part2 {

    public static void main(String[] args) throws IOException {
        String received = monkeyBusiness1000("src/Year_2022/Day_11/source.txt")+"";
        copyToClipboard(received);
        System.out.println("----------------------------------------------------");
        System.out.println("FINAL RESULT: "+received);
    }

    public static long monkeyBusiness1000(String path) throws IOException {
        String[] file = Files.readAllLines(Path.of(path)).toArray(new String[0]);
        List<Monkey2> monkeys = new LinkedList<>();
        List<Long> interactionCount = new LinkedList<>();

        for (int i = 1; i < file.length - 4; i += 7) {
            Monkey2 monkey = new Monkey2(file[i].replace("Starting items:", "").replace(" ", ""),
                    file[i + 1],
                    file[i + 2],
                    file[i + 3],
                    file[i + 4]);
            monkeys.add(monkey);
            interactionCount.add(0L);
        }
        for (long round = 0; round < 10000; round++) {
            for (int monkeyIndex = 0; monkeyIndex < monkeys.size(); monkeyIndex++) {
                Monkey2 monkey = monkeys.get(monkeyIndex);
                long itemCount = monkey.items.size();

                for (long i = 0; i < itemCount; i++) {
                    long newInteractionCount = interactionCount.get(monkeyIndex)+1;
                    interactionCount.set(monkeyIndex,newInteractionCount);

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