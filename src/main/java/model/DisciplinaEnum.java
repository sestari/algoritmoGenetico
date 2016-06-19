package model;

public enum DisciplinaEnum {

    IA("Inteligência Articial"), COMPUTACAO_GRAFICA("Computação Gráfica"),
    MINERACAO_DADOS("Mineração de Dados"), BANCOS_DADOS("Banco de Dados"),
    ALGORITMOS("Algoritmos"), ESTRUTURA_DE_DADOS("Estrutura de Dados"),
    GRAFOS("Grafos");

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
