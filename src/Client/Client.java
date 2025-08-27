package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private static final String HOST = "localhost"; // IP do servidor
    private static final int PORTA = 8080;

    public static void main(String[] args) {
        System.out.println("BUSCANDO CONEXÃO...");

        try (Socket socket = new Socket(HOST, PORTA);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("CONECTADO!");
            String mensagem;

            while (true) {
                System.out.print("Digite uma mensagem para o servidor (ou 'sair' para terminar): ");
                mensagem = teclado.readLine();

                if (mensagem == null || mensagem.trim().isEmpty()) {
                    continue;
                }

                out.println(mensagem);
                out.flush();

                if (mensagem.equalsIgnoreCase("sair")) {
                    System.out.println("Encerrando a conexão.");
                    break;
                }

                String linha;
                while ((linha = in.readLine()) != null) {
                    if (linha.equals("<<END>>")) {
                        break;
                    }
                    System.out.println("Servidor: " + linha);
                }
            }

        } catch (UnknownHostException e) {
            System.out.println("Host não encontrado!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}