/**
    @Title 凯撒密码加密解密,就是给你由大写字母组成的字符串,求出原来的字符串,加密
    撒密码是古罗马恺撒大帝用来对军事情报进行加解密的算法，它采用了替换方法对信息中的每一个英文字符循环替换为字母表序列中该字符后面的第三个字符，即，字母表的对应关系如下：
    原文：A B C D E F G H I J K L M N O P Q R S T U V W X Y Z‪‬‪‬‪‬‪‬‪‬‮‬‫‬‭‬‪‬‪‬‪‬‪‬‪‬‮‬‫‬‮‬‪‬‪‬‪‬‪‬‪‬‮‬‫‬‮‬‪‬‪‬‪‬‪‬‪‬‮‬‫‬‫‬‪‬‪‬‪‬‪‬‪‬‮‬‫‬‫‬
    密文：D E F G H I J K L M N O P Q R S T U V W X Y Z A B C‪‬‪‬‪‬‪‬‪‬‮‬‫‬‭‬‪‬‪‬‪‬‪‬‪‬‮‬‫‬‮‬‪‬‪‬‪‬‪‬‪‬‮‬‫‬‮‬‪‬‪‬‪‬‪‬‪‬‮‬‫‬‫‬‪‬‪‬‪‬‪‬‪‬‮‬‫‬‫‬
 */

import java.util.*;

public class CaesarDecode {

    public static StringBuffer solution(String s){
        StringBuffer result = new StringBuffer("");

        if(s.length() == 0 || s.equals("")){
            return result;
        }

        for(int i = 0; i < s.length(); i++){
            result.append((char)(s.charAt(i)+3));
        }
        return result;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(solution(s).toString());
    }
}