/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Interfaces;

import java.sql.SQLException;
import java.util.List;
import medrecapp.Entity.ResepDt;

/**
 *
 * @author Fachrul Pralienka BM
 */
public interface ResepDtInterface {

    public void insertResepDt(ResepDt rdt)throws SQLException;
    public void updateResepDt(ResepDt rdt, String noResep, String idObat)throws SQLException;
    public void deleteResepDt(String idObat)throws SQLException;
    public List getAllResepDt()throws SQLException;

    public List getAllResepDtByNoResep(String noResep)throws SQLException;

}
