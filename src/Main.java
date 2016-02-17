import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.fazecast.jSerialComm.SerialPort;
public class Main extends JFrame
{
	private Container contents;
	private JButton greenButton, redButton, blueButton;
	private JPanel grid;
	private SerialPort serialPort;
	private boolean arduinoConnected;
	private boolean hostIsSurface = true;
	
	public Main ()
	{
		//---------------CREATE THE LAYOUT FOR THE BUTTONS--------------------
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		greenButton = new JButton("Turn Green On");
		greenButton.setForeground(Color.black);
		greenButton.setBackground(Color.decode("#bfff80"));
	    
		redButton = new JButton("Turn Red On");
		redButton.setForeground(Color.white);
		redButton.setBackground(Color.decode("#ff6666"));
		
		blueButton = new JButton("Turn Blue On");
		blueButton.setForeground(Color.white);
		blueButton.setBackground(Color.decode("#7ec0ee"));
		
		JPanel buttonGrid = new JPanel(new GridLayout(3, 1));
	    buttonGrid.add(greenButton);
	    buttonGrid.add(redButton);
	    buttonGrid.add(blueButton);
	    
	    c.gridx = 0;
	    c.gridy = 4;
	    add(buttonGrid, c);
	    
	    setBackground(Color.white);
	    setSize(200, 200);
	    setVisible(true);
	    
	    //------------------SET THE SERIAL WRITER UP--------------------
	    
	    //check whether the port is open
	    boolean portOpen = false;
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
			//For Ricky - COM4 if Surface, COM3 if Dell Laptop
			if((hostIsSurface && port.getSystemPortName().equalsIgnoreCase("COM4")) ||
					port.getSystemPortName().equalsIgnoreCase("COM4"))
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
		
		//if you press the green button
		greenButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//and the arduino is connected
				if(arduinoConnected)
				{
					//send data 'g' to the arduino where it will ignite the pin to light up green
					byte buffer[] = {'g'};
					int bytesToWrite = buffer.length;
					serialPort.writeBytes(buffer, bytesToWrite);
				}
			}
		});
		
		//same concept as green
		blueButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(arduinoConnected)
				{
					byte buffer[] = {'b'};
					int bytesToWrite = buffer.length;
					serialPort.writeBytes(buffer, bytesToWrite);
				}
			}
		});
		
		//same concept as blue
		redButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(arduinoConnected)
				{
					byte buffer[] = {'r'};
					int bytesToWrite = buffer.length;
					serialPort.writeBytes(buffer, bytesToWrite);
				}
			}
		});
		
		//close the operations if we hit the 'X' on the window
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	}
	
	//runs the GUI, original code commented out
	public static void main (String args[])
	{
		Main mainView = new Main();
		/*boolean arduinoConnected = false;
		int arduinoPort = 0;
		int i = 0;
		
		SerialPort[] ports = SerialPort.getCommPorts();
		System.out.println("To display the ports that we have available: ");
		for(SerialPort port : ports)
		{
			System.out.println(port.getSystemPortName() + "\n");
			if(port.getSystemPortName().equalsIgnoreCase("COM3"))
			{
				arduinoConnected = true;
				arduinoPort = i;
			}
			i++;
		}
		
		if(arduinoConnected)
		{
			SerialPort serialPort = ports[arduinoPort];
			//check whether port can open
			if(serialPort.openPort())
				System.out.println("Port opened successfully.");
			else 
			{
				System.out.println("Unable to open the port.");
				return;
			}
			
		
			byte buffer[] = {'n'};
			
			int bytesToWrite = buffer.length;
			
			serialPort.writeBytes(buffer, bytesToWrite);
			
		}*/

	}
}
