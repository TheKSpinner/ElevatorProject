package Elevator;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import de.re.easymodbus.exceptions.ModbusException;


public class Methods {
	// ------------------BASIC MOVEMENT FUNCTIONS
	/*public static void crawl(int speed) throws UnknownHostException, SocketException, ModbusException, IOException {
		modbus.getClient().WriteSingleRegister(registries.MOTOR_CRAWL_SPEED, speed);
	}*/

	public static void upOn(int speed) throws IOException, ModbusException {
		switch (speed) {
		case 1:
			UpdateSensors.updateSensor("speedV1", "up");
			
			modbus.getClient().WriteSingleCoil(registries.MOTOR_UP_V1, true);
			break;
		case 2:
			UpdateSensors.updateSensor("speedV2", "up");
			modbus.getClient().WriteSingleCoil(registries.MOTOR_UP_V2, true);
			break;
		default:
			break;

		}

	}

	public static void upOff() throws IOException, ModbusException {
		modbus.getClient().WriteSingleCoil(registries.MOTOR_UP_V1, false);
		modbus.getClient().WriteSingleCoil(registries.MOTOR_UP_V2, false);
		if(UpdateSensors.getJSONUpdate()!=null) {
			UpdateSensors.updateSensor("speedV1", "0");
			UpdateSensors.updateSensor("speedV2", "0");
			//crawl(0);
		}
		
		
	}

	public static void downOn(int speed) throws IOException, ModbusException {
		switch (speed) {
		case 1:
			modbus.getClient().WriteSingleCoil(registries.MOTOR_DOWN_V1, true);
			UpdateSensors.updateSensor("speedV1", "down");
			break;
		case 2:
			modbus.getClient().WriteSingleCoil(registries.MOTOR_DOWN_V2, true);
			UpdateSensors.updateSensor("speedV2", "down");
			break;
		default:
			break;

		}

	}

	public static void downOff() throws IOException, ModbusException {
		modbus.getClient().WriteSingleCoil(registries.MOTOR_DOWN_V1, false);
		modbus.getClient().WriteSingleCoil(registries.MOTOR_DOWN_V2, false);
		//crawl(0);
		if(UpdateSensors.getJSONUpdate()!=null) {
			UpdateSensors.updateSensor("speedV1", "0");
			UpdateSensors.updateSensor("speedV2", "0");
			//crawl(0);
		}
	}

	// ------------------COMPLEX MOVEMENT FUNCTION
	
	public static void GetToFloor(int floor) throws IOException, ModbusException, InterruptedException {
		// ------------------GOING TO FIRST(1) FLOOR
		if (floor == 1) {
			DataStorage_CONTROLLER.modifyValue("reqLevel1", "0");
			
			if (sensorMethods.level2reached() == true || sensorMethods.level3reached() == true || sensorMethods.level4reached() == true) {
				if (sensorMethods.isOpen() == true) {
					CloseDoors();
				}

				if (sensorMethods.LVL2Approch1() == false) {
					Methods.downOn(2);

				}
				while (sensorMethods.LVL2Approch1() == false) {
					// wait
				}
				Methods.downOff();
				if (sensorMethods.upper_safety1() == false) {

					Methods.downOn(1);
				}
				TimeUnit.MILLISECONDS.sleep(4800);
				downOff();

				OpenDoors();
				UpdateSensors.updateSensor("level1", "1");
				UpdateSensors.updateSensor("level4", "0");
				UpdateSensors.updateSensor("level3", "0");
				UpdateSensors.updateSensor("level2", "0");
				return;
			}
			if (sensorMethods.level1reached() == true) {
				UpdateSensors.updateSensor("level1", "1");
				UpdateSensors.updateSensor("level4", "0");
				UpdateSensors.updateSensor("level3", "0");
				UpdateSensors.updateSensor("level2", "0");
				return;
			}
		}
		// ------------------GOING TO SECOND(2) FLOOR
		if (floor == 2) {
			DataStorage_CONTROLLER.modifyValue("reqLevel2", "0");
			// ------------------IF THE ELEVATOR IS BELOW THE SECOND(2) FLOOR
			if (sensorMethods.level1reached() == true) {
				if (sensorMethods.isOpen() == true) {
					CloseDoors();
				}
				if (sensorMethods.LVL1Approch2() == false) {
					Methods.upOn(2);
				}
				while (sensorMethods.LVL1Approch2() == false) {
					// wait
				}
				Methods.upOff();
				if (sensorMethods.lower_safety2() == false) {
					Methods.upOn(1);
				}
				TimeUnit.MILLISECONDS.sleep(4800);
				upOff();

				OpenDoors();
				UpdateSensors.updateSensor("level2", "1");
				UpdateSensors.updateSensor("level1", "0");
				UpdateSensors.updateSensor("level4", "0");
				UpdateSensors.updateSensor("level3", "0");
				return;
			}
			// ------------------IF THE ELEVATOR IS ABOVE THE SECOND(2) FLOOR
			if (sensorMethods.level3reached() == true || sensorMethods.level4reached() == true) {
				if (sensorMethods.isOpen() == true) {
					CloseDoors();
				}
				if (sensorMethods.LVL3Approch2() == false) {
					Methods.downOn(2);
				}
				while (sensorMethods.LVL3Approch2() == false) {
					// wait
				}
				Methods.downOff();
				if (sensorMethods.upper_safety2() == false) {

					Methods.downOn(1);
				}
				TimeUnit.MILLISECONDS.sleep(4800);
				downOff();

				OpenDoors();
				UpdateSensors.updateSensor("level4", "0");
				UpdateSensors.updateSensor("level3", "0");
				UpdateSensors.updateSensor("level2", "1");
				UpdateSensors.updateSensor("level1", "0");
				
				return;
			}
			if (sensorMethods.level2reached() == true) {
				UpdateSensors.updateSensor("level2", "1");
				UpdateSensors.updateSensor("level1", "0");
				UpdateSensors.updateSensor("level4", "0");
				UpdateSensors.updateSensor("level3", "0");
				return;
			}
		}
		// ------------------GOING TO THIRD(3) FLOOR
		if (floor == 3) {
			DataStorage_CONTROLLER.modifyValue("reqLevel3", "0");
			// ------------------IF THE ELEVATOR IS BELOW THE THIRD(3) FLOOR
			if (sensorMethods.level1reached() == true || sensorMethods.level2reached()) {
				if (sensorMethods.isOpen() == true) {
					CloseDoors();
				}
				if (sensorMethods.LVL2Approch3() == false) {
					Methods.upOn(2);
				}
				while (sensorMethods.LVL2Approch3() == false) {
					// wait
				}
				Methods.upOff();
				if (sensorMethods.lower_safety3() == false) {

					Methods.upOn(1);
				}
				TimeUnit.MILLISECONDS.sleep(4800);
				Methods.upOff();
				OpenDoors();
				UpdateSensors.updateSensor("level3", "1");
				UpdateSensors.updateSensor("level1", "0");
				UpdateSensors.updateSensor("level2", "0");
				UpdateSensors.updateSensor("level4", "0");
				return;
			}
			// ------------------IF THE ELEVATOR IS ABOVE THE THIRD(3) FLOOR
			if (sensorMethods.level4reached() == true) {
				if (sensorMethods.isOpen() == true) {
					CloseDoors();
				}
				if (sensorMethods.LVL4Approch3() == false) {
					Methods.downOn(2);
				}
				while (sensorMethods.LVL4Approch3() == false) {
					// wait
				}
				Methods.downOff();
				if (sensorMethods.upper_safety3() == false) {

					Methods.downOn(1);
				}

				TimeUnit.MILLISECONDS.sleep(4800);
				downOff();
				OpenDoors();
				UpdateSensors.updateSensor("level3", "1");
				UpdateSensors.updateSensor("level1", "0");
				UpdateSensors.updateSensor("level2", "0");
				UpdateSensors.updateSensor("level4", "0");
				return;
			}
			if (sensorMethods.level3reached() == true) {
				UpdateSensors.updateSensor("level3", "1");
				UpdateSensors.updateSensor("level1", "0");
				UpdateSensors.updateSensor("level2", "0");
				UpdateSensors.updateSensor("level4", "0");
				return;
			}
		}
		// ------------------GOING TO FOURTH(4) FLOOR
		if (floor == 4) {
			DataStorage_CONTROLLER.modifyValue("reqLevel4", "0");
			// ------------------IF THE ELEVATOR IS BELOW THE FOURTH(4) FLOOR
			if (sensorMethods.level1reached() == true || sensorMethods.level2reached() == true || sensorMethods.level3reached() == true) {
				if (sensorMethods.isOpen() == true) {
					CloseDoors();
				}
				if (sensorMethods.LVL3Approch4() == false) {

					Methods.upOn(2);
				}
				while (sensorMethods.LVL3Approch4() == false) {
					// wait
				}
				Methods.upOff();
				if (sensorMethods.lower_safety4() == false) {

					Methods.upOn(1);
				}

				TimeUnit.MILLISECONDS.sleep(4800);
				upOff();
				UpdateSensors.updateSensor("level4", "1");
				UpdateSensors.updateSensor("level1", "0");
				UpdateSensors.updateSensor("level2", "0");
				UpdateSensors.updateSensor("level3", "0");
				OpenDoors();

				return;
			}
			if (sensorMethods.level4reached() == true) {

				UpdateSensors.updateSensor("level4", "1");
				UpdateSensors.updateSensor("level1", "0");
				UpdateSensors.updateSensor("level2", "0");
				UpdateSensors.updateSensor("level3", "0");
				return;
			}

		}
	}

	// ---------------------------DOORS FUNCTIONS
	public static void OpenDoors() throws IOException, ModbusException {
		
		
		DataStorage_CONTROLLER.modifyValue("reqDoor", "0");
		modbus.getClient().WriteSingleCoil(registries.MOTOR_OPEN, true);

		while (!sensorMethods.isOpen()) {
			// wait
		}
		;

		modbus.getClient().WriteSingleCoil(registries.MOTOR_OPEN, false);
		UpdateSensors.updateSensor("doorState", "open");

	}

	public static void CloseDoors() throws IOException, ModbusException {
		DataStorage_CONTROLLER.modifyValue("reqDoor", "0");
		modbus.getClient().WriteSingleCoil(registries.MOTOR_CLOSE, true);

		while (!sensorMethods.isClosed()) {
			// wait
		}
		;

		modbus.getClient().WriteSingleCoil(registries.MOTOR_CLOSE, false);
		UpdateSensors.updateSensor("doorState", "closed");

	}

	public static void stopDoors() throws UnknownHostException, SocketException, ModbusException, IOException {
		modbus.getClient().WriteSingleCoil(registries.MOTOR_CLOSE, false);
		modbus.getClient().WriteSingleCoil(registries.MOTOR_OPEN, false);
		if(UpdateSensors.getJSONUpdate()!=null) {
			UpdateSensors.updateSensor("doorState", "0");
		}

	}

	
}
