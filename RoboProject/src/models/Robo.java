package models;

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
}