package ek.kotsu.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Eric Kim on 16/3/24.
 */
public class InputStringChecker {

    public static boolean inByteSizeString(String input, int min, int max) {
        return !(input.getBytes().length > max || input.getBytes().length < min);
    }

    public static boolean among(String input, String[] among) {
        if (among.length > 0) {
            for (String amongStr : among) {
                if (amongStr.equals(input)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public static boolean regex(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

}
