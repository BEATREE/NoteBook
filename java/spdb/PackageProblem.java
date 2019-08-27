/**
    @Type 动态规划
    @Title 一个背包有一定的承重 W，有 N 件物品，每一件都有自己的价值，记录在数组 v 中，也都有自己的重量，记录在数组         w 中，每件物品只能选择放于不放，要求在不超过背包承重的前提下，选出物品的总价值最大
 */

public class PackageProblem {

    /**
        @Shortages 有个严重的问题就是，直接采用自顶向下的递归算法会导致要不止一次的解决公共子问题，因此效率是相当低下的。
        @param values           物品价值的数组
        @param weight           物品重量的数组
        @param index            当前待选择的物品的索引
        @param capacity         当前背包容量
     */
    public static void solution1(int[] values, int[] weight,int index, int capacity){
        //基准条件：如果索引无效或者容量不足，直接返回当前价值0
        if (index < 0 || capacity <= 0)
            return 0;
        
        // 不放第 index 个物品的价值
        int res = solution(values, weight, index-1, capacity);
        // 放第 index 个物品的价值
        if(weight[index] < capacity){
            res = Math.max(res, values[index] + solution(values, weight, index - 1, capacity - weight[index]))
        }

        return res;
    }
    public static int knapSack(int[] weight, int[] values, int C) {
        int size = weight.length;
        return solution1(weight, values, size - 1, C);
        // 从后最后往最前去算
    }

    /**
        @param values           物品价值的数组
        @param weight           物品重量的数组
        @param index            当前待选择的物品的索引
        @param capacity         当前背包容量
     */
    private static int[][] memo;                // 用于记录，其中行代表第n个商品，列代表容量
    public static void solution2(int[] values, int[] weight,int index, int capacity){
        if(index < 0 || capacity <= 0){
            return 0;
        }
        // 如果此问题已经求解过，直接返回
        if(memo[index][capacity] != 0){
            return memo[index][capacity];
        }

        // 不放第 index 个物品的价值
        int res = solution2(values, weight, index-1, capacity);
        if(weight[index] < capacity){
            res = Math.max(res, values[index] + solution2(values, weight, index-1, capacity-weight[index]));
        }
        // 添加子问题的解便于下次直接调用
        memo[index][capacity] = res;
        return res;
    }
    public static int knapSack2(int[] weight, int[] values, int C) {
        int size = weight.length;
        memo = new int[size][C + 1];
        return solution2(weight, values, size - 1, C);
    }

    public static void main(String[] args){

    }
}