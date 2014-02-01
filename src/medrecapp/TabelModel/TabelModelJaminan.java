/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.TabelModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import medrecapp.Entity.Jaminan;

/**
 *
 * @author Hady
 */
public class TabelModelJaminan extends AbstractTableModel{
    
    public List<Jaminan> list = new ArrayList<Jaminan>();

    public void setData(List<Jaminan>listJaminan){
        this.list = listJaminan;
        fireTableDataChanged();
    }

    public Jaminan getJaminan(int i){
        return list.get(i);
    }
    
    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0:
                return "ID Jaminan";
            case 1:
                return "Nama Jaminan";
            case 2:
                return "Keterangan";
        }
        return null;
    }

    public int getRowCount() {
        return list.size();
    }

    public int getColumnCount() {
        return 3;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return list.get(rowIndex).getIdJaminan();
            case 1:
                return list.get(rowIndex).getNmJaminan();
            case 2:
                return list.get(rowIndex).getKetJaminan();
            default:
                return null;
        }
    }
    
}
