package uk.ac.ebi.lipidhome.service.result.model;


public class MS1SearchRowResult extends ResultObject {
    private float mass;
    private boolean identified;
    private int faCarbons;
    private int faDoubleBonds;
    private float resMass;
    private String code;
    private String type;

    public MS1SearchRowResult(){
        super();
    }

    public MS1SearchRowResult(float mass, boolean identified, int faCarbons, int faDoubleBonds, float res_mass, String code, String type) {
        this.mass = mass;
        this.identified = identified;
        this.faCarbons = faCarbons;
        this.faDoubleBonds = faDoubleBonds;
        this.resMass = res_mass;
        this.code = code;
        this.type = type;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public boolean isIdentified() {
        return identified;
    }

    public void setIdentified(boolean identified) {
        this.identified = identified;
    }

    public int getFaCarbons() {
        return faCarbons;
    }

    public void setFaCarbons(int faCarbons) {
        this.faCarbons = faCarbons;
    }

    public int getFaDoubleBonds() {
        return faDoubleBonds;
    }

    public void setFaDoubleBonds(int faDoubleBonds) {
        this.faDoubleBonds = faDoubleBonds;
    }

    public float getResMass() {
        return resMass;
    }

    public void setResMass(float resMass) {
        this.resMass = resMass;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
