package uk.ac.ebi.lipidhome.service.util.ms1export;

import uk.ac.ebi.lipidhome.core.model.LipidType;

import java.util.List;

public class MS1Data2XML extends MS1DataConverter{

    public MS1Data2XML(List<MS1DataContainer> dataList) {
        super(dataList);
    }

    private String item2XML(MS1DataContainer item){
        StringBuilder rtn = new StringBuilder("<hit>");
        if(item.getItemId()!=null){
            rtn.append("<itemID>");
            rtn.append(item.getItemId());
            rtn.append("</itemID>");
        }
        if(item.getName()!=null){
            rtn.append("<name>");
            rtn.append(item.getName());
            rtn.append("</name>");
        }
        if(item.getCode()!=null){
            rtn.append("<code>");
            rtn.append(item.getCode());
            rtn.append("</code>");
        }
        if(item.getType()!= LipidType.NONE){
            rtn.append("<type>");
            rtn.append(item.getType().toString());
            rtn.append("</type>");
        }
        if(item.getMass()!=null){
            rtn.append("<mass>");
            rtn.append(item.getMass());
            rtn.append("</mass>");
        }
        if(item.getIdentified()!=null){
            rtn.append("<identified>");
            rtn.append(item.getIdentified());
            rtn.append("</identified>");
        }
        if(item.getFACarbons()!=null){
            rtn.append("<faCarbons>");
            rtn.append(item.getFACarbons());
            rtn.append("</faCarbons>");
        }
        if(item.getFADoubleBonds()!=null){
            rtn.append("<faDoubleBonds>");
            rtn.append(item.getFADoubleBonds());
            rtn.append("</faDoubleBonds>");
        }
        if(item.getResMass()!=null){
            rtn.append("<resMass>");
            rtn.append(item.getResMass());
            rtn.append("</resMass>");
        }
        if(item.getDelta()!=null){
            rtn.append("<delta>");
            rtn.append(item.getDelta());
            rtn.append("</delta>");
        }

        rtn.append("</hit>");
        return rtn.toString();
    }

    @Override
    String convert() {
        StringBuilder rtn = new StringBuilder("<xml>");
        rtn.append("<hits>");
        for (MS1DataContainer ms1DataContainer : dataList) {
            rtn.append(item2XML(ms1DataContainer));
        }
        rtn.append("</hits>");
        rtn.append("</xml>");
        return rtn.toString();
    }
}