/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viv;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Timer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author samuel ludueña 2021
 */
public class CuentaRegresiva extends Thread implements Runnable {

    private int tiempo;
    public JLabel label;
    private JPanel panel;

//private Thread contador;
    public CuentaRegresiva(int tiempo, JLabel label) {
        this.tiempo = tiempo;
        this.label = label;

    }

    @Override
    public void run() {
        while (tiempo >= 0)
        {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    label.setText(Integer.toString(tiempo));

                }

            });
            //cada segundo va disminuyendo un numero y lo pasa al label
            try
            {
                //    Thread.sleep(1000);
                //  contador.sleep(1000);

                sleep(1000);

            } catch (InterruptedException ex)
            {
                //     ex.printStackTrace();

                //   Thread.currentThread().interrupt(); // Restaurar la bandera de interrupción
                currentThread().interrupt();
                return;
            }

            tiempo--;

        }

    }

    public void interrumpir() {
        //     label.setText("");

        label.setText(Integer.toString(0));

        interrupt(); // Interrumpir el hilo

    }

}
