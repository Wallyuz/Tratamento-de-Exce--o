package main;

import models.Robo;
import exceptions.MovimentoInvalidoException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Cria o robo
        Robo robo = new Robo("vermelho");

        // Entrada do alimento pelo usuario
        System.out.print("Digite a posição X do alimento: ");
        int alimentoX = scanner.nextInt();
        System.out.print("Digite a posição Y do alimento: ");
        int alimentoY = scanner.nextInt();

        // Repetição ate o robo encontra o alimento
        while (!robo.encontrouAlimento(alimentoX, alimentoY)) {
            System.out.print("Digite a direção para mover o robô (up, down, right, left): ");
            String direcao = scanner.next();

            try {
                robo.mover(direcao);
            } catch (MovimentoInvalidoException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("O robô encontrou o alimento na posição (" + alimentoX + ", " + alimentoY + ")!");
        scanner.close();
    }
}