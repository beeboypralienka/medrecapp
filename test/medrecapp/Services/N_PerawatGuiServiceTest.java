/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package medrecapp.Services;

import javax.swing.JFrame;
import java.util.logging.Logger;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.util.logging.Level;
import javax.swing.UnsupportedLookAndFeelException;
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
public class N_PerawatGuiServiceTest {

    private static FrmUtama fu;
    private static FrameFixture frame;

    public N_PerawatGuiServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(N_PerawatGuiServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        fu = new FrmUtama();
        fu.setExtendedState(fu.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame = new FrameFixture(fu);
        frame.show();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        PerawatService ps = new PerawatService();
        ps.serviceDeletePerawat("PER.001");
        ps.serviceDeletePerawat("PER.002");

        SpesialisService ss = new SpesialisService();
        ss.serviceDeleteSpesialis("Sp.PD");
        ss.serviceDeleteSpesialis("Sp.Jt");
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

        frame.textBox("txtIDSpesialis").enterText("Sp.PD");
        frame.textBox("txtNamaSpesialis").enterText("Spesialis Penyakit Dalam");
        frame.textBox("txtTarifKonsul").enterText("80000");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Spesialis");
        frame.optionPane().okButton().click();

        frame.textBox("txtIDSpesialis").enterText("Sp.Jt");
        frame.textBox("txtNamaSpesialis").enterText("Spesialis Penyakit Jantung");
        frame.textBox("txtTarifKonsul").enterText("95000");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Spesialis");
        frame.optionPane().okButton().click();
    }

    @Test
    public void b_insertDataPerawatKosong() {
        System.out.println("2. InsertDataPerawatKosong");
        frame.menuItem("menuDataPerawat").click();

        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Perawat Gagal!");
        frame.optionPane().okButton().click();

        frame.textBox("txtNamaPerawat").enterText("Fitriya Rahmawati");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Perawat Gagal!");
        frame.optionPane().okButton().click();

        frame.textBox("txtTanggalKerja").enterText("2013-05-06");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Perawat Gagal!");
        frame.optionPane().okButton().click();
    }

    @Test
    public void c_insertDataPerawat() {
        System.out.println("3. InsertDataPerawat");
                
        frame.comboBox("pilihBagian").selectItem("Spesialis Penyakit Jantung");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Perawat");
        frame.optionPane().okButton().click();

        frame.textBox("txtNamaPerawat").enterText("Prima");
        frame.textBox("txtTanggalKerja").enterText("2009-10-06");
        frame.comboBox("pilihBagian").selectItem("Spesialis Penyakit Jantung");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Perawat");
        frame.optionPane().okButton().click();
    }

    @Test
    public void d_updateDataPerawatKosong() {
        System.out.println("4. UpdateDataPerawatKosong");

        frame.textBox("txtCari").enterText("fitri");
        frame.table("tabelPerawat").selectRows(0);
        frame.textBox("txtNamaPerawat").deleteText();
        frame.textBox("txtTanggalKerja").deleteText();
        frame.comboBox("pilihBagian").selectItem(0);
        frame.button("btnUpdate").click();
        frame.optionPane().requireTitle("Update Perawat Gagal!");
        frame.optionPane().okButton().click();

        frame.textBox("txtNamaPerawat").enterText("Intan Permata Yanti");
        frame.button("btnUpdate").click();
        frame.optionPane().requireTitle("Update Perawat Gagal!");
        frame.optionPane().okButton().click();

        frame.textBox("txtTanggalKerja").enterText("2010-05-10");
        frame.button("btnUpdate").click();
        frame.optionPane().requireTitle("Update Perawat Gagal!");
        frame.optionPane().okButton().click();
    }

    @Test
    public void e_updateDataPerawat() {
        System.out.println("5. UpdateDataPerawat");
                        
        frame.comboBox("pilihBagian").selectItem("Spesialis Penyakit Dalam");
        frame.button("btnUpdate").click();
        frame.optionPane().requireTitle("Update Perawat");
        frame.optionPane().okButton().click();

        frame.textBox("txtCari").enterText("per.002");
        frame.table("tabelPerawat").selectRows(0);
        frame.textBox("txtNamaPerawat").enterText(" Puspita Sari");
        frame.button("btnUpdate").click();
        frame.optionPane().requireTitle("Update Perawat");
        frame.optionPane().okButton().click();
    }

    @Test
    public void f_deleteDataSpesialisGagal() {
        System.out.println("6. DeleteDataSpesialis");
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
    public void g_deleteDataPerawat() {
        System.out.println("6. DeleteDataPerawat");
        frame.menuItem("menuDataPerawat").click();

        frame.table("tabelPerawat").selectRows(0);
        frame.button("btnDelete").click();
        frame.optionPane().requireTitle("Konfirmasi");
        frame.optionPane().yesButton().click();
        frame.optionPane().requireTitle("Delete Perawat");
        frame.optionPane().okButton().click();

        frame.table("tabelPerawat").selectRows(0);
        frame.button("btnDelete").click();
        frame.optionPane().requireTitle("Konfirmasi");
        frame.optionPane().yesButton().click();
        frame.optionPane().requireTitle("Delete Perawat");
        frame.optionPane().okButton().click();
    }

    @Test
    public void h_deleteDataSpesialisBerhasil() {
        System.out.println("7. DeleteDataSpesialis");
        frame.menuItem("menuDataSpesialis").click();

        for (int i = 1; i <= 2; i++) {
            frame.table("tabelSpesialis").selectRows(0);
            frame.button("btnDelete").click();
            frame.optionPane().requireTitle("Konfirmasi");
            frame.optionPane().yesButton().click();
            frame.optionPane().requireTitle("Delete Spesialis");
            frame.optionPane().okButton().click();
        }
    }
}
