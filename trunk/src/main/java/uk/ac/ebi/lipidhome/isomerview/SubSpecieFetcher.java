package uk.ac.ebi.lipidhome.isomerview;

import uk.ac.ebi.lipidhome.isomerview.models.FattyAcidSpecie;
import uk.ac.ebi.lipidhome.isomerview.models.SubSpecie;
import structure.SingleLinkConfiguration;

import java.sql.*;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: jfoster
 * Date: 06-Oct-2011
 * Time: 13:59:32
 * To change this template use File | Settings | File Templates.
 */
public class SubSpecieFetcher {
    private Long ssID;
    //this class needs acces to the database this should probably be handled by spring!
    private SubSpecie subSpecie;

    public SubSpecieFetcher() {

    }

    public Long getSsID() {
        return ssID;
    }

    public void setSsID(Long ssID) {
        this.ssID = ssID;
    }

    public SubSpecie getSubSpecie() {
        try{
            Driver driver = (Driver)Class.forName("com.mysql.jdbc.Driver").newInstance();
            Properties props = new Properties();
            props.put("user", "admin");
            props.put("password", "johnny5");
            //props.put("user", "root");
            //props.put("password", "newpwd");
            Connection con = driver.connect("jdbc:mysql://mysql-lnet.ebi.ac.uk:4265/lipidhomeTest", props);
            PreparedStatement ps = con.prepareStatement("select. ss.name, h.linkage,h.position,f.carbons, f.double_bonds, f.name, f.fatty_acid_species_id " +
                    "from sub_species as ss, sub_species_has_fatty_acid_species as h, fatty_acid_species as f " +
                    "where h.l_sub_species_id = ss.sub_species_id and h.l_fatty_acid_species_id = f.fatty_acid_species_id and ss.sub_species_id = ? order by 3");
            ps.setLong(1, ssID);
            SubSpecie subSpecie = new SubSpecie();
            subSpecie.setId(ssID);
            ResultSet rs = ps.executeQuery();
            Integer count = 0;
            HashMap<Integer,FattyAcidSpecie> fasAL = new HashMap<Integer,FattyAcidSpecie>();

            while(rs.next()){
                if(count == 0){
                    subSpecie.setName(rs.getString("name"));
                }
                FattyAcidSpecie fas = new FattyAcidSpecie();
                fas.setCarbons(rs.getInt("carbons"));
                fas.setDoubleBonds(rs.getInt("double_bonds"));
                fas.setName(rs.getString("name"));
                fas.setFasID(rs.getLong("fatty_acid_species_id"));
                if(rs.getString("linkage").equals("-")){
                    fas.setSlc(SingleLinkConfiguration.Acyl);
                }else{
                    fas.setSlc(SingleLinkConfiguration.Alkyl);
                }
                fasAL.put(rs.getInt("position"),fas);
            }
            subSpecie.setFattyAcids(fasAL);

            this.subSpecie = subSpecie;


        }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }catch(InstantiationException ie){
            ie.printStackTrace();
        }catch(IllegalAccessException iae){
            iae.printStackTrace();
        }
        return subSpecie;
    }

    public void setSubSpecie(SubSpecie subSpecie) {

        this.subSpecie = subSpecie;
    }
}
