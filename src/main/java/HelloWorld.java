import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import order.create.OrderGenerationTool;
import order.create.model.Bill;
import order.create.model.Order;
import order.create.model.OrderDetail;

import javax.lang.model.SourceVersion;
import javax.lang.model.element.VariableElement;
import javax.swing.text.StyledEditorKit;
import java.math.BigDecimal;
import java.util.*;

public class HelloWorld {

    public static void main(String[] args) {
        Long a = 12283L;
        Long b = 12283L;
        String message = "中间商";
        String message1 = "123";
        String message2 = "中间商1";
        System.out.println(message.length() + "," + message1.length() + "," + message2.length());
        if (a.equals(b))
            System.out.println(System.currentTimeMillis() / 1000L + "." + createRandomNum(99999,10000));
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