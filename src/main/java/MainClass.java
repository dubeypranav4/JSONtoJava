import org.json.JSONObject;

public class MainClass {

	public static void main(String[] args) {
	ObjectInstantiater objectInstantiater = new ObjectInstantiater();

		JSONObject obj = new JSONObject("{\n" +
				"  \"data\":{\n" +
				"    \"name\":\"Pranav Dubey\",\n" +
				"    \"age\" : \"15\",\n" +
				"    \"fpoint\":\"23.45\"\n" +
				"  },\n" +
				"  \"contract\":{\n" +
				"    \"name\":\"java.lang.String\",\n" +
				"    \"age\":\"java.lang.Integer\",\n" +
				"    \"fpoint\":\"java.lang.Double\"\n" +
				"  }\n" +
				"}");
	objectInstantiater.instantiate(obj);


	}
}
