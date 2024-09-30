package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class VacinaDAO extends DAO {
    private static VacinaDAO instance;

    private VacinaDAO() {
        getConnection();
        createTable();
    }

    // Singleton
    public static VacinaDAO getInstance() {
        return (instance==null?(instance = new VacinaDAO()):instance);
    }

// CRUD    
    public Vacina create(String medicacao, Date dataVacinacao, String lote, Date dataReforco) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO vacina (medicacao, dataVacinacao, lote, dataReforco) VALUES (?,?,?,?)");
            stmt.setString(1, medicacao);
            stmt.setDate(2, dataVacinacao);
            stmt.setString(3, lote);
            stmt.setDate(4, dataReforco);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(VacinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("vacina","id"));
    }
    

    private Vacina buildObject(ResultSet rs) {
        Vacina vacina = null;
        try {
            vacina = new Vacina(rs.getInt("id"), rs.getString("medicacao"), rs.getDate("dataVacinacao"), rs.getString("lote"), rs.getDate("dataReforco"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return vacina;
    }

    // Generic Retriever
    public List retrieve(String query) {
        List<Vacina> vacinas = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                vacinas.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return vacinas;
    }
    
    // RetrieveAll
    public List retrieveAll() {
        return this.retrieve("SELECT * FROM vacina");
    }
    
    // RetrieveLast
    public List retrieveLast(){
        return this.retrieve("SELECT * FROM vacina WHERE id = " + lastId("vacina","id"));
    }

    // RetrieveById
    public Vacina retrieveById(int id) {
        List<Vacina> vacinas = this.retrieve("SELECT * FROM vacina WHERE id = " + id);
        return (vacinas.isEmpty()?null:vacinas.get(0));
    }

    // RetrieveBySimilarName
    public List retrieveBySimilarName(String nome) {
        return this.retrieve("SELECT * FROM vacina WHERE nome LIKE '%" + nome + "%'");
    }    
        
    // Updade
    public void update(Vacina vacina) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE vacina SET medicacao=?, dataVacincacao=?, lote=?, dataReforco=? WHERE id=?");
            stmt.setString(1, vacina.getMedicacao());
            stmt.setDate(2, vacina.getDataVacinacao());
            stmt.setString(3, vacina.getLote());
            stmt.setDate(4, vacina.getDataReforco());
            stmt.setInt(5, vacina.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    
    // Delete   
    public void delete(Vacina vacina) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM vacina WHERE id = ?");
            stmt.setInt(1, vacina.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

}
