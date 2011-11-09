/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * @date August 2011
 *
 *
 * An interface for the Base data access object, all DAOs must have a datasource.
 */

package uk.ac.ebi.lipidhome.core.dao;

import javax.sql.DataSource;

public interface BaseDao<T> {
	DataSource getDataSource();
	void setDataSource(DataSource dataSource);
}
