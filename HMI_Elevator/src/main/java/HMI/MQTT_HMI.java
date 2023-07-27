package HMI;

import java.util.Map;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.json.simple.JSONObject;


public class MQTT_HMI extends Thread implements Runnable{
	public static MqttClient client;
	private static String USERNAME = "C4";
	private static String PASSWORD = "E00";
	private static String topic_recieve = "/23SS-SysArch/controller_to_HMI_C4";
    private static String topic_send = "/23SS-SysArch/HMI_to_controller_C4";
	
	public MQTT_HMI() throws MqttException {
		connect();
	}
	
	public static void connect()  {
		try {
			client = new MqttClient("tcp://mqtt-dashboard.com:1883", "Interfacing",null);
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//MqttConnectOptions connOpts = setUpConnectionOptions(USERNAME, PASSWORD);
		try {
			//client.connect(connOpts);
			client.connect();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			client.subscribe(topic_recieve);
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static MqttConnectOptions setUpConnectionOptions(String username, String password) {
		MqttConnectOptions connOpts = new MqttConnectOptions();
		connOpts.setCleanSession(true);
		connOpts.setUserName(username);
		connOpts.setPassword(password.toCharArray());
		return connOpts;
	}
	
	@SuppressWarnings("unused")
	public static void sendMessage(String Key, String Value) throws MqttPersistenceException, MqttException {
		
		
		JSONObject obj = JsonDecoder_HMI.JSONWriter(Key, Value);
		String json = obj.toString();
		MqttMessage mqttMessage = new MqttMessage(json.getBytes());
		client.publish(topic_send, mqttMessage);
		

	}
	
	/*public static void messageArrived(String topic, MqttMessage message) throws Exception {
		String jsonPayload = new String(message.getPayload());

		Map<String, String> decodedData = JsonDecoder_HMI.decodeJson(jsonPayload);

		DataStorage_HMI.setDecodedData(decodedData);
		System.out.println(decodedData);
		System.out.println("MESSAGE RECIEVED");
	}*/
	
	public static void recieveMessage() {

		try {
			client.setCallback(new MqttCallback() {
				public void messageArrived(String topic, MqttMessage message) {
					String jsonPayload = new String(message.getPayload());
					Map<String, String> decodedData = JsonDecoder_HMI.decodeJson(jsonPayload);
					DataStorage_HMI.setDecodedData(decodedData);
					System.out.println(decodedData);
				}

				@Override
				public void connectionLost(Throwable cause) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void deliveryComplete(IMqttDeliveryToken token) {
					// TODO Auto-generated method stub
					
				}
			});
		} catch (Exception e) {

		}
	}
	
	@Override
	public void run() {
		while(true) {
			recieveMessage();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
