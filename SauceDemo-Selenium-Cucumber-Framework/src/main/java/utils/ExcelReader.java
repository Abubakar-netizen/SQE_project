package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ExcelReader - Utility to read test data from Excel files
 */
public class ExcelReader {
    private String filePath;
    private Workbook workbook;

    public ExcelReader(String filePath) {
        this.filePath = filePath;
        try {
            FileInputStream fis = new FileInputStream(filePath);
            this.workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load Excel file: " + filePath);
        }
    }

    /**
     * Get all data from a specific sheet
     * 
     * @param sheetName Name of the sheet
     * @return List of maps containing row data
     */
    public List<Map<String, String>> getData(String sheetName) {
        List<Map<String, String>> data = new ArrayList<>();
        Sheet sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            throw new RuntimeException("Sheet not found: " + sheetName);
        }

        Row headerRow = sheet.getRow(0);
        if (headerRow == null) {
            return data;
        }

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null)
                continue;

            Map<String, String> rowData = new HashMap<>();

            for (int j = 0; j < headerRow.getLastCellNum(); j++) {
                Cell headerCell = headerRow.getCell(j);
                Cell dataCell = row.getCell(j);

                if (headerCell != null) {
                    String header = headerCell.getStringCellValue();
                    String value = getCellValue(dataCell);
                    rowData.put(header, value);
                }
            }
            data.add(rowData);
        }
        return data;
    }

    /**
     * Get cell value as string
     * 
     * @param cell Cell to read
     * @return Cell value as string
     */
    private String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                }
                return String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    /**
     * Get specific cell value
     * 
     * @param sheetName Sheet name
     * @param row       Row number (0-indexed)
     * @param column    Column number (0-indexed)
     * @return Cell value
     */
    public String getCellData(String sheetName, int row, int column) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new RuntimeException("Sheet not found: " + sheetName);
        }

        Row dataRow = sheet.getRow(row);
        if (dataRow == null) {
            return "";
        }

        Cell cell = dataRow.getCell(column);
        return getCellValue(cell);
    }

    /**
     * Get row count
     * 
     * @param sheetName Sheet name
     * @return Number of rows
     */
    public int getRowCount(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            return 0;
        }
        return sheet.getLastRowNum() + 1;
    }

    /**
     * Close workbook
     */
    public void close() {
        try {
            if (workbook != null) {
                workbook.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
