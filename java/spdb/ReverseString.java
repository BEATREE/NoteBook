/**
    @Title 反转字符串
    @Description 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。

                不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。

                你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
    @Example    输入：["h","e","l","l","o"]         输出：["o","l","l","e","h"]
                输入：["H","a","n","n","a","h"]     输出：["h","a","n","n","a","H"]
 */

import java.util.*;

class ReverseString {

    public void reverseString(char[] s) {

        int head = 0, rear = s.length-1;
        for(; head < s.length/2; head++){
            char temp = s[rear];                     // 交换位置
            s[rear] = s[head];
            s[head] = temp;
            rear--;
        }
        System.out.println(Arrays.toString(s));
    }

    public static void main(String[] args){
        char[] cA = {'h','e','l','l','o'};
        char[] cA2 = {'H','a','n','b','a','h'};
        ReverseString rs = new ReverseString();
        rs.reverseString(cA);
        rs.reverseString(cA2);
        char[] emptytest = {' '};
        rs.reverseString(emptytest);
        
    }
}