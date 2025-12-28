/**
 * READ FILES / CREATE FOLDERS & MOVE FILES
 * ------------------------------------------
 *  - Create folders per extension
 *  - Move files into them
 *  - Dry-run safety (very important)
 * 
 * We use NIO for File Operations (Recommended)
 *  - Safe
 *  - Clear
 *  - Cross-platform
 * 
 * Why This Is Production-Grade:
 *  - Uses Path + Files
 *  - Has dry-run safety
 *  - No duplicate logic
 *  - Clear separation of responsibilities
 *  - Works on Linux / Windows / macOS
 */

package app.versions.move_files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FileOrganizer {
    private static final boolean DRY_RUN = false; // change to files to actually move files
    public static void main(String[] args) {
        File dir = new File(System.getProperty("user.home"), "Downloads");

        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("Not a directory");
            return;
        }

        File[] files = dir.listFiles();
        if (files == null) return;

        Set<String>allowedExtensions = Set.of("pdf", "sql", "png", "jpeg");

        // HashMap (with stream)
        Map<String, List<File>> filesByExtensions = 
            Arrays.stream(files)
                  .filter(File::isFile)
                  .filter(f -> allowedExtensions.contains(getExtension(f)))
                  .collect(Collectors.groupingBy(FileOrganizer::getExtension));

        // Print the result
        filesByExtensions.forEach((ext, list) -> {
            try {
                organizeFiles(dir.toPath(), ext, list);
            } catch (IOException e) {
                System.out.println("Failed to organize ." + ext + " files: " + e.getMessage());
            }
        });

        System.out.println("Done!");

    }

    private static void organizeFiles(Path baseDir, String extension, List<File> files) 
            throws IOException {

        Path targetDir = baseDir.resolve(extension.toUpperCase());

        if (Files.notExists(targetDir)) {
            System.out.println("Create directory: " + targetDir);
            if (!DRY_RUN) {
                Files.createDirectories(targetDir);
            }
        }

        for (File file : files) {
            Path source = file.toPath();
            Path target = targetDir.resolve(file.getName());

            System.out.println("Move: " + source + " -> " + target);
            if (!DRY_RUN) {

                // Prevent accidental overriting
                if (Files.exists(target)) {
                    System.out.println("Skipping existing file: " + target);
                    return;
                }

                // Move files
                Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
            }
        }
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
