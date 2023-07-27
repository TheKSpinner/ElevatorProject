package Elevator;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

import de.re.easymodbus.exceptions.ModbusException;



public class test {
	
	public static void main(String[] args) throws UnknownHostException, SocketException, ModbusException, IOException,
			InterruptedException, MqttSecurityException, MqttException {
		
		
		
		//connect to modbus
		modbus.connect();
		
		//reset the simulation
		modbus.reset();
		
		//wait for the simulation to initialize
		TimeUnit.SECONDS.sleep(2);
		
		//Connect to the mqtt to send data to the HMI
		MQTT_CONTROLLER client=new MQTT_CONTROLLER();
		client.start();
		System.out.println("Mqtt connected");
		
		//Start Sensor updater 
		UpdateSensors runner_sensors=new UpdateSensors();
		runner_sensors.start();
		System.out.println("Sensor monitor started");		
		
		//Initialize the DataStorage map
		DataStorage_CONTROLLER.init();
		System.out.println("Data storage initialized");
		
		//Arm the emergency button
		emergency runner_sos=new emergency();
		runner_sos.start();
		System.out.println("Emergency button armed");
		
		//Start json monitor
		JSON_Monitoring_CONTROLLER runner = new JSON_Monitoring_CONTROLLER();
		runner.start();
		System.out.println("JSON Monitor started");
		

	}

}