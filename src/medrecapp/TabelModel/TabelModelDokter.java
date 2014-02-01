/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.TabelModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import medrecapp.Entity.Dokter;

/**
 *
 * @author Fachrul Pralienka BM
 */
public class TabelModelDokter extends AbstractTableModel{
    
    public List<Dokter> list = new ArrayList<Dokter>();

    public void setData(List<Dokter>listDokter){
        this.list = listDokter;
        fireTableDataChanged();
    }

    public Dokter getDokter(int i){
        return list.get(i);
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0:
                return "No Dokter";
            case 1:
                return "Nama Dokter";
            case 2:
                return "Spesialis";
            case 3:
                return "Tanggal Kerja";
            case 4:
                return "Alamat";
        }
        return null;
    }

    public int getRowCount() {
        return list.size();
    }

    public int getColumnCount() {
        return 5;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return list.get(rowIndex).getNoDokter();
            case 1:
                return list.get(rowIndex).getNmDokter();
            case 2:
                return list.get(rowIndex).getIdSpesialis();
            case 3:
                return list.get(rowIndex).getTglKerjaDok();
            case 4:
                return list.get(rowIndex).getAlamatDok();
            default:
                return null;
        }
    }

}