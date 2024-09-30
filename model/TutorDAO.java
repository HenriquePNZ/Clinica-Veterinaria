package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TutorDAO extends DAO {
    private static TutorDAO instance;

    private TutorDAO() {
        getConnection();
        createTable();
    }

    // Singleton
    public static TutorDAO getInstance() {
        return (instance == null ? (instance = new TutorDAO()) : instance);
    }

    // CRUD    
    public Tutor create(String nome, String endereco, String telefone, String email) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement(
                "INSERT INTO tutor (nome, endereco, telefone, email) VALUES (?, ?, ?, ?)"
            );
            stmt.setString(1, nome);
            stmt.setString(2, endereco);
            stmt.setString(3, telefone);
            stmt.setString(4, email);
            executeUpdate(stmt);
            return this.retrieveById(lastId("tutor", "id"));
        } catch (SQLException ex) {
            Logger.getLogger(TutorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; 
    }

    private Tutor buildObject(ResultSet rs) {
        Tutor tutor = null;
        try {
            tutor = new Tutor(rs.getInt("id"), rs.getString("nome"), rs.getString("telefone"), rs.getString("email"), rs.getString("endereco"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return tutor;
    }

    // Generic Retriever
    public List<Tutor> retrieve(String query) {
        List<Tutor> tutores = new ArrayList<>();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                tutores.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return tutores;
    }

    // Retrieve All
    public List<Tutor> retrieveAll() {
        return this.retrieve("SELECT * FROM tutor");
    }

    // Retrieve Last
    public List<Tutor> retrieveLast() {
        return this.retrieve("SELECT * FROM tutor WHERE id = " + lastId("tutor", "id"));
    }

    // Retrieve By Id
    public Tutor retrieveById(int id) {
        List<Tutor> tutores = this.retrieve("SELECT * FROM tutor WHERE id = " + id);
        return (tutores.isEmpty() ? null : tutores.get(0));
    }

    // Retrieve By Similar Name
    public List<Tutor> retrieveBySimilarName(String nome) {
        return this.retrieve("SELECT * FROM tutor WHERE nome LIKE '%" + nome + "%'");
    }

    // Update
    public void update(Tutor tutor) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement(
                "UPDATE tutor SET nome=?, endereco=?, telefone=?, email=? WHERE id=?"
            );
            stmt.setString(1, tutor.getNome());
            stmt.setString(2, tutor.getEndereco());
            stmt.setString(3, tutor.getTelefone());
            stmt.setString(4, tutor.getEmail());
            stmt.setInt(5, tutor.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    // Delete
    public void delete(Tutor tutor) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement(
                "DELETE FROM tutor WHERE id = ?"
            );
            stmt.setInt(1, tutor.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
