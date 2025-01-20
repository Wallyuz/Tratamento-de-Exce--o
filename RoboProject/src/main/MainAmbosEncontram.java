package main;

import java.util.Random;
import java.util.Scanner;
import models.Robo;
import models.RoboInteligente;
import exceptions.MovimentoInvalidoException;

public class MainAmbosEncontram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Inicialização dos robôs
        Robo roboNormal = new Robo("vermelho");
        RoboInteligente roboInteligente = new RoboInteligente("azul");

        // Input do alimento
        System.out.print("Digite a posição X do alimento: ");
        int alimentoX = scanner.nextInt();
        System.out.print("Digite a posição Y do alimento: ");
        int alimentoY = scanner.nextInt();

        // Contadores de movimentos
        int movimentosRoboNormal = 0;
        int movimentosRoboInteligente = 0;

        // Direções possíveis
        String[] direcoes = {"up", "down", "left", "right"};

        // Repetição até ambos os robôs encontrarem o alimento
        while (!roboNormal.encontrouAlimento(alimentoX, alimentoY) || !roboInteligente.encontrouAlimento(alimentoX, alimentoY)) {
            if (!roboNormal.encontrouAlimento(alimentoX, alimentoY)) {
                // Movimento do robô normal
                String direcaoRoboNormal = direcoes[random.nextInt(direcoes.length)];
                try {
                    roboNormal.mover(direcaoRoboNormal);
                    movimentosRoboNormal++;
                    System.out.println("Nova posição do robô " + roboNormal.getCor() + ": (" + roboNormal.getX() + ", " + roboNormal.getY() + ")");
                } catch (MovimentoInvalidoException | IllegalArgumentException e) {
                    System.out.println("Movimento do robô " + roboNormal.getCor() + ": " + e.getMessage());
                }
            }

            if (!roboInteligente.encontrouAlimento(alimentoX, alimentoY)) {
                // Movimento do robô inteligente
                String direcaoRoboInteligente = direcoes[random.nextInt(direcoes.length)];
                try {
                    roboInteligente.mover(direcaoRoboInteligente);
                    movimentosRoboInteligente++;
                    System.out.println("Nova posição do robô Inteligente " + roboInteligente.getCor() + ": (" + roboInteligente.getX() + ", " + roboInteligente.getY() + ")");
                } catch (MovimentoInvalidoException | IllegalArgumentException e) {
                    System.out.println("Movimento inválido do robô " + roboInteligente.getCor() + ": " + e.getMessage());
                }
            }

            // Retarda a execução para visualizar os movimentos
            try {
                Thread.sleep(150); // 1000 milissegundos = 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Exibe a matriz com a posição dos robôs e do alimento
            exibirMatriz(roboNormal, roboInteligente, alimentoX, alimentoY);
        }

        // Exibe o número de movimentos de cada robô
        System.out.println("Movimentos do robô " + roboNormal.getCor() + ": " + movimentosRoboNormal);
        System.out.println("Movimentos do robô " + roboInteligente.getCor() + ": " + movimentosRoboInteligente);
    }

    private static void exibirMatriz(Robo robo1, Robo robo2, int alimentoX, int alimentoY) {
        char[][] matriz = new char[4][4];

        // Inicializa a matriz com espaços vazios
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matriz[i][j] = '.';
            }
        }

        // Coloca os robôs na matriz
        matriz[robo1.getY()][robo1.getX()] = 'V';
        matriz[robo2.getY()][robo2.getX()] = 'Z';

        // Coloca o alimento na matriz
        matriz[alimentoY][alimentoX] = 'A';

        // Exibe a matriz
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}