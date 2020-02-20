package utils;

import java.util.Random;

/**
 * @Author KangQi
 * @Date 2020/2/19 14:32
 * @Version 1.0
 */
public class CommonUtil {
    public static String getRandomNumberStr(int length) {
        StringBuilder str = new StringBuilder();//定义变长字符串
        Random random = new Random();
        //随机生成数字，并添加到字符串
        for (int i = 0; i < length; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }
}
