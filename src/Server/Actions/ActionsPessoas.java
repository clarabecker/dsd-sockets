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
            if (partes.length != 2) {
                throw new IllegalArgumentException("Parâmetros Incorretos para GET");
            }

            if (pessoas.isEmpty()) {
                return "Sem pessoas cadastradas";
            }

            for (Pessoa pessoa : pessoas) {
                if (pessoa.getCpf().equals(partes[1].trim())) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(pessoa.getCpf())
                            .append(";")
                            .append(pessoa.getNome())
                            .append(";")
                            .append(pessoa.getEndereco());

                    if (pessoa.getTipo().equalsIgnoreCase("ESTUDANTE")) {
                        Estudante est = (Estudante) pessoa;
                        sb.append(";").append(est.getMatricula());
                    } else if (pessoa.getTipo().equalsIgnoreCase("PROFESSOR")) {
                        Professor prof = (Professor) pessoa;
                        sb.append(";").append(prof.getDisciplina());
                    }

                    return sb.toString();
                }
            }
            return "Pessoa não encontrada";

        } catch (Exception e) {
            return "ERRO: " + e.getMessage();
        }
    }


    protected String updatePessoa(String[] partes) {
        try {
            if (partes.length < 4) {
                throw new IllegalArgumentException("Parâmetros Incorretos para UPDATE");
            }

            String cpf = partes[1].trim();
            String nome = partes[2].trim();
            String endereco = partes[3].trim();

            for (Pessoa pessoa : pessoas) {
                if (pessoa.getCpf().equals(cpf)) {
                    pessoa.setNome(nome);
                    pessoa.setEndereco(endereco);

                    if (partes.length == 5) {
                        if (pessoa.getTipo().equalsIgnoreCase("ESTUDANTE")) {
                            ((Estudante) pessoa).setMatricula(partes[4].trim());
                        } else if (pessoa.getTipo().equalsIgnoreCase("PROFESSOR")) {
                            ((Professor) pessoa).setDisciplina(partes[4].trim());
                        }
                    }

                    return "Pessoa atualizada com sucesso";
                }
            }

            throw new NoSuchElementException("Pessoa não encontrada");

        } catch (Exception e) {
            return "ERRO: " + e.getMessage();
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
                throw new IllegalArgumentException("O CPF deve conter 11 digitos");
            }

            if(tipo.equalsIgnoreCase("ESTUDANTE")){
                pessoas.add(new Estudante(cpf, nome, endereco, tipo, partes[5].trim()));
            } else if (tipo.equalsIgnoreCase("PROFESSOR")){
                pessoas.add(new Professor(cpf, nome, endereco, tipo, partes[5].trim()));
            } else{
                throw new IllegalArgumentException("TIPO DESCONHECIDO");
            }

            return "SUCESSO! " + tipo + " " +  nome + " adicionado.";
        }catch (Exception e){
            return "ERRO: "+ e.getMessage();
        }
    }

    // LISTAR TODAS AS PESSOAS
    protected String listPessoas(String[] partes) {
        try {
            if (partes.length != 1) {
                throw new IllegalArgumentException("Parâmetros Incorretos para LIST");
            }

            if (pessoas.isEmpty()) {
                return "0";
            }

            StringBuilder sb = new StringBuilder();

            sb.append(String.format("%02d", pessoas.size())).append("\n");

            for (Pessoa p : pessoas) {
                sb.append(p.getCpf())
                        .append(";")
                        .append(p.getNome())
                        .append(";")
                        .append(p.getEndereco());

                if (p.getTipo().equalsIgnoreCase("ESTUDANTE")) {
                    Estudante est = (Estudante) p;
                    sb.append(";").append(est.getMatricula());
                }

                else if (p.getTipo().equalsIgnoreCase("PROFESSOR")) {
                    Professor prof = (Professor) p;
                    sb.append(";").append(prof.getDisciplina());
                }

                sb.append("\n");
            }

            return sb.toString().trim();

        } catch (Exception e) {
            return "ERRO: " + e.getMessage();
        }
    }

}
