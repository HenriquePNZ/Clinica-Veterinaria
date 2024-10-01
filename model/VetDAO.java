package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class VetDAO extends DAO {
    private static VetDAO instance;

    private VetDAO() {
        getConnection();
        createTable();
    }

    // Singleton
    public static VetDAO getInstance() {
        return (instance==null?(instance = new VetDAO()):instance);
    }

// CRUD    
    public Vet create(String nome, String telefone, String email, String especialidade, Time horariosDisponiveis) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO vet (nome, telefone, email, especialidade, horariosDisponiveis) VALUES (?,?,?,?,?)");
            stmt.setString(1, nome);
            stmt.setString(2, telefone);
            stmt.setString(3, email);
            stmt.setString(4, especialidade);
            stmt.setTime(5, horariosDisponiveis);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(VetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("vet","id"));
    }
    

    private Vet buildObject(ResultSet rs) {
        Vet vet = null;
        try {
            vet = new Vet(rs.getInt("id"), rs.getString("nome"), rs.getString("telefone"), rs.getString("email"), rs.getString("especialidade"), rs.getTime("horariosDisponiveis"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return vet;
    }

    // Generic Retriever
    public List retrieve(String query) {
        List<Vet> vets = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                vets.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return vets;
    }
    
    // RetrieveAll
    public List retrieveAll() {
        return this.retrieve("SELECT * FROM vet");
    }
    
    // RetrieveLast
    public List retrieveLast(){
        return this.retrieve("SELECT * FROM vet WHERE id = " + lastId("vet","id"));
    }

    // RetrieveById
    public Vet retrieveById(int id) {
        List<Vet> vets = this.retrieve("SELECT * FROM vet WHERE id = " + id);
        return (vets.isEmpty()?null:vets.get(0));
    }

    // RetrieveBySimilarName
    public List retrieveBySimilarName(String nome) {
        return this.retrieve("SELECT * FROM vet WHERE nome LIKE '%" + nome + "%'");
    }    
        
    // Updade
    public void update(Vet vet) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE vet SET nome=?, telefone=?, email=?, especialidade=?, horariosDisponiveis=? WHERE id=?");
            stmt.setString(1, vet.getNome());
            stmt.setString(2, vet.getTelefone());
            stmt.setString(3, vet.getEmail());
            stmt.setString(4, vet.getEspecialidade());
            stmt.setTime(5, vet.getHorariosDisponiveis());
            stmt.setInt(6, vet.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    
    // Delete   
    public void delete(Vet vet) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM vet WHERE id = ?");
            stmt.setInt(1, vet.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

}
