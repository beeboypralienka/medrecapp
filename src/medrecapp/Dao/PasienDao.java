/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import medrecapp.Entity.Pasien;
import medrecapp.Interfaces.PasienInterface;

/**
 *
 * @author Fachrul Pralienka BM
 */
public class PasienDao implements PasienInterface{

    private Connection connection;

    private final String insertPasien = "INSERT INTO pasien VALUES (?,?,?,?,?,?)";
    private final String updatePasien =
            "UPDATE pasien SET nm_pas=?, jk_pas=?, tgl_lahir=?, agama=?, alamat_pas=? WHERE no_rm=?";
    private final String deletePasien = "DELETE FROM pasien WHERE no_rm=?";
    private final String getAllPasien = "SELECT * FROM pasien";

    private final String getMaxNoRm = "SELECT MAX(no_rm)+1 FROM pasien";
    private final String getAllByNo = "SELECT * FROM pasien WHERE no_rm LIKE ?";
    private final String getAllByNama = "SELECT * FROM pasien WHERE nm_pas LIKE ?";

    private final String getPasienByNo = "SELECT * FROM pasien WHERE no_rm=?";

    /* Variabel untuk mengirim data hasil */
    public static String hasilInsert;
    public static String hasilUpdate;
    public static String hasilDelete;
    public static String hasilGetAll;
    public static String hasilGetMaxNoRm;
    public static String hasilGetAllByNo;
    public static String hasilGetAllByNama;
    public static String hasilGetPasienByNo;

    public PasienDao(Connection connection) {
        this.connection = connection;
    }

    public void insertPasien(Pasien p) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(insertPasien);
            ps.setString(1, p.getNoRm());
            ps.setString(2, p.getNmPas());
            ps.setString(3, p.getJkPas());
            ps.setString(4, p.getTglLahir());
            ps.setString(5, p.getAgama());
            ps.setString(6, p.getAlamatPas());
            ps.executeUpdate();
            ps.close();            
            hasilInsert = "ok";
        }catch(SQLException se){            
            hasilInsert = se.getMessage();
        }
    }

    public void updatePasien(Pasien p, String noRm) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(updatePasien);
            ps.setString(1, p.getNmPas());
            ps.setString(2, p.getJkPas());
            ps.setString(3, p.getTglLahir());
            ps.setString(4, p.getAgama());
            ps.setString(5, p.getAlamatPas());
            ps.setString(6, noRm);
            ps.executeUpdate();
            ps.close();            
            hasilUpdate = "ok";
        }catch(SQLException se){            
            hasilUpdate = se.getMessage();
        }
    }

    public void deletePasien(String noRm) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(deletePasien);
            ps.setString(1, noRm);
            ps.executeUpdate();
            ps.close();            
            hasilDelete = "ok";
        }catch(SQLException se){            
            hasilDelete = se.getMessage();
        }
    }

    public List getAllPasien() throws SQLException {
        try{
            List list = new ArrayList();
            Statement s = (Statement) connection.createStatement();
            ResultSet rs = s.executeQuery(getAllPasien);
            while(rs.next()){                
                Pasien p = new Pasien();
                p.setNoRm(rs.getString("no_rm"));
                p.setNmPas(rs.getString("nm_pas"));
                p.setJkPas(rs.getString("jk_pas"));
                p.setTglLahir(rs.getString("tgl_lahir"));
                p.setAgama(rs.getString("agama"));
                p.setAlamatPas(rs.getString("alamat_pas"));
                list.add(p);
            }
            rs.close();
            s.close();
            hasilGetAll = "ok";
            return list;
        }catch(SQLException se){            
            hasilGetAll = se.getMessage();
            return null;
        }
    }

    public String getMaxNoRm() throws SQLException {
        try {
            String max = null, hasil = null;
            PreparedStatement ps = null;
            ps = (PreparedStatement) connection.prepareStatement(getMaxNoRm);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                max = rs.getString(1);
                if(max == null){
                    hasil = "000001";
                } else if (max.length() == 1) {
                    hasil = "00000" + max;
                } else if (max.length() == 2) {
                    hasil = "0000" + max;
                } else if (max.length() == 3){
                    hasil = "000" + max;
                } else if (max.length() == 4){
                    hasil = "00" + max;
                } else if (max.length() == 5){
                    hasil = "0" + max;
                } else {
                    hasil = max;
                }
            }
            hasilGetMaxNoRm = "ok";
            return hasil;
        } catch (Throwable t) {
            //JOptionPane.showMessageDialog(null, t.getMessage(),"Get Max Nomor Rekam Medis Gagal!", JOptionPane.ERROR_MESSAGE);
            hasilGetMaxNoRm = t.getMessage();
            return null;
        }
    }

    public List getAllByNo(String noRm) throws SQLException {
        try {
            PreparedStatement ps = null;
            List list = new ArrayList();
            ps = (PreparedStatement) connection.prepareStatement(getAllByNo);
            ps.setString(1, "%"+noRm + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pasien p = new Pasien();
                p.setNoRm(rs.getString("no_rm"));
                p.setNmPas(rs.getString("nm_pas"));
                p.setJkPas(rs.getString("jk_pas"));
                p.setTglLahir(rs.getString("tgl_lahir"));
                p.setAgama(rs.getString("agama"));
                p.setAlamatPas(rs.getString("alamat_pas"));
                list.add(p);
            }
            ps.close();
            rs.close();
            hasilGetAllByNo = "ok";
            return list;
        } catch (Throwable t) {
            //JOptionPane.showMessageDialog(null, t.getMessage(),"Get All Pasien By No. RM Gagal!", JOptionPane.ERROR_MESSAGE);
            hasilGetAllByNo = t.getMessage();
            return null;
        }
    }

    public List getAllByNama(String nmPasien) throws SQLException {
        try {
            PreparedStatement ps = null;
            List list = new ArrayList();
            ps = (PreparedStatement) connection.prepareStatement(getAllByNama);
            ps.setString(1, "%"+nmPasien + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pasien p = new Pasien();
                p.setNoRm(rs.getString("no_rm"));
                p.setNmPas(rs.getString("nm_pas"));
                p.setJkPas(rs.getString("jk_pas"));
                p.setTglLahir(rs.getString("tgl_lahir"));
                p.setAgama(rs.getString("agama"));
                p.setAlamatPas(rs.getString("alamat_pas"));
                list.add(p);
            }
            ps.close();
            rs.close();
            hasilGetAllByNama = "ok";
            return list;
        } catch (Throwable t) {
            //JOptionPane.showMessageDialog(null, t.getMessage(),"Get All Pasien By Nama Pasien Gagal!", JOptionPane.ERROR_MESSAGE);
            hasilGetAllByNama = t.getMessage();
            return null;
        }
    }

    public List getPasienByNo(String noRm) throws SQLException {
        try {
            List list = new ArrayList();
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(getPasienByNo);
            ps.setString(1, noRm);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Pasien p = new Pasien();
                p.setNoRm(rs.getString("no_rm"));
                p.setNmPas(rs.getString("nm_pas"));
                p.setJkPas(rs.getString("jk_pas"));
                p.setTglLahir(rs.getString("tgl_lahir"));
                p.setAgama(rs.getString("agama"));
                p.setAlamatPas(rs.getString("alamat_pas"));
                list.add(p);
            }
            ps.close();
            rs.close();
            hasilGetPasienByNo = "ok";
            return list;
        } catch (Throwable t) {
            //JOptionPane.showMessageDialog(null, t.getMessage(),"Get Nama Pasien By Nomor Rekam Medis Gagal!", JOptionPane.ERROR_MESSAGE);
            hasilGetPasienByNo = t.getMessage();
            return null;
        }
    }

}
