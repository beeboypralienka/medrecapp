/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Interfaces;

import java.sql.SQLException;
import java.util.List;
import medrecapp.Entity.Tindakan;

/**
 *
 * @author Hady
 */
public interface TindakanInterface {

    public void insertTindakan(Tindakan t)throws SQLException;
    public void updateTindakan(Tindakan t, String noTindakan)throws SQLException;
    public void deleteTindakan(String noTindakan)throws SQLException;
    public List getAllTindakan()throws SQLException;

    // Method yang digunakan untuk melakukan pencarian via JTextfield
    public List getAllTindakanByNo(String noTindakan)throws SQLException;
    public List getAllTindakanByNm(String nmTindakan)throws SQLException;

    // Memanggil nomor MAX pada tabel tindakan
    public String getMaxNoTindakan()throws SQLException;

    // Method untuk assertion pada testing
    public List getAllDataTindakanByNo(String noTindakan)throws SQLException;
}
