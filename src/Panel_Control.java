import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import users.User;
import users.UserList;

import com.fazecast.jSerialComm.SerialPort;

import data.DataList;
import data.Report_Excel;


public class Panel_Control extends JPanel 
{
	
	//label for the status icon
	private JLabel statusIcon;
	
	//images used for the buttons
	private ImageIcon disconnectStatusImage;
	private ImageIcon connectStatusImage;
	private ImageIcon smallFormImage;
	private ImageIcon smallSensorImage;
	private ImageIcon smallLogOutImage;
	private ImageIcon smallLockImage;
	private ImageIcon smallPeopleImage;
	
	//lists of employees, action instances, and the current user
	private UserList employeeList;
	private DataList dataList;
	private User currentUser;
	
	
	private boolean arduinoConnected;
	private SerialPort serialPort;
	private Window_Credential tester;
	private boolean isPoweredOn;
	private Border emptyBorder = BorderFactory.createEmptyBorder();
	
	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
	
	SerialPort arduinoPort;
	
	//-----------------------------------------------------------------
	//---------------------------GUI SETUP-----------------------------
	//-----------------------------------------------------------------
	
	public Panel_Control(final UserList employeeList, final DataList dataList, final User currentUser, final boolean poweredOn) 
	{
		//intializes passed arguments
		this.employeeList = employeeList;
		this.dataList = dataList;
		this.currentUser = currentUser;
		this.isPoweredOn = poweredOn;
		
		setBackground(Color.WHITE);
		setLayout(null);
		
		//image for status
		URL url1 = getClass().getResource("status_good.png");
		URL url2 = getClass().getResource("status_bad.png");
		URL url3 = getClass().getResource("sensorIconSmall.png");
		URL url4 = getClass().getResource("formIconSmall.png");
		URL url5 = getClass().getResource("logOutIconSmall.png");
		URL url6 = getClass().getResource("lockIconSmall.png");
		URL url7 = getClass().getResource("peopleicon_small.png");
		
		connectStatusImage = new ImageIcon(url1);
		disconnectStatusImage = new ImageIcon(url2);
		smallSensorImage = new ImageIcon(url3);
		smallFormImage = new ImageIcon(url4);
		smallLogOutImage = new ImageIcon(url5);
		smallLockImage = new ImageIcon(url6);
		smallPeopleImage = new ImageIcon(url7);
		
		if(isPoweredOn)
			statusIcon = new JLabel(connectStatusImage);
		else
			statusIcon = new JLabel(disconnectStatusImage);
		
		//label for the status
		JLabel statusLabel = new JLabel("Status");
		statusLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		statusLabel.setBounds(24, 32, 86, 65);
		add(statusLabel);
		
		//label for the icon
		statusIcon.setBounds(120, 11, 65, 86);
		add(statusIcon);
		
		//button for creating a report
		JButton reportButton = new JButton("Create Report");
		reportButton.setIcon(smallFormImage);
		reportButton.setForeground(Color.BLACK);
		reportButton.setBorder(emptyBorder);
		reportButton.setBackground(Color.WHITE);
		reportButton.setFont(new Font("Calibri", Font.BOLD, 16));
		reportButton.setBounds(35, 160, 147, 47);
		add(reportButton);
		
		//button for monitoring the system
		JButton monitorButton = new JButton("Sensor Data");
		
		monitorButton.setIcon(smallSensorImage);
		monitorButton.setFont(new Font("Calibri", Font.BOLD, 16));
		monitorButton.setBorder(emptyBorder);
		monitorButton.setForeground(Color.BLACK);
		monitorButton.setBackground(Color.WHITE);
		monitorButton.setBounds(283, 160, 136, 47);
		add(monitorButton);
		
		//button for logging off
		JButton logOffButton = new JButton("Log Off");
		logOffButton.setIcon(smallLogOutImage);
		logOffButton.setFont(new Font("Calibri", Font.BOLD, 16));
		logOffButton.setForeground(Color.BLACK);
		logOffButton.setBorder(emptyBorder);
		logOffButton.setBackground(Color.WHITE);
		logOffButton.setBounds(27, 241, 119, 47);
		add(logOffButton);
		
		//button for adding a user
		JButton changePasswordButton = new JButton("Change Password");
		changePasswordButton.setIcon(smallLockImage);
		changePasswordButton.setFont(new Font("Calibri", Font.BOLD, 16));
		changePasswordButton.setForeground(Color.BLACK);
		changePasswordButton.setBorder(emptyBorder);
		changePasswordButton.setBackground(Color.WHITE);
		changePasswordButton.setBounds(275, 241, 194, 47);
		add(changePasswordButton);
		
		//button for powering on the air conditioning unit
		JButton powerOnButton = new JButton("Power Load On");
		powerOnButton.setFont(new Font("Calibri", Font.BOLD, 18));
		powerOnButton.setBorder(emptyBorder);
		powerOnButton.setBackground(Color.decode("#98ff98"));
		powerOnButton.setBounds(35, 333, 147, 47);
		add(powerOnButton);
		
		//button for shutting down the air conditioning unit
		JButton powerOffButton = new JButton("Power Load Off");
		powerOffButton.setFont(new Font("Calibri", Font.BOLD, 18));
		powerOffButton.setBorder(emptyBorder);
		powerOffButton.setBackground(Color.decode("#ff6666"));
		powerOffButton.setBounds(283, 333, 147, 47);
		add(powerOffButton);
		
		JButton modifyUsersButton = new JButton("Modify Users");
		modifyUsersButton.setIcon(smallPeopleImage);
		modifyUsersButton.setBorder(emptyBorder);
		modifyUsersButton.setBackground(Color.WHITE);
		modifyUsersButton.setFont(new Font("Calibri", Font.BOLD, 16));
		modifyUsersButton.setBounds(269, 32, 178, 65);
		add(modifyUsersButton);
		
		JLabel arduinoConnectedLabel = new JLabel("Arduino Disconnected");
		arduinoConnectedLabel.setForeground(Color.decode("#ff6666"));
		arduinoConnectedLabel.setBounds(172, 425, 136, 14);
		add(arduinoConnectedLabel);
		
		//if the user is administrator, make the button visible and active
		if(!currentUser.getUserType().equalsIgnoreCase("administrator"))
		{
			modifyUsersButton.setVisible(false);
			modifyUsersButton.enable(false);
		}
		
		//-----------------------------------------------------------------
		//--------------------ARDUINO SERIAL SETUP-------------------------
		//-----------------------------------------------------------------
						
			    
	    //find which port belongs to the Arduino
		//incrementer for different ports
		int i = 0;
		//check whether the arduino is connected
		arduinoConnected = false;
		arduinoPort = null;
		//get the ports on the computer
		SerialPort[] ports = SerialPort.getCommPorts();
		//will display the ports we have
		System.out.println("To display the ports that we have available: ");
		for(SerialPort port : ports)
		{
			System.out.println(port.getSystemPortName() + "\n");
			if(port.getSystemPortName().equalsIgnoreCase("COM3") || port.getSystemPortName().equalsIgnoreCase("COM4")
					|| port.getSystemPortName().equalsIgnoreCase("COM5") || port.getSystemPortName().equalsIgnoreCase("COM14"))
			{
				//if the port name is COM3 then the Arduino is connected
				arduinoConnected = true;
				arduinoConnectedLabel.setText("Arduino Connected");
				arduinoConnectedLabel.setForeground(Color.green);
				this.arduinoPort = ports[i];
			}
			//increment the ports if any
			i++;
		}
		
		
		//if the arduino is connected do the following
		if(arduinoConnected)
		{
			serialPort = arduinoPort;
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
		
		//classes needed for switching windows
		
		//-----------------------------------------------------------------
		//---------------------------BUTTON ACTION-------------------------
		//-----------------------------------------------------------------
		
		//SWITCHING TO "MODIFY USER" PANEL
		modifyUsersButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				if(tester != null)
				{
					Panel_UserSelection userSelectionPanel = new Panel_UserSelection(employeeList, currentUser);
					userSelectionPanel.setTester(tester);
					tester.getCards().add(userSelectionPanel, "userModPanel");
					tester.swapView("userModPanel");
					tester.setSize(450, 320);
				}
			}
		});
				
				
		monitorButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				//closePort();
				if(tester != null)
				{
					Panel_Monitor monitorPanel = monitorPanel = new Panel_Monitor(arduinoPort);
					monitorPanel.setTester(tester);
					tester.getCards().add(monitorPanel, "monitorPanel");
					tester.swapView("monitorPanel");
					tester.setSize(450, 320);
				}
			}
		});
		
		//create the excel report
		reportButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Report_Excel report = new Report_Excel();
				report.createExcelReport(employeeList, dataList);
			}
		});
		
		//change your password interface
		changePasswordButton.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent e) 
			{
				
				if(tester != null)
				{
					Panel_PasswordModifier passwordPanel = new Panel_PasswordModifier(employeeList, currentUser);
					passwordPanel.setTester(tester);
					tester.getCards().add(passwordPanel, "passwordPanel");
					tester.swapView("passwordPanel");
					tester.setSize(455, 400);
				}
			}
		});
		
		//log off and go back to the credential window
		logOffButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(tester != null)
				{
					closePort();
					tester.setPoweredOnValue(isPoweredOn);
					tester.swapView("credentialPanel");
					tester.setSize(350, 400);
				}
			}
		});
		
		/*--------------------------------
		 * SEND POWER ON SIGNAL TO ARDUINO
		--------------------------------*/
		powerOnButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(!isPoweredOn)
				{
					//change the poweredOn value to true and change icon
					isPoweredOn = true;
					statusIcon.setIcon(connectStatusImage);
					
					//increase the powerOn count and create an instance for reporting
					dataList.incrementPowerOnCount();
					Date date = new Date();
					dataList.addInstance(currentUser.getName(), "Power On", date);
					
					//if the arduino is connected then send signal
					if(arduinoConnected)
					{
						//send data 'd' to arduino to disable power
						byte buffer[] = {'d'};
						int bytesToWrite = buffer.length;
						serialPort.writeBytes(buffer, bytesToWrite);
					}
				}
				
			}
		});
		
		/*--------------------------------
		 * SEND POWER OFF SIGNAL TO ARDUINO
		--------------------------------*/
		powerOffButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//if item is powered on
				if(isPoweredOn)
				{
					//set isPoweredOn to false
					isPoweredOn = false;
					tester.setPoweredOnValue(isPoweredOn);
					statusIcon.setIcon(disconnectStatusImage);
					
					dataList.incrementShutOffCount();
					Date date = new Date();
					dataList.addInstance(currentUser.getName(), "Power Off", date);
					
				
					//if the arduino is connected then send signal
					if(arduinoConnected)
					{
						//send data 'd' to arduino to disable power
						byte buffer[] = {'e'};
						int bytesToWrite = buffer.length;
						serialPort.writeBytes(buffer, bytesToWrite);
					}
				}
			}
		});

	}
	
	//close the port so that it's no longer used by the program.
	private void closePort()
	{
		if(arduinoConnected)
		{
			serialPort.closePort();
			System.out.println("Port Closed poop");
		}
	}
	//allows for switching between cards
	public void setTester(Window_Credential tester)
	{
		this.tester = tester;
	}
}
