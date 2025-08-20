package Server;

import Model.Estudante;
import Model.Pessoa;
import Model.Professor;

import java.util.*;
import java.util.function.Function;

public class Actions {
    private static List<Pessoa> pessoas = new ArrayList<>();
    private Map<String, //Operações --> CRUD
            Function<String[], String>> // É a funcao que recebe as partes[] e retorna a mensagem String
            actions = new HashMap<>();

    public Actions() {
        actions.put("INSERT", this::insertPessoa);        // EM LAMBDA: actions.put("INSERT", partes -> inserirPessoas(partes));
        actions.put("UPDATE", this::updatePessoa);
        actions.put("GET", this::getPessoa);

    }


    private String getPessoa(String[] partes) {
        try {
            if(partes.length != 2){
                throw new IllegalArgumentException("Parâmetros Incorretos para GET");
            }

            for(Pessoa pessoa : pessoas){
                if(pessoa.getCpf().isEmpty()){
                    return "Pessoa não encontrada";
                }else if(pessoa.getCpf().equals(partes[1])){
                    return pessoa.getCpf()+";"+pessoa.getNome()+";"+pessoa.getEndereco()+"\n";
                }
            }
            return "Sem pessoas cadastradas";

        }catch (Exception e){
            return "ERRO: "+ e.getMessage();

        }

    }

    private String updatePessoa(String[] partes) {
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

    private String insertPessoa(String[] partes) {
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


    protected String actionInput(String message) {
        String[] partes = message.split(";");
        String operacao = partes[0];

        // Funcao que recebe a operacao e retorna o resultado
        Function<String[], String> acao = actions.get(operacao.toUpperCase());
        return (acao != null) ?
                acao.apply(partes)  //Chama o metodo/acao que capturada o UpperCase
                : "Operação inválida";
    }
}
