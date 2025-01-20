package main;

import java.util.Random;
import java.util.Scanner;
import models.Robo;
import exceptions.MovimentoInvalidoException;

public class MainDoisRobos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        Robo robo1 = new Robo("vermelho");
        Robo robo2 = new Robo("azul");

        System.out.print("Digite a posição X do alimento: ");
        int alimentoX = scanner.nextInt();
        System.out.print("Digite a posição Y do alimento: ");
        int alimentoY = scanner.nextInt();

        int movimentosValidosRobo1 = 0;
        int movimentosInvalidosRobo1 = 0;
        int movimentosValidosRobo2 = 0;
        int movimentosInvalidosRobo2 = 0;

        while (true) {
            // Movimento do robô 1
            try {
                robo1.mover(direcaoAleatoria(random.nextInt(4)));
                movimentosValidosRobo1++;
                exibirMatriz(robo1, robo2, alimentoX, alimentoY);
                if (robo1.getX() == alimentoX && robo1.getY() == alimentoY) {
                    System.out.println("O robô 1 encontrou o alimento em " + movimentosValidosRobo1 + " movimentos válidos.");
                    break;
                }
            } catch (MovimentoInvalidoException e) {
                movimentosInvalidosRobo1++;
                System.out.println(e.getMessage());
            }

            // Movimento do robô 2
            try {
                robo2.mover(direcaoAleatoria(random.nextInt(4)));
                movimentosValidosRobo2++;
                exibirMatriz(robo1, robo2, alimentoX, alimentoY);
                if (robo2.getX() == alimentoX && robo2.getY() == alimentoY) {
                    System.out.println("O robô 2 encontrou o alimento em " + movimentosValidosRobo2 + " movimentos válidos.");
                    break;
                }
            } catch (MovimentoInvalidoException e) {
                movimentosInvalidosRobo2++;
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Movimentos válidos do robô 1: " + movimentosValidosRobo1);
        System.out.println("Movimentos inválidos do robô 1: " + movimentosInvalidosRobo1);
        System.out.println("Movimentos válidos do robô 2: " + movimentosValidosRobo2);
        System.out.println("Movimentos inválidos do robô 2: " + movimentosInvalidosRobo2);
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

    private static void exibirMatriz(Robo robo1, Robo robo2, int alimentoX, int alimentoY) {
        char[][] matriz = new char[4][4];

        // Inicializa a matriz com '.'
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