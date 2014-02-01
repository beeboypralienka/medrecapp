/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package medrecapp.Services;

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import java.sql.ResultSet;
import com.mysql.jdbc.Statement;
import java.util.ArrayList;
import medrecapp.Entity.Spesialis;
import com.mysql.jdbc.Connection;
import java.util.List;
import medrecapp.Entity.Dokter;
import medrecapp.TabelModel.TabelModelDokter;
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
public class DokterServiceTest {

    private List<Dokter> list;
    private Connection connection;
    private DokterService instance;
    private TabelModelDokter tabelModelDokter;
    private String ExpNoDokter;
    private String ExpNmDokter;
    private String ExpSpesialisDokter;
    private String ExpTglKerjaDok;
    private String ExpAlamatDok;
    private static String ExpIdSpesialis;
    private Dokter d;
    private final String getAllDokter = "SELECT * FROM dokter";

    public DokterServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Spesialis sp = new Spesialis();
        SpesialisService sps = new SpesialisService();

        ExpIdSpesialis = "Sp.Mt";
        sp.setIdSpesialis(ExpIdSpesialis);
        sp.setNmSpesialis("Mata");
        sp.setTarifKonsul(50000);
        sps.serviceInsertSpesialis(sp);

        ExpIdSpesialis = "Sp.PD";
        sp.setIdSpesialis(ExpIdSpesialis);
        sp.setNmSpesialis("Dalam");
        sp.setTarifKonsul(70000);
        sps.serviceInsertSpesialis(sp);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        SpesialisService sps = new SpesialisService();

        ExpIdSpesialis = "Sp.Mt";
        sps.serviceDeleteSpesialis(ExpIdSpesialis);

        ExpIdSpesialis = "Sp.PD";
        sps.serviceDeleteSpesialis(ExpIdSpesialis);
    }

    @Before
    public void setUp() {
        list = new ArrayList<Dokter>();
        d = new Dokter();
        instance = new DokterService();
        tabelModelDokter = new TabelModelDokter();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of serviceInsertDokter method, of class DokterService.
     */
    @Test
    public void a_insertDokter() {
        System.out.println("1. serviceInsertDokter");
        ExpNoDokter    = "DOK.005";
        ExpNmDokter    = "dr. Lisa Sulistine";
        ExpIdSpesialis = "Sp.PD";
        ExpTglKerjaDok = "2008-09-25";
        ExpAlamatDok   = "Jl. Belimbing No. 78 Jakarta Utara";

        d.setNoDokter(ExpNoDokter);
        d.setNmDokter(ExpNmDokter);
        d.setIdSpesialis(ExpIdSpesialis);
        d.setTglKerjaDok(ExpTglKerjaDok);
        d.setAlamatDok(ExpAlamatDok);
        instance.serviceInsertDokter(d);

        tabelModelDokter.setData(instance.serviceGetAllDokterByNo(ExpNoDokter));
        assertEquals(ExpNmDokter, tabelModelDokter.getValueAt(0, 1));
        assertEquals(ExpIdSpesialis, tabelModelDokter.getValueAt(0, 2));
        assertEquals(ExpTglKerjaDok, tabelModelDokter.getValueAt(0, 3));
        assertEquals(ExpAlamatDok, tabelModelDokter.getValueAt(0, 4));
    }

    /**
     * Test of serviceUpdateDokter method, of class DokterService.
     */
    @Test
    public void b_updateDokter() {
        System.out.println("2. serviceUpdateDokter");
        ExpNoDokter    = "DOK.005";
        ExpNmDokter    = "dr. Nisa Suciya";
        ExpIdSpesialis = "Sp.Mt";
        ExpTglKerjaDok = "2010-09-25";
        ExpAlamatDok   = "Jl. Mangga No. 78 Bekasi Barat";

        d.setNmDokter(ExpNmDokter);
        d.setIdSpesialis(ExpIdSpesialis);
        d.setTglKerjaDok(ExpTglKerjaDok);
        d.setAlamatDok(ExpAlamatDok);
        instance.serviceUpdateDokter(d, ExpNoDokter);

        tabelModelDokter.setData(instance.serviceGetAllDokterByNo(ExpNoDokter));
        assertEquals(ExpNmDokter, tabelModelDokter.getValueAt(0, 1));
        assertEquals(ExpIdSpesialis, tabelModelDokter.getValueAt(0, 2));
        assertEquals(ExpTglKerjaDok, tabelModelDokter.getValueAt(0, 3));
        assertEquals(ExpAlamatDok, tabelModelDokter.getValueAt(0, 4));
    }

    /**
     * Test of serviceDeleteDokter method, of class DokterService.
     */
    @Test
    public void c_deleteDokter() {
        System.out.println("3. serviceDeleteDokter");
        ExpNoDokter = "DOK.005";
        instance.serviceDeleteDokter(ExpNoDokter);
        tabelModelDokter.setData(instance.serviceGetAllDokterByNo(ExpNoDokter));
        assertEquals(0, tabelModelDokter.getRowCount());
    }
    /**
     * Test of serviceGetAllDokter method, of class DokterService.
     */
    @Test
    public void d_getAllDokter() {
        System.out.println("4. serviceGetAllDokter");
        
        tabelModelDokter.setData(instance.serviceGetAllDokter());
        try{
            Statement s = (Statement) connection.createStatement();
            ResultSet rs = s.executeQuery(getAllDokter);
            while(rs.next()){
                d.setNoDokter(rs.getString("no_dokter"));
                d.setNmDokter(rs.getString("nm_dokter"));
                d.setIdSpesialis(rs.getString("id_spesialis"));
                d.setTglKerjaDok(rs.getString("tgl_kerja_dok"));
                d.setAlamatDok(rs.getString("alamat_dok"));
                list.add(d);
            }
            rs.close();
            s.close();
        }catch(Throwable t){
            // Sengaja dikosongin dulu
        }

        for(int i=0; i<list.size(); i++){
            assertEquals(list.get(i).getNoDokter(), tabelModelDokter.getValueAt(i, 0));
            assertEquals(list.get(i).getNmDokter(), tabelModelDokter.getValueAt(i, 1));
            assertEquals(list.get(i).getIdSpesialis(), tabelModelDokter.getValueAt(i, 2));
            assertEquals(list.get(i).getTglKerjaDok(), tabelModelDokter.getValueAt(i, 3));
            assertEquals(list.get(i).getAlamatDok(), tabelModelDokter.getValueAt(i, 4));
        }
    }
}
