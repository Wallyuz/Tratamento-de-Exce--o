package models;

public class ObstaculoExplosivo extends Obstaculo {

    public ObstaculoExplosivo(int id, int x, int y) {
        super(id, x, y);
    }

    @Override
    public void bater(Robo robo) {
        // Faz o robô explodir 
        robo.explodir();
        // Remove o obstáculo do tabuleiro
        this.setX(-1);
        this.setY(-1);
        System.out.println("A bomba explodiu e desapareceu do tabuleiro!");
    }
}