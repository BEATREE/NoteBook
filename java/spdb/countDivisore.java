/*
 *  @Description 输入一组数N和数字b ，求出该组数字中能被b 整除的个数。如输入1 2 3 4 5 6和 2，结果输出为3
 */

import java.util.*;

class countDivisore{

    public static int countDivisore(int[] n, int target){
        int result = 0;
        for(int single : n){
            if(single % target == 0){                    // 可以整除则进行加一
                result++;
            }
        }

        return result;
    }

    public static void main(String[] args){
        int length, target;                         // 数组长度与要查找的数
        Scanner sc = new Scanner(System.in);        // 读取输入

        System.out.println("请输入数组的长度:");
        length = sc.nextInt();

        int n[] = new int[length];                  // 定义数组
        for(int i=0; i < length; i++){
            System.out.println("请输入数组的第"+(i+1)+"个数字:");
            n[i] = sc.nextInt();
        }
        System.out.println("请输入要查找的数字：");
        target = sc.nextInt();

        System.out.println("可被整除的数有 "+countDivisore(n, target)+" 个");
    }
}