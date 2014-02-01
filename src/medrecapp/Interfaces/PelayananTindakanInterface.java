/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Interfaces;

import java.sql.SQLException;
import java.util.List;
import medrecapp.Entity.PelayananTindakan;

/**
 *
 * @author Fachrul Pralienka BM
 */
public interface PelayananTindakanInterface {

    public void insertPelayananTindakan(PelayananTindakan pt)throws SQLException;
    public void updatePelayananTindakan(String noTindakan,PelayananTindakan pt)throws SQLException;
    public void deletePelayananTindakan(String noDaftar)throws SQLException;
    public List getAllPelayananTindakan()throws SQLException;

    public List getAllByNoDaftar(String noDaftar)throws SQLException;

}
