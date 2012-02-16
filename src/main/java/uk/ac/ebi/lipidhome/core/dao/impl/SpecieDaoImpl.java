package uk.ac.ebi.lipidhome.core.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uk.ac.ebi.lipidhome.core.dao.SpecieDao;
import uk.ac.ebi.lipidhome.core.model.AdductIons;
import uk.ac.ebi.lipidhome.core.model.CrossReference;
import uk.ac.ebi.lipidhome.core.model.Paper;
import uk.ac.ebi.lipidhome.core.model.Specie;
import uk.ac.ebi.lipidhome.service.mapper.*;
import uk.ac.ebi.lipidhome.service.result.model.BaseSearchItem;
import uk.ac.ebi.lipidhome.service.result.model.MS1SearchRowResult;
import uk.ac.ebi.lipidhome.service.result.model.SimpleFAScanSpecie;

import java.util.List;

/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * The specieDaoImpl contains all the methods to access specie related information from the DataSource.
 *
 */
@Repository
public class SpecieDaoImpl extends BaseDaoImpl<Specie> implements SpecieDao<Specie>{

    /**
     *
     * @param id  The database id of the specie
     * @return A specie object that is a faithful representation of the species table
     */
	@Override
	public Specie getSpecie(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return (Specie) jdbcTemplate.queryForObject(
				"SELECT s.*, sc.model, c.formula, c.exact_mass " +
				"FROM species as s, composition as c, sub_class as sc " +
				"WHERE s.species_id = ? and s.l_composition_id = c.composition_id and s.l_sub_class_id = sc.sub_class_id;",
				new Object[] { id }, new SpecieMapper());
	}

    /**
     *
     * @param name The name or partial name of the specie to be searched for.
     * @param start The starting index of the result to return, this is used for paging of the data.
     * @param limit The number of records to return
     * @return A list search results
     */
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
    public List<BaseSearchItem> getSpecieByNameLike(String name) {
        name = "%%" + name + "%%";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.query(
				"SELECT species_id AS item_id, name, identified, 'specie' as type " +
				"FROM species " +
				"WHERE name LIKE ? ORDER BY identified DESC, name;",
				new Object[]{ name }, new BaseSearchItemMapper());
    }

    @Override
    public long getSpecieCountByNameLike(String name) {
        name = "%%" + name + "%%";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.queryForLong(
				"SELECT COUNT(species_id) " +
						"FROM species " +
						"WHERE name LIKE ?;",
				new Object[]{name});
    }

    /**
     *
     * @param id  The database id of the specie
     * @return  A list of the parents of this specie (sub classes)
     */
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

    /**
     *
     * @param id The database id of the specie
     * @return The number of distinct FA scan species within this specie.
     */
	@Override
	public int getFAScanSpeciesCountById(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.queryForInt(
				"SELECT count(f.FA_scan_species_id) " +
				"FROM FA_scan_species as f " +
				"WHERE f.l_species_id = ?;",
				new Object[]{id});
	}

    /**
     *
     * @param id The database id of the specie
     * @return The number of distinct sub species within this specie.
     */
	@Override
	public int getSubSpeciesCountById(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.queryForInt(
				"SELECT count(distinct ss.sub_species_id) " +
				"FROM FA_scan_species as f, FA_scan_species_has_sub_species as h, sub_species as ss " +
				"WHERE f.l_species_id = ? and h.l_FA_scan_species_id = f.FA_scan_species_id and h.l_sub_species_id = ss.sub_species_id;",
				new Object[]{id});
	}

    /**
     *
     * @param id The database id of the specie
     * @return The number of distinct isomers within this specie that are cross referenced to another resource.
     */
	@Override
	public int getIsomerCountById(Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate.queryForInt(
				"SELECT count(distinct i.isomer_id) " +
				"FROM FA_scan_species as f, FA_scan_species_has_sub_species as h, sub_species as ss, isomer as i " +
				"WHERE f.l_species_id = ? and h.l_FA_scan_species_id = f.FA_scan_species_id and h.l_sub_species_id = ss.sub_species_id and i.l_sub_species_id = ss.sub_species_id;",
				new Object[]{id});
	}

    /**
     *
     * @param id  The database id of the specie
     * @return A list of cross reference object each one a link to an external resource which has this specie in it.
     */
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

    /**
     *
     * @param id The database id of the specie
     * @return A list of paper objects each one representing a single paper with pmid, abstract and other information
     */
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

    /**
     *
     * @param id The database id of the specie
     * @return A list of simple sub specie objects
     */
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

    @SuppressWarnings("unchecked")
    @Override
    public List<MS1SearchRowResult> getMS1SearchResult(float mass, AdductIons adductIon, float tolerance, boolean identified) {
        double inferredMass = mass - adductIon.getMass();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        if(identified)
            return jdbcTemplate.query(
                    "SELECT  s.species_id as itemId, s.name, s.identified, s.fa_carbons, s.fa_double_bonds, ? as mass, c.exact_mass + ? as res_mass, ? as adductIon, c.formula, sc.code, 'specie' as type " +
                    "FROM species as s, composition as c, sub_class as sc " +
                    "WHERE s.l_composition_id = c.composition_id " +
                    "AND s.l_sub_class_id = sc.sub_class_id " +
                    "AND s.identified = TRUE " +
                    "AND c.exact_mass <= ? + ? " +
                    "AND c.exact_mass >= ? - ?;",
                    new Object[] { mass, adductIon.getMass(), adductIon.getName(), inferredMass, tolerance, inferredMass, tolerance }, new MS1SearchRowResultMapper());
        else
            return jdbcTemplate.query(
                    "SELECT  s.species_id as itemId, s.name, s.identified, s.fa_carbons, s.fa_double_bonds, ? as mass, c.exact_mass + ? as res_mass, ? as adductIon, c.formula, sc.code, 'specie' as type " +
                    "FROM species as s, composition as c, sub_class as sc " +
                    "WHERE s.l_composition_id = c.composition_id " +
                    "AND s.l_sub_class_id = sc.sub_class_id " +
                    "AND c.exact_mass <= ? + ? " +
                    "AND c.exact_mass >= ? - ?;",
                    new Object[] { mass, adductIon.getMass(), adductIon.getName(), inferredMass, tolerance, inferredMass, tolerance }, new MS1SearchRowResultMapper());
    }
}
