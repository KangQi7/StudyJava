package genericity;

import jsoup.test.skuQuestion.JdSkuQuestionModel;
import jsoup.test.skuQuestion.SkuInfo;
import jsoup.test.skuQuestion.SkuQuestion;
import org.junit.Test;

import javax.lang.model.type.ReferenceType;
import java.lang.reflect.*;
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
            "encrypt_number", "resultCode", "questionList", "customerName","brandName"
    );
    //
    private static List<Type> notTypes = Arrays.asList(String.class,Integer.class, Long.class, Date.class,
            Map.class, HashMap.class,Dictionary.class,
            int.class, long.class, float.class, double.class, boolean.class, byte.class,char.class,short.class);

    private static String decrypt(String token, String text){
        text = text.replaceAll("&" + token, "");
        return text;
    }

    private static String encrypt(String token,String text){
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

        List<JdSkuQuestionModel> models = Arrays.asList(model,model2);

        encryptObject(models, token);

        decryptObject(models, token);

        System.out.println(model.getQuestionList());
    }

    public static <T> void decryptObj(T obj, String token){
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
//                decryptObject(o , token);
            }
        }else {
//            decryptObject(obj, token);
        }
    }

//    public static <T> void decryptObject(T obj, String token) {
//        if(obj == null) {
//            return;
//        }
//        //如果是数组类型，循环执行
//        if (obj instanceof List<?>) {
//            for (Object o : (List<?>) obj) {
//                decryptObject(o , token);
//            }
//        } else {
//            //1.判断obj类型，如果是无需处理的类型则直接返回
//            if (types.contains(obj.getClass())){
//                return;
//            }
//
//            //2.获取类型各字段进行操作
//            Field[] fields = obj.getClass().getDeclaredFields();
//
//            for (Field field : fields) {
//                //如果是数组，获取其类型并循环递归
//                if (field.getGenericType() instanceof ParameterizedType) {
//                    ParameterizedType pt = (ParameterizedType) field.getGenericType();
//                    //数组，且其类型不为基本类型、String或Integer等无需处理字段的类型
//                    if (pt.getRawType().equals(List.class) && !types.contains(pt.getActualTypeArguments()[0])) {
//                        try {
//                            field.setAccessible(true);
//                            decryptObject(field.get(obj), token);
//                        } catch (IllegalAccessException e) {
//                            e.printStackTrace();
//                        }
//                    } else if(pt.getRawType().equals(List.class)
//                            && pt.getActualTypeArguments()[0].equals(String.class)
//                            && SENSITIVE_FIELDS.contains(field.getName())){
//                        try {
//                            field.setAccessible(true);
//                            List objList = (List)field.get(obj);
//                            List newList = new ArrayList();
//                            for (Object item:
//                                    objList) {
//                                item += "1";
//                                newList.add(item);
//                            }
//                            field.set(obj, newList);
//                        } catch (IllegalAccessException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                } else {
//                    //String类型，判断是否是加密字段，是则解密并赋值
//                    if (field.getGenericType().equals(String.class)) {
//                        if (!SENSITIVE_FIELDS.contains(field.getName())) {
//                            continue;//不是则继续循环字段
//                        }
//
//                        try {
//                            field.setAccessible(true);
//                            String value = field.get(obj).toString() + "1";
//                            field.set(obj, value);
//                            //处理加密字段
//                            if (field.getName().startsWith("encrypt_")) {
//                                Field _field = obj.getClass().getDeclaredField(field.getName().replace("encrypt_", ""));
//                                _field.setAccessible(true);
//                                _field.set(obj, Long.parseLong(value));
//                            }
//                        } catch (IllegalAccessException | NoSuchFieldException e) {
//                            e.printStackTrace();
//                        }
//                    } else if(!types.contains(field.getType())) {
//                        try {
//                            field.setAccessible(true);
//                            decryptObject(field.get(obj), token);
//                        } catch (IllegalAccessException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }
//    }




    /**
     * 解密object中的敏感字段
     *
     * @param obj
     * @param token
     * @param <T>
     */
    public static <T> void decryptObject(T obj, String token) {
        if (obj == null) {
            return;
        }
        //如果是数组类型，循环执行
        if (obj instanceof List<?>) {
            if(notTypes.contains(((List<?>) obj).get(0).getClass()))
                return;
            for (Object o : (List<?>) obj) {
                decryptObject(o, token);
            }
        } else {
            //1.判断obj类型，如果是无需处理的类型则直接返回
            if (notTypes.contains(obj.getClass())) {
                return;
            }

            //2.获取类型各字段进行操作
            Field[] fields = obj.getClass().getDeclaredFields();

            for (Field field : fields) {
//                //如果是数组，获取其类型并循环递归
//                if (field.getGenericType() instanceof ParameterizedType) {
//                    ParameterizedType pt = (ParameterizedType) field.getGenericType();
//                    //数组，且其类型不为基本类型、String或Integer等无需处理字段的类型
//                    if (pt.getRawType().equals(List.class) && !notTypes.contains(pt.getActualTypeArguments()[0])) {
//                        try {
//                            field.setAccessible(true);
//                            decryptObject(field.get(obj), token);
//                        } catch (IllegalAccessException e) {
//                            e.printStackTrace();
//                        }
//                    } else if (pt.getRawType().equals(List.class)
//                            && pt.getActualTypeArguments()[0].equals(String.class)
//                            && SENSITIVE_FIELDS.contains(field.getName())) {
//                        try {
//                            field.setAccessible(true);
//                            List objList = (List) field.get(obj);
//                            List newList = new ArrayList();
//                            for (Object item :
//                                    objList) {
//                                String value = decrypt(token, item.toString());//解密操作
//                                newList.add(value);
//                            }
//                            field.set(obj, newList);
//                        } catch (IllegalAccessException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                } else {
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
                                field.set(obj, value);
                                //处理加密字段
                                if (field.getName().startsWith("encrypt_")) {
                                    Field _field = obj.getClass().getDeclaredField(field.getName().replace("encrypt_", ""));
                                    _field.setAccessible(true);
                                    _field.set(obj, Long.parseLong(value));
                                }
                            }
                        } catch (IllegalAccessException | NoSuchFieldException e) {
                            e.printStackTrace();
                        }
                    } else if (!notTypes.contains(field.getType())) {
                        try {
                            field.setAccessible(true);
                            decryptObject(field.get(obj), token);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
//        }
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
            if(notTypes.contains(((List<?>) obj).get(0).getClass()))
                return;
            for (Object o : (List<?>) obj) {
                encryptObject(o, token);
            }
        } else {
            //1.判断obj类型，如果是无需处理的类型则直接返回
            if (notTypes.contains(obj.getClass())) {
                return;
            }

            //2.获取类型各字段进行操作
            Field[] fields = obj.getClass().getDeclaredFields();

            for (Field field : fields) {
//                //如果是数组，获取其类型并循环递归
//                if (field.getGenericType() instanceof ParameterizedType) {
//                    ParameterizedType pt = (ParameterizedType) field.getGenericType();
//                    //数组，且其类型不为基本类型、String或Integer等无需处理字段的类型
//                    if (pt.getRawType().equals(List.class) && !notTypes.contains(pt.getActualTypeArguments()[0])) {
//                        try {
//                            field.setAccessible(true);
//                            encryptObject(field.get(obj), token);
//                        } catch (IllegalAccessException e) {
//                            e.printStackTrace();
//                        }
//                    } else if (pt.getRawType().equals(List.class)
//                            && pt.getActualTypeArguments()[0].equals(String.class)
//                            && SENSITIVE_FIELDS.contains(field.getName())) {
//                        try {
//                            field.setAccessible(true);
//                            List objList = (List) field.get(obj);
//                            List newList = new ArrayList();
//                            for (Object item :
//                                    objList) {
//                                String value = encrypt(token, item.toString());//加密操作
//                                newList.add(value);
//                            }
//                            field.set(obj, newList);
//                        } catch (IllegalAccessException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                } else {
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
                                }else {
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
