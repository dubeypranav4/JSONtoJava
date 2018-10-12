import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class MainClass {

	public static void main(String[] args) throws IOException, ParseException {
	ObjectInstantiater objectInstantiater = new ObjectInstantiater();

		String loc  = System.getProperty("user.dir") + "/src/main/resources/advancedPayload.json";

		JSONParser parser = new JSONParser();
		org.json.JSONObject obj = new org.json.JSONObject(parser.parse(new FileReader(loc)).toString());
		objectInstantiater.instantiate(obj);


	}
}
