package main;

import java.util.Scanner;
import models.Robo;
import exceptions.MovimentoInvalidoException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Inicialização do robô
        Robo robo = new Robo("vermelho");

        // Input do alimento
        System.out.print("Digite a posição X do alimento: ");
        int alimentoX = scanner.nextInt();
        System.out.print("Digite a posição Y do alimento: ");
        int alimentoY = scanner.nextInt();

        // Direções possíveis
        String[] direcoes = {"up", "down", "left", "right"};

        // Repetição até o robô encontrar o alimento
        while (!robo.encontrouAlimento(alimentoX, alimentoY)) {
            System.out.print("Digite a direção para mover o robô (up, down, left, right): ");
            String direcao = scanner.next();

            try {
                robo.mover(direcao);
                System.out.println("Nova posição do robô " + robo.getCor() + ": (" + robo.getX() + ", " + robo.getY() + ")");
            } catch (MovimentoInvalidoException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            // Exibe a matriz com a posição do robô e do alimento
            exibirMatriz(robo, alimentoX, alimentoY);
        }

        System.out.println("O robô " + robo.getCor() + " encontrou o alimento na posição (" + alimentoX + ", " + alimentoY + ")!");
    }

    private static void exibirMatriz(Robo robo, int alimentoX, int alimentoY) {
        char[][] matriz = new char[4][4];

        // Sysout da matriz
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matriz[i][j] = '.';
            }
        }

        matriz[robo.getY()][robo.getX()] = 'V';
        matriz[alimentoY][alimentoX] = 'A';

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}