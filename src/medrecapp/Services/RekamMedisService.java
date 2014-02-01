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
import medrecapp.Dao.RekamMedisDao;
import medrecapp.Entity.RekamMedis;
import medrecapp.Interfaces.RekamMedisInterface;
import medrecapp.KoneksiDatabase.KoneksiDB;

/**
 *
 * @author Hady
 */
public class RekamMedisService {
    private Connection connection;
    private RekamMedisInterface ri;

    public RekamMedisService() {
        this.connection = KoneksiDB.getConnection();
        this.ri = new RekamMedisDao(connection);
    }
    
    public void serviceInsertRekamMedis(RekamMedis r){
        try{
            connection.setAutoCommit(false);
            ri.insertRekamMedis(r);
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
    
    public void serviceUpdatePasienMasukPoli(String noDaftar){
        try{
            connection.setAutoCommit(false);
            ri.updatePasienMasukPoli(noDaftar);
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
    
//    public void serviceDeleteRekamMedis(String noDaftar){
//        try{
//            connection.setAutoCommit(false);
//            ri.deleteRekamMedis(noDaftar);
//            connection.setAutoCommit(true);
//        }catch(SQLException se){
//            try{
//                connection.rollback();
//                connection.setAutoCommit(true);
//            }catch(SQLException see){
//                Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, see);
//            }
//            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, se);
//        }
//    }
    
    public List serviceGetAllAtributRekmedByNoDaftar(String noDaftar){
        try{
            return ri.getAllAtributRekmedByNoDaftar(noDaftar);
        }catch(SQLException se){
            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, se);
            return null;
        }
    }

    public List serviceGetAllAtributRekmed(){
        try{
            return ri.getAllAtributRekmed();
        }catch(SQLException se){
            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, se);
            return null;
        }
    }

    public List serviceGetRekamMedisByNoRm(String noRm){
        try{
            return ri.getRekamMedisByNoRm(noRm);
        }catch(SQLException se){
            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, se);
            return null;
        }
    }

    public String serviceGenerateNomorDaftar(String tanggal){
        try {
            return ri.generateNomor(tanggal);
        } catch (SQLException ex) {
            Logger.getLogger(RekamMedisService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List serviceGetRekamMedisByNoDaftar(String noDaftar){
        try{
            return ri.getRekamMedisByNoDaftar(noDaftar);
        }catch(SQLException se){
            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, se);
            return null;
        }
    }

    public List serviceGetRekamMedisPasienAntri(String poli, String tanggal){
        try{
            return ri.getRekamMedisPasienAntri(poli, tanggal);
        }catch(SQLException se){
            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, se);
            return null;
        }
    }

    public List serviceGetRekamMedisPasienPoli(String poli, String tanggal){
        try{
            return ri.getRekamMedisPasienPoli(poli, tanggal);
        }catch(SQLException se){
            Logger.getLogger(DokterService.class.getName()).log(Level.SEVERE, null, se);
            return null;
        }
    }

    public void serviceUpdatePemeriksaanAwal(RekamMedis rm, String noDaftar){
        try{
            connection.setAutoCommit(false);
            ri.updatePemeriksaanAwal(rm, noDaftar);
            connection.setAutoCommit(true);
        }catch(SQLException se){
            try{
                connection.rollback();
                connection.setAutoCommit(true);
            }catch(SQLException see){
                Logger.getLogger(PerawatService.class.getName()).log(Level.SEVERE, null, see);
            }
            Logger.getLogger(PerawatService.class.getName()).log(Level.SEVERE, null, se);
        }
    }

    public void serviceUpdatePemeriksaanLanjutan(RekamMedis rm, String noDaftar){
        try{
            connection.setAutoCommit(false);
            ri.updatePemeriksaanLanjutan(rm, noDaftar);
            connection.setAutoCommit(true);
        }catch(SQLException se){
            try{
                connection.rollback();
                connection.setAutoCommit(true);
            }catch(SQLException see){
                Logger.getLogger(PerawatService.class.getName()).log(Level.SEVERE, null, see);
            }
            Logger.getLogger(PerawatService.class.getName()).log(Level.SEVERE, null, se);
        }
    }

    public void serviceDeleteRekmed(){
        try{
            connection.setAutoCommit(false);
            ri.deleteRekamMedis();
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
}
