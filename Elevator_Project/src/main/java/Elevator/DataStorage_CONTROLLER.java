package Elevator;

import java.util.HashMap;
import java.util.Map;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DataStorage_CONTROLLER {
	static Map<String, String> oldDecodedData;
	public static Map<String, String> decodedData;
	

	public static void init() throws FileNotFoundException, IOException{
	 		decodedData= JsonDecoder_CONTROLLER.decodeJsonInit();
	 		oldDecodedData=new HashMap<>(decodedData);
	 	}

	public static Map<String, String> getDecodedData() {
		return decodedData;
	}

	public static void setDecodedData(Map<String, String> data) {
		oldDecodedData=decodedData;
		decodedData = data;
	}
	
	public static void modifyValue(String key,String newValue) {
		decodedData.put(key,newValue);
		
	}

}
