/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.Dao;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import medrecapp.Entity.ResepDt;
import medrecapp.Interfaces.ResepDtInterface;

/**
 *
 * @author Hady
 */
public class ResepDtDao implements ResepDtInterface{
    private Connection connection;

    private final String insertResepDt = "INSERT INTO resep_dt VALUES(?,?,?,?,?)";
    private final String updateResepDt =
            "UPDATE resep_dt SET id_obat=?, satuan_kons=?, dosis_kons=?, jumlah=? WHERE no_resep=? AND id_obat=?";
    private final String deleteResepDt = "DELETE FROM resep_dt WHERE no_resep=?";
    private final String getAllResepDt = "SELECT * FROM resep_dt";
    private final String getAllResepDtByNoResep = "SELECT * FROM resep_dt WHERE no_resep =?";

    public static String hasilInsert;
    public static String hasilUpdate;
    public static String hasilDelete;
    public static String hasilGetAll;
    public static String hasilGetAllResepDtByNoResep;


    public ResepDtDao(Connection connection) {
        this.connection = connection;
    }

    public void insertResepDt(ResepDt rdt) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(insertResepDt);
            ps.setString(1, rdt.getNoResep());
            ps.setString(2, rdt.getIdObat());
            ps.setString(3, rdt.getSatuanKons());
            ps.setString(4, rdt.getDosisKons());
            ps.setInt(5, rdt.getJumlah());
            ps.executeUpdate();
            ps.close();
            //JOptionPane.showMessageDialog(null, "Data resep detail berhasil ditambah!", "Insert Resep Detail", JOptionPane.INFORMATION_MESSAGE);
            hasilInsert = "ok";
        }catch(SQLException se){
            //JOptionPane.showMessageDialog(null, se.getMessage(),"Insert Resep Detail Gagal!",JOptionPane.ERROR_MESSAGE);
            hasilInsert = se.getMessage();
        }
    }

    public void updateResepDt(ResepDt rdt, String noResep, String idObat) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(updateResepDt);
            ps.setString(1, rdt.getIdObat());
            ps.setString(2, rdt.getSatuanKons());
            ps.setString(3, rdt.getDosisKons());
            ps.setInt(4, rdt.getJumlah());
            ps.setString(5, noResep);
            ps.setString(6, idObat);
            ps.executeUpdate();
            ps.close();
            //JOptionPane.showMessageDialog(null, "Data resep detail berhasil diubah!", "Update Resep Detail", JOptionPane.INFORMATION_MESSAGE);
            hasilUpdate = "ok";
        }catch(SQLException se){
            //JOptionPane.showMessageDialog(null, se.getMessage(),"Update Resep Detail Gagal!",JOptionPane.ERROR_MESSAGE);
            hasilUpdate = se.getMessage();
        }
    }

    public void deleteResepDt(String noResep) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(deleteResepDt);
            ps.setString(1, noResep);
            ps.executeUpdate();
            ps.close();
            //JOptionPane.showMessageDialog(null, "Data resep detail berhasil dihapus!","Delete Resep Detail",JOptionPane.INFORMATION_MESSAGE);
            hasilDelete = "ok";
        }catch(SQLException se){
            //JOptionPane.showMessageDialog(null, se.getMessage(),"Delete Resep Detail Gagal!",JOptionPane.ERROR_MESSAGE);
            hasilDelete = se.getMessage();
        }
    }

    public List getAllResepDt() throws SQLException {
        try{
            List list = new ArrayList();
            Statement s = (Statement) connection.createStatement();
            ResultSet rs = s.executeQuery(getAllResepDt);
            while(rs.next()){
                ResepDt rdt = new ResepDt();
                rdt.setNoResep(rs.getString("no_resep"));
                rdt.setIdObat(rs.getString("id_obat"));
                rdt.setSatuanKons(rs.getString("satuan_kons"));
                rdt.setDosisKons(rs.getString("dosis_kons"));
                rdt.setJumlah(rs.getInt("jumlah"));
                list.add(rdt);
            }
            rs.close();
            s.close();
            hasilGetAll = "ok";
            return list;
        }catch(SQLException se){
            //JOptionPane.showMessageDialog(null, se.getMessage(),"Get All Resep Detail Gagal!", JOptionPane.ERROR_MESSAGE);
            hasilGetAll = se.getMessage();
            return null;
        }
    }

    public List getAllResepDtByNoResep(String noResep) throws SQLException {
        try {
            PreparedStatement ps = null;
            List list = new ArrayList();
            ps = (PreparedStatement) connection.prepareStatement(getAllResepDtByNoResep);
            ps.setString(1, noResep);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ResepDt rd = new ResepDt();
                rd.setNoResep(rs.getString("no_resep"));
                rd.setIdObat(rs.getString("id_obat"));
                rd.setSatuanKons(rs.getString("satuan_kons"));
                rd.setDosisKons(rs.getString("dosis_kons"));
                rd.setJumlah(rs.getInt("jumlah"));
                list.add(rd);
            }
            ps.close();
            rs.close();
            hasilGetAllResepDtByNoResep = "ok";
            return list;
        } catch (Throwable t) {
            //JOptionPane.showMessageDialog(null, t.getMessage(),"Get All Dokter By Nomor Gagal!", JOptionPane.ERROR_MESSAGE);
            hasilGetAllResepDtByNoResep = t.getMessage();
            return null;
        }
    }
}
