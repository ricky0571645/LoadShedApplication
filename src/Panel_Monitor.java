import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import com.fazecast.jSerialComm.SerialPort;

import javax.swing.JButton;


public class Panel_Monitor extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private SerialPort serialPort;
	private Window_Credential tester;
	
	public Panel_Monitor(SerialPort arduinoPort) 
	{
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel lblBoxConnected = new JLabel("Box Connected:");
		lblBoxConnected.setFont(new Font("Calibri", Font.BOLD, 16));
		lblBoxConnected.setBounds(158, 145, 115, 14);
		add(lblBoxConnected);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Ricky Martinez\\Documents\\GitHub\\LoadShedApplication\\images\\sensorIcon.png"));
		label.setBounds(182, 26, 85, 85);
		add(label);
		
		JLabel lblNewLabel = new JLabel("n/a");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		lblNewLabel.setBounds(273, 145, 79, 14);
		add(lblNewLabel);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Calibri", Font.BOLD, 18));
		cancelButton.setBackground(Color.decode("#ff6666"));
		cancelButton.setBounds(180, 229, 89, 29);
		add(cancelButton);
		
		
		cancelButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(tester != null)
				{
					tester.swapView("controlPanel");
					tester.setSize(510, 480);
				}
			}
		});
		
//		// determine which serial port to use
//		SerialPort ports[] = SerialPort.getCommPorts();
//        System.out.println("Select a port:");
//        int i = 1;
//        for(SerialPort port : ports) {
//                System.out.println(i++ + ". " + port.getSystemPortName());
//        }
//        Scanner s = new Scanner(System.in);
//        int chosenPort = s.nextInt();
//
//        // open and configure the port
//        SerialPort port = ports[chosenPort - 1];
//        if(port.openPort()) {
//                System.out.println("Successfully opened the port.");
//        } else {
//                System.out.println("Unable to open the port.");
//                return;
//        }
//        port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
       
        // enter into an infinite loop that reads from the port and updates the GUI
		if(arduinoPort != null)
        {
			Scanner data = new Scanner(arduinoPort.getInputStream());
			while(data.hasNextLine()) 
	        {
	            lblBoxConnected.setText(data.nextLine());
	        }
        }
        
        //see if you can read in values
        

	}
	
	//alows for switching between cards
		public void setTester(Window_Credential tester)
		{
			this.tester = tester;
		}
}
