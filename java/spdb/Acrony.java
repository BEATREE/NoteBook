/**
    @Description    输入一个字符串，不同的单词用空格隔开，把这些单词的首字母取出并大写输出**，如输入：hello world，输出：HW，不过代码都是要求你实现多行输入的输出的，输入0则停止输入。
 */
import java.util.*;

class Acrony {

    public static void acrony(String s){
        String[] sArray = s.split(" ");         // 分割称为数组
        String result = "";
        for(String st : sArray){
            result += st.charAt(0);
        }
        System.out.println(result.toUpperCase());
    }

    public static void main(String[] args){
        String a = "";
        Scanner sc = new Scanner(System.in);
        while(!a.equals("0")){
            a = sc.nextLine();
            acrony(a);
        }
    }
}