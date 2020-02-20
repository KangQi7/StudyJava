package dom4j;

import java.util.Iterator;
import java.util.List;

import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import org.dom4j.*;
import org.junit.Test;

/**
 * 测试DOM4J识别xml
 * @Author KangQi
 * @Date 2019/12/6 8:45
 * @Version 1.0
 */
public class TestDOM4J {
    @Test
    public void testDom4j() throws DocumentException {
        String xmlStr = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>" +
                "<returnsms>" +
                "</returnsms>";
        Document document=DocumentHelper.parseText(xmlStr);//xmlStr为上图格式的字符串

        //3.获取根节点
        Element rootElement = document.getRootElement();
        List<Node> nodes = document.getRootElement().selectNodes("statusbox");
        if (!nodes.isEmpty()){
            for (Node node:
                 nodes) {
                String code = node.selectSingleNode("errorcode").getText();
                System.out.println(code);
                String sms = node.selectSingleNode("receivetime").getText() + "," +  node.selectSingleNode("taskid").getText() + ","
                        + node.selectSingleNode("mobile").getText() + ","  + node.selectSingleNode("errorcode").getText() + ";";
                System.out.println(sms);
            }
            System.out.println("成功发送");
        }

//        Node VideoCompany=document.selectSingleNode("returnstatus");//获取节点对象,注意引号内的“//”必须加 ，否则报错
//        Node DevIP=document.selectSingleNode("//");
//        //根据节点对象获取相应信息
//
//        String videoCompany=VideoCompany.getText();
//
//        String devIp=DevIP.getText();

//        System.out.println(devIp);//此时输出结果极为字符串：3333
    }
}
