package uk.ac.ebi.lipidhome.core.dao;

import uk.ac.ebi.lipidhome.core.model.*;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 *  The DaoFactory is the standardised way of getting a DAO in order to retrieve data from the DataSource.
 *  There is currently a DAO fr each level of the structure Hierarchy. This interface described the mehtods to get
 *  those DAOs.
 *
 */
public interface DaoFactory {
	
	CategoryDao<Category> getCategoryDao();
	MainClassDao<MainClass> getMainClassDao();
	SubClassDao<SubClass> getSubClassDao();
	SpecieDao<Specie> getSpecieDao();
	FAScanSpecieDao<FAScanSpecie> getFAScanSpecieDao();
	SubSpecieDao<SubSpecie> getSubSpecieDao();
    IsomerDao<Isomer> getIsomerDao();
}
