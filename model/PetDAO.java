package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PetDAO extends DAO {
    private static PetDAO instance;

    private PetDAO() {
        getConnection();
        createTable();
    }

    // Singleton
    public static PetDAO getInstance() {
        return (instance == null ? (instance = new PetDAO()) : instance);
    }

    // Create
    public Pet create(String nome, String raca, List<Double> historicoPeso, int idade, String sexo, String corPelagem, String estadoReprodutivo, Tutor tutor) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement(
                "INSERT INTO pet (nome, raca, historicoPeso, idade, sexo, corPelagem, estadoReprodutivo, tutor_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
            );
            stmt.setString(1, nome);
            stmt.setString(2, raca);
            stmt.setString(3, listToString(historicoPeso)); 
            stmt.setInt(4, idade);
            stmt.setString(5, sexo);
            stmt.setString(6, corPelagem);
            stmt.setString(7, estadoReprodutivo);  
            stmt.setInt(8, tutor.getId());
            executeUpdate(stmt);
            return this.retrieveById(lastId("pet", "id"));
        } catch (SQLException ex) {
            Logger.getLogger(PetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Build Object
    private Pet buildObject(ResultSet rs) {
        Pet pet = null;
        try {
            pet = new Pet(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("raca"),
                stringToList(rs.getString("historicoPeso")),
                rs.getInt("idade"),
                rs.getString("sexo"),
                rs.getString("corPelagem"),
                rs.getString("estadoReprodutivo"), 
                TutorDAO.getInstance().retrieveById(rs.getInt("tutor_id"))
            );
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return pet;
    }

    // Retrieve Generic
    public List<Pet> retrieve(String query) {
        List<Pet> pets = new ArrayList<>();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                pets.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return pets;
    }

    // RetrieveAll
    public List<Pet> retrieveAll() {
        return this.retrieve("SELECT * FROM pet");
    }

    // RetrieveLast
    public List<Pet> retrieveLast() {
        return this.retrieve("SELECT * FROM pet WHERE id = " + lastId("pet", "id"));
    }

    // RetrieveById
    public Pet retrieveById(int id) {
        List<Pet> pets = this.retrieve("SELECT * FROM pet WHERE id = " + id);
        return (pets.isEmpty() ? null : pets.get(0));
    }

    // RetrieveBySimilarName
    public List<Pet> retrieveBySimilarName(String nome) {
        return this.retrieve("SELECT * FROM pet WHERE nome LIKE '%" + nome + "%'");
    }

    // Update
    public void update(Pet pet) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement(
                "UPDATE pet SET nome=?, raca=?, historicoPeso=?, idade=?, sexo=?, corPelagem=?, estadoReprodutivo=?, tutor_id=? WHERE id=?"
            );
            stmt.setString(1, pet.getNome());
            stmt.setString(2, pet.getRaca());
            stmt.setString(3, listToString(pet.getHistoricoPeso())); 
            stmt.setInt(4, pet.getIdade());
            stmt.setString(5, pet.getSexo());
            stmt.setString(6, pet.getCorPelagem());
            stmt.setString(7, pet.getEstadoReprodutivo());
            stmt.setInt(8, pet.getTutor().getId());
            stmt.setInt(9, pet.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    // Delete
    public void delete(Pet pet) {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement("DELETE FROM pet WHERE id = ?");
            stmt.setInt(1, pet.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    // Utilitário para converter a lista de Double para String (serialização simples)
    private String listToString(List<Double> list) {
        if (list == null || list.isEmpty()) {
            return ""; 
        }
        StringBuilder sb = new StringBuilder();
        for (Double d : list) {
            sb.append(d.toString()).append(","); 
        }
        sb.deleteCharAt(sb.length() - 1); 
        return sb.toString();
    }

    // Utilitário para converter a String de volta para uma lista de Double (desserialização simples)
    private List<Double> stringToList(String str) {
        List<Double> list = new ArrayList<>();
        if (str != null && !str.isEmpty()) {
            String[] items = str.split(",");  
            for (String item : items) {
                list.add(Double.parseDouble(item)); 
            }
        }
        return list;
    }
}
