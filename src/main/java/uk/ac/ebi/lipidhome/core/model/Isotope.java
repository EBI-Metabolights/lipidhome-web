package uk.ac.ebi.lipidhome.core.model;

/**
 * Created with IntelliJ IDEA.
 * User: jfoster
 * Date: 15/02/13
 * Time: 10:52
 * To change this template use File | Settings | File Templates.
 */
public class Isotope implements Comparable<Isotope>{
    private Double mass;
    private Double intensity;

    public Isotope() {
    }

    public Isotope(Double mass, Double intensity) {
        this.mass = mass;
        this.intensity = intensity;
    }

    public Double getMass() {
        return mass;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }

    public Double getIntensity() {
        return intensity;
    }

    public void setIntensity(Double intensity) {
        this.intensity = intensity;
    }

    @Override
    public int compareTo(Isotope o) {
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;

        if (this == o) return EQUAL;
        if (this.mass < o.getMass()) return BEFORE;
        if (this.mass > o.getMass()) return AFTER;

        return EQUAL;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Isotope isotope = (Isotope) o;

        if (!mass.equals(isotope.mass)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return mass.hashCode();
    }


}
