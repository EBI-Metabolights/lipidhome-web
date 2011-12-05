package uk.ac.ebi.lipidhome.service.util.ms1export;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import uk.ac.ebi.lipidhome.core.model.LipidType;

public class MS1DataContainer {
    private JSONObject data;

    public MS1DataContainer(JSONObject data) {
        this.data = data;
    }

    public Long getItemId(){
        Long itemId = null;
        try {
            itemId = data.getLong("itemId");
        } catch (JSONException e) {
            /*Nothing here*/
        }
        return itemId;
    }

    public String getName(){
        String name = null;
        try {
            name = data.getString("name");
        } catch (JSONException e) {
            /*Nothing here*/
        }
        return name;
    }

    public String getCode(){
        String code = null;
        try {
            code = data.getString("code");
        } catch (JSONException e) {
            /*Nothing here*/
        }
        return code;
    }

    public LipidType getType(){
        LipidType type = null;
        try {
            type = LipidType.getType(data.getString("type"));
        } catch (JSONException e) {
            /*Nothing here*/
        }
        return type;
    }

    public Double getMass(){
        Double mass = null;
        try {
            mass = data.getDouble("mass");
        } catch (JSONException e) {
            /*Nothing here*/
        }
        return mass;
    }

    public Boolean getIdentified(){
        Boolean identified = null;
        try {
            identified = data.getBoolean("identified");
        } catch (JSONException e) {
            /*Nothing here*/
        }
        return identified;
    }

    public Long getFACarbons(){
        Long faCarbons = null;
        try {
            faCarbons = data.getLong("faCarbons");
        } catch (JSONException e) {
            /*Nothing here*/
        }
        return faCarbons;
    }

    public Long getFADoubleBonds(){
        Long faDoubleBonds = null;
        try {
            faDoubleBonds = data.getLong("faDoubleBonds");
        } catch (JSONException e) {
            /*Nothing here*/
        }
        return faDoubleBonds;
    }

    public Double getResMass(){
        Double resMass = null;
        try {
            resMass = data.getDouble("resMass");
        } catch (JSONException e) {
            /*Nothing here*/
        }
        return resMass;
    }

    public Double getDelta(){
        Double delta = null;
        try {
            delta = data.getDouble("delta");
        } catch (JSONException e) {
            /*Nothing here*/
        }
        return delta;
    }
}
