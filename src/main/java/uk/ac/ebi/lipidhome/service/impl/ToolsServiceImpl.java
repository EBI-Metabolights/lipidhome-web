package uk.ac.ebi.lipidhome.service.impl;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import uk.ac.ebi.lipidhome.core.dao.FAScanSpecieDao;
import uk.ac.ebi.lipidhome.core.dao.SpecieDao;
import uk.ac.ebi.lipidhome.core.model.AdductIons;
import uk.ac.ebi.lipidhome.core.model.FAScanSpecie;
import uk.ac.ebi.lipidhome.core.model.LipidType;
import uk.ac.ebi.lipidhome.core.model.Specie;
import uk.ac.ebi.lipidhome.service.ToolsService;
import uk.ac.ebi.lipidhome.service.result.ListConverter;
import uk.ac.ebi.lipidhome.service.result.Result;
import uk.ac.ebi.lipidhome.service.result.model.MS1SearchRowResult;
import uk.ac.ebi.lipidhome.service.util.ms1export.*;

import javax.ws.rs.core.Response;
import java.util.*;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 *
 *
 */
public class ToolsServiceImpl extends LipidService implements ToolsService {

    public ToolsServiceImpl() {

    }

    @Override
    public Response ms1Search(String masses, String level, Float tolerance, Boolean identified, String adductIons) {
        LipidType type = LipidType.getType(level);

        List<AdductIons> selectedAdductIons = new ArrayList<AdductIons>();
        try {
            JSONArray adductIonList = new JSONArray(adductIons);
            for(int i=0; i<adductIonList.length(); ++i){
                Long itemId = adductIonList.getLong(i);
                AdductIons adductIon = AdductIons.getAdductIon(itemId);
                selectedAdductIons.add(adductIon);

            }
        } catch (JSONException e) {/*Nothing here*/}

        List<Float> massList = getMassesList(masses);

        List<MS1SearchRowResult> rows = new ArrayList<MS1SearchRowResult>();
        switch (type) {
            case FA_SCAN_SPECIE:
                FAScanSpecieDao<FAScanSpecie> faScanSpecieDao = getDaoFactory().getFAScanSpecieDao();
                for (Float mass : massList) {
                    for (AdductIons adductIon : selectedAdductIons) {
                        rows.addAll(faScanSpecieDao.getMS1SearchResult(mass, adductIon, tolerance, identified));
                    }
                }
                break;
            default:
            case SPECIE:
                SpecieDao<Specie> specieDao = getDaoFactory().getSpecieDao();
                for (Float mass : massList) {
                    for (AdductIons adductIon : selectedAdductIons) {
                        rows.addAll(specieDao.getMS1SearchResult(mass, adductIon, tolerance, identified));
                    }
                }
                break;
        }

        ListConverter<MS1SearchRowResult> converter = new ListConverter<MS1SearchRowResult>();
        Result result = new Result(converter.getLipidObjectList(rows));
        return result2Response(result);
    }

    @Override
    public Response ms1Export(String data, String format) {
        Response.ResponseBuilder response = null;

        List<MS1DataContainer> dataList = new ArrayList<MS1DataContainer>();

        try {
            JSONArray items = new JSONArray(data);
            for(int i=0; i<items.length(); ++i){
                JSONObject item = items.getJSONObject(i);
                MS1DataContainer ms1DC = new MS1DataContainer(item);
                dataList.add(ms1DC);
            }
        } catch (JSONException e) {
            /*Nothing here*/
        }

        ExportFormat exportFormat = ExportFormat.getFormat(format);
        MS1DataConverter converter;
        switch (exportFormat){
            case CSV:
                converter = new MS1Data2CSV(dataList);
                break;
            case TSV:
                converter = new MS1Data2TSV(dataList);
                break;
            case EXCEL:
                converter = new MS1Data2Excel(dataList);
                HSSFWorkbook workbook = ((MS1Data2Excel) converter).getWorkbook();
                response = Response.ok((Object) workbook.getBytes());
                break;
            case XML:
                converter = new MS1Data2XML(dataList);
                break;
            default:
                data = "{\"hits\":" + data + "}";
                converter = null;
        }

        if(response==null)
            response = Response.ok( (converter==null) ?  data : converter.getConvertedData());

        String fileName = "lipidHomeDataExport." + exportFormat.getExtension();

        //octet-stream
		response.header("Content-Disposition", "attachment; filename=" + fileName);
		return response.build();
    }

    private List<Float> getMassesList(String masses){
        Set<Float> set = new HashSet<Float>();
        for (String s : masses.split("\n"))
            set.add(Float.parseFloat(s));
        List<Float> massList = new ArrayList<Float>(set);
        Collections.sort(massList);
        return  massList;
    }
}
