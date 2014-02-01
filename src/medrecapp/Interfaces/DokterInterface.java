/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Interfaces;

import java.sql.SQLException;
import java.util.List;
import medrecapp.Entity.Dokter;

/**
 *
 * @author Fachrul Pralienka BM
 */
public interface DokterInterface {

    // Standar CRUD untuk dokter
    public void insertDokter(Dokter d)throws SQLException;
    public void updateDokter(Dokter d, String noDokter)throws SQLException;
    public void deleteDokter(String noDokter)throws SQLException;
    public List getAllDokter()throws SQLException;

    // Method untuk pencarian data dokter
    public List getAllDokterByNo(String idDokter)throws SQLException;
    public List getAllDokterByNm(String nmDokter)throws SQLException;

    // Method untuk memberi nilai maximum pada nomor dokter
    public String getMaxIdDokter()throws SQLException;

    // Method untuk mengisi pilihan pada comboBox Nama Dokter berdasarkan ID Spesialis
    public List getAllDokterByIdSpesialis(String idSpesialis)throws SQLException;
    public String[] getAllNamaDokter(String dokter, int total)throws SQLException;

    // Method untuk mengambil No Dokter dari pilihan nama dokter di combo box
    public String getNoDokterByNama(String nama)throws SQLException;    
}
