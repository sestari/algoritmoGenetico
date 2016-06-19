package model;

import java.util.ArrayList;


public class Grade extends Individuo {

    public Grade(Integer geracao){
        super(geracao);
        setCromossomo(new ArrayList<Aula>(10));
    }

}
