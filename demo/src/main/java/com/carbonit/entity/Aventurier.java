package com.carbonit.entity;

import java.util.List;
import java.util.stream.Collectors;

import com.carbonit.enums.Actions;
import com.carbonit.enums.Orientation;
import com.carbonit.model.MapObject;
import com.carbonit.model.Vivant;

public class Aventurier extends MapObject implements Vivant{

    private String nom ;
    private int tresors = 0 ; 
    public static final String PATTERN = "^A\\-[a-zA-Z]{1,}\\-\\d{1,}\\-\\d{1,}\\-[NSEO]\\-[AGD]{1,}$" ;

    public int getTresors() {
        return tresors;
    }
    private List<Actions> instructions ;
    private int instructionIndex = 0 ;


    private Orientation orientation ;

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation or) {
        this.orientation = or;
    }

    public String getNom() {
        return this.nom;
    }

    public Aventurier(String nom, int x, int y, String or, String instructions) {
        super(x, y);
        this.nom = nom ;
        this.orientation = Orientation.getOrientation(or) ;
        this.instructions = instructions.chars().mapToObj(c -> (char)c).map(c -> Actions.getAction(c)).collect(Collectors.toList()) ;
    }

    private void tournerDroite(){
        switch (this.orientation) {
                case EST : setOrientation(Orientation.SUD) ; break ;
                case OUEST : setOrientation(Orientation.NORD) ; break ;
                case NORD : setOrientation(Orientation.EST) ; break ;
                case SUD : setOrientation(Orientation.OUEST) ; break;
                default : return ;
            }
    }

    private void tournerGauche() {
        switch (this.orientation) {
            case EST : setOrientation(Orientation.NORD) ; break ;
            case OUEST : setOrientation(Orientation.SUD) ; break ;
            case NORD : setOrientation(Orientation.OUEST) ; break ;
            case SUD : setOrientation(Orientation.EST) ; break;
            default : return ; 
            
        }        
    }    

    private void avancer(){
        switch (this.orientation) {
            case EST : this.x += 1 ;  break ;
            case OUEST : this.x -= 1 ;  break ;
            case NORD : this.y -= 1 ;  break ;
            case SUD : this.y += 1 ;  break ;
            default : return ;
        }          
    }        

    public void prendreTresor(){
        this.tresors += 1 ; 
    }    

    public Actions lireInstruction(){
        Actions result = null ;
        if (this.instructionSuivante()){
            result =  instructions.get(instructionIndex);
            instructionIndex ++ ;
        }
        return result ;
    }

    public boolean instructionSuivante(){
        return this.instructionIndex < this.instructions.size() ;
    }

    @Override
    public void executerAction(Actions action) {
        if (action==null) return ;
        switch (action) {
            case AVANCER: this.avancer(); break ;
            case TOURNER_DROITE: this.tournerDroite(); break ;
            case TOURNER_GAUCHE: this.tournerGauche(); break ;
            default:
                break;
        }
    }
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("A - " + this.nom + " - " + super.toString() + " - " + this.orientation.getLabel() + " - " +this.tresors);
        return result.toString();
    }
}
