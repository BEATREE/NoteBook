/**
    @Description 给年月日，判断是第几天

    思路：1 3 5 7 8 10 12 月 均是 31 天；只有 2 月不确定； 所以可以先判断是否是闰年，再计算天数即可
 */

class WhichDay {

    protected static int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static int getDay(int year, int month, int day){
        int count = 0;
        for(int i = 0; i < month-1; i++){
            count += days[i];                               // 加上之前的月份
        }

        if(month > 2 && isLeap(year)){                      // 如果是闰年并且月份大于2
            count += 1;                                     // 闰年二月多出来一天
        }
        count += day;

        return count;
    }

    public static boolean isLeap(int year){                 // 判断是不是闰年
        if((year%4 == 0 && year % 100 != 0) || year % 400 == 0){
            return true;
        }
        return false;
    }

    public static void main(String[] args){
        System.out.println(getDay(2019, 1, 1));
        System.out.println(getDay(2019, 2, 1));
        System.out.println(getDay(2008, 3, 1));
        
    }
}

