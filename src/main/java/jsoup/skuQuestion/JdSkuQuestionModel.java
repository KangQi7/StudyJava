package jsoup.skuQuestion;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class JdSkuQuestionModel {
    private List<SkuQuestion> questionList;

    private List<String> skus;

    private SkuInfo skuInfo;

    private String encrypt_number;

    private int totalItem;

    private String resultCode;

    private Date time;

    private Long number;

    private String phone;

    private String encrypt_phone;
}
