/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viv;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MiFrame1 extends JFrame {

    private JLabel label;
private JPanel panel;


    public MiFrame1() {
        
        
         setSize(500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(0);
        
        panel = new JPanel();
        panel.setBounds(100, 100, 200, 50);

          
     
        getContentPane().add(panel);
        
        
        setTitle("Mi JFrame");
        setSize(100, 100);
      //  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label = new JLabel();
        
     
        add(label);
    }

    public int iniciarCuentaRegresiva(int tiempo) {
        CuentaRegresiva cuentaRegresiva = new CuentaRegresiva(tiempo, label);
        
        Thread hiloCuentaRegresiva = new Thread(cuentaRegresiva);
        
        hiloCuentaRegresiva.start();
        
 

        return tiempo;
    }
    
    
 
    public void detencuenta(){
        
        
        
    }
    
    
    
}