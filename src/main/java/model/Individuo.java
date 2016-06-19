package model;

import java.util.Comparator;
import java.util.List;


public abstract class Individuo<GENE> implements Comparable<Individuo> {

    private Integer aptidao = 0;
    private Integer geracao;
    private List<GENE> cromossomo;

    public Individuo(Integer geracao){
        this.geracao = geracao;
    }

    public Integer getAptidao() {
        return aptidao;
    }

    public void setAptidao(Integer aptidao) {
        this.aptidao = aptidao;
    }

    public Integer getGeracao() {
        return geracao;
    }

    public void setGeracao(Integer geracao) {
        this.geracao = geracao;
    }

    public List<GENE> getCromossomo() {
        return cromossomo;
    }

    public void setCromossomo(List<GENE> cromossomo) {
        this.cromossomo = cromossomo;
    }

    public int compareTo(Individuo o) {
        //desc aptidao
        return o.getAptidao().compareTo(getAptidao());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Individuo<GENE> individuo = (Individuo<GENE>) o;

        if (!geracao.equals(individuo.geracao)) return false;
        return cromossomo.equals(individuo.cromossomo);

    }

    @Override
    public int hashCode() {
        int result = geracao.hashCode();
        result = 31 * result + aptidao.hashCode();
        result = 31 * result + cromossomo.hashCode();
        return result;
    }
}
