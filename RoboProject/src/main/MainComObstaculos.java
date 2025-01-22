package main;

import java.util.Random;
import java.util.Scanner;
import models.Robo;
import models.RoboInteligente;
import exceptions.MovimentoInvalidoException;

public class MainComObstaculos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        Robo roboNormal = new Robo("vermelho");
        RoboInteligente roboInteligente = new RoboInteligente("azul");
        int movimentosRoboNormal = 0;
        int movimentosRoboInteligente = 0;

        System.out.print("Digite a posição X do alimento: ");
        int alimentoX = scanner.nextInt();
        System.out.print("Digite a posição Y do alimento: ");
        int alimentoY = scanner.nextInt();


        char[][] tabuleiro = new char[4][4];

        // Inicializa o tabuleiro com '.'
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tabuleiro[i][j] = '.';
            }
        }

        // Coloca o alimento no tabuleiro
        tabuleiro[alimentoY][alimentoX] = 'A';

        System.out.println("Digite o número de bombas: ");
        int numBombas = scanner.nextInt();
        for (int i = 0; i < numBombas; i++) {
            System.out.println("Digite a posição da bomba " + (i + 1) + " (x y): ");
            int bombaX = scanner.nextInt();
            int bombaY = scanner.nextInt();
            tabuleiro[bombaY][bombaX] = 'B';
        }

        System.out.println("Digite o número de rochas: ");
        int numRochas = scanner.nextInt();
        for (int i = 0; i < numRochas; i++) {
            System.out.println("Digite a posição da rocha " + (i + 1) + " (x y): ");
            int rochaX = scanner.nextInt();
            int rochaY = scanner.nextInt();
            tabuleiro[rochaY][rochaX] = 'R';
        }

        while (true) {
            // Movimento do robô normal
            try {
                roboNormal.mover(direcaoAleatoria(random.nextInt(4)));
                movimentosRoboNormal++;
                exibirMatriz(roboNormal, roboInteligente, alimentoX, alimentoY, tabuleiro);
                // Retarda a execução para visualizar os movimentos
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
                if (roboNormal.getX() == alimentoX && roboNormal.getY() == alimentoY) {
                    System.out.println("O robô normal encontrou o alimento em " + movimentosRoboNormal + " movimentos.");
                    break;
                }
                if (tabuleiro[roboNormal.getY()][roboNormal.getX()] == 'B') {
                    roboNormal.explodir();
                }
            } catch (MovimentoInvalidoException e) {
                System.out.println(e.getMessage());
            }

            // Movimento do robô inteligente
            try {
                roboInteligente.mover(direcaoAleatoria(random.nextInt(4)));
                movimentosRoboInteligente++;
                exibirMatriz(roboNormal, roboInteligente, alimentoX, alimentoY, tabuleiro);
                if (roboInteligente.getX() == alimentoX && roboInteligente.getY() == alimentoY) {
                    System.out.println("O robô inteligente encontrou o alimento em " + movimentosRoboInteligente + " movimentos.");
                    break;
                }
                if (tabuleiro[roboInteligente.getY()][roboInteligente.getX()] == 'B') {
                    roboInteligente.explodir();
                }
            } catch (MovimentoInvalidoException e) {
                System.out.println(e.getMessage());
            }

            if (!roboNormal.isAtivo() && !roboInteligente.isAtivo()) {
                System.out.println("Ambos os robôs explodiram. \n");
                break;
            }      
        }

        System.out.println("Movimentos do robô normal: " + movimentosRoboNormal);
        System.out.println("Movimentos do robô inteligente: " + movimentosRoboInteligente);
    }

    private static String direcaoAleatoria(int direcao) {
        switch (direcao) {
            case 0: return "up";
            case 1: return "down";
            case 2: return "left";
            case 3: return "right";
            default: return "";
        }
    }

    private static void exibirMatriz(Robo roboNormal, Robo roboInteligente, int alimentoX, int alimentoY, char[][] tabuleiro) {
        char[][] matriz = new char[4][4];

        // Inicializa a matriz com o estado atual do tabuleiro
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matriz[i][j] = tabuleiro[i][j];
            }
        }

        matriz[roboNormal.getY()][roboNormal.getX()] = 'V';
        matriz[roboInteligente.getY()][roboInteligente.getX()] = 'Z';

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