package models;

import exceptions.MovimentoInvalidoException;

public class Robo {
    private int eixoX;
    private int eixoY;
    private String cor;

    // inicializa o robo com a cor e posição
    public Robo(String cor) {
        this.cor = cor;
        this.eixoX = 0;
        this.eixoY = 0;
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

    public void setCor(String cor) {
        this.cor = cor;
    }

    // Método para mover o robô com String
    public void mover(String direcao) throws MovimentoInvalidoException {
        switch (direcao.toLowerCase()) {
            case "up":
                if (eixoY + 1 < 0) throw new MovimentoInvalidoException("up");
                eixoY += 1;
                break;
            case "down":
                if (eixoY - 1 < 0) throw new MovimentoInvalidoException("down");
                eixoY -= 1;
                break;
            case "right":
                if (eixoX + 1 < 0) throw new MovimentoInvalidoException("right");
                eixoX += 1;
                break;
            case "left":
                if (eixoX - 1 < 0) throw new MovimentoInvalidoException("left");
                eixoX -= 1;
                break;
            default:
                throw new IllegalArgumentException("Direção inválida: " + direcao);
        }
        System.out.println("Nova posição do robô: (" + eixoX + ", " + eixoY + ")");
    }

    // Método sobrecarregado para mover o robô com int(verificar pq nao funciona)
    public void mover(int direcao) throws MovimentoInvalidoException {
        switch (direcao) {
            case 1:
                mover("up");
                break;
            case 2:
                mover("down");
                break;
            case 3:
                mover("right");
                break;
            case 4:
                mover("left");
                break;
            default:
                throw new IllegalArgumentException("Direção inválida: " + direcao);
        }
    }

    // Método para verificar se o robô encontrou o alimento
    public boolean encontrouAlimento(int alimentoX, int alimentoY) {
        return this.eixoX == alimentoX && this.eixoY == alimentoY;
    }
}