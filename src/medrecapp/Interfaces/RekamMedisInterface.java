/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Interfaces;

import java.sql.SQLException;
import java.util.List;
import medrecapp.Entity.RekamMedis;

/**
 *
 * @author Fachrul Pralienka BM
 */
public interface RekamMedisInterface {

    // Insert pendaftaran pasien ke poliklinik
    public void insertRekamMedis(RekamMedis rm)throws SQLException;

    // Ubah Status Pasien, masuk ke Poliklinik
    public void updatePasienMasukPoli(String noDaftar)throws SQLException;
    
    // Menampilkan rekam medis berdasarkan Nomor Pendaftaran
    public List getRekamMedisByNoDaftar(String noDaftar)throws SQLException;
    
    // Menampilkan riwayat rekam medis berdasarkan Nomor RM Pasien
    public List getRekamMedisByNoRm(String noRm)throws SQLException;

    // Membuat nomor pendaftaran otomatis
    public String generateNomor(String tanggal)throws SQLException;

    // Menampilkan pasien poliklinik yang "Antri" berdasarkan Poliklinik dan Tanggal
    public List getRekamMedisPasienAntri(String poli, String tanggal)throws SQLException;

    // Menampilkan pasien poliklinik yang != "Antri" berdasarkan Poliklinik dan Tanggal
    public List getRekamMedisPasienPoli(String poli, String tanggal)throws SQLException;

    // Update data pemeriksaan awal
    public void updatePemeriksaanAwal(RekamMedis rm, String noDaftar)throws SQLException;

    // Update data pemeriksaan lanjutan
    public void updatePemeriksaanLanjutan(RekamMedis rm, String noDaftar)throws SQLException;

    // Delete rekam medis untuk method tearDown()
    public void deleteRekamMedis()throws SQLException;

    // Get ALL atribut di tabel rekam medis By Nomor Pendaftaran
    public List getAllAtributRekmedByNoDaftar(String noDaftar)throws SQLException;

    // Get ALL attribut dari tabel rekam medis
    public List getAllAtributRekmed()throws SQLException;
    
}
