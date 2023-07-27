package HMI;

import java.util.Map;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataStorage_HMI {
	static Map<String, String> oldDecodedData;
	static Map<String, String> decodedData;
	

	public static void init() throws FileNotFoundException, IOException{
	 		decodedData= JsonDecoder_HMI.decodeJsonInit();
	 		oldDecodedData=decodedData;
	 	}

	public static Map<String, String> getDecodedData() {
		return decodedData;
	}

	public static void setDecodedData(Map<String, String> data) {
		oldDecodedData=decodedData;
		decodedData = data;
	}

	public static String getValue(String key, boolean which) {
		if(which ==true)
		{
			return (String) decodedData.get(key);
		}else
		{
			return (String) decodedData.get(key);
		}
		
	}
}
