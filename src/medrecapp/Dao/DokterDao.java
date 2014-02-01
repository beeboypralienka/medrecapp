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
import medrecapp.Entity.Dokter;
import medrecapp.Interfaces.DokterInterface;

/**
 *
 * @author Fachrul Pralienka BM
 */
public class DokterDao implements DokterInterface{
    
    private Connection connection;

    private final String insertDokter = "INSERT INTO dokter VALUES(?,?,?,?,?)";
    private final String updateDokter =
            "UPDATE dokter SET nm_dokter=?, id_spesialis=?, tgl_kerja_dok=?, alamat_dok=? WHERE no_dokter=?";
    private final String deleteDokter = "DELETE FROM dokter WHERE no_dokter=?";
    private final String getAllDokter = "SELECT * FROM dokter";

    private final String getAllByNoDokter = "SELECT * FROM dokter WHERE no_dokter LIKE ?";
    private final String getAllByNmDokter = "SELECT * FROM dokter WHERE nm_dokter LIKE ?";
    private final String getMaxNoDokter   = "SELECT MAX(SUBSTR(no_dokter,5,7))+1 FROM dokter";

    private final String getAllByIdSpesialis = "SELECT * FROM dokter WHERE id_spesialis = ?";
    private final String getNoDokterByNama = "SELECT no_dokter FROM dokter WHERE nm_dokter = ?";

    public static String hasilInsert;
    public static String hasilUpdate;
    public static String hasilDelete;
    public static String hasilGetAll;
    public static String hasilGetAllDokterByNo;
    public static String hasilGetAllDokterByNm;
    public static String hasilGetMaxIdDokter;
    public static String hasilGetAllDokterByIdSpesialis;
    public static String hasilGetAllNamaDokter;
    public static String hasilGetNoDokterByNama;

    public DokterDao(Connection connection) {
        this.connection = connection;
    }

    public void insertDokter(Dokter d) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(insertDokter);
            ps.setString(1, d.getNoDokter());
            ps.setString(2, d.getNmDokter());
            ps.setString(3, d.getIdSpesialis());
            ps.setString(4, d.getTglKerjaDok());
            ps.setString(5, d.getAlamatDok());
            ps.executeUpdate();
            ps.close();            
            hasilInsert = "ok";
        }catch(SQLException se){            
            hasilInsert = se.getMessage();
        }
    }

    public void updateDokter(Dokter d, String noDokter) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(updateDokter);
            ps.setString(1, d.getNmDokter());
            ps.setString(2, d.getIdSpesialis());
            ps.setString(3, d.getTglKerjaDok());
            ps.setString(4, d.getAlamatDok());
            ps.setString(5, noDokter);
            ps.executeUpdate();
            ps.close();            
            hasilUpdate = "ok";
        }catch(SQLException se){            
            hasilUpdate = se.getMessage();
        }
    }

    public void deleteDokter(String noDokter) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(deleteDokter);
            ps.setString(1, noDokter);
            ps.executeUpdate();
            ps.close();            
            hasilDelete = "ok";
        }catch(SQLException se){            
            hasilDelete = se.getMessage();
        }
    }

    public List getAllDokter() throws SQLException {
        try{
            List list = new ArrayList();
            Statement s = (Statement) connection.createStatement();
            ResultSet rs = s.executeQuery(getAllDokter);
            while(rs.next()){
                Dokter d = new Dokter();
                d.setNoDokter(rs.getString("no_dokter"));
                d.setNmDokter(rs.getString("nm_dokter"));
                d.setIdSpesialis(rs.getString("id_spesialis"));
                d.setTglKerjaDok(rs.getString("tgl_kerja_dok"));
                d.setAlamatDok(rs.getString("alamat_dok"));
                list.add(d);
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

    public List getAllDokterByNo(String noDokter) throws SQLException {
        try {
            PreparedStatement ps = null;
            List list = new ArrayList();
            ps = (PreparedStatement) connection.prepareStatement(getAllByNoDokter);
            ps.setString(1, "%"+noDokter + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Dokter d = new Dokter();
                d.setNoDokter(rs.getString("no_dokter"));
                d.setNmDokter(rs.getString("nm_dokter"));
                d.setIdSpesialis(rs.getString("id_spesialis"));
                d.setTglKerjaDok(rs.getString("tgl_kerja_dok"));
                d.setAlamatDok(rs.getString("alamat_dok"));
                list.add(d);
            }
            ps.close();
            rs.close();
            hasilGetAllDokterByNo = "ok";
            return list;
        } catch (Throwable t) {
            //JOptionPane.showMessageDialog(null, t.getMessage(),"Get All Dokter By Nomor Gagal!", JOptionPane.ERROR_MESSAGE);
            hasilGetAllDokterByNo = t.getMessage();
            return null;
        }
    }

    public List getAllDokterByNm(String nmDokter) throws SQLException {
        try {
            PreparedStatement ps = null;
            List list = new ArrayList();
            ps = (PreparedStatement) connection.prepareStatement(getAllByNmDokter);
            ps.setString(1, "%"+nmDokter + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Dokter d = new Dokter();
                d.setNoDokter(rs.getString("no_dokter"));
                d.setNmDokter(rs.getString("nm_dokter"));
                d.setIdSpesialis(rs.getString("id_spesialis"));
                d.setTglKerjaDok(rs.getString("tgl_kerja_dok"));
                d.setAlamatDok(rs.getString("alamat_dok"));
                list.add(d);
            }
            ps.close();
            rs.close();
            hasilGetAllDokterByNm = "ok";
            return list;
        } catch (Throwable t) {
            //JOptionPane.showMessageDialog(null, t.getMessage(),"Get All Dokter By Nama Gagal!", JOptionPane.ERROR_MESSAGE);
            hasilGetAllDokterByNm = t.getMessage();
            return null;
        }
    }

    public String getMaxIdDokter() throws SQLException {
        try {
            String max = null, hasil = null;
            PreparedStatement ps = null;
            ps = (PreparedStatement) connection.prepareStatement(getMaxNoDokter);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                max = rs.getString(1);
                if(max == null){
                  hasil = "001";
                } else if (max.length() == 1) {
                    hasil = "00" + max;
                } else if (max.length() == 2) {
                    hasil = "0" + max;
                } else {
                    hasil = max;
                }
            }
            hasilGetMaxIdDokter = "ok";
            return hasil;
        } catch (Throwable t) {
            //JOptionPane.showMessageDialog(null, t.getMessage(),"Get Max Nomor Dokter Gagal!", JOptionPane.ERROR_MESSAGE);
            hasilGetMaxIdDokter = t.getMessage();
            return null;
        }
    }

    public List getAllDokterByIdSpesialis(String idSpesialis) throws SQLException {
        try{
            List list = new ArrayList();
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(getAllByIdSpesialis);
            ps.setString(1, idSpesialis);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Dokter d = new Dokter();
                d.setNoDokter(rs.getString("no_dokter"));
                d.setNmDokter(rs.getString("nm_dokter"));
                list.add(d);
            }
            rs.close();
            ps.close();
            hasilGetAllDokterByIdSpesialis = "ok";
            return list;
        }catch(SQLException se){
            //JOptionPane.showMessageDialog(null, se.getMessage(),"Get All Dokter By Id Spesialis Gagal!",JOptionPane.ERROR_MESSAGE);
            hasilGetAllDokterByIdSpesialis = se.getMessage();
            return null;
        }
    }

    public String[] getAllNamaDokter(String dokter, int total) throws SQLException {
        try{
            //String[]nama = new String[total];
            String[]nama = new String[total+1];
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(getAllByIdSpesialis);
            ps.setString(1, dokter);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                //nama[rs.getRow()-1] = rs.getString("nm_dokter");
                nama[rs.getRow()] = rs.getString("nm_dokter");
            }
            rs.close();
            ps.close();
            hasilGetAllNamaDokter = "ok";
            return nama;
        }catch(SQLException se){
            //JOptionPane.showMessageDialog(null, se.getMessage(),"String[] Nama Dokter Gagal!",JOptionPane.ERROR_MESSAGE);
            hasilGetAllNamaDokter = se.getMessage();
            return null;
        }
    }

    public String getNoDokterByNama(String nama) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(getNoDokterByNama);
            ps.setString(1, nama);
            ResultSet rs = ps.executeQuery();
            Dokter d = new Dokter();
            String pop = null;
            while(rs.next()){
                d.setNoDokter(rs.getString("no_dokter"));
                pop = d.getNoDokter();
            }
            ps.close();
            rs.close();
            hasilGetNoDokterByNama = "ok";
            return pop;
        }catch(Throwable t){
            //JOptionPane.showMessageDialog(null, t.getMessage(), "Error - Get ID Spesialis", JOptionPane.ERROR_MESSAGE);
            hasilGetNoDokterByNama = t.getMessage();
            return null;
        }
    }

}
