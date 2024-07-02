package by.senla.yukhnevich.utils;

import java.io.File;

public class FileNameValidation {
    /**
     * Checks the file to work with it
     *
     * @param path - path to file for checking
     * @return - true if everything fine
     */
    public static boolean validateFile(String path) {
        if (path == null) {
            System.err.println("The path equals to null");
            return false;
        }
        if (path.isBlank()) {
            System.err.println("Path is empty");
            return false;
        }
        File file = new File(path);
        return file.exists() && file.length() > 0;
    }
}
