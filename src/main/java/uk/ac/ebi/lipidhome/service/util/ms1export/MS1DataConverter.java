package uk.ac.ebi.lipidhome.service.util.ms1export;

import java.util.List;

public abstract class MS1DataConverter {

    protected List<MS1DataContainer> dataList;

    protected MS1DataConverter(List<MS1DataContainer> dataList) {
        this.dataList = dataList;
    }

    abstract String convert();

    public String getConvertedData(){
        return convert();
    }
}
