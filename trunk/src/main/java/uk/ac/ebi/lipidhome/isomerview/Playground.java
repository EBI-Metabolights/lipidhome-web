package uk.ac.ebi.lipidhome.isomerview;

import exec.IterativePhospholipidGetterDefCarbsDoubleBondPerChain;
import uk.ac.ebi.lipidhome.isomerview.models.Isomer;
import uk.ac.ebi.lipidhome.isomerview.models.SubSpecie;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: jfoster
 * Date: 26-Oct-2011
 * Time: 14:28:57
 * To change this template use File | Settings | File Templates.
 */
public class Playground {
    public static void main(String[] args) {
        SubSpecieFetcher ssf = new SubSpecieFetcher();
        ssf.setSsID(2143l);
        SubSpecie ss = ssf.getSubSpecie();
        GenerateIsomersFromSubSpecie gifss = new GenerateIsomersFromSubSpecie();
        gifss.setGenerator(new IterativePhospholipidGetterDefCarbsDoubleBondPerChain());
        gifss.setSubSpecie(ss);
        ArrayList<Isomer> isomers =  gifss.generateIsomers();
    }
}
