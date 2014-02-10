/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmIntPasienLama.java
 *
 * Created on Dec 28, 2013, 9:00:47 PM
 */
package medrecapp.Gui.Internal;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import medrecapp.Dao.PasienDao;
import medrecapp.Dao.RekamMedisDao;
import medrecapp.Entity.RekamMedis;
import medrecapp.Services.DokterService;
import medrecapp.Services.JaminanService;
import medrecapp.Services.PasienService;
import medrecapp.Services.RekamMedisService;
import medrecapp.Services.SpesialisService;
import medrecapp.Services.StafService;
import medrecapp.TabelModel.TabelModelDokter;
import medrecapp.TabelModel.TabelModelJaminan;
import medrecapp.TabelModel.TabelModelPasien;
import medrecapp.TabelModel.TabelModelSpesialis;
import medrecapp.TabelModel.TabelModelStaf;

/**
 *
 * @author Fachrul Pralienka BM
 */
public class FrmIntPasienLama extends javax.swing.JInternalFrame {

    RekamMedisService rms = new RekamMedisService();
    PasienService ps = new PasienService();
    SpesialisService ss = new SpesialisService();
    DokterService ds = new DokterService();
    JaminanService js = new JaminanService();
    StafService sfs = new StafService();
    TabelModelSpesialis tms = new TabelModelSpesialis();
    TabelModelDokter tmd = new TabelModelDokter();
    TabelModelJaminan tmj = new TabelModelJaminan();
    TabelModelStaf tmsf = new TabelModelStaf();
    TabelModelPasien tmp = new TabelModelPasien();

    /** Creates new form FrmIntPasienLama */
    public FrmIntPasienLama() {
        initComponents();

        /* Memberi nilai isian pasien dari form penambahan pasien (FormInternal) */
        txtNoRm.setText(FrmIntPasienBaru.ID);
        txtNamaPasien.setText(FrmIntPasienBaru.nama);
        txtAlamat.setText(FrmIntPasienBaru.alamat);
        txtJenkel.setText(FrmIntPasienBaru.jk);
        txtTglLahir.setText(FrmIntPasienBaru.tglLahir);

        /* Mengisi comboBox pilihPoliTujuan dari database */
        tms.setData(ss.serviceGetAllSpesialis());
        int a = tms.getRowCount();
        pilihPoliTujuan.setModel(new javax.swing.DefaultComboBoxModel(ss.serviceGetAllNamaSpesialis(a)));

        /* Mengisi comboBox pilihJaminan dari database */
        tmj.setData(js.serviceGetAllJaminan());
        int b = tmj.getRowCount();
        pilihJaminan.setModel(new javax.swing.DefaultComboBoxModel(js.serviceGetAllIdJaminan(b)));

        tmsf.setData(sfs.serviceGetAllStaf());
        int c = tmsf.getRowCount();
        pilihStaf.setModel(new javax.swing.DefaultComboBoxModel(sfs.serviceGetAllNamaStaf(c)));

        Date dt = new Date();
        tglPendaftaran.setDate(dt);

        /* Mengisi pilihan dokter berdasarkan pilihan spesialis */
        //isiPilihanDokter();

        /* Menampilkan konfirmasi apabila form pendaftaran ini di-close */
        this.addInternalFrameListener(new InternalFrameAdapter() {

            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                if (txtNamaPasien.getText() == null ? "" == null : txtNamaPasien.getText().equals("")) {
                    dispose();
                } else {
                    int pilih = JOptionPane.showConfirmDialog(rootPane,
                            "Yakin ingin membatalkan pendaftaran ke Poliklinik?",
                            "Konfirmasi",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (pilih == JOptionPane.OK_OPTION) {
                        dispose();
                        /* Menghilangan nilai dari form penambahan pasien (internal) */
                        FrmIntPasienBaru.ID = "";
                        FrmIntPasienBaru.nama = "";
                        FrmIntPasienBaru.alamat = "";
                        FrmIntPasienBaru.jk = "";
                        FrmIntPasienBaru.tglLahir = "";
                    }
                }
            }
        });

        /* Apabila pilihan poli tujuan berubah, maka pilihan dokter akan ikut berubah */
        pilihPoliTujuan.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                try {
                    isiPilihanDokter();
                } catch (Throwable t) {
                }
            }
        });


    }

    public void isiPilihanDokter() {
        /*Me-remove All item dengan setModel*/
        String[] list = new String[]{""};
        pilihNamaDokter.setModel(new javax.swing.DefaultComboBoxModel(list));

        /*Mengambil data pilihan spesialis*/
        String nama = (String) pilihPoliTujuan.getSelectedItem();
        String kodeSpesialis = ss.serviceGetIDSpesialis(nama);

        /* Mencari dokter where id_spesialis = pilihSpesialis */
        tmd.setData(ds.serviceGetAllDokterByIdSpesialis(kodeSpesialis));
        int b = tmd.getRowCount();

        /* Menampilkan semua nama berdasrkan pilihan sebelumnya */
        pilihNamaDokter.setModel(new javax.swing.DefaultComboBoxModel(ds.serviceTampilNamaDokter(kodeSpesialis, b)));
    }

    public void refresh() {
        txtNoRm.setText("");
        txtNamaPasien.setText("");
        txtJenkel.setText("");
        txtTglLahir.setText("");
        txtAlamat.setText("");
        Date dt = new Date();
        tglPendaftaran.setDate(dt);
        pilihPoliTujuan.setSelectedIndex(0);
        pilihJaminan.setSelectedIndex(0);
        pilihStaf.setSelectedIndex(0);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtNoRm = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNamaPasien = new javax.swing.JTextField();
        txtAlamat = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTglLahir = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtJenkel = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        pilihPoliTujuan = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        pilihNamaDokter = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        pilihJaminan = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        tglPendaftaran = new com.toedter.calendar.JDateChooser();
        pilihStaf = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        btnDaftarkan = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Pendaftaran");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Pasien"));

        txtNoRm.setName("txtNoRm"); // NOI18N
        txtNoRm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNoRmKeyReleased(evt);
            }
        });

        jLabel1.setText("No. RM");

        jLabel3.setText("Nama Pasien");

        txtNamaPasien.setBackground(new java.awt.Color(204, 255, 255));
        txtNamaPasien.setEditable(false);
        txtNamaPasien.setFocusable(false);

        txtAlamat.setBackground(new java.awt.Color(204, 255, 255));
        txtAlamat.setEditable(false);
        txtAlamat.setFocusable(false);

        jLabel7.setText("Alamat");

        jLabel6.setText("Tanggal Lahir");

        txtTglLahir.setBackground(new java.awt.Color(204, 255, 255));
        txtTglLahir.setEditable(false);
        txtTglLahir.setFocusable(false);

        jLabel8.setText("Jenis Kelamin");

        txtJenkel.setBackground(new java.awt.Color(204, 255, 255));
        txtJenkel.setEditable(false);
        txtJenkel.setFocusable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNoRm, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNamaPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtTglLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtJenkel, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAlamat, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                            .addComponent(jLabel7))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNoRm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNamaPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTglLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtJenkel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Registrasi Pasien"));

        pilihPoliTujuan.setName("pilihPoli"); // NOI18N

        jLabel2.setText("Poliklinik Tujuan");

        pilihNamaDokter.setName("pilihDokter"); // NOI18N

        jLabel4.setText("Nama Dokter");

        jLabel5.setText("Jenis Jaminan");

        pilihJaminan.setName("pilihJaminan"); // NOI18N

        jLabel10.setText("Tanggal Pendaftaran");

        tglPendaftaran.setDateFormatString("yyyy-MM-dd");

        pilihStaf.setName("pilihStaf"); // NOI18N

        jLabel11.setText("Staf");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(pilihNamaDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pilihJaminan, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 212, Short.MAX_VALUE))
                            .addComponent(pilihStaf, 0, 232, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(tglPendaftaran, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(pilihPoliTujuan, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tglPendaftaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pilihPoliTujuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pilihNamaDokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pilihJaminan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pilihStaf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        btnDaftarkan.setText("DAFTARKAN");
        btnDaftarkan.setName("btnDaftarkan"); // NOI18N
        btnDaftarkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDaftarkanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDaftarkan, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDaftarkan, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDaftarkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDaftarkanActionPerformed
        // TODO add your handling code here:
        String namaPasien = txtNamaPasien.getText();
        int poliTujuan = pilihPoliTujuan.getSelectedIndex();
        int pilihanDokter = pilihNamaDokter.getSelectedIndex();
        int pilihanJaminan = pilihJaminan.getSelectedIndex();
        int pilihanStaf = pilihStaf.getSelectedIndex();
        String tgl = tglPendaftaran.getDate().toString();
        //JOptionPane.showMessageDialog(rootPane, "Poli Tujuan = "+poliTujuan+"\n"+"Max Dokter = "+pilihanDokter+"\n"+"Pilih Jaminan = "+pilihanJaminan+"\n"+"Pilih Staf = "+pilihanStaf);
        if ((namaPasien.equals("")) || (poliTujuan == -1) || (pilihanDokter == -1) || (pilihanJaminan == -1) || (pilihanStaf == -1) || (tgl.equals(""))) {
            JOptionPane.showMessageDialog(null, "Data tidak boleh kosong!", "Insert Rekam Medis Gagal!", JOptionPane.ERROR_MESSAGE);
        } else {

            /* Format Tanggal untuk Nomor Pendaftaran */
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
            String tglNoDaftar = sdf.format(tglPendaftaran.getDate());

            /* Format tanggal untuk pengisian tanggal ke database */
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            String tglDaftar = sdf2.format(tglPendaftaran.getDate());

            /* Panggil nilai maximal pendaftaran */
            String nomor = rms.serviceGenerateNomorDaftar(tglNoDaftar);
            if (!RekamMedisDao.hasilGenerateNomor.equals("ok")) {
                JOptionPane.showMessageDialog(null, RekamMedisDao.hasilGenerateNomor, "Generate Max Nomor Pendaftaran Gagal!", JOptionPane.ERROR_MESSAGE);
            }

            /* Nomor Rekam Medis */
            String teksRm = txtNoRm.getText();

            /* Nomor Staf */
            String staf = sfs.serviceGetIDStaf(pilihStaf.getSelectedItem().toString());

            /* ID Spesialis */
            String spesialis = ss.serviceGetIDSpesialis(pilihPoliTujuan.getSelectedItem().toString());

            /* ID Jaminan */
            String jaminan = pilihJaminan.getSelectedItem().toString();

            /* Nomor Dokter */
            String dokter = ds.serviceGetNoDokerByNama(pilihNamaDokter.getSelectedItem().toString());

            RekamMedis rm = new RekamMedis();
            rm.setNoDaftar(nomor);
            rm.setNoRm(teksRm);
            rm.setNoStaf(staf);
            rm.setBagianSpesialis(spesialis);
            rm.setIdJaminan(jaminan);
            rm.setNoDokter(dokter);
            rm.setStatus("Antri");
            rm.setTglDaftar(tglDaftar);
            rms.serviceInsertRekamMedis(rm);

            if (RekamMedisDao.hasilInsertRekamMedis.equals("ok")) {
                JOptionPane.showMessageDialog(null, "Data rekam medis berhasil ditambah!", "Insert Rekam Medis", JOptionPane.INFORMATION_MESSAGE);
                refresh();
            } else {
                JOptionPane.showMessageDialog(null, RekamMedisDao.hasilInsertRekamMedis, "Insert Rekam Medis Gagal!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnDaftarkanActionPerformed

    private void txtNoRmKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoRmKeyReleased
        // TODO add your handling code here:
        String noRm = txtNoRm.getText();
        String jenkel = null;
        tmp.setData(ps.serviceGetPasienByNo(noRm));
        if (!PasienDao.hasilGetPasienByNo.equals("ok")) {
            JOptionPane.showMessageDialog(null, PasienDao.hasilGetPasienByNo, "Get Pasien By Nomor Rekam Medis Gagal!", JOptionPane.ERROR_MESSAGE);
        } else {
            if (tmp.getRowCount() == 0) {
                txtNamaPasien.setText("");
                txtTglLahir.setText("");
                txtJenkel.setText("");
                txtAlamat.setText("");
            } else {
                txtNamaPasien.setText(tmp.getValueAt(0, 1).toString());
                txtJenkel.setText(tmp.getValueAt(0, 2).toString());
                if (txtJenkel.getText().equals("L")) {
                    txtJenkel.setText("Laki-laki");
                } else if (txtJenkel.getText().equals("P")) {
                    txtJenkel.setText("Perempuan");
                }
                txtTglLahir.setText(tmp.getValueAt(0, 3).toString());
                txtAlamat.setText(tmp.getValueAt(0, 5).toString());
            }
        }
    }//GEN-LAST:event_txtNoRmKeyReleased
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDaftarkan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JComboBox pilihJaminan;
    private javax.swing.JComboBox pilihNamaDokter;
    private javax.swing.JComboBox pilihPoliTujuan;
    private javax.swing.JComboBox pilihStaf;
    private com.toedter.calendar.JDateChooser tglPendaftaran;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtJenkel;
    private javax.swing.JTextField txtNamaPasien;
    private javax.swing.JTextField txtNoRm;
    private javax.swing.JTextField txtTglLahir;
    // End of variables declaration//GEN-END:variables
}
