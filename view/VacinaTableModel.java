package view;

import java.sql.Date;
import java.util.List;
import model.Vacina;
import model.VacinaDAO;

public class VacinaTableModel extends GenericTableModel {

    public VacinaTableModel(List vDados){
        super(vDados, new String[]{"Medicação","Data", "Lote", "Data reforço"});
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return Date.class;
            case 2:
                return String.class;
            case 3:
                return Date.class;               
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Vacina vacina = (Vacina) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return vacina.getMedicacao();
            case 1:
                return vacina.getDataVacinacao();
            case 2:
                return vacina.getLote();
            case 3:
                return vacina.getDataReforco();            
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }    
    
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Vacina vacina = (Vacina) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                vacina.setMedicacao((String)aValue);
                break;
            case 1:
                vacina.setDataVacinacao((Date)aValue);
                break;
            case 2:
                vacina.setLote((String)aValue);    
                break;
            case 3:
                vacina.setDataReforco((Date)aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        
        VacinaDAO.getInstance().update(vacina);
    }    
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //if (columnIndex == 0) return false;
        return true;
    }      
    
}
