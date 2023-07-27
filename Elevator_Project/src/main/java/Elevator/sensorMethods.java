package Elevator;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

import de.re.easymodbus.exceptions.ModbusException;

public class sensorMethods {
	// ------------------SENSOR VALUE CHECKING FUNCTIONS

		public static String getFloor() throws IOException, ModbusException {
			if (level1reached() == true)
				return "1";
			if (level2reached() == true)
				return "2";
			if (level3reached() == true)
				return "3";
			if (level4reached() == true)
				return "4";
			return null;
		}

		// ------------------FLOOR 1
		public static boolean lower_safety1() throws IOException, ModbusException {
			return modbus.getClient().ReadDiscreteInputs(registries.LVL_1_SAFETY_LOW, 1)[0];
		}

		public static boolean level1reached() throws IOException, ModbusException {
			boolean a=(modbus.getClient().ReadDiscreteInputs(registries.LVL_1_reached, 1)[0]);
			return a;
		}

		public static boolean upper_safety1() throws IOException, ModbusException {
			return modbus.getClient().ReadDiscreteInputs(registries.LVL_1_SAFETY_HIGH, 1)[0];
		}

		// ------------------FLOOR 2
		public static boolean lower_safety2() throws IOException, ModbusException {
			return modbus.getClient().ReadDiscreteInputs(registries.LVL_2_SAFETY_LOW, 1)[0];
		}

		public static boolean level2reached() throws IOException, ModbusException {
			return modbus.getClient().ReadDiscreteInputs(registries.LVL_2_reached, 1)[0];
		}

		public static boolean upper_safety2() throws IOException, ModbusException {
			return modbus.getClient().ReadDiscreteInputs(registries.LVL_2_SAFETY_HIGH, 1)[0];
		}

		// ------------------FLOOR 3
		public static boolean lower_safety3() throws IOException, ModbusException {
			return modbus.getClient().ReadDiscreteInputs(registries.LVL_3_SAFETY_LOW, 1)[0];
		}

		public static boolean level3reached() throws IOException, ModbusException {
			return modbus.getClient().ReadDiscreteInputs(registries.LVL_3_reached, 1)[0];
		}

		public static boolean upper_safety3() throws IOException, ModbusException {
			return modbus.getClient().ReadDiscreteInputs(registries.LVL_3_SAFETY_HIGH, 1)[0];
		}

		// ------------------FLOOR 4
		public static boolean lower_safety4() throws IOException, ModbusException {
			return modbus.getClient().ReadDiscreteInputs(registries.LVL_4_SAFETY_LOW, 1)[0];
		}

		public static boolean level4reached() throws IOException, ModbusException {
			return modbus.getClient().ReadDiscreteInputs(registries.LVL_4_reached, 1)[0];
		}

		public static boolean upper_safety4() throws IOException, ModbusException {
			return modbus.getClient().ReadDiscreteInputs(registries.LVL_4_SAFETY_HIGH, 1)[0];
		}

		// ------------------FLOOR 2 TO 1
		public static boolean LVL2Approch1() throws IOException, ModbusException {
			return modbus.getClient().ReadDiscreteInputs(registries.LVL_1_TO_2, 1)[0];
		}

		public static boolean LVL1Approch2() throws IOException, ModbusException {
			return modbus.getClient().ReadDiscreteInputs(registries.LVL_2_TO_1, 1)[0];
		}

		// ------------------FLOOR 2 TO 3
		public static boolean LVL3Approch2() throws IOException, ModbusException {
			return modbus.getClient().ReadDiscreteInputs(registries.LVL_2_TO_3, 1)[0];
		}

		public static boolean LVL2Approch3() throws IOException, ModbusException {
			return modbus.getClient().ReadDiscreteInputs(registries.LVL_3_TO_2, 1)[0];
		}

		// ------------------FLOOR 4 TO 3
		public static boolean LVL4Approch3() throws IOException, ModbusException {
			return modbus.getClient().ReadDiscreteInputs(registries.LVL_3_TO_4, 1)[0];
		}

		public static boolean LVL3Approch4() throws IOException, ModbusException {
			return modbus.getClient().ReadDiscreteInputs(registries.LVL_4_TO_3, 1)[0];
		}
		// ------------------CHECKING FUNCTIONS ---IS...

		public static boolean isErrorState() throws IOException, ModbusException {
			return modbus.getClient().ReadDiscreteInputs(registries.ELEVATOR_ERROR, 1)[0];
		}

		public static boolean isOn() throws IOException, ModbusException {
			return modbus.getClient().ReadDiscreteInputs(registries.MOTOR_ON, 1)[0];
		}

		public static boolean isReady() throws IOException, ModbusException {
			return modbus.getClient().ReadDiscreteInputs(registries.MOTOR_READY, 1)[0];
		}

		public static boolean isClosed() throws IOException, ModbusException {
			return modbus.getClient().ReadDiscreteInputs(registries.DOOR_IS_CLOSE_COIL, 1)[0];
		}

		public static boolean isOpen() throws IOException, ModbusException {
			return modbus.getClient().ReadDiscreteInputs(registries.DOOR_IS_OPEN_COIL, 1)[0];
		}

		public static boolean isSpeed2UpOn() throws UnknownHostException, SocketException, ModbusException, IOException {
			if(modbus.getClient().ReadDiscreteInputs(registries.MOTOR_UP_V2, 1)[0]==true ) {
				return true;
			}else {
				return false;
			}
		}
		public static boolean isSpeed2DownOn() throws UnknownHostException, SocketException, ModbusException, IOException {
			if(modbus.getClient().ReadDiscreteInputs(registries.MOTOR_DOWN_V2, 1)[0]==true) {
				return true;
			}else {
				return false;
			}
		}
		
		public static boolean isSpeed1UpOn() throws UnknownHostException, SocketException, ModbusException, IOException {
			if(modbus.getClient().ReadDiscreteInputs(registries.MOTOR_UP_V1, 1)[0] == true) {
				return true;
			}else {
				return false;
			}
			
		}
		public static boolean isSpeed1DownOn() throws UnknownHostException, SocketException, ModbusException, IOException {
			if(modbus.getClient().ReadDiscreteInputs(registries.MOTOR_DOWN_V1, 1)[0]==true) {
				return true;
			}else {
				return false;
			}
			
		}
}
