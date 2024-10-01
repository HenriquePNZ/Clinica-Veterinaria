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

}
