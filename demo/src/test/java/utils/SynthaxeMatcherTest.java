package utils;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.carbonit.entity.Aventurier;
import com.carbonit.entity.Carte;
import com.carbonit.entity.Montagne;
import com.carbonit.entity.Tresor;
import com.carbonit.exceptions.InvalidFileException;
import com.carbonit.utils.SynthaxeMatcher;

public class SynthaxeMatcherTest {
    @Test
    public void testFileInputParsing() throws InvalidFileException {
        String input = "   A -      Lara -       1 - 1           - S - A A D A D A  G G A    " ;
        assertTrue(SynthaxeMatcher.matchesAny(input, Aventurier.PATTERN, Montagne.PATTERN, Tresor.PATTERN, Carte.PATTERN));


    }
}
