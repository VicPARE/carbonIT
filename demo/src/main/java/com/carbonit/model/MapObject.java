package com.carbonit.model;

public abstract class MapObject {

    public int x ;
    public int y ;

    public MapObject(int x, int y){
        this.x = x ;
        this.y = y ;
    }
    
    public int getX(){
        return this.x ;
    }
    public int getY(){
        return this.y ;
    }    
    @Override
    public String toString(){
        return x + " - " + y ;
    }
}
