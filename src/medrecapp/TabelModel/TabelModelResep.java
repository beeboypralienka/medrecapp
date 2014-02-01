/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.TabelModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import medrecapp.Entity.Resep;

/**
 *
 * @author Fachrul Pralienka BM
 */
public class TabelModelResep extends AbstractTableModel{
    
    public List<Resep> list = new ArrayList<Resep>();

    public void setData(List<Resep>listResep){
        this.list = listResep;
        fireTableDataChanged();
    }

    public Resep getResep(int i){
        return list.get(i);
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0:
                return "Nomor Resep";
            case 1:
                return "Nomor Pendaftaran";
            case 2:
                return "Tanggal";
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
                return list.get(rowIndex).getNoResep();
            case 1:
                return list.get(rowIndex).getNoDaftar();
            case 2:
                return list.get(rowIndex).getTglResep();
            default:
                return null;
        }
    }

}
