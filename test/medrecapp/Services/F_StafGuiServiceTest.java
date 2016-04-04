/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Services;

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import org.fest.swing.fixture.FrameFixture;
import javax.swing.JFrame;
import medrecapp.Gui.FrmUtama;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;
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
public class F_StafGuiServiceTest {
    private static FrmUtama fu;
    private static FrameFixture frame;

    public F_StafGuiServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(B_SpesialisGuiServiceTest.class.getName()).log(Level.SEVERE, null, ex);
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
        
        frame.menuItem("menuDataStaf").click();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        StafService stf = new StafService();
        stf.serviceDeleteStaf("STF.001");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void a_insertDataStafKosong() {
        System.out.println("1. InsertDataStafKosong");

        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Staf Gagal!");
        frame.optionPane().okButton().click();

        frame.textBox("txtNamaStaf").enterText("Syauki");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Staf Gagal!");
        frame.optionPane().okButton().click();
    }

    @Test
    public void b_insertDataSpesialis() {
        System.out.println("2. InsertDataStaf");
        
        frame.textBox("txtAlamat").enterText("Cilegon");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Staf");
        frame.optionPane().okButton().click();
    }

    @Test
    public void c_updateDataStafKosong() {
        System.out.println("3. UpdateDataStafKosong");

        frame.table("tabelStaf").selectRows(0);
        frame.textBox("txtNamaStaf").deleteText();
        frame.textBox("txtAlamat").deleteText();
        frame.button("btnUpdate").click();
        frame.optionPane().requireTitle("Update Staf Gagal!");
        frame.optionPane().okButton().click();

        frame.textBox("txtNamaStaf").enterText("Syauki Faatih Assalaam");
        frame.button("btnUpdate").click();
        frame.optionPane().requireTitle("Update Staf Gagal!");
        frame.optionPane().okButton().click();
    }

    @Test
    public void d_updateDataStaf() {
        System.out.println("4. UpdateDataStaf");
                
        frame.textBox("txtAlamat").enterText("Cilegon-Banten");
        frame.button("btnUpdate").click();
        frame.optionPane().requireTitle("Update Staf");
        frame.optionPane().okButton().click();
    }

    @Test
    public void e_deleteDataStaf() {
        System.out.println("5. DeleteDataStaf");

        frame.table("tabelStaf").selectRows(0);
        frame.button("btnDelete").click();
        frame.optionPane().requireTitle("Konfirmasi");
        frame.optionPane().yesButton().click();
        frame.optionPane().requireTitle("Delete Staf");
        frame.optionPane().okButton().click();
    }
}