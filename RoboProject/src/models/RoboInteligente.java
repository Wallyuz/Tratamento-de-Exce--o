package models;

import exceptions.MovimentoInvalidoException;
import java.util.Random;

public class RoboInteligente extends Robo {
    private Random random;

    public RoboInteligente(String cor) {
        super(cor);
        this.random = new Random();
    }
    

    @Override
    public void mover(String direcao) throws MovimentoInvalidoException {
        String[] direcoes = {"up", "down", "right", "left"};
        String ultimaDirecaoInvalida = null;

        while (true) {
            try {
                super.mover(direcao);
                break; // Movimento válido, sai do loop
            } catch (MovimentoInvalidoException | IllegalArgumentException e) {
                ultimaDirecaoInvalida = direcao;
                // Escolhe uma nova direção diferente da última
                do {
                    direcao = direcoes[random.nextInt(direcoes.length)];
                } while (direcao.equals(ultimaDirecaoInvalida));
            }
        }
    }

    public void moverComObstaculo(String direcao, Obstaculo obstaculo) throws MovimentoInvalidoException {
        mover(direcao);

        // Verifica se o robô bateu
        if (this.getX() == obstaculo.getX() && this.getY() == obstaculo.getY()) {
            obstaculo.bater(this);
            
        }
    }
}