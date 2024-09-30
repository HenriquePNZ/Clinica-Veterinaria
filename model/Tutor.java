package model;

import java.util.List;

public class Tutor {

    private int id;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private List<Pet> pets;

    // Construtor
    public Tutor(int id, String nome, String telefone, String email, String endereco) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;  
        this.email = email;
        this.endereco = endereco;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }
     
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public void setEmail(String email){
        this.email = email;
    }

    // Métodos da classe
    public void atualizarEndereco(String novoEndereco) {
        setEndereco(novoEndereco);
        System.out.println("Novo endereço atualizado para: " + novoEndereco);
    }
    
    public void atualizarTelefone(String novoTelefone) {
        setTelefone(novoTelefone);
        System.out.println("Novo telefone atualizado para: " + novoTelefone);
    }
    
    public void adicionarPet(Pet pet) {
        pets.add(pet);
        System.out.println("Pet " + pet.getNome() + " adicionado com sucesso");
    }
    
    public void removerPet(Pet pet) {
        if (pets.remove(pet)) {
            System.out.println("Pet " + pet.getNome() + " removido com sucesso"); 
        } else {
            System.out.println("Pet não encontrado");
        }
    }
    
    public List<Pet> listarPets() {
        if (pets.isEmpty()) {
            System.out.println("Nenhum pet encontrado para o tutor " + this.nome);
        } else {
            System.out.println("Pets do tutor " + this.nome + ":");
            for (Pet pet : pets) {
                System.out.println(pet.getNome());             }
        }
        return pets; 
    }
}
