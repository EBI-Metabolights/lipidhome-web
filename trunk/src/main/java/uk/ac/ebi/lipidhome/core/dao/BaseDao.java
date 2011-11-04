package uk.ac.ebi.lipidhome.core.dao;

import javax.sql.DataSource;

public interface BaseDao<T> {
	DataSource getDataSource();
	void setDataSource(DataSource dataSource);
}
