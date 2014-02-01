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
import medrecapp.Dao.DokterDao;
import medrecapp.Entity.Dokter;
import medrecapp.Interfaces.DokterInterface;
import medrecapp.KoneksiDatabase.KoneksiDB;

/**
 *
 * @author Fachrul Pralienka BM
 */
public class DokterService {

    private Connection connection;
    private DokterInterface di;

    public DokterService() {
        this.connection = KoneksiDB.getConnection();
        this.di = new DokterDao(connection);
    }

    public void serviceInsertDokter(Dokter d){
        try{
            connection.setAutoCommit(false);
            di.insertDokter(d);
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

    public void serviceUpdateDokter(Dokter d, String noDokter){
        try{
            connection.setAutoCommit(false);
            di.updateDokter(d, noDokter);
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

    public void serviceDeleteDokter(String noDokter){
        try{
            connection.setAutoCommit(false);
            di.deleteDokter(noDokter);
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

    public List serviceGetAllDokter(){
        try{
            return di.getAllDokter();
        }catch(SQLException se){
            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, se);
            return null;
        }
    }

    public List serviceGetAllDokterByNo(String noDokter){
        try{
            return di.getAllDokterByNo(noDokter);
        }catch(SQLException se){
            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, se);
            return null;
        }
    }

    public List serviceGetAllDokterByNm(String nmDokter){
        try{
            return di.getAllDokterByNm(nmDokter);
        }catch(SQLException se){
            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, se);
            return null;
        }
    }

    public String serviceGetMaxNoDokter(){
        try{
            return di.getMaxIdDokter();
        }catch(SQLException se){
            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, se);
            return null;
        }
    }

    public List serviceGetAllDokterByIdSpesialis(String idSpesialis){
        try{
            return di.getAllDokterByIdSpesialis(idSpesialis);
        }catch(SQLException se){
            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, se);
            return null;
        }
    }

    public String[] serviceTampilNamaDokter(String spesialis, int total){
        try{
            return di.getAllNamaDokter(spesialis, total);
        }catch(SQLException se){
            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, se);
            return null;
        }
    }

    public String serviceGetNoDokerByNama(String nama){
        try {
            return di.getNoDokterByNama(nama);
        } catch (SQLException ex) {
            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
