/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viv;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author samuel ludueña 2021
 */
public class Tiempo {

    Aleatorizar alea = new Aleatorizar();

    public int iniciarCuentaRegresiva(Escenario escenario, int tiempo) {

        System.out.println("iniciar cuenta ");

        escenario.setCr(new CuentaRegresiva(tiempo, escenario.getLabelTimer()));

        escenario.getCr().start();

        return tiempo;
    }

    public void timer(Escenario escenario, String key, LinkedHashMap<String, String[]> opMap, String ell,
            boolean flagTimerr, JTextField inputField, JLabel label,
            ActionListener initialListener, Sonidos sonid, int modoint, int opcint, CuentaRegresiva cuentaRegresiva) {

        //--CONTRARRELOJ EN LAS DECISIONES PARA MODO DE JUEGO 1 Y 2
        //    System.out.println("ENTRAMOS AL RELOJ SIENDO 1 O 2");
        //frame del temporizador el cual podremos modificar a nuestro gusto en el argumento del metodo iniciar cuenta regresiva de la clase miframe
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                escenario.setFlagTimer(true);

                //el numerito del temporizador que ira en decremento
                escenario.setTiempoint(iniciarCuentaRegresiva(escenario, 3));

                //tiempo que tardara en cerrarse para luego ejecutar lo que esta dentro del metodo.
                escenario.setTime(new Timer(escenario.getTiempoint() * 1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        //se performa esta accion al cerrarse
                        alea.aleatorizacion(escenario, key, modoint, opMap, ell);

                    }

                }
                ));

                escenario.getTime().setRepeats(false); // El temporizador solo se ejecuta una vez
                escenario.getTime().start();
            }

        });

    }

}
