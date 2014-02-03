/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmIntPoliklinik.java
 *
 * Created on Dec 28, 2013, 7:33:32 PM
 */
package medrecapp.Gui.Internal;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import medrecapp.Dao.RekamMedisDao;
import medrecapp.Gui.Dialog.FrmDlgAWTPeriksaAwal;
import medrecapp.Gui.Dialog.FrmDlgAWTPeriksaLanjutan;
import medrecapp.Gui.Dialog.FrmDlgPelayanan;
import medrecapp.Gui.Dialog.FrmDlgResep;
import medrecapp.Gui.FrmUtama;
import medrecapp.Services.RekamMedisService;
import medrecapp.TabelModel.TabelModelRekmedPasienPoli;

/**
 *
 * @author Fachrul Pralienka BM
 */
public class FrmIntPoliklinik extends javax.swing.JInternalFrame {

    RekamMedisService rms = new RekamMedisService();
    TabelModelRekmedPasienPoli tabelModelRekmedPasienPoli = new TabelModelRekmedPasienPoli();

    public static String noPendaftaranPoli;        

    /** Creates new form FrmIntPoliklinik */
    public FrmIntPoliklinik() {
        initComponents();
        setTitle("POLIKLINIK PENYAKIT " + FrmUtama.poliTujuan.toUpperCase());        

        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String tgl = sdf.format(dt);
        tglPendaftaran.setText(tgl);

        tabelAntrian.setModel(tabelModelRekmedPasienPoli);
        tabelModelRekmedPasienPoli.setData(rms.serviceGetRekamMedisPasienAntri(FrmUtama.poliTujuan, tglPendaftaran.getText()));
        sesuaikanAntrian();

//        btnMasuk.setEnabled(false);
//        btnAwal.setEnabled(false);
//        btnLanjutan.setEnabled(false);
//        btnPelayanan.setEnabled(false);
//        btnResep.setEnabled(false);

        tabelAntrian.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                int row = tabelAntrian.getSelectedRow();
                if (row != -1) {
                    noPendaftaranPoli = tabelAntrian.getValueAt(row, 0).toString();
                    btnMasuk.setEnabled(true);
                }
            }
        });

        tabelPasienPoli.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                int row = tabelPasienPoli.getSelectedRow();
                if (row != -1) {
                    noPendaftaranPoli = tabelPasienPoli.getValueAt(row, 0).toString();
                    btnAwal.setEnabled(true);
                    btnLanjutan.setEnabled(true);
//                    btnPelayanan.setEnabled(true);
//                    btnResep.setEnabled(true);
                }
            }
        });

        tabDaftar.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                if (panelAntrian.isShowing()) {
                    tabelAntrian.setModel(tabelModelRekmedPasienPoli);
                    tabelModelRekmedPasienPoli.setData(rms.serviceGetRekamMedisPasienAntri(FrmUtama.poliTujuan, tglPendaftaran.getText()));
                    if(!RekamMedisDao.hasilGetRekamMedisPasienAntri.equals("ok")){
                        JOptionPane.showMessageDialog(null, RekamMedisDao.hasilGetRekamMedisPasienAntri,"Get Rekam Medis Pasien Antri Gagal!", JOptionPane.ERROR_MESSAGE);
                    }
                    sesuaikanAntrian();
                    refreshAntrian();
                } else {
                    tabelPasienPoli.setModel(tabelModelRekmedPasienPoli);
                    tabelModelRekmedPasienPoli.setData(rms.serviceGetRekamMedisPasienPoli(FrmUtama.poliTujuan, tglPendaftaran.getText()));
                    if(!RekamMedisDao.hasilGetRekamMedisPasienPoli.equals("ok")){
                        JOptionPane.showMessageDialog(null, RekamMedisDao.hasilGetRekamMedisPasienPoli,"Get Rekam Medis Pasien Poli Gagal!", JOptionPane.ERROR_MESSAGE);
                    }
                    sesuaikanPoli();
                    refreshPasienPoli();
                }
            }
        });

    }

    public void sesuaikanAntrian() {
        TableColumnModel tcm = tabelAntrian.getColumnModel();
        for (int kolom = 0; kolom < tcm.getColumnCount(); kolom++) {
            int lebarKolomMax = 0;
            for (int baris = 0; baris < tabelAntrian.getRowCount(); baris++) {
                TableCellRenderer tcr = tabelAntrian.getCellRenderer(baris, kolom);
                Object nilaiTable = tabelAntrian.getValueAt(baris, kolom);
                Component comp = tcr.getTableCellRendererComponent(tabelAntrian, nilaiTable,
                        false, false, baris, kolom);
                lebarKolomMax = Math.max(comp.getPreferredSize().width, lebarKolomMax);
            }
            TableColumn tc = tcm.getColumn(kolom);
            tc.setPreferredWidth(lebarKolomMax);
        }
    }

    public void sesuaikanPoli() {
        TableColumnModel tcm = tabelPasienPoli.getColumnModel();
        for (int kolom = 0; kolom < tcm.getColumnCount(); kolom++) {
            int lebarKolomMax = 0;
            for (int baris = 0; baris < tabelPasienPoli.getRowCount(); baris++) {
                TableCellRenderer tcr = tabelPasienPoli.getCellRenderer(baris, kolom);
                Object nilaiTable = tabelPasienPoli.getValueAt(baris, kolom);
                Component comp = tcr.getTableCellRendererComponent(tabelPasienPoli, nilaiTable,
                        false, false, baris, kolom);
                lebarKolomMax = Math.max(comp.getPreferredSize().width, lebarKolomMax);
            }
            TableColumn tc = tcm.getColumn(kolom);
            tc.setPreferredWidth(lebarKolomMax);
        }
    }

    public void refreshAntrian(){
        tabelModelRekmedPasienPoli.setData(rms.serviceGetRekamMedisPasienAntri(FrmUtama.poliTujuan, tglPendaftaran.getText()));
        btnMasuk.setEnabled(false);
        txtCariAntrian.setText("");
        sesuaikanAntrian();
    }

    public void refreshPasienPoli(){
        tabelModelRekmedPasienPoli.setData(rms.serviceGetRekamMedisPasienPoli(FrmUtama.poliTujuan, tglPendaftaran.getText()));
//        btnAwal.setEnabled(false);
//        btnLanjutan.setEnabled(false);
        sesuaikanPoli();

//        btnPelayanan.setEnabled(false);
//        btnResep.setEnabled(false);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        tabDaftar = new javax.swing.JTabbedPane();
        panelAntrian = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelAntrian = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtCariAntrian = new javax.swing.JTextField();
        btnMasuk = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnRefreshAntrian = new javax.swing.JButton();
        panelPasienPoli = new javax.swing.JPanel();
        btnResep = new javax.swing.JButton();
        txtCariDaftar = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelPasienPoli = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btnPelayanan = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnLanjutan = new javax.swing.JButton();
        btnAwal = new javax.swing.JButton();
        btnRefreshPasienPoli = new javax.swing.JButton();
        tglPendaftaran = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jLabel4.setText("Tanggal :");

        tabDaftar.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        tabDaftar.setName("panelPasienPoli"); // NOI18N

        tabelAntrian.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelAntrian.setName("tabelAntriPoli"); // NOI18N
        jScrollPane1.setViewportView(tabelAntrian);

        jLabel1.setText("Masukkan No. RM / Nama Pasien");

        btnMasuk.setText("MASUK POLIKLINIK");
        btnMasuk.setName("tombolMasukPoli"); // NOI18N
        btnMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasukActionPerformed(evt);
            }
        });

        jLabel3.setText(":");

        btnRefreshAntrian.setText("REFRESH");
        btnRefreshAntrian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshAntrianActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAntrianLayout = new javax.swing.GroupLayout(panelAntrian);
        panelAntrian.setLayout(panelAntrianLayout);
        panelAntrianLayout.setHorizontalGroup(
            panelAntrianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAntrianLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAntrianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAntrianLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addComponent(txtCariAntrian, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 561, Short.MAX_VALUE)
                .addComponent(btnRefreshAntrian, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1151, Short.MAX_VALUE)
        );
        panelAntrianLayout.setVerticalGroup(
            panelAntrianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAntrianLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAntrianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelAntrianLayout.createSequentialGroup()
                        .addGroup(panelAntrianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCariAntrian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelAntrianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRefreshAntrian, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        tabDaftar.addTab("Antrian Pasien", panelAntrian);

        btnResep.setText("RESEP OBAT");
        btnResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResepActionPerformed(evt);
            }
        });

        tabelPasienPoli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelPasienPoli.setName("tabelPasienPoli"); // NOI18N
        jScrollPane2.setViewportView(tabelPasienPoli);

        jLabel2.setText("Masukkan No. RM / Nama Pasien");

        btnPelayanan.setText("PELAYANAN");
        btnPelayanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPelayananActionPerformed(evt);
            }
        });

        jLabel5.setText(":");

        btnLanjutan.setText("LANJUTAN");
        btnLanjutan.setName("tombolLanjutan"); // NOI18N
        btnLanjutan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLanjutanActionPerformed(evt);
            }
        });

        btnAwal.setText("AWAL");
        btnAwal.setName("tombolAwal"); // NOI18N
        btnAwal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAwalActionPerformed(evt);
            }
        });

        btnRefreshPasienPoli.setText("REFRESH");
        btnRefreshPasienPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshPasienPoliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPasienPoliLayout = new javax.swing.GroupLayout(panelPasienPoli);
        panelPasienPoli.setLayout(panelPasienPoliLayout);
        panelPasienPoliLayout.setHorizontalGroup(
            panelPasienPoliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPasienPoliLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPasienPoliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPasienPoliLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addComponent(txtCariDaftar, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(btnRefreshPasienPoli, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAwal, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLanjutan, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPelayanan, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnResep, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1151, Short.MAX_VALUE)
        );
        panelPasienPoliLayout.setVerticalGroup(
            panelPasienPoliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPasienPoliLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPasienPoliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelPasienPoliLayout.createSequentialGroup()
                        .addGroup(panelPasienPoliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCariDaftar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPasienPoliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnResep, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLanjutan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAwal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPelayanan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRefreshPasienPoli, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        tabDaftar.addTab("Daftar Pasien Poliklinik", panelPasienPoli);

        tglPendaftaran.setEditable(false);
        tglPendaftaran.setFocusable(false);
        tglPendaftaran.setRequestFocusEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tglPendaftaran, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(941, Short.MAX_VALUE))
            .addComponent(tabDaftar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1156, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tglPendaftaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabDaftar, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPelayananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPelayananActionPerformed
        // TODO add your handling code here:
        FrmDlgPelayanan fdp = new FrmDlgPelayanan(null, true);
        fdp.setVisible(true);
    }//GEN-LAST:event_btnPelayananActionPerformed

    private void btnResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResepActionPerformed
        // TODO add your handling code here:
        FrmDlgResep fdrs = new FrmDlgResep(null, true);
        fdrs.setVisible(true);
    }//GEN-LAST:event_btnResepActionPerformed

    private void btnLanjutanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLanjutanActionPerformed
        // TODO add your handling code here:
        FrmDlgAWTPeriksaLanjutan fpl = new FrmDlgAWTPeriksaLanjutan(null, true);
        fpl.setVisible(true);
        refreshPasienPoli();
    }//GEN-LAST:event_btnLanjutanActionPerformed

    private void btnAwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAwalActionPerformed
        // TODO add your handling code here:
        FrmDlgAWTPeriksaAwal fpa = new FrmDlgAWTPeriksaAwal(null, true);
        fpa.setVisible(true);
        refreshPasienPoli();
    }//GEN-LAST:event_btnAwalActionPerformed

    private void btnRefreshAntrianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshAntrianActionPerformed
        // TODO add your handling code here:
        tabelModelRekmedPasienPoli.setData(rms.serviceGetRekamMedisPasienAntri(FrmUtama.poliTujuan, tglPendaftaran.getText()));
        btnMasuk.setEnabled(false);
        txtCariAntrian.setText("");
    }//GEN-LAST:event_btnRefreshAntrianActionPerformed

    private void btnRefreshPasienPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshPasienPoliActionPerformed
        // TODO add your handling code here:
        tabelModelRekmedPasienPoli.setData(rms.serviceGetRekamMedisPasienPoli(FrmUtama.poliTujuan, tglPendaftaran.getText()));
        btnAwal.setEnabled(false);
        btnLanjutan.setEnabled(false);
        btnPelayanan.setEnabled(false);
        btnResep.setEnabled(false);
    }//GEN-LAST:event_btnRefreshPasienPoliActionPerformed

    private void btnMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasukActionPerformed
        // TODO add your handling code here:
        int row = tabelAntrian.getSelectedRow();
        if (row == -1) {
            return;
        }
        rms.serviceUpdatePasienMasukPoli(tabelAntrian.getValueAt(row, 0).toString());
        if(RekamMedisDao.hasilUpdatePasienMasukPoli.equals("ok")){
            JOptionPane.showMessageDialog(null, "Data pasien berhasil masuk poli!", "Update Rekam Medis", JOptionPane.INFORMATION_MESSAGE);
            refreshAntrian();
        }else{
            JOptionPane.showMessageDialog(null, RekamMedisDao.hasilUpdatePasienMasukPoli,"Update Rekam Medis Gagal!",JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnMasukActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAwal;
    private javax.swing.JButton btnLanjutan;
    private javax.swing.JButton btnMasuk;
    private javax.swing.JButton btnPelayanan;
    private javax.swing.JButton btnRefreshAntrian;
    private javax.swing.JButton btnRefreshPasienPoli;
    private javax.swing.JButton btnResep;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelAntrian;
    private javax.swing.JPanel panelPasienPoli;
    private javax.swing.JTabbedPane tabDaftar;
    private javax.swing.JTable tabelAntrian;
    private javax.swing.JTable tabelPasienPoli;
    private javax.swing.JTextField tglPendaftaran;
    private javax.swing.JTextField txtCariAntrian;
    private javax.swing.JTextField txtCariDaftar;
    // End of variables declaration//GEN-END:variables
}
