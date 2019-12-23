package xml.test;

import com.sun.scenario.effect.impl.prism.PrImage;
import lombok.Data;

/**
 * @Author KangQi
 * @Date 2019/12/6 11:13
 * @Version 1.0
 */
@Data
public class SMSReturn {
    //返回状态
    private String returnstatus;
    //消息
    private String message;
    //余额
    private String remainpoint;
    //任务序列ID
    private String taskID;
    //成功短信数
    private String successCounts;
}
