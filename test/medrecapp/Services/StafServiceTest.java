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
import medrecapp.Entity.Staf;
import medrecapp.TabelModel.TabelModelStaf;
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
public class StafServiceTest {

    private List<Staf> list;
    private Connection connection;
    private StafService instance;
    private TabelModelStaf tabelModelStaf;
    private String ExpNoStaf, ExpNmStaf, ExpAlamatStaf;
    private Staf s;
    private final String getAllStaf = "SELECT * FROM staf";

    public StafServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        list = new ArrayList<Staf>();
        instance = new StafService();
        tabelModelStaf = new TabelModelStaf();
        s = new Staf();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of serviceInsertStaf method, of class StafService.
     */
    @Test
    public void a_insertStaf() {
        System.out.println("1. serviceInsertStaf");

        ExpNoStaf     = "STF.003";
        ExpNmStaf     = "Danu Simatupang";
        ExpAlamatStaf = "Jl. GOR Dharma Ayu No. 77";
       
        s.setNoStaf(ExpNoStaf);
        s.setNmStaf(ExpNmStaf);
        s.setAlamatStaf(ExpAlamatStaf);       
        instance.serviceInsertStaf(s);

        tabelModelStaf.setData(instance.serviceGetAllStafByNo(ExpNoStaf));
        assertEquals(ExpNoStaf, tabelModelStaf.getValueAt(0, 0));
        assertEquals(ExpNmStaf, tabelModelStaf.getValueAt(0, 1));
        assertEquals(ExpAlamatStaf, tabelModelStaf.getValueAt(0, 2));

    }

    /**
     * Test of serviceUpdateDokter method, of class StafService.
     */
    @Test
    public void b_updateStaf() {
        System.out.println("2. serviceUpdateStaf");

        ExpNoStaf     = "STF.003";
        ExpNmStaf     = "Joko Tole";
        ExpAlamatStaf = "Jl. GOR Wiralodra No. 77";
        
        s.setNmStaf(ExpNmStaf);
        s.setAlamatStaf(ExpAlamatStaf);        
        instance.serviceUpdateStaf(s, ExpNoStaf);

        tabelModelStaf.setData(instance.serviceGetAllStafByNo(ExpNoStaf));
        assertEquals(ExpNoStaf, tabelModelStaf.getValueAt(0, 0));
        assertEquals(ExpNmStaf, tabelModelStaf.getValueAt(0, 1));
        assertEquals(ExpAlamatStaf, tabelModelStaf.getValueAt(0, 2));

    }

    /**
     * Test of serviceDeleteStaf method, of class StafService.
     */
    @Test
    public void c_deleteStaf() {
        System.out.println("3. serviceDeleteStaf");

        ExpNoStaf = "STF.003";        
        instance.serviceDeleteStaf(ExpNoStaf);

        tabelModelStaf.setData(instance.serviceGetAllStafByNo(ExpNoStaf));
        assertEquals(0, tabelModelStaf.getRowCount());
    }

    /**
     * Test of serviceGetAllDokter method, of class StafService.
     */
    @Test
    public void d_getAllStaf() {
        System.out.println("4. serviceGetAllStaf");
        
        tabelModelStaf.setData(instance.serviceGetAllStaf());

        try{
            Statement stt = (Statement) connection.createStatement();
            ResultSet rs = stt.executeQuery(getAllStaf);
            while(rs.next()){                
                s.setNoStaf(rs.getString("no_staf"));
                s.setNmStaf(rs.getString("nm_staf"));
                s.setAlamatStaf(rs.getString("alamat_staf"));
                list.add(s);
            }
            rs.close();
            stt.close();
        }catch(Throwable t){
            // Sengaja dikosongin
        }

        for(int i=0; i<list.size(); i++){
            assertEquals(list.get(i).getNoStaf(), tabelModelStaf.getValueAt(i, 0));
            assertEquals(list.get(i).getNmStaf(), tabelModelStaf.getValueAt(i, 1));
            assertEquals(list.get(i).getAlamatStaf(), tabelModelStaf.getValueAt(i, 2));
        }

    }

}