import com.fazecast.jSerialComm.*;
public class Main 
{
	public static void main (String args[])
	{
		boolean arduinoConnected = false;
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
			
			
			
		}
		
		
	}
}
