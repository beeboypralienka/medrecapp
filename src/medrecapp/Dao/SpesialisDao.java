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
import medrecapp.Entity.Spesialis;
import medrecapp.Interfaces.SpesialisInterface;

/**
 *
 * @author Fachrul Pralienka BM
 */
public class SpesialisDao implements SpesialisInterface{
    
    private Connection connection;

    // Query standar untuk CRUD
    private final String insertSpesialis = "INSERT INTO spesialis VALUES(?,?,?)";
    private final String updateSpesialis = "UPDATE spesialis SET nm_spesialis=?, tarif_konsul=? WHERE id_spesialis=?";
    private final String deleteSpesialis = "DELETE FROM spesialis WHERE id_spesialis=?";
    private final String getAllSpesialis = "SELECT * FROM spesialis";

    // Query untuk mengambil nilai ID dari JComboBox by nama
    private final String getIDSpesialis = "SELECT * FROM spesialis WHERE nm_spesialis=?";

    // Query untuk mengambil nama spesialis dari tabel by id
    private final String getNmSpesialis = "SELECT * FROM spesialis WHERE id_spesialis=?";

    // Query untuk mencari spesialis via JTExtField
    private final String getAllByIdSpesialis = "SELECT * FROM spesialis WHERE id_spesialis LIKE ?";
    private final String getAllByNmSpesialis = "SELECT * FROM spesialis WHERE nm_spesialis LIKE ?";

    /* Variabel untuk mengirim data hasil */
    public static String hasilInsert;
    public static String hasilUpdate;
    public static String hasilDelete;
    public static String hasilGetAll;
    
    public static String hasilGetAllNmSpesialis;
    public static String hasilGetIDSpesialis;
    public static String hasilGetNmSpesialis;
    public static String hasilGetAllSpesialisById;
    public static String hasilGetAllSpesialisByNm;

    public SpesialisDao(Connection connection) {
        this.connection = connection;
    }

    public void insertSpesialis(Spesialis s) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(insertSpesialis);
            ps.setString(1, s.getIdSpesialis());
            ps.setString(2, s.getNmSpesialis());
            ps.setInt(3, s.getTarifKonsul());       
            ps.executeUpdate();
            ps.close();
            //System.out.println("Insert Spesialis - Data spesialis berhasil ditambah!");
            hasilInsert = "ok";
        }catch(SQLException se){            
            //System.out.println("Insert Spesialis Gagal - "+se.getMessage());
            hasilInsert = se.getMessage();
        }
    }

    public void updateSpesialis(Spesialis s, String idSpesialis) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(updateSpesialis);
            ps.setString(1, s.getNmSpesialis());
            ps.setInt(2, s.getTarifKonsul());
            ps.setString(3, idSpesialis);
            ps.executeUpdate();
            ps.close();
            //System.out.println("Update Spesialis - Data spesialis berhasil diubah!");
            hasilUpdate = "ok";
        }catch(SQLException se){
            //System.out.println("Update Spesialis Gagal - "+se.getMessage());
            hasilUpdate = se.getMessage();
        }
    }

    public void deleteSpesialis(String idSpesialis) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(deleteSpesialis);
            ps.setString(1, idSpesialis);
            ps.executeUpdate();
            ps.close();
            //System.out.println("Delete Spesialis - Data spesialis berhasil dihapus!");
            hasilDelete = "ok";
        }catch(SQLException se){            
            //System.out.println("Delete Spesialis Gagal - "+se.getMessage());
            hasilDelete = se.getMessage();
        }
    }

    public List getAllSpesialis() throws SQLException {
        try{
            List list = new ArrayList();
            Statement s = (Statement) connection.createStatement();
            ResultSet rs = s.executeQuery(getAllSpesialis);
            while(rs.next()){
                Spesialis sp = new Spesialis();
                sp.setIdSpesialis(rs.getString("id_spesialis"));
                sp.setNmSpesialis(rs.getString("nm_spesialis"));
                sp.setTarifKonsul(rs.getInt("tarif_konsul"));
                list.add(sp);
            }
            rs.close();
            s.close();
            //System.out.println("Get All Spesialis - Berhasil dipanggil!");
            hasilGetAll = "ok";
            return list;
        }catch(SQLException se){
            //System.out.println("Get All Spesialis Gagal - "+se.getMessage());
            hasilGetAll = se.getMessage();
            return null;
        }
    }

    public String[] getAllNmSpesialis(int row) throws SQLException {
        try{
            //String[] data = new String[row];
            String[] data = new String[row+1];
            Statement st = (Statement) connection.createStatement();
            ResultSet rs = st.executeQuery(getAllSpesialis);
            Spesialis sp = new Spesialis();
            while(rs.next()){
                sp.setNmSpesialis (rs.getString("nm_spesialis"));
                String nmSpesialis = sp.getNmSpesialis();
                //data[rs.getRow()-1] = nmSpesialis;
                data[rs.getRow()] = nmSpesialis;
            }
            st.close();
            rs.close();
            hasilGetAllNmSpesialis = "ok";
            return data;
        }catch(Throwable t){
            //JOptionPane.showMessageDialog(null, t.getMessage(), "Error - Get Nama Spesialis", JOptionPane.ERROR_MESSAGE);
            hasilGetAllNmSpesialis = t.getMessage();
            return null;
        }
    }

    public String getIDSpesialis(String nama) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(getIDSpesialis);
            ps.setString(1, nama);
            ResultSet rs = ps.executeQuery();
            Spesialis sp = new Spesialis();
            String pop = null;
            while(rs.next()){
                sp.setIdSpesialis (rs.getString("id_spesialis"));
                pop = sp.getIdSpesialis();
            }
            ps.close();
            rs.close();
            hasilGetIDSpesialis = "ok";
            return pop;
        }catch(Throwable t){            
            hasilGetIDSpesialis = t.getMessage();
            return null;
        }
    }

    public String getNmSpesialis(String id) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(getNmSpesialis);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            Spesialis sp = new Spesialis();
            String pop = null;
            while(rs.next()){
                sp.setIdSpesialis (rs.getString("nm_spesialis"));
                pop = sp.getIdSpesialis();
            }
            ps.close();
            rs.close();
            hasilGetNmSpesialis = "ok";
            return pop;
        }catch(Throwable t){
            //JOptionPane.showMessageDialog(null, t.getMessage(), "Error - Get ID Spesialis", JOptionPane.ERROR_MESSAGE);
            hasilGetNmSpesialis = t.getMessage();
            return null;
        }
    }

    public List getAllSpesialisById(String idSpesialis) throws SQLException {
        try {
            PreparedStatement ps = null;
            List list = new ArrayList();
            ps = (PreparedStatement) connection.prepareStatement(getAllByIdSpesialis);
            ps.setString(1, "%" + idSpesialis + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Spesialis s = new Spesialis();
                s.setIdSpesialis(rs.getString("id_spesialis"));
                s.setNmSpesialis(rs.getString("nm_spesialis"));
                s.setTarifKonsul(rs.getInt("tarif_konsul"));
                list.add(s);
            }
            ps.close();
            rs.close();
            hasilGetAllSpesialisById = "ok";
            return list;
        } catch (Throwable t) {
            //JOptionPane.showMessageDialog(null, t.getMessage(),"Get All Spesialis By Nomor Gagal!", JOptionPane.ERROR_MESSAGE);
            hasilGetAllSpesialisById = t.getMessage();
            return null;
        }
    }

    public List getAllSpesialisByNm(String nmSpesialis) throws SQLException {
        try {
            PreparedStatement ps = null;
            List list = new ArrayList();
            ps = (PreparedStatement) connection.prepareStatement(getAllByNmSpesialis);
            ps.setString(1, "%" + nmSpesialis + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Spesialis s = new Spesialis();
                s.setIdSpesialis(rs.getString("id_spesialis"));
                s.setNmSpesialis(rs.getString("nm_spesialis"));
                s.setTarifKonsul(rs.getInt("tarif_konsul"));
                list.add(s);
            }
            ps.close();
            rs.close();
            hasilGetAllSpesialisByNm = "ok";
            return list;
        } catch (Throwable t) {
            //JOptionPane.showMessageDialog(null, t.getMessage(),"Get All Spesialis By Nama Gagal!", JOptionPane.ERROR_MESSAGE);
            hasilGetAllSpesialisByNm = t.getMessage();
            return null;
        }
    }



}
