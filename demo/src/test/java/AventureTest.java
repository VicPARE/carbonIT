import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.junit.Test;

import com.carbonit.Aventure;
import com.carbonit.entity.Aventurier;
import com.carbonit.entity.Montagne;
import com.carbonit.exceptions.InvalidFileException;
import com.carbonit.exceptions.InvalidSynthaxException;
import com.carbonit.utils.FileUtility;

public class AventureTest {

@Test
public void obstacleTest() throws InvalidFileException, InvalidSynthaxException, FileNotFoundException {
    File inputFile = FileUtility.getRessourceFile("aventure.txt") ;
    Aventure aventure= new Aventure(inputFile) ;
    Aventurier lara = aventure.aventuriers.get(0) ;
    assertEquals("Lara",lara.getNom());
    assertEquals(false,aventure.obstacle(lara));
    aventure.montagnes.add(new Montagne(1, 2));
    assertEquals(true,aventure.obstacle(lara));
}    

@Test
public void testscenario() throws InvalidFileException, InvalidSynthaxException, FileNotFoundException {
    File inputFile = FileUtility.getRessourceFile("aventure.txt") ;
    Aventure aventure= new Aventure(inputFile) ;
    Aventurier lara = aventure.aventuriers.get(0) ; 
    aventure.executerTour(lara); // obstacle montagne au Nord
    assertEquals(1,lara.getX());
    assertEquals(2,lara.getY());
}   

@Test
public void testscenario1() throws InvalidFileException, InvalidSynthaxException, IOException {
    File inputFile = FileUtility.getRessourceFile("scenario1_test.txt") ;
    File outputFile  = new File(Path.of("D:","carbonIT","test_output").toFile(),"output.txt");
    Aventure aventure= new Aventure(inputFile) ;
    Aventurier lara = aventure.aventuriers.get(0) ;
    Aventurier eric = aventure.aventuriers.get(1) ;
    aventure.lancerAventure();
    assertEquals(1,eric.getTresors());
    assertEquals(0,lara.getTresors());
    File output = aventure.writeOutput(outputFile);
    assertNotNull(output);
    assertEquals("output.txt",output.getName());
    List<String> outputLines = FileUtility.parseFile(output) ;
    assertEquals("C - 3 - 4",outputLines.get(0));
    assertEquals("A - Eric - 1 - 3 - O - 1",outputLines.get(5));
}   

@Test(expected = InvalidSynthaxException.class)
public void testscenarioErrorSynthaxe() throws InvalidFileException, InvalidSynthaxException, FileNotFoundException {
    File inputFile = FileUtility.getRessourceFile("scenario_error.log") ;
    new Aventure(inputFile) ;
}   


}