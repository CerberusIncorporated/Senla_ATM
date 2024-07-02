package by.senla.yukhnevich.utils;

import java.util.regex.Pattern;

/**
 * Validation for card
 */
public class RegexAtmValidation {
    /**
     * @param string - a string to check for a pattern
     * @return - true if pattern ХХХХ-ХХХХ-ХХХХ-ХХХХ correct
     */
    public static boolean validateRegex(String string) {
        Pattern pattern = Pattern.compile("^\\d{4}-\\d{4}-\\d{4}-\\d{4}$");
        return pattern.matcher(string).matches();
    }
}
