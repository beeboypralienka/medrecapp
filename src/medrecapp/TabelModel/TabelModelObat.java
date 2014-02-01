/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.TabelModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import medrecapp.Entity.Obat;

/**
 *
 * @author Hady
 */
public class TabelModelObat extends AbstractTableModel {
    
    public List<Obat> list = new ArrayList<Obat>();

    public void setData(List<Obat>listObat){
        this.list = listObat;
        fireTableDataChanged();
    }

    public Obat getObat(int i){
        return list.get(i);
    }
    
    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0:
                return "ID Obat";
            case 1:
                return "Keterangan";
        }
        return null;
    }

    public int getRowCount() {
        return list.size();
    }

    public int getColumnCount() {
        return 2;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return list.get(rowIndex).getIdObat();
            case 1:
                return list.get(rowIndex).getKetObat();
            default:
                return null;
        }
    }
    
}
