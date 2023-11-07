package PantryPal;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.geometry.Insets;
import java.io.*;
import javax.sound.sampled.*;

public class AudioRecorder {
    public AudioFormat audioFormat;
    public TargetDataLine targetDataLine;

    public AudioFormat getAudioFormat() {
        // the number of samples of audio per second.
        // 44100 represents the typical sample rate for CD-quality audio.
        float sampleRate = 44100;

        // the number of bits in each sample of a sound that has been digitized.
        int sampleSizeInBits = 16;

        // the number of audio channels in this format (1 for mono, 2 for stereo).
        int channels = 1;

        // whether the data is signed or unsigned.
        boolean signed = true;

        // whether the audio data is stored in big-endian or little-endian order.
        boolean bigEndian = false;

        return new AudioFormat(
                sampleRate,
                sampleSizeInBits,
                channels,
                signed,
                bigEndian);
    }

    public void startRecording(String FILE_PATH) {
        Thread t = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // the format of the TargetDataLine
                            DataLine.Info dataLineInfo = new DataLine.Info(
                                    TargetDataLine.class,
                                    audioFormat);
                            // the TargetDataLine used to capture audio data from the microphone
                            targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
                            targetDataLine.open(audioFormat);
                            targetDataLine.start();

                            // the AudioInputStream that will be used to write the audio data to a file
                            AudioInputStream audioInputStream = new AudioInputStream(
                                    targetDataLine);

                            // the file that will contain the audio data
                            if (FILE_PATH != null) {
                                File audioFile = new File(FILE_PATH);
                                AudioSystem.write(
                                        audioInputStream,
                                        AudioFileFormat.Type.WAVE,
                                        audioFile);
                            } else {
                                System.err.println("FILE_PATH is null");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
        t.start();
    }

    public void stopRecording() {
        targetDataLine.stop();
        targetDataLine.close();
    }
}
