package model;

public enum DisciplinaEnum {

    IA("Inteligência Articial"), COMPUTACAO_GRAFICA("Computação Gráfica"),
    MINERACAO_DADOS("Mineração de Dados"), BANCOS_DADOS("Banco de dados"),
    ALGORITMOS("Algoritmos");

    private String disciplina;

    private DisciplinaEnum(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }
}
