package viv;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.Timer;

/**
 *
 * @author samuel ludueña 2021
 */
public class Ventanas {

    private String input2 = "";

    public void ventsPresS(JTextField inputField, ActionListener initialListener) {

        System.out.println("estoy en ventpress");

        do
        {

            inputField.addActionListener(initialListener);

            initialListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() throws Exception {

                            try
                            {

                                String inputt2 = inputField.getText();

                                if (inputt2.matches("S"))
                                {

                                    input2 = inputt2.toLowerCase();

                                }

                                input2 = inputt2;

                            } catch (NumberFormatException ex)
                            {

                                inputField.setText("");
                            }

                            return null;
                        }

                        @Override
                        protected void done() {

                            String textoIngresado = inputField.getText();

                            inputField.setText("");
                        }
                    };

                    worker.execute();
                }
            };

        } while (!(input2.matches("s")));



        inputField.removeActionListener(initialListener);

    }

    public void VentsPresSDentroJuego(JTextField inputField, ActionListener initialListener,
            String inpuut2, CuentaRegresiva cuentaRegresiva,
            Timer time, Escenario escenario, LinkedHashMap<String, String[]> opMap, ArrayList<String> inv, Sonidos sonid, String key, int tiempoint) {

        input2 = inpuut2;

        escenario.getCr().interrumpir();

        escenario.getTiemp().iniciarCuentaRegresiva(escenario, 0); //lo vuelve a cero pero sigue el conteo

        escenario.getTime().stop();

        //   System.out.println("estoy en ventana dentro juego");
        do
        {

            inputField.addActionListener(initialListener);

            initialListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() throws Exception {

                            try
                            {

                                String inputt2 = inputField.getText();

                                if (inputt2.matches("S"))
                                {

                                    input2 = inputt2.toLowerCase();

                                }

                                input2 = inputt2;

                            } catch (NumberFormatException ex)
                            {

                                inputField.setText("");
                            }

                            return null;
                        }

                        @Override
                        protected void done() {

                            String textoIngresado = inputField.getText();

                            inputField.setText("");
                        }
                    };

                    worker.execute();
                }
            };

        } while (!(input2.matches("s")));

   

        inputField.removeActionListener(initialListener);

    }

}
