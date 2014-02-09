/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package medrecapp.Services;

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import com.mysql.jdbc.Connection;
import java.sql.ResultSet;
import com.mysql.jdbc.Statement;
import java.util.ArrayList;
import java.util.List;
import medrecapp.Entity.Perawat;
import medrecapp.Entity.Spesialis;
import medrecapp.TabelModel.TabelModelPerawat;
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
public class M_PerawatServiceTest {

    private List<Perawat> list;
    private Connection connection;
    private PerawatService instance;
    private TabelModelPerawat tabelModelPerawat;
    private String ExpNoPerawat;
    private String ExpNmPerawat;
    private String ExpTglKerja;
    private String ExpPerSpesialis;
    private static String ExpIdSpesialis;
    private Perawat p;
    private final String getAllPerawat = "SELECT * FROM perawat";    

    public M_PerawatServiceTest() {
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
        //System.out.println(SpesialisDao.hasilInsert);

        ExpIdSpesialis = "Sp.PD";
        sp.setIdSpesialis(ExpIdSpesialis);
        sp.setNmSpesialis("Dalam");
        sp.setTarifKonsul(70000);
        sps.serviceInsertSpesialis(sp);
        //System.out.println(SpesialisDao.hasilInsert);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        SpesialisService sps = new SpesialisService();

        ExpIdSpesialis = "Sp.Mt";
        sps.serviceDeleteSpesialis(ExpIdSpesialis);
        //System.out.println(SpesialisDao.hasilDelete);

        ExpIdSpesialis = "Sp.PD";
        sps.serviceDeleteSpesialis(ExpIdSpesialis);
        //System.out.println(SpesialisDao.hasilDelete);
    }

    @Before
    public void setUp() {
        list = new ArrayList<Perawat>();
        p = new Perawat();
        instance = new PerawatService();
        tabelModelPerawat = new TabelModelPerawat();
    }

    @After
    public void tearDown() {                
    }
    /**
     * Test of serviceInsertPerawat method, of class PerawatService.
     */
    @Test
    public void a_insertPerawat() {
        System.out.println("1. serviceInsertPerawat");

        ExpNoPerawat    = "PER.003";
        ExpNmPerawat    = "Lina Meiga";
        ExpTglKerja     = "2013-05-04";
        ExpPerSpesialis = "Sp.Mt";
        
        p.setNoPerawat(ExpNoPerawat);        
        p.setNmPerawat(ExpNmPerawat);        
        p.setTglKerjaPer(ExpTglKerja);        
        p.setPerSpesialis(ExpPerSpesialis);                
        instance.serviceInsertPerawat(p);        
        
        tabelModelPerawat.setData(instance.serviceGetAllDataPerawatByNo(ExpNoPerawat));
        assertEquals(ExpNmPerawat, tabelModelPerawat.getValueAt(0, 1));
        assertEquals(ExpTglKerja, tabelModelPerawat.getValueAt(0, 2));
        assertEquals(ExpPerSpesialis, tabelModelPerawat.getValueAt(0, 3));

    }

    /**
     * Test of serviceUpdatePerawat method, of class PerawatService.
     */
    @Test
    public void b_updatePerawat() {
        System.out.println("2. serviceUpdatePerawat");

        ExpNoPerawat    = "PER.003";
        ExpNmPerawat    = "Linawati";
        ExpTglKerja     = "2009-10-03";
        ExpPerSpesialis = "Sp.PD";
        
        p.setNmPerawat(ExpNmPerawat);
        p.setTglKerjaPer(ExpTglKerja);
        p.setPerSpesialis(ExpPerSpesialis);        
        instance.serviceUpdatePerawat(p, ExpNoPerawat);        
        
        tabelModelPerawat.setData(instance.serviceGetAllDataPerawatByNo(ExpNoPerawat));

        assertEquals(ExpNmPerawat, tabelModelPerawat.getValueAt(0, 1));
        assertEquals(ExpTglKerja, tabelModelPerawat.getValueAt(0, 2));
        assertEquals(ExpPerSpesialis, tabelModelPerawat.getValueAt(0, 3));
    }
    /**
     * Test of serviceDeletePerawat method, of class PerawatService.
     */
    @Test
    public void c_deletePerawat() {
        System.out.println("3. serviceDeletePerawat");
        ExpNoPerawat = "PER.003";        
        instance.serviceDeletePerawat(ExpNoPerawat);        
        tabelModelPerawat.setData(instance.serviceGetAllDataPerawatByNo(ExpNoPerawat));
        assertEquals(0, tabelModelPerawat.getRowCount());
    }
    /**
     * Test of serviceGetAllPerawat method, of class PerawatService.
     */    

    @Test
    public void d_getAllPerawat() {
        System.out.println("4. serviceGetAllPerawat");        
        
        tabelModelPerawat.setData(instance.serviceGetAllPerawat());
        try{
            Statement s = (Statement) connection.createStatement();
            ResultSet rs = s.executeQuery(getAllPerawat);
            while(rs.next()){
                Perawat pr = new Perawat();
                pr.setNoPerawat(rs.getString("no_perawat"));
                pr.setNmPerawat(rs.getString("nm_perawat"));
                pr.setTglKerjaPer(rs.getString("tgl_kerja_per"));
                pr.setPerSpesialis(rs.getString("nm_spesialis"));
                list.add(pr);
            }            
            rs.close();
            s.close();
        }catch(Throwable t){
            // Sengaja dikosongin dulu
        }

        for(int i=0; i<list.size(); i++){
            assertEquals(list.get(i).getNoPerawat(), tabelModelPerawat.getValueAt(i, 0));
            assertEquals(list.get(i).getNmPerawat(), tabelModelPerawat.getValueAt(i, 1));
            assertEquals(list.get(i).getTglKerjaPer(), tabelModelPerawat.getValueAt(i, 2));
            assertEquals(list.get(i).getPerSpesialis(), tabelModelPerawat.getValueAt(i, 3));
        }

    }
}
