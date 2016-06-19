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

        List<Professor> professores = gerarProfessores();
        Map<DisciplinaEnum, List<Professor>> disciplinaProfessores = new HashMap<DisciplinaEnum, List<Professor>>();

        disciplinaProfessores.put(DisciplinaEnum.ALGORITMOS, professores);
        disciplinaProfessores.put(DisciplinaEnum.IA, professores);
        disciplinaProfessores.put(DisciplinaEnum.BANCOS_DADOS, professores);
        disciplinaProfessores.put(DisciplinaEnum.COMPUTACAO_GRAFICA, professores);
        disciplinaProfessores.put(DisciplinaEnum.MINERACAO_DADOS, professores);

        alg.init(20, 1000, 100, disciplinaProfessores);
    }


    private List<Professor> gerarProfessores(){
        List<Professor> professores = new ArrayList<Professor>();

        Professor prof = new Professor("Pedro da Silva");
        prof.setHorarios(Arrays.asList(HorariosEnum.QUARTA_1, HorariosEnum.QUARTA_2, HorariosEnum.SEXTA_1, HorariosEnum.SEGUNDA_2));
        professores.add(prof);

        prof = new Professor("Bernardo da Cunha");
        prof.setHorarios(Arrays.asList(HorariosEnum.SEGUNDA_1, HorariosEnum.SEGUNDA_2, HorariosEnum.SEXTA_1, HorariosEnum.SEXTA_2));
        professores.add(prof);

        prof = new Professor("Adalberto da Silveira");
        prof.setHorarios(Arrays.asList(HorariosEnum.TERCA_1, HorariosEnum.TERCA_2, HorariosEnum.QUARTA_1, HorariosEnum.QUARTA_2
                , HorariosEnum.QUINTA_1, HorariosEnum.QUINTA_2));
        professores.add(prof);

        return professores;

        //10
    }
}
