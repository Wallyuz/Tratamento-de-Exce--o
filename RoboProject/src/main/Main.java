package main;

import models.Robo;

public class Main {
    public static void main(String[] args) {
  
        Robo robo = new Robo("vermelho");

    
        System.out.println("Posição inicial do robô: (" + robo.getX() + ", " + robo.getY() + ")");
        System.out.println("Cor: " + robo.getCor());

        
        robo.setX(2);
        robo.setY(3);

        
        System.out.println("Nova posição do robô: (" + robo.getX() + ", " + robo.getY() + ")");

       
        robo.setCor("azul");

      
        System.out.println("Nova cor: " + robo.getCor());
    }
}