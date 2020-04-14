import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

public class HelloWorld {

    public static void main(String[] args) {
        String imageUrl = "http://omsproductionimg.yangkeduo.com/images/unknown/0/L6Bze4giRtmOG4K8rK2wenQ5VMkjx06a.jpg";
        try {
            BufferedImage image = ImageIO.read(new URL(imageUrl));
            System.out.println(image.getWidth());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Long test(Long total){
        total = 10L;
        return 0L;
    }

    /**
     * 在左闭右闭的区间内获取一个随机int值
     * @param min
     * @param max
     * @return
     */
    private static Integer createRandomNum(Integer min, Integer max){
        if (max < min){
            Integer temp = min;
            min = max;
            max = temp;
        }
        Random random = new Random(System.currentTimeMillis());
        return random.nextInt(max - min + 1) + min;
    }
}