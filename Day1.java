import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day1{
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(args[0]);
        File file = new File(args[0]);
        Scanner input = new Scanner(file);
        ArrayList<Integer> first = new ArrayList<>();
        ArrayList<Integer> second = new ArrayList<>();
        int ans = 0;
        int position = 0;
        while(input.hasNextLine()){
            String[] split = input.nextLine().split("   ");
            first.add(Integer.parseInt(split[0]));
            second.add(Integer.parseInt(split[1]));
        }
        Collections.sort(second);
        for(int i = 0; i < first.size(); i++){
            int repeats = 0;
            position = second.indexOf(first.get(i).intValue());
            if(position != -1){
                while(position < second.size() && second.get(position).equals(first.get(i))){
                    repeats++;
                    position++;
                }
            }
            ans += repeats * first.get(i);
        }
        System.out.println(ans);
    }
}