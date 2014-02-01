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
public class TabelModelRekmedPasienPoli extends AbstractTableModel{

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
                return "No. RM";
            case 2:
                return "Nama Pasien";
            case 3:
                return "Dokter";
            case 4:
                return "Jaminan";
            case 5:
                return "Status";
        }
        return null;
    }

    public int getRowCount() {
        return list.size();
    }

    public int getColumnCount() {
        return 6;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return list.get(rowIndex).getNoDaftar();
            case 1:
                return list.get(rowIndex).getNoRm();            
            case 2:
                return list.get(rowIndex).getNmPas();
            case 3:
                return list.get(rowIndex).getNoDokter();
            case 4:
                return list.get(rowIndex).getIdJaminan();
            case 5:
                return list.get(rowIndex).getStatus();
            default:
                return null;
        }
    }

}
