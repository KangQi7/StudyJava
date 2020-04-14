package genericity;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jsoup.skuQuestion.JdSkuQuestionModel;
import jsoup.skuQuestion.SkuInfo;
import jsoup.skuQuestion.SkuQuestion;
import org.junit.Test;

import javax.security.auth.Subject;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Author kangqi
 * @Date 2019/11/29 9:39
 * @Version 1.0
 */
public class TestGenericity {

    /**
     * 敏感字段
     */
    private static final List<String> SENSITIVE_FIELDS = Arrays.asList("expire",
            "encrypt_number", "resultCode", "questionList", "customerName", "brandName"
            ,"phone"
    );
    /**
     * 不处理类型
     */
    private static List<Type> notTypes = Arrays.asList(String.class, Integer.class, Long.class, Date.class,
            Map.class, Dictionary.class, BigDecimal.class, Double.class, Float.class, Boolean.class,//HashMap.class
            Byte.class, Short.class,
            int.class, long.class, float.class, double.class, boolean.class, byte.class, char.class, short.class);

    /**
     * 含有敏感字段的对象（为解密以json字符串存储的加密字段进行判断）
     */
    private static final List<String> SENSITIVE_OBJECT = Arrays.asList("invoiceEasyInfo", "consigneeInfo", "vatInfo");

    /**
     * 不敏感对象名（用来减少回调次数）
     */
    private static final List<String> NOT_SENSITIVE_OBJECT = Arrays.asList("itemInfoList", "goodsInfo", "logisticsRealInfoVo",
            "EMPTY_PAGE_RESULT", "pageNo", "pageSize", "totalItem");

    private static String decrypt(String token, String text) {
        text = text.replaceAll("&" + token, "");
        return text;
    }

    private static String encrypt(String token, String text) {
        text += "&" + token;
        return text;
    }

    @Test
    public void test() {
        String token = "encrypt";
//        JdSkuQuestionModel model = JdSkuQuestionModel.builder()
//                .totalItem(1)
//                .resultCode("code")
//                .questionList(Arrays.asList(SkuQuestion.builder().answerCount(5L).expire("5e").created("5c").build(),
//                        SkuQuestion.builder().answerCount(4L).expire("4e").created("4c").build(),
//                        SkuQuestion.builder().answerCount(3L).expire("3e").created("3c").build(),
//                        SkuQuestion.builder().answerCount(2L).expire("2e").created("2c").build(),
//                        SkuQuestion.builder().answerCount(1L).expire("1e").created("1c").build()))
//                .skuInfo(SkuInfo.builder()
//                        .brandId(2)
//                        .id(2)
//                        .brandName("skuInfo1").build())
//                .skus(Arrays.asList("5s", "4s", "3s", "2s", "1s"))
//                .time(new Date())
//                .encrypt_number("111")
//                .phone("123456&encrypt")
//                .build();
//
//        JdSkuQuestionModel model2 = JdSkuQuestionModel.builder()
//                .totalItem(1)
//                .resultCode("code2")
//                .questionList(Arrays.asList(SkuQuestion.builder().answerCount(5L).expire("25e").created("5c").build(),
//                        SkuQuestion.builder().answerCount(4L).expire("24e").created("4c").build(),
//                        SkuQuestion.builder().answerCount(3L).expire("23e").created("3c").build(),
//                        SkuQuestion.builder().answerCount(2L).expire("22e").created("2c").build(),
//                        SkuQuestion.builder().answerCount(1L).expire("21e").created("1c").build()))
//                .skuInfo(SkuInfo.builder()
//                        .brandId(2)
//                        .id(2)
//                        .brandName("skuInfo2").build())
//                .skus(Arrays.asList("25s", "24s", "23s", "22s", "21s"))
//                .time(new Date())
//                .encrypt_number("2111")
//                .build();
//
//        List<JdSkuQuestionModel> models = Arrays.asList(model, model2);
//
//        decryptObject(models, token, true);

        List<Map> maps = new ArrayList<>();
        HashMap map1 = new HashMap();
        map1.put("brandName","name1");
        map1.put("resultCode",1);
        map1.put("encrypt_brandName","");

        HashMap map2 = new HashMap();
        map2.put("brandName","name2");
        map2.put("resultCode",2);
        maps.add(map1);
        maps.add(map2);

//        encryptObject(maps, token);

        decryptObject(maps, token, true);
    }

    /**
     * 迭代解密json中包含的敏感字段
     *
     * @param json  需要处理的json对象
     * @param token 店铺的token
     */
    public static void decryptJson(JSONObject json, String token) {
        Set<Map.Entry<String, Object>> entries = json.entrySet();
        // 遍历entry
        for (Map.Entry entry : entries) {
            // 如果为json对象继续迭代
            if (entry.getValue() instanceof JSONObject) {
                decryptJson((JSONObject) entry.getValue(), token);
                // 如果为json数组遍历其中的元素继续迭代
            } else if (entry.getValue() instanceof JSONArray) {
                JSONArray array = (JSONArray) entry.getValue();
                for (int i = 0; i < array.size(); i++) {
                    decryptJson(array.getJSONObject(i), token);
                }
                // 如果为字符串，判断是否为敏感字段，调用解密方法
            } else if (entry.getValue() instanceof String) {
                String key = (String) entry.getKey();
                if (SENSITIVE_FIELDS.contains(key)) {
                    // 将解密后的明文写回json
                    json.put(key, decrypt((String) entry.getValue(), token));
                }
            }
        }
    }

    public static <T> void decryptObject(T obj, String token) {
        decryptObject(obj, token, false);
    }
    /**
     * 解密object中的敏感字段
     *
     * @param obj
     * @param token
     * @param <T>
     */
    public static <T> void decryptObject(T obj, String token, boolean hide) {
        if (obj == null) {
            return;
        }
        //如果是数组类型，循环执行
        if (obj instanceof List<?>) {
            //如果数组无元素，直接返回
            if (((List<?>) obj).size() == 0)
                return;
            //且其类型不为基本类型、String或Integer等无需处理字段的类型
            if (notTypes.contains(((List<?>) obj).get(0).getClass()))
                return;
            for (Object o : (List<?>) obj) {
                decryptObject(o, token, hide);
            }
        } else if (obj instanceof JSONObject) {
            decryptJson((JSONObject) obj, token);
        } else if (obj instanceof Map) {
            Iterator keys = ((Map) obj).keySet().iterator();
            while (keys.hasNext()) {
                String key = keys.next().toString();
                if (SENSITIVE_FIELDS.contains(key)) {
                    String enValue = ((Map) obj).get(key).toString();
                    String value = decrypt(enValue, token);
                    if (hide) {
                        if(((Map)obj).containsKey("encrypt_" + key)){
                            ((Map) obj).put("encrypt_" + key, enValue);//添加密文字段保存密文
                        }
                        value = hidePlaintext(key, value);
                    }
                    ((Map) obj).put(key, value);
                }
            }
        } else {
            //1.判断obj类型，如果是无需处理的类型则直接返回
            if (notTypes.contains(obj.getClass())) {
                return;
            }

            //2.获取类型各字段进行操作
            Field[] fields = obj.getClass().getDeclaredFields();

            for (Field field : fields) {
                //String类型，判断是否是加密字段，是则解密并赋值
                if (field.getGenericType().equals(String.class)) {
                    if (!SENSITIVE_FIELDS.contains(field.getName())) {
                        continue;//不是则继续循环字段
                    }

                    try {
                        field.setAccessible(true);
                        Object enValue = field.get(obj);
                        //这里可能因字段值为null而报错，所以进行了非空判断
                        if (enValue != null) {
                            String value = decrypt(enValue.toString(), token);//解密操作

                            if (hide) {//隐藏明文操作
                                value = hidePlaintext(field.getName(), value);
                                try {
                                    //保存密文的返回
                                    Field _field = obj.getClass().getDeclaredField("encrypt_" + field.getName());
                                    _field.setAccessible(true);
                                    _field.set(obj, enValue);
                                } catch (NoSuchFieldException e) {

                                }
                            }

                            field.set(obj, value);
//                            //处理加密字段，暂不处理此情况，目前未涉及这类字段
//                            if (field.getName().startsWith("encrypt_")) {
//                                Field _field = obj.getClass().getDeclaredField(field.getName().replace("encrypt_", ""));
//                                _field.setAccessible(true);
//                                _field.set(obj, Long.parseLong(value));
//                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else if (!notTypes.contains(field.getType())) {
                    try {
                        field.setAccessible(true);
                        Object enObject = field.get(obj);
                        //这里可能因字段值为null而报错，所以进行了非空判断
                        if (enObject != null) {
                            //判断是否是含敏感信息的对象
                            if (SENSITIVE_OBJECT.contains(field.getName()) && enObject instanceof String) {
                                JSONObject enJsonObject = JSONObject.parseObject(enObject.toString());
                                if (enJsonObject != null) {
                                    decryptJson(enJsonObject, token);
                                    field.set(obj, enJsonObject.toJSONString());
                                }
                            } else if (!NOT_SENSITIVE_OBJECT.contains(field.getName())) {//排除非敏感对象字段
                                decryptObject(field.get(obj), token, hide);
                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static String hidePlaintext(String field, String plaintext) {
        String str = plaintext;
        str = str.substring(0, 1) + "***" + str.substring(1);
        return str;
    }

    /**
     * 加密object中的敏感字段
     *
     * @param obj
     * @param token
     * @param <T>
     */
    public static <T> void encryptObject(T obj, String token) {
        if (obj == null) {
            return;
        }
        //如果是数组类型，循环执行
        if (obj instanceof List<?>) {
            //如果数组无元素，直接返回
            if (((List<?>) obj).size() == 0)
                return;

            if (notTypes.contains(((List<?>) obj).get(0).getClass()))
                return;
            for (Object o : (List<?>) obj) {
                encryptObject(o, token);
            }
        } else if (obj instanceof Map){
            Iterator keys = ((Map) obj).keySet().iterator();
            while (keys.hasNext()){
                String key = keys.next().toString();
                if (SENSITIVE_FIELDS.contains(key)){
                    String value = ((Map) obj).get(key).toString();
                    value = encrypt(token, value);
                    ((Map) obj).put(key, value);
                }
            }
        } else {
            //1.判断obj类型，如果是无需处理的类型则直接返回
            if (notTypes.contains(obj.getClass())) {
                return;
            }

            //2.获取类型各字段进行操作
            Field[] fields = obj.getClass().getDeclaredFields();

            for (Field field : fields) {
                //String类型，判断是否是加密字段，是则解密并赋值
                if (field.getGenericType().equals(String.class) ||
                        field.getGenericType().equals(Long.class) ||
                        field.getGenericType().equals(Integer.class)) {
                    if (!SENSITIVE_FIELDS.contains(field.getName())) {
                        continue;//不是则继续循环字段
                    }

                    try {
                        field.setAccessible(true);
                        Object deValue = field.get(obj);//这里可能因字段值为null而报错，所以进行了非空判断
                        if (deValue != null) {
                            String value = encrypt(token, deValue.toString());//加密操作
                            //如果字段类型是整型、长整型，将密文赋值给其对应的加密字段
                            if (field.getGenericType().equals(Long.class) ||
                                    field.getGenericType().equals(Integer.class)) {
                                //处理加密字段，判断加密字段存在
                                Field _field = obj.getClass().getDeclaredField("encrypt_" + field.getName());
                                if (_field != null) {
                                    _field.setAccessible(true);
                                    _field.set(obj, value);
                                }
                                //赋值敏感字段为null
                                field.set(obj, null);
                            } else {
                                field.set(obj, value);
                            }
                        }
                    } catch (IllegalAccessException | NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                } else if (!notTypes.contains(field.getType())) {
                    try {
                        field.setAccessible(true);
                        encryptObject(field.get(obj), token);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
//        }
    }
}
