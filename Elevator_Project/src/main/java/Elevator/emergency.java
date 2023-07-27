package Elevator;

import java.io.IOException;

import de.re.easymodbus.exceptions.ModbusException;

public class emergency extends Thread{
	public static void sos() {
		try {
			if(DataStorage_CONTROLLER.decodedData.get("reqEmergencyStop").equals("1"))
			{
				System.out.println("EMERGENCY");
				Methods.upOff();
				Methods.downOff();
			}
			DataStorage_CONTROLLER.modifyValue("reqEmergencyStop", "0");
		} catch (IOException | ModbusException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(true) {
			sos();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
