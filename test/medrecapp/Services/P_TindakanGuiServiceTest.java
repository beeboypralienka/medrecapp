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
public class P_TindakanGuiServiceTest {

    private static FrmUtama fu;
    private static FrameFixture frame;

    public P_TindakanGuiServiceTest() {
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
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        TindakanService ts = new TindakanService();
        ts.serviceDeleteTindakan("TIND.001");
        ts.serviceDeleteTindakan("TIND.002");

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
    public void b_insertDataTindakanKosong() {
        System.out.println("2. InsertDataTindakanKosong");
        frame.menuItem("menuDataTindakan").click();

        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Tindakan Gagal!");
        frame.optionPane().okButton().click();

        frame.textBox("txtNamaTindakan").enterText("GV Sedang");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Tindakan Gagal!");
        frame.optionPane().okButton().click();

        frame.comboBox("pilihTindakan").selectItem("Spesialis Penyakit Dalam");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Tindakan Gagal!");
        frame.optionPane().okButton().click();
    }

    @Test
    public void c_insertDataTindakan() {
        System.out.println("3. InsertDataTindakan");

        frame.textBox("txtKeterangan").enterText("Tindakan VIP");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Tindakan");
        frame.optionPane().okButton().click();

        frame.textBox("txtNamaTindakan").enterText("Drae");
        frame.comboBox("pilihTindakan").selectItem("Spesialis Penyakit Dalam");
        frame.textBox("txtKeterangan").enterText("Tindakan KJS");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Tindakan");
        frame.optionPane().okButton().click();
    }

    @Test
    public void d_updateDataTindakanKosong() {
        System.out.println("4. UpdateDataTindakanKosong");

        frame.textBox("txtCari").enterText("gv");
        frame.table("tabelTindakan").selectRows(0);
        frame.textBox("txtNamaTindakan").deleteText();
        frame.comboBox("pilihTindakan").selectItem(0);
        frame.textBox("txtKeterangan").deleteText();
        frame.button("btnUpdate").click();
        frame.optionPane().requireTitle("Update Tindakan Gagal!");
        frame.optionPane().okButton().click();

        frame.textBox("txtNamaTindakan").enterText("GV Sedang");
        frame.button("btnUpdate").click();
        frame.optionPane().requireTitle("Update Tindakan Gagal!");
        frame.optionPane().okButton().click();

        frame.comboBox("pilihTindakan").selectItem("Spesialis Penyakit Jantung");
        frame.button("btnUpdate").click();
        frame.optionPane().requireTitle("Update Tindakan Gagal!");
        frame.optionPane().okButton().click();
    }

    @Test
    public void e_updateDataTindakan() {
        System.out.println("5. UpdateDataTindakan");

        frame.textBox("txtKeterangan").enterText("Tindakan khusus untuk pasien VIP");
        frame.button("btnUpdate").click();
        frame.optionPane().requireTitle("Update Tindakan");
        frame.optionPane().okButton().click();

        frame.textBox("txtCari").enterText("TIND.0002");
        frame.table("tabelTindakan").selectRows(0);
        frame.textBox("txtNamaTindakan").enterText(" Kecil");
        frame.button("btnUpdate").click();
        frame.optionPane().requireTitle("Update Tindakan");
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
    public void g_deleteDataTindakan() {
        System.out.println("7. DeleteDataTindakan");
        frame.menuItem("menuDataTindakan").click();

        frame.table("tabelTindakan").selectRows(0);
        frame.button("btnDelete").click();
        frame.optionPane().requireTitle("Konfirmasi");
        frame.optionPane().yesButton().click();
        frame.optionPane().requireTitle("Delete Tindakan");
        frame.optionPane().okButton().click();

        frame.table("tabelTindakan").selectRows(0);
        frame.button("btnDelete").click();
        frame.optionPane().requireTitle("Konfirmasi");
        frame.optionPane().yesButton().click();
        frame.optionPane().requireTitle("Delete Tindakan");
        frame.optionPane().okButton().click();
    }

    @Test
    public void h_deleteDataSpesialisBerhasil() {
        System.out.println("6. DeleteDataSpesialisBerhasil");
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
