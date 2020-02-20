package error;

import org.jsoup.Connection;
import org.junit.Test;

public class ErrorStudy {
    @Test
    public void TestError(){
        try {
            ErrorStudy errorStudy = new ErrorStudy();
            // 测试对象的equals
            ErrorStudy errorStudy1 = new ErrorStudy();
            if (errorStudy.equals(errorStudy1)){
                System.out.println("equals");
            }
            errorStudy.methodA(2);
        }
        catch (SpecialException e){
            System.out.println(e.getMessage());
        }
    }

    public void methodA(int money) throws SpecialException {
        if (--money <= 0) throw new SpecialException("out");
        System.out.println("methodA");
    }
}
