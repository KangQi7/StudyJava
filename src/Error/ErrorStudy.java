package Error;

public class ErrorStudy {
    public void TestError(){
        try {
            ErrorStudy errorStudy = new ErrorStudy();
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
