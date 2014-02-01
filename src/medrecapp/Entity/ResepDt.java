/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Entity;

/**
 *
 * @author asFachrul Pralienka BMus
 */
public class ResepDt {

    private String noResep;
    private String idObat;
    private String satuanKons;
    private String dosisKons;
    private int jumlah;

    public String getDosisKons() {
        return dosisKons;
    }

    public void setDosisKons(String dosisKons) {
        this.dosisKons = dosisKons;
    }

    public String getIdObat() {
        return idObat;
    }

    public void setIdObat(String idObat) {
        this.idObat = idObat;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getNoResep() {
        return noResep;
    }

    public void setNoResep(String noResep) {
        this.noResep = noResep;
    }

    public String getSatuanKons() {
        return satuanKons;
    }

    public void setSatuanKons(String satuanKons) {
        this.satuanKons = satuanKons;
    }

}
