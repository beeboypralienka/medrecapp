/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import javax.swing.JOptionPane;
import medrecapp.Entity.Tindakan;
import medrecapp.Interfaces.TindakanInterface;

/**
 *
 * @author Hady
 */
public class TindakanDao implements TindakanInterface{
    
    private Connection connection;
    
    private final String insertTindakan = "INSERT INTO tindakan VALUES (?,?,?,?)";
    private final String updateTindakan = "UPDATE tindakan SET nm_tindakan=?, tindakan_spesialis=?, ket_tindakan=? WHERE no_tindakan=?";
    private final String deleteTindakan = "DELETE FROM tindakan WHERE no_tindakan = ?";
    private final String getAllTindakan = 
            "SELECT t.no_tindakan, t.nm_tindakan, s.nm_spesialis, t.ket_tindakan "
            + "FROM tindakan t, spesialis s "
            + "WHERE t.tindakan_spesialis = s.id_spesialis ORDER BY t.no_tindakan ASC";
    private final String getAllByNoTindakan = 
            "SELECT t.no_tindakan, t.nm_tindakan, s.nm_spesialis, t.ket_tindakan "
            + "FROM tindakan t, spesialis s "
            + "WHERE t.tindakan_spesialis = s.id_spesialis AND t.no_tindakan LIKE ?";
    private final String getAllByNmTindakan =
             "SELECT t.no_tindakan, t.nm_tindakan, s.nm_spesialis, t.ket_tindakan "
            + "FROM tindakan t, spesialis s "
            + "WHERE t.tindakan_spesialis = s.id_spesialis AND t.nm_tindakan LIKE ?";
    private final String getMaxNoTindakan = "SELECT MAX(SUBSTR(no_tindakan,6,8))+1 FROM tindakan";
    private final String getAllDataTindakanByNo = "SELECT * FROM tindakan WHERE no_tindakan = ?";

    public static String hasilInsert;
    public static String hasilUpdate;
    public static String hasilDelete;
    public static String hasilGetAll;

    public TindakanDao(Connection connection){
        this.connection = connection;
    }
    
    public void insertTindakan(Tindakan t) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(insertTindakan);
            ps.setString(1, t.getNoTindakan());
            ps.setString(2, t.getNmTindakan());
            ps.setString(3, t.getTindakanSpesialis());
            ps.setString(4, t.getKetTindakan());
            ps.executeUpdate();
            ps.close();
            //JOptionPane.showMessageDialog(null, "Data tindakan berhasil ditambah!", "Insert Tindakan", JOptionPane.INFORMATION_MESSAGE);
            hasilInsert = "ok";
        }catch(SQLException se){
            //JOptionPane.showMessageDialog(null, se.getMessage(), "Insert Tindakan Gagal", JOptionPane.ERROR_MESSAGE);
            hasilInsert = se.getMessage();
        }
    }

    public void updateTindakan(Tindakan t, String noTindakan) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(updateTindakan);
            ps.setString(1, t.getNmTindakan());
            ps.setString(2, t.getTindakanSpesialis());
            ps.setString(3, t.getKetTindakan());
            ps.setString(4, noTindakan);
            ps.executeUpdate();
            ps.close();
            //JOptionPane.showMessageDialog(null, "Data tindakan berhasil diubah", "Update Tindakan", JOptionPane.INFORMATION_MESSAGE);
            hasilUpdate = "ok";
        }catch(SQLException se){
            //JOptionPane.showMessageDialog(null, se.getMessage(), "Update Tindakan Gagal!", JOptionPane.ERROR_MESSAGE);
            hasilUpdate = se.getMessage();
        }
    }

    public void deleteTindakan(String noTindakan) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(deleteTindakan);
            ps.setString(1, noTindakan);
            ps.executeUpdate();
            ps.close();
            //JOptionPane.showMessageDialog(null, "Data tindakan berhasil dihapus!", "Delete Tindakan", JOptionPane.INFORMATION_MESSAGE);
            hasilDelete = "ok";
        }catch(SQLException se){
            //JOptionPane.showMessageDialog(null, se.getMessage(), "Delete Tindakan Gagal", JOptionPane.ERROR_MESSAGE);
            hasilDelete = se.getMessage();
        }
    }

    public List getAllTindakan() throws SQLException {
        try{
            List list = new ArrayList();
            Statement s = (Statement) connection.createStatement();
            ResultSet rs = s.executeQuery(getAllTindakan);
            while(rs.next()){
                Tindakan t = new Tindakan();
                t.setNoTindakan(rs.getString("no_tindakan"));
                t.setNmTindakan(rs.getString("nm_tindakan"));
                t.setTindakanSpesialis(rs.getString("nm_spesialis"));
                t.setKetTindakan(rs.getString("ket_tindakan"));
                list.add(t);
            }
            rs.close();
            s.close();
            hasilGetAll = "ok";
            return list;
        }catch(SQLException se){
            //JOptionPane.showMessageDialog(null, se.getMessage(), "Get All Tindakan Gagal", JOptionPane.ERROR_MESSAGE);
            hasilGetAll = se.getMessage();
            return null;
        }
    }

    public List getAllTindakanByNo(String noTindakan) throws SQLException {
        try{
            PreparedStatement ps = null;
            List list = new ArrayList();
            ps = (PreparedStatement) connection.prepareStatement(getAllByNoTindakan);
            ps.setString(1, "%" + noTindakan + "%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Tindakan t = new Tindakan();
                t.setNoTindakan(rs.getString("no_tindakan"));
                t.setNmTindakan(rs.getString("nm_tindakan"));
                t.setTindakanSpesialis(rs.getString("nm_spesialis"));
                t.setKetTindakan(rs.getString("ket_tindakan"));
                list.add(t);
            }
            ps.close();
            rs.close();
            return list;
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null, se.getMessage(), "Get All Tindakan By Nomor Gagal", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
    }

    public List getAllTindakanByNm(String nmTindakan) throws SQLException {
        try{
            PreparedStatement ps = null;
            List list = new ArrayList();
            ps = (PreparedStatement) connection.prepareStatement(getAllByNmTindakan);
            ps.setString(1, "%" + nmTindakan + "%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Tindakan t = new Tindakan();
                t.setNoTindakan(rs.getString("no_tindakan"));
                t.setNmTindakan(rs.getString("nm_tindakan"));
                t.setTindakanSpesialis(rs.getString("nm_spesialis"));
                t.setKetTindakan(rs.getString("ket_tindakan"));
                list.add(t);
            }
            ps.close();
            rs.close();
            return list;
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null, se.getMessage(), "Get All Tindakan By Nama Gagal", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
    }

    public String getMaxNoTindakan() throws SQLException {
        try {
            String max = null, hasil = null;
            PreparedStatement ps = null;
            ps = (PreparedStatement) connection.prepareStatement(getMaxNoTindakan);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                max = rs.getString(1);
                if(max == null){
                    hasil = "0001";
                } else if (max.length() == 1) {
                    hasil = "000" + max;
                } else if (max.length() == 2) {
                    hasil = "00" + max;
                } else if (max.length() == 3) {
                    hasil = "0" + max;
                } else {
                    hasil = max;
                }
            }
            return hasil;
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(null, t.getMessage(),
                    "Get Max Nomor Tindakan Gagal!", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public List getAllDataTindakanByNo(String noTindakan) throws SQLException {
        try{
            PreparedStatement ps = null;
            List list = new ArrayList();
            ps = (PreparedStatement) connection.prepareStatement(getAllDataTindakanByNo);
            ps.setString(1, noTindakan);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Tindakan t = new Tindakan();
                t.setNoTindakan(rs.getString("no_tindakan"));
                t.setNmTindakan(rs.getString("nm_tindakan"));
                t.setTindakanSpesialis(rs.getString("tindakan_spesialis"));
                t.setKetTindakan(rs.getString("ket_tindakan"));
                list.add(t);
            }
            ps.close();
            rs.close();
            return list;
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null, se.getMessage(), "Get All Tindakan By Nomor Gagal", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
    }
}
