package PantryPal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class AutoLogin {


    public static String autoLoginUsername(String file) {
        String username = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            username = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return username;
    }

    public static String autoLoginPassword(String file) {
        String password = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            password = reader.readLine();
            password = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return password;
    }

    public static boolean autoLoginEnabled(String filename){
        File file = new File(filename);
        boolean enabled;
        try {
            List<String> lines = Files.readAllLines(file.toPath());

            if (lines.isEmpty()) {
                enabled = false;
            } else {
                enabled = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return enabled;
    }

    public static void createFile(String username, String password) {
        Path filePath = Paths.get("AutoLogin.txt");

        List<String> content = Arrays.asList(username, password);

        try {
            Files.write(filePath, content);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void clearFile() {
        Path filePath = Paths.get("AutoLogin.txt");

        try {
            Files.write(filePath, "".getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createFileTest(String username, String password) {
        Path filePath = Paths.get("TestFile.txt");

        List<String> content = Arrays.asList(username, password);

        try {
            Files.write(filePath, content);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearFileTest() {
        Path filePath = Paths.get("TestFile.txt");

        try {
            Files.write(filePath, "".getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
