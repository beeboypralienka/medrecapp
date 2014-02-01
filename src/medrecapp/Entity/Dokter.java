/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Entity;

/**
 *
 * @author Fachrul Pralienka BM
 */
public class Dokter {

    private String noDokter;
    private String nmDokter;
    private String idSpesialis;
    private String tglKerjaDok;
    private String alamatDok;

    public String getAlamatDok() {
        return alamatDok;
    }

    public void setAlamatDok(String alamatDok) {
        this.alamatDok = alamatDok;
    }

    public String getIdSpesialis() {
        return idSpesialis;
    }

    public void setIdSpesialis(String idSpesialis) {
        this.idSpesialis = idSpesialis;
    }

    public String getNmDokter() {
        return nmDokter;
    }

    public void setNmDokter(String nmDokter) {
        this.nmDokter = nmDokter;
    }

    public String getNoDokter() {
        return noDokter;
    }

    public void setNoDokter(String noDokter) {
        this.noDokter = noDokter;
    }

    public String getTglKerjaDok() {
        return tglKerjaDok;
    }

    public void setTglKerjaDok(String tglKerjaDok) {
        this.tglKerjaDok = tglKerjaDok;
    }
     
}
