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
import medrecapp.TabelModel.TabelModelSpesialis;
import com.mysql.jdbc.Connection;
import java.util.List;
import medrecapp.Entity.Spesialis;
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
public class SpesialisServiceTest {

    private List<Spesialis> list;
    private Connection connection;
    private SpesialisService instance;
    private TabelModelSpesialis tabelModelSpesialis;
    private String ExpIdSpesialis;
    private String ExpNmSpesialis;
    private int ExpTarifKonsul;
    private Spesialis sp;
    private final String getAllSpesialis = "SELECT * FROM spesialis";

    public SpesialisServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        list = new ArrayList<Spesialis>();
        instance = new SpesialisService();
        tabelModelSpesialis = new TabelModelSpesialis();
        sp = new Spesialis();
    }

    @After
    public void tearDown() {        
    }

    /**
     * Test of serviceInsertSpesialis method, of class SpesialisService.
     */
    @Test
    public void a_insertSpesialis() {
        System.out.println("1. serviceInsertSpesialis");

        ExpIdSpesialis = "Sp.Mt";
        ExpNmSpesialis = "Spesialis Mata";
        ExpTarifKonsul = 70000;
        
        sp.setIdSpesialis(ExpIdSpesialis);
        sp.setNmSpesialis(ExpNmSpesialis);
        sp.setTarifKonsul(ExpTarifKonsul);        
        instance.serviceInsertSpesialis(sp);

        tabelModelSpesialis.setData(instance.serviceGetAllSpesialisById(ExpIdSpesialis));
        assertEquals(ExpIdSpesialis, tabelModelSpesialis.getValueAt(0, 0));
        assertEquals(ExpNmSpesialis, tabelModelSpesialis.getValueAt(0, 1));
        assertEquals(ExpTarifKonsul, tabelModelSpesialis.getValueAt(0, 2));
    }

    /**
     * Test of serviceUpdateSpesialis method, of class SpesialisService.
     */
    @Test
    public void b_updateSpesialis() {
        System.out.println("2. serviceUpdateSpesialis");

        ExpIdSpesialis = "Sp.Mt";
        ExpNmSpesialis = "Spesialis Penyakit Mata";
        ExpTarifKonsul = 75000;
        
        sp.setNmSpesialis(ExpNmSpesialis);
        sp.setTarifKonsul(ExpTarifKonsul);
        instance.serviceUpdateSpesialis(sp, ExpIdSpesialis);

        tabelModelSpesialis.setData(instance.serviceGetAllSpesialisById(ExpIdSpesialis));
        assertEquals(ExpIdSpesialis, tabelModelSpesialis.getValueAt(0, 0));
        assertEquals(ExpNmSpesialis, tabelModelSpesialis.getValueAt(0, 1));
        assertEquals(ExpTarifKonsul, tabelModelSpesialis.getValueAt(0, 2));
    }

    /**
     * Test of serviceDeleteSpesialis method, of class SpesialisService.
     */
    @Test
    public void c_deleteSpesialis() {
        System.out.println("3. serviceDeleteSpesialis");

        ExpIdSpesialis = "Sp.Mt";
        instance.serviceDeleteSpesialis(ExpIdSpesialis);

        tabelModelSpesialis.setData(instance.serviceGetAllSpesialisById(ExpIdSpesialis));
        assertEquals(0, tabelModelSpesialis.getRowCount());
    }

    /**
     * Test of serviceGetAllSpesialis method, of class SpesialisService.
     */
    @Test
    public void d_getAllSpesialis() {
        System.out.println("4. serviceGetAllSpesialis");

        tabelModelSpesialis.setData(instance.serviceGetAllSpesialis());

        try{
            Statement stt = (Statement) connection.createStatement();
            ResultSet rs = stt.executeQuery(getAllSpesialis);
            while(rs.next()){
                sp.setIdSpesialis(rs.getString("id_spesialis"));
                sp.setNmSpesialis(rs.getString("nm_spesialis"));
                sp.setTarifKonsul(rs.getInt("tarif_konsul"));
                list.add(sp);
            }
            rs.close();
            stt.close();
        }catch(Throwable t){
            // Sengaja dikosongin
        }

        for(int i=0; i<list.size(); i++){
            assertEquals(list.get(i).getIdSpesialis(), tabelModelSpesialis.getValueAt(i, 0));
            assertEquals(list.get(i).getNmSpesialis(), tabelModelSpesialis.getValueAt(i, 1));
            assertEquals(list.get(i).getTarifKonsul(), tabelModelSpesialis.getValueAt(i, 2));
        }
    }

}