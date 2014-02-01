/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.TabelModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import medrecapp.Entity.Pasien;

/**
 *
 * @author Fachrul Pralienka BM
 */
public class TabelModelPasien extends AbstractTableModel{
    
    public List<Pasien> list = new ArrayList<Pasien>();
    
    public void setData(List<Pasien>listPasien){
        this.list = listPasien;
        fireTableDataChanged();
    }

    public Pasien getSpesialis(int i){
        return list.get(i);
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0:
                return "No RM";
            case 1:
                return "Nama Pasien";
            case 2:
                return "JK";
            case 3:
                return "Tanggal Lahir";
            case 4:
                return "Agama";
            case 5:
                return "Alamat";
        }
        return null;
    }

    public int getRowCount() {
        return list.size();
    }

    public int getColumnCount() {
        return 6;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return list.get(rowIndex).getNoRm();
            case 1:
                return list.get(rowIndex).getNmPas();
            case 2:
                return list.get(rowIndex).getJkPas();
            case 3:
                return list.get(rowIndex).getTglLahir();
            case 4:
                return list.get(rowIndex).getAgama();
            case 5:
                return list.get(rowIndex).getAlamatPas();
            default:
                return null;
        }
    }



}
