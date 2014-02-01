/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.TabelModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import medrecapp.Entity.Perawat;

/**
 *
 * @author Hady
 */
public class TabelModelPerawat extends AbstractTableModel{
    
    public List<Perawat> list = new ArrayList<Perawat>();

    public void setData(List<Perawat>listPerawat){
        this.list = listPerawat;
        fireTableDataChanged();
    }

    public Perawat getPerawat(int i){
        return list.get(i);
    }
    
    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0:
                return "No. Perawat";
            case 1:
                return "Nama Perawat";
            case 2:
                return "Tanggal Kerja";
            case 3:
                return "Bagian Spesialis";
        }
        return null;
    }

    public int getRowCount() {
        return list.size();
    }

    public int getColumnCount() {
        return 4;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return list.get(rowIndex).getNoPerawat();
            case 1:
                return list.get(rowIndex).getNmPerawat();
            case 2:
                return list.get(rowIndex).getTglKerjaPer();
            case 3:
                return list.get(rowIndex).getPerSpesialis();
            default:
                return null;
        }
    }
    
}
