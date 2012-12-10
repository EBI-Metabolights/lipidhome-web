package uk.ac.ebi.lipidhome.service.impl;

import uk.ac.ebi.lipidhome.core.dao.SubSpecieDao;
import uk.ac.ebi.lipidhome.core.model.SubSpecie;
import uk.ac.ebi.lipidhome.core.model.SubSpecieChain;
import uk.ac.ebi.lipidhome.fastlipid.counter.BooleanRBCounterStartSeeder;
import uk.ac.ebi.lipidhome.fastlipid.exec.GeneralIterativeLipidGetter;
import uk.ac.ebi.lipidhome.fastlipid.exec.IterativePhospholipidGetterDefCarbsDoubleBondPerChain;
import uk.ac.ebi.lipidhome.fastlipid.generator.ChainFactoryGenerator;
import uk.ac.ebi.lipidhome.fastlipid.generator.LNetMoleculeGeneratorException;
import uk.ac.ebi.lipidhome.fastlipid.structure.ChainInfoContainer;
import uk.ac.ebi.lipidhome.fastlipid.structure.ChemInfoContainer;
import uk.ac.ebi.lipidhome.fastlipid.structure.HeadGroup;
import uk.ac.ebi.lipidhome.fastlipid.structure.SingleLinkConfiguration;
import uk.ac.ebi.lipidhome.fastlipid.structure.rule.BondDistance3nPlus2Rule;
import uk.ac.ebi.lipidhome.fastlipid.structure.rule.BondRule;
import uk.ac.ebi.lipidhome.fastlipid.structure.rule.StarterDoubleBondRule;
import uk.ac.ebi.lipidhome.service.SubSpecieService;
import uk.ac.ebi.lipidhome.service.result.ListConverter;
import uk.ac.ebi.lipidhome.service.result.Result;
import uk.ac.ebi.lipidhome.service.result.model.SimpleIsomer;
import uk.ac.ebi.lipidhome.service.result.model.SubSpecieSummary;

import javax.ws.rs.core.Response;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * The SubSpecieServiceImpl contains all the logic for  fulfilling the methods defined in the SubSpecieServiceService Interface
 *
 */
public class SubSpecieServiceImpl extends LipidService implements SubSpecieService{

    /**
     *SubSpecieFAScanSpecie of interest
     * A SubSpecie Object is built and from it a SubSpecieSummary is built. This object is transformed to
     * json via the result2Response method in LipidService and returned as a response object.
     * @return A response object containing a json formatted SubSpecieSummary.
     */
    @Override
    public Response getSubSpecieSummary(Long id) {
        Result result;
        SubSpecieDao<SubSpecie> subSpecieDao = getDaoFactory().getSubSpecieDao();

        try {
            SubSpecie subSpecie = subSpecieDao.getSubSpecie(id);
            SubSpecieSummary ssSummary = new SubSpecieSummary(subSpecie);
            ssSummary.setChain(subSpecieDao.getChainNameById(id));
            ssSummary.setAnnotatedIsomers(subSpecieDao.getIsomerCountById(id));
            ssSummary.setXrefs(subSpecieDao.getCrossReferencesList(id));
            ssSummary.setPapers(subSpecieDao.getPapersList(id));

            result = new Result(ssSummary);

        } catch (RuntimeException e) {
            String errorMessage = "Record with id " + id + " is unavailable.";
            result = new Result(errorMessage);
        }
        return result2Response(result);
    }

    /**
     *
     * A List of SimpleIsomer Objects is built on request by the SubSpecieDao. Once converted into a Result object
     * by the ListConverter it can be transformed into a Response object that contains the SimpleIsomer list encoded
     * as a json string.
     * @return A response object containing a json formatted List of SimpleIsomer.
     */
    @Override
    public Response getIsomerSimpleList(Long id) {
        Result result;

        SubSpecieDao<SubSpecie> subSpecieDao = getDaoFactory().getSubSpecieDao();
        String eCode = subSpecieDao.geteCode(id);

        try {
            Map<String, SimpleIsomer> identifiedIsomers = new HashMap<String, SimpleIsomer>();
            for (SimpleIsomer simpleIsomer : subSpecieDao.getSimpleIsomerList(id)){
                identifiedIsomers.put(simpleIsomer.getName(), simpleIsomer);
            }

            SubSpecie subSpecie = subSpecieDao.getSubSpecie(id);
            List<SubSpecieChain> chains = subSpecieDao.getChainsById(id);
            List<SimpleIsomer> theoreticalIsomers = getTheoreticalIsomers(subSpecie, chains, eCode);
            for (SimpleIsomer theoreticalIsomer : theoreticalIsomers){
                if(identifiedIsomers.keySet().contains(theoreticalIsomer.getName())){
                    theoreticalIsomer.setItemId(identifiedIsomers.get(theoreticalIsomer.getName()).getItemId());
                    theoreticalIsomer.setIdentified(true);
                }
            }
            Collections.sort(theoreticalIsomers);

            ListConverter<SimpleIsomer> converter = new ListConverter<SimpleIsomer>();
            result = new Result(converter.getLipidObjectList(theoreticalIsomers));
        } catch (RuntimeException e) {
            String errorMessage = "Record with id " + id + " is unavailable.";
            result = new Result(errorMessage);
        }

        return result2Response(result);
    }

    private List<SimpleIsomer> getTheoreticalIsomers(SubSpecie subSpecie, List<SubSpecieChain> chains, String eCode) {
        Integer[] cs = new Integer[chains.size()];
        Integer[] db = new Integer[chains.size()];
        SingleLinkConfiguration[] slc = new SingleLinkConfiguration[chains.size()];
        for (int i = 0; i < cs.length; i++) {
            SubSpecieChain ssc = chains.get(i);
            cs[i] = ssc.getCarbons();
            db[i] = ssc.getDoubleBonds();
            slc[i] = ssc.getSingleLinkConfiguration();
        }

        List<BondRule> rules = Arrays.asList(new BondDistance3nPlus2Rule(), new StarterDoubleBondRule(2));
        ChainFactoryGenerator cfg = new ChainFactoryGenerator(rules, new BooleanRBCounterStartSeeder(rules), true);

        GeneralIterativeLipidGetter generator = new GeneralIterativeLipidGetter(cfg);
        generator.setExoticModeOn(true);
        //the next line apparently is not allowed.
        generator.setCarbonsPerChains(cs);
        generator.setDoubleBondsPerChains(db);
        generator.setHeadGroup(HeadGroup.valueOf(eCode));
        generator.configForSmilesOutput();
        generator.setGenerateChainInfoContainer(true);
        generator.setLinkConfigs(slc);
        generator.run();

        List<SimpleIsomer> theoreticalIsomers = new ArrayList<SimpleIsomer>();
        try{

            ChemInfoContainer res = generator.nextChemInfoContainer();
            Long resCount =0l;
            while(res!=null) {
                if(resCount<101){
                    theoreticalIsomers.add(new SimpleIsomer(subSpecie.getItemId()+resCount,isomerName(res.getChainsInfo(), subSpecie), res.getSmiles()));
                }
                resCount++;
                res = generator.nextChemInfoContainer();
            }

        }catch(LNetMoleculeGeneratorException lmge){
            lmge.printStackTrace();
        }

        return theoreticalIsomers;
    }



    private String isomerName(List<ChainInfoContainer> chains, SubSpecie subSpecie){
        String ssname = subSpecie.getName();
        StringBuffer sb = new StringBuffer();
        sb.append(ssname);
        String regex = "\\d+:\\d+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(ssname); // get a matcher object
        int count = 0;
        int offSet = 0;
        int skipped = 0;
        while(m.find()) {
            if(!m.group().equals("0:0")){
                ChainInfoContainer chainIC = chains.get(count-skipped);
                List<Integer> dbonds = chainIC.getDoubleBondPositions();
                StringBuffer bondBuffer = new StringBuffer();
                if(dbonds.size()>0){
                    bondBuffer.append("[");
                    for (Integer dbond : dbonds) {
                        bondBuffer.append(dbond);
                        bondBuffer.append(",");
                    }
                    bondBuffer.deleteCharAt(bondBuffer.length()-1);
                    bondBuffer.append("]");
                }

                sb.insert(m.end() + offSet,bondBuffer.toString());

                offSet += bondBuffer.length();
            }else{
                skipped++;
            }
            count++;

        }
        return sb.toString();
    }
}
