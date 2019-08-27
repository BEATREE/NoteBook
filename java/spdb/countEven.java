/*
 *  @Description: 输入一个数字要求输出该数字各个位上偶数的和，如输入5584，输出12  
 */

class countEven{

    public static int getCountEven(int number){
        int remainder, result = 0;
        while( number > 0 ){
            remainder = number % 10;            // 取得末位数
            if( remainder % 2 == 0){            // 如果是偶数
                result += remainder;
            }
            number /= 10;
        }
        return result;
    }

    public static void main(String[] args){
        System.out.println(getCountEven(5584));
        System.out.println(getCountEven(2));
        System.out.println(getCountEven(0));
        System.out.println(getCountEven(11111111));

    }
}