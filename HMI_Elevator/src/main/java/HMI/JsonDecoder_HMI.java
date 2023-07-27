package HMI;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonDecoder_HMI {
	@SuppressWarnings("unchecked")
	public static Map<String, String> decodeJson(String jsonPayload) {
		 Map<String, String> updatedData = new HashMap<>();
	        try {
	            JSONParser parser = new JSONParser();
	            JSONObject jsonObject = (JSONObject) parser.parse(jsonPayload);
	            updatedData.putAll(jsonObject);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }

	        return updatedData;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, String> decodeJsonInit() throws FileNotFoundException, IOException {
		Map<String, String> decodedData = new HashMap<>();
		String filePath = new String("resetValues/CONTROLLER_TO_HMI.json");
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
