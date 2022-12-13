package Year_2022.Day_12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.util.ArrayList;
import java.util.Arrays;

public class day12_part1 {
    static int[][] input, distance, visits;

    public static void main(String[] args) throws IOException {
        String received = methodName("src/Year_2022/Day_12/source.txt") + "";
        copyToClipboard(received);
        System.out.println("----------------------------------------------------");
        System.out.println("FINAL RESULT: " + received);
    }

    public static long methodName(String path) throws IOException {
        String[] file = Files.readAllLines(Path.of(path)).toArray(new String[0]);
        input = new int[file.length][file[0].length()];
        distance = init(file.length,file[0].length());
        visits = init(file.length,file[0].length());
        int[] start = new int[2];
        int[] end = new int[2];

        for (int i = 0; i < file.length; i++) {
            for (int ii = 0; ii < file[i].length(); ii++) {
                if (file[i].charAt(ii) == 'S') {
                    input[i][ii] = 0;
                    distance[i][ii] = 0;
                    start[0] = i;
                    start[1] = ii;
                } else if (file[i].charAt(ii) == 'E') {
                    input[i][ii] = 25;
                    end[0] = i;
                    end[1] = ii;
                } else {
                    input[i][ii] = file[i].charAt(ii) - 97;
                }
            }
        }

        int[] position = start;
        int counter = 0;
        int runs = 200;
        do {
            System.out.println("pos: "+position[0]+":"+position[1]);
            visits[position[0]][position[1]] = 0;
            updateNeighbours(position);
            position = updatePosition(position);
            counter++;
            System.out.println("------------visits----------------");
            print(visits);
            System.out.println("------------DISTANCE----------------");
            print(distance);
        } while (position[0] != end[0] || position[1] != end[1]);
        //System.out.println("-------------INPUT------------------");
        //print(input);
        //System.out.println("------------DISTANCE----------------");
        //print(distance);
        return distance[end[0]][end[1]];
    }

    public static int getDistance(int x, int y, int[] oldpos){
        if (x>=0 && y>=0 && x<distance.length && y<distance[0].length){
            System.out.println(x+":"+y);
            if (Math.abs(input[x][y]-input[oldpos[0]][oldpos[1]])<=1 && visits[x][y]>0) return distance[x][y];
            else return Integer.MAX_VALUE;
        }else return Integer.MAX_VALUE;
    }
    public static int[] updatePosition(int[] position){
        int x = position[0];
        int y = position[1];
        int up = movePossible(x-1,y,position)?getDistance(x-1,y,position):Integer.MAX_VALUE;
        int down = movePossible(x+1,y,position)?getDistance(x+1,y,position):Integer.MAX_VALUE;
        int left = movePossible(x,y-1,position)?getDistance(x,y-1,position):Integer.MAX_VALUE;
        int right = movePossible(x,y+1,position)?getDistance(x,y+1,position):Integer.MAX_VALUE;
        System.out.println("up\t"+up);
        System.out.println("down\t"+down);
        System.out.println("left\t"+left);
        System.out.println("right\t"+right);
        int min = Math.min(Math.min(up,down),Math.min(left,right));
        if (up==min) position[0]--;
        else if (down==min) position[0]++;
        else if (left==min) position[1]--;
        else position[1]++;
        return new int[]{position[0],position[1]};
    }

    public static boolean movePossible(int x, int y, int[] oldpos){
        if (x>=0 && y>=0 && x<distance.length && y<distance[0].length) {
            return Math.abs(input[x][y] - input[oldpos[0]][oldpos[1]]) <= 1;
        }else return false;
    }
    public static void updateNeighbours(int[] position){
        int x = position[0];
        int y = position[1];
        int newDistance = distance[x][y]+1;
        if (x>0){
            System.out.println(Math.min(distance[x-1][y],newDistance));
            distance[x-1][y] = movePossible(x-1,y,position)?Math.min(distance[x-1][y],newDistance):distance[x-1][y];
        }
        if (x<distance.length-1){
            distance[x+1][y] = movePossible(x+1,y,position)?Math.min(distance[x+1][y],newDistance):distance[x+1][y];
        }
        if (y>0){
            distance[x][y-1] = movePossible(x,y-1,position)?Math.min(distance[x][y-1],newDistance):distance[x][y-1];
        }
        if (y<distance[0].length-1){
            distance[x][y+1] = movePossible(x,y+1,position)?Math.min(distance[x][y+1],newDistance):distance[x][y+1];
        }
    }

    private static void umfeld(int[][] in,int[] pos) {
        for (int i = pos[0]-1; i <= pos[0]+1; i++) {
            for (int ii = pos[1]-1; ii <= pos[1]+1; ii++) {
                if (i>=0 && ii>=0 && i<in.length && ii<in[0].length){
                    System.out.print(in[i][ii]+"\t");
                }else System.out.print(".\t");
            }
            System.out.println();
        }
    }
    private static void print(int[][] in) {
        for (int i = 0; i < in.length; i++) {
            for (int ii = 0; ii < in[i].length; ii++) {
                if (in[i][ii]==Integer.MAX_VALUE-1) System.out.print(".\t");
                else System.out.print(in[i][ii]+"\t");
            }
            System.out.println();
        }
    }

    public static int[][] init(int rows,int columns){
        int[][] erg = new int[rows][columns];
        for (int i = 0; i < erg.length; i++) {
            Arrays.fill(erg[i],Integer.MAX_VALUE-1);
        }
        return erg;
    }

    public static void copyToClipboard(String received) {
        StringSelection stringSelection = new StringSelection(received);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
}