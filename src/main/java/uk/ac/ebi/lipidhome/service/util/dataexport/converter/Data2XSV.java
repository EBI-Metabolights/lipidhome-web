package uk.ac.ebi.lipidhome.service.util.dataexport.converter;

import uk.ac.ebi.lipidhome.service.util.dataexport.DataContainer;

import java.util.List;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 *
 *
 */
public abstract class Data2XSV extends DataConverter{

    protected Data2XSV(List<DataContainer> dataList) {
        super(dataList);
    }

    protected String getHeader(String delimiter){
        StringBuilder rtn = new StringBuilder();
        if(dataList.size()>0){
            for (String key : dataList.get(0).getKeys()) {
                rtn.append(key);
                rtn.append(delimiter);
            }
            rtn.deleteCharAt(rtn.length()-1);
        }
        rtn.append("\n");
        return rtn.toString();
    }

    protected String item2XSV(DataContainer item, String delimiter){
        StringBuilder rtn = new StringBuilder();
        if(dataList.size()>0){
            for (String key : dataList.get(0).getKeys()) {
                rtn.append(item.getValue(key));
                rtn.append(delimiter);
            }
            rtn.deleteCharAt(rtn.length()-1);
        }
        rtn.append("\n");
        return rtn.toString();
    }


}
