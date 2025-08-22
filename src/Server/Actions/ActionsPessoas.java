package Server.Actions;

import Model.Estudante;
import Model.Pessoa;
import Model.Professor;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ActionsPessoas {
    protected static List<Pessoa> pessoas = new ArrayList<>();

    protected String deletePessoa(String[] partes) {
        try{
            if(partes.length != 2){
                throw new IllegalArgumentException("Parâmetros Incorretos para DELETE");
            }

            if(pessoas.isEmpty()){
                return "Sem pessoas cadastradas";
            }

            String cpf = partes[1].trim();
            boolean removida = pessoas.removeIf(p -> p.getCpf().equals(cpf));

            return removida
                    ? "Pessoa removida com sucesso"
                    : "Pessoa não encontrada";

        }catch (Exception e){
            return "ERRO: "+ e.getMessage();
        }
    }

    protected String getPessoa(String[] partes) {
        try {
            if(partes.length != 2){
                throw new IllegalArgumentException("Parâmetros Incorretos para GET");
            }

            if(pessoas.isEmpty()){
                return "Sem pessoas cadastradas";
            }

            for(Pessoa pessoa : pessoas){
                if(pessoa.getCpf().equals(partes[1].trim())){
                    return pessoa.getCpf()+";"+pessoa.getNome()+";"+pessoa.getEndereco();
                }
            }
            return "Pessoa não encontrada";

        }catch (Exception e){
            return "ERRO: "+ e.getMessage();

        }

    }

    protected String updatePessoa(String[] partes) {
        try{
            if(partes.length != 4){
                throw new IllegalArgumentException("Parâmetros Incorretos para UPDATE");
            }

            String cpf = partes[1].trim();
            String nome = partes[2].trim();
            String endereco = partes[3].trim();

            for(Pessoa pessoa: pessoas){
                if(pessoa.getCpf().equals(cpf)){
                    pessoa.setNome(nome);
                    pessoa.setEndereco(endereco);
                    return  "Pessoa atualizada com sucesso " + nome;
                }
            }
            throw new NoSuchElementException("Pessoa não encontrada");

        }catch (Exception e){
            return "ERRO: "+ e.getMessage();
        }
    }

    protected String insertPessoa(String[] partes) {
        try{
            if(partes.length != 6){
                throw new IllegalArgumentException("Parâmetros Incompletos para INSERT");
            }

            String tipo = partes[1].trim();
            String cpf = partes[2].trim();
            String nome = partes[3].trim();
            String endereco = partes[4].trim();

            if(cpf.length() != 11){
                throw new IllegalArgumentException("O CPF deve contér 11 digitos");
            }

            if(tipo.equalsIgnoreCase("ESTUDANTE")){
                pessoas.add(new Estudante(cpf, nome, endereco, partes[5].trim()));
            }else if (tipo.equalsIgnoreCase("PROFESSOR")){
                pessoas.add(new Professor(cpf, nome, endereco, partes[5].trim()));
            }else{
                throw new IllegalArgumentException("TIPO DESCONHECIDO");
            }
            return "SUCESSO! " + tipo + " " +  nome + " adicionado.";
        }catch (Exception e){
            return "ERRO: "+ e.getMessage();
        }
    }
}
