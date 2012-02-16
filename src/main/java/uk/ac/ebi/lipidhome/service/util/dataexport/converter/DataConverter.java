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
public abstract class DataConverter {

    protected List<DataContainer> dataList;

    protected DataConverter(List<DataContainer> dataList) {
        this.dataList = dataList;
    }

    abstract String convert();

    public String getConvertedData(){
        return convert();
    }
}
