/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * @date August 2011
 *
 *
 *  The DaoFactory is the standardised way of getting a DAO in order to retrieve data from the DataSource.
 *  There is currently a DAO fr each level of the structure Hierarchy. All the methods to get and set DAO factories are
 *  available here.
 */

package uk.ac.ebi.lipidhome.core.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.ac.ebi.lipidhome.core.dao.*;
import uk.ac.ebi.lipidhome.core.model.*;

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

    @Autowired
    private IsomerDao<Isomer> isomerDao;
	
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

    @Override
    public IsomerDao<Isomer> getIsomerDao() {
        return isomerDao;
    }

    public  void setIsomerDao(IsomerDao<Isomer> isomerDao){
        this.isomerDao = isomerDao;
    }
}
