package model;

import java.util.ArrayList;
import java.util.List;

public class Pet {
    private int id;
    private String nome;
    private String raca;
    private ArrayList<Double> historicoPeso;  // rever o tipo
    private int idade;
    private String sexo;
    private String corPelagem;
    private String estadoReprodutivo;
    private Tutor tutor;

    public Pet(int id, String nome, String raca, List<Double> stringToList, int idade, String historicoPeso, String sexo, String string4, Tutor tutor) {
        this.id = id;
        this.nome = nome;
        this.raca = raca;
        this.historicoPeso = new ArrayList<>();
        this.idade = idade;
        this.sexo = sexo;
        this.corPelagem = corPelagem;
        this.estadoReprodutivo = estadoReprodutivo; 
        this.tutor = tutor;  // Inicializa o novo atributo
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

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public ArrayList<Double> getHistoricoPeso() {
        return historicoPeso;
    }

    public void setHistoricoPeso(ArrayList<Double> historicoPeso) {
        this.historicoPeso = historicoPeso;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCorPelagem() {
        return corPelagem;
    }

    public void setCorPelagem(String corPelagem) {
        this.corPelagem = corPelagem;
    }

    public Tutor getTutor() {
        return tutor;  // Novo getter
    }

    public void setTutor(Tutor tutor) {  // Novo setter
        this.tutor = tutor;
    }
    
    
    public String getEstadoReprodutivo() {
        return estadoReprodutivo;
    }

    public void setEstadoReprodutivo(String estadoReprodutivo) {
        this.estadoReprodutivo = estadoReprodutivo;
    }
    
    
    //MÉTODOS DA CLASSE
    
    public void atualizarEstadoReprodutivo(String novoEstadoReprodutivo){
        setEstadoReprodutivo(novoEstadoReprodutivo);
        System.out.println("Novo estado reprodutivo: " + novoEstadoReprodutivo);
    }
    
    public void exibirInformações(){
        System.out.println("Id: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Raça: " + raca);
        System.out.println("Idade: " + idade);
        System.out.println("Sexo do animal: " + sexo);
        System.out.println("Cor da pelagem: " + corPelagem);
        System.out.println("Estado reprodutivo: " + estadoReprodutivo);
        System.out.println("Último peso: " + obterUltimoPeso());
        System.out.println("Tutor: " + tutor.getNome());
    }
    
    public void adicionarHistoricoPeso(double novoPeso){
        this.historicoPeso.add(novoPeso);
    }
    
    public double obterUltimoPeso(){
        if(!historicoPeso.isEmpty()){
            return historicoPeso.get(historicoPeso.size() - 1);
           
        } else{ 
            System.out.println("Nenhum peso disponível no histórico");
            return 0.0;
        }
    }
}

