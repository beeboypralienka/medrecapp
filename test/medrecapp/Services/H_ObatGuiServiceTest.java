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
        frame.menuItem("menuDataObat").click();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
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
    }

    @Test
    public void b_insertDataSpesialis() {
        System.out.println("2. InsertDataObat");

//        frame.textBox("txtIDSpesialis").enterText("Sp.PD");
//        frame.textBox("txtNamaSpesialis").enterText("Penyakit Dalam");
//        frame.textBox("txtTarifKonsul").enterText("80000");
//        frame.button("btnInsert").click();
//        frame.optionPane().requireTitle("Insert Obat");
//        frame.optionPane().okButton().click();
    }

    @Test
    public void c_updateDataSpesialisKosong() {
        System.out.println("3. UpdateDataObatKosong");

//        frame.table("tabelSpesialis").selectRows(0);
//        frame.textBox("txtNamaSpesialis").deleteText();
//        frame.textBox("txtTarifKonsul").deleteText();
//        frame.button("btnUpdate").click();
//        frame.optionPane().requireTitle("Update Obat Gagal!");
//        frame.optionPane().okButton().click();
    }

    @Test
    public void d_updateDataSpesialis() {
        System.out.println("4. UpdateDataObat");

//        frame.table("tabelSpesialis").selectRows(0);
//        frame.textBox("txtNamaSpesialis").selectText(0, 0);
//        frame.textBox("txtNamaSpesialis").enterText("Spesialis Penyakit Dalam");
//        frame.textBox("txtTarifKonsul").enterText("95000");
//        frame.button("btnUpdate").click();
//        frame.optionPane().requireTitle("Update Obat");
//        frame.optionPane().okButton().click();
    }

    @Test
    public void e_deleteDataSpesialis() {
        System.out.println("5. DeleteDataObat");

//        frame.table("tabelSpesialis").selectRows(0);
//        frame.button("btnDelete").click();
//        frame.optionPane().requireTitle("Konfirmasi");
//        frame.optionPane().yesButton().click();
//        frame.optionPane().requireTitle("Delete Obat");
//        frame.optionPane().okButton().click();
    }

}