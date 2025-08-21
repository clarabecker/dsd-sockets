package Model;

import java.util.ArrayList;
import java.util.List;

public class Universidade {
    private Long ID;
    private String nome;
    private int numeroSalas;
    private int capacidadeAlunos;
    private List<Pessoa> comunidadeAcademica;

    public Universidade(Long ID, String nome, int numeroSalas, int capacidadeAlunos) {
        this.ID = ID;
        this.nome=nome;
        this.numeroSalas = numeroSalas;
        this.capacidadeAlunos = capacidadeAlunos;
        this.comunidadeAcademica = new ArrayList<>();
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumeroSalas(){
        return this.numeroSalas;
    }

    public void setNumeroSalas(int salas){
        if(salas > 0){
            this.numeroSalas = salas;
        }
    }

    public int getCapacidadeAlunos(){
        return this.capacidadeAlunos;
    }

    public void setCapacidadeAlunos(int capacidade){
        if(capacidadeAlunos > 0) {
            this.capacidadeAlunos = capacidade;
        }
    }

    public Long getID() {
        return ID;
    }

    public List<Pessoa> getComunidadeAcademica() {
        return comunidadeAcademica;
    }

    public void setComunidadeAcademica(List<Pessoa> comunidade) {
        this.comunidadeAcademica = comunidade;
    }

    public Universidade(Long ID, String nome, int numeroSalas, int capacidadeAlunos, List<Pessoa> comunidadeAcademica) {
        this.ID = ID;
        this.nome = nome;
        this.numeroSalas = numeroSalas;
        this.capacidadeAlunos = capacidadeAlunos;
        this.comunidadeAcademica = comunidadeAcademica;
    }
}
