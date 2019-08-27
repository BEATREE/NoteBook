/**
    @Desciption 从求组中找出唯一出现一次得数
                    分解出题目1：   重复的数字出现偶数次
                    分解出题目2：   重复的数字不定次数
    思路：如果是 1 ，即重复的出现偶数次，则可以直接进行循环，并作异或运算。时间复杂度O(n) 空间复杂度O(1)
         如果是 2 ，即不定次数，则可以将出现的放在 hashMap 中，统计出现次数，最后遍历出出现次数是 1 的。时间复杂度O(n) 空间复杂度O(n)
 */

import java.util.*;

class OnlyOnce {

    public static int getOnce(int[] nums){          // 用于解决分解出题目1：   重复的数字出现偶数次
        if(nums.length == 0 || nums == null){
            return -1;
        }
        int result = nums[0];
        for(int i=1; i < nums.length; i++){
            result ^= nums[i];                  // 利用异或运算求解唯一数
        }

        return result;
    }

    public static int getOnce2(int[] nums){          // 用于解决分解出题目2：   重复的数字出现偶数次
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for(int num : nums){
            if(map.containsKey(num)){               // 如果已经存在了，则加上一
                map.put(num, map.get(num)+1);
            }else{                                  // 如果未存在则放入，并置出现次数为 1
                map.put(num, 1);
            }
        }
        
        for(HashMap.Entry<Integer, Integer> a : map.entrySet()){    // 获得map的entry集合，然后遍历，寻找只出现一次的数字
            if(a.getValue() == 1){
                return a.getKey();
            }
        }
        return -1;
    }

    public static void main(String[] args){
        int[] nums = {1, 2, 1, 3, 2, 3, 5};         // 都是偶数个重复，只有一个单独放出来
        int[] nums2 = {1, 2, 1, 3, 2, 2, 2, 3, 3, 5};         // 都是偶数个重复，只有一个单独放出来

        System.out.println(getOnce(nums));
        System.out.println(getOnce2(nums2));
    }
}