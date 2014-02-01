/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package medrecapp.TabelModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import medrecapp.Entity.PelayananTindakan;

/**
 *
 * @author Fachrul Pralienka BM
 */
public class TabelModelPelayananTindakan extends AbstractTableModel {

    public List<PelayananTindakan> list = new ArrayList<PelayananTindakan>();

    public void setData(List<PelayananTindakan> listPelayananTindakan) {
        this.list = listPelayananTindakan;
        fireTableDataChanged();
    }

    public PelayananTindakan getPelayananTindakan(int i) {
        return list.get(i);
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "No Tindakan";
            case 1:
                return "No Pelayanan";
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
        switch (columnIndex) {
            case 0:
                return list.get(rowIndex).getNoDaftar();
            case 1:
                return list.get(rowIndex).getNoTindakan();
            default:
                return null;
        }
    }
}
