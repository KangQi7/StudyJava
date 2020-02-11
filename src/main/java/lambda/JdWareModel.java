package lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * 京东采集商品sku工具类
 */
public class JdWareModel {
    //sku选择列表，用于标记已采集和未采集的skuId
    private List<SelectSku> skuIdList;
    //sku列表，用于记录spu中总共的sku
    private List<String> skuIds;

    /**
     * 构造方法，skuId为第一个被选skuId
     * @param skuId
     */
    public JdWareModel(String skuId){
        //初始化sku选择列表
        skuIdList = new ArrayList<>();
        SelectSku selectSku = new SelectSku(skuId, true );
        skuIdList.add(selectSku);
        //初始化sku列表
        skuIds = new ArrayList<>();
        skuIds.add(skuId);
    }

    /**
     * 添加SkuId
     * @param addSkuIds
     * @return 返回添加数
     */
    public int addSkuIds(List<String> addSkuIds){
        addSkuIds.removeAll(skuIds);
        int i = 0;
        //添加到sku对象列表
        for (String skuId : addSkuIds){
            skuIdList.add(new SelectSku(skuId, false));
            i++;
        }
        //添加到skuId列表
        skuIds.addAll(addSkuIds);
        return i;
    }

    /**
     * 获取一个尚未被选择（采集）的skuId
     * @return
     */
    public String getSkuId(){
        SelectSku selectSku = skuIdList.stream().filter(s -> s.selected == false).findFirst().orElse(null);
        if (selectSku != null){
            selectSku.selected = true;
            return selectSku.skuId;
        }
        else{
            return null;
        }
    }

    /**
     * sku标记对象
     */
    private class SelectSku{
        public String skuId;
        //是否已被选择
        public boolean selected;
        public SelectSku(String skuId, boolean selected){
            this.skuId = skuId;
            this.selected = selected;
        }
    }
}
