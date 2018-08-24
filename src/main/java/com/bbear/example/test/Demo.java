package com.bbear.example.test;

/**
 * @author junxiongchen
 * @date 2018/08/20
 */
public class Demo {
    public static int num = 397;
    public static void main(String[] args) {
        Demo demo = new Demo();
        char[] chars = {'a','b','A','c','D'};
        demo.sortLetters(chars);

    }

    private static void solution(int num) {

        num = (num%10)* 100 + (num/10%10)*10 + num/100 ;
        System.out.println(num);
    }
    public void sortLetters(char[] chars) {
// write your code here
        int i=0,j=chars.length-1;
        while(i<=j)
        {
            while((i<=j)&&Character.isLowerCase(chars[i]))
            {
                i++;
            }
            while((i<=j)&&Character.isUpperCase(chars[j]))
            {
                j--;
            }
            if(i<=j)
            {
                char tmp;
                tmp=chars[i];
                chars[i]=chars[j];
                chars[j]=tmp;
            }
        }
        System.out.println(chars);
        return;
    }
}
