/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Services;

import com.mysql.jdbc.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import medrecapp.Dao.SpesialisDao;
import medrecapp.Entity.Spesialis;
import medrecapp.Interfaces.SpesialisInterface;
import medrecapp.KoneksiDatabase.KoneksiDB;

/**
 *
 * @author Fachrul Pralienka BM
 */
public class SpesialisService {

    private Connection connection;
    private SpesialisInterface si;

    public SpesialisService() {
        this.connection = KoneksiDB.getConnection();
        this.si = new SpesialisDao(connection);
    }

    public void serviceInsertSpesialis(Spesialis sp){
        try{
            connection.setAutoCommit(false);
            si.insertSpesialis(sp);
            connection.setAutoCommit(true);
        }catch(SQLException se){
            try{
                connection.rollback();
                connection.setAutoCommit(true);
            }catch(SQLException see){
                Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, see);
            }
            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, se);
        }
    }

    public void serviceUpdateSpesialis(Spesialis sp, String idSpesialis){
        try{
            connection.setAutoCommit(false);
            si.updateSpesialis(sp, idSpesialis);
            connection.setAutoCommit(true);
        }catch(SQLException se){
            try{
                connection.rollback();
                connection.setAutoCommit(true);
            }catch(SQLException see){
                Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, see);
            }
            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, se);
        }
    }

    public void serviceDeleteSpesialis(String idSpesialis){
        try{
            connection.setAutoCommit(false);
            si.deleteSpesialis(idSpesialis);
            connection.setAutoCommit(true);
        }catch(SQLException se){
            try{
                connection.rollback();
                connection.setAutoCommit(true);
            }catch(SQLException see){
                Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, see);
            }
            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, se);
        }
    }

    public List serviceGetAllSpesialis(){
        try{
            return si.getAllSpesialis();
        }catch(SQLException se){
            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, se);
            return null;
        }
    }

    public String[] serviceGetAllNamaSpesialis(int b){
        try{
            return si.getAllNmSpesialis(b);
        }catch(SQLException t){
            Logger.getLogger(SpesialisService.class.getName()).log(Level.SEVERE, null, t);
            return null;
        }
    }

    public String serviceGetIDSpesialis(String b){
        try{
            return si.getIDSpesialis(b);
        }catch(SQLException t){
            Logger.getLogger(SpesialisService.class.getName()).log(Level.SEVERE, null, t);
            return null;
        }
    }

    public String serviceGetNmSpesialis(String id){
        try{
            return si.getNmSpesialis(id);
        }catch(SQLException t){
            Logger.getLogger(SpesialisService.class.getName()).log(Level.SEVERE, null, t);
            return null;
        }
    }

    public List serviceGetAllSpesialisById(String idSpesialis){
        try{
            return si.getAllSpesialisById(idSpesialis);
        }catch(SQLException se){
            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, se);
            return null;
        }
    }

    public List serviceGetAllSpesialisByNm(String nmSpesialis){
        try{
            return si.getAllSpesialisByNm(nmSpesialis);
        }catch(SQLException se){
            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, se);
            return null;
        }
    }

}
