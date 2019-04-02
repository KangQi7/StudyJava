package jsoup.test.skuQuestion;

import lombok.Data;

@Data
public class SkuAnswer {
    private int id;

    private int productId;

    private int status;

    private int clientType;

    private int associatedId;

    private int anonymous;

    private int type;

    private int recommend;

    private int top;

    private String created;

    private String modified;

    private int likeCount;

    private int rewardBeanNum;

    private String rewardCreated;

    private String content;

    private UserInfo userInfo;

    private boolean like;
}
