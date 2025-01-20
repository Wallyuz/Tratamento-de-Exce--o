package main;

import java.util.Random;
import java.util.Scanner;
import models.Robo;
import models.RoboInteligente;
import exceptions.MovimentoInvalidoException;

public class MainDoisRobos{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Inicialização dos robôs
        RoboInteligente robo1 = new RoboInteligente("vermelho");
        RoboInteligente robo2 = new RoboInteligente("azul");

        // Input do alimento
        System.out.print("Digite a posição X do alimento: ");
        int alimentoX = scanner.nextInt();
        System.out.print("Digite a posição Y do alimento: ");
        int alimentoY = scanner.nextInt();

        // Contadores de movimentos válidos e inválidos
        int movimentosValidosRobo1 = 0;
        int movimentosInvalidosRobo1 = 0;
        int movimentosValidosRobo2 = 0;
        int movimentosInvalidosRobo2 = 0;

        // Direções possíveis
        String[] direcoes = {"up", "down", "right", "left"};

        // Variável para alternar entre os robôs
        boolean turnoRobo1 = true;

        // Repetição até um dos robôs encontrar o alimento
        while (!robo1.encontrouAlimento(alimentoX, alimentoY) && !robo2.encontrouAlimento(alimentoX, alimentoY)) {
            if (turnoRobo1) {
                // Movimento do robô 1
                String direcaoRobo1 = direcoes[random.nextInt(direcoes.length)];
                try {
                    robo1.mover(direcaoRobo1);
                    movimentosValidosRobo1++;
                    System.out.println("Nova posição do robô " + robo1.getCor() + ": (" + robo1.getX() + ", " + robo1.getY() + ")");
                } catch (MovimentoInvalidoException | IllegalArgumentException e) {
                    movimentosInvalidosRobo1++;
                }

                // Verifica se o robô 1 encontrou o alimento
                if (robo1.encontrouAlimento(alimentoX, alimentoY)) {
                    System.out.println("O robô " + robo1.getCor() + " encontrou o alimento na posição (" + alimentoX + ", " + alimentoY + ")!");
                    break;
                }
            } else {
                // Movimento do robô 2
                String direcaoRobo2 = direcoes[random.nextInt(direcoes.length)];
                try {
                    robo2.mover(direcaoRobo2);
                    movimentosValidosRobo2++;
                    System.out.println("Nova posição do robô " + robo2.getCor() + ": (" + robo2.getX() + ", " + robo2.getY() + ")");
                } catch (MovimentoInvalidoException | IllegalArgumentException e) {
                    movimentosInvalidosRobo2++;
                }

                // Verifica se o robô 2 encontrou o alimento
                if (robo2.encontrouAlimento(alimentoX, alimentoY)) {
                    System.out.println("O robô " + robo2.getCor() + " encontrou o alimento na posição (" + alimentoX + ", " + alimentoY + ")!");
                    break;
                }
            }

            // Alterna o turno entre os robôs
            turnoRobo1 = !turnoRobo1;

            // Deixa o sysout dos resultado mais lento
            try {
                Thread.sleep(150); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Exibe a matriz com a posição dos robôs e do alimento
            exibirMatriz(robo1, robo2, alimentoX, alimentoY);
        }

        // Exibe o número de movimentos válidos e inválidos de cada robô
        System.out.println("Movimentos válidos do robô " + robo1.getCor() + ": " + movimentosValidosRobo1);
        System.out.println("Movimentos inválidos do robô " + robo1.getCor() + ": " + movimentosInvalidosRobo1);
        System.out.println("Movimentos válidos do robô " + robo2.getCor() + ": " + movimentosValidosRobo2);
        System.out.println("Movimentos inválidos do robô " + robo2.getCor() + ": " + movimentosInvalidosRobo2);
    }

    private static void exibirMatriz(Robo robo1, Robo robo2, int alimentoX, int alimentoY) {
        char[][] matriz = new char[4][4];

        // Sysout a matriz com a posição dos robôs e do alimento
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matriz[i][j] = '.';
            }
        }

        matriz[robo1.getY()][robo1.getX()] = 'V';
        matriz[robo2.getY()][robo2.getX()] = 'Z';
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