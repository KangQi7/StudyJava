package image;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.junit.Test;
import org.springframework.util.Base64Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLDecoder;

/**
 * @Author KangQi
 * @Date 2020/4/13 20:11
 * @Version 1.0
 */
public class ThumbnailatorTest {

    private static String filePath = "G:\\学习代码\\Study Java\\src\\main\\java\\image\\file\\base64.txt";

    /**
     * 测试生成带水印的图片文件，并将其转为base64存入文件
     */
    @Test
    public void writerImageBase64StrToFile() {
        String imagePath = "G:\\学习代码\\Study Java\\src\\main\\java\\image\\file\\xiaoxin.jpg";
        String watermarkPath = "G:\\学习代码\\Study Java\\src\\main\\java\\image\\file\\remai1.png";
        String targetPath = "G:\\学习代码\\Study Java\\src\\main\\java\\image\\file\\image-with-watermark.jpg";

        try {
            BufferedImage image = ImageIO.read(new FileInputStream(imagePath));
            BufferedImage watermark = ImageIO.read(new FileInputStream(watermarkPath));

            // 缩放水印
            BufferedImage _watermark = Thumbnails.of(watermark)
                    .size(image.getWidth(), image.getHeight())
                    .asBufferedImage();

            // 输出图片
            Thumbnails.of(image)
                    .size(image.getWidth(), image.getHeight())
                    .watermark(_watermark, 0.3f)
                    .outputQuality(0.8f)
                    .toFile(new File(targetPath));

            // 打水印，输出BufferedImage
            BufferedImage _image = Thumbnails.of(image)
                    .size(image.getWidth(), image.getHeight())
                    .watermark(_watermark, 0.3f)
                    .outputQuality(0.8f)
                    .asBufferedImage();

            // 写入文件
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ImageIO.write(_image, "jpg", stream);
            String base64 = Base64Utils.encodeToString(stream.toByteArray());
            writerFile(filePath, base64);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试读取base64字符串转为图片输出
     */
    @Test
    public void readImageBase64ToImageFile(){
        String base64Str = readFile(filePath);
        try {
            byte[] bytes = Base64Utils.decodeFromString(base64Str.trim());
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(bytes));
            String currentFileName = "G:\\学习代码\\Study Java\\src\\main\\java\\image\\file\\image-watermark.jpg";
            File file = new File (currentFileName);
            String format = "jpg";
            // 将图片保存为jpeg格式的文件file。也可以保存为jpg格式
            ImageIO.write(bufferedImage,format,file);
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(String filePath) {
        try {
            InputStream in = new FileInputStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            return stringBuilder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writerFile(String filePath, String content) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(filePath);
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
