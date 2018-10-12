import com.sun.deploy.util.StringUtils;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import org.json.JSONObject;

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
		ClassPool pool = ClassPool.getDefault();
		CtClass newClass = pool.makeClass("NewClass");
		return getObjectFromMap(dataMap,metaMap);
	}

	//todo make one hashmap and another one tree map to increase the efficiency
	private Object getObjectFromMap(Map<String, String> dataMap, Map<String, String> metaMap) {
		for ()
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
