/**
    @Title 计算一个十进制数转换为二进制后1的个数
 */

public class BinaryOneNum {

    public static int solution1(int num){
        int count = 0;
        while( num >= 1){
            if(num % 2 == 1){
                count++;
            }
            num >>= 1;
        }
        return count;
    }

    public static int solution2(int num){
        int count = 0;
        for(; num >= 1; count++){
            num &= (num -1) ;           // 清除最低位的1;例如x = 0b1100, x-1=0b1011, x&(x-1)=0b1000
        }
        return count;
    }

    public static void main(String[] args){
        System.out.println(solution1(102));
        System.out.println(solution2(102));
        System.out.println(solution1(15));
        System.out.println(solution2(15));

    }
}