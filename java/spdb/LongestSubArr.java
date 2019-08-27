/**
    @Title 给定数组 arr， 返回 arr 的最长 递增 子序列长度。
    @Example arr=[2, 1, 5, 3, 6, 4, 8, 9, 7];   最长 递增 子序列为：[1, 3, 4, 8, 9]   返回 5
    @Type 动态规划
 */

public class LongestSubArr {

    public static int solution1(int[] arr){
        int[] subLen = new int[arr.length];         // 用于存储以arr当前位置结尾的，并且前边小于自己的数又多少
        int max = 1;
        arr[0] = 1;
        for(int i = 1; i < arr.length; i++){
            int count = 0;
            for(int j = 0; j < i; j++){             // 扫描从开头到当前位置的数，如果发现比自己小的，则记录下来
                if(arr[j] < arr[i] && count < subLen[j]){   // 如果小，并且它的长度还要大于count
                    count = subLen[j];              // 赋值给 subLen
                }
            }
            subLen[i] = count + 1;
            if( max < subLen[i]){
                max = subLen[i];
            }
        }
        return max;
    }

    public static void main(String[] args){
        int[] arr = {2, 1, 5, 3, 6, 4, 8, 9, 7};
        System.out.println(solution1(arr));
    }
}