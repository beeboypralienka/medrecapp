/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Interfaces;

import java.io.BufferedOutputStream;
import java.sql.SQLException;
import java.util.List;
import medrecapp.Entity.Pasien;

/**
 *
 * @author Fachrul Pralienka BM
 */
public interface PasienInterface {

    public void insertPasien(Pasien p)throws SQLException;
    public void updatePasien(Pasien p, String noRm)throws SQLException;
    public void deletePasien(String noRm)throws SQLException;
    public List getAllPasien()throws SQLException;

    // Mengambil nilai MAX dari No. Rekam Medis
    public String getMaxNoRm()throws SQLException;

    // Mencari pasien berdasarkan No. RM atau Nama pasien
    public List getAllByNo(String noRm)throws SQLException;
    public List getAllByNama(String nmPasien)throws SQLException;

    // Mengambil satu data pasien berdasarkan no rm
    public List getPasienByNo(String noRm)throws SQLException;
//    public String getJenkelPasienByNo(String noRm)throws SQLException;
//    public String getTglLahirPasienByNo(String noRm)throws SQLException;
//    public String getAgamaPasienByNo(String noRm)throws SQLException;
//    public String getAlamatPasienByNo(String noRm)throws SQLException;

}
