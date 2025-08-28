package Server;

import Server.Actions.Actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket cliente;
    private static Actions actions = new Actions();

    public ClientHandler(Socket cliente){
        this.cliente = cliente;
    }

    @Override
    public void run() {
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
             PrintWriter saida = new PrintWriter(cliente.getOutputStream(), true)) {

            String mensagem;
            while ((mensagem = entrada.readLine()) != null) {
                if (mensagem.equalsIgnoreCase("sair")) {
                    break;
                }

                System.out.println("Mensagem recebida: " + mensagem);
                String resposta = actions.actionInput(mensagem);

                String[] linhas = resposta.split("\n");
                for (String linha : linhas) {
                    saida.println(linha);
                }

                saida.println("<<END>>");
                saida.flush();
            }

            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
