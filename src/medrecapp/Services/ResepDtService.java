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
import medrecapp.Dao.ResepDtDao;
import medrecapp.Entity.ResepDt;
import medrecapp.Interfaces.ResepDtInterface;
import medrecapp.KoneksiDatabase.KoneksiDB;

/**
 *
 * @author Hady
 */
public class ResepDtService {
    private Connection connection;
    private ResepDtInterface rdi;

    public ResepDtService() {
        this.connection = KoneksiDB.getConnection();
        this.rdi = new ResepDtDao(connection);
    }
    
    public void serviceInsertResepDt(ResepDt r){
        try{
            connection.setAutoCommit(false);
            rdi.insertResepDt(r);
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
    
    public void serviceUpdateResepDt(ResepDt r, String noResep, String idObat){
        try{
            connection.setAutoCommit(false);
            rdi.updateResepDt(r, noResep, idObat);
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

    public void serviceDeleteResepDt(String noResep){
        try{
            connection.setAutoCommit(false);
            rdi.deleteResepDt(noResep);
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

    public List serviceGetAllResepDt(){
        try{
            return rdi.getAllResepDt();
        }catch(SQLException se){
            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, se);
            return null;
        }
    }

    public List serviceGetAllResepDtByNoResep(String noResep){
        try{
            return rdi.getAllResepDtByNoResep(noResep);
        }catch(SQLException se){
            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, se);
            return null;
        }
    }
}
