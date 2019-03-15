import jsoup.test.TestJsoup;
import regex.test.TestRegex;

import java.io.IOException;

public class HelloWorld {
    public static void main(String[] args){
        TestRegex testRegex = new TestRegex();
        try {
            testRegex.testMatchMothed();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        testJsoup.testJsoupSelecter();
    }
}