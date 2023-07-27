package Elevator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonDecoder_CONTROLLER {
	@SuppressWarnings("unchecked")
	public static Map<String, String> decodeJson(String jsonPayload) {
		
		 Map<String, String> decodedData = new HashMap<>();
		    Map<String, String> originalData = DataStorage_CONTROLLER.getDecodedData();

		    try {
		        JSONParser parser = new JSONParser();
		        JSONObject jsonObject = (JSONObject) parser.parse(jsonPayload);
		        decodedData.putAll(jsonObject);
		    } catch (ParseException e) {
		        e.printStackTrace();
		    }
		    
		    Map<String, String> updatedData = new HashMap<>(originalData);

		    
		    
		    for (Map.Entry<String, String> entry : decodedData.entrySet()) {
		        String key = entry.getKey();
		        String value = entry.getValue();
		        if (originalData.containsKey(key)) {
		            updatedData.put(key, value); // Update the value in the updatedData map
		        }
		    }
		    
		    return updatedData;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, String> decodeJsonInit() throws FileNotFoundException, IOException {
		Map<String, String> decodedData = new HashMap<>();
		String filePath = new String("resetValues/HMI_TO_CONTROLLER.json");
		JSONParser parser = new JSONParser();

		try (FileReader fileReader = new FileReader(filePath)) {
			// Parse the JSON file
			Object obj = parser.parse(fileReader);
			// Cast the parsed object to JSONObject
			JSONObject jsonObject = (JSONObject) obj;
			decodedData.putAll(jsonObject);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return decodedData;
	}

	public static JSONObject JSONWriter(String Key, String Value) {
		JSONObject obj = new JSONObject();
		obj.put(Key, Value);
		return obj;
	}
}
