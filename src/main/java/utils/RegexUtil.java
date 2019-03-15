package utils;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
    public List<String> GetMatchsString(String text, String startStr, String bodyStr, String endStr){
        List<String> list = new ArrayList<String>();
        Pattern p = Pattern.compile("(?<=" + startStr + ")" + bodyStr + "(?=" + endStr + ")");
        Matcher m = p.matcher(text); // 获取 matcher 对象
        while (m.find()) {
            list.add(m.group());
        }
        return list;
    }

    public static String GetMatchString(String text, String startStr, String bodyStr, String endStr){
        StringBuilder regex = new StringBuilder();
        if (startStr != null && !startStr.equals("")){
            regex.append("(?<=" + startStr + ")");
        }
        if (bodyStr != null && !bodyStr.equals("")){
            regex.append(bodyStr);
        }
        else{
            throw new IllegalArgumentException("参数bodyStr必须存在");
        }
        if (endStr != null && !endStr.equals("")){
            regex.append("(?=" + endStr + ")");
        }
//        Pattern p = Pattern.compile("(?<=" + startStr + ")" + bodyStr + "(?=" + endStr + ")");
        Pattern p = Pattern.compile(regex.toString());
        Matcher m = p.matcher(text); // 获取 matcher 对象
        if (m.find()) {
            return m.group();
        }
        return "";
    }
}
