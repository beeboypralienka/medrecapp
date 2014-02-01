/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Entity;

/**
 *
 * @author Fachrul Pralienka BM
 */
public class Perawat {

    private String noPerawat;
    private String nmPerawat;
    private String tglKerjaPer;
    private String perSpesialis;

    public String getPerSpesialis() {
        return perSpesialis;
    }

    public void setPerSpesialis(String perSpesialis) {
        this.perSpesialis = perSpesialis;
    }

    public String getNmPerawat() {
        return nmPerawat;
    }

    public void setNmPerawat(String nmPerawat) {
        this.nmPerawat = nmPerawat;
    }

    public String getNoPerawat() {
        return noPerawat;
    }

    public void setNoPerawat(String noPerawat) {
        this.noPerawat = noPerawat;
    }

    public String getTglKerjaPer() {
        return tglKerjaPer;
    }

    public void setTglKerjaPer(String tglKerjaPer) {
        this.tglKerjaPer = tglKerjaPer;
    }


}
