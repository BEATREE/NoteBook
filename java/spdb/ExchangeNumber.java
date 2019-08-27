/**
    @Title 输入十个数，最大数和最后一个数交换，最小树和第一个数交换
 */

import java.util.*;

public class ExchangeNumber{

    public static void exchange1(int[] arr){
        System.out.println(Arrays.toString(arr));
        int max = 0;
        int min = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > arr[max]){               // get the index of max num in this array
                max = i;
            }
            if(arr[i] < arr[min]){               // get the index of min num in this array
                min = i;
            }
        }

        changeNum(arr, 0, min);
        changeNum(arr, arr.length-1, max);

        System.out.println(Arrays.toString(arr));

    }

    public static void changeNum(int[] arr, int pos1, int pos2){
        int temp;
        temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = temp;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int length;                         // the length of the array
        length = sc.nextInt();
        int[] arr = new int[length];
        for(int i = 0; i < length; i++){
            arr[i] = sc.nextInt();
        }
        exchange1(arr);
    }
}