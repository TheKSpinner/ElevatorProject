package HMI;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.plaf.synth.SynthOptionPaneUI;

import org.eclipse.paho.client.mqttv3.MqttException;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;

public class gui_user {

	private static final long serialVersionUID = 1L;
	private JFrame frmElevator;
	private JPasswordField passwordField;
	private static JTextField textField;

	public gui_user() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmElevator = new JFrame();

		frmElevator.setTitle("Elevator");
		frmElevator.setBounds(100, 100, 450, 420);
		frmElevator.getContentPane().setLayout(null);
		frmElevator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 70));
		textField.setText("1");

		textField.setBackground(new Color(240, 240, 240));
		textField.setBounds(356, 30, 45, 75);
		frmElevator.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setEditable(false);
		
		JButton Level1 = new JButton("1");
		Level1.setFont(new Font("Tahoma", Font.PLAIN, 50));
		Level1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MQTT_HMI.sendMessage("reqLevel1", "1");
				} catch (MqttException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		Level1.setBounds(30, 30, 85, 75);
		frmElevator.getContentPane().add(Level1);

		JButton Level2 = new JButton("2");
		Level2.setFont(new Font("Tahoma", Font.PLAIN, 50));
		Level2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MQTT_HMI.sendMessage("reqLevel2", "1");
				} catch (MqttException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		Level2.setBounds(177, 30, 85, 75);
		frmElevator.getContentPane().add(Level2);

		JButton Level3 = new JButton("3");
		Level3.setFont(new Font("Tahoma", Font.PLAIN, 50));
		Level3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MQTT_HMI.sendMessage("reqLevel3", "1");
				} catch (MqttException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		Level3.setBounds(30, 147, 85, 75);
		frmElevator.getContentPane().add(Level3);

		JButton Level4 = new JButton("4");
		Level4.setFont(new Font("Tahoma", Font.PLAIN, 50));
		Level4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MQTT_HMI.sendMessage("reqLevel4", "1");
				} catch (MqttException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		Level4.setBounds(177, 147, 85, 75);
		frmElevator.getContentPane().add(Level4);

		passwordField = new JPasswordField();
		passwordField.setBounds(341, 191, 80, 19);
		frmElevator.getContentPane().add(passwordField);
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (String.valueOf(passwordField.getPassword()).substring(0, 8).equals("admin123")) {
					gui_admin admin = new gui_admin();
					admin.start();
				}
			}
		});

		JLabel lblNewLabel = new JLabel("Admin Pass");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(341, 162, 80, 19);
		frmElevator.getContentPane().add(lblNewLabel);

		

		JButton Open_Doors = new JButton("OPEN");
		Open_Doors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MQTT_HMI.sendMessage("reqDoor", "open");
				} catch (MqttException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		Open_Doors.setFont(new Font("Tahoma", Font.PLAIN, 21));
		Open_Doors.setBounds(30, 267, 85, 75);
		frmElevator.getContentPane().add(Open_Doors);

		JButton Close_Doors = new JButton("CLOSE");
		Close_Doors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MQTT_HMI.sendMessage("reqDoor", "close");
				} catch (MqttException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		Close_Doors.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Close_Doors.setBounds(177, 267, 85, 75);
		frmElevator.getContentPane().add(Close_Doors);

		JButton SOS = new JButton("SOS");
		SOS.setBackground(new Color(255, 0, 0));
		SOS.setFont(new Font("Tahoma", Font.PLAIN, 17));
		SOS.setBounds(336, 267, 85, 75);
		SOS.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					MQTT_HMI.sendMessage("reqEmergencyStop", "1");
				} catch (MqttException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		frmElevator.getContentPane().add(SOS);
		frmElevator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmElevator.setVisible(true);

	}

	

	public static void setFloor(String s) {
		textField.setText(s);
	}

}
