package com.carbonit;

import java.io.File;
import java.io.IOException;

import com.carbonit.exceptions.InvalidFileException;
import com.carbonit.exceptions.InvalidSynthaxException;

public class Main {
 
    public static void main(String[] args) throws InvalidFileException, InvalidSynthaxException, IOException {
        File inputFile = new File(args[0]) ;
        File outputFile = new File(args[1]) ;
        Aventure aventure = new Aventure(inputFile) ;
        aventure.lancerAventure();
        aventure.writeOutput(outputFile);
    }
}