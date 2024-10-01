package view;

import java.util.List;
import model.Pet;
import model.PetDAO;

/**
 *
 * @author Prof. Dr. Plinio Vilela - prvilela@unicamp.br
 */
public class PetTableModel extends GenericTableModel {

    public PetTableModel(List vDados){
        super(vDados, new String[]{"Idade","Sexo", "Cor pelagem", "Estado reprodutivo"});
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
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
        Pet pet = (Pet) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return pet.getIdade();
            case 1:
                return pet.getSexo();
            case 2:
                return pet.getCorPelagem();
            case 3:
                return pet.getEstadoReprodutivo();            
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }    
    
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Pet pet = (Pet) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                pet.setIdade((Integer)aValue);
                break;
            case 1:
                pet.setSexo((String)aValue);
                break;
            case 2:
                pet.setCorPelagem((String)aValue);    
                break;
            case 3:
                pet.setEstadoReprodutivo((String)aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        
        PetDAO.getInstance().update(pet);
    }    
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //if (columnIndex == 0) return false;
        return true;
    }      
    
}
