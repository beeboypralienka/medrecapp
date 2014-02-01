/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.TabelModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import medrecapp.Entity.Tindakan;

/**
 *
 * @author Hady
 */
public class TabelModelTindakan extends AbstractTableModel{
    
    public List<Tindakan> list = new ArrayList<Tindakan>();

    public void setData(List<Tindakan>listTindakan){
        this.list = listTindakan;
        fireTableDataChanged();
    }

    public Tindakan getTindakan(int i){
        return list.get(i);
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0:
                return "No Tindakan";
            case 1:
                return "Nama Tindakan";
            case 2:
                return "Tindakan Spesialis";
            case 3:
                return "Keterangan";
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
                return list.get(rowIndex).getNoTindakan();
            case 1:
                return list.get(rowIndex).getNmTindakan();
            case 2:
                return list.get(rowIndex).getTindakanSpesialis();
            case 3:
                return list.get(rowIndex).getKetTindakan();
            default:
                return null;
        }
    }
    
}
