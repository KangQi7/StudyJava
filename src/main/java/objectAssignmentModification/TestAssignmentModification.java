package objectAssignmentModification;

/**
 * @Author KangQi
 * @Date 2020/4/14 14:10
 * @Version 1.0
 */
public class TestAssignmentModification {
    public void test(){
        String[] images = new String[]{"123","456","789"};
        String image = images[0];
        image = "246";
        System.out.println(String.join(",",images));

        try {
            if (images[0].equals("123")){
                throw new Exception("赋值出错");
            }
            System.out.println(images[0]);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
