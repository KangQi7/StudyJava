package jd.getGoods;

import lombok.Builder;
import lombok.Data;

/**
 * @Author KangQi
 * @Date 2020/1/10 11:41
 * @Version 1.0
 */
@Data
@Builder
public class SearchGoodsParam {
    private String keyword;
    private String enc;
    private Integer qrst;
    private Integer rt;
    private Integer stop;
    private Integer vt;
    private String wq;
    private Integer stock;
    private Integer page;
    private Integer s;
    private Integer click;
}
