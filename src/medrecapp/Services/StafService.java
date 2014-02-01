/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Services;

import com.mysql.jdbc.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import medrecapp.Dao.StafDao;
import medrecapp.Entity.Staf;
import medrecapp.Interfaces.StafInterface;
import medrecapp.KoneksiDatabase.KoneksiDB;

/**
 *
 * @author Hady
 */
public class StafService {
    private Connection connection;
    private StafInterface si;

    public StafService() {
        this.connection = KoneksiDB.getConnection();
        this.si = new StafDao(connection);
    }
    
    public void serviceInsertStaf(Staf s){
        try{
            connection.setAutoCommit(false);
            si.insertStaf(s);
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
    
    public void serviceUpdateStaf(Staf s, String noStaf){
        try{
            connection.setAutoCommit(false);
            si.updateStaf(s, noStaf);
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
    
    public void serviceDeleteStaf(String noStaf){
        try{
            connection.setAutoCommit(false);
            si.deleteStaf(noStaf);
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
    
    public List serviceGetAllStaf(){
        try{
            return si.getAllStaf();
        }catch(SQLException se){
            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, se);
            return null;
        }
    }

    public String[] serviceGetAllNamaStaf(int row){
        try{
            return si.getAllNmStaf(row);
        }catch(SQLException t){
            Logger.getLogger(SpesialisService.class.getName()).log(Level.SEVERE, null, t);
            return null;
        }
    }

    public String serviceGetIDStaf(String nama){
        try{
            return si.getIDStaf(nama);
        }catch(SQLException t){
            Logger.getLogger(SpesialisService.class.getName()).log(Level.SEVERE, null, t);
            return null;
        }
    }

    public List serviceGetAllStafByNo(String noStaf){
        try{
            return si.getAllStafByNo(noStaf);
        }catch(SQLException se){
            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, se);
            return null;
        }
    }

    public List serviceGetAllStafByNm(String nmStaf){
        try{
            return si.getAllStafByNm(nmStaf);
        }catch(SQLException se){
            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, se);
            return null;
        }
    }

    public String serviceGetMaxNoStaf(){
        try{
            return si.getMaxNoStaf();
        }catch(SQLException se){
            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, se);
            return null;
        }
    }
}
