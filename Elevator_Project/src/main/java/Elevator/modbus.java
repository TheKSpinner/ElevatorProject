package Elevator;

import java.io.IOException;

import de.re.easymodbus.exceptions.ModbusException;
import de.re.easymodbus.modbusclient.ModbusClient;

public class modbus {
	
	private static ModbusClient client;
	
	// ------------------CONNECT AND RESET

		public static void reset() throws IOException, ModbusException {
			Methods.upOff();
			Methods.downOff();
			Methods.stopDoors();
			DataStorage_CONTROLLER.init();
			client.WriteSingleCoil(registries.SIMULATION_RESET_COIL, true);
			System.out.println("Simulation was reset");
			client.WriteSingleCoil(registries.SIMULATION_RESET_COIL, false);
		}

		public static boolean connect() {

			try {
				client = new ModbusClient("ea-pc111.ei.htwg-konstanz.de", 508);
				client.Connect();
				System.out.println("\nConnection succesful!\n");
				return true;
			} catch (IOException e) {
				return false;
			}

		}

		// ------------------CLASS COMUNICATION
		public static ModbusClient getClient() {
			return client;
		}
}
