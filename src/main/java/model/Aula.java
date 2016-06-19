package model;

public class Aula {

    private Professor professor;
    private DisciplinaEnum disciplina;
    private HorariosEnum horario;


    public Aula(int posicaoHorario){
        horario = HorariosEnum.values()[posicaoHorario];
    }

    public HorariosEnum getHorario() {
        return horario;
    }

    public void setHorario(HorariosEnum horario) {
        this.horario = horario;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public DisciplinaEnum getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(DisciplinaEnum disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Aula aula = (Aula) o;

        if (professor != null && aula.professor != null && !professor.equals(aula.professor)) return false;
        if (disciplina != aula.disciplina) return false;
        return horario == aula.horario;

    }

    @Override
    public int hashCode() {
        int result = professor.hashCode();
        result = 31 * result + disciplina.hashCode();
        result = 31 * result + horario.hashCode();
        return result;
    }
}
