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
	//runs the GUI, original code commented out
	public static void main (String args[])
	{
		Window_Credential initialView = new Window_Credential();
		//Window_Control control = new Window_Control();
	}
}
