package VoiceInput;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class MockVoiceRecorder {

    // Mock implementation for testing purposes
    public static String record(String action) {
        // Simulate the behavior of the real method during testing
        if (action.equals("record")) {
            return "listening";
        } else {
            return "";
        }
    }

    public static String stopRecord(String action) {

        if (action.equals("stop")) {
            return "stop listening";
        } else {
            return "";
        }
    }
}