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
import medrecapp.TabelModel.TabelModelJaminan;
import com.mysql.jdbc.Connection;
import java.util.List;
import medrecapp.Entity.Jaminan;
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
public class JaminanServiceTest {

    private List<Jaminan> list;
    private Connection connection;
    private JaminanService instance;
    private TabelModelJaminan tabelModelJaminan;
    private String expIdJaminan;
    private String expNmJaminan;
    private String expKetJaminan;
    private Jaminan j;
    private final String getAllJaminan = "SELECT * FROM jaminan";

    public JaminanServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        list = new ArrayList<Jaminan>();
        instance = new JaminanService();
        tabelModelJaminan = new TabelModelJaminan();
        j = new Jaminan();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of serviceInsertJaminan method, of class JaminanService.
     */
    @Test
    public void a_insertJaminan() {
        System.out.println("1. serviceInsertJaminan");

        expIdJaminan = "JPK PNS";
        expNmJaminan = "Jaminan PK Pegawai Negeri Sipil";
        expKetJaminan = "Jaminan kesahatan untuk pegawai negeri sipil";
        
        j.setIdJaminan(expIdJaminan);
        j.setNmJaminan(expNmJaminan);
        j.setKetJaminan(expKetJaminan);
        instance.serviceInsertJaminan(j);

        tabelModelJaminan.setData(instance.serviceGetAllJaminanById(expIdJaminan));
        assertEquals(expIdJaminan, tabelModelJaminan.getValueAt(0, 0));
        assertEquals(expNmJaminan, tabelModelJaminan.getValueAt(0, 1));
        assertEquals(expKetJaminan, tabelModelJaminan.getValueAt(0, 2));
    }

    /**
     * Test of serviceUpdateJaminan method, of class JaminanService.
     */
    @Test
    public void b_updateJaminan() {
        System.out.println("2. serviceUpdateJaminan");
        
        expIdJaminan = "JPK PNS";
        expNmJaminan = "Jaminan Pemeliharaan Kesehatan PNS";
        expKetJaminan = "Jaminan kesahatan untuk PNS";
        
        j.setNmJaminan(expNmJaminan);
        j.setKetJaminan(expKetJaminan);
        instance.serviceUpdateJaminan(j, expIdJaminan);

        tabelModelJaminan.setData(instance.serviceGetAllJaminanById(expIdJaminan));
        assertEquals(expIdJaminan, tabelModelJaminan.getValueAt(0, 0));
        assertEquals(expNmJaminan, tabelModelJaminan.getValueAt(0, 1));
        assertEquals(expKetJaminan, tabelModelJaminan.getValueAt(0, 2));
    }

    /**
     * Test of serviceDeleteJaminan method, of class JaminanService.
     */
    @Test
    public void c_deleteJaminan() {
        System.out.println("3. serviceDeleteJaminan");
        expIdJaminan = "JPK PNS";
        instance.serviceDeleteJaminan(expIdJaminan);

        tabelModelJaminan.setData(instance.serviceGetAllJaminanById(expIdJaminan));
        assertEquals(0, tabelModelJaminan.getRowCount());
    }

    /**
     * Test of serviceGetAllJaminan method, of class JaminanService.
     */
    @Test
    public void d_getAllJaminan() {
        System.out.println("4. serviceGetAllJaminan");
        tabelModelJaminan.setData(instance.serviceGetAllJaminan());

        try{
            Statement stt = (Statement) connection.createStatement();
            ResultSet rs = stt.executeQuery(getAllJaminan);
            while(rs.next()){
                j.setIdJaminan(rs.getString("id_jaminan"));
                j.setNmJaminan(rs.getString("nm_jaminan"));
                j.setKetJaminan(rs.getString("ket_jaminan"));
                list.add(j);
            }
            rs.close();
            stt.close();
        }catch(Throwable t){
            // Sengaja dikosongin
        }

        for(int i=0; i<list.size(); i++){
            assertEquals(list.get(i).getIdJaminan(), tabelModelJaminan.getValueAt(i, 0));
            assertEquals(list.get(i).getNmJaminan(), tabelModelJaminan.getValueAt(i, 1));
            assertEquals(list.get(i).getKetJaminan(), tabelModelJaminan.getValueAt(i, 2));
        }
    }

}