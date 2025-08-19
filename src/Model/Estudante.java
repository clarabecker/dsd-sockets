package Model;

public class Estudante extends Pessoa{

    private String matricula;

    public Estudante(String cpf, String nome, String endereco, String matricula) {
        super(cpf, nome, endereco);
        this.matricula = matricula;
    }

    public String getMatricula(){
        return this.matricula;
    }

    public void setMatricula(String matricula){
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return super.toString() +
                "matricula='" + matricula + '\'' +
                '}';
    }
}
