package Model;

public abstract class Pessoa {

    private String cpf;
    private String nome;
    private String endereco;
    private String tipo;

    public Pessoa(String cpf, String nome, String endereco, String tipo) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.tipo = tipo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                "tipo"+ tipo +
                '}';
    }
}
