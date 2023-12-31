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
    private JLabel label;
    private JPanel panel;

    public CuentaRegresiva(int tiempo, JLabel label) {
        this.tiempo = tiempo;
        this.label = label;

    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
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

                sleep(1000);

            } catch (InterruptedException ex)
            {

                currentThread().interrupt();
                return;
            }

            tiempo--;

        }

    }

    public void interrumpir() {

        label.setText(Integer.toString(0));

        System.out.println("interrumpir");

        interrupt(); // Interrumpir el hilo

    }

}
