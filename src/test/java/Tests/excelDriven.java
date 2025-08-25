package Tests;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import java.io.*;
import java.util.*;

public class excelDriven {

    public static void main(String[] args) throws IOException {
        ArrayList<String> appleData = getData("Apple");
        if (appleData.size() == 0) {
            System.out.println("Apple not found!");
        } else {
            System.out.println("Row data for Apple: " + appleData);

            // To get the price, find the "price" column index (dynamically)
            // Assume header is in row 0
            FileInputStream fis = new FileInputStream("/Users/adhishgurjar/Documents/yourfile.xlsx"); // update path!
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            int priceCol = -1;
            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                if (headerRow.getCell(i).getStringCellValue().equalsIgnoreCase("price")) {
                    priceCol = i;
                    break;
                }
            }
            workbook.close();
            fis.close();

            if (priceCol != -1 && appleData.size() > priceCol) {
                System.out.println("Price of Apple is: " + appleData.get(priceCol));
            } else {
                System.out.println("Price column not found!");
            }
        }
    }

    // Use same logic as your getData, but for fruit_name instead of TestCases
    public static ArrayList<String> getData(String fruitName) throws IOException {
        ArrayList<String> a = new ArrayList<String>();
        FileInputStream fis = new FileInputStream("/Users/adhishgurjar/Documents/yourfile.xlsx"); // update path!
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0); // or use getSheet("yourSheetName")

        // Identify "fruit_name" column by scanning the first row
        Iterator<Row> rows = sheet.iterator();
        Row firstRow = rows.next();
        Iterator<Cell> ce = firstRow.cellIterator();
        int k = 0;
        int fruitCol = 0;
        while (ce.hasNext()) {
            Cell value = ce.next();
            if (value.getStringCellValue().equalsIgnoreCase("fruit_name")) {
                fruitCol = k;
            }
            k++;
        }

        // Once column is identified, scan entire fruit_name column to identify "Apple" row
        while (rows.hasNext()) {
            Row r = rows.next();
            if (r.getCell(fruitCol).getStringCellValue().equalsIgnoreCase(fruitName)) {
                Iterator<Cell> cv = r.cellIterator();
                while (cv.hasNext()) {
                    Cell c = cv.next();
                    if (c.getCellType() == CellType.STRING) {
                        a.add(c.getStringCellValue());
                    } else {
                        a.add(String.valueOf(c.getNumericCellValue()));
                    }
                }
                break; // Stop after finding first match
            }
        }
        workbook.close();
        fis.close();
        return a;
    }
}