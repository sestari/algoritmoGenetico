package model;

public enum HorariosEnum {

    SEGUNDA_1("Segunda 12/13"), SEGUNDA_2("Segunda 14/15"),
    TERCA_1("Terça 12/13"), TERCA_2("Terça 14/15"),
    QUARTA_1("Quarta 12/13"), QUARTA_2("Quarta 14/15"),
    QUINTA_1("Quinta 12/13"), QUINTA_2("Quinta 14/15"),
    SEXTA_1("Sexta 12/13"), SEXTA_2("Sexta 14/15");

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
