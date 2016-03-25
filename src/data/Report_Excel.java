package data;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import users.UserList;


public class Report_Excel 
{
	
	private UserList employeeList;
	private DataList dataList;
	private ArrayList<Row> dataRows = new ArrayList<Row>();
	private int totalInstanceCount;
	
	public void createExcelReport( UserList employeeList, DataList dataList)
	{
		this.employeeList = employeeList;
		this.dataList = dataList;
		try {
			//create a file, a workbook, and add the workbook to the file
			Workbook wb = new XSSFWorkbook();
			FileOutputStream reportFile = new FileOutputStream("reportFile.xlsx");
			CellStyle cellStyle = wb.createCellStyle();
			Sheet sheet = wb.createSheet("Report");
			
			//create two rows that we'll work with
			Row row0 = sheet.createRow((short)0);
			Row row2 = sheet.createRow((short)2);
			Row row4 = sheet.createRow((short)4);
			Row row5 = sheet.createRow((short)5);
			Row row6 = sheet.createRow((short)6);
			Row row7 = sheet.createRow((short)7);
			Row row8 = sheet.createRow((short)8);
			Row row9 = sheet.createRow((short)9);
			//Row row10 = sheet.createRow((short)10);
			
			//create all the merged cells
			createMergeCells(sheet);
			
			//create lines
			upperLine(wb, row4, 0, 6);
			upperLine(wb, row7, 0, 6);
			upperLine(wb, row9, 0, 6);
			//upperLine(wb, row10, 0, 6);
			
			//-----------------ROW 0--------------------
			//create the report label creation date
			row0.createCell(5).setCellValue("Report Creation Date:");
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
			Date date = new Date();
			//add the formatted date to the cell
			row0.createCell(6).setCellValue(dateFormat.format(date));
			//size the cells to the width of the contents
			sheet.autoSizeColumn(5);
			sheet.autoSizeColumn(6);
			// bold, center, upperborder, rightborder, allBorder, left
			setStyle(wb, row0, 5, false, false, false, false, true, false);
			setStyle(wb, row0, 6, false, false, false, false, true, false);
			
			//-----------------ROW 2--------------------
			row2.createCell(2).setCellValue("Load Shed Controller Report");
			setStyle(wb, row2, 2, true, true, false, false, false, false);
			
			//-----------------ROW 4--------------------
			row4.createCell(0).setCellValue("Shut Off Count:");
			row4.createCell(6);
			setStyle(wb, row4, 0, false, false, true, false, false, true);
			setStyle(wb, row4, 6, false, false, true, true, false, false);
			row4.createCell(2).setCellValue(dataList.getShutOffCount());
			setStyle(wb, row4, 2, false, false, true, false, false, false);
			
			//-----------------ROW 5--------------------
			row5.createCell(0);
			row5.createCell(6);
			setStyle(wb, row5, 0, false, false, false, false, false, true);
			setStyle(wb, row5, 6, false, false, false, true, false, false);
			
			//-----------------ROW 6--------------------
			row6.createCell(0).setCellValue("Number of Users:");
			row6.createCell(6);
			setStyle(wb, row6, 0, false, false, false, false, false, true);
			setStyle(wb, row6, 6, false, false, false, true, false, false);
			row6.createCell(2).setCellValue(employeeList.listSize());
			
			
			//-----------------ROW 8--------------------
			row8.createCell(0).setCellValue("Incidents");
			setStyle(wb, row8, 0, true, true, false, false, false, false);
			
			//-----------------ROW 9--------------------
			row9.createCell(0).setCellValue("User");
			setStyle(wb, row9, 0, true, true, true, false, false, true);
			
			row9.createCell(2).setCellValue("Function");
			setStyle(wb, row9, 2, true, true, true, false, false, false);
			
			row9.createCell(4).setCellValue("Time Stamp");
			setStyle(wb, row9, 4, true, true, true, false, false, false);
			
			row9.createCell(6);
			setStyle(wb, row9, 6, false, false, true, true, false, false);
			
			//if there are any incidents
			// bold, center, upperborder, rightborder, allBorder, left
			
			totalInstanceCount = dataList.getPowerOnCount() + dataList.getShutOffCount();
			if(totalInstanceCount > 0)
			{
				//create rows
				for(int i = 0; i <= totalInstanceCount; i++)
				{
					if(i != 0)
					{
						//dataRows.add(sheet.createRow((short)(10 + i - 1)));
						
						//create end line
						upperLine( wb, dataRows.get(i - 1), 0, 6);
						
						//format rows
						dataRows.get(i - 1).createCell(0).setCellValue(dataList.list.get(i - 1).getName());
						setStyle(wb, dataRows.get(i - 1), 0, false, true, true, false, false, true);
						
						dataRows.get(i - 1).createCell(2).setCellValue(dataList.list.get(i - 1).getAction());
						setStyle(wb, dataRows.get(i - 1), 2, false, true, true, false, false, false);
						
						DateFormat dateFormatIncident = new SimpleDateFormat("MM/dd/yyyy  HH:mm:ss");
						dataRows.get(i - 1).createCell(4).setCellValue(dateFormatIncident.format(dataList.list.get(i - 1).getDate()));
						setStyle(wb, dataRows.get(i - 1), 4, false, true, true, false, false, false);
						
						dataRows.get(i - 1).createCell(6);
						setStyle(wb, dataRows.get(i - 1), 6, false, false, true, true, false, false);
					}
					dataRows.add(sheet.createRow((short)(10 + i)));
					upperLine( wb, dataRows.get(i), 0, 6);
					
				}
				
				
			}
						    
			wb.write(reportFile);
			reportFile.close();
			
			launchExcelReport();
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// create a new workbook
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	//-----------------------------------------------------------------
	//-----------------------HELPER METHODS----------------------------
	//-----------------------------------------------------------------
	
	//Create the merged cells for the excel sheet
	private void createMergeCells(Sheet sheet)
	{
		//to show what's going on
		sheet.addMergedRegion(new CellRangeAddress(
	            2, //first row (0-based)
	            2, //last row  (0-based)
	            2, //first column (0-based)
	            5  //last column  (0-based)
	    ));
		
		
		sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 1 ));
		sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 1 ));
		sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 1 ));
		sheet.addMergedRegion(new CellRangeAddress(8, 8, 0, 6 ));
		
		//increment through the rows to merge cells
		for(int i = 9; i < 50; i++)
		{
			sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 1));
		}
		
		for(int i = 9; i < 50; i++)
		{
			sheet.addMergedRegion(new CellRangeAddress(i, i, 2, 3));
		}

		for(int i = 9; i < 50; i++)
		{
			sheet.addMergedRegion(new CellRangeAddress(i, i, 4, 6));
		}

	}
	
	private void upperLine(Workbook wb, Row row, int startColumn, int endColumn)
	{
		CellStyle style = wb.createCellStyle();
		style.setBorderTop(CellStyle.BORDER_THIN);
		for(int i = startColumn; i <= endColumn; i++)
		{
			row.createCell(i);
			row.getCell(i).setCellStyle(style);
		}
	}
	
	
	 private void setStyle(Workbook wb, Row row, int column, 
	 boolean bold, boolean center, boolean upperBorder, boolean rightBorder, boolean fullBorder, boolean leftBorder)
	 {
		 CellStyle style = wb.createCellStyle();
		 Font boldFont= wb.createFont();
		 if(bold)
		 {
			 boldFont.setBold(true);
			 style.setFont(boldFont);
		 }
		 if(center)
			 style.setAlignment(CellStyle.ALIGN_CENTER);
		 if(upperBorder)
			 style.setBorderTop(CellStyle.BORDER_THIN);
		 if(rightBorder)
			 style.setBorderRight(CellStyle.BORDER_THIN);
		 if(leftBorder)
			 style.setBorderLeft(CellStyle.BORDER_THIN);
		 if(fullBorder)
		 {
			style.setBorderBottom(CellStyle.BORDER_THIN);
			style.setBorderLeft(CellStyle.BORDER_THIN);
			style.setBorderRight(CellStyle.BORDER_THIN);
			style.setBorderTop(CellStyle.BORDER_THIN);
		 }
		 
		 row.getCell(column).setCellStyle(style);
	 } 
	 
	
	//automatically launch the excel report created
	private void launchExcelReport()
	{
		if (!Desktop.isDesktopSupported()) {
	        System.err.println("Desktop not supported");
	        // use alternative (Runtime.exec)
	        return;
	    }

	    Desktop desktop = Desktop.getDesktop();
	    if (!desktop.isSupported(Desktop.Action.EDIT)) {
	        System.err.println("EDIT not supported");
	        // use alternative (Runtime.exec)
	        return;
	    }

	    try {
	        desktop.open(new File("reportFile.xlsx"));
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
	}
	
}
