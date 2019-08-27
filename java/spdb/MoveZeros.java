/**
    @Title 移动0
    @Description 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
    @Examples 输入: [0,1,0,3,12] 输出: [1,3,12,0,0]
 */

import java.util.*;

class MoveZeros {

    public static void moveZeroes(int[] nums) {
        int pointer = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0){                   // 碰到了0
                pointer = i;
                for(;pointer < nums.length; pointer++){
                    if(nums[pointer] != 0){
                        nums[i] = nums[pointer];
                        nums[pointer] = 0;
                        break;
                    }
                }
            }
        }

        System.out.println(Arrays.toString(nums));
    }

    public static void moveZeroes2(int[] nums) {
        if(nums == null || nums.length < 2){
            return;
        }
        int counter = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0){
                counter++;
            }else{
                nums[i - counter] = nums[i];
            }
        }
        for(int j = 0; j < counter; j++){
            nums[nums.length - 1 -j] = 0;
        }
        System.out.println(Arrays.toString(nums));
    }

    public static void moveZeroes3(int[] nums) {
        if(nums == null || nums.length < 2){
            return;
        }
        int j = 0;                                 // j 是填充指针
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){                       // 如果非零就放到j的位置，同时 j 后移
                nums[j] = nums[i];
                j++;
            }
        }
        for(; j< nums.length; j++){
            nums[j] = 0;
        }
        
        System.out.println(Arrays.toString(nums));

    }

    public static void main(String[] args){
        int[] a = {0,1,0,3,12};
        moveZeroes(a);
        moveZeroes2(a);
        moveZeroes3(a);
    }
 }