package Server;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Actions {
    private Map<String, //Operações --> CRUD
            Function<String[], String>> // É a funcao que recebe as partes[] e retorna a mensagem String
            actions = new HashMap<>();

    public Actions() {
        actions.put("INSERT", this::inserirPessoas);        // EM LAMBDA: actions.put("INSERT", partes -> inserirPessoas(partes));

    }

    public String inserirPessoas(String[] partes) {
        return "Inserindo Pessoa";
    }

    public String actionInput(String message) {
        String[] partes = message.split(";");
        String operacao = partes[0];

        // Funcao que recebe a operacao e retorna o resultado
        Function<String[], String> acao = actions.get(operacao.toUpperCase());
        return (acao != null) ?
                acao.apply(partes)  //Chama o metodo/acao que capturada o UpperCase
                : "Operação inválida";
    }
}
