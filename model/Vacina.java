package model;

import java.sql.Date;

public class Vacina {
    private int id;
    private String medicacao;
    private Date dataVacinacao;
     private String lote;
    private Date dataReforco;

    
    public Vacina(int id, String medicacao, Date dataVacinacao, String lote, Date dataReforco){
        this.id = id;
        this.medicacao = medicacao;
        this.dataVacinacao = dataVacinacao;
        this.lote = lote;
        this.dataReforco = dataReforco;
    }
    public int getId() {
        return id;
    }

    public String getMedicacao() {
        return medicacao;
    }

    public void setMedicacao(String medicacao) {
        this.medicacao = medicacao;
    }

    public Date getDataVacinacao() {
        return dataVacinacao;
    }

    public void setDataVacinacao(Date dataVacinacao) {
        this.dataVacinacao = dataVacinacao;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Date getDataReforco() {
        return dataReforco;
    }

    public void setDataReforco(Date dataReforco) {
        this.dataReforco = dataReforco;
    }
   
    
//MÉTODOS DA CLASSE
    

    
}
