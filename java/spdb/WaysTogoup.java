
/*
 *  @Description: 求N阶楼梯共有多少种上楼方式，每次只能上1个或2个台阶。
    @Type   动态规划
 */
import java.util.*;

class WaysTogoup{

    /*
        方法一：暴力法
        算法
        在暴力法中，我们将会把所有可能爬的阶数进行组合，也就是 1 和 2 。而在每一步中我们都会继续调用 climbStairs
        这个函数模拟爬 1 阶和 2 阶的情形，并返回两个函数的返回值之和。

        climbStairs(i,n)=climbStairs(i+1,n)+climbStairs(i+2,n) 

        其中 i 定义了当前阶数，而 n 定义了目标阶数。

        时间复杂度：O(2^n)。树形递归的大小为 2^n 
        空间复杂度：O(n)。递归树的深度可以达到 n 。 
    */
    public static int climbStairs1(int i, int n){
        if( i > n ){    // 例子：只有 1 阶，但是步伐为 2； 所以只计算走一步的情况即可
            return 0;   // climbStairs(1,1)=climbStairs(1,1)+climbStairs(2,1) 
        }
        if( i== n){     // 例子：只有 1 阶，步伐为 1； 所以只走一步即可
            return 1;
        }
        return climbStairs1(i+1, n) + climbStairs1(i+2, n);
    }

    /**
        方法二： 记忆法递归
        算法：
            在上一种方法中，我们计算每一步的结果时出现了冗余。另一种思路是，我们可以把每一步的结果存储在 memo 数组之中，每当函数再次被调用，我们就直接从 memo 数组返回结果。
     */
    public static int climbStairs2(int i, int n, int[] memo){
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        if (memo[i] > 0) {
            return memo[i];
        }
        memo[i] = climbStairs2(i + 1, n, memo) + climbStairs2(i + 2, n, memo);
        return memo[i];
    }

    /**
       方法三：动态规划
       这个问题可以被分解为一些包含最优子结构的子问题，即它的最优解可以从其子问题的最优解来有效地构建，我们可以使用动态规划来解决这一问题。
        到达第 i 阶的方法有两种 ：
            1. 第 i-1 阶爬行一阶
            2. 第 i-2 阶爬行两阶
        所以到达第 i 阶的总数就是第 i-1 阶 和 第 i-2 阶之和
        令 dp[i] 表示能到达第 i 阶的方法总数：  dp[i] = dp[i-1] + dp[i-2]

        时间复杂度： O(n)，单循环到 n
        空间复杂度： O(n)，dp 数组用了 n 的空间。 
    */
    public static int climbStairs3(int n){
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n+1];        // 初始化保存阶梯方式的数组
        dp[1] = 1;
        dp[2] = 2;

        for(int i = 3; i <= n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }

    /**
        方法四：斐波那契数
        我们使用 dp 数组，其中 dp[i]=dp[i−1]+dp[i−2]。可以很容易通过分析得出 dp[i] 其实就是第 i 个斐波那契数。
    */
    public static int climbStairs4(int n){
        if(n == 1){
            return 1;
        }
        int first = 1;
        int second = 2;          // 定义第一阶和第二阶的阶数
        for(int i = 3; i <= n; i++){
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }

    public static void main(String[] args){
        int n;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        System.out.println(climbStairs1(0, n));

        int memo[] = new int[n + 1];                    //记忆化递归使用的数组
        System.out.println(climbStairs2(0, n, memo));
        System.out.println(climbStairs3(n));
        System.out.println(climbStairs4(n));

        // System.out.println(Arrays.toString(args));
    }
}