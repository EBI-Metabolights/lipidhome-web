package uk.ac.ebi.lipidhome.core.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import uk.ac.ebi.lipidhome.core.dao.SpecieDao;
import uk.ac.ebi.lipidhome.core.model.CrossReference;
import uk.ac.ebi.lipidhome.core.model.Paper;
import uk.ac.ebi.lipidhome.core.model.Specie;
import uk.ac.ebi.lipidhome.service.mapper.BaseSearchItemMapper;
import uk.ac.ebi.lipidhome.service.mapper.CrossReferenceMapper;
import uk.ac.ebi.lipidhome.service.mapper.PaperMapper;
import uk.ac.ebi.lipidhome.service.mapper.SimpleFAScanSpecieMapper;
import uk.ac.ebi.lipidhome.service.mapper.SpecieMapper;
import uk.ac.ebi.lipidhome.service.result.model.BaseSearchItem;
import uk.ac.ebi.lipidhome.service.result.model.SimpleFAScanSpecie;

@Repository
public class SpecieDaoImpl extends BaseDaoImpl<Specie> implements SpecieDao<Specie>{

	@Override
	public Specie getSpecie(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return (Specie) jdbcTemplate.queryForObject(
				"SELECT s.*, sc.model, c.formula, c.mass " +
				"FROM species as s, composition as c, sub_class as sc " +
				"WHERE s.species_id = ? and s.l_composition_id = c.composition_id and s.l_sub_class_id = sc.sub_class_id;",
				new Object[] { id }, new SpecieMapper());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BaseSearchItem> getSpecieByNameLike(String name, Long start, Long limit){
		name = "%%" + name + "%%";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"SELECT species_id AS item_id, name, identified, 'specie' as type " +
				"FROM species " +
				"WHERE name LIKE ? ORDER BY identified DESC, name LIMIT ?, ?;",
				new Object[]{ name, start, limit}, new BaseSearchItemMapper());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BaseSearchItem> getSpecieParents(Long id){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"SELECT sc.sub_class_id AS item_id, sc.name, TRUE as identified, 'subClass' as type " +
				"FROM species AS s, sub_class AS sc " +
				"WHERE s.l_sub_class_id = sc.sub_class_id AND s.species_id = ?;",
				new Object[]{ id }, new BaseSearchItemMapper());
	}

	@Override
	public int getFAScanSpeciesCountById(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.queryForInt(
				"SELECT count(f.FA_scan_species_id) " +
				"FROM FA_scan_species as f " +
				"WHERE f.l_species_id = ?;",
				new Object[]{id});
	}

	@Override
	public int getSubSpeciesCountById(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.queryForInt(
				"SELECT count(distinct ss.sub_species_id) " +
				"FROM FA_scan_species as f, FA_scan_species_has_sub_species as h, sub_species as ss " +
				"WHERE f.l_species_id = ? and h.l_FA_scan_species_id = f.FA_scan_species_id and h.l_sub_species_id = ss.sub_species_id;",
				new Object[]{id});
	}

	@Override
	public int getIsomerCountById(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.queryForInt(
				"SELECT count(distinct i.isomer_id) " +
				"FROM FA_scan_species as f, FA_scan_species_has_sub_species as h, sub_species as ss, isomer as i " +
				"WHERE f.l_species_id = ? and h.l_FA_scan_species_id = f.FA_scan_species_id and h.l_sub_species_id = ss.sub_species_id and i.l_sub_species_id = ss.sub_species_id;",
				new Object[]{id});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrossReference> getCrossReferencesList(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"SELECT r.name, x.url " +
				"FROM xref as x, resource as r, species_has_xref as h " +
				"WHERE h.l_species_id = ? AND h.l_xref_id = x.xref_id AND x.l_resource_id = r.resource_id;",
				new Object[] { id }, new CrossReferenceMapper());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Paper> getPapersList(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"SELECT p.*, GROUP_CONCAT( distinct m.name SEPARATOR ', ' ) as mesh_terms, GROUP_CONCAT( distinct a.name SEPARATOR ', ' ) as authors " +   
				"FROM species_has_paper as h, mesh_term as m, paper_has_mesh_term as hm, paper as p, author as a, paper_has_author as ha " +
				"WHERE ha.l_author_id = a.author_id and ha.l_paper_id = p.paper_id and hm.l_paper_id = p.paper_id and hm.l_mesh_term_id = m.mesh_term_id and p.paper_id = h.l_paper_id and h.l_species_id = ? group by p.paper_id;",
				new Object[] { id }, new PaperMapper());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SimpleFAScanSpecie> getSimpleFAScanSpeciesList(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"SELECT f.FA_scan_species_id, f.name, f.identified, f.score " +
				"FROM FA_scan_species as f " +
				"WHERE f.l_species_id = ?;",
				new Object[] { id }, new SimpleFAScanSpecieMapper());
	}
}
