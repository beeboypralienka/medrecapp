/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package medrecapp.TabelModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import medrecapp.Entity.RekamMedis;

/**
 *
 * @author Fachrul Pralienka BM
 */
public class TabelModelRekmedByNoRm extends AbstractTableModel{

    public List<RekamMedis> list = new ArrayList<RekamMedis>();

    public void setData(List<RekamMedis>listRekamMedis){
        this.list = listRekamMedis;
        fireTableDataChanged();
    }

    public RekamMedis getRekamMedis(int i){
        return list.get(i);
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0:
                return "No. Pendaftaran";            
            case 1:
                return "Tanggal Daftar";
            case 2:
                return "Poli Tujuan";
            case 3:
                return "Dokter";
            case 4:
                return "Jaminan";
            case 5:
                return "Status";
            case 6:
                return "Staf Pendaftar";
        }
        return null;
    }

    public int getRowCount() {
        return list.size();
    }

    public int getColumnCount() {
        return 7;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return list.get(rowIndex).getNoDaftar();            
            case 1:
                return list.get(rowIndex).getTglDaftar();
            case 2:
                return list.get(rowIndex).getBagianSpesialis();
            case 3:
                return list.get(rowIndex).getNoDokter();
            case 4:
                return list.get(rowIndex).getIdJaminan();
            case 5:
                return list.get(rowIndex).getStatus();
            case 6:
                return list.get(rowIndex).getNoStaf();
            default:
                return null;
        }
    }

}
