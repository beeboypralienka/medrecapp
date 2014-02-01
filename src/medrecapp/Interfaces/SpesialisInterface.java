/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Interfaces;

import java.sql.SQLException;
import java.util.List;
import medrecapp.Entity.Spesialis;

/**
 *
 * @author Fachrul Pralienka BM
 */
public interface SpesialisInterface {

    // Standar CRUD
    public void insertSpesialis(Spesialis s)throws SQLException;
    public void updateSpesialis(Spesialis s, String idSpesialis)throws SQLException;
    public void deleteSpesialis(String idSpesialis)throws SQLException;
    public List getAllSpesialis()throws SQLException;

    // Method untuk mengisi dan mengambil data di JComboBox
    public String[] getAllNmSpesialis(int row)throws SQLException;
    public String getIDSpesialis(String nama)throws SQLException;

    // Method untuk memilih data JComboBox dari tabel
    public String getNmSpesialis(String id)throws SQLException;

    // Method untuk mencari spesalis via JTextField
    public List getAllSpesialisById(String idSpesialis)throws SQLException;
    public List getAllSpesialisByNm(String nmSpesialis)throws SQLException;
}
