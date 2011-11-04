package uk.ac.ebi.lipidhome.core.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uk.ac.ebi.lipidhome.core.dao.CategoryDao;
import uk.ac.ebi.lipidhome.core.dao.DaoFactory;
import uk.ac.ebi.lipidhome.core.dao.FAScanSpecieDao;
import uk.ac.ebi.lipidhome.core.dao.MainClassDao;
import uk.ac.ebi.lipidhome.core.dao.SpecieDao;
import uk.ac.ebi.lipidhome.core.dao.SubClassDao;
import uk.ac.ebi.lipidhome.core.dao.SubSpecieDao;
import uk.ac.ebi.lipidhome.core.model.Category;
import uk.ac.ebi.lipidhome.core.model.FAScanSpecie;
import uk.ac.ebi.lipidhome.core.model.MainClass;
import uk.ac.ebi.lipidhome.core.model.Specie;
import uk.ac.ebi.lipidhome.core.model.SubClass;
import uk.ac.ebi.lipidhome.core.model.SubSpecie;

@Component
public class DaoFactoryImpl implements DaoFactory {

	@Autowired
	private CategoryDao<Category> categoryDao;
	
	@Autowired
	private MainClassDao<MainClass> mainClassDao;
	
	@Autowired
	private SubClassDao<SubClass> subClassDao;
	
	@Autowired
	private SpecieDao<Specie> specieDao;
	
	@Autowired
	private FAScanSpecieDao<FAScanSpecie> faScanSpecieDao;
	
	@Autowired
	private SubSpecieDao<SubSpecie> subSpecieDao;
	
	public DaoFactoryImpl(){
		
	}
	
	@Override
	public CategoryDao<Category> getCategoryDao() {
		return categoryDao;
	}
	
	public void setCategoryDao(CategoryDao<Category> categoryDao){
		this.categoryDao = categoryDao;
	}

	@Override
	public MainClassDao<MainClass> getMainClassDao() {
		return this.mainClassDao;
	}

	public void setMainClassDao(MainClassDao<MainClass> mainClassDao) {
		this.mainClassDao = mainClassDao;
	}

	@Override
	public SubClassDao<SubClass> getSubClassDao() {
		return subClassDao;
	}

	public void setSubClassDao(SubClassDao<SubClass> subClassDao) {
		this.subClassDao = subClassDao;
	}

	@Override
	public SpecieDao<Specie> getSpecieDao() {
		return specieDao;
	}
	
	public void setSpecieDao(SpecieDao<Specie> specieDao){
		this.specieDao = specieDao;
	}

	@Override
	public FAScanSpecieDao<FAScanSpecie> getFAScanSpecieDao() {
		return faScanSpecieDao;
	}
	
	public void setFAScanSpecieDao(FAScanSpecieDao<FAScanSpecie> faScanSpecie){
		this.faScanSpecieDao = faScanSpecie;
	}

	@Override
	public SubSpecieDao<SubSpecie> getSubSpecieDao() {
		return subSpecieDao;
	}
	
	public void setSubSpecieDao(SubSpecieDao<SubSpecie> subSpecieDao){
		this.subSpecieDao = subSpecieDao;
	}
}
