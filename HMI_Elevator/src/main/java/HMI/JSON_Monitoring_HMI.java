package HMI;

import java.io.IOException;
import java.util.Map;

import de.re.easymodbus.exceptions.ModbusException;

public class JSON_Monitoring_HMI extends Thread{

	

	private static void checkValueChanges(Map<String, String> currentMap, Map<String, String> previousMap) throws IOException, ModbusException {
		// Iterate over the map's entries and compare values
		for (Map.Entry<String, String> entry : currentMap.entrySet()) {
			String key = entry.getKey();
			Object currentValue = entry.getValue();
			Object previousValue = previousMap.get(key);

			// Compare the current and previous values
			if (!currentValue.equals(previousValue)) {
				switch (entry.getKey()) {
				case "level1":
					if (currentValue.equals("1")) {
						gui_user.setFloor("1");
					} else {

					}
					break;
				case "level2":
					System.out.println("CASE 2");
					if (currentValue.equals("1")) {
						System.out.println("SETEZ FLOOR 2");
						gui_user.setFloor("2");
					} else {

					}
					break;
				case "level3":
					if (currentValue.equals("1")) {
						gui_user.setFloor("3");
					} else {

					}
					break;
				case "level4":
					if (currentValue.equals("1")) {
						gui_user.setFloor("4");
					} else {

					}
					break;
				case "speedV1":
					if (currentValue.equals("down")) {
						gui_admin.setColorLedSpeed1(true);
						gui_admin.setColorLedSpeed2(false);
					} else if(currentValue.equals("up")){
						gui_admin.setColorLedSpeed1(true);
						gui_admin.setColorLedSpeed2(false);
					} else {
						gui_admin.setColorLedSpeed1(false);
						gui_admin.setColorLedSpeed2(false);
					}
					break;
				case "speedV2":
					if (currentValue.equals("down")) {
						gui_admin.setColorLedSpeed1(false);
						gui_admin.setColorLedSpeed2(true);
					} else if(currentValue.equals("up")){
						gui_admin.setColorLedSpeed1(false);
						gui_admin.setColorLedSpeed2(true);
					} else {
						gui_admin.setColorLedSpeed2(false);
						gui_admin.setColorLedSpeed1(false);
					}
					break;
				case "doorState":
					if (currentValue.equals("open")) {
						gui_admin.setColorLedOpened(true);
						gui_admin.setColorLedClosed(false);
					} else if (currentValue.equals("closed")) {
						gui_admin.setColorLedClosed(true);
						gui_admin.setColorLedOpened(false);
					} else {
						gui_admin.setColorLedOpened(false);
						gui_admin.setColorLedClosed(false);
					}
					break;

				
				case "error":
					if(currentValue.equals("1")) {
						//show error state in admin
					}else
					{
						//nothing
					}
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
				checkValueChanges(DataStorage_HMI.decodedData, DataStorage_HMI.oldDecodedData);
				Thread.sleep(500);
			}
		} catch (IOException | ModbusException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
