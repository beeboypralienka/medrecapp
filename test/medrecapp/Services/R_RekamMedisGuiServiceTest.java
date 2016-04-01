/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Services;

import medrecapp.Gui.FrmUtama;
import javax.swing.JFrame;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.UIManager;
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
public class R_RekamMedisGuiServiceTest {
    private static FrmUtama fu;
    private static FrameFixture frame;
    
    public R_RekamMedisGuiServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
         try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(R_RekamMedisGuiServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        fu = new FrmUtama();
        fu.setExtendedState(fu.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame = new FrameFixture(fu);
        frame.show();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        RekamMedisService rms = new RekamMedisService();
        rms.serviceDeleteRekmed();

        PasienService pns = new PasienService();
        pns.serviceDeletePasien("000001");

        PerawatService ps = new PerawatService();
        ps.serviceDeletePerawat("PER.001");        

        DokterService ds = new DokterService();
        ds.serviceDeleteDokter("DOK.001");        

        StafService sfs = new StafService();
        sfs.serviceDeleteStaf("STF.001");        

        SpesialisService ss = new SpesialisService();
        ss.serviceDeleteSpesialis("Sp.PD");        

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
    public void a_insertDataSpesialis() {
        System.out.println("1. insertDataSpesialis");
        frame.menuItem("menuDataSpesialis").click();
        frame.textBox("txtIDSpesialis").enterText("Sp.PD");
        frame.textBox("txtNamaSpesialis").enterText("Spesialis Penyakit Dalam");
        frame.textBox("txtTarifKonsul").enterText("80000");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Spesialis");
        frame.optionPane().okButton().click();
    }

    @Test
    public void d_insertDataDokter() {
        System.out.println("4. insertDataDokter");
        frame.menuItem("menuDataDokter").click();

        frame.textBox("txtNamaDokter").enterText("Yudi Kusnaedi");
        frame.comboBox("pilihSpesialis").selectItem("Spesialis Penyakit Dalam");
        frame.textBox("txtTglKerja").enterText("2010-10-02");
        frame.textBox("txtAlamat").enterText("Bandung");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Dokter");
        frame.optionPane().okButton().click();
    }

    @Test
    public void e_insertDataPerawat() {
        System.out.println("5. insertDataPerawat");
        frame.menuItem("menuDataPerawat").click();

        frame.textBox("txtNamaPerawat").enterText("Prima Puspitasari");
        frame.textBox("txtTanggalKerja").enterText("2009-04-10");
        frame.comboBox("pilihBagian").selectItem("Spesialis Penyakit Dalam");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Perawat");
        frame.optionPane().okButton().click();
    }

    @Test
    public void c_insertDataStaf() {
        System.out.println("3. insertDataStaf");
        frame.menuItem("menuDataStaf").click();

        frame.textBox("txtNamaStaf").enterText("Ashar Kurniawan");
        frame.textBox("txtAlamat").enterText("Jakarta Timur");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Staf");
        frame.optionPane().okButton().click();
    }

    @Test
    public void b_insertDataJaminan() {
        System.out.println("2. insertDataJaminan");
        frame.menuItem("menuDataJaminan").click();

        frame.textBox("txtIDJaminan").enterText("KJS");
        frame.textBox("txtNamaJaminan").enterText("Kartu Jakarta Sehat");
        frame.textBox("txtKeterangan").enterText("Khusus warga DKI");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Jaminan");
        frame.optionPane().okButton().click();
    }

    @Test
    public void f_insertRegistrasiKosongKePoli(){
        System.out.println("6. insertRegistrasiKosongKePoli");
        frame.menuItem("menuDataPasienBaru").click();

        frame.textBox("txtNmPasien").enterText("Udin Sumardin");
        frame.radioButton("radioPerempuan").click();
        frame.radioButton("radioLaki").click();
        frame.textBox("txtTglLahir").enterText("1990-09-09");
        frame.comboBox("pilihAgama").selectItem("Islam");
        frame.textBox("txtAlamat").enterText("Cisitu Lama");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Pasien");
        frame.optionPane().okButton().click();

        frame.textBox("txtNoRm").deleteText();
        frame.button("btnDaftarkan").click();
        frame.optionPane().requireTitle("Insert Rekam Medis Gagal!");
        frame.optionPane().okButton().click();

        frame.textBox("txtNoRm").enterText("857622");
        frame.button("btnDaftarkan").click();
        frame.optionPane().requireTitle("Insert Rekam Medis Gagal!");
        frame.optionPane().okButton().click();

        frame.textBox("txtNoRm").selectAll();
        frame.textBox("txtNoRm").enterText("000001");
        frame.button("btnDaftarkan").click();
        frame.optionPane().requireTitle("Insert Rekam Medis Gagal!");
        frame.optionPane().okButton().click();

        frame.comboBox("pilihPoli").selectItem("Spesialis Penyakit Dalam");
        frame.button("btnDaftarkan").click();
        frame.optionPane().requireTitle("Insert Rekam Medis Gagal!");
        frame.optionPane().okButton().click();

        frame.comboBox("pilihDokter").selectItem("YUDI KUSNAEDI");
        frame.button("btnDaftarkan").click();
        frame.optionPane().requireTitle("Insert Rekam Medis Gagal!");
        frame.optionPane().okButton().click();

        frame.comboBox("pilihJaminan").selectItem("KJS");
        frame.button("btnDaftarkan").click();
        frame.optionPane().requireTitle("Insert Rekam Medis Gagal!");
        frame.optionPane().okButton().click();
    }

    @Test
    public void g_insertRegistrasiKePoli(){
        System.out.println("7. insertRegistrasiKePoli");
                                        
        frame.comboBox("pilihStaf").selectItem("Ashar Kurniawan");
        frame.button("btnDaftarkan").click();
        frame.optionPane().requireTitle("Insert Rekam Medis");
        frame.optionPane().okButton().click();
    }

    @Test
    public void h_updateRekmedMasukPoli(){
        System.out.println("8. updateRekmedMasukPoli");
        frame.menuItem("menuPoliPenyakitDalam").click();

        frame.table("tabelAntriPoli").selectRows(0);
        frame.button("tombolMasukPoli").click();
        frame.optionPane().requireTitle("Update Rekam Medis");
        frame.optionPane().okButton().click();        
    }

    @Test
    public void i_updateRekmedPeriksaAwalKosong(){
        System.out.println("9. updateRekmedPeriksaAwalKosong");
        frame.tabbedPane("panelPasienPoli").selectTab(1);

        frame.table("tabelPasienPoli").selectRows(0);
        frame.button("tombolAwal").click();

        frame.dialog("periksaAwal").button("tombolSimpan").click();
        frame.dialog("periksaAwal").optionPane().requireTitle("Update Pemeriksaan Awal Gagal!");
        frame.dialog("periksaAwal").optionPane().okButton().click();

        frame.dialog("periksaAwal").comboBox("pilihPerawat").selectItem("Prima Puspitasari");
        frame.dialog("periksaAwal").button("tombolSimpan").click();
        frame.dialog("periksaAwal").optionPane().requireTitle("Update Pemeriksaan Awal Gagal!");
        frame.dialog("periksaAwal").optionPane().okButton().click();

        frame.dialog("periksaAwal").textBox("txtMassaBadan").enterText("65");
        frame.dialog("periksaAwal").button("tombolSimpan").click();
        frame.dialog("periksaAwal").optionPane().requireTitle("Update Pemeriksaan Awal Gagal!");
        frame.dialog("periksaAwal").optionPane().okButton().click();

        frame.dialog("periksaAwal").textBox("txtTinggiBadan").enterText("170");
        frame.dialog("periksaAwal").button("tombolSimpan").click();
        frame.dialog("periksaAwal").optionPane().requireTitle("Update Pemeriksaan Awal Gagal!");
        frame.dialog("periksaAwal").optionPane().okButton().click();

        frame.dialog("periksaAwal").textBox("txtTekananDarah").enterText("20/30");
        frame.dialog("periksaAwal").button("tombolSimpan").click();
        frame.dialog("periksaAwal").optionPane().requireTitle("Update Pemeriksaan Awal Gagal!");
        frame.dialog("periksaAwal").optionPane().okButton().click();

        frame.dialog("periksaAwal").textBox("txtNadi").enterText("30");
        frame.dialog("periksaAwal").button("tombolSimpan").click();
        frame.dialog("periksaAwal").optionPane().requireTitle("Update Pemeriksaan Awal Gagal!");
        frame.dialog("periksaAwal").optionPane().okButton().click();

        frame.dialog("periksaAwal").textBox("txtTemperatur").enterText("30");
        frame.dialog("periksaAwal").button("tombolSimpan").click();
        frame.dialog("periksaAwal").optionPane().requireTitle("Update Pemeriksaan Awal Gagal!");
        frame.dialog("periksaAwal").optionPane().okButton().click();

        frame.dialog("periksaAwal").textBox("txtPernapasan").enterText("20");
        frame.dialog("periksaAwal").button("tombolSimpan").click();
        frame.dialog("periksaAwal").optionPane().requireTitle("Update Pemeriksaan Awal Gagal!");
        frame.dialog("periksaAwal").optionPane().okButton().click();
    }

    @Test
    public void j_updateRekmedPeriksaAwal(){
        System.out.println("10. updateRekmedPeriksaAwal");
                                                        
        frame.dialog("periksaAwal").comboBox("pilihKesadaran").selectItem("Compos Mentis");
        frame.dialog("periksaAwal").button("tombolSimpan").click();
        frame.dialog("periksaAwal").optionPane().requireTitle("Update Pemeriksaan Awal");
        frame.dialog("periksaAwal").optionPane().okButton().click();
    }

    @Test
    public void k_updateRekmedPeriksaLanjutanKosong(){
        System.out.println("11. updateRekmedPeriksaLanjutanKosong");
        frame.table("tabelPasienPoli").selectRows(0);
        frame.button("tombolLanjutan").click();

        frame.dialog("periksaLanjutan").button("tombolOK").click();
        frame.dialog("periksaLanjutan").optionPane().requireTitle("Update Pemeriksaan Lanjutan Gagal!");
        frame.dialog("periksaLanjutan").optionPane().okButton().click();

        frame.dialog("periksaLanjutan").textBox("txtAnamnesa").enterText("Sakit perut");
        frame.dialog("periksaLanjutan").button("tombolOK").click();
        frame.dialog("periksaLanjutan").optionPane().requireTitle("Update Pemeriksaan Lanjutan Gagal!");
        frame.dialog("periksaLanjutan").optionPane().okButton().click();

        frame.dialog("periksaLanjutan").textBox("txtDiagnosis").enterText("Radang lambung");
        frame.dialog("periksaLanjutan").button("tombolOK").click();
        frame.dialog("periksaLanjutan").optionPane().requireTitle("Update Pemeriksaan Lanjutan Gagal!");
        frame.dialog("periksaLanjutan").optionPane().okButton().click();
    }

    @Test
    public void l_updateRekmedPeriksaLanjutan(){
        System.out.println("12. updateRekmedPeriksaLanjutan");
                
        frame.dialog("periksaLanjutan").textBox("txtTerapi").enterText("Terapi Energi");
        frame.dialog("periksaLanjutan").button("tombolOK").click();
        frame.dialog("periksaLanjutan").optionPane().requireTitle("Update Pemeriksaan Lanjutan");
        frame.dialog("periksaLanjutan").optionPane().okButton().click();
    }

    @Test
    public void m_lihatDataRekamMedis(){
        System.out.println("12. lihatDataRekamMedis");

        frame.menuItem("menuRekamMedisPasien").click();
        frame.table("tabelPasien").selectRows(0);
        frame.button("btnRekmedByNoRm").click();
        frame.dialog("frmDlgAWTRekMedByNoRm").table("tabelRekmedByNoRm").selectRows(0);
        frame.dialog("frmDlgAWTRekMedByNoRm").button("btnRekmedByNoDaftar").click();
        frame.dialog("frmDlgAWTRekMedByNoDaftar").button("btnOK").click();

    }

}