package com.codtech;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileHandlingUtility {

    // Path to the text file
    private static final String FILE_PATH = "sample.txt";

    public static void main(String[] args) {
        try {
           
            writeToFile("Hello, this is the first line.\nThis is the second line."); // 1. Write initial content to the file
            
            System.out.println("=== File Content ==="); // 2. Read content from the file
            
            readFromFile();
           
            modifyLine(2, "This is the updated second line."); // 3. Modify a specific line in the file

           
            System.out.println("\n=== Updated File Content ==="); // 4. Read content again to verify changes
            readFromFile();

        } catch (IOException e) {
            System.err.println("An error occurred during file operations: " + e.getMessage());
        }
    }

    // Method to write to a file
    public static void writeToFile(String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
        writer.write(content);
        writer.close();
        System.out.println("Content written to file.");
    }

    // Method to read from a file
    public static void readFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        int lineNumber = 1;
        while ((line = reader.readLine()) != null) {
            System.out.println(lineNumber++ + ": " + line);
        }
        reader.close();
    }

    // Method to modify a specific line in the file
    public static void modifyLine(int lineNumberToModify, String newLineContent) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));

        if (lineNumberToModify <= 0 || lineNumberToModify > lines.size()) {
            System.err.println("Invalid line number.");
            return;
        }

        lines.set(lineNumberToModify - 1, newLineContent); // Modify the desired line

        Files.write(Paths.get(FILE_PATH), lines); // Rewrite file with updated content
        System.out.println("Line " + lineNumberToModify + " modified.");
    }
}
