package algoritmo;

import model.*;

import java.util.*;

public class Algoritmo {

    private List<Individuo> populacao;
    private Map<DisciplinaEnum, List<Professor>> disciplinaProfessores;
    private Random random;
    private Integer qtPopulacao;
    private Integer geracaoMaxima;
    private Integer aptidaoIdeal = 400;
    private Integer geracaoAtual = 1;

    public void init(Integer qtPopulacao, Integer geracaoMaxima, Map<DisciplinaEnum, List<Professor>> disciplinaProfessores){
        this.populacao = new ArrayList<Individuo>();
        this.disciplinaProfessores = disciplinaProfessores;
        this.qtPopulacao = qtPopulacao;
        this.geracaoMaxima = geracaoMaxima;
        this.random = new Random();

        iniciarPopulacao();

        calcularAptidao();

        while(!fimEvolucao()){

            crossoverAndMutacao();

            calcularAptidao();

            selecionarMelhores();

            geracaoAtual++;
        }

        System.out.println("Fim");
        imprimirGradeFinal();
    }

    private void imprimirGradeFinal(){
        for(Individuo<Aula> ind : populacao){
            if(ind.getAptidao().equals(aptidaoIdeal)){
                System.out.println("--------- GRADE FINAL:");
                for(Aula aula : ind.getCromossomo()){
                    System.out.println(aula.getHorario().getHorario()+" - "+aula.getDisciplina().toString()+" - Professor: "+aula.getProfessor().getNome() );
                }
            }
        }
    }

    /**
     * Inicia a população
     */
    private void iniciarPopulacao(){
        for(int i = 0; i < qtPopulacao; i++){
            Individuo individuo = new Grade(geracaoAtual);

            //Popula o cromossomo com os genes(Aula)
            for(int posicaoHorario = 0; posicaoHorario < 10; posicaoHorario++){

                Aula aula = new Aula(posicaoHorario);
                populaGeneRandom(aula);

                individuo.getCromossomo().add(aula);
            }

            populacao.add(individuo);
        }
    }

    /**
     * Popula aleatóriamente o gene
     * @param aula
     */
    private void populaGeneRandom(Aula aula){
        int indexRandom = random(DisciplinaEnum.values().length);
        aula.setDisciplina(DisciplinaEnum.values()[indexRandom]);

        //popula o professor que oferta a disciplina
        if(disciplinaProfessores.get(aula.getDisciplina()) != null) {
            indexRandom = random(disciplinaProfessores.get(aula.getDisciplina()).size());
            aula.setProfessor(disciplinaProfessores.get(aula.getDisciplina()).get(indexRandom));
        }
    }

    /**
     * Faz o crossover dos individuos selecionados por roleta e depois gera a mutação,
     * caso o individuo não exista na população ele é adicionado
     */
    private void crossoverAndMutacao(){
        //ordena pela aptidão de cada
        Collections.sort(populacao);

        List<Individuo> novaPopulacao = new ArrayList<Individuo>();

        while(novaPopulacao.size() < qtPopulacao){
            Individuo filho = null;
            Individuo filho1 = null;

            do {
                Casal casal = geraCasalSelecaoPorRoleta();
                filho = crossover(casal.getPai(), casal.getMae());
                filho1 = crossover(casal.getMae(), casal.getPai());

                mutacao(filho);
                mutacao(filho1);
            }while (populacao.contains(filho) || populacao.contains(filho1) ||
                    novaPopulacao.contains(filho) || novaPopulacao.contains(filho1));

            novaPopulacao.add(filho);
            novaPopulacao.add(filho1);
        }

        populacao.addAll(novaPopulacao);
    }

    /**
     * Gera o casal por seleção de roleta
     * @return
     */
    private Casal geraCasalSelecaoPorRoleta(){
        Individuo pai = getSelecaoPorRoleta();
        Individuo mae = null;

        do {
            mae = getSelecaoPorRoleta();
        } while (pai.equals(mae));

        return new Casal(pai,mae);
    }


    /**
     * seleção por roleta
     * @return
     */
    private Individuo getSelecaoPorRoleta(){
        Integer somatoria = 0;
        for(Individuo i : populacao){
            somatoria += i.getAptidao();
        }
        Integer random = random(somatoria);

        Integer posicaoEscolhida = -1;
        do{
            posicaoEscolhida++;
            somatoria -= populacao.get(posicaoEscolhida).getAptidao();
        }while(somatoria > random);

        return populacao.get(posicaoEscolhida);
    }

    /**
     * Crossover de um ponto aleatório
     * @param pai
     * @param mae
     * @return
     */
    private Individuo crossover(Individuo pai, Individuo mae){
        Individuo filho = new Grade(geracaoAtual);

        Integer soma;
        do{
            soma = random(6);
        }while(soma == 0);

        List<Aula> cromossomos = pai.getCromossomo().subList(0,soma);
        filho.getCromossomo().addAll(cromossomos);

        cromossomos = mae.getCromossomo().subList(soma,10);
        filho.getCromossomo().addAll(cromossomos);

        return filho;
    }

    /**
     * Gera a mutação selecionando um gene aleatório
     * @param ind
     */
    private void mutacao(Individuo<Aula> ind){
        Aula gene = ind.getCromossomo().get(random(ind.getCromossomo().size()));
        DisciplinaEnum disciplinaAntiga = gene.getDisciplina();

        do {
            populaGeneRandom(gene);
        } while (disciplinaAntiga.equals(gene.getDisciplina()));
    }


    /**
     * Cálcula o aptidão(fitness)
     */
    private void calcularAptidao(){
        Integer media = 0;
        Integer maior = 0;

        for(Individuo<Aula> ind : populacao){
            if(ind.getAptidao() == 0) {
                Integer aptidao = 0;

                aptidao += validaDisciplinaProfessor(ind); //100 pontos
                aptidao += validaHorarioProfessor(ind); //100 pontos
                aptidao += validaMateriasPorSemana(ind); //100 pontos
                aptidao += valida2HorariosLivresProfessor(ind); //100 pontos

                ind.setAptidao(aptidao);
            }

            media =+ ind.getAptidao();
            if(ind.getAptidao() > maior){
                maior = ind.getAptidao();
            }
        }

        System.out.println("Geração "+geracaoAtual+" - Aptidão ideal: "+aptidaoIdeal+" - Média aptidão: "+media+" - Maior aptidão:"+maior);
    }

    /**
     * Valida se as materias na grade estão 2 ou 4 setadas
     * @param ind
     * @return 100 pontos
     */
    private Integer validaMateriasPorSemana(Individuo<Aula> ind){
        Integer pontos = 0;


        for(Aula aula : ind.getCromossomo()) {
            DisciplinaEnum disciplina = aula.getDisciplina();
            int qt = 0;

            for(Aula aula2 : ind.getCromossomo()) {
                if(aula2.getDisciplina() == disciplina){
                    qt++;
                }
            }

            if(qt == 2 || qt == 4){
                pontos += 10;
            }
        }

        return pontos;
    }

    /**
     * Valida se o professor que esta na grade tem 2 horários livres para preparar a aula
     * @param ind
     * @return 100 pontos
     */
    private Integer valida2HorariosLivresProfessor(Individuo<Aula> ind){
        Integer pontos = 0;


        for(Aula aula : ind.getCromossomo()) {
            Integer aulas = 0;

            if (aula.getProfessor() != null) {

                for(Aula aula2 : ind.getCromossomo()) {
                    if(aula2.getProfessor() != null && aula2.getProfessor().equals(aula.getProfessor())){
                        aulas++;
                    }
                }

                if((aula.getProfessor().getHorarios().size() - aulas) > 2){
                    pontos += 10;
                }
            }
        }

        return pontos;
    }

    /**
     * Verifica se o professor tem o horário disponível para a aula
     * @param ind
     * @return 100 pontos
     */
    private Integer validaHorarioProfessor(Individuo<Aula> ind){
        Integer pontos = 0;

        //percorre gene por gene
        for(Aula aula : ind.getCromossomo()) {
            if (aula.getProfessor() != null && aula.getProfessor().getHorarios().contains(aula.getHorario())) {
                pontos += 10;
            }
        }
        return pontos;
    }

    /**
     * Valida se a disciplina tem algum professor disponível para ela
     * @param ind
     * @return 100 pontos
     */
    private Integer validaDisciplinaProfessor(Individuo<Aula> ind){
        Integer pontos = 0;

        //percorre gene por gene
        for(Aula aula : ind.getCromossomo()) {
            if (aula.getProfessor() != null) {
                pontos += 10;
            }
        }
        return pontos;
    }

    /**
     * Seleciona os melhores individuos e elimina os ruins
     */
    private void selecionarMelhores(){
        Collections.sort(populacao);
        populacao = populacao.subList(0, qtPopulacao);
    }

    /**
     * Verifica se a evolução terminou
     * @return
     */
    private boolean fimEvolucao(){
        if(geracaoAtual >= geracaoMaxima){
            return true;
        }
        for(Individuo<Aula> ind : populacao){
            if(ind.getAptidao().equals(aptidaoIdeal)){
                return true;
            }
        }
        return false;
    }

    private int random(int limite){
        return random.nextInt(limite);
    }
}
