package model;

public class Casal {

    private Individuo pai;
    private Individuo mae;

    public Casal(Individuo pai, Individuo mae) {
        this.pai = pai;
        this.mae = mae;
    }

    public Individuo getPai() {
        return pai;
    }

    public void setPai(Individuo pai) {
        this.pai = pai;
    }

    public Individuo getMae() {
        return mae;
    }

    public void setMae(Individuo mae) {
        this.mae = mae;
    }

}
