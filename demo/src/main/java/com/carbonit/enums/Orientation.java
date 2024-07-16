package com.carbonit.enums;

public enum Orientation {
        NORD("N"),
        SUD("S"),
        EST("E"),
        OUEST("O");

        private String label ;

        public String getLabel() {
            return label ;
        }

        Orientation(String label){
            this.label = label ;
        }

        public static Orientation getOrientation(String orientation){
            switch (orientation) {
                case "N": return NORD ;
                case "S": return SUD ;
                case "E": return EST ;      
                case "O": return OUEST ;      
            default:
                    return null;
            }
        }
 }

