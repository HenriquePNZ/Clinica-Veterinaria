package model;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DAO {
    public static final String DBURL = "jdbc:h2:./clinicaAtualizado.db";
    private static Connection con;
    protected static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    // Connect to H2 Database
    public static Connection getConnection() {
        if (con == null) {
            try {
                con = DriverManager.getConnection(DBURL);
                if (con != null) {
                    DatabaseMetaData meta = con.getMetaData();
                }
            } catch (SQLException e) {
                System.err.println("Exception: " + e.getMessage());
            }
        }
        return con;
    }

    protected ResultSet getResultSet(String query) {
        Statement s = null;
        ResultSet rs = null;
        try {
            s = con.createStatement();
            rs = s.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            // You might want to close the statement in a different context
        }
        return rs;
    }

    protected int executeUpdate(PreparedStatement queryStatement) {
        int update = 0;
        try {
            update = queryStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return update;
    }

    protected int lastId(String tableName, String primaryKey) {
        Statement s = null;
        int lastId = -1;
        try {
            s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT MAX(" + primaryKey + ") AS id FROM " + tableName);
            if (rs.next()) {
                lastId = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return lastId;
    }

    public static void terminar() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    // Create tables
    protected final boolean createTable() {
        try {
            PreparedStatement stmt;
            
            // Table tutor:
            stmt = DAO.getConnection().prepareStatement(
                "CREATE TABLE IF NOT EXISTS tutor( \n"
                + "id INTEGER PRIMARY KEY AUTO_INCREMENT, \n"
                + "nome VARCHAR(255), \n"
                + "telefone VARCHAR(20), \n"
                + "email VARCHAR(255), \n"
                + "endereco VARCHAR(255)); \n"
            );
            executeUpdate(stmt);
            System.out.println("Table 'tutor' created successfully or already exists.");

                // Table pet:
            stmt = DAO.getConnection().prepareStatement(
            "CREATE TABLE IF NOT EXISTS pet( \n"
            + "id INTEGER PRIMARY KEY AUTO_INCREMENT, \n"
            + "nome VARCHAR(255), \n"
            + "raca VARCHAR(100), \n"
            + "historicoPeso VARCHAR(255), \n"
            + "idade INTEGER, \n"
            + "sexo VARCHAR(10), \n"
            + "corPelagem VARCHAR(100), \n"
            + "estadoReprodutivo VARCHAR(100), \n"  // Corrigido: adicionei vírgula aqui
            + "especie VARCHAR(50), \n"              // Corrigido: adicionei aqui sem parênteses extras
            + "tutor_id INT, \n"
            + "FOREIGN KEY (tutor_id) REFERENCES tutor(id) \n"
            + ");"  // Corrigido: coloco o ponto e vírgula aqui
        );
        executeUpdate(stmt);
        System.out.println("Table 'pet' created successfully or already exists.");


            // Table vet:
            stmt = DAO.getConnection().prepareStatement(
                "CREATE TABLE IF NOT EXISTS vet( \n"
                + "id INTEGER PRIMARY KEY AUTO_INCREMENT, \n"
                + "nome VARCHAR(255), \n"
                + "telefone VARCHAR(20), \n"
                + "email VARCHAR(255), \n"
                + "especialidade VARCHAR(100), \n"
                + "horariosDisponiveis TIME \n"
                + "); \n"
            );

            executeUpdate(stmt);
            System.out.println("Table 'vet' created successfully or already exists.");

            // Table consulta:
            stmt = DAO.getConnection().prepareStatement(
                "CREATE TABLE IF NOT EXISTS consulta( \n"
                + "id INTEGER PRIMARY KEY AUTO_INCREMENT, \n"
                + "data DATE, \n"
                + "horario TIME, \n"
                + "categoria VARCHAR(100), \n"
                + "id_tutor INTEGER, \n"
                + "id_veterinario INTEGER, \n"
                + "id_pet INTEGER, \n"
                + "FOREIGN KEY (id_tutor) REFERENCES tutor(id), \n"
                + "FOREIGN KEY (id_veterinario) REFERENCES vet(id), \n"
                + "FOREIGN KEY (id_pet) REFERENCES pet(id)); \n"
            );
            executeUpdate(stmt);
            System.out.println("Table 'consulta' created successfully or already exists.");

            // Table vacina:
            stmt = DAO.getConnection().prepareStatement(
                "CREATE TABLE IF NOT EXISTS vacina( \n"
                + "id INTEGER PRIMARY KEY AUTO_INCREMENT, \n"
                + "medicacao VARCHAR(255), \n"
                + "dataVacinacao DATE, \n"
                + "lote VARCHAR(100), \n"
                + "dataReforco DATE); \n"
            );
            executeUpdate(stmt);
            System.out.println("Table 'vacina' created successfully or already exists.");
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, "Error creating tables", ex);
        }
        return false;
    }
}
