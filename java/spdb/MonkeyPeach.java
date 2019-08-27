// the problem aboub monkey and peach
/**
    @Description: 猴子吃桃问题：猴子第一天摘下若干个桃子，当即吃了一半，还不过瘾，又多吃了一个，
                第二天早上又将剩下的桃子吃掉一半，又多吃了一个。以后每天早上都吃前一天剩下的一半零一个。
                到第10天早上想再吃时，见只剩下一个桃子了。求第一天共摘多少个桃子？
 */

import java.util.*;

class MonkeyPeach {
    
    public static int getPeach( int day,int num){
        if(day == 1){               // case of the first day
            return num;             // when day == 10, what we return is the number of day == 9
            // so when day == 1; the parameter of num is actually the original number
        }

        return getPeach(day-1, (num+1)<<1);
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int day, remainNum;
        day = sc.nextInt();
        remainNum = sc.nextInt();
        System.out.println(getPeach(day, remainNum));
    }
}