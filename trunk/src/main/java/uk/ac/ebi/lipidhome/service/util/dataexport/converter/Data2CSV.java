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
public class Data2CSV extends Data2XSV {

    private static String DELIMITER = ",";

    public Data2CSV(List<DataContainer> dataList) {
        super(dataList);
    }

    @Override
    String convert() {
        StringBuilder rtn = new StringBuilder(getHeader(DELIMITER));
        for (DataContainer ms1DataContainer : dataList) {
            rtn.append(item2XSV(ms1DataContainer, DELIMITER));
        }
        return rtn.toString();
    }
}
