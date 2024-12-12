import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
                    System.out.println(test[0] + " " + test[1]);
                    ans += test[0] * test[1];
                }
            }
        }
        System.out.println(ans);
    }

    public static int[] findCost(int startY, int startX, ArrayList<char[]> graph, boolean[][] traveledTo, int area, int dir){
        int horizontal = 0;
        int vertical = 0;
        int sides = 0;
        int[][] directions = {{-1,0}, {1,0}, {0,1}, {0,-1}};
        char original = graph.get(startY)[startX];
        traveledTo[startY][startX] = true;
        for(int i = 0; i < directions.length; i++){
            int[] direction = directions[i];
            if(valid(startY + direction[0], startX + direction[1], graph)){
                char other = graph.get(startY + direction[0])[startX + direction[1]];
                if(other != original){
                    if(i/2 == 0){
                        vertical++;
                    }
                    else{
                        horizontal++;
                    }
                }
                else if(!traveledTo[startY + direction[0]][startX + direction[1]]){
                    int[] additional = findCost(startY + direction[0], startX + direction[1], graph, traveledTo, area + 1, i/2);
                    area = additional[1];
                    sides += additional[0];
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
        if(dir == -1){
            sides += vertical + horizontal;
        }
        else if(dir == 0){
            sides += vertical;
        }
        else{
            sides += horizontal;
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
