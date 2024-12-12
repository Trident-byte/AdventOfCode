import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class day11 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file = new File("day11.txt");
        Scanner input = new Scanner(file);
        long ans = 0;
        HashMap<Long, ArrayList<Long>> transformations = new HashMap<>();
        HashMap<Long, Long> occurances = new HashMap<>();
        while(input.hasNextLine()){
            String[] line = input.nextLine().split(" ");
            for(String stone: line){
                occurances.put(Long.parseLong(stone), (long) 1);
            }
        }
        for(int i = 0; i < 75; i++){
//            System.out.println(i);
            occurances = splitStones(occurances);
//            System.out.println(stones.size());
//            System.out.println(stones.toString());
        }
        System.out.println(occurances.size());
        for(long i: occurances.keySet()){
            ans += occurances.get(i);
        }
        System.out.println(ans);
    }

    private static HashMap<Long, Long> splitStones(HashMap<Long, Long> stones){
        HashMap<Long, Long> newStones = new HashMap<Long, Long>();
        for(Long stone: stones.keySet()){
            int digits = (((int) Math.log10(stone)) + 1);
            if(stone == 0){
                newStones.put((long) 1, stones.get(stone) + newStones.getOrDefault(1,(long) 0));
            }
            else if(digits % 2 == 0){
                int splitPoint = digits/2;
                int divider = (int) Math.pow(10, splitPoint);
                newStones.put(stone/divider, stones.get(stone) + newStones.getOrDefault(stone/divider,(long) 0));
                newStones.put(stone % divider, stones.get(stone) + newStones.getOrDefault(stone%divider,(long) 0));
            }
            else{
                newStones.put(stone * 2024, stones.get(stone) + newStones.getOrDefault(stone * 2024,(long) 0));
            }
        }
        return newStones;
    }
}
