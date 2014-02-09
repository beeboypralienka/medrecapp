/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Services;

import javax.swing.JFrame;
import medrecapp.Gui.FrmTestUtama;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.UIManager;
import medrecapp.Gui.FrmUtama;
import org.fest.swing.fixture.FrameFixture;
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
public class D_JaminanGuiServiceTest {

    private static FrmUtama fu;
    private static FrameFixture frame;

    public D_JaminanGuiServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FrmTestUtama.class.getName()).log(Level.SEVERE, null, ex);
        }

        fu = new FrmUtama();
        fu.setExtendedState(fu.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame = new FrameFixture(fu);
        frame.show();
        frame.menuItem("menuDataJaminan").click();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        JaminanService js = new JaminanService();
        js.serviceDeleteJaminan("KJS");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void a_insertDataJaminanKosong() {
        System.out.println("1. InsertDataJaminanKosong");

        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Jaminan Gagal!");
        frame.optionPane().okButton().click();
    }

    @Test
    public void b_insertDataJaminan() {
        System.out.println("2. InsertDataJaminan");

        frame.textBox("txtIDJaminan").enterText("KJS");
        frame.textBox("txtNamaJaminan").enterText("Kartu JKT Sehat");
        frame.textBox("txtKeterangan").enterText("Warga DKI");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Jaminan");
        frame.optionPane().okButton().click();
    }

    @Test
    public void c_updateDataJaminanKosong() {
        System.out.println("3. UpdateDataJaminanKosong");

        frame.table("tabelJaminan").selectRows(0);
        frame.textBox("txtNamaJaminan").deleteText();
        frame.textBox("txtKeterangan").deleteText();
        frame.button("btnUpdate").click();
        frame.optionPane().requireTitle("Update Jaminan Gagal!");
        frame.optionPane().okButton().click();
    }

    @Test
    public void d_updateDataJaminan() {
        System.out.println("4. UpdateDataJaminan");

        frame.table("tabelJaminan").selectRows(0);
        frame.textBox("txtNamaJaminan").selectText(0, 0);
        frame.textBox("txtNamaJaminan").enterText("Kartu Jakarta Sehat");
        frame.textBox("txtKeterangan").enterText("Khusus Warga DKI");
        frame.button("btnUpdate").click();
        frame.optionPane().requireTitle("Update Jaminan");
        frame.optionPane().okButton().click();
    }

    @Test
    public void e_deleteDataJaminan() {
        System.out.println("5. DeleteDataJaminan");

        frame.table("tabelJaminan").selectRows(0);
        frame.button("btnDelete").click();
        frame.optionPane().requireTitle("Konfirmasi");
        frame.optionPane().yesButton().click();
        frame.optionPane().requireTitle("Delete Jaminan");
        frame.optionPane().okButton().click();
    }

}