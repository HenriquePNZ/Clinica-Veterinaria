package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsultaDAO extends DAO {
    private static ConsultaDAO instance;

    private ConsultaDAO() {
        getConnection();
        createTable();
    }

    // Singleton
    public static ConsultaDAO getInstance() {
        return (instance == null ? (instance = new ConsultaDAO()) : instance);
    }

    // Create
    public Consulta create(Date data, Time horario, String categoria, Tutor tutor, Vet vet, Pet pet) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement(
                "INSERT INTO consulta (data, horario, categoria, tutor_id, vet_id, pet_id) VALUES (?, ?, ?, ?, ?, ?)"
            );
            stmt.setDate(1, data);
            stmt.setTime(2, horario);
            stmt.setString(3, categoria);
            stmt.setInt(4, tutor.getId());
            stmt.setInt(5, vet.getId());
            stmt.setInt(6, pet.getId());
            executeUpdate(stmt);
            return this.retrieveById(lastId("consulta", "id"));
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Build Object
    private Consulta buildObject(ResultSet rs) {
        Consulta consulta = null;
        try {
            Tutor tutor = TutorDAO.getInstance().retrieveById(rs.getInt("tutor_id"));
            Vet vet = VetDAO.getInstance().retrieveById(rs.getInt("vet_id"));
            Pet pet = PetDAO.getInstance().retrieveById(rs.getInt("pet_id"));
            
            consulta = new Consulta(
                rs.getInt("id"),
                rs.getDate("data"),
                rs.getTime("horario"),
                rs.getString("categoria"),
                tutor,
                vet,
                pet
            );
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return consulta;
    }

    // Retrieve Generic
    public List<Consulta> retrieve(String query) {
        List<Consulta> consultas = new ArrayList<>();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                consultas.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return consultas;
    }

    // RetrieveAll
    public List<Consulta> retrieveAll() {
        return this.retrieve("SELECT * FROM consulta");
    }

    // RetrieveLast
    public List<Consulta> retrieveLast() {
        return this.retrieve("SELECT * FROM consulta WHERE id = " + lastId("consulta", "id"));
    }

    // RetrieveById
    public Consulta retrieveById(int id) {
        List<Consulta> consultas = this.retrieve("SELECT * FROM consulta WHERE id = " + id);
        return (consultas.isEmpty() ? null : consultas.get(0));
    }

    // Update
    public void update(Consulta consulta) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement(
                "UPDATE consulta SET data=?, horario=?, categoria=?, tutor_id=?, vet_id=?, pet_id=? WHERE id=?"
            );
            stmt.setDate(1, consulta.getData());
            stmt.setTime(2, consulta.getHorario());
            stmt.setString(3, consulta.getCategoria());
            stmt.setInt(4, consulta.getTutor().getId());
            stmt.setInt(5, consulta.getVeterinario().getId());
            stmt.setInt(6, consulta.getPet().getId());
            stmt.setInt(7, consulta.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    // Delete
    public void delete(Consulta consulta) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("DELETE FROM consulta WHERE id = ?");
            stmt.setInt(1, consulta.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
