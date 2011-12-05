package uk.ac.ebi.lipidhome.service.util.ms1export;

import uk.ac.ebi.lipidhome.core.model.LipidType;

import java.util.List;

public abstract class MS1Data2XSV extends MS1DataConverter{

    protected String getHeader(String delimiter){
        StringBuilder rtn = new StringBuilder();
        rtn.append("itemID");
        rtn.append(delimiter);
        rtn.append("name");
        rtn.append(delimiter);
        rtn.append("code");
        rtn.append(delimiter);
        rtn.append("type");
        rtn.append(delimiter);
        rtn.append("mass");
        rtn.append(delimiter);
        rtn.append("identified");
        rtn.append(delimiter);
        rtn.append("faCarbons");
        rtn.append(delimiter);
        rtn.append("faDoubleBonds");
        rtn.append(delimiter);
        rtn.append("resMass");
        rtn.append(delimiter);
        rtn.append("delta");
        rtn.append("\n");
        return rtn.toString();
    }

    protected String item2XSV(MS1DataContainer item, String delimiter){
        StringBuilder rtn = new StringBuilder();
        rtn.append((item.getItemId()!=null) ? item.getItemId() : "");
        rtn.append(delimiter);
        rtn.append((item.getName()!=null) ? item.getName() : "");
        rtn.append(delimiter);
        rtn.append((item.getCode()!=null) ? item.getCode() : "");
        rtn.append(delimiter);
        rtn.append((item.getType()!=LipidType.NONE) ? item.getType().toString() : "");
        rtn.append(delimiter);
        rtn.append((item.getMass()!=null) ? item.getMass() : "");
        rtn.append(delimiter);
        rtn.append((item.getIdentified()!=null) ? item.getIdentified() : "");
        rtn.append(delimiter);
        rtn.append((item.getFACarbons()!=null) ? item.getFACarbons() : "");
        rtn.append(delimiter);
        rtn.append((item.getFADoubleBonds()!=null) ? item.getFADoubleBonds() : "");
        rtn.append(delimiter);
        rtn.append((item.getResMass()!=null) ? item.getResMass() : "");
        rtn.append(delimiter);
        rtn.append((item.getDelta()!=null) ? item.getDelta() : "");
        rtn.append("\n");
        return rtn.toString();
    }

    protected MS1Data2XSV(List<MS1DataContainer> dataList) {
        super(dataList);
    }
}
