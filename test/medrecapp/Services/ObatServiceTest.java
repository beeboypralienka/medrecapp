/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package medrecapp.Services;

import java.sql.ResultSet;
import com.mysql.jdbc.Statement;
import java.util.ArrayList;
import medrecapp.TabelModel.TabelModelObat;
import com.mysql.jdbc.Connection;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import java.util.List;
import medrecapp.Entity.Obat;
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
public class ObatServiceTest {

    private List<Obat> list;
    private Connection connection;
    private ObatService instance;
    private TabelModelObat tabelModelObat;
    private String expIdObat;
    private String expKetObat;
    private Obat ob;
    private final String getAllObat = "SELECT * FROM obat";

    public ObatServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        ObatService os = new ObatService();
        os.serviceDeleteObat("PROMAG");
    }

    @Before
    public void setUp() {
        list = new ArrayList<Obat>();
        ob = new Obat();
        instance = new ObatService();
        tabelModelObat = new TabelModelObat();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of serviceInsertObat method, of class ObatService.
     */
    @Test
    public void a_insertObat() {
        System.out.println("1. serviceInsertObat");

        expIdObat = "PROMAG";
        expKetObat = "Obat Sakit Maag";

        ob.setIdObat(expIdObat);
        ob.setKetObat(expKetObat);
        instance.serviceInsertObat(ob);

        tabelModelObat.setData(instance.serviceGetAllObatById(expIdObat));
        assertEquals(expIdObat, tabelModelObat.getValueAt(0, 0));
        assertEquals(expKetObat, tabelModelObat.getValueAt(0, 1));

    }

    /**
     * Test of serviceUpdateObat method, of class ObatService.
     */
    @Test
    public void b_updateObat() {
        System.out.println("2. serviceUpdateObat");

        expIdObat = "PROMAG";
        expKetObat = "Obat untuk sakit lambung";

        ob.setKetObat(expKetObat);
        instance.serviceUpdateObat(ob, expIdObat);

        tabelModelObat.setData(instance.serviceGetAllObatById(expIdObat));
        assertEquals(expKetObat, tabelModelObat.getValueAt(0, 1));

    }

    /**
     * Test of serviceDeleteObat method, of class ObatService.
     */
    @Test
    public void c_deleteObat() {
        System.out.println("3. serviceDeleteObat");
        expIdObat = "PROMAG";
        instance.serviceDeleteObat(expIdObat);
        tabelModelObat.setData(instance.serviceGetAllObatById(expIdObat));
        assertEquals(0, tabelModelObat.getRowCount());

    }

    /**
     * Test of serviceGetAllDokter method, of class ObatService.
     */
    @Test
    public void d_getAllDokter() {
        System.out.println("4. serviceGetAllDokter");

        tabelModelObat.setData(instance.serviceGetAllObat());
        try {
            Statement s = (Statement) connection.createStatement();
            ResultSet rs = s.executeQuery(getAllObat);
            while (rs.next()) {
                ob.setIdObat(rs.getString("id_obat"));
                ob.setKetObat(rs.getString("ket_obat"));
                list.add(ob);
            }
            rs.close();
            s.close();
        } catch (Throwable t) {
            // Sengaja dikosongin dulu
        }

        for (int i = 0; i < list.size(); i++) {
            assertEquals(list.get(i).getIdObat(), tabelModelObat.getValueAt(i, 0));
            assertEquals(list.get(i).getKetObat(), tabelModelObat.getValueAt(i, 1));
        }
    }
}
