/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Services;

import java.sql.ResultSet;
import com.mysql.jdbc.Statement;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import java.util.ArrayList;
import medrecapp.Entity.Spesialis;
import medrecapp.TabelModel.TabelModelTindakan;
import com.mysql.jdbc.Connection;
import java.util.List;
import medrecapp.Entity.Tindakan;
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
public class TindakanServiceTest {

    private List<Tindakan> list;
    private Connection connection;
    private TindakanService instance;
    private TabelModelTindakan tabelModelTindakan;
    private String expNoTindakan;
    private String expNmTindakan;
    private String expTindakanSpesialis;
    private String expKetTindakan;
    private static String ExpIdSpesialis;
    private Tindakan td;
    private final String getAllTindakan = "SELECT * FROM tindakan";

    public TindakanServiceTest() {
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

    }

    @AfterClass
    public static void tearDownClass() throws Exception {

        TindakanService ts = new TindakanService();
        ts.serviceDeleteTindakan("TIND.0001");

        SpesialisService sps = new SpesialisService();        
        sps.serviceDeleteSpesialis("Sp.Mt");

    }

    @Before
    public void setUp() {
        list = new ArrayList<Tindakan>();
        td = new Tindakan();
        instance = new TindakanService();
        tabelModelTindakan = new TabelModelTindakan();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of serviceInsertTindakan method, of class TindakanService.
     */
    @Test
    public void a_insertTindakan() {
        System.out.println("1. serviceInsertTindakan");
        expNoTindakan        = "TIND.0001";
        expNmTindakan        = "GV Sedang";
        expTindakanSpesialis = "Sp.Mt";
        expKetTindakan       = "Tindakan pada spesialis bedah";

        td.setNoTindakan(expNoTindakan);
        td.setNmTindakan(expNmTindakan);
        td.setTindakanSpesialis(expTindakanSpesialis);
        td.setKetTindakan(expKetTindakan);
        instance.serviceInsertTindakan(td);

        tabelModelTindakan.setData(instance.serviceGetAllDataTindakanByNo(expNoTindakan));
        assertEquals(expNoTindakan, tabelModelTindakan.getValueAt(0, 0));
        assertEquals(expNmTindakan, tabelModelTindakan.getValueAt(0, 1));
        assertEquals(expTindakanSpesialis, tabelModelTindakan.getValueAt(0, 2));
        assertEquals(expKetTindakan, tabelModelTindakan.getValueAt(0, 3));
    }

    /**
     * Test of serviceUpdateTindakan method, of class TindakanService.
     */
    @Test
    public void b_updateTindakan() {
        System.out.println("2. serviceUpdateTindakan");
        
        expNoTindakan        = "TIND.0001";
        expNmTindakan        = "Drae Sedang";
        expTindakanSpesialis = "Sp.Mt";
        expKetTindakan       = "Tindakan pada spesialis pembedahan";
        
        td.setNmTindakan(expNmTindakan);
        td.setTindakanSpesialis(expTindakanSpesialis);
        td.setKetTindakan(expKetTindakan);
        instance.serviceUpdateTindakan(td, expNoTindakan);

        tabelModelTindakan.setData(instance.serviceGetAllDataTindakanByNo(expNoTindakan));
        assertEquals(expNoTindakan, tabelModelTindakan.getValueAt(0, 0));
        assertEquals(expNmTindakan, tabelModelTindakan.getValueAt(0, 1));
        assertEquals(expTindakanSpesialis, tabelModelTindakan.getValueAt(0, 2));
        assertEquals(expKetTindakan, tabelModelTindakan.getValueAt(0, 3));
    }

    /**
     * Test of serviceDeleteTindakan method, of class TindakanService.
     */
    @Test
    public void c_deleteTindakan() {
        System.out.println("3. serviceDeleteTindakan");

        expNoTindakan        = "TIND.0001";
        instance.serviceDeleteTindakan(expNoTindakan);
        tabelModelTindakan.setData(instance.serviceGetAllDataTindakanByNo(expNoTindakan));
        assertEquals(0, tabelModelTindakan.getRowCount());
    }

    /**
     * Test of serviceGetAllTindakan method, of class TindakanService.
     */
    @Test
    public void d_getAllTindakan() {
        System.out.println("4. serviceGetAllTindakan");

        tabelModelTindakan.setData(instance.serviceGetAllTindakan());
        try{
            Statement s = (Statement) connection.createStatement();
            ResultSet rs = s.executeQuery(getAllTindakan);
            while(rs.next()){
                td.setNoTindakan(rs.getString("no_tindakan"));
                td.setNmTindakan(rs.getString("nm_tindakan"));
                td.setTindakanSpesialis(rs.getString("tindakan_spesialis"));
                td.setKetTindakan(rs.getString("ket_tindakan"));
                list.add(td);
            }
            rs.close();
            s.close();
        }catch(Throwable t){
            // Sengaja dikosongin dulu
        }

        for(int i=0; i<list.size(); i++){
            assertEquals(list.get(i).getNoTindakan(), tabelModelTindakan.getValueAt(i, 0));
            assertEquals(list.get(i).getNmTindakan(), tabelModelTindakan.getValueAt(i, 1));
            assertEquals(list.get(i).getTindakanSpesialis(), tabelModelTindakan.getValueAt(i, 2));
            assertEquals(list.get(i).getKetTindakan(), tabelModelTindakan.getValueAt(i, 3));
        }
    }
}