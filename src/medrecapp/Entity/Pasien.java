/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Entity;

/**
 *
 * @author Fachrul Pralienka BM
 */
public class Pasien {

    private String noRm;
    private String nmPas;
    private String jkPas;
    private String tglLahir;
    private String agama;
    private String alamatPas;

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getAlamatPas() {
        return alamatPas;
    }

    public void setAlamatPas(String alamatPas) {
        this.alamatPas = alamatPas;
    }

    public String getJkPas() {
        return jkPas;
    }

    public void setJkPas(String jkPas) {
        this.jkPas = jkPas;
    }

    public String getNmPas() {
        return nmPas;
    }

    public void setNmPas(String nmPas) {
        this.nmPas = nmPas;
    }

    public String getNoRm() {
        return noRm;
    }

    public void setNoRm(String noRm) {
        this.noRm = noRm;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }
    

}
