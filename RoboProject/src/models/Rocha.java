package models;

public class Rocha extends Obstaculo {

    public Rocha(int id, int x, int y) {
        super(id, x, y);
    }

    @Override
    public void bater(Robo robo) {
        // Faz o robô voltar 
        robo.voltarPosicaoAnterior();
    }
}