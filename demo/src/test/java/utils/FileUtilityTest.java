package utils;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.carbonit.exceptions.InvalidFileException;
import com.carbonit.utils.FileUtility;

public class FileUtilityTest {


    @Test
    public void testgetRessourceFile() throws InvalidFileException, FileNotFoundException {

        File inputFile = FileUtility.getRessourceFile("aventureTest.txt") ;
        assertNotNull(inputFile);
        assertTrue(inputFile.exists());
    }

    @Test
    public void testFileInputParsing() throws InvalidFileException, FileNotFoundException {

        File inputFile = FileUtility.getRessourceFile("aventureTest.txt") ;
        assertNotNull(inputFile);
        List<String> inputLines = FileUtility.parseFile(inputFile) ;
        assertEquals("#Test 1",inputLines.get(0));
        assertEquals("C - 3 -4",inputLines.get(1));
        assertEquals("M - 1- 0",inputLines.get(2));
        assertEquals("A - Lara - 1 - 1 - N - AADADAGGA",inputLines.get(6));
    }

    @Test
    public void testOutputWriting() throws IOException, InvalidFileException {
        File outputFile  = new File(Path.of("D:","carbonIT","test_output").toFile(),"output.txt");
        outputFile.getParentFile().mkdirs();
        List<String> inputLines = new ArrayList<String>(){{
            add("#Test 1");
            add("C - 3 -4");
            add("M - 1- 0");
            add("A - Lara - 0- 0 - N - AADADAGGA");
        }
        } ;
        File output = FileUtility.writeOutputFile(inputLines,outputFile);
        assertTrue(output.exists()); 
        List<String> outputLines = FileUtility.parseFile(outputFile);
        assertEquals("#Test 1",outputLines.get(0));
        assertEquals("C - 3 -4",outputLines.get(1));
        assertEquals("M - 1- 0",outputLines.get(2));
        assertEquals("A - Lara - 0- 0 - N - AADADAGGA",outputLines.get(3));        
    }

    @Test(expected = FileNotFoundException.class)
    public void testsMissingFile() throws FileNotFoundException {
        FileUtility.getRessourceFile("missingFile.abc") ;
    }   
}
