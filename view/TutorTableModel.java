package view;

import java.util.List;
import model.Tutor;
import model.TutorDAO;

public class TutorTableModel extends GenericTableModel {

    public TutorTableModel(List vDados){
        super(vDados, new String[]{"Nome","Telefone", "Email", "Endereco"});
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Tutor tutor = (Tutor) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return tutor.getNome();
            case 1:
                return tutor.getTelefone();
            case 2:
                return tutor.getEmail();
            case 3:
                return tutor.getEndereco();               
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }    
    
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Tutor tutor = (Tutor) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                tutor.setNome((String)aValue);
                break;
            case 1:
                tutor.setTelefone((String)aValue);
                break;
            case 2:
                tutor.setEmail((String)aValue);    
                break;
            case 3:
                tutor.setEndereco((String)aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        
        TutorDAO.getInstance().update(tutor);
    }    
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //if (columnIndex == 0) return false;
        return true;
    }      
    
}
