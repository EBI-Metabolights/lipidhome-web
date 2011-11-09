/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * @date August 2011
 *
 *
 *  The DaoFactory is the standardised way of getting a DAO in order to retrieve data from the DataSource.
 *  There is currently a DAO fr each level of the structure Hierarchy. This interface described the mehtods to get
 *  those DAOs.
 */
package uk.ac.ebi.lipidhome.core.dao;

import uk.ac.ebi.lipidhome.core.model.Category;
import uk.ac.ebi.lipidhome.core.model.FAScanSpecie;
import uk.ac.ebi.lipidhome.core.model.MainClass;
import uk.ac.ebi.lipidhome.core.model.Specie;
import uk.ac.ebi.lipidhome.core.model.SubClass;
import uk.ac.ebi.lipidhome.core.model.SubSpecie;

public interface DaoFactory {
	
	CategoryDao<Category> getCategoryDao();
	MainClassDao<MainClass> getMainClassDao();
	SubClassDao<SubClass> getSubClassDao();
	SpecieDao<Specie> getSpecieDao();
	FAScanSpecieDao<FAScanSpecie> getFAScanSpecieDao();
	SubSpecieDao<SubSpecie> getSubSpecieDao();
}
