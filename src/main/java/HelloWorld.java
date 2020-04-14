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
        List<Integer> integers = Arrays.asList(1,2,3,4,5);
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