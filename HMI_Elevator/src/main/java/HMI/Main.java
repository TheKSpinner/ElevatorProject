package HMI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args)
			throws MqttSecurityException, MqttException, FileNotFoundException, IOException, InterruptedException {

		// MQTT_HMI_TO_CONTROLLER.sendMessage();
		System.out.println("Trying to connect to MQTT");
		MQTT_HMI mqttHMI = new MQTT_HMI();
		mqttHMI.start();
		System.out.println("Mqtt connected");

		// start hmi gui_user
		gui_user runner_gui = new gui_user();
		System.out.println("GUI started");
		
		// Storage initialization DataStorage_HMI.init();
		DataStorage_HMI.init();
		System.out.println("Storage initialized");

		// start json monitor
		JSON_Monitoring_HMI runner = new JSON_Monitoring_HMI();
		runner.start();
		System.out.println("JSON monitor started");

	}

}
