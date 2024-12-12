import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
                    HashSet<int[]> sides = new HashSet<>();
                    int test = findCost(i,j, graph, traveledTo, 1, sides);
                    System.out.println(test + " " + sides.size());
                    ans += test * sides.size();
                }
            }
        }
        System.out.println(ans);
    }

    public static int findCost(int startY, int startX, ArrayList<char[]> graph, boolean[][] traveledTo, int area, HashSet<int[]> sides) {
        int horizontal = 0;
        int vertical = 0;
        int[][] directions = {{-1,0}, {1,0}, {0,1}, {0,-1}};
        int newY;
        int newX;
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
                    area = findCost(newY, newX, graph, traveledTo, area + 1, sides);
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
        return area;
    }

    public static boolean valid(int startY, int startX, ArrayList<char[]> graph){
        if(startX < 0 || startY < 0 || startY >= graph.size() || startX >= graph.get(0).length){
            return false;
        }
        return true;
    }
}
