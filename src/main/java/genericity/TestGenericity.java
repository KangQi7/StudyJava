package genericity;

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
        JdSkuQuestionModel model = JdSkuQuestionModel.builder()
                .totalItem(1)
                .resultCode("code")
                .questionList(Arrays.asList(SkuQuestion.builder().answerCount(5L).expire("5e").created("5c").build(),
                        SkuQuestion.builder().answerCount(4L).expire("4e").created("4c").build(),
                        SkuQuestion.builder().answerCount(3L).expire("3e").created("3c").build(),
                        SkuQuestion.builder().answerCount(2L).expire("2e").created("2c").build(),
                        SkuQuestion.builder().answerCount(1L).expire("1e").created("1c").build()))
                .skuInfo(SkuInfo.builder()
                        .brandId(2)
                        .id(2)
                        .brandName("skuInfo1").build())
                .skus(Arrays.asList("5s", "4s", "3s", "2s", "1s"))
                .time(new Date())
                .encrypt_number("111")
                .phone("123456&encrypt")
                .build();

        JdSkuQuestionModel model2 = JdSkuQuestionModel.builder()
                .totalItem(1)
                .resultCode("code2")
                .questionList(Arrays.asList(SkuQuestion.builder().answerCount(5L).expire("25e").created("5c").build(),
                        SkuQuestion.builder().answerCount(4L).expire("24e").created("4c").build(),
                        SkuQuestion.builder().answerCount(3L).expire("23e").created("3c").build(),
                        SkuQuestion.builder().answerCount(2L).expire("22e").created("2c").build(),
                        SkuQuestion.builder().answerCount(1L).expire("21e").created("1c").build()))
                .skuInfo(SkuInfo.builder()
                        .brandId(2)
                        .id(2)
                        .brandName("skuInfo2").build())
                .skus(Arrays.asList("25s", "24s", "23s", "22s", "21s"))
                .time(new Date())
                .encrypt_number("2111")
                .build();

        List<JdSkuQuestionModel> models = Arrays.asList(model, model2);

        decryptObject(models, token, true);

        List<Map> maps = new ArrayList<>();
        HashMap map1 = new HashMap();
        map1.put("brandName","name1");
        map1.put("resultCode",1);

        HashMap map2 = new HashMap();
//        map2.put("brandName","name2");
//        map2.put("resultCode",2);
        maps.add(map1);
        maps.add(map2);

        encryptObject(maps, token);

        decryptObject(maps, token);

        System.out.println(model.getQuestionList());
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

            if (notTypes.contains(((List<?>) obj).get(0).getClass()))
                return;
            for (Object o : (List<?>) obj) {
                decryptObject(o, token, hide);
            }
        } else if (obj instanceof Map){
            Iterator keys = ((Map) obj).keySet().iterator();
            while (keys.hasNext()){
                String key = keys.next().toString();
                if (SENSITIVE_FIELDS.contains(key)){
                    String value = ((Map) obj).get(key).toString();
                    value = decrypt(token, value);
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
                        Object enValue = field.get(obj);//这里可能因字段值为null而报错，所以进行了非空判断
                        if (enValue != null) {
                            String value = decrypt(token, enValue.toString());//解密操作

                            if (hide) {//隐藏明文操作
                                value = hidePlaintext(field.getName(), value);
                                try {
                                    //保存密文的返回
                                    Field _field = obj.getClass().getDeclaredField("encrypt_" + field.getName());
                                    _field.setAccessible(true);
                                    _field.set(obj, enValue);
                                } catch(NoSuchFieldException e){

                                }
                            }

                            field.set(obj, value);
//                            //处理加密字段
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
                        decryptObject(field.get(obj), token, hide);
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
