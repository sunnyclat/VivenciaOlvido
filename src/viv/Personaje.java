package viv;

public class Personaje {

    private int vida;

    public Personaje() {

        this.vida = 100;

    }

    public int getVida() {

        return this.vida;

    }

    public void setVida(int vida) {

        this.vida = vida;

    }

    public String getEstado() {

        String vidas = "vida " + String.valueOf(this.vida);

        return vidas;
    }

}
