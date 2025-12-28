package com.minte9.io.robot;

import java.io.IOException;

public class SoundControl {
    public static void main(String[] args) throws IOException, InterruptedException {
            
        // On Linux, using "amixer" to control sound

        // Mute sound
        String[] command = { "amixer", "set", "Master", "mute" };

        // Unmute sound
        // String[] command = { "amixer", "set", "Master", "unmute" };
        
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process process = processBuilder.start();
        
        // Wait for the process to complete
        int exitCode = process.waitFor();
        if (exitCode == 0) {
            System.out.println("Sound control command executed successfully.");
        } else {
            System.out.println("Sound control command failed.");
        }
    }
}
