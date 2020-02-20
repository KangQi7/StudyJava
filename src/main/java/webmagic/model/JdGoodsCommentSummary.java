package webmagic.model;

import lombok.Data;

/**
 * 京东商品评论概要统计
 *
 * @Author KangQi
 * @Date 2020/2/20 15:58
 * @Version 1.0
 */
@Data
public class JdGoodsCommentSummary {
    private int skuId;

    private int averageScore;

    private int defaultGoodCount;

    private String defaultGoodCountStr;

    private int commentCount;

    private String commentCountStr;

    private int goodCount;

    private String goodCountStr;

    private double goodRate;

    private int goodRateShow;

    private int generalCount;

    private String generalCountStr;

    private double generalRate;

    private int generalRateShow;

    private int poorCount;

    private String poorCountStr;

    private double poorRate;

    private int poorRateShow;

    private int videoCount;

    private String videoCountStr;

    private int afterCount;

    private String afterCountStr;

    private int showCount;

    private String showCountStr;

    private int oneYear;

    private int sensitiveBook;

    private int fixCount;

    private int plusCount;

    private String plusCountStr;

    private int buyerShow;

    private int poorRateStyle;

    private int generalRateStyle;

    private int goodRateStyle;

    private int installRate;

    private int score1Count;

    private int score2Count;

    private int score3Count;

    private int score4Count;

    private int score5Count;
}
