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
import medrecapp.Dao.JaminanDao;
import medrecapp.Entity.Jaminan;
import medrecapp.Interfaces.JaminanInterface;
import medrecapp.KoneksiDatabase.KoneksiDB;

/**
 *
 * @author Hady
 */
public class JaminanService {
    private Connection connection;
    private JaminanInterface ji;
    
    public JaminanService(){
        this.connection = KoneksiDB.getConnection();
        this.ji = new JaminanDao(connection);
    }
    
    public void serviceInsertJaminan(Jaminan j){
        try{
            connection.setAutoCommit(false);
            ji.insertJaminan(j);
            connection.setAutoCommit(true);
        }catch(SQLException se){
            try{
                connection.rollback();
                connection.setAutoCommit(true);
            }catch(SQLException see){
                Logger.getLogger(JaminanService.class.getName()).log(Level.SEVERE, null, see);
            }
            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, se);
        }
    }
    
    public void serviceUpdateJaminan(Jaminan j, String idJaminan){
        try{
            connection.setAutoCommit(false);
            ji.updateJaminan(j, idJaminan);
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
    
    public void serviceDeleteJaminan(String idJaminan){
        try{
            connection.setAutoCommit(false);
            ji.deleteJaminan(idJaminan);
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
    public List serviceGetAllJaminan(){
        try{
            return ji.getAllJaminan();
        }catch(SQLException se){
            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, se);
            return null;
        }
    }

    public String[] serviceGetAllIdJaminan(int b){
        try{
            return ji.getAllIdJaminan(b);
        }catch(SQLException t){
            Logger.getLogger(SpesialisService.class.getName()).log(Level.SEVERE, null, t);
            return null;
        }
    }

    public List serviceGetAllJaminanById(String idJaminan){
        try{
            return ji.getAllJaminanById(idJaminan);
        }catch(SQLException se){
            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, se);
            return null;
        }
    }

    public List serviceGetAllJaminanByNm(String nmJaminan){
        try{
            return ji.getAllJaminanByNm(nmJaminan);
        }catch(SQLException se){
            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, se);
            return null;
        }
    }

}


