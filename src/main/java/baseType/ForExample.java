package baseType;

/**
 * @Author KangQi
 * @Date 2020/1/21 10:33
 * @Version 1.0
 */
public class ForExample {
    static boolean foo(char c)
    {
        System.out.print(c);
        return true;
    }
    public static void main(String[] args) {
        int i =0;
        for(foo('A');foo('B')&&(i<2);foo('C'))
        {
            i++;
            foo('D');
        }
        //输出：ABDCBDCB
        //原因是for的执行顺序
    }
}
