package PantryPalTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import VoiceInput.*;

public class VoiceRecorderTests {
    MockVoiceRecorder mock;

    @BeforeEach
    void setUp() throws Exception {
        mock = new MockVoiceRecorder();
    }

    // BDD1
    @Test
    void record() {
        String input = "record";
        String result = MockVoiceRecorder.record(input);
        assertEquals(result.equals("listening"), true);
    }

    // BDD2
    @Test
    void stopRecord() {
        String input = "stop";
        String result = MockVoiceRecorder.stopRecord(input);
        assertEquals(result.equals("stop listening"), true);
    }
}