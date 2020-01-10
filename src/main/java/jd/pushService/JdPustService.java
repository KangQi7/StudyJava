package jd.pushService;

import com.google.gson.Gson;
import com.jdcloud.sdk.auth.CredentialsProvider;
import com.jdcloud.sdk.auth.StaticCredentialsProvider;
import com.jdcloud.sdk.http.HttpRequestConfig;
import com.jdcloud.sdk.http.Protocol;
import com.jdcloud.sdk.service.yundingdatapush.client.YundingdatapushClient;
import com.jdcloud.sdk.service.yundingdatapush.model.*;
import org.junit.Test;

/**
 * @Author KangQi
 * @Date 2019/12/27 11:35
 * @Version 1.0
 */
public class JdPustService {
    private final String appKey = "F1A8CF2B4ED8441639BECF7E700D6245"; // appKey
    private final String accessKey = "B62D1748F2C84E6D64A5C654FAE2A2A8"; // appKey
    private final String secretKey = "B986ED5AE69AFC327888A45C6D898762"; // secretKey
    private final String ydRdsInstanceId = "yd-mysql-6vq1h7b5xh"; // 云鼎数据库实例ID

    @Test
    public void testSDKApi() {
        String venderId = "798862";
        // 1.设置accessKey和secretKey
        CredentialsProvider credentialsProvider = new StaticCredentialsProvider(accessKey, secretKey);

        // 2.创建YundingdatapushClient
        YundingdatapushClient client = YundingdatapushClient.builder()
                .credentialsProvider(credentialsProvider)
                .httpRequestConfig(new HttpRequestConfig.Builder().protocol(Protocol.HTTPS).build()) //默认为HTTPS
                .build();

        // 3.设置请求参数
        DescribeDatapushVendersRequest request = new DescribeDatapushVendersRequest();
        request.setAppkey(appKey);
        request.setVenderId(venderId);
        request.setYdRdsInstanceId(ydRdsInstanceId);

        // 4.执行请求得到响应
        DescribeDatapushVendersResponse response = client.describeDatapushVenders(request);
//        // 5.处理响应
//        System.out.println(new Gson().toJson(response));

        // 如果用户已添加，则返回
        if (response.getError() == null
                && response.getResult().getVenders().size() == 1) {
            return;
        } else {
            // 2.如果未添加则添加用户
            AddDatapushVenderRequest addDatapushVenderRequest = new AddDatapushVenderRequest();
            Vender vender = new Vender();
            vender.setAppkey(appKey);
            vender.setVenderId(venderId);
            vender.setYdRdsInstanceId(ydRdsInstanceId);
            addDatapushVenderRequest.setDatapushVender(vender);

            AddDatapushVenderResponse addDatapushVenderResponse =
                    client.addDatapushVender(addDatapushVenderRequest);

            // 判断一下添加成功没有，如果失败记录日志
            if (addDatapushVenderResponse.getError() != null) {
                System.out.println("数据同步服务-添加用户失败：" + addDatapushVenderResponse.getError().getMessage());
            }
        }

//        if (response.getError() == null && response.getResult().getVenders().size() == 1)
//            System.out.println(response.getResult().getVenders().get(0).getVenderId());
//        else
//            System.out.println("不存在");
    }
}
