/**
    @Title 如果字符串有连续个M(M>=4)按字典递增顺序相邻的大写字母，则缩写为“首字母-结束字母”
    @Input      ABCD    |   XYABCDEFA
    @Output     A-D     |   XYA-FA
 */

public class StringAbbreviation {

    public static void solution1(String s){
        if(s.equals("") || s.length() == 0){
            System.out.println("Please input a correct string");
            return;
        }
        StringBuffer sb = new StringBuffer(s);                  // StringBuffer is thread-safe and can be modified.
        int begin = 0, end = 0;
        int count;
        for(int i = 0; i < s.length()-1; i++){
            if(s.charAt(i+1) - s.charAt(i) == 1){               // these chars are next to each other
                if(i == 0 || s.charAt(i) - s.charAt(i-1) != 1){ // if it is the first one or it's a new begin
                    begin = i;
                }
                end = i+1;
            }
        }
        // System.out.println(begin + "-" + end);
        if(end - begin >= 3){                                   // ABCD's index is from 0 to 3, but it's 4 character
            sb.delete(begin+1, end);
            sb.insert(begin+1, "-");
        }
        System.out.println(sb);
    }

    public static void main(String[] args){
        String s = "ABCD";
        String s2 = "XYABCDEFA";
        solution1("");
        solution1(s);
        solution1(s2);
    }
}