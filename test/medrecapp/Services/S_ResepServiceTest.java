/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Services;

import java.sql.ResultSet;
import com.mysql.jdbc.Statement;
import medrecapp.TabelModel.TabelModelRekmedAll;
import medrecapp.Entity.RekamMedis;
import medrecapp.TabelModel.TabelModelPasien;
import medrecapp.Entity.Pasien;
import medrecapp.TabelModel.TabelModelStaf;
import medrecapp.Entity.Staf;
import medrecapp.TabelModel.TabelModelDokter;
import medrecapp.Entity.Dokter;
import medrecapp.TabelModel.TabelModelPerawat;
import medrecapp.Entity.Perawat;
import medrecapp.TabelModel.TabelModelJaminan;
import medrecapp.Entity.Jaminan;
import medrecapp.TabelModel.TabelModelSpesialis;
import medrecapp.Entity.Spesialis;
import java.util.ArrayList;
import com.mysql.jdbc.Connection;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import java.util.List;
import medrecapp.Entity.Obat;
import medrecapp.Entity.Resep;
import medrecapp.TabelModel.TabelModelObat;
import medrecapp.TabelModel.TabelModelResep;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Fachrul Pralienka BM
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class S_ResepServiceTest {

    private List<Resep> list;
    private Connection connection;
    private ResepService instance;
    private TabelModelResep tabelModelResep;
    private String expNoDaftar;
    private String expNoResep;
    private String expTglResep;
    private Resep rp;
    private final String getAllResep = "SELECT * FROM resep";

    public S_ResepServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        list = new ArrayList<Resep>();
        rp = new Resep();
        instance = new ResepService();
        tabelModelResep = new TabelModelResep();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void a_insertDataSpesialis() {
        System.out.println("1. insertDataSpesialis");
        Spesialis sp = new Spesialis();
        SpesialisService sps = new SpesialisService();
        sp.setIdSpesialis("Sp.PD");
        sp.setNmSpesialis("Penyakit Dalam");
        sp.setTarifKonsul(50000);
        sps.serviceInsertSpesialis(sp);

        TabelModelSpesialis tabelModelSpesialis = new TabelModelSpesialis();
        tabelModelSpesialis.setData(sps.serviceGetAllSpesialisById("Sp.PD"));
        assertEquals("Sp.PD", tabelModelSpesialis.getValueAt(0, 0));
        assertEquals("Penyakit Dalam", tabelModelSpesialis.getValueAt(0, 1));
        assertEquals(50000, tabelModelSpesialis.getValueAt(0, 2));

    }

    @Test
    public void b_insertDataJaminan() {
        System.out.println("2. insertDataJaminan");
        Jaminan jm = new Jaminan();
        JaminanService jms = new JaminanService();
        jm.setIdJaminan("KJS");
        jm.setNmJaminan("Kartu Jakarta Sehat");
        jm.setKetJaminan("Jaminan Kesehatan untuk warga Jakarta yang kurang mampu");
        jms.serviceInsertJaminan(jm);

        TabelModelJaminan tabelModelJaminan = new TabelModelJaminan();
        tabelModelJaminan.setData(jms.serviceGetAllJaminanById("KJS"));
        assertEquals("KJS", tabelModelJaminan.getValueAt(0, 0));
        assertEquals("Kartu Jakarta Sehat", tabelModelJaminan.getValueAt(0, 1));
        assertEquals("Jaminan Kesehatan untuk warga Jakarta yang kurang mampu", tabelModelJaminan.getValueAt(0, 2));
    }

    @Test
    public void c_insertDataPerawat() {
        System.out.println("3. insertDataPerawat");
        Perawat p = new Perawat();
        PerawatService ps = new PerawatService();
        p.setNoPerawat("PER.003");
        p.setNmPerawat("Fitriya Rahmawati");
        p.setTglKerjaPer("2009-09-09");
        p.setPerSpesialis("Sp.PD");
        ps.serviceInsertPerawat(p);

        TabelModelPerawat tabelModelPerawat = new TabelModelPerawat();
        tabelModelPerawat.setData(ps.serviceGetAllDataPerawatByNo("PER.003"));
        assertEquals("Fitriya Rahmawati", tabelModelPerawat.getValueAt(0, 1));
        assertEquals("2009-09-09", tabelModelPerawat.getValueAt(0, 2));
        assertEquals("Sp.PD", tabelModelPerawat.getValueAt(0, 3));
    }

    @Test
    public void d_insertDataDokter() {
        System.out.println("4. insertDataDokter");
        Dokter d = new Dokter();
        DokterService ds = new DokterService();
        d.setNoDokter("DOK.003");
        d.setNmDokter("HARYONO");
        d.setIdSpesialis("Sp.PD");
        d.setTglKerjaDok("2008-01-02");
        d.setAlamatDok("Jakarta");
        ds.serviceInsertDokter(d);

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
        Staf sf = new Staf();
        StafService sfs = new StafService();
        sf.setNoStaf("STF.003");
        sf.setNmStaf("Asnar Sudirja");
        sf.setAlamatStaf("Jakarta Timur");
        sfs.serviceInsertStaf(sf);

        TabelModelStaf tabelModelStaf = new TabelModelStaf();
        tabelModelStaf.setData(sfs.serviceGetAllStafByNo("STF.003"));
        assertEquals("STF.003", tabelModelStaf.getValueAt(0, 0));
        assertEquals("Asnar Sudirja", tabelModelStaf.getValueAt(0, 1));
        assertEquals("Jakarta Timur", tabelModelStaf.getValueAt(0, 2));
    }

    @Test
    public void f_insertDataPasien() {
        System.out.println("6. insertDataPasien");
        Pasien pn = new Pasien();
        PasienService pns = new PasienService();
        pn.setNoRm("000003");
        pn.setNmPas("Udin Samsudin");
        pn.setJkPas("L");
        pn.setTglLahir("1990-09-04");
        pn.setAgama("Islam");
        pn.setAlamatPas("Malang");
        pns.serviceInsertPasien(pn);

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
        System.out.println("7. insertRekamMedis");
        RekamMedis rm = new RekamMedis();
        RekamMedisService rms = new RekamMedisService();
        List<RekamMedis>listRekamMedis = new ArrayList<RekamMedis>();

        expNoDaftar = "090920130001";
        rm.setNoDaftar(expNoDaftar);
        rm.setNoRm("000003");
        rm.setNoStaf("STF.003");
        rm.setBagianSpesialis("Sp.PD");
        rm.setIdJaminan("KJS");
        rm.setNoDokter("DOK.003");
        rm.setStatus("Antri");
        rm.setTglDaftar("2013-09-09");
        rms.serviceInsertRekamMedis(rm);

        expNoDaftar = "090920130002";
        rm.setNoDaftar(expNoDaftar);
        rm.setNoRm("000003");
        rm.setNoStaf("STF.003");
        rm.setBagianSpesialis("Sp.PD");
        rm.setIdJaminan("KJS");
        rm.setNoDokter("DOK.003");
        rm.setStatus("Antri");
        rm.setTglDaftar("2013-09-09");
        rms.serviceInsertRekamMedis(rm);

        TabelModelRekmedAll tabelModelRekmedAll = new TabelModelRekmedAll();
        tabelModelRekmedAll.setData(rms.serviceGetAllAtributRekmed());
        try{
            Statement s = (Statement) connection.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM rekam_medis");
            while(rs.next()){
                rm.setNoDaftar(rs.getString("no_daftar"));
                rm.setNoRm(rs.getString("no_rm"));
                rm.setNoStaf(rs.getString("no_staf"));
                rm.setBagianSpesialis(rs.getString("bagian_spesialis"));
                rm.setIdJaminan(rs.getString("id_jaminan"));
                rm.setNoDokter(rs.getString("no_dokter"));
                rm.setStatus(rs.getString("status"));
                rm.setTglDaftar(rs.getString("tgl_daftar"));
                listRekamMedis.add(rm);
            }
            rs.close();
            s.close();
        }catch(Throwable t){
        }

        for(int i=0; i<listRekamMedis.size(); i++){
            assertEquals(listRekamMedis.get(i).getNoDaftar(), tabelModelRekmedAll.getValueAt(i, 0));
            assertEquals(listRekamMedis.get(i).getNoRm(), tabelModelRekmedAll.getValueAt(i, 1));
            assertEquals(listRekamMedis.get(i).getNoStaf(), tabelModelRekmedAll.getValueAt(i, 2));
            assertEquals(listRekamMedis.get(i).getBagianSpesialis(), tabelModelRekmedAll.getValueAt(i, 3));
            assertEquals(listRekamMedis.get(i).getIdJaminan(), tabelModelRekmedAll.getValueAt(i, 4));
            assertEquals(listRekamMedis.get(i).getNoDokter(), tabelModelRekmedAll.getValueAt(i, 5));
            assertEquals(listRekamMedis.get(i).getStatus(), tabelModelRekmedAll.getValueAt(i, 6));
            assertEquals(listRekamMedis.get(i).getTglDaftar(), tabelModelRekmedAll.getValueAt(i, 7));
        }

    }

    @Test
    public void h_insertDataObat() {
        System.out.println("8. insertDataObat");
        List<Obat>listObat = new ArrayList<Obat>();
        Obat ob = new Obat();
        ObatService os = new ObatService();

        ob.setIdObat("PROMAG");
        ob.setKetObat("Obat Sakit Maag");
        os.serviceInsertObat(ob);

        ob.setIdObat("DULCOLAX");
        ob.setKetObat("Obat Sakit Perut");
        os.serviceInsertObat(ob);

        TabelModelObat tabelModelObat = new TabelModelObat();
        tabelModelObat.setData(os.serviceGetAllObat());
        try {
            Statement s = (Statement) connection.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM obat");
            while (rs.next()) {
                ob.setIdObat(rs.getString("id_obat"));
                ob.setKetObat(rs.getString("ket_obat"));
                listObat.add(ob);
            }
            rs.close();
            s.close();
        } catch (Throwable t) {
        }

        for (int i = 0; i < listObat.size(); i++) {
            assertEquals(listObat.get(i).getIdObat(), tabelModelObat.getValueAt(i, 0));
            assertEquals(listObat.get(i).getKetObat(), tabelModelObat.getValueAt(i, 1));
        }
    }

    @Test
    public void i_insertResep() {
        System.out.println("--------------------------------------");
        System.out.println("9. serviceInsertResep");

        expNoDaftar = "090920130001";
        expNoResep = "000000001";
        expTglResep = "2014-01-16";

        rp.setNoDaftar(expNoDaftar);
        rp.setNoResep(expNoResep);
        rp.setTglResep(expTglResep);
        instance.serviceInsertResep(rp);

        tabelModelResep.setData(instance.serviceGetAllResepByNoResep(expNoResep));
        assertEquals(expNoResep, tabelModelResep.getValueAt(0, 0));
        assertEquals(expNoDaftar, tabelModelResep.getValueAt(0, 1));
        assertEquals(expTglResep, tabelModelResep.getValueAt(0, 2));
    }

    @Test
    public void j_updateResep() {
        System.out.println("10. serviceUpdateResep");

        expNoResep = "000000001";
        expNoDaftar = "090920130002";
        expTglResep = "2012-12-13";

        rp.setNoDaftar(expNoDaftar);
        rp.setTglResep(expTglResep);
        instance.serviceUpdateResep(rp, expNoResep);

        tabelModelResep.setData(instance.serviceGetAllResepByNoResep(expNoResep));
        assertEquals(expNoDaftar, tabelModelResep.getValueAt(0, 1));
        assertEquals(expTglResep, tabelModelResep.getValueAt(0, 2));
    }

    @Test
    public void k_getAllResep() {
        System.out.println("11. serviceGetAllResep");

        tabelModelResep.setData(instance.serviceGetAllResep());
        try{
            Statement s = (Statement) connection.createStatement();
            ResultSet rs = s.executeQuery(getAllResep);
            while(rs.next()){
                rp.setNoResep(rs.getString("no_resep"));
                rp.setNoDaftar(rs.getString("no_daftar"));
                rp.setTglResep(rs.getString("tgl_resep"));
                list.add(rp);
            }
            rs.close();
            s.close();
        }catch(Throwable t){
        }

        for(int i=0; i<list.size(); i++){
            assertEquals(list.get(i).getNoResep(), tabelModelResep.getValueAt(i, 0));
            assertEquals(list.get(i).getNoDaftar(), tabelModelResep.getValueAt(i, 1));
            assertEquals(list.get(i).getTglResep(), tabelModelResep.getValueAt(i, 2));
        }

    }

    @Test
    public void l_deleteResep() {
        System.out.println("12. serviceDeleteResep");
        expNoResep = "000000001";
        instance.serviceDeleteResep(expNoResep);

        tabelModelResep.setData(instance.serviceGetAllResepByNoResep(expNoResep));
        assertEquals(0, tabelModelResep.getRowCount());
    }

    @Test
    public void m_deleteDataObat(){
        System.out.println("--------------------------------------");
        System.out.println("13. deleteDataObat");
        ObatService os = new ObatService();
        os.serviceDeleteObat("PROMAG");
        os.serviceDeleteObat("DULCOLAX");

        TabelModelObat tabelModelObat = new TabelModelObat();
        tabelModelObat.setData(os.serviceGetAllObat());
        assertEquals(0, tabelModelObat.getRowCount());
    }

    @Test
    public void n_deleteDataRekmed() {
        System.out.println("14. deleteDataRekmed");
        RekamMedisService rms = new RekamMedisService();
        rms.serviceDeleteRekmed();

        TabelModelRekmedAll tabelModelRekmedAll = new TabelModelRekmedAll();
        tabelModelRekmedAll.setData(rms.serviceGetAllAtributRekmed());
        assertEquals(0, tabelModelRekmedAll.getRowCount());
    }

    @Test
    public void o_deleteDataJaminan() {
        System.out.println("15. deleteDataJaminan");
        JaminanService jms = new JaminanService();
        jms.serviceDeleteJaminan("KJS");

        TabelModelJaminan tabelModelJaminan = new TabelModelJaminan();
        tabelModelJaminan.setData(jms.serviceGetAllJaminanById("KJS"));
        assertEquals(0, tabelModelJaminan.getRowCount());
    }

    @Test
    public void p_deleteDataPerawat() {
        System.out.println("16. deleteDataPerawat");
        PerawatService ps = new PerawatService();
        ps.serviceDeletePerawat("PER.003");

        TabelModelPerawat tabelModelPerawat = new TabelModelPerawat();
        tabelModelPerawat.setData(ps.serviceGetAllDataPerawatByNo("PER.003"));
        assertEquals(0, tabelModelPerawat.getRowCount());
    }

    @Test
    public void q_deleteDataDokter() {
        System.out.println("17. deleteDataDokter");
        DokterService ds = new DokterService();
        ds.serviceDeleteDokter("DOK.003");

        TabelModelDokter tabelModelDokter = new TabelModelDokter();
        tabelModelDokter.setData(ds.serviceGetAllDokterByNo("DOK.003"));
        assertEquals(0, tabelModelDokter.getRowCount());
    }

    @Test
    public void r_deleteDataStaf() {
        System.out.println("18. deleteDataStaf");
        StafService sfs = new StafService();
        sfs.serviceDeleteStaf("STF.003");

        TabelModelStaf tabelModelStaf = new TabelModelStaf();
        tabelModelStaf.setData(sfs.serviceGetAllStafByNo("STF.003"));
        assertEquals(0, tabelModelStaf.getRowCount());
    }

    @Test
    public void s_deleteDataPasien() {
        System.out.println("19. deleteDataPasien");
        PasienService pns = new PasienService();
        pns.serviceDeletePasien("000003");

        TabelModelPasien tabelModelPasien = new TabelModelPasien();
        tabelModelPasien.setData(pns.serviceGetAllPasienByNoRm("000003"));
        assertEquals(0, tabelModelPasien.getRowCount());
    }

    @Test
    public void t_deleteDataSpesialis() {
        System.out.println("20. deleteDataSpesialis");
        SpesialisService ss = new SpesialisService();
        ss.serviceDeleteSpesialis("Sp.PD");

        TabelModelSpesialis tabelModelSpesialis = new TabelModelSpesialis();
        tabelModelSpesialis.setData(ss.serviceGetAllSpesialisById("Sp.PD"));
        assertEquals(0, tabelModelSpesialis.getRowCount());
    }

}