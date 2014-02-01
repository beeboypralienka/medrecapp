/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Interfaces;

import java.sql.SQLException;
import java.util.List;
import medrecapp.Entity.Resep;

/**
 *
 * @author Fachrul Pralienka BM
 */
public interface ResepInterface {

    public void insertResep(Resep rs)throws SQLException;
    public void updateResep(Resep rs, String noResep)throws SQLException;
    public void deleteResep(String noResep)throws SQLException;
    public List getAllResep()throws SQLException;

    public List getAllResepByNoResep(String noResep)throws SQLException;
    public void tearDownResep()throws SQLException;
}
