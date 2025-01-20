package models;

import exceptions.MovimentoInvalidoException;

public class Robo {
    private int eixoX;
    private int eixoY;
    private int posicaoAnteriorX;
    private int posicaoAnteriorY;
    private String cor;
    private boolean ativo;

    // inicializa o robo com a cor e posição
    public Robo(String cor) {
        this.cor = cor;
        this.eixoX = 0;
        this.eixoY = 0;
        this.posicaoAnteriorX = 0;
        this.posicaoAnteriorY = 0;
        this.ativo = true;
    }

    // gets e sets das posiçoes do eixo e da cor 
    public int getX() {
        return eixoX;
    }

    public void setX(int x) {
        this.eixoX = x;
    }

    public int getY() {
        return eixoY;
    }

    public void setY(int y) {
        this.eixoY = y;
    }

    public String getCor() {
        return cor;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void explodir() {
        this.ativo = false;
        System.out.println("O robô " + cor + " explodiu!");
    }
    

    public void voltarPosicaoAnterior() {
        this.eixoX = this.posicaoAnteriorX;
        this.eixoY = this.posicaoAnteriorY;
        System.out.println("O robô " + cor + " voltou para a posição anterior: (" + eixoX + ", " + eixoY + ")");
    }

    public void mover(String direcao) throws MovimentoInvalidoException {
        if (!ativo) {
            throw new MovimentoInvalidoException("O robô " + cor + " não pode se mover porque está inativo.");
        }

        // Salva a posição anterior
        this.posicaoAnteriorX = this.eixoX;
        this.posicaoAnteriorY = this.eixoY;

        int novoX = eixoX;
        int novoY = eixoY;

        switch (direcao) {
            case "up":
                novoY--;
                break;
            case "down":
                novoY++;
                break;
            case "left":
                novoX--;
                break;
            case "right":
                novoX++;
                break;
            default:
                throw new IllegalArgumentException("Direção inválida: " + direcao);
        }

        // Verifica se o movimento é válido 
        if (novoX < 0 || novoX >= 4 || novoY < 0 || novoY >= 4) {
            throw new MovimentoInvalidoException("Movimento inválido para a direção: " + direcao);
        }

        // Atualiza a posição 
        eixoX = novoX;
        eixoY = novoY;
    }

    public boolean encontrouAlimento(int x, int y) {
        //  verifica se o robô encontrou o alimento
        return eixoX == x && eixoY == y;
    }
}