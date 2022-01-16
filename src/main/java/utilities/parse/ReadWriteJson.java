package utilities.parse;



import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import testNG.listeners.SuiteListener;
import utilities.Constants;


public class ReadWriteJson {

	public static String getValueFromConfigFile(String nodeName) {
		return getValueFromName(nodeName, Constants.RESOURCES_PATH + SuiteListener.configPath);
	}

	private static String getValueFromName(String nodeName, String dataPath) {
		String value = "";
		String key = "";
		String content = "";
		String path = dataPath.replace("/", "\\");
		File file = new File(path);
		try {
			content = FileUtils.readFileToString(file, "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			JSONObject jsonObject = new JSONObject(content);
			for (Iterator it = jsonObject.keys(); it.hasNext();) {
				key = (String) it.next();
			}
			JSONArray arr = jsonObject.getJSONArray(key);
			for (int i = 0; i < arr.length(); i++) {
				value = arr.getJSONObject(i).getString(nodeName);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return value;
	}

}
