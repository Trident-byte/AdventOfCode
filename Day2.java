import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day2 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(args[0]);
        File file = new File(args[0]);
        Scanner input = new Scanner(file);
        int ans = 0;
        while(input.hasNextLine()){
            String report = input.nextLine();
            String[] levels = report.split(" ");
            if(safe(levels) == -1){
                print(levels);
                ans++;
            }
            else{
                String[] newLevels = remove(levels, safe(levels) + 1);
                String[] secondVer = remove(levels, safe(levels));
                String[] thirdVar = null;
                if(safe(levels) > 0){
                    thirdVar = remove(levels,safe(levels) - 1);
                }
                if(safe(newLevels) == -1 || safe(secondVer) == -1){
                    print(levels);
                    ans++;
                }
                else if(thirdVar != null && safe(thirdVar) == -1){
                    print(levels);
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }

    public static int safe(String[] report){
        int[] diff = diffs(report);
        int isIncreasing = 1;
        if(diff[0] < 0){
            isIncreasing *= -1;
        }
        for(int i = 0; i < diff.length; i++){
            if(diff[i] * isIncreasing < 1 || diff[i] * isIncreasing > 3){
                return i;
            }
        }
        return -1;
    }

    private static int[] diffs(String[] report){
        int[] diff = new int[report.length - 1];
        int prev = Integer.parseInt(report[0]);
        for(int i = 1; i < report.length; i++){
            int next = Integer.parseInt(report[i]);
            diff[i - 1] = next - prev;
            prev = next;
        }
        return diff;
    }

    private static String[] remove(String[] level, int toRemove){
        String[] newLevels = new String[level.length - 1];
        int back = 0;
        for(int i = 0; i < level.length; i++){
            if(i == toRemove){
                back++;
            }
            else{
                newLevels[i - back] = level[i];
            }
        }
        return newLevels;
    }

    private static void print(String[] levels){
        String line = "[";
        for(String level: levels){
            line += level + ",";
        }
        line = line.substring(0, line.length() - 1) + "]";
        System.out.println(line);
    }
}
