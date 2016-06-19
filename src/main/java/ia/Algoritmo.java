package ia;

import model.*;

import java.util.*;

public class Algoritmo {

    private List<Individuo> populacao;
    private List<Casal> casal;
    private Map<DisciplinaEnum, List<Professor>> disciplinaProfessores;
    private Random random;
    private Integer qtPopulacao;
    private Integer geracaoMaxima;
    private Integer aptidaoIdeal;
    private Integer geracaoAtual = 1;

    public void init(Integer qtPopulacao, Integer geracaoMaxima, Integer aptidaoIdeal, Map<DisciplinaEnum, List<Professor>> disciplinaProfessores){
        this.populacao = new ArrayList<Individuo>();
        this.casal = new ArrayList<Casal>();
        this.disciplinaProfessores = disciplinaProfessores;
        this.aptidaoIdeal = aptidaoIdeal;
        this.qtPopulacao = qtPopulacao;
        this.geracaoMaxima = geracaoMaxima;
        this.random = new Random();

        iniciarPopulacao();

        calcularAptidao();

        while(!fimEvolucao()){
            selecao();

            crossover();

            mutacao();

            calcularAptidao();

            selecionarMelhores();

            geracaoAtual++;
        }

        System.out.println("Fim");
    }

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
        System.out.println("populaGeneRandom");
        int indexRandom = random(DisciplinaEnum.values().length);
        aula.setDisciplina(DisciplinaEnum.values()[indexRandom]);

        //popula o professor que oferta a disciplina
        if(disciplinaProfessores.get(aula.getDisciplina()) != null) {
            indexRandom = random(disciplinaProfessores.get(aula.getDisciplina()).size());
            aula.setProfessor(disciplinaProfessores.get(aula.getDisciplina()).get(indexRandom));
        }
    }

    private void selecao(){
        System.out.println("selecao");

        //ordena pela aptidão de cada
        Collections.sort(populacao);

        while(casal.size() < (qtPopulacao/2)){
            Individuo pai = getSelecaoPorRoleta();
            Individuo mae = null;

            do{
                mae = getSelecaoPorRoleta();
            }while(pai.equals(mae));

            casal.add(new Casal(pai, mae));
        }
    }

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

        System.out.println("getSelecaoPorRoleta geracao:"+geracaoAtual+" somatoria:"+somatoria+" random:"+random+" posicaoEscolhida:"+posicaoEscolhida);

        return populacao.get(posicaoEscolhida);
    }

    private void crossover(){
        System.out.println("crossover");
        for(Casal cs : casal){
            populacao.add(geraFilho(cs.getPai(), cs.getMae()));
            populacao.add(geraFilho(cs.getMae(), cs.getPai()));
            cs = null;
        }
        casal.clear();
    }


    /**
     * Cruzamento Uniforme - os bits são copiados aleatóriamento do primeiro ou segundo pai
     * @param pai
     * @param mae
     * @return
     */
    private Individuo geraFilho(Individuo pai, Individuo mae){
        System.out.println("geraFilho");
        Individuo filho = new Grade(geracaoAtual);

        List<Aula> cromossomos = pai.getCromossomo().subList(0,3);
        filho.getCromossomo().addAll(cromossomos);

        cromossomos = mae.getCromossomo().subList(3,7);
        filho.getCromossomo().addAll(cromossomos);

        cromossomos = pai.getCromossomo().subList(7,10);
        filho.getCromossomo().addAll(cromossomos);

        return filho;
    }
    private void mutacao(){
        System.out.println("mutacao");
        for(Individuo<Aula> ind : populacao){
            if(ind.getAptidao() == null) {
                Aula gene = ind.getCromossomo().get(random(9));
                DisciplinaEnum disciplinaAntiga = gene.getDisciplina();

                do{
                    populaGeneRandom(gene);
                }while(disciplinaAntiga.equals(gene.getDisciplina()));
            }
        }
    }

    private void calcularAptidao(){
        System.out.println("calcularAptidao");

        Integer media = 0;
        Integer maior = 0;

        for(Individuo<Aula> ind : populacao){
            if(ind.getAptidao() == null) {
                Integer aptidao = 0;

                aptidao += validaHorarioProfessor(ind);
                aptidao += validaDiaLivreProfessor(ind);
                aptidao += valida2MateriasPorSemana(ind);

                ind.setAptidao(aptidao);

                media =+ ind.getAptidao();
                if(ind.getAptidao() > maior){
                    maior = ind.getAptidao();
                }
            }
        }
        System.out.println("Geração "+geracaoAtual+", média aptidão: "+media+" maior:"+maior);
    }


    private Integer valida2MateriasPorSemana(Individuo<Aula> ind){
        Integer pontos = 0;

        return pontos;
    }

    private Integer validaDiaLivreProfessor(Individuo<Aula> ind){
        Integer pontos = 0;

        return pontos;
    }

    //Máx retornado 100
    private Integer validaHorarioProfessor(Individuo<Aula> ind){
        System.out.println("validaHorarioProfessor");
        Integer pontos = 0;

        //percorre gene por gene
        for(Aula aula : ind.getCromossomo()) {
            if (aula.getProfessor() != null && aula.getProfessor().getHorarios().contains(aula.getHorario())) {
                pontos += 10;
            }
        }
        return pontos;
    }

    private void selecionarMelhores(){
        System.out.println("selecionarMelhores");
        Collections.sort(populacao);
        populacao = populacao.subList(0, qtPopulacao);
        populacao.size();
    }

    private boolean fimEvolucao(){
        System.out.println("fimEvolucao");
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
