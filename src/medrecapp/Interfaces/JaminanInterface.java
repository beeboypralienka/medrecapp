/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Interfaces;

import java.sql.SQLException;
import java.util.List;
import medrecapp.Entity.Jaminan;

/**
 *
 * @author Fachrul Pralienka BM
 */
public interface JaminanInterface {

    public void insertJaminan(Jaminan j)throws SQLException;
    public void updateJaminan(Jaminan j, String idJaminan)throws SQLException;
    public void deleteJaminan(String idJaminan)throws SQLException;
    public List getAllJaminan()throws SQLException;

    // Method untuk mengisi dan mengambil data di JComboBox
    public String[] getAllIdJaminan(int row)throws SQLException;
    
    // Method untuk pencarian jaminan via JTextField
    public List getAllJaminanById(String idJaminan)throws SQLException;
    public List getAllJaminanByNm(String nmJaminan)throws SQLException;

}
