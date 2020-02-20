package utils;

import java.util.Random;

/**
 * @Author KangQi
 * @Date 2020/2/19 14:33
 * @Version 1.0
 */
public class RandomUtil{
    /**
     * 随机获取大小在min和max之间的整数值
     * @param min
     * @param max
     * @return
     */
    public static int getRandomNumber(int min, int max) {
        Random rand = new Random();
        if (min > max){
            int temp = min;
            min = max;
            max = temp;
        }
        return rand.nextInt(max - min + 1) + min;
    }

    /**
     * 随机获取大小在0 ~ size之间的正整数值
     * @param size 数组大小
     * @return 返回随机index值
     */
    public static int getRandomIndex(int size){
        if (size < 0){
            size = -size;
        }
        return new Random().nextInt(size);
    }
}
