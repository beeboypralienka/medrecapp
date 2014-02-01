/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package medrecapp.Services;

import java.sql.ResultSet;
import com.mysql.jdbc.Statement;
import medrecapp.TabelModel.TabelModelPasien;
import medrecapp.TabelModel.TabelModelStaf;
import medrecapp.TabelModel.TabelModelDokter;
import medrecapp.TabelModel.TabelModelPerawat;
import medrecapp.TabelModel.TabelModelJaminan;
import medrecapp.TabelModel.TabelModelSpesialis;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import medrecapp.Entity.Pasien;
import medrecapp.Entity.Staf;
import medrecapp.Entity.Dokter;
import medrecapp.Entity.Perawat;
import medrecapp.Entity.Jaminan;
import medrecapp.Entity.Spesialis;
import java.util.ArrayList;
import medrecapp.TabelModel.TabelModelPelayananTindakan;
import com.mysql.jdbc.Connection;
import java.util.List;
import medrecapp.Entity.PelayananTindakan;
import medrecapp.Entity.RekamMedis;
import medrecapp.Entity.Tindakan;
import medrecapp.TabelModel.TabelModelRekmedAll;
import medrecapp.TabelModel.TabelModelTindakan;
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
public class PelayananTindakanServiceTest {

    private List<PelayananTindakan> list;
    private Connection connection;
    private PelayananTindakanService instance;
    private TabelModelPelayananTindakan tabelModelPelayananTindakan;
    private String expNoDaftar;
    private String expNoTindakan;
    private PelayananTindakan pt;
    private final String getAllPelayananTindakan = "SELECT * FROM pelayanan_tindakan";

    public PelayananTindakanServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        list = new ArrayList<PelayananTindakan>();
        pt = new PelayananTindakan();
        instance = new PelayananTindakanService();
        tabelModelPelayananTindakan = new TabelModelPelayananTindakan();
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
        expNoDaftar = "090920130001";

        RekamMedis rm = new RekamMedis();
        rm.setNoDaftar(expNoDaftar);
        rm.setNoRm("000003");
        rm.setNoStaf("STF.003");
        rm.setBagianSpesialis("Sp.PD");
        rm.setIdJaminan("KJS");
        rm.setNoDokter("DOK.003");
        rm.setStatus("Antri");
        rm.setTglDaftar("2013-09-09");
        RekamMedisService rms = new RekamMedisService();
        rms.serviceInsertRekamMedis(rm);

        TabelModelRekmedAll tabelModelRekmedAll = new TabelModelRekmedAll();
        tabelModelRekmedAll.setData(rms.serviceGetAllAtributRekmedByNoDaftar(expNoDaftar));
        assertEquals(expNoDaftar, tabelModelRekmedAll.getValueAt(0, 0));
        assertEquals("000003", tabelModelRekmedAll.getValueAt(0, 1));
        assertEquals("STF.003", tabelModelRekmedAll.getValueAt(0, 2));
        assertEquals("Sp.PD", tabelModelRekmedAll.getValueAt(0, 3));
        assertEquals("KJS", tabelModelRekmedAll.getValueAt(0, 4));
        assertEquals("DOK.003", tabelModelRekmedAll.getValueAt(0, 5));
        assertEquals("Antri", tabelModelRekmedAll.getValueAt(0, 6));
        assertEquals("2013-09-09", tabelModelRekmedAll.getValueAt(0, 7));
    }

    @Test
    public void h_insertDataTindakan(){
        System.out.println("8. insertDataTindakan");

        List<Tindakan>listTindakan = new ArrayList<Tindakan>();

        expNoTindakan = "TIND.0001";
        Tindakan td = new Tindakan();
        td.setNoTindakan(expNoTindakan);
        td.setNmTindakan("GV Sedang");
        td.setTindakanSpesialis("Sp.PD");
        td.setKetTindakan("GV Sedang - Tindakan pada spesialis penyakit dalam");
        TindakanService ts = new TindakanService();
        ts.serviceInsertTindakan(td);

        expNoTindakan = "TIND.0002";
        td.setNoTindakan(expNoTindakan);
        td.setNmTindakan("Drae Sedang");
        td.setTindakanSpesialis("Sp.PD");
        td.setKetTindakan("Drae Sedang - Tindakan pada spesialis penyakit dalam");
        ts.serviceInsertTindakan(td);

        expNoTindakan = "TIND.0003";
        td.setNoTindakan(expNoTindakan);
        td.setNmTindakan("Drae Kecil");
        td.setTindakanSpesialis("Sp.PD");
        td.setKetTindakan("Drae Kecil - Tindakan pada spesialis penyakit dalam untuk pasien KJS");
        ts.serviceInsertTindakan(td);

        TabelModelTindakan tabelModelTindakan = new TabelModelTindakan();
        tabelModelTindakan.setData(ts.serviceGetAllTindakan());
        try{
            Statement s = (Statement) connection.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM tindakan");
            while(rs.next()){
                td.setNoTindakan(rs.getString("no_tindakan"));
                td.setNmTindakan(rs.getString("nm_tindakan"));
                td.setTindakanSpesialis(rs.getString("tindakan_spesialis"));
                td.setKetTindakan(rs.getString("ket_tindakan"));
                listTindakan.add(td);
            }
            rs.close();
            s.close();
        }catch(Throwable t){
        }

        for(int i=0; i<listTindakan.size(); i++){
            assertEquals(listTindakan.get(i).getNoTindakan(), tabelModelTindakan.getValueAt(i, 0));
            assertEquals(listTindakan.get(i).getNmTindakan(), tabelModelTindakan.getValueAt(i, 1));
            assertEquals(listTindakan.get(i).getTindakanSpesialis(), tabelModelTindakan.getValueAt(i, 2));
            assertEquals(listTindakan.get(i).getKetTindakan(), tabelModelTindakan.getValueAt(i, 3));
        }
    }

    @Test
    public void i_insertPelayananTindakan() {
        System.out.println("--------------------------------------");
        System.out.println("9. serviceInsertPelayananTindakan");

        expNoDaftar = "090920130001";
        expNoTindakan = "TIND.0001";

        pt.setNoDaftar(expNoDaftar);
        pt.setNoTindakan(expNoTindakan);
        instance.serviceInsertPelayananTindakan(pt);

        expNoDaftar = "090920130001";
        expNoTindakan = "TIND.0002";

        pt.setNoDaftar(expNoDaftar);
        pt.setNoTindakan(expNoTindakan);
        instance.serviceInsertPelayananTindakan(pt);

        tabelModelPelayananTindakan.setData(instance.serviceGetAllPelayananTindakan());
        try{
            Statement s = (Statement) connection.createStatement();
            ResultSet rs = s.executeQuery(getAllPelayananTindakan);
            while(rs.next()){
                pt.setNoDaftar(rs.getString("no_daftar"));
                pt.setNoTindakan(rs.getString("no_tindakan"));
                list.add(pt);
            }
            rs.close();
            s.close();
        }catch(Throwable t){
        }

        for(int i=0; i<list.size(); i++){
            assertEquals(list.get(i).getNoDaftar(), tabelModelPelayananTindakan.getValueAt(i, 0));
            assertEquals(list.get(i).getNoTindakan(), tabelModelPelayananTindakan.getValueAt(i, 1));
        }
    }

    @Test
    public void j_updatePelayananTindakan() {
        System.out.println("10. serviceUpdatePelayananTindakan");

        expNoDaftar = "090920130001";
        expNoTindakan = "TIND.0002";

        pt.setNoDaftar(expNoDaftar);
        pt.setNoTindakan(expNoTindakan);
        instance.serviceUpdatePelayananTindakan("TIND.0003", pt);

        tabelModelPelayananTindakan.setData(instance.serviceGetAllPelayananTindakan());
        try{
            Statement s = (Statement) connection.createStatement();
            ResultSet rs = s.executeQuery(getAllPelayananTindakan);
            while(rs.next()){
                pt.setNoDaftar(rs.getString("no_daftar"));
                pt.setNoTindakan(rs.getString("no_tindakan"));
                list.add(pt);
            }
            rs.close();
            s.close();
        }catch(Throwable t){
        }

        for(int i=0; i<list.size(); i++){
            assertEquals(list.get(i).getNoDaftar(), tabelModelPelayananTindakan.getValueAt(i, 0));
            assertEquals(list.get(i).getNoTindakan(), tabelModelPelayananTindakan.getValueAt(i, 1));
        }
    }    

    @Test
    public void k_getAllPelayananTindakan() {
        System.out.println("11. serviceGetAllPelayananTindakan");

        tabelModelPelayananTindakan.setData(instance.serviceGetAllPelayananTindakan());
        try{
            Statement s = (Statement) connection.createStatement();
            ResultSet rs = s.executeQuery(getAllPelayananTindakan);
            while(rs.next()){
                pt.setNoDaftar(rs.getString("no_daftar"));
                pt.setNoTindakan(rs.getString("no_tindakan"));
                list.add(pt);
            }
            rs.close();
            s.close();
        }catch(Throwable t){
        }

        for(int i=0; i<list.size(); i++){
            assertEquals(list.get(i).getNoDaftar(), tabelModelPelayananTindakan.getValueAt(i, 0));
            assertEquals(list.get(i).getNoTindakan(), tabelModelPelayananTindakan.getValueAt(i, 1));
        }
    }

    @Test
    public void l_deletePelayananTindakan() {
        System.out.println("12. serviceDeletePelayananTindakan");
        expNoDaftar = "090920130001";
        instance.serviceDeletePelayananTindakan(expNoDaftar);

        tabelModelPelayananTindakan.setData(instance.serviceGetAllByNoDaftar(expNoDaftar));
        assertEquals(0, tabelModelPelayananTindakan.getRowCount());
    }

    @Test
    public void m_deleteDataTindakan(){
        System.out.println("--------------------------------------");
        System.out.println("13. deleteDataTindakan");
        TindakanService rms = new TindakanService();
        rms.serviceDeleteTindakan("TIND.0001");
        rms.serviceDeleteTindakan("TIND.0002");
        rms.serviceDeleteTindakan("TIND.0003");

        TabelModelTindakan tabelModelTindakan = new TabelModelTindakan();
        tabelModelTindakan.setData(rms.serviceGetAllTindakan());
        assertEquals(0, tabelModelTindakan.getRowCount());       
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
