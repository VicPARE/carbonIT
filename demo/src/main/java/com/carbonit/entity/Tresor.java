package com.carbonit.entity;

import com.carbonit.model.MapObject;

public class Tresor extends MapObject {

    public static final String PATTERN = "^T\\-\\d{1,}\\-\\d{1,}-\\d{1,}" ;

    private int butin ;

    public Tresor(int x, int y, int butin) {
        super(x, y);
        this.butin = butin ;
    }

    public int getButin() {
        return this.butin;
    }

    public void priseButin(){
        this.butin-=1;
    }

    @Override
    public String toString(){
        return "T - " + super.toString() + " - " + getButin() ;
    }    
}
