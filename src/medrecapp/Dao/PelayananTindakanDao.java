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
import medrecapp.Entity.PelayananTindakan;
import medrecapp.Interfaces.PelayananTindakanInterface;

/**
 *
 * @author Fachrul Pralienka BM
 */
public class PelayananTindakanDao implements PelayananTindakanInterface {

    private Connection connection;
    private final String insertPelayananTindakan = "INSERT INTO pelayanan_tindakan VALUES(?,?)";
    private final String updatePelayananTindakan =
            "UPDATE pelayanan_tindakan SET no_tindakan=? "
            + "WHERE no_daftar=? AND no_tindakan=?";
    private final String deletePelayananTindakan = "DELETE FROM pelayanan_tindakan WHERE no_daftar=?";
    private final String getAllPelayananTindakan = "SELECT * FROM pelayanan_tindakan";
    private final String getAllByNoDaftar = "SELECT * FROM pelayanan_tindakan WHERE no_daftar=?";

    public static String hasilInsert;
    public static String hasilUpdate;
    public static String hasilDelete;
    public static String hasilGetAll;
    public static String hasilGetAllByNoDaftar;

    public PelayananTindakanDao(Connection connection) {
        this.connection = connection;
    }

    public void insertPelayananTindakan(PelayananTindakan pt) throws SQLException {
        try {
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(insertPelayananTindakan);
            ps.setString(1, pt.getNoDaftar());
            ps.setString(2, pt.getNoTindakan());
            ps.executeUpdate();
            ps.close();
            //JOptionPane.showMessageDialog(null, "Data tindakan berhasil ditambah!", "Insert Tindakan", JOptionPane.INFORMATION_MESSAGE);
            hasilInsert = "ok";
        } catch (SQLException se) {
            //JOptionPane.showMessageDialog(null, se.getMessage(), "Insert Tindakan Gagal", JOptionPane.ERROR_MESSAGE);
            hasilInsert = se.getMessage();
        }
    }

    public void updatePelayananTindakan(String noTindakan,PelayananTindakan pt) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(updatePelayananTindakan);
            ps.setString(1, noTindakan);
            ps.setString(2, pt.getNoDaftar());
            ps.setString(3, pt.getNoTindakan());
            ps.executeUpdate();
            ps.close();
            hasilUpdate = "ok";
        }catch(SQLException se){         
            hasilUpdate = se.getMessage();
        }
    }

    public void deletePelayananTindakan(String noDaftar) throws SQLException {
        try{
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(deletePelayananTindakan);
            ps.setString(1, noDaftar);
            ps.executeUpdate();
            ps.close();
            hasilDelete = "ok";
        }catch(SQLException se){
            hasilDelete = se.getMessage();
        }
    }

    public List getAllPelayananTindakan() throws SQLException {
        try{
            List list = new ArrayList();
            Statement s = (Statement) connection.createStatement();
            ResultSet rs = s.executeQuery(getAllPelayananTindakan);
            while(rs.next()){
                PelayananTindakan pt = new PelayananTindakan();
                pt.setNoDaftar(rs.getString("no_daftar"));
                pt.setNoTindakan(rs.getString("no_tindakan"));
                list.add(pt);
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

    public List getAllByNoDaftar(String noDaftar) throws SQLException {
        try {
            PreparedStatement ps = null;
            List list = new ArrayList();
            ps = (PreparedStatement) connection.prepareStatement(getAllByNoDaftar);
            ps.setString(1, noDaftar);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PelayananTindakan pt = new PelayananTindakan();
                pt.setNoDaftar(rs.getString("no_daftar"));
                pt.setNoTindakan(rs.getString("no_tindakan"));
                list.add(pt);
            }
            ps.close();
            rs.close();
            hasilGetAllByNoDaftar = "ok";
            return list;
        } catch (Throwable t) {
            //JOptionPane.showMessageDialog(null, t.getMessage(),"Get All Dokter By Nomor Gagal!", JOptionPane.ERROR_MESSAGE);
            hasilGetAllByNoDaftar = t.getMessage();
            return null;
        }
    }
}
