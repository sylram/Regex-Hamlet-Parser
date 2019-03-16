import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HamletParserTest {
    private String hamletText;
    private HamletParser hamletParser;

    @Before
    public void setUp() {
        this.hamletParser = new HamletParser();
        this.hamletText = hamletParser.getHamletData();
    }

    @Test
    public void testChangeHamletToLeon() {
        //given
        String given ="Enter Hamlet- and MARCELLUS and Hamlet";
        String expected = "Enter Leon- and MARCELLUS and Leon";

        //When
        Matcher matcher = HamletParser.findHamletMatcher(given);
        String actual = HamletParser.replaceData(given,"Leon",matcher);

        //Then
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testChangeHoratioToTariq() {
        //given
        String given ="Enter Horatio and MARCELLUS and Horatio";
        String expected = "Enter Tariq and MARCELLUS and Tariq";

        //When
        Matcher matcher = HamletParser.findHoratioMatcher(given);
        String actual = HamletParser.replaceData(given,"Tariq",matcher);

        //Then
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testFindHoratio() {
        //given
        String given ="Enter HORATIO and MARCELLUS";

        //When
        boolean actual = HamletParser.findHoracio(given);

        //Then
        Assert.assertTrue(actual);
    }

    @Test
    public void testFindHamlet() {
        //given
        String given ="The Tragedy of Hamlet .";

        //When
        boolean actual = HamletParser.findHamlet(given);

        //Then
        Assert.assertTrue(actual);
    }
    @Test
    public void testReplaceData() {
        //given
        HamletParser hamletParser = new HamletParser();


        //When
        String textChanged = hamletParser.getNewHamletData();
        boolean actual = HamletParser.findHamlet(textChanged);
        boolean actual2 = HamletParser.findHoracio(textChanged);

        //Then
        Assert.assertFalse(actual);
        Assert.assertFalse(actual2);
    }
    @Test
    public void testReplaceData2() {
        //given
        HamletParser hamletParser = new HamletParser();
        String textChanged = hamletParser.getNewHamletData();
        Pattern pattern = Pattern.compile("Leon",Pattern.CASE_INSENSITIVE);
        Pattern pattern1 = Pattern.compile("Tariq",Pattern.CASE_INSENSITIVE);


        //When
        Matcher matcher = pattern.matcher(textChanged);
        Matcher matcher1 = pattern1.matcher(textChanged);

        boolean actual = matcher.find();
        boolean actual2 = matcher1.find();


        //Then
        Assert.assertTrue(actual);
        Assert.assertTrue(actual2);
    }
}