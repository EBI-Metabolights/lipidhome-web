/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 *  This class contians the implementations of all the emthods defined in UtilitiesService. These services are not
 *  related to a single hierarchy structure level and so have been lumped tog ether in this class.
 */
package uk.ac.ebi.lipidhome.service.impl;

import uk.ac.ebi.lipidhome.core.dao.*;
import uk.ac.ebi.lipidhome.core.model.*;
import uk.ac.ebi.lipidhome.service.UtilitiesService;
import uk.ac.ebi.lipidhome.service.result.ListConverter;
import uk.ac.ebi.lipidhome.service.result.Result;
import uk.ac.ebi.lipidhome.service.result.model.AdductIonItem;
import uk.ac.ebi.lipidhome.service.result.model.BaseSearchItem;
import uk.ac.ebi.lipidhome.service.result.model.ResultObjectList;
import uk.ac.ebi.lipidhome.service.util.HierarchyNode;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class UtilitiesServiceImpl extends LipidService implements UtilitiesService{

	public UtilitiesServiceImpl(){
		
	}

    /**
     *
     * @param query An SQL query string with the appropriate place holders.
     * @param type The structure hierarchy level to be searched
     * @param start The starting record number  (used for paging the data)
     * @param limit The limit defining how many results to show per page.
     * @param page ??
     * @param callback ??
     * @return A Response object of json formatted BaseSearchItem results.
     */
	@Override
	public Response doSearch(String query, String type, Long start, Long limit, Long page, String callback) {

        List<BaseSearchItem> list = new ArrayList<BaseSearchItem>();
        long totalCount = 0;

        switch (LipidType.getType(type)){
            case CATEGORY:
                CategoryDao<Category> categoryDao = getDaoFactory().getCategoryDao();
                try{
                    list = categoryDao.getCategoryByNameLike(query, start, limit);
                    totalCount = categoryDao.getCategoryCountByNameLike(query);
                } catch (RuntimeException e) {}
                break;
            case MAIN_CLASS:
                MainClassDao<MainClass> mainClassDao = getDaoFactory().getMainClassDao();
                try{
                    list = mainClassDao.getMainClassByNameLike(query, start, limit);
                    totalCount = mainClassDao.getMainClassCountByNameLike(query);
                } catch (RuntimeException e) {}
                break;
            case SUB_CLASS:
                SubClassDao<SubClass> subClassDao = getDaoFactory().getSubClassDao();
                try{
                    list = subClassDao.getSubClassByNameLike(query, start, limit);
                    totalCount = subClassDao.getSubClassCountByNameLike(query);
                } catch (RuntimeException e) {}
                break;
            case SPECIE:
                SpecieDao<Specie> specieDao = getDaoFactory().getSpecieDao();
                try{
                    list = specieDao.getSpecieByNameLike(query, start, limit);
                    totalCount = specieDao.getSpecieCountByNameLike(query);
                } catch (RuntimeException e) {}
                break;
            case FA_SCAN_SPECIE:
                FAScanSpecieDao<FAScanSpecie> faScanSpecieDao = getDaoFactory().getFAScanSpecieDao();
                try{
                    list = faScanSpecieDao.getFAScanSpecieByNameLike(query, start, limit);
                    totalCount = faScanSpecieDao.getFAScanSpecieCountByNameLike(query);
                } catch (RuntimeException e) {}
                break;
            case SUB_SPECIE:
                SubSpecieDao<SubSpecie> subSpecieDao = getDaoFactory().getSubSpecieDao();
                try {
                    list = subSpecieDao.getSubSpeciesByNameLike(query, start, limit);
                    totalCount =  subSpecieDao.getSubSpeciesCountByNameLike(query);
                } catch (RuntimeException e) {}
                break;
            case ISOMER:
                IsomerDao<Isomer> isomerDao = getDaoFactory().getIsomerDao();
                try {
                    list = isomerDao.getIsomerByNameLike(query, start, limit);
                    totalCount =  isomerDao.getIsomerCountByNameLike(query);
                } catch (RuntimeException e) {
                    System.out.println("ISOMER: " + e.getMessage());
                }
                break;

            default:    // SEARCH FOR ALL :)
                list.addAll(getDaoFactory().getCategoryDao().getCategoryByNameLike(query));
                list.addAll(getDaoFactory().getMainClassDao().getMainClassByNameLike(query));
                list.addAll(getDaoFactory().getSubClassDao().getSubClassByNameLike(query));
                list.addAll(getDaoFactory().getSpecieDao().getSpecieByNameLike(query));
                list.addAll(getDaoFactory().getFAScanSpecieDao().getFAScanSpecieByNameLike(query));
                list.addAll(getDaoFactory().getSubSpecieDao().getSubSpeciesByNameLike(query));
                list.addAll(getDaoFactory().getIsomerDao().getIsomerByNameLike(query));

                totalCount = list.size();
                if(totalCount>0){
                    Long end = start + limit;
                    end = (end < totalCount) ? end : totalCount-1;

                    Collections.sort(list);
                    list = list.subList(safeLongToInt(start), safeLongToInt(end));
                }
        }

        ListConverter<BaseSearchItem> converter = new ListConverter<BaseSearchItem>();
        Result result = new Result(converter.getLipidObjectList(list));
        result.setTotalCount(totalCount);

		return result2Response(result, callback);
	}

    private static int safeLongToInt(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException (l + " cannot be cast to int without changing its value.");
        }
        return (int) l;
    }

    /**
     * When a path is obtained for a particular type all the parent nodes of that record are retrieved and built into
     * the hierarchy tree in a cascading sort  of effect due to the case/switch that does not break until the highest
     * level type (category) is retrieved and the full parent tree of the item created.
     *
     * @param itemId The database ID of the record of interest
     * @param name The name of the record of interest
     * @param identified The identified status of the record
     * @param type The structure hierarchy level of the record.
     * @return
     */
	@Override
	public Response getPathsTo(Long itemId, String name, Boolean identified, String type) {
		BaseSearchItem bsi = new BaseSearchItem(itemId, name, identified, type); 
		HierarchyNode tree = new HierarchyNode(bsi);
		
		switch(LipidType.getType(type)){
		case ISOMER:
			for(HierarchyNode isomer : tree.getChildren(LipidType.ISOMER)){
				IsomerDao<Isomer> isomerDao = getDaoFactory().getIsomerDao();
                isomer.addChildren(isomerDao.getIsomerParents(isomer.getItemId()));
			}
		case SUB_SPECIE:
			for(HierarchyNode subSpecie : tree.getChildren(LipidType.SUB_SPECIE)){
				SubSpecieDao<SubSpecie> subSpecieDao = getDaoFactory().getSubSpecieDao();
				subSpecie.addChildren(subSpecieDao.getSubSpecieParents(subSpecie.getItemId()));
			}
		case FA_SCAN_SPECIE:
			for(HierarchyNode faScanSpecie : tree.getChildren(LipidType.FA_SCAN_SPECIE)){
				System.out.println(faScanSpecie.getItemId());
				FAScanSpecieDao<FAScanSpecie> fassDao = getDaoFactory().getFAScanSpecieDao();
				faScanSpecie.addChildren(fassDao.getFAScanSpecieParents(faScanSpecie.getItemId()));
			}
		case SPECIE:
			for(HierarchyNode specie : tree.getChildren(LipidType.SPECIE)){
				SpecieDao<Specie> specieDao = getDaoFactory().getSpecieDao();
				specie.addChildren(specieDao.getSpecieParents(specie.getItemId()));
			}
		case SUB_CLASS:
			for(HierarchyNode subClass : tree.getChildren(LipidType.SUB_CLASS)){
				SubClassDao<SubClass> subClassDao = getDaoFactory().getSubClassDao();
				subClass.addChildren(subClassDao.getSubClassParents(subClass.getItemId()));
			}
		case MAIN_CLASS:
			for(HierarchyNode mainClass : tree.getChildren(LipidType.MAIN_CLASS)){
				MainClassDao<MainClass> mainClassDao = getDaoFactory().getMainClassDao();
				mainClass.addChildren(mainClassDao.getMainClassParents(mainClass.getItemId()));
			}
			break;
			
		default:
			//throw exception
		}
		
		Result result = new Result(tree.toFlatLists());
		return result2Response(result);
	}

    @Override
    public Response getAdductIons() {
        List<AdductIonItem> list = new ArrayList<AdductIonItem>();
        for (AdductIons adductIon : AdductIons.values()) {
            AdductIonItem aii = new AdductIonItem(adductIon.getItemId(), adductIon.getName(), adductIon.getMass());
            list.add(aii);
        }

        ListConverter<AdductIonItem> converter = new ListConverter<AdductIonItem>();
        ResultObjectList adductIonsList = converter.getLipidObjectList(list);
        return result2Response(new Result(adductIonsList));
    }
}