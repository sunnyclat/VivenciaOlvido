package viv;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.Timer;

/**
 *
 * @author samuel ludueña 2021
 */
public class Decisiones {

    private int opc = 0;

    private String input2 = "";

    private boolean crash = false;

    private boolean flagTimer = false;

    private Tiempo tiemp = new Tiempo();

    public int minput(JTextField inputField, JLabel labelTimer, ActionListener initialListener, String ell, boolean flagTimer,
            int opcint, Sonidos sonid, JLabel label, String opcs, String blanc,
            LinkedHashMap<String, String[]> opMap, ArrayList<String> inv, String dec) {

        opc = 0;

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

                                opc = Integer.parseInt(inputField.getText());

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

        } while (!(opc >= 1 && opc <= 3));

        inputField.setText("");

        inputField.removeActionListener(initialListener);

        return opc;
    }

    public int minputmod3(JTextField inputField, JLabel labelTimer, ActionListener initialListener, String ell, boolean flagTimer,
            int opcint, Sonidos sonid, JLabel label, String opcs, String blanc,
            LinkedHashMap<String, String[]> opMap, ArrayList<String> inv, String dec, Escenario escenario, String key) {

        opc = 0;


        /*
        ArrayList<String> po = new ArrayList<>();

        escenario.setEll(opMap.get(key)[2]);

        Pattern pat = Pattern.compile("\\b[A-ZÁÉÍÓÚÜ]+\\b");
        Matcher palabr = pat.matcher(escenario.getEll());

        String pq = "";

        while (palabr.find())
        {

            pq = palabr.group().toLowerCase();

            po.add(pq);

        }

        String ppp = String.join(" ", po);

        escenario.setEll(ppp);

        po.remove(pq);
         */
        if (escenario.getOpMap().get(key)[2].contains("."))
        {

            System.out.println("crash");

            crash = true;

        }

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

                                opc = Integer.parseInt(inputField.getText());

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

        } while (!(opc >= 1 && opc <= 3) || (crash == true && opc == 3));

        crash = false;

        inputField.setText("");

        inputField.removeActionListener(initialListener);

        return opc;
    }

    public void elecConySinElem2(String opciones, JTextField inputField,
            JLabel label, String ellst, ActionListener initialListener, Sonidos sonid, Escenario escenario) {

        label.setText(opciones);

        escenario.setEll("");

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

                                escenario.setEll(inputField.getText());

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

        } while ((escenario.getEll().isEmpty()));

        inputField.removeActionListener(initialListener);

        sonid.difSonidos(escenario.getEll());

    }

    public int minputmod1(String key, int modoint, JTextField inputField, ActionListener initialListener, String elle, int opcint, Boolean flagTimerr, Escenario escenario) {

        opc = 0;

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

                                opc = Integer.parseInt(inputField.getText());

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

        } while (!(opc >= 1 && opc <= 3) && (escenario.getEll().isEmpty()));

        flagTimer = true;

        inputField.setText("");

        inputField.removeActionListener(initialListener);

        return opc;
    }

    public String elecConySinElem13(int modoint, String key, JTextField inputField, Sonidos sonid, String ellst, String opcs, String dec,
            LinkedHashMap<String, String[]> opMap, ArrayList<String> inv,
            JLabel label, ActionListener initialListener, String blanc, JLabel labelTimer, boolean flagTimer, int opcint, Escenario escenario) {

        escenario.setEll(codMod13(key, modoint, inputField, opcs, dec,
                opMap, inv, blanc,
                label, initialListener, labelTimer, flagTimer, opcint, sonid, escenario));

        sonid.difSonidos(escenario.getEll());

        return escenario.getEll();

    }

    public String codMod13(String key, int modoint, JTextField inputField, String opcs, String dec,
            LinkedHashMap<String, String[]> opMap, ArrayList<String> inv, String blanc,
            JLabel label, ActionListener initialListener, JLabel labelTimer, boolean flagTimer, int opcint, Sonidos sonid, Escenario escenario) {

        escenario.setEll("");

        do
        {

            opcs = "<html> <br/>" + dec + "<br/>" + "INVENTARIO: " + inv + "<br/>" + "<br/>" + "[0pcion 1]    "
                    + opMap.get(key)[0] + "<br/>" + "[Opcion 2]  " + opMap.get(key)[1] + "<br/>" + blanc + opMap.get(key)[2] + "</html>";

            label.setText(opcs);

            if (modoint == 3)
            {

                opc = minputmod3(inputField, labelTimer, initialListener, escenario.getEll(), flagTimer, opcint, sonid, label, opcs, blanc, opMap, inv, dec, escenario, key);
            }

            if (modoint == 1)
            {

                opc = minputmod1(key, modoint, inputField, initialListener, escenario.getEll(), opcint, flagTimer, escenario);

            }

            inputField.removeActionListener(initialListener);

            if (opc == 1)
            {

                ArrayList<String> po = new ArrayList<>();
                escenario.setEll(opMap.get(key)[0]);

                Pattern pat = Pattern.compile("\\b[A-ZÁÉÍÓÚÜ]+\\b");
                Matcher palabr = pat.matcher(escenario.getEll());

                String pq = "";

                while (palabr.find())
                {

                    pq = palabr.group().toLowerCase();

                    po.add(pq);

                }

                String ppp = String.join(" ", po);

                escenario.setEll(ppp);

                po.remove(pq);
            }

            if (opc == 2)
            {

                ArrayList<String> po = new ArrayList<>();

                escenario.setEll(opMap.get(key)[1]);

                Pattern pat = Pattern.compile("\\b[A-ZÁÉÍÓÚÜ]+\\b");
                Matcher palabr = pat.matcher(escenario.getEll());

                String pq = "";

                while (palabr.find())
                {

                    pq = palabr.group().toLowerCase();

                    po.add(pq);

                }

                String ppp = String.join(" ", po);

                escenario.setEll(ppp);

                po.remove(pq);

            }

            if (opc == 3)
            {

                ArrayList<String> po = new ArrayList<>();

                escenario.setEll(opMap.get(key)[2]);

                Pattern pat = Pattern.compile("\\b[A-ZÁÉÍÓÚÜ]+\\b");
                Matcher palabr = pat.matcher(escenario.getEll());

                String pq = "";

                while (palabr.find())
                {

                    pq = palabr.group().toLowerCase();

                    po.add(pq);

                }

                String ppp = String.join(" ", po);

                escenario.setEll(ppp);

                po.remove(pq);

            }

        } while (!(opc >= 1 && opc <= 3) && (escenario.getEll().isEmpty())); //si no se cumple esto, sale

        return escenario.getEll();
    }

}
