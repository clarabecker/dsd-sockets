package Server;

import Model.Estudante;
import Model.Pessoa;
import Model.Professor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Actions {
    private static List<Pessoa> pessoas = new ArrayList<>();
    private Map<String, //Operações --> CRUD
            Function<String[], String>> // É a funcao que recebe as partes[] e retorna a mensagem String
            actions = new HashMap<>();

    public Actions() {
        actions.put("INSERT", this::inserirPessoas);        // EM LAMBDA: actions.put("INSERT", partes -> inserirPessoas(partes));
        actions.put("UPDATE", this::atualizarPessoas);
    }

    private String atualizarPessoas(String[] strings) {
        return "Atualizando Pessoa";
    }

    private String inserirPessoas(String[] partes) {
        try{
            if(partes.length < 6){
                return "Erro: Parâmetros insuficientes para INSERT";
            }

            String tipo = partes[1].trim();
            String cpf = partes[2].trim();
            String nome = partes[3].trim();
            String endereco = partes[4].trim();

            if(tipo.equalsIgnoreCase("ESTUDANTE")){
                pessoas.add(new Estudante(cpf, nome, endereco, partes[5].trim()));
            }else if (tipo.equalsIgnoreCase("PROFESSOR")){
                pessoas.add(new Professor(cpf, nome, endereco, partes[5].trim()));
            }else{
                return "Erro: TIPO DESCONHECIDO";
            }
            return "SUCESSO! " + tipo + " " +  nome + " adicionado.";
        }catch (Exception e){
            return "Error ao inserir pessoa"+ e.getMessage();
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
