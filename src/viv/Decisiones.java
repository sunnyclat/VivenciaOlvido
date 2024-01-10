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

    private String palab;

    private boolean crash = false;

    private String opp1, opp2, opp3;

    private boolean okAlea;

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

        if (escenario.getOpMap().get(key)[2].contains("NOT"))
        {

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

            //      } while (!(opc >= 1 && opc <= 3) || (crash == true && opc == 3));
        } while (!(opc >= 1 && opc <= 3) && (escenario.getEll().isEmpty()) || (crash == true && opc == 3));

        crash = false;

        inputField.removeActionListener(initialListener);

        return opc;
    }

    public void elecConySinElem2(String opciones, JTextField inputField,
            JLabel label, String ellst, ActionListener initialListener, Sonidos sonid, Escenario escenario, String key) {

        label.setText(opciones);

        escenario.setEll("");

        palab = "";

        opp1 = "";
        opp2 = "";
        opp3 = "";

        String op1 = escenario.getOpMap().get(key)[0];
        String op2 = escenario.getOpMap().get(key)[1];
        String op3 = escenario.getOpMap().get(key)[2];

        System.out.println("mapa " + escenario.getOpMap().get(key)[2]);

        Pattern pat = Pattern.compile("\\b[A-ZÁÉÍÓÚÜ]+\\b");

        Matcher matcher1 = pat.matcher(op1);
        Matcher matcher2 = pat.matcher(op2);
        Matcher matcher3 = pat.matcher(op3);

        StringBuilder output1StringBuilder = new StringBuilder();
        StringBuilder output2StringBuilder = new StringBuilder();
        StringBuilder output3StringBuilder = new StringBuilder();

        while (matcher1.find())
        {
            String wordInUpperCase = matcher1.group();

            String wordInLowerCase = wordInUpperCase.toLowerCase();

            output1StringBuilder.append(wordInLowerCase).append(" ");

        }

        String out1 = output1StringBuilder.toString().trim();

        while (matcher2.find())
        {
            String wordInUpperCase = matcher2.group();

            String wordInLowerCase = wordInUpperCase.toLowerCase();

            output2StringBuilder.append(wordInLowerCase).append(" ");

        }

        String out2 = output2StringBuilder.toString().trim();

        while (matcher3.find())
        {
            String wordInUpperCase = matcher3.group();

            String wordInLowerCase = wordInUpperCase.toLowerCase();

            output3StringBuilder.append(wordInLowerCase).append(" ");

        }

        String out3 = output3StringBuilder.toString().trim();

        opp1 = out1;
        opp2 = out2;
        opp3 = out3;

        if (escenario.getOpMap().get(key)[2].contains("NOT"))
        {

            crash = true;
            opp3 = ".";
        }

        System.out.println("opp1 " + opp1);
        System.out.println("opp2 " + opp2);
        System.out.println("opp3 " + opp3);

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

                                palab = inputField.getText();

                                if (okAlea == true)
                                {

                                    escenario.setEll(palab);

                                }

                            } catch (NumberFormatException ex)
                            {

                            }

                            return null;

                        }

                        @Override
                        protected void done() {

                        }
                    };

                    worker.execute();
                }

            };

            if (!escenario.getPalabAlea().isEmpty())
            {

                palab = escenario.getEll();

            }

        } while ((!((palab.matches(opp1))
                || (palab.matches(opp2)) || (palab.matches(opp3)) && crash == false)) && escenario.getPalabAlea().isEmpty());

        System.out.println("valor palab fuera " + palab);

        escenario.setEll(palab);

        okAlea = false;
        crash = false;

        palab = "";

        escenario.setPalabAlea("");

        inputField.removeActionListener(initialListener);

        sonid.difSonidos(escenario.getEll());

    }

    public int minputmod1(String key, int modoint, JTextField inputField, ActionListener initialListener, String elle, int opcint, Boolean flagTimerr, Escenario escenario) {

        opc = 0;

        if (escenario.getOpMap().get(key)[2].contains("NOT"))
        {

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

        } while (!(opc >= 1 && opc <= 3) && (escenario.getEll().isEmpty()) || (crash == true && opc == 3));

        crash = false;

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
