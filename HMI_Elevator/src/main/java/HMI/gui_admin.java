package HMI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.eclipse.paho.client.mqttv3.MqttException;


public class gui_admin extends Thread{

	private JFrame frame;
	JButton speed_down_1 = new JButton("speed_down_1");
	JButton speed_down_2 = new JButton("speed_down_2");
	JButton speed_up_1 = new JButton("speed_up_1");
	JButton speed_up_2 = new JButton("speed_up_2");
	JButton Reset=new JButton("Reset");
	static JLabel ledClosed = new JLabel("•");
	static JLabel ledOpened = new JLabel("•");
	static JLabel ledSpeed1 = new JLabel("•");
	static JLabel ledSpeed2 = new JLabel("•");

	/**
	 * Create the application.
	 */
	public gui_admin() {
		
	}
	
	static public void setColorLedSpeed1(boolean a) {
		if(a==true)
		{
			ledSpeed1.setForeground(Color.green);
		}else {
			ledSpeed1.setForeground(Color.red);
		}
	}
	
	static public void setColorLedSpeed2(boolean a) {
		if(a==true)
		{
			ledSpeed2.setForeground(Color.green);
		}else {
			ledSpeed2.setForeground(Color.red);
		}
	}
	
	static public void setColorLedOpened(boolean a) {
		if(a==true)
		{
			ledOpened.setForeground(Color.green);
		}else {
			ledOpened.setForeground(Color.red);
		}
	}
	
	static public void setColorLedClosed(boolean a) {
		if(a==true)
		{
			ledClosed.setForeground(Color.green);
		}else {
			ledClosed.setForeground(Color.red);
		}
	}
	
	public void initialize() {
		frame = new JFrame();
		frame.setTitle("Admin Control Panel");
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);

		speed_down_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		speed_down_2.setBounds(10, 10, 110, 52);
		speed_down_2.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					MQTT_HMI.sendMessage("reqV2", "0");

				} catch (MqttException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

				try {
					MQTT_HMI.sendMessage("reqV2", "down");

				} catch (MqttException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		frame.getContentPane().add(speed_down_2);

		speed_up_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		speed_up_2.setBounds(316, 10, 110, 52);
		speed_up_2.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				
				try {
					MQTT_HMI.sendMessage("reqV2", "0");

				} catch (MqttException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

				try {
					MQTT_HMI.sendMessage("reqV2", "up");

				} catch (MqttException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		frame.getContentPane().add(speed_up_2);

		speed_down_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		speed_down_1.setBounds(10, 72, 110, 52);
		speed_down_1.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				try {					

					MQTT_HMI.sendMessage("reqV1", "0");

				} catch (MqttException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			@Override
			public void mousePressed(MouseEvent e) {

				try {
					
					MQTT_HMI.sendMessage("reqV1", "down");

				} catch (MqttException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		frame.getContentPane().add(speed_down_1);

		speed_up_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		speed_up_1.setBounds(316, 72, 110, 52);
		speed_up_1.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					MQTT_HMI.sendMessage("reqV1", "0");

				} catch (MqttException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			@Override
			public void mousePressed(MouseEvent e) {

				try {
					MQTT_HMI.sendMessage("reqV1", "up");

				} catch (MqttException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		frame.getContentPane().add(speed_up_1);
		
		JButton close = new JButton("CLOSE");
		close.setFont(new Font("Tahoma", Font.PLAIN, 12));
		close.setBounds(10, 134, 110, 52);
		close.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				
				try {
					MQTT_HMI.sendMessage("reqDoorFine", "0");
				} catch (MqttException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


			}

			@Override
			public void mousePressed(MouseEvent e) {

				try {
					MQTT_HMI.sendMessage("reqDoorFine", "close");
				} catch (MqttException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		frame.getContentPane().add(close);
		
		JButton open = new JButton("OPEN");
		open.setFont(new Font("Tahoma", Font.PLAIN, 12));
		open.setBounds(316, 134, 110, 52);
		open.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				
				try {
					MQTT_HMI.sendMessage("reqDoorFine", "0");
				} catch (MqttException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			@Override
			public void mousePressed(MouseEvent e) {

				try {
					MQTT_HMI.sendMessage("reqDoorFine", "open");
				} catch (MqttException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		
		Reset.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Reset.setBounds(184, 209, 85, 44);
		Reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					MQTT_HMI.sendMessage("reqReset", "1");
				} catch (MqttException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		frame.getContentPane().add(Reset);
		
		frame.getContentPane().add(open);
		
		
		ledClosed.setFont(new Font("Tahoma", Font.PLAIN, 130));
		ledClosed.setBounds(35, 180, 110, 70);
		ledClosed.setForeground(Color.green);
		frame.getContentPane().add(ledClosed);
		
		
		ledOpened.setFont(new Font("Tahoma", Font.PLAIN, 130));
		ledOpened.setBounds(345, 180, 110, 70);
		ledOpened.setForeground(Color.red);
		frame.getContentPane().add(ledOpened);
		
		
		ledSpeed1.setFont(new Font("Tahoma", Font.PLAIN, 130));
		ledSpeed1.setBounds(190, 55, 110, 70);
		ledSpeed1.setForeground(Color.red);
		frame.getContentPane().add(ledSpeed1);
		
		
		ledSpeed2.setFont(new Font("Tahoma", Font.PLAIN, 130));
		ledSpeed2.setBounds(190, -5, 110, 70);
		ledSpeed2.setForeground(Color.red);
		frame.getContentPane().add(ledSpeed2);
		
		frame.setVisible(true);
	}
	@Override
	public void run() {
		initialize();
	}
	
	
	
	
}
