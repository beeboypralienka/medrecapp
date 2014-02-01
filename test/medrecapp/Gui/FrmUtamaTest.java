/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Gui;

import medrecapp.Services.JaminanService;
import medrecapp.Services.SpesialisService;
import medrecapp.Services.StafService;
import medrecapp.Services.DokterService;
import medrecapp.Services.PerawatService;
import medrecapp.Services.PasienService;
import medrecapp.Services.RekamMedisService;
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
public class FrmUtamaTest {

    private static FrmUtama fu;
    private static FrameFixture frame;

    public FrmUtamaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FrmUtamaTest.class.getName()).log(Level.SEVERE, null, ex);
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
        ps.serviceDeletePerawat("PER.002");

        DokterService ds = new DokterService();
        ds.serviceDeleteDokter("DOK.001");
        ds.serviceDeleteDokter("DOK.002");

        StafService sfs = new StafService();
        sfs.serviceDeleteStaf("STF.001");
        sfs.serviceDeleteStaf("STF.002");

        SpesialisService ss = new SpesialisService();
        ss.serviceDeleteSpesialis("Sp.PD");
        ss.serviceDeleteSpesialis("Sp.BD");

        JaminanService js = new JaminanService();
        js.serviceDeleteJaminan("KJS");
        js.serviceDeleteJaminan("BPJS Kesehatan");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void a_insertDataSpesialis() {
        System.out.println("1. insertDataSpesialisMata");
        frame.menuItem("menuDataSpesialis").click();
        frame.textBox("txtIDSpesialis").enterText("Sp.PD");
        frame.textBox("txtNamaSpesialis").enterText("Spesialis Penyakit Dalam");
        frame.textBox("txtTarifKonsul").enterText("80000");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Spesialis");
        frame.optionPane().okButton().click();

        frame.textBox("txtIDSpesialis").enterText("Sp.BD");
        frame.textBox("txtNamaSpesialis").enterText("Spesialis Bedah");
        frame.textBox("txtTarifKonsul").enterText("95000");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Spesialis");
        frame.optionPane().okButton().click();
    }

    @Test
    public void b_insertDataDokter() {
        System.out.println("2. insertDataDokter");
        frame.menuItem("menuDataDokter").click();

        frame.textBox("txtNamaDokter").enterText("Fuad Suhadi");
        frame.comboBox("pilihSpesialis").selectItem("Spesialis Bedah");
        frame.textBox("txtTglKerja").enterText("2013-09-02");
        frame.textBox("txtAlamat").enterText("Jakarta");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Dokter");
        frame.optionPane().okButton().click();

        frame.textBox("txtNamaDokter").enterText("Yudi Kusnaedi");
        frame.comboBox("pilihSpesialis").selectItem("Spesialis Penyakit Dalam");
        frame.textBox("txtTglKerja").enterText("2010-10-02");
        frame.textBox("txtAlamat").enterText("Bandung");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Dokter");
        frame.optionPane().okButton().click();
    }

    @Test
    public void c_insertDataPerawat() {
        System.out.println("3. insertDataPerawat");
        frame.menuItem("menuDataPerawat").click();

        frame.textBox("txtNamaPerawat").enterText("Fitriya Rahmawati");
        frame.textBox("txtTanggalKerja").enterText("2011-10-09");
        frame.comboBox("pilihBagian").selectItem("Spesialis Bedah");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Perawat");
        frame.optionPane().okButton().click();

        frame.textBox("txtNamaPerawat").enterText("Prima Puspitasari");
        frame.textBox("txtTanggalKerja").enterText("2009-04-10");
        frame.comboBox("pilihBagian").selectItem("Spesialis Penyakit Dalam");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Perawat");
        frame.optionPane().okButton().click();
    }

    @Test
    public void d_insertDataStaf() {
        System.out.println("4. insertDataStaf");
        frame.menuItem("menuDataStaf").click();

        frame.textBox("txtNamaStaf").enterText("Ashar Kurniawan");
        frame.textBox("txtAlamat").enterText("Jakarta Timur");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Staf");
        frame.optionPane().okButton().click();

        frame.textBox("txtNamaStaf").enterText("Rudi Siahaan");
        frame.textBox("txtAlamat").enterText("Jakarta Timur");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Staf");
        frame.optionPane().okButton().click();
    }

    @Test
    public void e_insertDataJaminan() {
        System.out.println("5. insertDataJaminan");
        frame.menuItem("menuDataJaminan").click();

        frame.textBox("txtIDJaminan").enterText("KJS");
        frame.textBox("txtNamaJaminan").enterText("Kartu Jakarta Sehat");
        frame.textBox("txtKeterangan").enterText("Jaminan kesehatan warga DKI Jakarta");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Jaminan");
        frame.optionPane().okButton().click();

        frame.textBox("txtIDJaminan").enterText("BPJS Kesehatan");
        frame.textBox("txtNamaJaminan").enterText("Badan Penyelenggara Jaminan Sosial");
        frame.textBox("txtKeterangan").enterText("Jaminan kesehatan untuk seluruh WNI");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Jaminan");
        frame.optionPane().okButton().click();
    }

    @Test
    public void f_insertRegistrasiPasien(){
        System.out.println("6. insertRegistrasiPasien");
        frame.menuItem("menuPasienBaru").click();

        frame.textBox("txtNmPasien").enterText("Udin Sumardin");
        frame.radioButton("radioLaki").click();
        frame.textBox("txtTglLahir").enterText("1990-09-09");
        frame.comboBox("pilihAgama").selectItem("Islam");
        frame.textBox("txtAlamat").enterText("Jl. Cisitu Lama No. 33 Bandung");
        frame.button("btnInsert").click();
        frame.optionPane().requireTitle("Insert Pasien");
        frame.optionPane().okButton().click();

        frame.comboBox("pilihPoli").selectItem("Spesialis Penyakit Dalam");
        frame.comboBox("pilihDokter").selectItem("YUDI KUSNAEDI");
        frame.comboBox("pilihJaminan").selectItem("KJS");
        frame.comboBox("pilihStaf").selectItem("Ashar Kurniawan");
        frame.button("btnDaftarkan").click();
        frame.optionPane().requireTitle("Insert Rekam Medis");
        frame.optionPane().okButton().click();
    }

    @Test
    public void g_updateRekmedMasukPoli(){
        System.out.println("7. updateRekmedMasukPoli");
        frame.menuItem("menuPoliPenyakitDalam").click();

        frame.table("tabelAntriPoli").selectRows(0);
        frame.button("tombolMasukPoli").click();
        frame.optionPane().requireTitle("Update Rekam Medis");
        frame.optionPane().okButton().click();

        frame.tabbedPane("panelPasienPoli").selectTab(1);        

        frame.table("tabelPasienPoli").selectRows(0);
        frame.button("tombolAwal").click();
        frame.dialog("periksaAwal").comboBox("pilihPerawat").selectItem("Prima Puspitasari");
        frame.dialog("periksaAwal").textBox("txtMassaBadan").enterText("65");
        frame.dialog("periksaAwal").textBox("txtTinggiBadan").enterText("170");
        frame.dialog("periksaAwal").textBox("txtTekananDarah").enterText("20/30");
        frame.dialog("periksaAwal").textBox("txtNadi").enterText("30");
        frame.dialog("periksaAwal").textBox("txtTemperatur").enterText("30");
        frame.dialog("periksaAwal").textBox("txtPernapasan").enterText("20");
        frame.dialog("periksaAwal").comboBox("pilihKesadaran").selectItem("Compos Mentis");
        frame.dialog("periksaAwal").button("tombolSimpan").click();
        frame.dialog("periksaAwal").optionPane().requireTitle("Update Pemeriksaan Awal");
        frame.dialog("periksaAwal").optionPane().okButton().click();

        frame.table("tabelPasienPoli").selectRows(0);
        frame.button("tombolLanjutan").click();
        frame.dialog("periksaLanjutan").textBox("txtAnamnesa").enterText("Sakit perut");
        frame.dialog("periksaLanjutan").textBox("txtDiagnosis").enterText("Radang lambung");
        frame.dialog("periksaLanjutan").textBox("txtTerapi").enterText("Terapi Energi");
        frame.dialog("periksaLanjutan").button("tombolOK").click();
        frame.dialog("periksaLanjutan").optionPane().requireTitle("Update Pemeriksaan Lanjutan");
        frame.dialog("periksaLanjutan").optionPane().okButton().click();

    }

}