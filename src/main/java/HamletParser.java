import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by thook on 10/7/15.
 */
public class HamletParser {

    private String hamletData;


    public HamletParser() {
        this.hamletData = loadFile();
    }
    
    private String loadFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("hamlet.txt").getFile());
        StringBuilder result = new StringBuilder("");

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    public String getHamletData() {

        return hamletData;
    }

    public String getNewHamletData(){
        String retrieved = getHamletData();
        String hamletReplaced = replaceData(retrieved,"Leon",findHamletMatcher(retrieved));
        return replaceData(hamletReplaced,"Tariq",findHoratioMatcher(hamletReplaced));
    }

    public static String replaceData(String given, String replacement, Matcher matcher) {
        StringBuffer replaced = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(replaced, replacement);
        }
        matcher.appendTail(replaced);
        return replaced.toString();
    }

    public static Matcher findHoratioMatcher(String given) {
        String patternString = "Horatio";
        Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(given);
        return matcher;
    }

    public static boolean findHamlet(String given) {
        return findHamletMatcher(given).find();
    }

    public static boolean findHoracio(String given) {
        return findHoratioMatcher(given).find();
    }


    public static Matcher findHamletMatcher(String given) {
        String patternString = "Hamlet";
//        String patternString= "Hamlet";
        Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(given);
//        Pattern.matches(pattern, given);
        return matcher;
    }

    public static void main(String[] args) {
        HamletParser hamletParser = new HamletParser();
        String retrieve = hamletParser.getNewHamletData();
        System.out.println(retrieve);
    }

}

//
//
//
//
//        String patternString = "((.*Hamlet.*) (.+?))";
//        Pattern pattern = Pattern.compile(patternString);
//        Matcher matcher = pattern.matcher(given);
//        String replaced = matcher.replaceAll("Leon");
//
//





//    public static String getHamlet(String str){
//        String patternString = ".*Hamlet.*";
//        Pattern pattern = Pattern.compile(patternString,Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(str);
//        String pattern2 = pattern.pattern();
//
//    }



