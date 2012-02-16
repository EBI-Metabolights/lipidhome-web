package uk.ac.ebi.lipidhome.core.dao;

import javax.sql.DataSource;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * An interface for the Base data access object, all DAOs must have a datasource.
 *
 */
public interface BaseDao<T> {
	DataSource getDataSource();
	void setDataSource(DataSource dataSource);
}
