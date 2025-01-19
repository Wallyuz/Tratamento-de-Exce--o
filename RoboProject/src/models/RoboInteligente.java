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
            // Movimento válido
            try {
                super.mover(direcao);
                break;

                // Escolhe uma nova direção,caso invalido 
            } catch (MovimentoInvalidoException | IllegalArgumentException e) {
                ultimaDirecaoInvalida = direcao;
               
                do {
                    direcao = direcoes[random.nextInt(direcoes.length)];
                } while (direcao.equals(ultimaDirecaoInvalida));
            }
        }
    }
}