package Server;

import Model.Estudante;
import Model.Pessoa;
import Model.Professor;
import Model.Universidade;

import java.util.*;
import java.util.function.Function;

public class Actions {
    private static List<Pessoa> pessoas = new ArrayList<>();
    private static List<Universidade> universidades = new ArrayList<>();
    private Map<String, //Operações --> CRUD
            Function<String[], String>> // É a funcao que recebe as partes[] e retorna a mensagem String
            actions = new HashMap<>();

    public Actions() {
        actions.put("INSERT", this::insertPessoa);        // EM LAMBDA: actions.put("INSERT", partes -> inserirPessoas(partes));
        actions.put("UPDATE", this::updatePessoa);
        actions.put("GET", this::getPessoa);
        actions.put("DELETE", this::deletePessoa);
        actions.put("INSERT_UNI", this::insertUni);
    }

    private String insertUni(String[] partes) {
        try{
            if(partes.length != 5){
                throw new IllegalArgumentException("Parâmetros Incompletos para INSERT_UNI");
            }

            Long id = Long.valueOf(partes[1].trim());
            String nome = partes[2].trim();
            int capacidadeSalas = Integer.parseInt(partes[3].trim());
            int capacidadeAlunos = Integer.parseInt(partes[4].trim());

            for(Universidade u : universidades){
                if(u.getID().equals(id)){
                    throw new IllegalArgumentException("ID já esta cadastrado");
                }
            }

            universidades.add(new Universidade(id, nome, capacidadeSalas, capacidadeAlunos));
            return "Universidade cadastrado com sucesso";

        }catch (Exception e){
            return "ERRO: "+ e.getMessage();
        }
    }

    private String deletePessoa(String[] partes) {
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

    private String getPessoa(String[] partes) {
        try {
            if(partes.length != 2){
                throw new IllegalArgumentException("Parâmetros Incorretos para GET");
            }

            if(pessoas.isEmpty()){
                return "Sem pessoas cadastradas";
            }

            for(Pessoa pessoa : pessoas){
               if(pessoa.getCpf().equals(partes[1].trim())){
                    return pessoa.getCpf()+";"+pessoa.getNome()+";"+pessoa.getEndereco()+"\n";
                }
            }
            return "Pessoa não encontrada";

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
