package ia;

import model.DisciplinaEnum;
import model.HorariosEnum;
import model.Professor;

import java.util.*;

public class Main {

    public static void main(String[] args){
        Main main = new Main();
        main.init();
    }

    private void init(){
        Algoritmo alg = new Algoritmo();

        List<Professor> professores = gerarProfessoresA();
        List<Professor> professoresB = gerarProfessoresB();

        Map<DisciplinaEnum, List<Professor>> disciplinaProfessores = new HashMap<DisciplinaEnum, List<Professor>>();

        disciplinaProfessores.put(DisciplinaEnum.ALGORITMOS, professores);
        disciplinaProfessores.put(DisciplinaEnum.IA, professores);
        disciplinaProfessores.put(DisciplinaEnum.BANCOS_DADOS, professores);
        disciplinaProfessores.put(DisciplinaEnum.COMPUTACAO_GRAFICA, professoresB);
        disciplinaProfessores.put(DisciplinaEnum.MINERACAO_DADOS, professores);
        disciplinaProfessores.put(DisciplinaEnum.ESTRUTURA_DE_DADOS, professoresB);
        disciplinaProfessores.put(DisciplinaEnum.GRAFOS, professoresB);

        alg.init(20, 10000, disciplinaProfessores);
    }

    private List<Professor> gerarProfessoresA(){
        List<Professor> professores = new ArrayList<Professor>();

        Professor prof = new Professor("Pedro da Silva");
        prof.setHorarios(Arrays.asList(
                HorariosEnum.SEGUNDA_2,
                HorariosEnum.TERCA_1,  HorariosEnum.TERCA_2,
                HorariosEnum.QUARTA_1, HorariosEnum.QUARTA_2,
                HorariosEnum.SEXTA_1,  HorariosEnum.SEXTA_2));
        professores.add(prof);

        prof = new Professor("Bernardo da Cunha");
        prof.setHorarios(Arrays.asList(
                HorariosEnum.SEGUNDA_1, HorariosEnum.SEGUNDA_2 ,
                HorariosEnum.QUINTA_1, HorariosEnum.QUINTA_2,
                HorariosEnum.SEXTA_1, HorariosEnum.SEXTA_2));
        professores.add(prof);

        prof = new Professor("Adalberto da Silveira");
        prof.setHorarios(Arrays.asList(
                HorariosEnum.TERCA_1, HorariosEnum.TERCA_2,
                HorariosEnum.QUARTA_1, HorariosEnum.QUARTA_2,
                HorariosEnum.QUINTA_1, HorariosEnum.QUINTA_2));
        professores.add(prof);

        return professores;
    }

    private List<Professor> gerarProfessoresB(){
        List<Professor> professores = new ArrayList<Professor>();

        Professor prof = new Professor("Afonso Silveira");
        prof.setHorarios(Arrays.asList(
                HorariosEnum.SEGUNDA_2,
                HorariosEnum.TERCA_1,  HorariosEnum.TERCA_2,
                HorariosEnum.QUARTA_1, HorariosEnum.QUARTA_2,
                HorariosEnum.SEXTA_1,  HorariosEnum.SEXTA_2));
        professores.add(prof);

        prof = new Professor("Bernardo da Cunha");
        prof.setHorarios(Arrays.asList(
                HorariosEnum.SEGUNDA_1, HorariosEnum.SEGUNDA_2 ,
                HorariosEnum.QUINTA_1, HorariosEnum.QUINTA_2,
                HorariosEnum.SEXTA_1, HorariosEnum.SEXTA_2));
        professores.add(prof);

        prof = new Professor("Adalberto da Silveira");
        prof.setHorarios(Arrays.asList(
                HorariosEnum.TERCA_1, HorariosEnum.TERCA_2,
                HorariosEnum.QUARTA_1, HorariosEnum.QUARTA_2,
                HorariosEnum.QUINTA_1, HorariosEnum.QUINTA_2));
        professores.add(prof);

        return professores;
    }
}
