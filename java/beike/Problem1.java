import java.util.*;
class Problem1 {
    /**
        幸运字符串 t，长度为 n， 最短字符串s；
        使得字符串 t 在 s 中出现 k 次

        input: 正整数 n k 使用空格隔开
        第二行: 长度为 n 的字符串 t

     */

    public static String solution(int n, int k, String t){
        String s = t;
        int count = 0;
        int pointer = n-1;          // 用于指示
        char[] ca = s.toCharArray();
        for(int i = 0; count < k-1; i++){
            if(s.charAt(pointer)==ca[i%n]){
                if((i%n!=0) && ca[i%n]==ca[(i%n)-1]){
                    
                    s+=ca[i%n];           // 追加到字符串s最后
                    pointer++;
                }
            }else{
                s+=ca[i%n];           // 追加到字符串s最后
                pointer++;
            }
            if((i+1) % n == 0){
                count++;
            }
        }

        return s;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n, k;           // 长度为 n, 出现 k 次

        n = sc.nextInt();
        k = sc.nextInt();
        String t ;
        sc.nextLine();
        t = sc.nextLine();

        System.out.println(solution(n, k, t));
    }
}