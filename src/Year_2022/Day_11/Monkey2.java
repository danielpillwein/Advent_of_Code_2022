package Year_2022.Day_11;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Monkey2 {
    List<Long> items;
    String operation;
    String operationValue;
    long testValue;
    int nextTrueMonkey;
    int nextFalseMonkey;

    public Monkey2(String items, String operation, String testValue, String nextTrueMonkey, String nextFalseMonkey) {
        this.items = Arrays.stream(items.split(","))
                .map(Long::valueOf).
                collect(Collectors.toList());
        this.operation = operation.substring(operation.indexOf("old")+4,operation.indexOf("old")+5);
        this.operationValue = operation.replace("Operation: new = old "+this.operation,"").replace(" ","");
        this.testValue = Long.parseLong(testValue.substring(testValue.indexOf("by")+3));
        this.nextTrueMonkey = Integer.parseInt(nextTrueMonkey.split("monkey")[1].replace(" ",""));
        this.nextFalseMonkey = Integer.parseInt(nextFalseMonkey.split("monkey")[1].replace(" ",""));
    }

    public long updateWorry(long item){
        switch (operation) {
            case "+" -> {
                this.items.remove(item);
                item += operationValue.equals("old") ? item : Long.parseLong(operationValue);
            }
            case "*" -> {
                this.items.remove(item);
                item *= operationValue.equals("old") ? item : Long.parseLong(operationValue);
            }
        }
        return item%9699690;
    }

    public int throwTo(long item){
        return item%testValue==0?nextTrueMonkey:nextFalseMonkey;
    }

    @Override
    public String toString() {
        return "Monkey{" +
                "\n\titems=" + items +
                "\n\toperation='" + operation +
                "\n\toperationValue=" + operationValue +
                "\n\ttestValue=" + testValue +
                "\n\tnextTrueMonkey=" + nextTrueMonkey +
                "\n\tnextFalseMonkey=" + nextFalseMonkey+
                "\n}";
    }
}
