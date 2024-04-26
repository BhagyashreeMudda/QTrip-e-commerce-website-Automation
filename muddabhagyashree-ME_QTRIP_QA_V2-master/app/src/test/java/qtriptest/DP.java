package qtriptest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import org.apache.poi.ss.formula.atp.Switch;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;


public class DP {
    
    @DataProvider(name = "data-provider") 
    public Object[][] dpMethod(Method m) throws IOException{
        Object [][]data = null; 
        ///home/crio-user/workspace/dhi18ren-ME_QTRIP_QA/app/src/test/resources/DatasetsforQTrip.xlsx
                try {
                    File fileName = new File("src/test/resources/DatasetsforQTrip.xlsx");
    
                    FileInputStream file = new FileInputStream(fileName);
                    // Create Workbook instance holding reference to .xlsx file
                    XSSFWorkbook workbook = new XSSFWorkbook(file); 
                       // Get first/desired sheet from the workbook
                        XSSFSheet sheet = workbook.getSheet(m.getName());
                        int rowCount = sheet.getLastRowNum();
                        System.out.println(rowCount);
                        int colsCount = sheet.getRow(1).getLastCellNum();
                        System.out.println(colsCount);
                        data = new Object[rowCount][colsCount-1];
                        for (int outer = 1; outer <= rowCount; outer++) { // outer for loop to iterate each row
                            XSSFRow rows = sheet.getRow(outer);
                            
                            for (int inner = 1; inner < colsCount; inner++) { // inner for loop to iterate each cell
                                XSSFCell cell = rows.getCell(inner);
                                switch (cell.getCellType()) {
                                    case STRING:
                                        data[outer-1][inner-1] = cell.getStringCellValue();
                                        break;
                                    case NUMERIC:
                                        data[outer-1][inner-1] = String.valueOf(cell.getNumericCellValue());
                                        break;
                                    case BOOLEAN:
                                        data[outer-1][inner-1] = cell.getBooleanCellValue();
                                        break;
                                    default:
                                        data[outer-1][inner-1] = null;
                                        break;
                                }
                            }
                        } 

                } catch (Exception e) {
                //TODO: handle exception
                System.out.println(e.getMessage());
            } 
            return data;
    }
}
