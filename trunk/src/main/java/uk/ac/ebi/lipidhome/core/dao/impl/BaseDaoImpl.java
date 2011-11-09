/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * @date August 2011
 *
 *
 *  The implementation of the BaseDao which is to be extended by all other DAOs gives acccess to the DataSource.
 */
package uk.ac.ebi.lipidhome.core.dao.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import uk.ac.ebi.lipidhome.core.dao.BaseDao;


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
