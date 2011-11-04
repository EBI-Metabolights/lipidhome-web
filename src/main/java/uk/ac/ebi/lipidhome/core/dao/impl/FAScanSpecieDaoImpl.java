package uk.ac.ebi.lipidhome.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import uk.ac.ebi.lipidhome.core.dao.FAScanSpecieDao;
import uk.ac.ebi.lipidhome.core.model.CrossReference;
import uk.ac.ebi.lipidhome.core.model.FAScanChain;
import uk.ac.ebi.lipidhome.core.model.FAScanSpecie;
import uk.ac.ebi.lipidhome.core.model.Paper;
import uk.ac.ebi.lipidhome.service.mapper.BaseSearchItemMapper;
import uk.ac.ebi.lipidhome.service.mapper.CrossReferenceMapper;
import uk.ac.ebi.lipidhome.service.mapper.FAScanChainMapper;
import uk.ac.ebi.lipidhome.service.mapper.FAScanSpecieMapper;
import uk.ac.ebi.lipidhome.service.mapper.PaperMapper;
import uk.ac.ebi.lipidhome.service.mapper.SimpleSubSpecieMapper;
import uk.ac.ebi.lipidhome.service.result.model.BaseSearchItem;
import uk.ac.ebi.lipidhome.service.result.model.SimpleSubSpecie;

@Repository
public class FAScanSpecieDaoImpl extends BaseDaoImpl<FAScanSpecie> implements FAScanSpecieDao<FAScanSpecie>{

	@Override
	public FAScanSpecie getSpecie(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return (FAScanSpecie) jdbcTemplate.queryForObject(
				"SELECT f.FA_scan_species_id, f.name, c.formula, c.mass, f.identified, sc.model " +
				"FROM species as s, composition as c, FA_scan_species as f, sub_class as sc " +
				"WHERE s.l_sub_class_id = sc.sub_class_id and f.FA_scan_species_id = ? and f.l_species_id = s.species_id and s.l_composition_id = c.composition_id;;",
				new Object[] { id }, new FAScanSpecieMapper());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BaseSearchItem> getFAScanSpecieByNameLike(String name, Long start, Long limit){
		name = "%%" + name + "%%";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"SELECT FA_scan_species_id AS item_id, name, identified, 'faScanSpecie' as type " +
				"FROM FA_scan_species " +
				"WHERE name LIKE ? ORDER BY identified DESC, name LIMIT ?, ?;",
				new Object[]{ name, start, limit}, new BaseSearchItemMapper());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BaseSearchItem> getFAScanSpecieParents(Long id){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"SELECT s.species_id AS item_id, s.name, s.identified, 'specie' as type " +
				"FROM species AS s, FA_scan_species AS f " +
				"WHERE s.species_id = f.l_species_id AND f.fa_scan_species_id = ?;",
				new Object[]{ id }, new BaseSearchItemMapper());
	}

	@Override
	public int getSubSpeciesCountById(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.queryForInt(
				"SELECT COUNT(distinct ss.sub_species_id) " +
				"FROM  FA_scan_species_has_sub_species as h, sub_species as ss " +
				"WHERE h.l_FA_scan_species_id = ? and h.l_sub_species_id = ss.sub_species_id;",
				new Object[]{id});
	}

	@Override
	public int getIsomerCountById(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.queryForInt(
				"SELECT COUNT(distinct i.isomer_id) " +
				"FROM FA_scan_species_has_sub_species as h, sub_species as ss, isomer as i " +
				"WHERE h.l_FA_scan_species_id = ? and h.l_sub_species_id = ss.sub_species_id and i.l_sub_species_id = ss.sub_species_id;",
				new Object[]{id});
	}
	
	@Override
	public String getChainById(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		@SuppressWarnings("unchecked")
		List<FAScanChain> list = jdbcTemplate.query(
				"SELECT fa.name, h.count " +
				"FROM fatty_acid_species as fa, fatty_acid_species_has_FA_scan_species as h " +
				"WHERE h.l_FA_scan_species_id = ? and h.l_fatty_acid_species_id = fa.fatty_acid_species_id;",
				new Object[]{id}, new FAScanChainMapper());
		
		List<String> result = new ArrayList<String>();
		for(FAScanChain chain : list){
			for(int i=0; i<chain.getCount(); ++i){
				result.add(chain.getName());
			}
		}
		return StringUtils.collectionToDelimitedString(result, " + ");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrossReference> getCrossReferencesList(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"SELECT r.name, x.url " +
				"FROM xref as x, resource as r, FA_scan_species_has_xref as h " +
				"WHERE h.l_FA_scan_species_id = ? AND h.l_xref_id = x.xref_id AND x.l_resource_id = r.resource_id;",
				new Object[] { id }, new CrossReferenceMapper());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Paper> getPapersList(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"SELECT p.*, GROUP_CONCAT( distinct m.name SEPARATOR ', ' ) as mesh_terms, GROUP_CONCAT( distinct a.name SEPARATOR ', ' ) as authors " +   
				"FROM FA_scan_species_has_paper as h, mesh_term as m, paper_has_mesh_term as hm, paper as p, author as a, paper_has_author as ha " +
				"WHERE ha.l_author_id = a.author_id and ha.l_paper_id = p.paper_id and hm.l_paper_id = p.paper_id and hm.l_mesh_term_id = m.mesh_term_id and p.paper_id = h.l_paper_id and h.l_FA_scan_species_id = ? group by p.paper_id;",
				new Object[] { id }, new PaperMapper());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SimpleSubSpecie> getSimpleSubSpeciesList(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"SELECT ss.sub_species_id, ss.name, ss.identified, ss.score " +
				"FROM sub_species as ss, FA_scan_species_has_sub_species as h " +
				"WHERE h.l_sub_species_id = ss.sub_species_id and h.l_FA_scan_species_id = ?;",
				new Object[] { id }, new SimpleSubSpecieMapper());
	}

}
