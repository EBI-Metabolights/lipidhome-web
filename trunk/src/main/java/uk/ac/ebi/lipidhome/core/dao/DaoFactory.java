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
