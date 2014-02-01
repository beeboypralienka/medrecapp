/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.TabelModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import medrecapp.Entity.Spesialis;

/**
 *
 * @author Fachrul Pralienka BM
 */
public class TabelModelSpesialis extends AbstractTableModel{
    
    public List<Spesialis> list = new ArrayList<Spesialis>();

    public void setData(List<Spesialis>listSpesialis){
        this.list = listSpesialis;
        fireTableDataChanged();
    }

    public Spesialis getSpesialis(int i){
        return list.get(i);
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0:
                return "ID Spesialis";
            case 1:
                return "Nama Spesialis";
            case 2:
                return "Tarif Konsul";
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
                return list.get(rowIndex).getIdSpesialis();
            case 1:
                return list.get(rowIndex).getNmSpesialis();
            case 2:
                return list.get(rowIndex).getTarifKonsul();
            default:
                return null;
        }
    }

}
