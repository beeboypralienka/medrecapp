/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.TabelModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import medrecapp.Entity.ResepDt;

/**
 *
 * @author Fachrul Pralienka BM
 */
public class TabelModelResepDt extends AbstractTableModel{
    
    public List<ResepDt> list = new ArrayList<ResepDt>();

    public void setData(List<ResepDt>listResep){
        this.list = listResep;
        fireTableDataChanged();
    }

    public ResepDt getResep(int i){
        return list.get(i);
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0:
                return "Nomor Resep";
            case 1:
                return "ID Obat";
            case 2:
                return "Satuan Konsumsi";
            case 3:
                return "Dosis Konsumsi";
            case 4:
                return "Jumlah";
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
                return list.get(rowIndex).getNoResep();
            case 1:
                return list.get(rowIndex).getIdObat();
            case 2:
                return list.get(rowIndex).getSatuanKons();
            case 3:
                return list.get(rowIndex).getDosisKons();
            case 4:
                return list.get(rowIndex).getJumlah();
            default:
                return null;
        }
    }

}
