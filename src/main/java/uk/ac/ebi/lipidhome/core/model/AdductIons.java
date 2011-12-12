package uk.ac.ebi.lipidhome.core.model;

public enum AdductIons {
    NEUTRAL(0.0, "[M]", 1L),
    HPlus (1.007825032, "[M+H]+", 2L),
    KPlus (38.96370668,"[M+K]+", 3L),
    NaPlus (22.98976928,"[M+Na]+",4L),
    NH4Plus (17.026549096,"[M+NH4]+", 5L),
    HMinus (-1.007825032,"[M-H]-", 6L),
    ClMinus (34.96885268,"[M-Cl]-", 7L),
    BrMinus (78.9183371,"[M-Br]-", 8L);
    //AceMinus (-1.0078250732,"[M-Ace]-", 9L);

    private Double mass;
    private String name;
    private Long itemId;

    AdductIons(Double mass, String name, Long itemId) {
        this.mass = mass;
        this.name = name;
        this.itemId = itemId;
    }

    public Long getItemId() {
        return itemId;
    }

    public Double getMass() {
        return mass;
    }

    public String getName() {
        return name;
    }

    public boolean isAdductedIon(String name){
        return this.name.equals(name);
    }

    public boolean isAdductedIon(Long itemId){
        return this.itemId == itemId;
    }

    public static AdductIons getAdductIon(String name){
        for (AdductIons adductIons : AdductIons.values()) {
            if(adductIons.isAdductedIon(name)) return adductIons;
        }
        return NEUTRAL;
    }

    public static AdductIons getAdductIon(Long itemId){
        for (AdductIons adductIons : AdductIons.values()) {
            if(adductIons.isAdductedIon(itemId)) return adductIons;
        }
        return NEUTRAL;
    }
}