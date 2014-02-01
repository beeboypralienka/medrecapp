/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import medrecapp.Entity.Obat;
import medrecapp.Interfaces.ObatInterface;

/**
 *
 * @author Hady
 */
public class ObatDao implements ObatInterface{
    
    private Connection connection;
    
    private final String insertObat = "INSERT INTO obat VALUES(?,?)";
    private final String updateObat = "UPDATE obat SET ket_obat=? WHERE id_obat=?";
    private final String deleteObat = "DELETE FROM obat WHERE id_obat=?";
    private final String getAllObat = "SELECT * FROM obat";

    private final String getAllByIdObat = "SELECT * FROM obat WHERE id_obat LIKE ?";

    public static String hasilInsert;
    public static String hasilUpdate;
    public static String hasilDelete;
    public static String hasilGetAll;
    public static String hasilGetAllObatById;
    
    public ObatDao(Connection connection){
        this.connection = connection;
    }

    public void insertObat(Obat ob) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(insertObat);
            ps.setString(1, ob.getIdObat());
            ps.setString(2, ob.getKetObat());
            ps.executeUpdate();
            ps.close();
            //JOptionPane.showMessageDialog(null, "Data obat berhasil ditambah","Insert Obat", JOptionPane.INFORMATION_MESSAGE);
            hasilInsert = "ok";
        }catch(SQLException se){
            //JOptionPane.showMessageDialog(null, se.getMessage(),"Insert Obat Gagal", JOptionPane.ERROR_MESSAGE);
            hasilInsert = se.getMessage();
        }
    }

    public void updateObat(Obat ob, String idObat) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(updateObat);
            ps.setString(1, ob.getKetObat());
            ps.setString(2, idObat);
            ps.executeUpdate();
            ps.close();
            //JOptionPane.showMessageDialog(null, "Data obat berhasil diubah","Update Obat",JOptionPane.INFORMATION_MESSAGE);
            hasilUpdate = "ok";
        }catch(SQLException se){
            //JOptionPane.showMessageDialog(null, se.getMessage(),"Update Obat Gagal",JOptionPane.ERROR_MESSAGE);
            hasilUpdate = se.getMessage();
        }
    }

    public void deleteObat(String idObat) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(deleteObat);
            ps.setString(1, idObat);
            ps.executeUpdate();
            ps.close();
            //JOptionPane.showMessageDialog(null, "Data Obat Berhasil dihapus","Delete Obat",JOptionPane.INFORMATION_MESSAGE);
            hasilDelete = "ok";
        }catch(SQLException se){
            //JOptionPane.showMessageDialog(null, se.getMessage(),"Delete Obat Gagal",JOptionPane.ERROR_MESSAGE);
            hasilDelete = se.getMessage();
        }
    }

    public List getAllObat() throws SQLException {
        try{
            List list = new ArrayList();
            Statement s = (Statement) connection.createStatement();
            ResultSet rs = s.executeQuery(getAllObat);
            while(rs.next()){
                Obat ob = new Obat();
                ob.setIdObat(rs.getString("id_obat"));
                ob.setKetObat(rs.getString("ket_obat"));
                list.add(ob);
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

    public List getAllObatById(String idObat) throws SQLException {
        try {
            PreparedStatement ps = null;
            List list = new ArrayList();
            ps = (PreparedStatement) connection.prepareStatement(getAllByIdObat);
            ps.setString(1, "%" + idObat + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Obat o = new Obat();
                o.setIdObat(rs.getString("id_obat"));
                o.setKetObat(rs.getString("ket_obat"));
                list.add(o);
            }
            ps.close();
            rs.close();
            hasilGetAllObatById = "ok";
            return list;
        } catch (Throwable t) {
            //JOptionPane.showMessageDialog(null, t.getMessage(),"Get All Obat By ID Gagal!", JOptionPane.ERROR_MESSAGE);
            hasilGetAllObatById = t.getMessage();
            return null;
        }
    }
    
}
