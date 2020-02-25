package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author KangQi
 * @Date 2020/2/25 8:52
 * @Version 1.0
 */
public class ExcelUtil {

    /**
     * 解析2007 excel版本（xlsx），返回一维数组集合
     *
     * @param file 文件
     * @return 一维数组集合
     */
    private static List<String[]> readExcelFile2007(MultipartFile file) throws Exception {
        List<String[]> stringList = new ArrayList<>();
        InputStream input = file.getInputStream();
        XSSFWorkbook workbook = new XSSFWorkbook(input);
        // 获取excel表中的第一个工作表
        XSSFSheet sheet = workbook.getSheetAt(0);
        for (int sheetRow = 0; sheetRow < sheet.getLastRowNum() + 1; sheetRow++){
            XSSFRow row = sheet.getRow(sheetRow);
            String[] values = new String[row.getLastCellNum()];
            for ( int sheetCell = 0; sheetCell < row.getLastCellNum(); sheetCell++){
                XSSFCell cell = row.getCell(sheetCell);
                String value = getCellValue(cell);
                values[sheetCell] = value;
            }
            stringList.add(sheetRow,values);
        }
        input.close();
        return stringList;
    }

    /**
     * 获取单元格值
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell){
        String cellValue = "";
        if(cell == null){
            return cellValue;
        }
        //把数字当成String来读，避免出现1读成1.0的情况
        if(cell.getCellTypeEnum() == CellType.NUMERIC){
            cell.setCellType(CellType.STRING);
        }
        //判断数据的类型
        switch (cell.getCellTypeEnum()){
            case NUMERIC: //数字
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA: //公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case BLANK: //空值
                cellValue = "";
                break;
            case ERROR: //故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }
}
