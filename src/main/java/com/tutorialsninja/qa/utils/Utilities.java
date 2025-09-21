package com.tutorialsninja.qa.utils;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

public class Utilities {

    public static final int IMPLICIT_WAIT_TIME = 10;
    public static final int PAGE_LOAD_TIME = 5;

    public static String generateEmailTimeStamp(){
        Date date = new Date();
        String timeStamp =  date.toString().replace(" ","_").replace(":","_");
        return "cut"+timeStamp+"@gmail.com";
    }

    public static Object[][] getTestDataFromExcel(String sheetName){
        String excelFilePath = System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata" +
                "\\TutorialsNinjaTestData.xlsx";
        File file = new File(excelFilePath);
        XSSFWorkbook workbook = null;

        try {
            FileInputStream fisExcel = new FileInputStream(file);
            workbook = new XSSFWorkbook(fisExcel);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        assert workbook != null;
        XSSFSheet sheet = workbook.getSheet(sheetName);

        int noOfRows = sheet.getLastRowNum();
        int noOfColumns = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[noOfRows][noOfColumns];

        for(int r=0; r<noOfRows; r++){
            XSSFRow row = sheet.getRow(r + 1);
            for(int c=0; c<noOfColumns; c++){
                XSSFCell cell = row.getCell(c);
                CellType cellType = cell.getCellType();

                switch (cellType){
                    case STRING:{
                        data[r][c] = cell.getStringCellValue();
                        break;
                    }
                    case NUMERIC:{
                        data[r][c] = Integer.toString((int)cell.getNumericCellValue());
                        break;
                    }
                    case BOOLEAN:{
                        data[r][c] =cell.getBooleanCellValue();
                        break;
                    }
                }
            }
        }
        try {
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    public static String captureScreenshot(WebDriver driver, String testName){
        File srcScreenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destinationScreenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
        try{
            FileHandler.copy(srcScreenShot, new File(destinationScreenshotPath));
        }catch (Throwable e){
            e.printStackTrace();
        }
        return destinationScreenshotPath;
    }
}
