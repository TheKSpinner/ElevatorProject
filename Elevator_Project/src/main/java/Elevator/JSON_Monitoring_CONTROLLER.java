package Elevator;

import java.io.IOException;
import java.util.Map;

import de.re.easymodbus.exceptions.ModbusException;

public class JSON_Monitoring_CONTROLLER extends Thread{

	private static void checkValueChanges(Map<String, String> currentMap, Map<String, String> previousMap) throws IOException, ModbusException, InterruptedException {
		// Iterate over the map's entries and compare values
		for (Map.Entry<String, String> entry : currentMap.entrySet()) {
			String key = entry.getKey();
			Object currentValue = entry.getValue();
			Object previousValue = previousMap.get(key);

			// Compare the current and previous values
			if (!currentValue.equals(previousValue)) {
				switch (entry.getKey()) {
				case "reqLevel1":
					if (currentValue.equals("1")) {
						Methods.GetToFloor(1);
					} else {
						//nothing
					}
					break;
				case "reqLevel2":
					if (currentValue.equals("1")) {
						Methods.GetToFloor(2);
					} else {
						//nothing
					}
					break;
				case "reqLevel3":
					if (currentValue.equals("1")) {
						Methods.GetToFloor(3);
					} else {
						//nothing
					}
					break;
				case "reqLevel4":
					if (currentValue.equals("1")) {
						Methods.GetToFloor(4);
					} else {
						//nothing
					}
					break;
				case "reqEmergencyStop":
					if (currentValue.equals("1")) {
						emergency.sos();
					}
					break;
				case "reqReset":
					if (currentValue.equals("1")) {
						modbus.reset();
					} else {
						//nothing
					}
					break;
				case "reqV1":
					if (currentValue.equals("down")) {
						Methods.downOn(1);
					} else if(currentValue.equals("up")){
							Methods.upOn(1);
					} else {
						Methods.upOff();
						Methods.downOff();
					}
					break;
				case "reqV2":
					if (currentValue.equals("down")) {
						Methods.downOn(2);
					} else if(currentValue.equals("up")){
							Methods.upOn(2);
					} else {
						Methods.upOff();
						Methods.downOff();
					}
					break;
				case "reqDoor":
					if (currentValue.equals("open")) {
						Methods.OpenDoors();
					} else if (currentValue.equals("close")) {
						Methods.CloseDoors();
					} else {
						Methods.stopDoors();
					}
					break;

				
				case "reqDoorFine":
					if (currentValue.equals("open")) {
						modbus.getClient().WriteSingleCoil(registries.MOTOR_OPEN, true);
					} else if (currentValue.equals("close")) {
						modbus.getClient().WriteSingleCoil(registries.MOTOR_CLOSE, true);
					} else {
						modbus.getClient().WriteSingleCoil(registries.MOTOR_OPEN, false);;
						modbus.getClient().WriteSingleCoil(registries.MOTOR_CLOSE, false);
					}
					break;
				}
			}
		}
		// Update the previous map with the current map for the next iteration
		previousMap.clear();
		previousMap.putAll(currentMap);

	}
	
	@Override
	public void run() {
		try {
			while(true) {
				checkValueChanges(DataStorage_CONTROLLER.decodedData, DataStorage_CONTROLLER.oldDecodedData);
				Thread.sleep(500);
			}
		} catch (IOException | ModbusException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
