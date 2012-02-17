package uk.ac.ebi.lipidhome.core.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import uk.ac.ebi.lipidhome.core.dao.FAScanSpecieDao;
import uk.ac.ebi.lipidhome.core.model.*;
import uk.ac.ebi.lipidhome.service.mapper.*;
import uk.ac.ebi.lipidhome.service.result.model.BaseSearchItem;
import uk.ac.ebi.lipidhome.service.result.model.MS1SearchRowResult;
import uk.ac.ebi.lipidhome.service.result.model.SimpleSubSpecie;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * The FAScanSpecieDaoImpl contains all the methods to access FAScanSpecie related information from the DataSource.
 *
 */
@Repository
public class FAScanSpecieDaoImpl extends BaseDaoImpl<FAScanSpecie> implements FAScanSpecieDao<FAScanSpecie>{

    /**
     *
     * @param id The database id of the FAScanSpecie
     * @return a FAScanSpecie object that is a faithful representation fo the FA_scan_species_table
     */
	@Override
	public FAScanSpecie getSpecie(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return (FAScanSpecie) jdbcTemplate.queryForObject(
				"SELECT f.FA_scan_species_id, f.name, c.formula, c.exact_mass, f.identified, sc.model " +
				"FROM species as s, composition as c, FA_scan_species as f, sub_class as sc " +
				"WHERE s.l_sub_class_id = sc.sub_class_id and f.FA_scan_species_id = ? and f.l_species_id = s.species_id and s.l_composition_id = c.composition_id;;",
				new Object[] { id }, new FAScanSpecieMapper());
	}

    /**
     *
     * @param name The name or partial name of the category to be searched for.
     * @param start The starting index of the result to return, this is used for paging of the data.
     * @param limit The number of records to return
     * @return A list search results
     */
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
    public List<BaseSearchItem> getFAScanSpecieByNameLike(String name) {
        name = "%%" + name + "%%";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"SELECT FA_scan_species_id AS item_id, name, identified, 'faScanSpecie' as type " +
				"FROM FA_scan_species " +
				"WHERE name LIKE ? ORDER BY identified DESC, name;",
				new String[]{ name }, new BaseSearchItemMapper());
    }

    @Override
    public long getFAScanSpecieCountByNameLike(String name) {
        name = "%%" + name + "%%";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.queryForLong(
				"SELECT COUNT(FA_scan_species_id) " +
						"FROM FA_scan_species " +
						"WHERE name LIKE ?;",
				new Object[]{name});
    }

    /**
     *
     * @param id  The database id of the FA scan specie
     * @return  A list of the parents of this FA scan specie (species)
     */
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

    /**
     *
     * @param id  The database id of the FA scan specie
     * @return The number of distinct sub species within this FA scan specie.
     */
	@Override
	public int getSubSpeciesCountById(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.queryForInt(
				"SELECT COUNT(distinct ss.sub_species_id) " +
				"FROM  FA_scan_species_has_sub_species as h, sub_species as ss " +
				"WHERE h.l_FA_scan_species_id = ? and h.l_sub_species_id = ss.sub_species_id;",
				new Object[]{id});
	}

    /**
     *
     * @param id  The database id of the FA scan specie
     * @return  The number of distinct isomers within this FA scan specie that are cross reference to another resource.
     */

	@Override
	public int getIsomerCountById(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.queryForInt(
				"SELECT COUNT(distinct i.isomer_id) " +
				"FROM FA_scan_species_has_sub_species as h, sub_species as ss, isomer as i " +
				"WHERE h.l_FA_scan_species_id = ? and h.l_sub_species_id = ss.sub_species_id and i.l_sub_species_id = ss.sub_species_id;",
				new Object[]{id});
	}

    /**
     *
     * @param id The database id of the FA scan specie
     * @return A string of fatty acid chain names concatenated by  " + "
     */
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


    /**
     *
     * @param id  The database id of the FA scan specie
     * @return A list of cross reference object each one a link to an external resource which has this FA scan specie in it.
     */
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

    /**
     *
     * @param id  The database id of the FA scan specie
     * @return  A list of paper objects each one representing a single paper with pmid, abstract and other information
     */
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

    /**
     *
     * @param id  The database id of the FA scan specie
     * @return A list of simple sub specie objects
     */
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

    @SuppressWarnings("unchecked")
    @Override
    public List<MS1SearchRowResult> getMS1SearchResult(float mass, AdductIons adductIon, float tolerance, boolean identified) {
        double inferredMass = mass - adductIon.getMass();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        if(identified)
            return jdbcTemplate.query(
                    "SELECT  f.FA_scan_species_id as itemId, f.name, f.identified, s.fa_carbons, s.fa_double_bonds, ? as mass, c.exact_mass + ? as res_mass, ? as adductIon, c.formula, sc.code, 'specie' as type " +
                    "FROM FA_scan_species as f, species as s, composition as c, sub_class as sc " +
                    "WHERE f.l_species_id = s.species_id " +
                    "AND s.l_composition_id = c.composition_id " +
                    "AND s.l_sub_class_id = sc.sub_class_id " +
                    "AND f.identified = TRUE " +
                    "AND c.exact_mass <= ? + ? " +
                    "AND c.exact_mass >= ? - ?;",
                    new Object[] { mass, adductIon.getMass(), adductIon.getName(), inferredMass, tolerance, inferredMass, tolerance }, new MS1SearchRowResultMapper());
        else
            return jdbcTemplate.query(
                    "SELECT  f.FA_scan_species_id as itemId, f.name, f.identified, s.fa_carbons, s.fa_double_bonds, ? as mass, c.exact_mass + ? as res_mass, ? as adductIon, c.formula, sc.code, 'specie' as type " +
                    "FROM FA_scan_species as f, species as s, composition as c, sub_class as sc " +
                    "WHERE f.l_species_id = s.species_id " +
                    "AND s.l_composition_id = c.composition_id " +
                    "AND s.l_sub_class_id = sc.sub_class_id " +
                    "AND c.exact_mass <= ? + ? " +
                    "AND c.exact_mass >= ? - ?;",
                    new Object[] { mass, adductIon.getMass(), adductIon.getName(), inferredMass, tolerance, inferredMass, tolerance }, new MS1SearchRowResultMapper());
    }

}
