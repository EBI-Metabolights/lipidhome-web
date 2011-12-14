package uk.ac.ebi.lipidhome.service.util.dataexport.converter;

import uk.ac.ebi.lipidhome.service.util.dataexport.DataContainer;

import java.util.List;

public class Data2XML extends DataConverter{

    public Data2XML(List<DataContainer> dataList) {
        super(dataList);
    }

    private String item2XML(DataContainer item){
        StringBuilder rtn = new StringBuilder("<hit>");
        for (String key : item.getKeys()) {
            rtn.append("<" + key + ">");
            rtn.append(item.getValue(key));
            rtn.append("</" + key + ">");
        }
        rtn.append("</hit>");
        return rtn.toString();
    }

    @Override
    String convert() {
        StringBuilder rtn = new StringBuilder("<xml>");
        rtn.append("<hits>");
        for (DataContainer item : dataList) {
            rtn.append(item2XML(item));
        }
        rtn.append("</hits>");
        rtn.append("</xml>");
        return rtn.toString();
    }
}