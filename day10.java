import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class day10 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("day10.txt");
        Scanner input = new Scanner(file);
        int ans = 0;
        ArrayList<int[]> graph = new ArrayList<>();
        while(input.hasNextLine()){
            String line = input.nextLine();
            int[] newLine = new int[line.length()];
            for(int i = 0; i < line.length(); i++){
                newLine[i] = line.charAt(i) - '0';
            }
            graph.add(newLine);
        }
        for(int i = 0; i < graph.size(); i++){
            for(int j = 0; j < graph.get(0).length; j++){
                if(graph.get(i)[j] == 0){
                    ArrayList<int[]> found = new ArrayList<>();
                    ans += findPaths(j, i, graph);
                }
            }
        }
        System.out.println(ans);
    }

    public static int findPaths(int startX, int startY, ArrayList<int[]> graph){
        if(graph.get(startY)[startX] == 9){
            return 1;
        }
        int ans = 0;
        int[][] directions = {{0,1}, {0,-1}, {1,0},{-1,0}};
        for(int[] direction: directions){
            if(valid(startX, startY, graph, direction)){
                ans += findPaths(startX + direction[1], startY + direction[0], graph);
            }
        }
        return ans;
    }

    public static boolean valid(int startX, int startY, ArrayList<int[]> graph, int[] direction){
        int newX = startX + direction[1];
        int newY = startY + direction[0];
        if(newX >= 0 && newX < graph.get(0).length && newY >= 0 && newY < graph.size()){
            int firstVal = graph.get(startY)[startX];
            int secondVal = graph.get(newY)[newX];
            return secondVal - firstVal == 1;
        }
        return false;
    }

    public static boolean wasFound(int testX, int testY, ArrayList<int[]> found){
        for(int[] coord: found){
            if(testX == coord[1] && testY == coord[0]){
                return true;
            }
        }
        return false;
    }
}
