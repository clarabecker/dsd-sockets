package Server.Actions;

import Model.Pessoa;
import Model.Universidade;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static Server.Actions.ActionsPessoas.pessoas;

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

    protected String updateUni(String[] partes) {
        try{
            if(partes.length != 5){
                throw new IllegalArgumentException("Parâmetros Incorretos para UPDATE");
            }

            Long ID = Long.valueOf(partes[1].trim());
            String nome = partes[2].trim();
            int numeroSalas = Integer.parseInt(partes[3].trim());
            int capacidadeAlunos = Integer.parseInt(partes[4].trim());

            for(Universidade u: universidades){
                if(u.getID().equals(ID)){
                    u.setNome(nome);
                    u.setNumeroSalas(numeroSalas);
                    u.setCapacidadeAlunos(capacidadeAlunos);
                    return  "Universidade atualizada com sucesso " + nome;
                }
            }
            throw new NoSuchElementException("Universidade não encontrada");

        }catch (Exception e){
            return "ERRO: "+ e.getMessage();
        }
    }

    protected String deletePessoa(String[] partes) {
        try{
            if(partes.length != 2){
                throw new IllegalArgumentException("Parâmetros Incorretos para DELETE");
            }

            if(universidades.isEmpty()){
                return "Nenhuma universidade cadastrada";
            }

            Long ID = Long.valueOf(partes[1].trim());
            boolean removida = universidades.removeIf(universidade -> universidade.getID().equals(ID));

            return removida
                    ? "Universidade removida com sucesso"
                    : "Universidade não encontrada";

        }catch (Exception e){
            return "ERRO: "+ e.getMessage();
        }
    }

    protected String addPessoaUni(String[] partes) {
        try {
            if (partes.length != 3) {
                throw new IllegalArgumentException("Parâmetros Incorretos para ADD_PEOPLE_UNI");
            }

            Long ID = Long.valueOf(partes[1].trim());
            String cpf = partes[2].trim();

            Pessoa pessoaEncontrada = null;
            for(Pessoa p: pessoas){
                if(p.getCpf().equals(cpf)){
                   pessoaEncontrada = p;
                   break;
                }
            }

            if (pessoaEncontrada == null) {
                return "Pessoa não encontrada";
            }

            Universidade universidadeEncontrada = null;
            for (Universidade u : universidades) {
                if (u.getID().equals(ID)) {
                    universidadeEncontrada = u;
                    break;
                }
            }
            if (universidadeEncontrada == null) {
                return "Universidade não cadastrada";
            }

            if (universidadeEncontrada.getComunidadeAcademica().contains(pessoaEncontrada)) {
                return "Pessoa já vinculada à universidade";
            }


            universidadeEncontrada.getComunidadeAcademica().add(pessoaEncontrada);
            return "Pessoa adicionada à universidade com sucesso";
        } catch (Exception e) {
            return "ERRO: " + e.getMessage();
        }

    }
}
