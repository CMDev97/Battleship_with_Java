package battleship;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {

    public static String[] splitCoordinate(String coordinate) {
        Pattern pattern = Pattern.compile("([A-Za-z]+)([0-9]+)");
        Matcher matcher = pattern.matcher(coordinate);

        if (matcher.matches()) {
            String letters = matcher.group(1);
            String numbers = matcher.group(2);
            return new String[]{letters, numbers};
        } else {
            throw new IllegalStateException("Error !");
        }
    }

    public static int[] convertCoordinate(String coordinate) {
        String[] positionSplit = Utility.splitCoordinate(coordinate);
        int indexCharacter = Constant.CHARACTERS.indexOf(positionSplit[0]);
        int column = Integer.parseInt(positionSplit[1])-1;
        return new int[]{indexCharacter, column};
    }

}
