package uk.ac.ebi.lipidhome.isomerview;

import exec.IterativePhospholipidGetterDefCarbsDoubleBondPerChain;
import lnetmoleculegenerator.LNetMoleculeGeneratorException;
import uk.ac.ebi.lipidhome.isomerview.models.Isomer;
import uk.ac.ebi.lipidhome.isomerview.models.SubSpecie;
import structure.ChemInfoContainer;

import java.util.ArrayList;

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
        //error here also we do not no the linkages! although we can extract them from the name i guess..... but maybe it should be in the databse
        generator.setLinkConfigsR1R2(subSpecie.getFattyAcids().get(1).getSlc(),subSpecie.getFattyAcids().get(2).getSlc());
        generator.run();
        try{
            ChemInfoContainer res = generator.nextChemInfoContainer();

            while(res!=null) {
                System.out.println("Smiles:"+res.getSmiles());
                Isomer isomer = new Isomer();
                isomer.setSmile(res.getSmiles());
                isomers.add(isomer);
                res = generator.nextChemInfoContainer();
            }

        }catch(LNetMoleculeGeneratorException lmge){
            lmge.printStackTrace();
        }

        //System.out.println("Total:"+generator.getNumOfTotalStructures());

        return isomers;
    }
}
