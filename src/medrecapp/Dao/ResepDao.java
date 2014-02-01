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
import medrecapp.Entity.Resep;
import medrecapp.Interfaces.ResepInterface;

/**
 *
 * @author Hady
 */
public class ResepDao implements ResepInterface{
    private Connection connection;

    private final String insertResep = "INSERT INTO resep VALUES(?,?,?)";
    private final String updateResep =
            "UPDATE resep SET no_daftar=?, tgl_resep=? WHERE no_resep=?";
    private final String deleteResep = "DELETE FROM resep WHERE no_resep=?";
    private final String getAllResep = "SELECT * FROM resep";

    private final String getAllResepByNoResep = "SELECT * FROM resep WHERE no_resep LIKE ?";
    private final String tearDownResep = "DELETE FROM resep WHERE no_resep LIKE '%'";

    public static String hasilInsert;
    public static String hasilUpdate;
    public static String hasilDelete;
    public static String hasilGetAll;

    public static String hasilGetAllResepByNoResep;
    public static String hasilTearDownResep;

    public ResepDao(Connection connection) {
        this.connection = connection;
    }

    public void insertResep(Resep rs) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(insertResep);
            ps.setString(1, rs.getNoResep());
            ps.setString(2, rs.getNoDaftar());
            ps.setString(3, rs.getTglResep());
            ps.executeUpdate();
            ps.close();
            //JOptionPane.showMessageDialog(null, "Data Resep berhasil ditambah!", "Insert Resep", JOptionPane.INFORMATION_MESSAGE);
            hasilInsert = "ok";
        }catch(SQLException se){
            //JOptionPane.showMessageDialog(null, se.getMessage(),"Insert Resep Gagal!",JOptionPane.ERROR_MESSAGE);
            hasilInsert = se.getMessage();
        }
    }

    public void updateResep(Resep rs, String noResep) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(updateResep);
            ps.setString(1, rs.getNoDaftar());
            ps.setString(2, rs.getTglResep());
            ps.setString(3, noResep);
            ps.executeUpdate();
            ps.close();
            //JOptionPane.showMessageDialog(null, "Data resep berhasil diubah!", "Update Resep", JOptionPane.INFORMATION_MESSAGE);
            hasilUpdate = "ok";
        }catch(SQLException se){
            //JOptionPane.showMessageDialog(null, se.getMessage(),"Update Resep Gagal!",JOptionPane.ERROR_MESSAGE);
            hasilUpdate = se.getMessage();
        }
    }

    public void deleteResep(String noResep) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(deleteResep);
            ps.setString(1, noResep);
            ps.executeUpdate();
            ps.close();
            //JOptionPane.showMessageDialog(null, "Data resep berhasil dihapus!","Delete Resep",JOptionPane.INFORMATION_MESSAGE);
            hasilDelete = "ok";
        }catch(SQLException se){
            //JOptionPane.showMessageDialog(null, se.getMessage(),"Delete Resep Gagal!",JOptionPane.ERROR_MESSAGE);
            hasilDelete = se.getMessage();
        }
    }

    public List getAllResep() throws SQLException {
        try{
            List list = new ArrayList();
            Statement s = (Statement) connection.createStatement();
            ResultSet rs = s.executeQuery(getAllResep);
            while(rs.next()){
                Resep rsp = new Resep();
                rsp.setNoResep(rs.getString("no_resep"));
                rsp.setNoDaftar(rs.getString("no_daftar"));
                rsp.setTglResep(rs.getString("tgl_resep"));
                list.add(rsp);
            }
            rs.close();
            s.close();
            hasilGetAll = "ok";
            return list;
        }catch(SQLException se){
            //JOptionPane.showMessageDialog(null, se.getMessage(),"Get All Resep Gagal!", JOptionPane.ERROR_MESSAGE);
            hasilGetAll = se.getMessage();
            return null;
        }
    }

    public List getAllResepByNoResep(String noResep) throws SQLException {
        try {
            PreparedStatement ps = null;
            List list = new ArrayList();
            ps = (PreparedStatement) connection.prepareStatement(getAllResepByNoResep);
            ps.setString(1, "%"+noResep + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Resep r = new Resep();
                r.setNoResep(rs.getString("no_resep"));
                r.setNoDaftar(rs.getString("no_daftar"));
                r.setTglResep(rs.getString("tgl_resep"));
                list.add(r);
            }
            ps.close();
            rs.close();
            hasilGetAllResepByNoResep = "ok";
            return list;
        } catch (Throwable t) {
            //JOptionPane.showMessageDialog(null, t.getMessage(),"Get All Dokter By Nomor Gagal!", JOptionPane.ERROR_MESSAGE);
            hasilGetAllResepByNoResep = t.getMessage();
            return null;
        }
    }

    public void tearDownResep() throws SQLException {
        try{
            Statement s = (Statement) connection.createStatement();
            s.execute(tearDownResep);
            s.close();
            hasilTearDownResep = "ok";
        }catch(SQLException se){
            hasilTearDownResep = se.getMessage();
        }
    }
    
}
