/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Interfaces;

import java.sql.SQLException;
import java.util.List;
import medrecapp.Entity.Obat;

/**
 *
 * @author Fachrul Pralienka BM
 */
public interface ObatInterface {

    public void insertObat(Obat ob)throws SQLException;
    public void updateObat(Obat ob, String idObat)throws SQLException;
    public void deleteObat(String idObat)throws SQLException;
    public List getAllObat()throws SQLException;

    public List getAllObatById(String idObat)throws SQLException;
}
