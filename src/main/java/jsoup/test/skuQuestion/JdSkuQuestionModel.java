package jsoup.test.skuQuestion;

import lombok.Data;

import java.util.List;

@Data
public class JdSkuQuestionModel {
    private List<SkuQuestion> questionList;

    private SkuInfo skuInfo;

    private int totalItem;

    private String resultCode;
}
