package com.carbonit.entity;

import com.carbonit.model.MapObject;

public class Montagne extends MapObject{

    public static final String PATTERN = "^M\\-\\d{1,}\\-\\d{1,}" ;

    public Montagne(int x, int y) {
        super(x, y);
    }
    @Override
    public String toString(){
        return "M - " + super.toString() ;
    }
}
