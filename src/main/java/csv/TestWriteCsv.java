package csv;

import jsoup.Student;

import java.util.ArrayList;
import java.util.List;

import static utils.CsvUtil.writeCSV;

public class TestWriteCsv {
    public static void testCsv(){
        Student s=new Student();
        s.setAge(21);
        s.setEmail("11");
        s.setName("yaochongwei");
        s.setPhone("157");
        s.setSex("1");

        Student s1=new Student();
        s1.setAge(22);
        s1.setEmail("11");
        s1.setName("yaochongwei");
        s1.setPhone("157");
        s1.setSex("0");
        List<Student> l=new ArrayList<>();
        l.add(s);
        l.add(s1);
        String csvFilePath = "D://yaochongwei.csv";
        String[] csvHeaders = { "年龄", "邮件", "姓名","手机","性别" };
        writeCSV(l,csvFilePath,csvHeaders);
    }
}
