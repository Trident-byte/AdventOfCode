import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class day12 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file = new File("day11.txt");
        Scanner input = new Scanner(file);
        long ans = 0;
        ArrayList<char[]> graph = new ArrayList<>();
        while(input.hasNextLine()){
            String line = input.nextLine();
            graph.add(line.toCharArray());
        }
        boolean[][] traveledTo = new boolean[graph.size()][graph.get(0).length];
        System.out.println(ans);
    }

    public static int findPerimeter(int startX, int startY, ArrayList<char[]> graph, boolean[][] traveledTo, int area){
        int ans = 0;
        int[][] directions = {{-1,0}, {0,-1}, {1,0}, {0,1}};
        for(int i = 0; i < graph.size(); i++){
            for(int j = 0; j < graph.get(0).length; j++){
                char original = graph.get(i)[j];
                for(int[] direction: directions){
                    if(valid(i + direction[0], j + direction[1], graph)){
                        char other = graph.get(i + direction[0])[j + direction[1]];
                        if(other != original){

                        }
                    }
                }
            }
        }
        return ans;
    }

    public static boolean valid(int startY, int startX, ArrayList<char[]> graph){
        if(startX < 0 && startY < 0){
            return false;
        }
        return true;
    }
}
