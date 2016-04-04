/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Services;

import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.UIManager;
import javax.swing.JFrame;
import org.fest.swing.fixture.FrameFixture;
import medrecapp.Gui.FrmUtama;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
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
public class H_ObatGuiServiceTest {

    private static FrmUtama fu;
    private static FrameFixture frame;

    public H_ObatGuiServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(H_ObatGuiServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        fu = new FrmUtama();
        fu.setExtendedState(fu.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame = new FrameFixture(fu);
        frame.show();
        
        /***          
         * Mempercepat waktu testing GUI
         */
        frame.robot.settings().delayBetweenEvents(0);
        frame.robot.settings().dragDelay(0);
        frame.robot.settings().dropDelay(0);
        frame.robot.settings().eventPostingDelay(0);
        
        frame.menuItem("menuDataObat").click();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        ObatService os = new ObatService();
        os.serviceDeleteObat("Omeprazol");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void a_insertDataObatKosong() {
        System.out.println("1. InsertDataObatKosong");

        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Obat Gagal!");
        frame.optionPane().okButton().click();

        frame.textBox("txtIdObat").enterText("Omeprazol");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Obat Gagal!");
        frame.optionPane().okButton().click();
    }

    @Test
    public void b_insertDataObat() {
        System.out.println("2. InsertDataObat");
        
        frame.textBox("txtKeterangan").enterText("Obat Lambung");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Obat");
        frame.optionPane().okButton().click();
    }

    @Test
    public void c_updateDataObatKosong() {
        System.out.println("3. UpdateDataObatKosong");

        frame.table("tabelObat").selectRows(0);
        frame.textBox("txtKeterangan").deleteText();
        frame.button("btnUpdate").click();
        frame.optionPane().requireTitle("Update Obat Gagal!");
        frame.optionPane().okButton().click();
    }

    @Test
    public void d_updateDataObat() {
        System.out.println("4. UpdateDataObat");
        
        frame.textBox("txtKeterangan").enterText("Obat Penyakit Lambung");
        frame.button("btnUpdate").click();
        frame.optionPane().requireTitle("Update Obat");
        frame.optionPane().okButton().click();
    }

    @Test
    public void e_deleteDataObat() {
        System.out.println("5. DeleteDataObat");

        frame.table("tabelObat").selectRows(0);
        frame.button("btnDelete").click();
        frame.optionPane().requireTitle("Konfirmasi");
        frame.optionPane().yesButton().click();
        frame.optionPane().requireTitle("Delete Obat");
        frame.optionPane().okButton().click();
    }

}