/**
    @Title 不使用临时变量，交换两个数的值
 */
import java.util.*;

public class ExchangeTwoNums {

    public static void solution1(int a, int b){
        System.out.println("Before Exchange: a = "+ a + ",  b = "+ b);
        System.out.println("Before Exchange: a.binary value = "+ Integer.toBinaryString(a) + ",  b.binary value = "+ Integer.toBinaryString(b));

        a = a^b;
        b = a^b;            // b = a^b^b = a
        a = a^b;            // a = a^b^b = a^b^a = b

        System.out.println("After Exchange: a = "+ a + ",  b = "+ b);
        System.out.println("After Exchange: a.binary value = "+ Integer.toBinaryString(a) + ",  b.binary value = "+ Integer.toBinaryString(b));
    }

    public static void main(String[] args){
        solution1(1, 3);
    }
}