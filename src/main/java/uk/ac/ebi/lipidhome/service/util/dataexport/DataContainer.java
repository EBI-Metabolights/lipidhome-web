package uk.ac.ebi.lipidhome.service.util.dataexport;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataContainer {
    private JSONObject data;

    public DataContainer(JSONObject data) {
        this.data = data;
    }

    public List<String> getKeys(){
        List<String> keys = new ArrayList<String>();

        Iterator it = data.keys();
        while(it.hasNext()){
            String key = (String) it.next();
            keys.add(key);
        }

        return keys;
    }

    public String getValue(String key){
        try {
            return data.getString(key);
        } catch (JSONException e) {
            return "";
        }
    }
}
