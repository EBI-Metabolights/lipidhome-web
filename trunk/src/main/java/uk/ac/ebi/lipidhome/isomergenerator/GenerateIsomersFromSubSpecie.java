package uk.ac.ebi.lipidhome.isomergenerator;

import exec.IterativePhospholipidGetterDefCarbsDoubleBondPerChain;
import lnetmoleculegenerator.LNetMoleculeGeneratorException;
import structure.ChainInfoContainer;
import structure.ChemInfoContainer;
import uk.ac.ebi.lipidhome.core.model.Isomer;
import uk.ac.ebi.lipidhome.isomergenerator.models.SubSpecie;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: jfoster
 * Date: 06-Oct-2011
 * Time: 12:04:00
 * To change this template use File | Settings | File Templates.
 */
public class GenerateIsomersFromSubSpecie {
    private IterativePhospholipidGetterDefCarbsDoubleBondPerChain generator;
    private SubSpecie subSpecie;

    public GenerateIsomersFromSubSpecie() {
    }

    public IterativePhospholipidGetterDefCarbsDoubleBondPerChain getGenerator() {
        return generator;
    }

    public void setGenerator(IterativePhospholipidGetterDefCarbsDoubleBondPerChain generator) {
        this.generator = generator;
    }

    public SubSpecie getSubSpecie() {
        return subSpecie;
    }

    public void setSubSpecie(SubSpecie subSpecie) {
        this.subSpecie = subSpecie;
    }

    public ArrayList<Isomer> generateIsomers(){
        ArrayList<Isomer> isomers = new ArrayList<Isomer>();
        generator.setCarbonsChainA(subSpecie.getFattyAcids().get(1).getCarbons());
        generator.setCarbonsChainB(subSpecie.getFattyAcids().get(2).getCarbons());
        generator.setDoubleBondsChainA(subSpecie.getFattyAcids().get(1).getDoubleBonds());
        generator.setDoubleBondsChainB(subSpecie.getFattyAcids().get(2).getDoubleBonds());
        //generator.setHeadMolStream(IterativePhospholipidGetterDefCarbsDoubleBondPerChain.class.getClassLoader().getResourceAsStream("PC.mol"));
        generator.setPathToHead("/home/amundo/pride/lipidhome/src/main/resources/structures/models/mainclass/PC.mol");
        generator.configForSmilesOutput();
        generator.setGenerateChainInfoContainer(true);
        //error here also we do not no the linkages! although we can extract them from the name i guess..... but maybe it should be in the databse
        generator.setLinkConfigsR1R2(subSpecie.getFattyAcids().get(1).getSlc(),subSpecie.getFattyAcids().get(2).getSlc());
        generator.run();
        try{
            ChemInfoContainer res = generator.nextChemInfoContainer();

            while(res!=null) {
                //System.out.println("Smiles:"+res.getSmiles());
                //System.out.println("exact:" + res.getExactMass());
                //System.out.println("natural" + res.getNaturalMass());
                Isomer isomer = new Isomer();
                isomer.setSmile(res.getSmiles());
                System.out.println(isomerName(res.getChainsInfo()));
                isomer.setName(isomerName(res.getChainsInfo()));                
                isomers.add(isomer);
                res = generator.nextChemInfoContainer();
            }

        }catch(LNetMoleculeGeneratorException lmge){
            lmge.printStackTrace();
        }

        //System.out.println("Total:"+generator.getNumOfTotalStructures());

        return isomers;
    }

    private String isomerName(List<ChainInfoContainer> chains){
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
            bondBuffer.append("[");
            for (Integer dbond : dbonds) {
                bondBuffer.append(dbond);
                bondBuffer.append(",");
            }
            bondBuffer.deleteCharAt(bondBuffer.length()-1);
            bondBuffer.append("]");
            sb.insert(m.end() + offSet,bondBuffer.toString());
            offSet += bondBuffer.length();
            count++;
        }
        return sb.toString();
    }
}
