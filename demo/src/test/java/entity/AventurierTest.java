package entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.regex.Pattern;

import org.junit.Test;

import com.carbonit.entity.Aventurier;
import com.carbonit.enums.Actions;
import com.carbonit.enums.Orientation;

public class AventurierTest {
    @Test
    public void testLectureInstruction(){
        Aventurier lara = new Aventurier("Lara", 1, 1, "S", "AADDAA") ;
        // lara.executerAction(lara.lireInstruction());
        // assertEquals(lara.getX(),1) ;
        // assertEquals(lara.getY(),2) ;
        assertEquals(lara.lireInstruction(),Actions.AVANCER) ;
        assertEquals(lara.lireInstruction(),Actions.AVANCER) ;
        assertEquals(lara.lireInstruction(),Actions.TOURNER_DROITE) ;
        assertEquals(lara.lireInstruction(),Actions.TOURNER_DROITE) ;
        assertEquals(lara.lireInstruction(),Actions.AVANCER) ;
        assertEquals(lara.lireInstruction(),Actions.AVANCER) ;
        assertEquals(lara.lireInstruction(),null) ;
    }


    @Test
    public void testMouvement(){
        Aventurier lara = new Aventurier("Lara", 1, 1, "S", "AADDAAGAGA") ;

        lara.executerAction(lara.lireInstruction());
        assertEquals(1,lara.getX()) ;
        assertEquals(2,lara.getY()) ;
        
        lara.executerAction(lara.lireInstruction());
        assertEquals(1,lara.getX()) ;
        assertEquals(3,lara.getY()) ;
        
        lara.executerAction(lara.lireInstruction());
        assertEquals(1,lara.getX()) ;
        assertEquals(3,lara.getY()) ;
        assertEquals(Orientation.OUEST,lara.getOrientation()) ;
        
        lara.executerAction(lara.lireInstruction());
        assertEquals(1,lara.getX()) ;
        assertEquals(3,lara.getY()) ;
        assertEquals(Orientation.NORD,lara.getOrientation()) ;

        lara.executerAction(lara.lireInstruction());
        assertEquals(1,lara.getX()) ;
        assertEquals(2,lara.getY()) ;

        lara.executerAction(lara.lireInstruction());
        assertEquals(1,lara.getX()) ;
        assertEquals(1,lara.getY()) ;

        lara.executerAction(lara.lireInstruction());
        assertEquals(1,lara.getX()) ;
        assertEquals(1,lara.getY()) ;
        assertEquals(Orientation.OUEST,lara.getOrientation()) ;     

        lara.executerAction(lara.lireInstruction());
        assertEquals(0,lara.getX()) ;
        assertEquals(1,lara.getY()) ;

        lara.executerAction(lara.lireInstruction());
        assertEquals(0,lara.getX()) ;
        assertEquals(1,lara.getY()) ;   
        assertEquals(Orientation.SUD,lara.getOrientation()) ;    
        
        lara.executerAction(lara.lireInstruction());
        assertEquals(0,lara.getX()) ;
        assertEquals(2,lara.getY()) ;         

        // hors spectre action
        lara.executerAction(lara.lireInstruction());
        assertEquals(0,lara.getX()) ;
        assertEquals(2,lara.getY()) ;    
    }    
    @Test
    public void testRamassageTresor(){
        Aventurier lara = new Aventurier("Lara", 1, 1, "S", "AADDAAGAGA") ;
        assertEquals(0,lara.getTresors()) ;
        lara.prendreTresor() ;
        assertEquals(1,lara.getTresors()) ;
    }

    @Test
    public void testEcriture(){
        Aventurier lara = new Aventurier("Lara", 1, 1, "S", "AADDAAGAGA") ;
        assertEquals("A - Lara - 1 - 1 - S - 0",lara.toString()) ;
        lara.prendreTresor() ;
        assertEquals("A - Lara - 1 - 1 - S - 1",lara.toString()) ;
    }   

    @Test
    public void testRegexp(){
        String inputLine = "A  -  Lara - 1 - 1 - S - AADADAGGA";
        assertTrue(Pattern.compile(Aventurier.PATTERN)
                .matcher(inputLine.replaceAll("[\\s\\t]",""))
                .find()) ;
    }       
}
