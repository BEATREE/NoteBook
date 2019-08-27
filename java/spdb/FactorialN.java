/**
    @Title  N的阶乘
 */

import java.util.*;
import java.math.*;

public class FactorialN {

    // use function for
    public static int solution(int N){
        if(N == 1 || N == 0)  return 1;           // 0! == 1
        int result = 1;
        for(int i = 1; i <= N; i++){
            result *= i;
        }
        return result;
    }

    // use BigDecimal to expand the range
    public static BigDecimal bigDecimalSolution(int N){
        if(N == 1 || N == 0){
            return BigDecimal.valueOf(1);
        }
        return BigDecimal.valueOf(N).multiply(bigDecimalSolution(N-1));
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n;
        n = sc.nextInt();
        System.out.println(solution(n));
        System.out.println(bigDecimalSolution(n));

    }
}