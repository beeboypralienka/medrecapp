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
import medrecapp.TabelModel.TabelModelPasien;
import com.mysql.jdbc.Connection;
import java.util.List;
import medrecapp.Entity.Pasien;
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
public class PasienServiceTest {

    private List<Pasien> list;
    private Connection connection;
    private PasienService instance;
    private TabelModelPasien tabelModelPasien;
    private String ExpNoRm;
    private String ExpNmPas;
    private String ExpJkPas;
    private String ExpTglLahir;
    private String ExpAgama;
    private String ExpAlamatPas;
    private Pasien p;
    private final String getAllPasien = "SELECT * FROM pasien";

    public PasienServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        list = new ArrayList<Pasien>();
        p = new Pasien();
        instance = new PasienService();
        tabelModelPasien = new TabelModelPasien();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of serviceInsertPasien method, of class PasienService.
     */
    @Test
    public void a_insertPasien() {
        System.out.println("1. serviceInsertPasien");

        ExpNoRm = "000005";
        ExpNmPas = "Harun Yahya";
        ExpJkPas = "L";
        ExpTglLahir = "1990-09-23";
        ExpAgama = "Islam";
        ExpAlamatPas = "Jl. Merdeka No. 73 Bandung";

        p.setNoRm(ExpNoRm);
        p.setNmPas(ExpNmPas);
        p.setJkPas(ExpJkPas);
        p.setTglLahir(ExpTglLahir);
        p.setAgama(ExpAgama);
        p.setAlamatPas(ExpAlamatPas);
        instance.serviceInsertPasien(p);

        tabelModelPasien.setData(instance.serviceGetAllPasienByNoRm(ExpNoRm));
        assertEquals(ExpNmPas, tabelModelPasien.getValueAt(0, 1));
        assertEquals(ExpJkPas, tabelModelPasien.getValueAt(0, 2));
        assertEquals(ExpTglLahir, tabelModelPasien.getValueAt(0, 3));
        assertEquals(ExpAgama, tabelModelPasien.getValueAt(0, 4));
        assertEquals(ExpAlamatPas, tabelModelPasien.getValueAt(0, 5));
    }

    /**
     * Test of serviceUpdatePasien method, of class PasienService.
     */
    @Test
    public void b_updatePasien() {
        System.out.println("2. serviceUpdatePasien");

        ExpNoRm = "000005";
        ExpNmPas = "Hasan Sadikin";
        ExpJkPas = "L";
        ExpTglLahir = "1989-12-10";
        ExpAgama = "Islam";
        ExpAlamatPas = "Jl. Kebangsaan Timur No. 99 Jakarta";

        p.setNmPas(ExpNmPas);
        p.setJkPas(ExpJkPas);
        p.setTglLahir(ExpTglLahir);
        p.setAgama(ExpAgama);
        p.setAlamatPas(ExpAlamatPas);
        instance.serviceUpdatePasien(p, ExpNoRm);

        tabelModelPasien.setData(instance.serviceGetAllPasienByNoRm(ExpNoRm));
        assertEquals(ExpNmPas, tabelModelPasien.getValueAt(0, 1));
        assertEquals(ExpJkPas, tabelModelPasien.getValueAt(0, 2));
        assertEquals(ExpTglLahir, tabelModelPasien.getValueAt(0, 3));
        assertEquals(ExpAgama, tabelModelPasien.getValueAt(0, 4));
        assertEquals(ExpAlamatPas, tabelModelPasien.getValueAt(0, 5));
    }

    /**
     * Test of serviceDeletePasien method, of class PasienService.
     */
    @Test
    public void c_deletePasien() {
        System.out.println("3. serviceDeletePasien");
        ExpNoRm = "000005";
        instance.serviceDeletePasien(ExpNoRm);
        tabelModelPasien.setData(instance.serviceGetAllPasienByNoRm(ExpNoRm));
        assertEquals(0, tabelModelPasien.getRowCount());
    }

    /**
     * Test of serviceGetAllPasien method, of class PasienService.
     */
    @Test
    public void d_getAllPasien() {
        System.out.println("4. serviceGetAllPasien");

        tabelModelPasien.setData(instance.serviceGetAllPasien());
        try{
            Statement s = (Statement) connection.createStatement();
            ResultSet rs = s.executeQuery(getAllPasien);
            while(rs.next()){
                p.setNoRm(rs.getString("no_rm"));
                p.setNmPas(rs.getString("nm_pas"));
                p.setJkPas(rs.getString("jk_pas"));
                p.setTglLahir(rs.getString("tgl_lahir"));
                p.setAgama(rs.getString("agama"));
                p.setAlamatPas(rs.getString("alamat_pas"));
                list.add(p);
            }
            rs.close();
            s.close();
        }catch(Throwable t){
            // Sengaja dikosongin dulu
        }

        for(int i=0; i<list.size(); i++){
            assertEquals(list.get(i).getNoRm(), tabelModelPasien.getValueAt(i, 0));
            assertEquals(list.get(i).getNmPas(), tabelModelPasien.getValueAt(i, 1));
            assertEquals(list.get(i).getJkPas(), tabelModelPasien.getValueAt(i, 2));
            assertEquals(list.get(i).getTglLahir(), tabelModelPasien.getValueAt(i, 3));
            assertEquals(list.get(i).getAgama(), tabelModelPasien.getValueAt(i, 4));
            assertEquals(list.get(i).getAlamatPas(), tabelModelPasien.getValueAt(i, 5));
        }
    }
}
