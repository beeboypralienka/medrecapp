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
public class TabelModelRekmedByNoDaftar extends AbstractTableModel{

    public List<RekamMedis> list = new ArrayList<RekamMedis>();

    public void setData(List<RekamMedis>listRekamMedis){
        this.list = listRekamMedis;
        fireTableDataChanged();
    }

    public RekamMedis getRekamMedis(int i){
        return list.get(i);
    }

//    @Override
//    public String getColumnName(int column) {
//        switch(column){
//            case 0:
//                return "No. Pendaftaran";
//            case 1:
//                return "Poli Tujuan";
//            case 2:
//                return "Dokter";
//            case 3:
//                return "Jaminan";
//            case 4:
//                return "Nama Staf";
//        }
//        return null;
//    }

    public int getRowCount() {
        return list.size();
    }

    public int getColumnCount() {
        return 11;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return list.get(rowIndex).getBeratBdn();
            case 1:
                return list.get(rowIndex).getTinggiBdn();
            case 2:
                return list.get(rowIndex).getTensiDarah();
            case 3:
                return list.get(rowIndex).getNadi();
            case 4:
                return list.get(rowIndex).getTemperatur();
            case 5:
                return list.get(rowIndex).getPernapasan();
            case 6:
                return list.get(rowIndex).getKesadaran();
            case 7:
                return list.get(rowIndex).getNoPerawat();
            case 8:
                return list.get(rowIndex).getAnamnesa();
            case 9:
                return list.get(rowIndex).getDiagnosis();
            case 10:
                return list.get(rowIndex).getTerapi();
            default:
                return null;
        }
    }

}
