/**
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 * @author Joe Foster <jfoster@ebi.ac.uk>
 *
 * An enumeration of the structure heirarchy
 * Category - Identical to LIPID MAPS Categor e.g. Glycerophospholipids
 * Main class -  Identical to LIPID MAPYS main class e.g. glycherophosphocholines
 * Sub class -  similar to lipid amps sub class except 1acyl,2alky & 1,alkyl,2acly species are collapsed into monoacyl, monoalkyl
 * Specie - Typical mass spec identification, sub class known, linkages known, total number of FA carbon and double bonds known only.
 * Fatty acid scan specie - Individual fatty acid number of carbons and dobule bonds known, position of fatty acid unknown
 * Sub specie - Fatty acid sn positions known
 * Isomer- Double bond/ modification positions known.
 */

package uk.ac.ebi.lipidhome.core.model;

public enum LipidType {
	NONE("none"),
	CATEGORY("category"),
	MAIN_CLASS("mainClass"),
	SUB_CLASS("subClass"),
	SPECIE("specie"),
	FA_SCAN_SPECIE("faScanSpecie"),
	SUB_SPECIE("subSpecie"),
	ISOMER("isomer");
	
	private String type;
	
	LipidType(String type){
		this.type = type;
	}
	
	public boolean isType(String type){
		return this.type.equals(type);
	}
	
	public static LipidType getType(String type){
		for(LipidType ltype : LipidType.values()){
			if(ltype.isType(type)) return ltype;
		}
		return LipidType.NONE;
	}
}
