/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Interfaces;

import java.sql.SQLException;
import java.util.List;
import medrecapp.Entity.Perawat;

/**
 *
 * @author Fachrul Pralienka BM
 */
public interface PerawatInterface {

    // Methid standar CRUD perawat
    public void insertPerawat(Perawat pr)throws SQLException;
    public void updatePerawat(Perawat pr, String noPerawat)throws SQLException;
    public void deletePerawat(String noPerawat)throws SQLException;
    public List getAllPerawat()throws SQLException;

    // Method untuk mencari perawat via JTextfield
    public List getAllPerawatByNo(String noPerawat)throws SQLException;
    public List getAllPerawatByNm(String nmPerawat)throws SQLException;

    // Method untuk memanggil nilai maximal nomor perawat
    public String getMaxNoPerawat()throws SQLException;

    // Method untuk mengisi dan mengambil data perawat di JComboBox
    public String[] getAllNmPerawat(int row)throws SQLException;
    public String getNoPerawat(String nama)throws SQLException;

     // Method untuk assertion pada testing
    public List getAllDataPerawatByNo(String noPerawat)throws SQLException;

    // Method untuk menampilkan data perawat by poli --> Untuk mengisi JComboBox pada FrmDlgPasien
    //public List getAllPerawatByPoli(String poli)throws SQLException;

    // Method untuk mengisi nama perawat by poli pada JComboBox di Frm FrmDlgPasien
    //public String[] getAllNmPerawatByPoli(String poli, int row)throws SQLException;

}
