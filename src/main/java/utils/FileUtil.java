package utils;

import java.io.*;

public class FileUtil {
    public static String ReadFile(String filePath){
        StringBuilder stringBuilder = new StringBuilder();

        try {
            /* 读入TXT文件 */
            File filename = new File(filePath); // 要读取以上路径的input。txt文件
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename)); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            String line;
            line = br.readLine();
            while (line != null) {
                stringBuilder.append(line);
                line = br.readLine(); // 一次读入一行数据
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
