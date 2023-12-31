/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viv;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author samuel ludueña 2021
 */
public class Sonidos {

    public String puertaMaderaNoAbre = "src/sonidos/puertaMaderaNoAbre.wav";
    public String puertaAbriendose = "src/sonidos/puertaAbriendose.m4a";
    public String puertaGarageAbriendo = "src/sonidos/puertaGarageAbriendo.m4a";
    public String brokenDoor = "src/sonidos/brokenDoor.wav";

    public void reproducirSonido(String sonido) {
        try
        {
            // Cargar el archivo de audio
            File archivoSonido = new File(sonido);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(archivoSonido);

            // Crear el clip de audio
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            // Reproducir el sonido
            clip.start();

        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e)
        {
            e.printStackTrace();
        }
    }

    public void difSonidos(String ell) {

        if (!(ell.contains("patear") || ell.contains("puerta blanca")))
        {

            return;
        }

        if (ell.contains("patear"))
        {

            reproducirSonido(brokenDoor);

        }

        if (ell.contains("puerta blanca"))
        {

            reproducirSonido(puertaMaderaNoAbre);

        }

    }

}
