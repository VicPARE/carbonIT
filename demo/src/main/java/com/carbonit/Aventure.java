package com.carbonit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.carbonit.entity.Aventurier;
import com.carbonit.entity.Carte;
import com.carbonit.entity.Montagne;
import com.carbonit.entity.Tresor;
import com.carbonit.enums.Actions;
import com.carbonit.enums.Orientation;
import com.carbonit.exceptions.InvalidFileException;
import com.carbonit.exceptions.InvalidSynthaxException;
import com.carbonit.model.MapObject;
import com.carbonit.utils.FileUtility;
import com.carbonit.utils.SynthaxeMatcher;

public class Aventure {
        List<String> inputLines ;
        public Carte carte ;
        public List<Montagne> montagnes;
        public List<Tresor> tresors ;
        public List<Aventurier> aventuriers ;
        public Aventure(){
                this.inputLines= new ArrayList<>();
                this.montagnes= new ArrayList<>();
                this.tresors= new ArrayList<>();
                this.aventuriers= new ArrayList<>();
        }

        public Aventure(File inputFile) throws InvalidFileException, InvalidSynthaxException{
                this();
                this.initializeFromFile(inputFile);
        }

        
        public void initializeFromFile(File inputFile) throws InvalidFileException, InvalidSynthaxException{
                this.inputLines = FileUtility.parseFile(inputFile);
                for (String input : inputLines){
                        if (input.trim().startsWith("#")){
                                continue;
                        } 
                        if (SynthaxeMatcher.matchesAny(input, Aventurier.PATTERN, Carte.PATTERN, Tresor.PATTERN, Montagne.PATTERN) == false){
                                throw new InvalidSynthaxException("Invalid line " + input) ;
                        }
                        String[] parsedLine = input.split("-") ;
                        for (int i =0 ; i< parsedLine.length;i++) parsedLine[i] = parsedLine[i].replaceAll("[\\s\\t]","");
                        switch (parsedLine[0]){
                                case "C" : this.carte = new Carte(Integer.valueOf(parsedLine[1]), Integer.valueOf(parsedLine[2])); break ;
                                case "M" : this.montagnes.add(new Montagne(Integer.valueOf(parsedLine[1]), Integer.valueOf(parsedLine[2])));break ;
                                case "T" : this.tresors.add(new Tresor(
                                                Integer.valueOf(parsedLine[1].trim())
                                                , Integer.valueOf(parsedLine[2].trim())
                                                , Integer.valueOf(parsedLine[3].trim()
                                                )));break ;
                                case "A" : this.aventuriers.add(new Aventurier(parsedLine[1].trim()
                                                        ,Integer.valueOf(parsedLine[2].trim())
                                                        ,Integer.valueOf(parsedLine[3].trim())
                                                        ,parsedLine[4].trim()
                                                        ,parsedLine[5].trim()
                                                        ))    ;   break ;
                                default : break;                                                                
                        }
                }
        }

        public Optional <MapObject> localiserObjet(int x, int y, Class<? extends MapObject> cls) {
                List<MapObject> allObjects = new ArrayList<MapObject>(){{addAll(montagnes);addAll(tresors);addAll(aventuriers);}} ;
                return allObjects.stream().filter(m-> m.getX()== x && m.getY()==y && m.getClass() == cls).findAny() ;
        } 

        public void executerTour(Aventurier aventurier){
                Actions action = aventurier.lireInstruction() ;
                if (action == null) return;
                if (!action.equals(Actions.AVANCER)) aventurier.executerAction(action);
                else {
                     if (!this.obstacle(aventurier)) {
                        aventurier.executerAction(action); 
                        Optional<MapObject> tresor = localiserObjet(aventurier.getX(),aventurier.getY(), Tresor.class) ;
                        if (tresor.isPresent()){
                                ramassageTresor((Tresor) tresor.get(),aventurier);
                        }
                     }   
                } 
        }


        public boolean obstacle(Aventurier aventurier){
                Orientation orientation = aventurier.getOrientation() ;
                int xObstacle = aventurier.getX() + (orientation.equals(Orientation.EST)?1:0) + (orientation.equals(Orientation.OUEST)?-1:0);
                int yObstacle = aventurier.getY() + (orientation.equals(Orientation.NORD)?-1:0) + (orientation.equals(Orientation.SUD)?1:0);
                if (this.carte.estHorsCarte(xObstacle,yObstacle)) return true ;               
                return   localiserObjet(xObstacle, yObstacle, Montagne.class).isPresent() 
                        ||  localiserObjet(xObstacle, yObstacle, Aventurier.class).isPresent();                         
                
        }

        private void ramassageTresor(Tresor tresor, Aventurier aventurier){
                if (tresor.getButin()>0) {
                        aventurier.prendreTresor();
                        tresor.priseButin();
                }
        }

        public boolean finAventure(){
                return !this.aventuriers.stream().filter(a -> a.instructionSuivante()).findAny().isPresent() ;
        }

        public void lancerAventure(){
                while (!finAventure()) {
                        for (Aventurier aventurier : aventuriers) this.executerTour(aventurier);
                }
        }

        public File writeOutput(File outputFile) throws IOException{
                outputFile.getParentFile().mkdirs();
                if (!outputFile.exists()) outputFile.createNewFile();
                List<String> lines = new ArrayList<>();
                if (this.carte!=null) lines.add(this.carte.toString());
                lines.addAll(this.montagnes.stream().map(m->m.toString()).collect(Collectors.toList()));
                lines.addAll(this.tresors.stream().filter(t-> !this.localiserObjet(t.getX(), t.getY(), Aventurier.class).isPresent()).map(t->t.toString()).collect(Collectors.toList()));
                lines.addAll(this.aventuriers.stream().map(a->a.toString()).collect(Collectors.toList()));
                File output = FileUtility.writeOutputFile(lines,outputFile) ;         
                System.out.println("Results written in file " + output.getCanonicalPath());   
                return   output    ;  
        }        
}
