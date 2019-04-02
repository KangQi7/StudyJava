package jsoup.test;

import com.alibaba.fastjson.JSONObject;
import jsoup.test.skuQuestion.JdSkuQuestionModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestJSON {
    public static void jsonStrToJava(String jsonStr){
        //1、使用JSONObject
        JdSkuQuestionModel jsonObject=JSONObject.parseObject(jsonStr, JdSkuQuestionModel.class);

        System.out.println(jsonObject.getTotalItem());
    }

    public static String regexPromotion(String sourceCode) {
        String regex = "\\((.+?)}\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sourceCode);
        if (matcher.find()) {
            String promotion =  matcher.group(1);
            promotion+="}";
            return promotion;
        }
        return "";
    }
}
