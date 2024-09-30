package model;

import java.sql.Date;
import java.sql.Time;

public class Consulta {

    private int id;
    private Date data;
    private Time horario;
    private String categoria;
    private Tutor tutor;
    private Vet veterinario;
    private Pet pet;

    public Consulta(int id, Date data, Time horario, String categoria, Tutor tutor, Vet veterinario, Pet pet) {
        this.id = id;
        this.data = data;
        this.horario = horario;
        this.categoria = categoria;
        this.tutor = tutor;
        this.veterinario = veterinario;
        this.pet = pet;
    }

    public int getId() {
        return id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getHorario() {
        return horario;
    }

    public void setHorario(Time horario) {
        this.horario = horario;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public Vet getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Vet veterinario) {
        this.veterinario = veterinario;
    }

    public Pet getPet() {  // Novo getter
        return pet;
    }

    public void setPet(Pet pet) {  // Novo setter
        this.pet = pet;
    }
    
    //MÉTODOS DA CLASSE
    
    public void agendarConsulta(Date novaData, Time novoHorario, String novaCategoria, Vet novoVet, Tutor novoTutor, Pet novoPet ){
       this.data = novaData;
       this.horario = novoHorario;
       this.categoria = novaCategoria;
       this.veterinario = novoVet;
       this.tutor = novoTutor;
       this.pet = novoPet;
       
    }
    
    public void alterarCategoria(String novaCategoria){
        setCategoria(novaCategoria);
        
        System.out.println("Categoria alteada para: " + novaCategoria);
    }
    
  public void cancelarConsulta(int consultaId) {
        if (this.id == consultaId) { 
            this.horario = null;
            this.categoria = null;
            this.tutor = null;
            this.veterinario = null;
            this.pet = null;
            System.out.println("Consulta com ID " + consultaId + " foi cancelada com sucesso!");
        } else {
            System.out.println("Consulta com ID " + consultaId + " não encontrada.");
        }
    }

    // Método para exibir detalhes da consulta
    public void exibirDetalhesConsulta() {
        if (this.data == null || this.horario == null) {
            System.out.println("Nenhuma consulta agendada.");
        } else {
            System.out.println("Detalhes da Consulta:");
            System.out.println("ID da Consulta: " + id);
            System.out.println("Data: " + data);
            System.out.println("Horário: " + horario);
            System.out.println("Categoria: " + categoria);
            System.out.println("Veterinário: " + veterinario.getNome());
            System.out.println("Tutor: " + tutor.getNome());
            System.out.println("Pet: " + pet.getNome());
        }
    }
}
