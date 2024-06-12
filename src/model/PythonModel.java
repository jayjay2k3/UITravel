/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Paths;

/**
 *
 * @author ACER
 */
public class PythonModel {
    public static void main(String[] args) {
        String inputData = "Tệ nhưng rất tốt"; // Example input string, modify as needed
        String basePath = System.getProperty("user.dir");
        String scriptPath = Paths.get(basePath, "src",  "model", "model.py").toString();

        try {
            // Construct the command to run the Python script
            ProcessBuilder pb = new ProcessBuilder("python", scriptPath, inputData);
            pb.redirectErrorStream(true); // Redirect error stream to the output stream
            Process process = pb.start();

            // Capture the output from the script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            // Wait for the process to complete
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Prediction result: " + result.toString());

            } else {
                System.out.println("Error occurred: " + result.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
