package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static final String HOST = "localhost";
    private static final int PORTA = 8080;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORTA);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Conectado ao servidor!");

            boolean executando = true;
            while (executando) {
                System.out.println("\n===== MENU =====");
                System.out.println("1 - Inserir Pessoa");
                System.out.println("2 - Atualizar Pessoa");
                System.out.println("3 - Consultar Pessoa");
                System.out.println("4 - Remover Pessoa");
                System.out.println("5 - Listar Pessoas");
                System.out.println("6 - Inserir Universidade");
                System.out.println("7 - Consultar Universidade");
                System.out.println("8 - Listar Pessoas em Universidade");
                System.out.println("9 - Sair");
                System.out.print("Escolha uma opção: ");

                String opcao = teclado.readLine();
                String mensagem = null;

                switch (opcao) {
                    case "1":
                        System.out.print("Digite (cpf;nome;endereco;tipo): ");
                        mensagem = "INSERT;" + teclado.readLine();
                        break;

                    case "2":
                        System.out.print("Digite (cpf;novoNome;novoEndereco): ");
                        mensagem = "UPDATE;" + teclado.readLine();
                        break;

                    case "3":
                        System.out.print("Digite CPF: ");
                        mensagem = "GET;" + teclado.readLine();
                        break;

                    case "4":
                        System.out.print("Digite CPF: ");
                        mensagem = "DELETE;" + teclado.readLine();
                        break;

                    case "5":
                        mensagem = "LIST";
                        break;

                    case "6":
                        System.out.print("Digite (nome;endereco): ");
                        mensagem = "INSERT_UNI;" + teclado.readLine();
                        break;

                    case "7":
                        System.out.print("Digite nome da universidade: ");
                        mensagem = "GET_UNI;" + teclado.readLine();
                        break;

                    case "8":
                        System.out.print("Digite nome da universidade: ");
                        mensagem = "LIST_PESSOA_UNI;" + teclado.readLine();
                        break;

                    case "9":
                        System.out.println("Encerrando conexão.");
                        executando = false;
                        mensagem = "sair";
                        break;

                    default:
                        System.out.println("Opção inválida!");
                        continue;
                }

                out.println(mensagem);
                out.flush();

                if (!executando) break;

                String linha;
                while ((linha = in.readLine()) != null) {
                    if (linha.equals("<<END>>")) break;
                    System.out.println("Servidor: " + linha);
                }
            }

        } catch (IOException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
        }
    }
}
