package enums;

public class Test {
    @org.junit.Test
    public void TestEnums(){
        Integer a = TestEnum.A.ordinal();
        Integer b = 0;
        System.out.println(b.equals(TestEnum.A));
    }
}
