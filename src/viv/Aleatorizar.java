/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viv;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author samuel ludueña 2021
 */
public class Aleatorizar {

    public void aleatorizacion(Escenario escenario, String key, int modoint, LinkedHashMap<String, String[]> opMap, String ell) {

        System.out.println("estoy en aleatorizacion");

        String[] words1 = opMap.get(key)[0].split("\\s+");

        String[] words2 = opMap.get(key)[1].split("\\s+");

        boolean foundUppercase = false;
        String uppercaseWord = "";

        for (String word : words1)
        {

            boolean allUppercase = true;

            for (int i = 0; i < word.length(); i++)
            {

                if (!Character.isUpperCase(word.charAt(i)))
                {
                    allUppercase = false;
                    break;
                }
            }

            if (allUppercase)
            {
                foundUppercase = true;

                escenario.setElec1(word);
                break;
            }
        }

        for (String word : words2)
        {

            boolean allUppercase = true;

            for (int i = 0; i < word.length(); i++)
            {

                if (!Character.isUpperCase(word.charAt(i)))
                {
                    allUppercase = false;
                    break;
                }
            }

            if (allUppercase)
            {
                foundUppercase = true;

                escenario.setElec2(word);
                break;
            }
        }

        Random rand = new Random();

        String[] options =
        {
            escenario.getElec1(), escenario.getElec2()
        };

        escenario.setEll(options[rand.nextInt(options.length)]); // selecciona una opción aleatoria del arreglo

        escenario.setOkAlea(true);

        escenario.setPalabAlea(escenario.getEll());

        //  System.out.println("aleator "  +  escenario.palabAlea );
    }

}
