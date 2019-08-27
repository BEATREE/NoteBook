/**
    @Description 1+2/3+3/5+4/7+…数列求和，输出结果；
 */

class CountFraction {

    public static int addTogether(int n){
        if(n == 0){
            return 0;
        }
        int result = 0;
        for(int i = 1; i <= n; i++){
            result += i/(2*i-1);
        }
        return result;
    }

    public static void main(String[] args){
        System.out.println(addTogether(10));
    }
}
