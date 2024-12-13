import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class day12 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file = new File("day12.txt");
        Scanner input = new Scanner(file);
        long ans = 0;
        ArrayList<char[]> graph = new ArrayList<>();
        while(input.hasNextLine()){
            String line = input.nextLine();
            graph.add(line.toCharArray());
        }
        boolean[][] traveledTo = new boolean[graph.size()][graph.get(0).length];
        for(int i = 0; i < graph.size(); i++){
            for(int j = 0; j < graph.get(0).length; j++){
                if(!traveledTo[i][j]) {
                    int[] test = findCost(i,j, graph, traveledTo, 1, -1);
                    System.out.print(graph.get(i)[j] + ":");
                    System.out.println(test[0] + " " + test[1]);
                    ans += test[0] * test[1];
                }
            }
        }
        // int[] test = findCost(0,0, graph, traveledTo, 1, -1);
        // System.out.println(test[0] + " " + test[1]);
        System.out.println(ans);
    }

    public static int[] findCost(int startY, int startX, ArrayList<char[]> graph, boolean[][] traveledTo, int area, int prevDir) {
        int[][] directions = {{-1,0}, {1,0}, {0,1}, {0,-1}};
        int newY;
        int newX;
        int horizontal = 0;
        int vertical = 0;
        int[] moved = new int[4];
        if(prevDir != -1){
            moved[prevDir] = 1;
        }
        int sides = 0;
        char original = graph.get(startY)[startX];
        traveledTo[startY][startX] = true;
        for(int i = 0; i < directions.length; i++){
            int[] direction = directions[i];
            newY = startY + direction[0];
            newX = startX + direction[1];
            if(valid(newY, newX, graph)){
                char other = graph.get(newY)[newX];
                if(other != original){
                    if(i/2 == 0){
                        vertical++;
                    }
                    else{
                        horizontal++;
                    }
                }
                else if(!traveledTo[newY][newX]){
                    int back = i;
                    if(i == 1 || i ==3) back--;
                    else back++;
                    int[] additional = findCost(newY, newX, graph, traveledTo, area + 1, back);
                    area = additional[1];
                    sides += additional[0];
                    moved[i] = 1;
                }
                else{
                    moved[i] = 1;
                }
            }
            else{
                if(i/2 == 0){
                    vertical++;
                }
                else{
                    horizontal++;
                }
            }
        }
        // if(startY == 2 && startX == 3){
        //     System.out.println(Arrays.toString(moved));
        // }
        if(vertical == 1 && horizontal == 1){
            // System.out.println(startY + " " + startX + " " + 1);
            sides++;
        }
        else if(horizontal + vertical == 4){
            // System.out.println(startY + " " + startX + " " + 4);
            sides += 4;
        }
        else if(horizontal > 0 && vertical > 0){
            // System.out.println(startY + " " + startX + " " + Math.max(horizontal, vertical));
            sides += Math.max(horizontal, vertical);
        }
        if(moved[0] == 1){
            for(int i = 0; i < 2; i++){
                if(moved[i + 2] == 1){
                    int[] testDir = {startY + directions[0][0], startX + directions[i+2][1]};
                    if(valid(testDir[0], testDir[1], graph) && graph.get(testDir[0])[testDir[1]] != original){
                        // System.out.println(startY + " " + startX + " " + 1);
                        sides++;
                    }
                }
            }
        }
        if(moved[1] == 1){
            // System.out.println("test");
            for(int i = 0; i < 2; i++){
                if(moved[i + 2] == 1){
                    int[] testDir = {startY + directions[1][0], startX + directions[i+2][1]};
                    // System.out.println(testDir[0] + " " + testDir[1]);
                    if(valid(testDir[0], testDir[1], graph) && graph.get(testDir[0])[testDir[1]] != original){
                        sides++;
                    }
                }
            }
        }
        int[] ans = {sides, area};
        return ans;
    }

    public static boolean valid(int startY, int startX, ArrayList<char[]> graph){
        if(startX < 0 || startY < 0 || startY >= graph.size() || startX >= graph.get(0).length){
            return false;
        }
        return true;
    }
}
