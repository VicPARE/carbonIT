package com.carbonit.enums;

public enum Actions {
    AVANCER("A"),
    TOURNER_DROITE("D"),
    TOURNER_GAUCHE("G");

    private String label ;

    public String getLabel() {
        return label ;
    }

    Actions(String label){
        this.label = label;
    }

    public static Actions getAction(String action){
        switch (action) {
            case "A": return AVANCER ;
            case "D": return TOURNER_DROITE ;
            case "G": return TOURNER_GAUCHE ;      
        default:
                return null;
        }
    }

    public static Actions getAction(char action){
        switch (action) {
            case 'A': return AVANCER ;
            case 'D': return TOURNER_DROITE ;
            case 'G': return TOURNER_GAUCHE ;      
        default:
                return null;
        }
    }    
}
