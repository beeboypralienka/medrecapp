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

public class J_PasienGuiServiceTest {
  private static FrmUtama fu;
  private static FrameFixture frame;

    public J_PasienGuiServiceTest() {
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
        
        /***          
         * Mempercepat waktu testing GUI
         */
        frame.robot.settings().delayBetweenEvents(0);
        frame.robot.settings().dragDelay(0);
        frame.robot.settings().dropDelay(0);
        frame.robot.settings().eventPostingDelay(0);
        
        frame.menuItem("menuDataPasienBaru").click();
    }

     @AfterClass
    public static void tearDownClass() throws Exception {
        PasienService ps = new PasienService();
        ps.serviceDeletePasien("000001");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void a_insertDataPasienKosong() {
        System.out.println("1. InsertDataPasienKosong");

        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Pasien Gagal!");
        frame.optionPane().okButton().click();

        frame.textBox("txtNmPasien").enterText("Merinda");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Pasien Gagal!");
        frame.optionPane().okButton().click();

        frame.radioButton("radioPerempuan").click();
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Pasien Gagal!");
        frame.optionPane().okButton().click();

        frame.textBox("txtTglLahir").enterText("1999-09-09");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Pasien Gagal!");
        frame.optionPane().okButton().click();

        frame.comboBox("pilihAgama").selectItem("Katholik");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Pasien Gagal!");
        frame.optionPane().okButton().click();
    }

     @Test
    public void b_insertDataPasien() {
        System.out.println("2. InsertDataPasien");
                                
        frame.textBox("txtAlamat").enterText("Sumedang");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Pasien");
        frame.optionPane().okButton().click();
    }

      @Test
    public void c_updateDataPasienKosong() {
        System.out.println("3. UpdateDataPasienKosong");
        frame.menuItem("menuRekamMedisPasien").click();

        frame.textBox("txtCari").enterText("Joko Susilo");
        frame.textBox("txtCari").selectAll();
        frame.textBox("txtCari").enterText("000001");
        
        frame.table("tabelPasien").selectRows(0);
        frame.button("btnUbah").click();

        frame.dialog("frmDlgAWTPasien").textBox("txtNmPasien").deleteText();
        frame.dialog("frmDlgAWTPasien").textBox("txtTglLahir").deleteText();
        frame.dialog("frmDlgAWTPasien").textBox("txtAlamat").deleteText();
        frame.dialog("frmDlgAWTPasien").button("btnUpdate").click();
        frame.dialog("frmDlgAWTPasien").optionPane().requireTitle("Update Pasien Gagal!");
        frame.dialog("frmDlgAWTPasien").optionPane().okButton().click();

        frame.dialog("frmDlgAWTPasien").textBox("txtNmPasien").enterText("Ruminta");
        frame.dialog("frmDlgAWTPasien").button("btnUpdate").click();
        frame.dialog("frmDlgAWTPasien").optionPane().requireTitle("Update Pasien Gagal!");
        frame.dialog("frmDlgAWTPasien").optionPane().okButton().click();

        frame.dialog("frmDlgAWTPasien").radioButton("radioLaki").click();
        frame.dialog("frmDlgAWTPasien").button("btnUpdate").click();
        frame.dialog("frmDlgAWTPasien").optionPane().requireTitle("Update Pasien Gagal!");
        frame.dialog("frmDlgAWTPasien").optionPane().okButton().click();

        frame.dialog("frmDlgAWTPasien").textBox("txtTglLahir").enterText("1999-09-09");
        frame.dialog("frmDlgAWTPasien").button("btnUpdate").click();
        frame.dialog("frmDlgAWTPasien").optionPane().requireTitle("Update Pasien Gagal!");
        frame.dialog("frmDlgAWTPasien").optionPane().okButton().click();

        frame.dialog("frmDlgAWTPasien").comboBox("pilihAgama").selectItem("Hindu");
        frame.dialog("frmDlgAWTPasien").button("btnUpdate").click();
        frame.dialog("frmDlgAWTPasien").optionPane().requireTitle("Update Pasien Gagal!");
        frame.dialog("frmDlgAWTPasien").optionPane().okButton().click();
    }

    @Test
    public void d_updateDataPasien() {
        System.out.println("4. UpdateDataPasien");
                                
        frame.dialog("frmDlgAWTPasien").textBox("txtAlamat").enterText("Medan");
        frame.dialog("frmDlgAWTPasien").button("btnUpdate").click();
        frame.dialog("frmDlgAWTPasien").optionPane().requireTitle("Update Pasien");
        frame.dialog("frmDlgAWTPasien").optionPane().okButton().click();        
    }

    @Test
    public void e_deleteDataPasien() {
        System.out.println("5. DeleteDataPasien");

        frame.table("tabelPasien").selectRows(0);
        frame.button("btnHapus").click();
        frame.optionPane().requireTitle("Konfirmasi");
        frame.optionPane().yesButton().click();
        frame.optionPane().requireTitle("Delete Pasien");
        frame.optionPane().okButton().click();
    }


}