package Server;

import Model.Pessoa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Server {
    private static final int PORTA = 80; //MUDAR DEPOIS
    private static Actions actions = new Actions();
    private static List<Pessoa> pessoas = new ArrayList<>();

    public static void main(String[] args) {

        try (ServerSocket servidor = new ServerSocket(PORTA)) {
            System.out.println("Servidor iniciado na porta " + PORTA);

            while (true) {
                try (Socket cliente = servidor.accept();
                     BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                     PrintWriter saida = new PrintWriter(cliente.getOutputStream(), true)) {

                    System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());

                    String mensagem;
                    while ((mensagem = entrada.readLine()) != null) {
                        if (mensagem.equalsIgnoreCase("SAIR")) {
                            break;
                        }

                        System.out.println("Mensagem recebida: " + mensagem);
                        String resposta = actions.actionInput(mensagem);
                        saida.println(resposta);
                    }
                } catch (IOException e) {
                    System.err.println("Erro na comunicação com o cliente: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}