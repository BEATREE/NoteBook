/**
    @Title 有效的字母异位词
    @Description 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词
    @Examples 输入: s = "anagram", t = "nagaram" 输出: true
 */

import java.util.*;

class ValidAnagram {

    public static boolean isAnagram(String s, String t){
        if (s.length() != t.length()) {
          return false;
        }
        
        int[] alpha = new int[26];                      // 最多 26 个字母，所以声明一个 数组
        for(int i = 0; i < s.length(); i++){
            alpha[s.charAt(i) - 'a']++;                 // 循环 s，在对应的位置上加上 1
        }
        for(int j = 0; j < t.length(); j++){
            alpha[t.charAt(j) - 'a']--;                 // 循环 t，对应位置减去1
            if(alpha[t.charAt(j) - 'a'] < 0){
                return false;                           // 当有一个变为负数的时候，说明 t 中该字母多了，故返回false
            }
        }
        return true;
    }

    public static void main(String[] args){
        String a, b;
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入第一个单词：");   a = sc.nextLine();
        System.out.println("请输入第二个单词：");   b = sc.nextLine();
        System.out.println(isAnagram(a, b));
    }
}