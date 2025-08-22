package Model;

public class Professor extends Pessoa{
    private String disciplina;

    public Professor(String cpf, String nome, String endereco, String tipo, String disciplina) {
        super(cpf, nome, endereco, tipo);
        this.disciplina = disciplina;
    }

    public String getDisciplina(){
        return this.disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public String toString() {
        return super.toString() +
                "disciplina='" + disciplina + '\'' +
                '}';
    }
}
