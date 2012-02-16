package uk.ac.ebi.lipidhome.service.util.ms1export;

import java.util.List;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 *
 *
 */
public class MS1Data2CSV extends MS1Data2XSV {

    private static String DELIMITER = ",";

    public MS1Data2CSV(List<MS1DataContainer> dataList) {
        super(dataList);
    }

    @Override
    String convert() {
        StringBuilder rtn = new StringBuilder(getHeader(DELIMITER));
        for (MS1DataContainer ms1DataContainer : dataList) {
            rtn.append(item2XSV(ms1DataContainer, DELIMITER));
        }
        return rtn.toString();
    }
}
