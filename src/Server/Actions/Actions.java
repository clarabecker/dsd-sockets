package Server.Actions;

import Model.Estudante;
import Model.Pessoa;
import Model.Professor;
import Model.Universidade;

import java.util.*;
import java.util.function.Function;

public class Actions {
    private static ActionsPessoas actionsPessoas = new ActionsPessoas();
    private static ActionsUniversidade actionsUniversidade = new ActionsUniversidade();
    private Map<String, //Operações --> CRUD
            Function<String[], String>> // É a funcao que recebe as partes[] e retorna a mensagem String
            actions = new HashMap<>();

    public Actions() {
        actions.put("INSERT", actionsPessoas::insertPessoa);        // EM LAMBDA: actions.put("INSERT", partes -> inserirPessoas(partes));
        actions.put("UPDATE", actionsPessoas::updatePessoa);
        actions.put("GET", actionsPessoas::getPessoa);
        actions.put("DELETE", actionsPessoas::deletePessoa);
        actions.put("INSERT_UNI", actionsUniversidade::insertUni);
        actions.put("UPDATE_UNI", actionsUniversidade::updateUni);
        actions.put("GET_UNI", actionsUniversidade::getUni);
        actions.put("DELETE_UNI", actionsUniversidade::deletePessoa);
        actions.put("ADD_PESSOA_UNI", actionsUniversidade::addPessoaUni);
        actions.put("REMOVE_PESSOA_UNI", actionsUniversidade::removePessoaUni);
        actions.put("GET_PESSOA_UNI", actionsUniversidade::getPessoaUni);
        actions.put("LIST_PESSOA_UNI", actionsUniversidade:: listPessoasUni);
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
