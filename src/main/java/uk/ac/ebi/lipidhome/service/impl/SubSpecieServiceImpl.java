/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * The SubSpecieServiceImpl contains all the logic for  fulfilling the methods defined in the SubSpecieServiceService Interface
 */


package uk.ac.ebi.lipidhome.service.impl;

import exec.IterativePhospholipidGetterDefCarbsDoubleBondPerChain;
import lnetmoleculegenerator.LNetMoleculeGeneratorException;
import structure.ChainInfoContainer;
import structure.ChemInfoContainer;
import uk.ac.ebi.lipidhome.core.dao.SubSpecieDao;
import uk.ac.ebi.lipidhome.core.model.SubSpecie;
import uk.ac.ebi.lipidhome.core.model.SubSpecieChain;
import uk.ac.ebi.lipidhome.service.SubSpecieService;
import uk.ac.ebi.lipidhome.service.result.ListConverter;
import uk.ac.ebi.lipidhome.service.result.Result;
import uk.ac.ebi.lipidhome.service.result.model.SimpleIsomer;
import uk.ac.ebi.lipidhome.service.result.model.SubSpecieSummary;

import javax.ws.rs.core.Response;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


        try {
            Set<String> identifiedIsomers = new HashSet<String>();
            for (SimpleIsomer simpleIsomer : subSpecieDao.getSimpleIsomerList(id)){
                identifiedIsomers.add(simpleIsomer.getName());
            }

            SubSpecie subSpecie = subSpecieDao.getSubSpecie(id);
            List<SubSpecieChain> chains = subSpecieDao.getChainsById(id);
            List<SimpleIsomer> theoreticalIsomers = getTheoreticalIsomers(subSpecie, chains);
            for (SimpleIsomer theoreticalIsomer : theoreticalIsomers){
                if(identifiedIsomers.contains(theoreticalIsomer.getName())){
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

    private List<SimpleIsomer> getTheoreticalIsomers(SubSpecie subSpecie, List<SubSpecieChain> chains) {
        IterativePhospholipidGetterDefCarbsDoubleBondPerChain generator = new IterativePhospholipidGetterDefCarbsDoubleBondPerChain();
        SubSpecieChain sscA = chains.get(0);
        SubSpecieChain sscB = chains.get(1);
        generator.setCarbonsChainA(sscA.getCarbons());
        generator.setCarbonsChainB(sscB.getCarbons());
        generator.setDoubleBondsChainA(sscA.getDoubleBonds());
        generator.setDoubleBondsChainB(sscB.getDoubleBonds());
        generator.setHeadMolStream(subSpecie.getModelAsInputStream());
        generator.configForSmilesOutput();
        generator.setGenerateChainInfoContainer(true);
        generator.setLinkConfigsR1R2(sscA.getSingleLinkConfiguration(), sscB.getSingleLinkConfiguration());
        generator.run();

        List<SimpleIsomer> theoreticalIsomers = new ArrayList<SimpleIsomer>();
        try{

            ChemInfoContainer res = generator.nextChemInfoContainer();

            while(res!=null) {
                theoreticalIsomers.add(new SimpleIsomer(isomerName(res.getChainsInfo(), subSpecie), res.getSmiles()));
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
        while(m.find()) {
            ChainInfoContainer chainIC = chains.get(count);
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
            count++;
        }
        return sb.toString();
    }
}
