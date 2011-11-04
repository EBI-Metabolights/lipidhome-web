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
