/**
 * /**
 * READ FILES - REGEX - FILTER BY EXTENSION
 * ----------------------------------------
 * Use regex only if the rule is complex
 */

package app.versions.filter_files.regex;

import java.io.File;
import java.util.regex.Pattern;

public class FileOrganizer {
    public static void main(String[] args) {

        File dir = new File("/home/catalin/Downloads");
        if (!dir.exists()) {
            System.out.println("Not a directory: " + dir.getAbsolutePath());
            return;
        }

        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }

        Pattern pattern = Pattern.compile(".*\\.(pdf|sql)$", Pattern.CASE_INSENSITIVE);

        for(File file : files) {
            if (pattern.matcher(file.getName()).matches()) {
                System.out.println(file.getName());
            }
        }

        System.out.println("Done!");
    }
}
