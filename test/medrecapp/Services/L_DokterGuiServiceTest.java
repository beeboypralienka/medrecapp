/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package medrecapp.Services;

import org.fest.swing.fixture.FrameFixture;
import medrecapp.Services.frame;
import javax.swing.JFrame;
import medrecapp.Gui.FrmUtama;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.UIManager;
import org.fest.swing.fixture.JComboBoxFixture;
import org.fest.swing.fixture.JOptionPaneFixture;
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
public class L_DokterGuiServiceTest {

    private static FrmUtama fu;
    private static FrameFixture frame;

    public L_DokterGuiServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(L_DokterGuiServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        fu = new FrmUtama();
        fu.setExtendedState(fu.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame = new FrameFixture(fu);
        frame.show();
        frame.menuItem("menuDataDokter").click();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        DokterService ds = new DokterService();
        ds.serviceDeleteDokter("DOK.001");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void a_insertDataDokterKosong() {
        System.out.println("1. InsertDataDokterKosong");

        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Dokter Gagal!");
        frame.optionPane().okButton().click();
    }

    @Test
    public void b_insertDataDokter() {
        System.out.println("2. InsertDataDokter");

        frame.textBox("txtNmDokter").enterText("Amalia");
        frame.comboBox("pilihSpesialis").selectItem("Spesialis Penyakit Dalam");
        frame.textBox("txtTglKerja").enterText("2013-10-13");
        frame.textBox("txtAlamat").enterText("Garut");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Dokter");
        frame.optionPane().okButton().click();
    }

    @Test
    public void c_updateDataDokterKosong() {
        System.out.println("3. UpdateDataDokterKosong");

        frame.table("tabelDokter").selectRows(0);
        frame.textBox("txtNmDokter").deleteText();
        frame.comboBox("pilihSpesialis").selectItem(0);
        frame.textBox("txtTglKerja").deleteText();
        frame.textBox("txtAlamat").deleteText();
        frame.button("btnUpdate").click();
        frame.optionPane().requireTitle("Update Dokter Gagal!");
        frame.optionPane().okButton().click();
    }

    @Test
    public void d_updateDataDokter() {
        System.out.println("4. UpdateDataDokter");

        frame.table("tabelDokter").selectRows(0);
        frame.textBox("txtNmDokter").selectText(0, 0);
        frame.textBox("txtNmDokter").enterText("Amalia Khoirunnisa");
        frame.comboBox("pilihSpesialis").selectItem("Spesialis Mata");
        frame.textBox("txtTglKerja").enterText("2013-10-10");
        frame.textBox("txtAlamat").enterText("Garut - Jawa Barat");
        frame.button("btnUpdate").click();
        frame.optionPane().requireTitle("Update Dokter");
        frame.optionPane().okButton().click();
    }

    @Test
    public void e_deleteDataDokter() {
        System.out.println("5. DeleteDataDokter");

        frame.table("tabelDokter").selectRows(0);
        frame.button("btnDelete").click();
        frame.optionPane().requireTitle("Konfirmasi");
        frame.optionPane().yesButton().click();
        frame.optionPane().requireTitle("Delete Spesialis");
        frame.optionPane().okButton().click();
    }

}
