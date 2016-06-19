package model;

public enum HorariosEnum {

    SEGUNDA_1("Segunda 13/14"), SEGUNDA_2("Segunda 14/15"), TERCA_1("Terça 13/14"), TERCA_2("Terça 14/15"),
    QUARTA_1("Quarta 13/14"), QUARTA_2("Quarta 14/15"), QUINTA_1("Quinta 13/14"), QUINTA_2("Quinta 14/15"),
    SEXTA_1("Sexta 13/14"), SEXTA_2("Sexta 14/15");

    private String horario;

    private HorariosEnum(String horario) {
        this.horario = horario;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
