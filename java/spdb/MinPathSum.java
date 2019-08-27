/**
    @Title 给定一个矩阵 m ， 从左上角每次只能向下或向右走，最后到达右下角的位置，
            路径上所有的数字累加起来就是路径和。
            返回所有路径中的最小路径和。
    @Type 动态规划
    @Example

        1 3 5 9         最小路径和为1，3，1，0，6，1，0；所以返回12
        8 1 3 4
        5 0 6 1
        8 8 4 0
    @Thought    from the bottom to the top. sum[i][j] = m[i][j] + min(sum[i-1][j], sum[i][j-1])
 */

public class MinPathSum {

    public static int minPath(int[][] arr){
        int x = arr.length;
        int y = arr[0].length;
        int[][] sum = new int[x][y];
        sum[0][0] = arr[0][0];
        
        for(int i = 1; i < y; i++){
            sum[0][i] = sum[0][i-1] + arr[0][i];
        }
        for(int i = 1; i < x; i++){
            sum[i][0] = sum[i-1][0] + arr[i][0];
        }

        for(int i = 1; i < x; i++){
            for(int j = 1; j < y; j++){
                if(sum[i-1][j] < sum[i][j-1]){
                    sum[i][j] = sum[i-1][j] + arr[i][j];
                }else{
                    sum[i][j] = sum[i][j-1] + arr[i][j];
                }
            }
        }

        return sum[x-1][y-1];
    }

    public static int minPath2(int[][] arr){
        int[] dp = new int[arr[0].length];
        dp[0] = arr[0][0];

        for(int j=1;j<arr[0].length;j++)
        {
            dp[j]=dp[j-1]+arr[0][j];
            //求出第一行的dp
        }

        for (int i = 1; i < arr.length; i++){
            dp[0] = arr[i][0] + dp[0];
            // dp[0] 代表每一行最左边的的dp
            // 后一行的 dp 覆盖前一行的 dp
            for(int j = 1; j < arr[0].length; j++){
                dp[j] = Math.min(dp[j-1] + arr[i][j], dp[j] + arr[i][j]);
            }
        }

        return dp[arr[0].length-1];
    }

    public static void main(String[] args){
        int[][] arr = {{1, 3, 5, 9},
                       {8, 1, 3, 4},
                       {5, 0, 6, 1},
                       {8, 8, 4, 0}
                      };
        System.out.println(minPath(arr));
        System.out.println(minPath2(arr));

    }
}