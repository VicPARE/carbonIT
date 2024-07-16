package com.carbonit.entity;

import com.carbonit.model.MapObject;

public class Carte extends MapObject {

    public static final String PATTERN = "^C\\-\\d{1,}\\-\\d{1,}" ;

    public Carte(int x, int y) {
        super(x, y);
    }

    public String toString(){
        return "C - " + super.toString() ;
    }

    public boolean estHorsCarte(int x , int y){
        return (x >= this.getX()) || (x < 0)  || (y < 0) || (y >= this.getY()) ;
    }
}
