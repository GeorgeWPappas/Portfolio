import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

public class SoundClip {
    public SoundClip() {} // Constructor.
   
    public void playSound() {
        try {
            File soundFile = new File("buttonSound.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile); // Opens an audio input stream.
            Clip clip = AudioSystem.getClip(); // Gets a sound clip resource.
            clip.open(audioIn); // Opens the audio clip & loads samples from the audio input stream.
            clip.start(); // Starts playing clip.
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Could not play sound.");
        } catch (IOException e) {
            System.out.println("Could not play sound.");
        } catch (LineUnavailableException e) {
            System.out.println("Could not play sound.");
        }
    }
}