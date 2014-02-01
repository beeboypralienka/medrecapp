/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Entity;

/**
 *
 * @author Fachrul Pralienka BM
 */
public class RekamMedis {

    private String noDaftar;
    private String noRm;
    private String noStaf;
    private String bagianSpesialis;
    private String idJaminan;
    private String noDokter;
    private String noPerawat;
    private int nadi;
    private int temperatur;
    private int pernapasan;
    private String kesadaran;
    private String anamnesa;
    private float tinggiBdn;
    private float beratBdn;
    private String tensiDarah;
    private String diagnosis;
    private String terapi;
    private String status;
    private String tglDaftar;

    /* Entity Tambahan */
    private String nmPas;
    //private String nmDokter;



    public String getBagianSpesialis() {
        return bagianSpesialis;
    }

    public void setBagianSpesialis(String bagianSpesialis) {
        this.bagianSpesialis = bagianSpesialis;
    }

    public String getTglDaftar() {
        return tglDaftar;
    }

    public void setTglDaftar(String tglDaftar) {
        this.tglDaftar = tglDaftar;
    }

    public String getAnamnesa() {
        return anamnesa;
    }

    public void setAnamnesa(String anamnesa) {
        this.anamnesa = anamnesa;
    }

    public float getBeratBdn() {
        return beratBdn;
    }

    public void setBeratBdn(float beratBdn) {
        this.beratBdn = beratBdn;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getIdJaminan() {
        return idJaminan;
    }

    public void setIdJaminan(String idJaminan) {
        this.idJaminan = idJaminan;
    }
    
    public String getKesadaran() {
        return kesadaran;
    }

    public void setKesadaran(String kesadaran) {
        this.kesadaran = kesadaran;
    }

    public int getNadi() {
        return nadi;
    }

    public void setNadi(int nadi) {
        this.nadi = nadi;
    }

    public String getNoDaftar() {
        return noDaftar;
    }

    public void setNoDaftar(String noDaftar) {
        this.noDaftar = noDaftar;
    }

    public String getNoDokter() {
        return noDokter;
    }

    public void setNoDokter(String noDokter) {
        this.noDokter = noDokter;
    }

    public String getNoPerawat() {
        return noPerawat;
    }

    public void setNoPerawat(String noPerawat) {
        this.noPerawat = noPerawat;
    }

    public String getNoRm() {
        return noRm;
    }

    public void setNoRm(String noRm) {
        this.noRm = noRm;
    }

    public String getNoStaf() {
        return noStaf;
    }

    public void setNoStaf(String noStaf) {
        this.noStaf = noStaf;
    }

    public int getPernapasan() {
        return pernapasan;
    }

    public void setPernapasan(int pernapasan) {
        this.pernapasan = pernapasan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTemperatur() {
        return temperatur;
    }

    public void setTemperatur(int temperatur) {
        this.temperatur = temperatur;
    }

    public String getTensiDarah() {
        return tensiDarah;
    }

    public void setTensiDarah(String tensiDarah) {
        this.tensiDarah = tensiDarah;
    }

    public String getTerapi() {
        return terapi;
    }

    public void setTerapi(String terapi) {
        this.terapi = terapi;
    }

    public float getTinggiBdn() {
        return tinggiBdn;
    }

    public void setTinggiBdn(float tinggiBdn) {
        this.tinggiBdn = tinggiBdn;
    }

//    public String getNmDokter() {
//        return nmDokter;
//    }
//
//    public void setNmDokter(String nmDokter) {
//        this.nmDokter = nmDokter;
//    }

    public String getNmPas() {
        return nmPas;
    }

    public void setNmPas(String nmPas) {
        this.nmPas = nmPas;
    }
    
}
