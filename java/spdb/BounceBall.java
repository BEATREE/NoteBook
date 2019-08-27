/**
    @Title 小球从100米下落，每次回弹一半距离，第几次落地后的总距离。
    1       2       3       4
    100     50      25      12.5
    100     200     250     300

    miles[n] = heightN * 2 + miles[n-1]
    heightN = height/2^(n-1)
    miles[n] = heightN/2^(n-1) + miles[n-1];
 */

import java.util.*;

public class BounceBall {

    public static int solution1(int height, int time){
        if(time == 1){
            return height;
        }
        int heightN = height>>(time-1);                 // move right
        return heightN*2 + solution1(height, time-1);
    }

    public static int solution2(int height, int time){
        int heightN = height;
        int countHeight = 0;
        
        for(int i = 1; i < time; i++){
            heightN = heightN>>1;
            countHeight += 2 * heightN;
        }

        countHeight += 100;
        return countHeight;
    }

    public static void main(String[] args){
        System.out.println(solution1(100, 3));
        System.out.println(solution2(100, 3));

    }
}