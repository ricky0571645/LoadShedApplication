import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fazecast.jSerialComm.SerialPort;


public class Window_Control extends JPanel
{
	//-----------------------------------------------------------------
	//-----------------------VARIABLE DECLARATIONS---------------------
	//-----------------------------------------------------------------
	//Labels
	private JLabel systemStatus;
	private JLabel statusImage;
	
	//Buttons
	private JButton exportDataButton;
	private JButton nullButton;
	private JButton nullButton2;
	private JButton logOffButton;
	private JButton powerOffButton;
	private JButton powerOnButton;
	
	private Font fontGUI = new Font("Candara", Font.BOLD, 15);
	//Middle buttons 
	private static final int BUTTON_HEIGHT = 70;
	
	//Insets
	private Insets insets;
	
	//Graphic Icon
	ImageIcon disconnectStatusImage;
	ImageIcon connectStatusImage;
	
	//An instance of Window_Credential for swapping
	private Window_Credential tester;
	
	private static final int ROW1 = 0;
	private static final int ROW2 = 1;
	private static final int ROW3 = 2;
	private static final int ROW4 = 3;
	
	private boolean arduinoConnected;
	private SerialPort serialPort;
	
	//--------------FOR DEBUGGING-----------
	//--------------------------------------
	boolean poweredOn = true;
		
	//-----------------------------------------------------------------
	//---------------------------GUI SETUP-----------------------------
	//-----------------------------------------------------------------
	public Window_Control()
	{
		setLayout(new GridBagLayout());
		GridBagConstraints constraint = new GridBagConstraints();
		
		insets = new Insets(0,0,0,0);
		
		//image for status
		connectStatusImage = new ImageIcon("status_good.png", "");
		disconnectStatusImage = new ImageIcon("status_bad.png", "");
		
		if(poweredOn)
			statusImage = new JLabel(connectStatusImage);
		else
			statusImage = new JLabel(disconnectStatusImage);
		
		//add the image to the top
		systemStatus = new JLabel("Status: ");
		systemStatus.setFont(new Font("Calibri", Font.PLAIN, 30));
		systemStatus.setBackground(Color.blue);
		constraint.fill = GridBagConstraints.NONE;
		constraint.weightx = .5;
		constraint.gridwidth = 1;
		constraint.ipadx = 0;  
		constraint.ipady = 0;
		insets.top = 0;
		insets.left = 0;
		constraint.insets = insets;
		//constraint.anchor = GridBagConstraints.FIRST_LINE_START;
		constraint.gridx = 0;
		constraint.gridy = ROW1;
		add(systemStatus, constraint);
				
		//add the image to the top
		constraint.fill = GridBagConstraints.PAGE_START;
		constraint.weightx = .5;
		constraint.gridwidth = 1;
		constraint.ipadx = 0;  
		constraint.ipady = 0;
		insets.top = 0;
		insets.left = 0;
		constraint.insets = insets;
		constraint.gridx = 1;
		constraint.gridy = ROW1;
		add(statusImage, constraint);
		
		//Add button for exporting data
		exportDataButton = new JButton("Export Data To File");
		exportDataButton.setFont(fontGUI);
		exportDataButton.setForeground(Color.white);
		exportDataButton.setBackground(Color.decode("#7ec0ee"));
	    constraint.fill = GridBagConstraints.HORIZONTAL;
	    constraint.weightx = 0.5;
		constraint.gridwidth = 1;
		constraint.ipadx = 10;
	    constraint.ipady = BUTTON_HEIGHT;
	    insets.top = 20;
	    insets.left = 0;
	    constraint.gridx = 0;
	    constraint.gridy = ROW2;
	    add(exportDataButton, constraint);
	    
	    //Add button for 
	    nullButton = new JButton("No Function");
	    nullButton.setFont(fontGUI);
	    nullButton.setForeground(Color.white);
	    nullButton.setBackground(Color.decode("#7ec0ee"));
  	    constraint.fill = GridBagConstraints.HORIZONTAL;
  	    constraint.weightx = 0.5;
  		constraint.gridwidth = 1;
  		constraint.ipadx = 10;
  	    constraint.ipady = BUTTON_HEIGHT;
  	    insets.top = 20;
  	    insets.left = 0;
  	    constraint.gridx = 3;
  	    constraint.gridy = ROW2;
  	    add(nullButton, constraint);
  	    
  	    //Add button for 
  	    logOffButton = new JButton("Log Off");
  	    logOffButton.setFont(fontGUI);
  	    logOffButton.setForeground(Color.white);
  	    logOffButton.setBackground(Color.decode("#7ec0ee"));
	    constraint.fill = GridBagConstraints.HORIZONTAL;
	    constraint.weightx = 0.5;
		constraint.gridwidth = 1;
		constraint.ipadx = 10;
	    constraint.ipady = BUTTON_HEIGHT;
	    insets.top = 30;
	    insets.left = 0;
	    constraint.gridx = 0;
	    constraint.gridy = ROW3;
	    add(logOffButton, constraint);
	    
  	    //Add button for 
	    nullButton2 = new JButton("No Function Here");
	    nullButton2.setFont(fontGUI);
	    nullButton2.setForeground(Color.white);
	    nullButton2.setBackground(Color.decode("#7ec0ee"));
  	    constraint.fill = GridBagConstraints.HORIZONTAL;
  	    constraint.weightx = 0.5;
  		constraint.gridwidth = 1;
  		constraint.ipadx = 10;
  	    constraint.ipady = BUTTON_HEIGHT;
  	    insets.top = 30;
  	    insets.left = 0;
  	    constraint.gridx = 3;
  	    constraint.gridy = ROW3;
  	    add(nullButton2, constraint);
	    
  	    //Add button for 
  	    powerOnButton = new JButton("Power Load On");
  	    powerOnButton.setFont(fontGUI);
  	    powerOnButton.setForeground(Color.black);
  	    powerOnButton.setBackground(Color.decode("#98ff98"));
	    constraint.fill = GridBagConstraints.HORIZONTAL;
	    constraint.weightx = 0.5;
		constraint.gridwidth = 1;
		constraint.ipadx = 10;
	    constraint.ipady = BUTTON_HEIGHT;
	    insets.top = 40;
	    insets.left = 0;
	    insets.right = 5;
	    constraint.gridx = 1;
	    constraint.gridy = ROW4;
	    add(powerOnButton, constraint);
	    
		 //Add button for 
	    powerOffButton = new JButton("Power Load Off");
	    powerOffButton.setFont(fontGUI);
	    powerOffButton.setForeground(Color.white);
	    powerOffButton.setBackground(Color.decode("#ff6666"));
	    constraint.fill = GridBagConstraints.HORIZONTAL;
	    constraint.weightx = 0.5;
		constraint.gridwidth = 1;
		constraint.ipadx = 10;
	    constraint.ipady = BUTTON_HEIGHT;
	    insets.top = 40;
	    insets.left = 5;
	    constraint.gridx = 2;
	    constraint.gridy = ROW4;
	    add(powerOffButton, constraint);
		
		setBackground(Color.white);
		setVisible(true);
		
		//-----------------------------------------------------------------
		//--------------------ARDUINO SERIAL SETUP-------------------------
		//-----------------------------------------------------------------
				
	    
	    //find which port belongs to the Arduino
		int arduinoPort = 0;
		//incrementer for different ports
		int i = 0;
		//check whether the arduino is connected
		arduinoConnected = false;
		
		//get the ports on the computer
		SerialPort[] ports = SerialPort.getCommPorts();
		//will display the ports we have
		System.out.println("To display the ports that we have available: ");
		for(SerialPort port : ports)
		{
			System.out.println(port.getSystemPortName() + "\n");
			if(port.getSystemPortName().equalsIgnoreCase("COM3"))
			{
				//if the port name is COM3 then the Arduino is connected
				arduinoConnected = true;
				arduinoPort = i;
			}
			//increment the ports if any
			i++;
		}
		
		//if the arduino is connected do the following
		if(arduinoConnected)
		{
			serialPort = ports[arduinoPort];
			//check whether port can open
			if(serialPort.openPort())
			{
				System.out.println("Port opened successfully.");
			}
			else 
			{
				System.out.println("Unable to open the port.");
				return;
			}
		}
		
		//-----------------------------------------------------------------
		//---------------------------BUTTON ACTION-------------------------
		//-----------------------------------------------------------------
		
		//power off signal to Arduino
		powerOffButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				poweredOn = false;
				statusImage.setIcon(disconnectStatusImage);
				//and the arduino is connected
				if(arduinoConnected)
				{
					//send data 'd' to arduino to disable power
					byte buffer[] = {'d'};
					int bytesToWrite = buffer.length;
					serialPort.writeBytes(buffer, bytesToWrite);
				}
			}
		});
		
		powerOnButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				poweredOn = true;
				statusImage.setIcon(connectStatusImage);
				//and the arduino is connected
				
			}
		});
		
		
		exportDataButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				// create a new file
				try {
					//create a file, a workbook, and add the workbook to the file
					Workbook wb = new XSSFWorkbook();
					FileOutputStream reportFile = new FileOutputStream("reportFile.xlsx");
					Sheet sheet = wb.createSheet("Report");
					
					//create two rows that we'll work with
					Row row = sheet.createRow((short)0);
					Row row2 = sheet.createRow((short)2);
					
					//create the report label creation date
					row.createCell(5).setCellValue("Report Creation Date:");
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					Date date = new Date();
					//add the formatted date to the cell
					row.createCell(6).setCellValue(dateFormat.format(date));
					//size the cells to the width of the contents
					sheet.autoSizeColumn(5);
					sheet.autoSizeColumn(6);
					
					//merge the following cells together
					sheet.addMergedRegion(new CellRangeAddress(
				            2, //first row (0-based)
				            2, //last row  (0-based)
				            2, //first column (0-based)
				            5  //last column  (0-based)
				    ));
					
					row2.createCell(2).setCellValue("Load Shed Controller Report");
					CellStyle cellStyle = wb.createCellStyle();
					cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
					row2.getCell(2).setCellStyle(cellStyle);
					
					CellStyle borderStyle = wb.createCellStyle();
					borderStyle.setBorderBottom(CellStyle.BORDER_MEDIUM);
					borderStyle.setBorderLeft(CellStyle.BORDER_MEDIUM);
					borderStyle.setBorderRight(CellStyle.BORDER_MEDIUM);
					borderStyle.setBorderTop(CellStyle.BORDER_MEDIUM);
					
					row.getCell(5).setCellStyle(borderStyle);
					row.getCell(6).setCellStyle(borderStyle);
					
					
					    
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
				
				// create a new sheet

			}
		});
		
		
		logOffButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(tester != null)
				{
					tester.swapView("credentialPanel");
					tester.setSize(350, 400);
				}
			}
		});

	}
	
	public void launchExcelReport()
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
	
	
	public void setTester(Window_Credential tester)
	{
		this.tester = tester;
	}
}
