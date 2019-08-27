import java.util.*;
class Problem2 {
    /**
        S 元钱 1<= <=10^9
        n 种器材
        第 i 种费用是 c[i]
        
     */

    public static int solution(int n, int s, int[] c){
        Arrays.sort(c);
        int i = 0;
        while(i<n){
            if(s-c[i] < 0){
                break;
            }
            s -= c[i];
            i++;
        }
        return i;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n, s;           // 长度为 n, 出现 k 次
        n = sc.nextInt();
        s = sc.nextInt();
        int[] c = new int[n];
        for(int i=0; i < n; i++){
            c[i] = sc.nextInt();
        }

        System.out.println(solution(n, s, c));
    }
}