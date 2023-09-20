package app.configs;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * This class contains generic methods to read and write data into excel sheet
 * @author Govinda Rai
 *
 */
public class ExcelFileUtility {

	/**
	 * This method will read data from excel sheet and return the value when sheetname
	 * row no and cell number is specified
	 * @param SheetName
	 * @param rowNo
	 * @param celNo
	 * @return
	 * @throws Throwable
	 */

	String excelPath ="src/main/resources/apiData.xlsx";
	public String readDataFromExcel(String SheetName, int rowNo, int celNo) throws Throwable
	{
		FileInputStream fis = new FileInputStream(excelPath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(SheetName);
		Row ro = sh.getRow(rowNo);
		Cell cel = ro.getCell(celNo);
		String value = cel.getStringCellValue();
		return value;
	}

	/**
	 * This method will write data into excel sheet
	 * @param SheetName
	 * @param rowNo
	 * @param celNo
	 * @param value
	 * @throws Throwable
	 */
	public void writeDataIntoExcel(String SheetName, int rowNo, int celNo, String value) throws Throwable
	{
		FileInputStream fis = new FileInputStream(excelPath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(SheetName);
		Row ro = sh.getRow(rowNo);
		if(ro==null) {
			ro=sh.createRow(rowNo);
		}
		Cell cel = ro.createCell(celNo);
		cel.setCellValue(value);
		FileOutputStream fos = new FileOutputStream(excelPath);
		wb.write(fos);

	}

	/**
	 * This method will return last row number
	 * @param SheetName
	 * @return
	 * @throws Throwable
	 * @throws Throwable
	 */
	public int getRowCount(String SheetName) throws Throwable, Throwable
	{
		FileInputStream fis = new FileInputStream(excelPath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(SheetName);
		int row = sh.getLastRowNum();
		return row;
	}

	/**
	 * This method will read multiple data from excel sheet with the help of sheetname
	 * and return 2 dimensional object [][]
	 * @param SheetName
	 * @return
	 * @throws Throwable
	 */
	public Object[][] readmultipleDataFromExcel(String SheetName) throws Throwable
	{
		FileInputStream fis = new FileInputStream(excelPath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(SheetName);
		int lastRow = sh.getLastRowNum();
		int lastCell = sh.getRow(0).getLastCellNum();

		Object[][] data = new Object[lastRow][lastCell];

		for(int i = 0;i<lastRow;i++)
		{
			for(int j=0;j<lastCell;j++)
			{
				data[i][j]=sh.getRow(i+1).getCell(j).getStringCellValue();
			}
		}

		return data;

	}

	/**
	 * @param SheetName
	 * @param TCName
	 * @return
	 * @throws Throwable
	 */
	public int getTCNum(String SheetName,String TCName) throws Throwable
	{
		FileInputStream fis = new FileInputStream(excelPath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(SheetName);
		int lastRow = sh.getLastRowNum();
		//int lastCell = sh.getRow(0).getLastCellNum();

		String[] s = new String[lastRow];

		for(int i = 0;i<lastRow;i++)
		{
			s[i]=sh.getRow(i+1).getCell(0).getStringCellValue();
			if(s[i].equals(TCName)) {
				return i+1;
			}
		}

		return -1;

	}
}
