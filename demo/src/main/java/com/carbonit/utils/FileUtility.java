package com.carbonit.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.carbonit.exceptions.InvalidFileException;

public class FileUtility {

        public static File getRessourceFile(String fileName) throws FileNotFoundException {
            URL ressource = FileUtility.class.getResource("/"+fileName) ;
            if (ressource == null){
                throw new FileNotFoundException("Cannot find inputFile"+fileName);
            }
            try {
                String url_decoded =  URLDecoder.decode(ressource.getFile(),"UTF-8") ;
                return new File(url_decoded);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return new File(ressource.getFile());
        }

        public static List<String> parseFile(File inputFile) throws InvalidFileException{
            List<String> result = new ArrayList<String>();
            if (inputFile == null) {
                return null ;
            }
            try{
                result =  Files.readAllLines(inputFile.toPath());             
            }catch (IOException err){
                throw new InvalidFileException("Error reading input File " + inputFile.getName() ,err);
            }
            return result;
        }

        public static File writeOutputFile(List<String> lines, File outputFile) throws IOException{
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, false)) ;
            for (String line : lines){
                writer.append(line);
                writer.newLine();
            }
            writer.close();
            return outputFile;
        }            
               
}
