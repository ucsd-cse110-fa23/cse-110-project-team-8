package PantryPalTest;

import org.junit.jupiter.api.Test;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.ListFolderErrorException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import PantryPal.*;
import Server.*;

public class ShareFunctionalityTests {
    File Testfile;
    DropBox dropBox;
    final String TITLE = "some_title";
    final String INGREDIENTS = "some_ingredients";
    final String INSTRUCTION = "some_instructions";
    final String IMAGE_PATH = "testImage.jpg";
    @BeforeEach
    void setUp() {
        dropBox = new DropBox();
    }
       
    @AfterEach
    void closeDown() throws ListFolderErrorException, DbxException{
       dropBox.delete(TITLE);
       String fileName = TITLE + ".pdf";

        // Get the current working directory
        Path currentDirectory = Paths.get(System.getProperty("user.dir"));

        // Create the path to the file in the current directory
        Path filePath = currentDirectory.resolve(fileName);

        try {
            // Delete the file
            Files.delete(filePath);
        } catch (IOException e) {

        }
        
    }

    @Test
    void testDropBoxURL() throws IOException, DbxException {
        String URL = dropBox.DropBoxTest(TITLE, INGREDIENTS, INSTRUCTION,IMAGE_PATH);
        assertNotEquals(" ", URL);
    }
}
