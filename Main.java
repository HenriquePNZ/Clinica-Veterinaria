import java.util.List;
import model.DAO;
import model.Tutor;
import model.TutorDAO;

public class Main {
    public static void main(String[] args) {
        // Obtenha a instância do DAO
        TutorDAO tutorDAO = TutorDAO.getInstance();
        
        // 1. Criar um novo tutor
        System.out.println("=== Criar Tutor ===");
        Tutor novoTutor = tutorDAO.create("Maria Souza", "Av. Brasil, 100", "99999-8888", "maria.souza@email.com");
        if (novoTutor != null) {
            System.out.println("Tutor criado: " + novoTutor.getNome() + ", ID: " + novoTutor.getId());
        }

        // 2. Recuperar todos os tutores
        System.out.println("\n=== Listar Todos os Tutores ===");
        List<Tutor> tutores = tutorDAO.retrieveAll();
        if (!tutores.isEmpty()) {
            for (Tutor t : tutores) {
                System.out.println("ID: " + t.getId() + ", Nome: " + t.getNome());
            }
        } else {
            System.out.println("Nenhum tutor encontrado.");
        }

        // Fechar a conexão
        DAO.terminar();
    }
}
