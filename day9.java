import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class day9 {
    public static void main(String[] args) throws FileNotFoundException{
        File file = new File("day9.txt");
        Scanner input = new Scanner(file);
        long ans = 0;
        String line = "";
        line = input.nextLine();
        int length = 0;
        int curIndex = 0;
        int[] sizes = new int[line.length()];
        for(char c: line.toCharArray()){
            length += c - '0';
        }
        int[] test = new int[length];
        Arrays.fill(test, -1);
        for(int i = 0; i < line.length(); i++){
            int amt = line.charAt(i) - '0';
            sizes[i] = amt;
            if(i % 2 == 0){
                for(int j = 0; j < amt; j++){
                    test[curIndex] = i/2;
                    curIndex++;
                }
            }
            else{
                curIndex += amt;
            }
        }
        input.close();
        checkSum(test, sizes);
        for(int i = 0; i < test.length; i++){
            if(test[i] != -1) {
                ans += i * test[i];
            }
        }
        System.out.println(ans);
//        System.out.println(Arrays.toString(test));
    }

    private static void checkSum(int[] array, int[] sizes){
        //Part1
        /*
        int left = 0;
        int right = array.length - 1;
        while(left < right){
            if(array[left] == -1){
                while(array[right] == -1){
                    right--;
                }
                array[left] = array[right];
                array[right] = -1;
                right--;
            }
            left++;
        }

         */

        int[] starts = new int[sizes.length];
        int start = 0;
        for(int i = 0; i < sizes.length;i++){
            starts[i] = start;
            start += sizes[i];
        }
        for(int i = sizes.length - 1; i > 0; i -= 2){
            int blockSize = sizes[i];
            for(int j = 1; j < i; j += 2){
                int gapSize = sizes[j];
                if(blockSize <= gapSize){
                    swapBlocks(starts[i], starts[j], array, blockSize);
                    sizes[j] -= blockSize;
                    starts[j] += blockSize;
                    break;
                }
            }
        }
    }

    private static void swapBlocks(int start, int gapIndex, int[] array, int size){
        int right = start;
        int left = gapIndex;
        for(int i = 0; i < size; i++){
            array[left] = array[right];
            array[right] = -1;
            left++;
            right++;
        }
    }
}



