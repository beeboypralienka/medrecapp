/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Interfaces;

import java.sql.SQLException;
import java.util.List;
import medrecapp.Entity.Staf;

/**
 *
 * @author Fachrul Pralienka BM
 */
public interface StafInterface {

    public void insertStaf(Staf st)throws SQLException;
    public void updateStaf(Staf st, String noStaf)throws SQLException;
    public void deleteStaf(String noStaf)throws SQLException;
    public List getAllStaf()throws SQLException;

    // Method untuk mengisi dan mengambil data di JComboBox
    public String[] getAllNmStaf(int row)throws SQLException;
    public String getIDStaf(String nama)throws SQLException;

    // Method untuk melakukan pencarian staf via JTextfield
    public List getAllStafByNo(String noStaf)throws SQLException;
    public List getAllStafByNm(String nmStaf)throws SQLException;

    // Method untuk memanggil nilai maximal NoStaf
    public String getMaxNoStaf()throws SQLException;

}
