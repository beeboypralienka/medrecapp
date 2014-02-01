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
public class TabelModelRekmedAll extends AbstractTableModel {

    public List<RekamMedis> list = new ArrayList<RekamMedis>();

    public void setData(List<RekamMedis> listRekamMedis) {
        this.list = listRekamMedis;
        fireTableDataChanged();
    }

    public RekamMedis getRekamMedis(int i) {
        return list.get(i);
    }

//    @Override
//    public String getColumnName(int column) {
//        switch(column){
//            case 0:
//                return "No. Pendaftaran";
//            case 1:
//                return "Tanggal Daftar";
//            case 2:
//                return "Poli Tujuan";
//            case 3:
//                return "Dokter";
//            case 4:
//                return "Jaminan";
//            case 5:
//                return "Status";
//            case 6:
//                return "Staf Pendaftar";
//        }
//        return null;
//    }
    public int getRowCount() {
        return list.size();
    }

    public int getColumnCount() {
        return 19;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {

            /*
            0. private String noDaftar;
            1. private String noRm;
            2. private String noStaf;
            3. private String bagianSpesialis;
            4. private String idJaminan;
            5. private String noDokter;
            6. private String status;
            7. private String tglDaftar;

            8. private String noPerawat;
            9. private float beratBdn;
            10. private float tinggiBdn;
            11. private String tensiDarah;
            12. private int nadi;
            13. private int temperatur;
            14. private int pernapasan;
            15. private String kesadaran;

            16. private String anamnesa;
            17. private String diagnosis;
            18. private String terapi;

             */
            case 0:
                return list.get(rowIndex).getNoDaftar();
            case 1:
                return list.get(rowIndex).getNoRm();
            case 2:
                return list.get(rowIndex).getNoStaf();
            case 3:
                return list.get(rowIndex).getBagianSpesialis();
            case 4:
                return list.get(rowIndex).getIdJaminan();
            case 5:
                return list.get(rowIndex).getNoDokter();
            case 6:
                return list.get(rowIndex).getStatus();
            case 7:
                return list.get(rowIndex).getTglDaftar();


            case 8:
                return list.get(rowIndex).getNoPerawat();
            case 9:
                return list.get(rowIndex).getBeratBdn();
            case 10:
                return list.get(rowIndex).getTinggiBdn();
            case 11:
                return list.get(rowIndex).getTensiDarah();
            case 12:
                return list.get(rowIndex).getNadi();
            case 13:
                return list.get(rowIndex).getTemperatur();
            case 14:
                return list.get(rowIndex).getPernapasan();
            case 15:
                return list.get(rowIndex).getKesadaran();

                
            case 16:
                return list.get(rowIndex).getAnamnesa();
            case 17:
                return list.get(rowIndex).getDiagnosis();
            case 18:
                return list.get(rowIndex).getTerapi();
            default:
                return null;
        }
    }
}
