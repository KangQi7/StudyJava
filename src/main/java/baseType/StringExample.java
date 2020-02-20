package baseType;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.junit.Test;
import org.omg.CORBA.portable.ValueOutputStream;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

/**
 * @Author KangQi
 * @Date 2020/1/21 9:34
 * @Version 1.0
 */
public class StringExample {
    String str = new String("tarena");
    char[] ch = {'a', 'b', 'c'};

    @Test
    public void testFinal() {
        StringExample ex = new StringExample();
        ex.change(ex.str, ex.ch);
        System.out.print(ex.str + " and ");
        System.out.print(ex.ch);
        Long a = 127L;
        Long b = 128L;
    }

    @Test
    public void testEquals() {
        String s1 = "abc";
        String s2 = "bac";
        s2 = "abc";
        if (s1.equals(s2)) {
            System.out.println("s1 equals s2");
        } else {
            System.out.println("s1 not equals s2");
        }
    }

    @Test
    public void testReplace() {
        String str = "hello world!";
        str = str.replace('l', 'd');
        System.out.println(str);
        str = str.replace("d", "l");
        System.out.println(str);
        str = str.replaceAll("l", "d");
        System.out.println(str);
        str = str.replaceAll("he.{3}", "hello");
        System.out.println(str);
        str = str.replaceFirst("w.+", "!world!");
        System.out.println(str);
        str = str.replaceFirst("!", "?");
        System.out.println(str);
    }
    @Test
    public void testSplit() {
        String str = "a;b;c;;d; ;;e;f";
        String[] list = str.split(";");
        System.out.println(list);
        list = str.split(";", 3);
        System.out.println(list);
        //guava
        List list1 = Splitter.on(';')
                .trimResults()
                .omitEmptyStrings()
                .splitToList(str);
        System.out.println(JSON.toJSONString(list1));
    }

    @Test
    public void testJoin(){
        String s = "";
        List<String> list = Arrays.asList("a","b");
        s = String.join(",",list);
        System.out.println(s);
        //guava
        Joiner joiner = Joiner.on(",").skipNulls();
        String result = joiner.join("hello",null," world");
        System.out.println(result);

        System.out.println(joiner.join(list));

        System.out.println(Joiner.on(";").skipNulls().join(list));
    }

    public void change(String str, char ch[]) {
        //引用类型变量，传递的是地址，属于引用传递。
        str = "test ok";
        ch[0] = 'g';
    }
}
