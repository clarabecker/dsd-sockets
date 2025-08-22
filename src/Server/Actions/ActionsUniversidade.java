package Server.Actions;

import Model.Universidade;

import java.util.ArrayList;
import java.util.List;

public class ActionsUniversidade {
    private static List<Universidade> universidades = new ArrayList<>();

    protected String getUni(String[] partes) {
        try {
            if(partes.length != 2){
                throw new IllegalArgumentException("Parâmetros Incorretos para GET");
            }

            if(universidades.isEmpty()){
                return "Sem universidades cadastradas";
            }

            Long ID = Long.valueOf(partes[1].trim());
            for(Universidade u : universidades){
                if(u.getID().equals(ID)){
                    return u.getNome()+";"+u.getNumeroSalas()+";"+u.getCapacidadeAlunos();
                }
            }
            return "Universidade não encontrada";

        }catch (Exception e){
            return "ERRO: "+ e.getMessage();

        }
    }

    protected String insertUni(String[] partes) {
        try{
            if(partes.length != 5){
                throw new IllegalArgumentException("Parâmetros Incompletos para INSERT_UNI");
            }

            Long id = Long.valueOf(partes[1].trim());
            String nome = partes[2].trim();
            int numeroSalas = Integer.parseInt(partes[3].trim());
            int capacidadeAlunos = Integer.parseInt(partes[4].trim());

            for(Universidade u : universidades){
                if(u.getID().equals(id)){
                    throw new IllegalArgumentException("ID já esta cadastrado");
                }
            }

            universidades.add(new Universidade(id, nome, numeroSalas, capacidadeAlunos));
            return "Universidade cadastrado com sucesso";

        }catch (Exception e){
            return "ERRO: "+ e.getMessage();
        }
    }


}
