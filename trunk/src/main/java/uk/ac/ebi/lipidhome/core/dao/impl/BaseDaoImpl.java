package uk.ac.ebi.lipidhome.core.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.ebi.lipidhome.core.dao.BaseDao;

import javax.sql.DataSource;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * The implementation of the BaseDao which is to be extended by all other DAOs gives acccess to the DataSource.
 *
 */
public class BaseDaoImpl<T> implements BaseDao<T>{

	@Autowired
	private DataSource dataSource;
	
	public BaseDaoImpl(){
		
	}
	
	@Override
	public DataSource getDataSource() {
		return dataSource;
	}

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
