package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VetDAO extends DAO {
    private static VetDAO instance;

    private VetDAO() {
        getConnection();
        createVetTable();
    }

    // Singleton
    public static VetDAO getInstance() {
        return (instance == null ? (instance = new VetDAO()) : instance);
    }

    // Método para criar a tabela de veterinários (sem sobrescrever o método final)
    private void createVetTable() {
        try {
            PreparedStatement stmt = DAO.getConnection().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS vet ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT,"
                    + "nome VARCHAR(100),"
                    + "telefone VARCHAR(20),"
                    + "email VARCHAR(100),"
                    + "especialidade VARCHAR(100),"
                    + "horariosDisponiveis TEXT"
                    + ");");
            stmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(VetDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // CRUD
    public Vet create(String nome, String telefone, String email, String especialidade, String horariosDisponiveis) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO vet (nome, telefone, email, especialidade, horariosDisponiveis) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, nome);
            stmt.setString(2, telefone);
            stmt.setString(3, email);
            stmt.setString(4, especialidade);
            stmt.setString(5, horariosDisponiveis);
            executeUpdate(stmt);
            return this.retrieveById(lastId("vet", "id"));  // Retorna o último veterinário inserido
        } catch (SQLException ex) {
            Logger.getLogger(VetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; // Retornar null em caso de falha
    }

    // Método para construir o objeto Vet a partir do ResultSet
    private Vet buildObject(ResultSet rs) {
        Vet vet = null;
        try {
            vet = new Vet(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("telefone"),
                rs.getString("email"),
                rs.getString("especialidade"),
                rs.getString("horariosDisponiveis")
            );
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return vet;
    }

    // Recuperação genérica de dados
    public List<Vet> retrieve(String query) {
        List<Vet> vets = new ArrayList<>();
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

    // Recupera todos os veterinários
    public List<Vet> retrieveAll() {
        return this.retrieve("SELECT * FROM vet");
    }

    // Recupera o último veterinário inserido
    public List<Vet> retrieveLast() {
        return this.retrieve("SELECT * FROM vet WHERE id = " + lastId("vet", "id"));
    }

    // Recupera um veterinário por ID
    public Vet retrieveById(int id) {
        List<Vet> vets = this.retrieve("SELECT * FROM vet WHERE id = " + id);
        return (vets.isEmpty() ? null : vets.get(0));
    }

    // Recupera veterinários por nome similar
    public List<Vet> retrieveBySimilarName(String nome) {
        return this.retrieve("SELECT * FROM vet WHERE nome LIKE '%" + nome + "%'");
    }

    // Atualiza os dados de um veterinário
    public void update(Vet vet) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE vet SET nome=?, telefone=?, email=?, especialidade=?, horariosDisponiveis=? WHERE id=?");
            stmt.setString(1, vet.getNome());
            stmt.setString(2, vet.getTelefone());
            stmt.setString(3, vet.getEmail());
            stmt.setString(4, vet.getEspecialidade());
            stmt.setString(5, vet.getHorariosDisponiveis());
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
