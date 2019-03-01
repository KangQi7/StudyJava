package JsoupTest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class TestJsoup {
    public Document getDocument(String homePage){
        return Jsoup.parse(homePage);
    }
}
