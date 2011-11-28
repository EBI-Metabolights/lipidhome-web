package uk.ac.ebi.lipidhome.service.impl;

import uk.ac.ebi.lipidhome.core.dao.SpecieDao;
import uk.ac.ebi.lipidhome.core.model.LipidType;
import uk.ac.ebi.lipidhome.core.model.Specie;
import uk.ac.ebi.lipidhome.service.ToolsService;
import uk.ac.ebi.lipidhome.service.result.ListConverter;
import uk.ac.ebi.lipidhome.service.result.Result;
import uk.ac.ebi.lipidhome.service.result.model.MS1SearchRowResult;

import javax.ws.rs.core.Response;
import java.util.*;


public class ToolsServiceImpl extends LipidService implements ToolsService {

    public ToolsServiceImpl() {

    }

    @Override
    public Response ms1Search(String masses, String level, Float tolerance, Boolean identified) {
        LipidType type = LipidType.getType(level);

        List<Float> massList = getMassesList(masses);

        List<MS1SearchRowResult> rows = new ArrayList<MS1SearchRowResult>();
        switch (type) {
            default:
            case SPECIE:
                SpecieDao<Specie> specieDao = getDaoFactory().getSpecieDao();
                for (Float mass : massList) {
                    rows.addAll(specieDao.getMS1SearchResult(mass, tolerance, identified));
                }
                break;
        }

        ListConverter<MS1SearchRowResult> converter = new ListConverter<MS1SearchRowResult>();
        Result result = new Result(converter.getLipidObjectList(rows));
        return result2Response(result);
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
