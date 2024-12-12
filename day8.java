import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class day8 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("day8.txt");
        Scanner input = new Scanner(file);
        int ans = 0;
        int lines = 0;
        int length = 0;
        HashMap<Character, ArrayList<int[]>> antennas = new HashMap<>();
        while(input.hasNextLine()){
            String line = input.nextLine();
            length = line.length();
            for(int i = 0; i < line.length(); i++){
                char character = line.charAt(i);
                if(character != '.'){
                    if(!antennas.containsKey(character)){
                        ArrayList<int[]> coords = new ArrayList<>();
                        int[] coord = {lines, i};
                        coords.add(coord);
                        antennas.put(character, coords);
                    }
                    else{
                        ArrayList<int[]> coords = antennas.get(character);
                        int[] coord = {lines, i};
                        coords.add(coord);
                        antennas.put(character, coords);
                    }
                }
            }
            lines++;
        }
        boolean[][] graph = new boolean[lines][length];
//        for(char c: antennas.keySet()){
//            for(int[] coords: antennas.get(c)){
//                graph[coords[0]][coords[1]] = true;
//            }
//        }
        System.out.println(antiNodes(antennas, graph));
//        for(boolean[] line: graph){
//            System.out.println(Arrays.toString(line));
//        }
    }

    public static int antiNodes(HashMap<Character, ArrayList<int[]>> antennas, boolean[][] graph){
        int ans = 0;
        for(char c: antennas.keySet()){
            ArrayList<int[]> nodes = antennas.get(c);
            for(int i = 0; i < nodes.size(); i++){
                int[] firstCoord = nodes.get(i);
                for(int j = i + 1; j < nodes.size(); j++){
                    int[] secondCoord = nodes.get(j);
                    ans += checkCoords(firstCoord, secondCoord, graph, nodes);
                }
            }
//            System.out.println(ans);
        }
        return ans;
    }

    public static boolean valid(int distY, int distX, boolean[][] graph){
        if(distY < 0 || distX < 0 || distY >= graph.length || distX >= graph[0].length){
            return false;
        }
        return true;
    }

    public static boolean occupied(int distY, int distX, boolean[][] graph){
        return !graph[distY][distX];
    }

    public static int checkCoords(int[] firstCoord, int[] secondCoord, boolean[][] graph, ArrayList<int[]> nodes){
        int ans = 0;
        int yDist = secondCoord[0] - firstCoord[0];
        int xDist = secondCoord[1] - firstCoord[1];
        int newY = firstCoord[0];
        int newX = firstCoord[1];
        while(valid(newY, newX, graph)){
            if(!graph[newY][newX]){
                ans++;
                graph[newY][newX] = true;
            }
            newY -= yDist;
            newX -= xDist;
        }
        newY = secondCoord[0];
        newX = secondCoord[1];
        while(valid(newY, newX, graph)){
            if(!graph[newY][newX]){
                ans++;
                graph[newY][newX] = true;
            }
            newY += yDist;
            newX += xDist;
        }
        return ans;
    }
}
