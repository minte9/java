/**
 * READ FILES / GROUP BY EXTENSION
 * -------------------------------
 * Classic way (no streams) to understand the logic.
 * 
 * Encapsulate get extension logic:
 *  - Reusable
 *  - Testable
 *  - Safe
 */

package app.versions.group_by.classic_no_stream;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileOrganizer {
    public static void main(String[] args) {

        File dir = new File(System.getProperty("user.home"), "Downloads");

        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("Not a directory");
            return;
        }

        File[] files = dir.listFiles();
        if (files == null) return;

        Set<String>allowedExtensions = Set.of("pdf", "sql");

        // HashMap (key, value / no duplicates)
        Map<String, List<File>> filesByExtensions = new HashMap<>();

        // Group by extension
        for(File file : files) {
            if (!file.isFile()) continue;

            String ext = getExtension(file);
            
            if (!allowedExtensions.contains(ext)) continue;

            filesByExtensions
                .computeIfAbsent(ext, k -> new ArrayList<>())
                .add(file);
        }

        // Print the result
        filesByExtensions.forEach((ext, list) -> {
            System.out.println(ext + ":");
            list.forEach(f -> System.out.println("  " + f.getName()));
        });

        System.out.println("Done!");
    }
    
    private static String getExtension(File file) {
        String name = file.getName();
        int dot = name.lastIndexOf(".");

        if (dot == -1 || dot == name.length() - 1) {
            return "no_extension";
        }

        return name.substring(dot + 1).toLowerCase();
    }
}