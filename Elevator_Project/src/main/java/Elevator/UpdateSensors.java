package Elevator;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.json.simple.JSONObject;

import de.re.easymodbus.exceptions.ModbusException;

public class UpdateSensors extends Thread {
	private static JSONObject obj;

	public UpdateSensors() throws FileNotFoundException, IOException {
		obj=new JSONObject(JsonDecoder_CONTROLLER.decodeJsonInit());
	}
	

	private static void sendValues()
			throws IOException, ModbusException, InterruptedException, MqttPersistenceException, MqttException {
		
		System.out.println(obj);
		MQTT_CONTROLLER.sendMessage(obj);
	}

	@SuppressWarnings("unchecked")
	public static void updateSensor(String key, String value) {
		obj.put(key, value);
	}

	public static JSONObject getJSONUpdate() {
		return obj;
	}

	@Override
	public void run() {
		try {
			while (true) {
				sendValues();
				Thread.sleep(500);
			}
		} catch (IOException | ModbusException | InterruptedException | MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
