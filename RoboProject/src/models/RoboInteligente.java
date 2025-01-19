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
                break; // Movimento válido
            } catch (MovimentoInvalidoException | IllegalArgumentException e) {
                ultimaDirecaoInvalida = direcao;
                // Escolhe uma nova direção 
                do {
                    direcao = direcoes[random.nextInt(direcoes.length)];
                } while (direcao.equals(ultimaDirecaoInvalida));
            }
        }
    }
}