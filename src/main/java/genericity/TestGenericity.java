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
            "encrypt_number", "resultCode", "questionList", "customerName","skus"
    );
    //
    private static List<Type> types = Arrays.asList(String.class,Integer.class, Long.class, Date.class,
            Map.class, HashMap.class,Dictionary.class,
            int.class, long.class, float.class, double.class, boolean.class, byte.class,char.class,short.class);

    @Test
    public void test() {
        String token = "";
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

    public static <T> void decryptObject(T obj, String token) {
        if(obj == null){
            return;
        }
        //如果是数组类型，循环执行
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                decryptObject(o , token);
            }
        } else {
            //1.判断obj类型，如果是无需处理的类型则直接返回
            if (types.contains(obj.getClass())){
                return;
            }

            //2.获取类型各字段进行操作
            Field[] fields = obj.getClass().getDeclaredFields();

            for (Field field : fields) {
                //如果是数组，获取其类型并循环递归
                if (field.getGenericType() instanceof ParameterizedType) {
                    ParameterizedType pt = (ParameterizedType) field.getGenericType();
                    //数组，且其类型不为基本类型、String或Integer等无需处理字段的类型
                    if (pt.getRawType().equals(List.class) && !types.contains(pt.getActualTypeArguments()[0])) {
                        try {
                            field.setAccessible(true);
                            decryptObject(field.get(obj), token);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    } else if(pt.getRawType().equals(List.class)
                            && pt.getActualTypeArguments()[0].equals(String.class)
                            && SENSITIVE_FIELDS.contains(field.getName())){
                        try {
                            field.setAccessible(true);
                            List objList = (List)field.get(obj);
                            List newList = new ArrayList();
                            for (Object item:
                                    objList) {
                                item += "1";
                                newList.add(item);
                            }
                            field.set(obj, newList);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    //String类型，判断是否是加密字段，是则解密并赋值
                    if (field.getGenericType().equals(String.class)) {
                        if (!SENSITIVE_FIELDS.contains(field.getName())) {
                            continue;//不是则继续循环字段
                        }

                        try {
                            field.setAccessible(true);
                            String value = field.get(obj).toString() + "1";
                            field.set(obj, value);
                            //处理加密字段
                            if (field.getName().startsWith("encrypt_")) {
                                Field _field = obj.getClass().getDeclaredField(field.getName().replace("encrypt_", ""));
                                _field.setAccessible(true);
                                _field.set(obj, Long.parseLong(value));
                            }
                        } catch (IllegalAccessException | NoSuchFieldException e) {
                            e.printStackTrace();
                        }
                    } else if(!types.contains(field.getType())) {
                        try {
                            field.setAccessible(true);
                            decryptObject(field.get(obj), token);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
