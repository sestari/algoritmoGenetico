package model;

import java.util.List;

/**
 * Created by andresestari on 6/14/16.
 */
public class Professor {


    public Professor(String nome){
        setNome(nome);
    }

    private String nome;
    private List<HorariosEnum> horarios;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<HorariosEnum> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<HorariosEnum> horarios) {
        this.horarios = horarios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Professor professor = (Professor) o;

        if (nome != null ? !nome.equals(professor.nome) : professor.nome != null) return false;
        return horarios != null ? horarios.equals(professor.horarios) : professor.horarios == null;

    }

    @Override
    public int hashCode() {
        int result = nome != null ? nome.hashCode() : 0;
        result = 31 * result + (horarios != null ? horarios.hashCode() : 0);
        return result;
    }
}
