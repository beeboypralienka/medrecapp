/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package medrecapp.Services;

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import medrecapp.Entity.Dokter;
import medrecapp.Entity.Jaminan;
import medrecapp.Entity.Pasien;
import medrecapp.Entity.Perawat;
import medrecapp.Entity.RekamMedis;
import medrecapp.Entity.Spesialis;
import medrecapp.Entity.Staf;
import medrecapp.TabelModel.TabelModelDokter;
import medrecapp.TabelModel.TabelModelJaminan;
import medrecapp.TabelModel.TabelModelPasien;
import medrecapp.TabelModel.TabelModelPerawat;
import medrecapp.TabelModel.TabelModelRekmedAll;
import medrecapp.TabelModel.TabelModelSpesialis;
import medrecapp.TabelModel.TabelModelStaf;

/**
 *
 * @author Fachrul Pralienka BM
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RekamMedisServiceTest {

    private List<RekamMedis> list;
    private Connection connection;
    private RekamMedisService instance;
    private TabelModelRekmedAll tabelModelRekmedAll;
    private String expNoDaftar;
    private String expNoRm;
    private String expNoStaf;
    private String expBagianSpesialis;
    private String expIdJaminan;
    private String expNoDokter;
    private String expNoPerawat;
    private int expNadi;
    private int expTemperatur;
    private int expPernapasan;
    private String expKesadaran;
    private String expAnamnesa;
    private float expTinggiBdn;
    private float expBeratBdn;
    private String expTensiDarah;
    private String expDiagnosis;
    private String expTerapi;
    private String expStatus;
    private String expTglDaftar;
    private String expNmPas;
    private RekamMedis rm;
    private final String getAllRekmed = "SELECT * FROM rekam_medis";

    public RekamMedisServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
//        insertDataSpesialis();
//        insertDataJaminan();
//        insertDataPerawat();
//        insertDataDokter();
//        insertDataStaf();
//        insertDataPasien();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
//        deleteDataRekmed();
//        deleteDataJaminan();
//        deleteDataPerawat();
//        deleteDataDokter();
//        deleteDataStaf();
//        deleteDataPasien();
//        deleteDataSpesialis();
    }

    @Before
    public void setUp() {
        rm = new RekamMedis();
        list = new ArrayList<RekamMedis>();
        instance = new RekamMedisService();
        tabelModelRekmedAll = new TabelModelRekmedAll();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void a_insertDataSpesialis() {
        System.out.println("1. insertDataSpesialis");
//        Insert data spesialis
        Spesialis sp = new Spesialis();
        SpesialisService sps = new SpesialisService();
        sp.setIdSpesialis("Sp.PD");
        sp.setNmSpesialis("Penyakit Dalam");
        sp.setTarifKonsul(50000);
        sps.serviceInsertSpesialis(sp);
//        System.out.println("Spesialis Insert: "+SpesialisDao.hasilInsert);

        TabelModelSpesialis tabelModelSpesialis = new TabelModelSpesialis();
        tabelModelSpesialis.setData(sps.serviceGetAllSpesialisById("Sp.PD"));
        assertEquals("Sp.PD", tabelModelSpesialis.getValueAt(0, 0));
        assertEquals("Penyakit Dalam", tabelModelSpesialis.getValueAt(0, 1));
        assertEquals(50000, tabelModelSpesialis.getValueAt(0, 2));
    }

    @Test
    public void b_insertDataJaminan() {
        System.out.println("2. insertDataJaminan");
//        Insert data jaminan
        Jaminan jm = new Jaminan();
        JaminanService jms = new JaminanService();
        jm.setIdJaminan("KJS");
        jm.setNmJaminan("Kartu Jakarta Sehat");
        jm.setKetJaminan("Jaminan Kesehatan untuk warga Jakarta yang kurang mampu");
        jms.serviceInsertJaminan(jm);
//        System.out.println("Jaminan Insert: "+JaminanDao.hasilInsert);

        TabelModelJaminan tabelModelJaminan = new TabelModelJaminan();
        tabelModelJaminan.setData(jms.serviceGetAllJaminanById("KJS"));
        assertEquals("KJS", tabelModelJaminan.getValueAt(0, 0));
        assertEquals("Kartu Jakarta Sehat", tabelModelJaminan.getValueAt(0, 1));
        assertEquals("Jaminan Kesehatan untuk warga Jakarta yang kurang mampu", tabelModelJaminan.getValueAt(0, 2));
    }

    @Test
    public void c_insertDataPerawat() {
        System.out.println("3. insertDataPerawat");
//        Insert data perawat
        Perawat p = new Perawat();
        PerawatService ps = new PerawatService();
        p.setNoPerawat("PER.003");
        p.setNmPerawat("Fitriya Rahmawati");
        p.setTglKerjaPer("2009-09-09");
        p.setPerSpesialis("Sp.PD");
        ps.serviceInsertPerawat(p);
//        System.out.println("Perawat Insert: "+PerawatDao.hasilInsertPerawat);

        TabelModelPerawat tabelModelPerawat = new TabelModelPerawat();
        tabelModelPerawat.setData(ps.serviceGetAllDataPerawatByNo("PER.003"));
        assertEquals("Fitriya Rahmawati", tabelModelPerawat.getValueAt(0, 1));
        assertEquals("2009-09-09", tabelModelPerawat.getValueAt(0, 2));
        assertEquals("Sp.PD", tabelModelPerawat.getValueAt(0, 3));
    }

    @Test
    public void d_insertDataDokter() {
        System.out.println("4. insertDataDokter");
//        Insert data dokter
        Dokter d = new Dokter();
        DokterService ds = new DokterService();
        d.setNoDokter("DOK.003");
        d.setNmDokter("HARYONO");
        d.setIdSpesialis("Sp.PD");
        d.setTglKerjaDok("2008-01-02");
        d.setAlamatDok("Jakarta");
        ds.serviceInsertDokter(d);
//        System.out.println("Dokter Insert: "+DokterDao.hasilInsert);

        TabelModelDokter tabelModelDokter = new TabelModelDokter();
        tabelModelDokter.setData(ds.serviceGetAllDokterByNo("DOK.003"));
        assertEquals("HARYONO", tabelModelDokter.getValueAt(0, 1));
        assertEquals("Sp.PD", tabelModelDokter.getValueAt(0, 2));
        assertEquals("2008-01-02", tabelModelDokter.getValueAt(0, 3));
        assertEquals("Jakarta", tabelModelDokter.getValueAt(0, 4));
    }

    @Test
    public void e_insertDataStaf() {
        System.out.println("5. insertDataStaf");
//        Insert data staf
        Staf sf = new Staf();
        StafService sfs = new StafService();
        sf.setNoStaf("STF.003");
        sf.setNmStaf("Asnar Sudirja");
        sf.setAlamatStaf("Jakarta Timur");
        sfs.serviceInsertStaf(sf);
//        System.out.println("Staf Insert: "+StafDao.hasilInsert);

        TabelModelStaf tabelModelStaf = new TabelModelStaf();
        tabelModelStaf.setData(sfs.serviceGetAllStafByNo("STF.003"));
        assertEquals("STF.003", tabelModelStaf.getValueAt(0, 0));
        assertEquals("Asnar Sudirja", tabelModelStaf.getValueAt(0, 1));
        assertEquals("Jakarta Timur", tabelModelStaf.getValueAt(0, 2));
    }

    @Test
    public void f_insertDataPasien() {
        System.out.println("6. insertDataPasien");
//        Insert data pasien
        Pasien pn = new Pasien();
        PasienService pns = new PasienService();
        pn.setNoRm("000003");
        pn.setNmPas("Udin Samsudin");
        pn.setJkPas("L");
        pn.setTglLahir("1990-09-04");
        pn.setAgama("Islam");
        pn.setAlamatPas("Malang");
        pns.serviceInsertPasien(pn);
//        System.out.println("Pasien Insert: "+PasienDao.hasilInsert);

        TabelModelPasien tabelModelPasien = new TabelModelPasien();
        tabelModelPasien.setData(pns.serviceGetAllPasienByNoRm("000003"));
        assertEquals("Udin Samsudin", tabelModelPasien.getValueAt(0, 1));
        assertEquals("L", tabelModelPasien.getValueAt(0, 2));
        assertEquals("1990-09-04", tabelModelPasien.getValueAt(0, 3));
        assertEquals("Islam", tabelModelPasien.getValueAt(0, 4));
        assertEquals("Malang", tabelModelPasien.getValueAt(0, 5));
    }

    @Test
    public void g_insertRekamMedis() {
        System.out.println("--------------------------------------");
        System.out.println("7. serviceInsertRekamMedis");
        expNoDaftar = "090920130001";
        expNoRm = "000003";
        expNoStaf = "STF.003";
        expBagianSpesialis = "Sp.PD";
        expIdJaminan = "KJS";
        expNoDokter = "DOK.003";
        expStatus = "Antri";
        expTglDaftar = "2013-09-09";

        rm.setNoDaftar(expNoDaftar);
        rm.setNoRm(expNoRm);
        rm.setNoStaf(expNoStaf);
        rm.setBagianSpesialis(expBagianSpesialis);
        rm.setIdJaminan(expIdJaminan);
        rm.setNoDokter(expNoDokter);
        rm.setStatus(expStatus);
        rm.setTglDaftar(expTglDaftar);

        instance.serviceInsertRekamMedis(rm);

        tabelModelRekmedAll.setData(instance.serviceGetAllAtributRekmedByNoDaftar(expNoDaftar));
        assertEquals(expNoDaftar, tabelModelRekmedAll.getValueAt(0, 0));
        assertEquals(expNoRm, tabelModelRekmedAll.getValueAt(0, 1));
        assertEquals(expNoStaf, tabelModelRekmedAll.getValueAt(0, 2));
        assertEquals(expBagianSpesialis, tabelModelRekmedAll.getValueAt(0, 3));
        assertEquals(expIdJaminan, tabelModelRekmedAll.getValueAt(0, 4));
        assertEquals(expNoDokter, tabelModelRekmedAll.getValueAt(0, 5));
        assertEquals(expStatus, tabelModelRekmedAll.getValueAt(0, 6));
        assertEquals(expTglDaftar, tabelModelRekmedAll.getValueAt(0, 7));
    }

    @Test
    public void h_updatePasienMasukPoli() {
        System.out.println("8. serviceUpdatePasienMasukPoli");
        expNoDaftar = "090920130001";
        instance.serviceUpdatePasienMasukPoli(expNoDaftar);

        tabelModelRekmedAll.setData(instance.serviceGetAllAtributRekmedByNoDaftar(expNoDaftar));
        assertEquals("Masuk Poli", tabelModelRekmedAll.getValueAt(0, 6));
    }

    @Test
    public void i_updatePemeriksaanAwal() {
        System.out.println("9. serviceUpdatePemeriksaanAwal");
        expNoDaftar = "090920130001";
        expNoPerawat = "PER.003";
        expNadi = 20;
        expTemperatur = 30;
        expPernapasan = 50;
//         Compos Mentis, Apatis, Delirium, Sumnoleh, Stupor, Coma
        expKesadaran = "Apatis";
        expTinggiBdn = 174;
        expBeratBdn = 65;
        expTensiDarah = "20/78";

        rm.setNoPerawat(expNoPerawat);
        rm.setNadi(expNadi);
        rm.setTemperatur(expTemperatur);
        rm.setPernapasan(expPernapasan);
        rm.setKesadaran(expKesadaran);
        rm.setTinggiBdn(expTinggiBdn);
        rm.setBeratBdn(expBeratBdn);
        rm.setTensiDarah(expTensiDarah);

        instance.serviceUpdatePemeriksaanAwal(rm, expNoDaftar);

        tabelModelRekmedAll.setData(instance.serviceGetAllAtributRekmedByNoDaftar(expNoDaftar));
        assertEquals(expNoPerawat, tabelModelRekmedAll.getValueAt(0, 8));
        assertEquals(expBeratBdn, tabelModelRekmedAll.getValueAt(0, 9));
        assertEquals(expTinggiBdn, tabelModelRekmedAll.getValueAt(0, 10));
        assertEquals(expTensiDarah, tabelModelRekmedAll.getValueAt(0, 11));
        assertEquals(expNadi, tabelModelRekmedAll.getValueAt(0, 12));
        assertEquals(expTemperatur, tabelModelRekmedAll.getValueAt(0, 13));
        assertEquals(expPernapasan, tabelModelRekmedAll.getValueAt(0, 14));
        assertEquals(expKesadaran, tabelModelRekmedAll.getValueAt(0, 15));
    }

    @Test
    public void j_updatePemeriksaanLanjutan() {
        System.out.println("10. serviceUpdatePemeriksaanLanjutan");
        expNoDaftar = "090920130001";
        expAnamnesa = "Sakit kepala, sakit perut, mual-mual";
        expDiagnosis = "Radang Lambung";
        expTerapi = "Perlu diberikan terapi energi";

        rm.setAnamnesa(expAnamnesa);
        rm.setDiagnosis(expDiagnosis);
        rm.setTerapi(expTerapi);
        instance.serviceUpdatePemeriksaanLanjutan(rm, expNoDaftar);

        tabelModelRekmedAll.setData(instance.serviceGetAllAtributRekmedByNoDaftar(expNoDaftar));
        assertEquals(expAnamnesa, tabelModelRekmedAll.getValueAt(0, 16));
        assertEquals(expDiagnosis, tabelModelRekmedAll.getValueAt(0, 17));
        assertEquals(expTerapi, tabelModelRekmedAll.getValueAt(0, 18));
    }

    @Test
    public void k_getAllRekamMedis() {
        System.out.println("11. serviceGetAllRekamMedis");

        tabelModelRekmedAll.setData(instance.serviceGetAllAtributRekmed());
        try{
            Statement s = (Statement) connection.createStatement();
            ResultSet rs = s.executeQuery(getAllRekmed);
            while(rs.next()){
                rm.setNoDaftar(rs.getString("no_daftar"));
                rm.setNoRm(rs.getString("no_rm"));
                rm.setNoStaf(rs.getString("no_staf"));
                rm.setBagianSpesialis(rs.getString("bagian_spesialis"));
                rm.setIdJaminan(rs.getString("id_jaminan"));
                rm.setNoDokter(rs.getString("no_dokter"));
                rm.setStatus(rs.getString("status"));
                rm.setTglDaftar(rs.getString("tgl_daftar"));

                rm.setNoPerawat(rs.getString("no_perawat"));
                rm.setBeratBdn(rs.getFloat("berat_bdn"));
                rm.setTinggiBdn(rs.getFloat("tinggi_bdn"));
                rm.setTensiDarah(rs.getString("tensi_darah"));
                rm.setNadi(rs.getInt("nadi"));
                rm.setTemperatur(rs.getInt("temperatur"));
                rm.setPernapasan(rs.getInt("pernapasan"));
                rm.setKesadaran(rs.getString("kesadarasn"));

                rm.setAnamnesa(rs.getString("anamnesa"));
                rm.setDiagnosis(rs.getString("diagnosis"));
                rm.setTerapi(rs.getString("terapi"));
                list.add(rm);
            }
            rs.close();
            s.close();
        }catch(Throwable t){
            // Sengaja dikosongin dulu
        }

        for(int i=0; i<list.size(); i++){
            assertEquals(list.get(i).getNoDaftar(), tabelModelRekmedAll.getValueAt(i, 0));
            assertEquals(list.get(i).getNoRm(), tabelModelRekmedAll.getValueAt(i, 1));
            assertEquals(list.get(i).getNoStaf(), tabelModelRekmedAll.getValueAt(i, 2));
            assertEquals(list.get(i).getBagianSpesialis(), tabelModelRekmedAll.getValueAt(i, 3));
            assertEquals(list.get(i).getIdJaminan(), tabelModelRekmedAll.getValueAt(i, 4));
            assertEquals(list.get(i).getNoDokter(), tabelModelRekmedAll.getValueAt(i, 5));
            assertEquals(list.get(i).getStatus(), tabelModelRekmedAll.getValueAt(i, 6));
            assertEquals(list.get(i).getTglDaftar(), tabelModelRekmedAll.getValueAt(i, 7));

            assertEquals(list.get(i).getNoPerawat(), tabelModelRekmedAll.getValueAt(i, 8));
            assertEquals(list.get(i).getBeratBdn(), tabelModelRekmedAll.getValueAt(i, 9));
            assertEquals(list.get(i).getTinggiBdn(), tabelModelRekmedAll.getValueAt(i, 10));
            assertEquals(list.get(i).getTensiDarah(), tabelModelRekmedAll.getValueAt(i, 11));
            assertEquals(list.get(i).getNadi(), tabelModelRekmedAll.getValueAt(i, 12));
            assertEquals(list.get(i).getTemperatur(), tabelModelRekmedAll.getValueAt(i, 13));
            assertEquals(list.get(i).getPernapasan(), tabelModelRekmedAll.getValueAt(i, 14));
            assertEquals(list.get(i).getKesadaran(), tabelModelRekmedAll.getValueAt(i, 15));

            assertEquals(list.get(i).getAnamnesa(), tabelModelRekmedAll.getValueAt(i, 16));
            assertEquals(list.get(i).getDiagnosis(), tabelModelRekmedAll.getValueAt(i, 17));
            assertEquals(list.get(i).getTerapi(), tabelModelRekmedAll.getValueAt(i, 18));
        }
    }

    @Test
    public void l_deleteDataRekmed() {
        System.out.println("--------------------------------------");
        System.out.println("12. deleteDataRekmed");
//        delete data reka medis
        RekamMedisService rms = new RekamMedisService();
        rms.serviceDeleteRekmed();
//        System.out.println("Rekmed Delete: "+RekamMedisDao.hasilDeleteRekamMedis);

        tabelModelRekmedAll.setData(instance.serviceGetAllAtributRekmed());
        assertEquals(0, tabelModelRekmedAll.getRowCount());
    }

    @Test
    public void m_deleteDataJaminan() {
        System.out.println("13. deleteDataJaminan");
//        delete data jaminan
        JaminanService jms = new JaminanService();
        jms.serviceDeleteJaminan("KJS");
//        System.out.println("Jaminan Delete: "+JaminanDao.hasilDelete);

        TabelModelJaminan tabelModelJaminan = new TabelModelJaminan();
        tabelModelJaminan.setData(jms.serviceGetAllJaminanById("KJS"));
        assertEquals(0, tabelModelJaminan.getRowCount());
    }

    @Test
    public void n_deleteDataPerawat() {
        System.out.println("14. deleteDataPerawat");
//        delete data perawat
        PerawatService ps = new PerawatService();
        ps.serviceDeletePerawat("PER.003");
//        System.out.println("Perawat Delete: "+PerawatDao.hasilDeletePerawat);

        TabelModelPerawat tabelModelPerawat = new TabelModelPerawat();
        tabelModelPerawat.setData(ps.serviceGetAllDataPerawatByNo("PER.003"));
        assertEquals(0, tabelModelPerawat.getRowCount());
    }

    @Test
    public void o_deleteDataDokter() {
        System.out.println("15. deleteDataDokter");
//        delete data dokter
        DokterService ds = new DokterService();
        ds.serviceDeleteDokter("DOK.003");
//        System.out.println("Dokter Delete: "+DokterDao.hasilDelete);

        TabelModelDokter tabelModelDokter = new TabelModelDokter();
        tabelModelDokter.setData(ds.serviceGetAllDokterByNo("DOK.003"));
        assertEquals(0, tabelModelDokter.getRowCount());
    }

    @Test
    public void p_deleteDataStaf() {
        System.out.println("16. deleteDataStaf");
//        delete data staf
        StafService sfs = new StafService();
        sfs.serviceDeleteStaf("STF.003");
//        System.out.println("Staf Delete: "+StafDao.hasilDelete);

        TabelModelStaf tabelModelStaf = new TabelModelStaf();
        tabelModelStaf.setData(sfs.serviceGetAllStafByNo("STF.003"));
        assertEquals(0, tabelModelStaf.getRowCount());
    }

    @Test
    public void q_deleteDataPasien() {
        System.out.println("17. deleteDataPasien");
//        delete data pasien
        PasienService pns = new PasienService();
        pns.serviceDeletePasien("000003");
//        System.out.println("Pasien Delete: "+PasienDao.hasilDelete);

        TabelModelPasien tabelModelPasien = new TabelModelPasien();
        tabelModelPasien.setData(pns.serviceGetAllPasienByNoRm("000003"));
        assertEquals(0, tabelModelPasien.getRowCount());
    }

    @Test
    public void r_deleteDataSpesialis() {
        System.out.println("18. deleteDataSpesialis");
//        delete data spesialis
        SpesialisService ss = new SpesialisService();
        ss.serviceDeleteSpesialis("Sp.PD");
//        System.out.println("Spesialis Delete: "+SpesialisDao.hasilDelete);

        TabelModelSpesialis tabelModelSpesialis = new TabelModelSpesialis();
        tabelModelSpesialis.setData(ss.serviceGetAllSpesialisById("Sp.PD"));
        assertEquals(0, tabelModelSpesialis.getRowCount());
    }
}
