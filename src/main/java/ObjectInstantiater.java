import com.sun.deploy.util.StringUtils;
import javassist.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class ObjectInstantiater {

	private static final String AP_DATA = "data";
	private static final String AP_CONTRACT = "contract";

	public void instantiate(JSONObject advancedPayload){
		JSONObject data = advancedPayload.getJSONObject(AP_DATA);
		JSONObject meta = advancedPayload.getJSONObject(AP_CONTRACT);

		Object obj = getObject(data,meta);

	}

	private Object getObject(JSONObject data, JSONObject meta) {
	Map<String,String> dataMap = getTreeMapFromJSON(data);
		Map<String, String> metaMap = getTreeMapFromJSON(meta);

		return getObjectFromMap("objects.NewClass",metaMap);
	}

	//todo make one hashmap and another one tree map to increase the efficiency
	private Object getObjectFromMap(String name, Map<String, String> metaMap) {
		Iterator<String> metaKeys = metaMap.keySet().iterator();

		ClassPool pool = ClassPool.getDefault();
		CtClass ngClass = pool.makeClass(name);
		CtField field = null;
		try {
			while (metaKeys.hasNext()) {
				String fieldName = metaKeys.next();
				String fieldType = metaMap.get(fieldName);

				field = new CtField(pool.get(fieldType), fieldName, ngClass);
				ngClass.addField(field);
				ngClass.addMethod(CtNewMethod.setter(getSetterName(fieldName),field));
				ngClass.addMethod(CtNewMethod.setter(getGetterName(fieldName),field));
			}
		}catch (Exception e){

		}
		try {
			ngClass.writeFile();
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CannotCompileException e) {
			e.printStackTrace();
		}

return ngClass;
	}

	private String getSetterName(String fieldName) {
		StringBuilder name = new StringBuilder();
		name.append("set");
		char[] chars = fieldName.toCharArray();
		if (Character.isLowerCase(chars[0])){
			chars[0] = Character.toUpperCase(chars[0]);
		}
		name.append(chars);
		return name.toString();

	}
	private String getGetterName(String fieldName) {
		StringBuilder name = new StringBuilder();
		name.append("get");
		char[] chars = fieldName.toCharArray();
		if (Character.isLowerCase(chars[0])){
			chars[0] = Character.toUpperCase(chars[0]);
		}
		name.append(chars);
		return name.toString();

	}

	private Map<String, String> getTreeMapFromJSON(JSONObject data) {
		Iterator<String> dataKeys = data.keys();

		Map<String,String>  dedomena  = new TreeMap<String, String>();

		while (dataKeys.hasNext()){
			String key = dataKeys.next();
			String value = data.getString(key);
			dedomena.put(key,value);
		}
		return dedomena;
	}


}
