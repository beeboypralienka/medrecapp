/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Entity;

/**
 *
 * @author Fachrul Pralienka BM
 */
public class Resep {

    private String noResep;
    private String noDaftar;
    private String tglResep;

    public String getNoDaftar() {
        return noDaftar;
    }

    public void setNoDaftar(String noDaftar) {
        this.noDaftar = noDaftar;
    }

    public String getNoResep() {
        return noResep;
    }

    public void setNoResep(String noResep) {
        this.noResep = noResep;
    }

    public String getTglResep() {
        return tglResep;
    }

    public void setTglResep(String tglResep) {
        this.tglResep = tglResep;
    }

}
