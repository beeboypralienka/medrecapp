/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package medrecapp.Services;

import javax.swing.JFrame;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.UIManager;
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
public class B_SpesialisGuiServiceTest {

    private static FrmUtama fu;
    private static FrameFixture frame;

    public B_SpesialisGuiServiceTest() {
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
        frame.menuItem("menuDataSpesialis").click();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        SpesialisService ss = new SpesialisService();
        ss.serviceDeleteSpesialis("Sp.PD");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void a_insertDataSpesialisKosong() {
        System.out.println("1. InsertDataSpesialisKosong");

        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Spesialis Gagal!");
        frame.optionPane().okButton().click();
    }

    @Test
    public void b_insertDataSpesialis() {
        System.out.println("2. InsertDataSpesialis");

        frame.textBox("txtIDSpesialis").enterText("Sp.PD");
        frame.textBox("txtNamaSpesialis").enterText("Penyakit Dalam");
        frame.textBox("txtTarifKonsul").enterText("80000");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Spesialis");
        frame.optionPane().okButton().click();

        frame.textBox("txtIDSpesialis").enterText("Sp.THT");
        frame.textBox("txtNamaSpesialis").enterText("Penyakit THT");
        frame.textBox("txtTarifKonsul").enterText("80000");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Spesialis");
        frame.optionPane().okButton().click();

        frame.textBox("txtIDSpesialis").enterText("Sp.Jt");
        frame.textBox("txtNamaSpesialis").enterText("Penyakit Jantung");
        frame.textBox("txtTarifKonsul").enterText("80000");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Spesialis");
        frame.optionPane().okButton().click();

        frame.textBox("txtIDSpesialis").enterText("Sp.Mt");
        frame.textBox("txtNamaSpesialis").enterText("Penyakit Mata");
        frame.textBox("txtTarifKonsul").enterText("80000");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Spesialis");
        frame.optionPane().okButton().click();
    }

    @Test
    public void c_updateDataSpesialisKosong() {
        System.out.println("3. UpdateDataSpesialisKosong");

        frame.textBox("txtCari").enterText("Sp.PD");
        frame.table("tabelSpesialis").selectRows(0);
        frame.textBox("txtNamaSpesialis").deleteText();
        frame.textBox("txtTarifKonsul").deleteText();
        frame.button("btnUpdate").click();
        frame.optionPane().requireTitle("Update Spesialis Gagal!");
        frame.optionPane().okButton().click();
    }

    @Test
    public void d_updateDataSpesialis() {
        System.out.println("4. UpdateDataSpesialis");

        frame.table("tabelSpesialis").selectRows(0);
        frame.textBox("txtNamaSpesialis").selectText(0, 0);
        frame.textBox("txtNamaSpesialis").enterText("Spesialis Penyakit Dalam");
        frame.textBox("txtTarifKonsul").enterText("95000");
        frame.button("btnUpdate").click();
        frame.optionPane().requireTitle("Update Spesialis");
        frame.optionPane().okButton().click();

        frame.textBox("txtCari").enterText("jantung");
        frame.table("tabelSpesialis").selectRows(0);
        frame.textBox("txtNamaSpesialis").selectText(0, 0);
        frame.textBox("txtNamaSpesialis").enterText("Spesialis ");
        frame.textBox("txtTarifKonsul").selectText(1, 2);
        frame.textBox("txtTarifKonsul").enterText("5");
        frame.button("btnUpdate").click();
        frame.optionPane().requireTitle("Update Spesialis");
        frame.optionPane().okButton().click();
    }

    @Test
    public void e_deleteDataSpesialis() {
        System.out.println("5. DeleteDataSpesialis");

        for (int i=0; i<4; i++)  {
            frame.table("tabelSpesialis").selectRows(0);
            frame.button("btnDelete").click();
            frame.optionPane().requireTitle("Konfirmasi");
            frame.optionPane().yesButton().click();
            frame.optionPane().requireTitle("Delete Spesialis");
            frame.optionPane().okButton().click();
        }
    }
}
