import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import order.create.OrderGenerationTool;
import order.create.model.Bill;
import order.create.model.Order;
import order.create.model.OrderDetail;

import javax.lang.model.SourceVersion;
import javax.lang.model.element.VariableElement;
import javax.swing.text.StyledEditorKit;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelloWorld {

    public static void main(String[] args) {
        String sms = ";s,";
        Map<Integer, String> companyInfo = new HashMap<>();
        companyInfo.put(54, "543");
        String id = "54";
        String company;
        try {
            company = companyInfo.get(Integer.parseInt(id));
        } catch(NumberFormatException e){
            company = null;
        }

        System.out.println(company);
        if (sms.contains(",") && sms.contains(";")) {
            System.out.println("Success-1");
        }
    }
}