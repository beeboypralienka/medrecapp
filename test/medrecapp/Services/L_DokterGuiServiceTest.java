/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package medrecapp.Services;

import org.fest.swing.fixture.FrameFixture;
import javax.swing.JFrame;
import medrecapp.Gui.FrmUtama;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.UIManager;
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
        
        /***          
         * Mempercepat waktu testing GUI
         */
        frame.robot.settings().delayBetweenEvents(0);
        frame.robot.settings().dragDelay(0);
        frame.robot.settings().dropDelay(0);
        frame.robot.settings().eventPostingDelay(0);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        DokterService ds = new DokterService();
        ds.serviceDeleteDokter("DOK.001");
        ds.serviceDeleteDokter("DOK.002");

        SpesialisService ss = new SpesialisService();
        ss.serviceDeleteSpesialis("Sp.THT");
        ss.serviceDeleteSpesialis("Sp.Mt");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void a_insertDataSpesialis() {
        System.out.println("1. InsertDataSpesialis");
        frame.menuItem("menuDataSpesialis").click();

        frame.textBox("txtIDSpesialis").enterText("Sp.THT");
        frame.textBox("txtNamaSpesialis").enterText("Spesialis Penyakit THT");
        frame.textBox("txtTarifKonsul").enterText("80000");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Spesialis");
        frame.optionPane().okButton().click();

        frame.textBox("txtIDSpesialis").enterText("Sp.Mt");
        frame.textBox("txtNamaSpesialis").enterText("Spesialis Penyakit Mata");
        frame.textBox("txtTarifKonsul").enterText("95000");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Spesialis");
        frame.optionPane().okButton().click();
    }

    @Test
    public void b_insertDataDokterKosong() {
        System.out.println("2. InsertDataDokterKosong");
        frame.menuItem("menuDataDokter").click();

        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Dokter Gagal!");
        frame.optionPane().okButton().click();

        frame.textBox("txtNamaDokter").enterText("Amalia");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Dokter Gagal!");
        frame.optionPane().okButton().click();

        frame.comboBox("pilihSpesialis").selectItem("Spesialis Penyakit THT");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Dokter Gagal!");
        frame.optionPane().okButton().click();

        frame.textBox("txtTglKerja").enterText("2013-10-13");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Dokter Gagal!");
        frame.optionPane().okButton().click();
    }

    @Test
    public void c_insertDataDokter() {
        System.out.println("3. InsertDataDokter");
                        
        frame.textBox("txtAlamat").enterText("Garut");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Dokter");
        frame.optionPane().okButton().click();

        frame.textBox("txtNamaDokter").enterText("Nuryamin");
        frame.comboBox("pilihSpesialis").selectItem("Spesialis Penyakit THT");
        frame.textBox("txtTglKerja").enterText("2009-10-13");
        frame.textBox("txtAlamat").enterText("Bandung");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Dokter");
        frame.optionPane().okButton().click();
    }

    @Test
    public void d_updateDataDokterKosong() {
        System.out.println("4. UpdateDataDokterKosong");

        frame.textBox("txtCari").enterText("amal");
        frame.table("tabelDokter").selectRows(0);
        frame.textBox("txtNamaDokter").deleteText();
        frame.comboBox("pilihSpesialis").selectItem(0);
        frame.textBox("txtTglKerja").deleteText();
        frame.textBox("txtAlamat").deleteText();
        frame.button("btnUpdate").click();
        frame.optionPane().requireTitle("Update Dokter Gagal!");
        frame.optionPane().okButton().click();

        frame.textBox("txtNamaDokter").enterText("Amalia Khoirunnisa");
        frame.button("btnUpdate").click();
        frame.optionPane().requireTitle("Update Dokter Gagal!");
        frame.optionPane().okButton().click();

        frame.comboBox("pilihSpesialis").selectItem("Spesialis Penyakit Mata");
        frame.button("btnUpdate").click();
        frame.optionPane().requireTitle("Update Dokter Gagal!");
        frame.optionPane().okButton().click();

        frame.textBox("txtTglKerja").enterText("2013-10-10");
        frame.button("btnUpdate").click();
        frame.optionPane().requireTitle("Update Dokter Gagal!");
        frame.optionPane().okButton().click();
    }

    @Test
    public void e_updateDataDokter() {
        System.out.println("5. UpdateDataDokter");
                                
        frame.textBox("txtAlamat").enterText("Garut - Jawa Barat");
        frame.button("btnUpdate").click();
        frame.optionPane().requireTitle("Update Dokter");
        frame.optionPane().okButton().click();

        frame.textBox("txtCari").enterText("dok.002");
        frame.table("tabelDokter").selectRows(0);
        frame.textBox("txtNamaDokter").enterText(" Khasannah");
        frame.button("btnUpdate").click();
        frame.optionPane().requireTitle("Update Dokter");
        frame.optionPane().okButton().click();
    }

    @Test
    public void f_deleteDataSpesialisGagal() {
        System.out.println("6. DeleteDataSpesialisGagal");
        frame.menuItem("menuDataSpesialis").click();

        frame.table("tabelSpesialis").selectRows(0);
        frame.button("btnDelete").click();
        frame.optionPane().requireTitle("Konfirmasi");
        frame.optionPane().yesButton().click();
        frame.optionPane().requireTitle("Delete Spesialis Gagal!");
        frame.optionPane().okButton().click();

        frame.table("tabelSpesialis").selectRows(1);
        frame.button("btnDelete").click();
        frame.optionPane().requireTitle("Konfirmasi");
        frame.optionPane().yesButton().click();
        frame.optionPane().requireTitle("Delete Spesialis Gagal!");
        frame.optionPane().okButton().click();
    }

    @Test
    public void g_deleteDataDokter() {
        System.out.println("7. DeleteDataDokter");
        frame.menuItem("menuDataDokter").click();

        frame.table("tabelDokter").selectRows(0);
        frame.button("btnDelete").click();
        frame.optionPane().requireTitle("Konfirmasi");
        frame.optionPane().yesButton().click();
        frame.optionPane().requireTitle("Delete Dokter");
        frame.optionPane().okButton().click();

        frame.table("tabelDokter").selectRows(0);
        frame.button("btnDelete").click();
        frame.optionPane().requireTitle("Konfirmasi");
        frame.optionPane().yesButton().click();
        frame.optionPane().requireTitle("Delete Dokter");
        frame.optionPane().okButton().click();
    }

    @Test
    public void h_deleteDataSpesialis() {
        System.out.println("6. DeleteDataSpesialis");
        frame.menuItem("menuDataSpesialis").click();

        for (int i = 0; i < 2; i++) {
            frame.table("tabelSpesialis").selectRows(0);
            frame.button("btnDelete").click();
            frame.optionPane().requireTitle("Konfirmasi");
            frame.optionPane().yesButton().click();
            frame.optionPane().requireTitle("Delete Spesialis");
            frame.optionPane().okButton().click();
        }
    }
}
