package uk.ac.ebi.lipidhome.core.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import uk.ac.ebi.lipidhome.core.dao.SubSpecieDao;
import uk.ac.ebi.lipidhome.core.model.CrossReference;
import uk.ac.ebi.lipidhome.core.model.Paper;
import uk.ac.ebi.lipidhome.core.model.SubSpecie;
import uk.ac.ebi.lipidhome.core.model.SubSpecieChain;
import uk.ac.ebi.lipidhome.service.mapper.*;
import uk.ac.ebi.lipidhome.service.result.model.BaseSearchItem;
import uk.ac.ebi.lipidhome.service.result.model.SimpleIsomer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * The subSpecieDaoImpl contains all the methods to access sub specie related information from the DataSource.
 *
 */
@Repository
public class SubSpecieDaoImpl extends BaseDaoImpl<SubSpecie> implements SubSpecieDao<SubSpecie>{

    /**
     *
     * @param id The database id of the  sub specie
     * @return A sub specie object that is a faithful representation of the species table
     */
	@Override
	public SubSpecie getSubSpecie(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return (SubSpecie) jdbcTemplate.queryForObject(
				"SELECT DISTINCT ss.sub_species_id, ss.name, sc.model, c.exact_mass, c.formula, ss.identified" +
				"FROM sub_species as ss, species as s, composition as c, FA_scan_species as f , FA_scan_species_has_sub_species as h, sub_class as sc " +
				"WHERE sc.sub_class_id = s.l_sub_class_id and s.l_composition_id = c.composition_id and f.l_species_id = s.species_id and h.l_FA_scan_species_id = f.FA_scan_species_id and h.l_sub_species_id = ss.sub_species_id and ss.sub_species_id = ?;",
				new Object[] { id }, new SubSpecieMapper());
	}

    /**
     *
     * @param name The name or partial name of the sub specie to be searched for.
     * @param start The starting index of the result to return, this is used for paging of the data.
     * @param limit The number of records to return
     * @return A list search results
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<BaseSearchItem> getSubSpeciesByNameLike(String name, Long start, Long limit) {
		name = "%%" + name + "%%";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"SELECT sub_species_id AS item_id, name, identified, 'subSpecie' as type " +
				"FROM sub_species " +
				"WHERE name LIKE ? ORDER BY identified DESC, name LIMIT ?, ?;",
				new Object[]{ name, start, limit}, new BaseSearchItemMapper());
	}

    @SuppressWarnings("unchecked")
    @Override
    public List<BaseSearchItem> getSubSpeciesByNameLike(String name) {
        name = "%%" + name + "%%";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"SELECT sub_species_id AS item_id, name, identified, 'subSpecie' as type " +
				"FROM sub_species " +
				"WHERE name LIKE ? ORDER BY identified DESC, name;",
				new String[]{ name }, new BaseSearchItemMapper());
    }

    /**
     *
     * @param id The database id of the  sub specie
     * @return A list of the parents of this specie (FA scn species)
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<BaseSearchItem> getSubSpecieParents(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"SELECT f.FA_scan_species_id AS item_id, f.name, f.identified, 'faScanSpecie' as type " +
				"FROM FA_scan_species_has_sub_species AS h, FA_scan_species AS f " +
				"WHERE h.l_FA_scan_species_id = f.FA_scan_species_id AND h.l_sub_species_id = ?;",
				new Object[]{ id }, new BaseSearchItemMapper());
	}

    /**
     *
     * @param name the name to look for
     * @return the number of sub species with a matching name
     */
	@Override
	public long getSubSpeciesCountByNameLike(String name){
		name = "%%" + name + "%%";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.queryForLong(
				"SELECT COUNT(sub_species_id) " +
						"FROM sub_species " +
						"WHERE name LIKE ?;",
				new Object[]{name});
	}

    /**
     *
     * @param id The database id of the  sub specie
     * @return  The number of distinct isomers within this sub specie that are cross referenced to another resource.
     */
	@Override
	public int getIsomerCountById(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.queryForInt(
				"SELECT COUNT(distinct i.isomer_id) " +
				"FROM isomer as i " +
				"WHERE i.l_sub_species_id = ?;",
				new Object[]{id});
	}

    /**
     *
     * @param id The database id of the  sub specie
     * @return A string of fatty acid chain names concatenated by  " + "
     */
	@Override
	public String getChainNameById(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		@SuppressWarnings("unchecked")
		List<SubSpecieChain> list = jdbcTemplate.query(
				"SELECT fa.name, h.position, h.linkage, fa.carbons, fa.double_bonds " +
				"FROM fatty_acid_species as fa, sub_species_has_fatty_acid_species as h " +
				"WHERE h.l_sub_species_id = ? and h.l_fatty_acid_species_id = fa.fatty_acid_species_id order by h.position;",
				new Object[]{id}, new SubSpecieChainMapper());
		
		List<String> result = new ArrayList<String>();
		for(SubSpecieChain chain : list){
			result.add(chain.getSubSpecieFASpecieName());
		}
		return StringUtils.collectionToDelimitedString(result, "/");
	}

    @Override
    public String geteCode(Long id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        return (String) jdbcTemplate.queryForObject(
            "SELECT distinct(s.ecode) " +
            "FROM species AS s, FA_scan_species AS f, FA_scan_species_has_sub_species AS h " +
            "WHERE h.l_sub_species_id = " + id + " AND f.FA_scan_species_id = h.l_FA_scan_species_id AND f.l_species_id = s.species_id;",
            String.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SubSpecieChain> getChainsById(Long id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"SELECT fa.name, h.position, h.linkage, fa.carbons, fa.double_bonds " +
				"FROM fatty_acid_species as fa, sub_species_has_fatty_acid_species as h " +
				"WHERE h.l_sub_species_id = ? and h.l_fatty_acid_species_id = fa.fatty_acid_species_id order by h.position;",
				new Object[]{id}, new SubSpecieChainMapper());
    }

    /**
     *
     * @param id The database id of the  sub specie
     * @return A list of cross reference object each one a link to an external resource which has this sub specie in it.
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<CrossReference> getCrossReferencesList(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"SELECT r.name, x.url " +
				"FROM xref as x, resource as r, sub_species_has_xref as h " +
				"WHERE h.l_sub_species_id = ? AND h.l_xref_id = x.xref_id AND x.l_resource_id = r.resource_id;",
				new Object[] { id }, new CrossReferenceMapper());
	}

    /**
     *
     * @param id The database id of the  sub specie
     * @return A list of paper objects each one representing a single paper with pmid, abstract and other information
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<Paper> getPapersList(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"SELECT p.*, GROUP_CONCAT( distinct m.name SEPARATOR ', ' ) as mesh_terms, GROUP_CONCAT( distinct a.name SEPARATOR ', ' ) as authors " +   
				"FROM sub_species_has_paper as h, mesh_term as m, paper_has_mesh_term as hm, paper as p, author as a, paper_has_author as ha " +
				"WHERE ha.l_author_id = a.author_id and ha.l_paper_id = p.paper_id and hm.l_paper_id = p.paper_id and hm.l_mesh_term_id = m.mesh_term_id and p.paper_id = h.l_paper_id and h.l_sub_species_id = ? group by p.paper_id;",
				new Object[] { id }, new PaperMapper());
	}

    /**
     *
     * @param id The database id of the  sub specie
     * @return A list of simple isomer objects
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<SimpleIsomer> getSimpleIsomerList(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"SELECT i.isomer_id, i.name " +
				"FROM isomer as i " +
				"WHERE i.l_sub_species_id = ?;",
				new Object[] { id }, new SimpleIsomerMapper());
	}

}
