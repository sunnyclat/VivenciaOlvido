package viv;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.util.LinkedHashSet;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class Escenario extends JFrame {

    private ArrayList<Opciones> opcl = new ArrayList<>();

    private ArrayList<Opciones> opcll = (ArrayList<Opciones>) opcl.clone();

    private LinkedList<String> titulos = new LinkedList<>();

    private LinkedList<String> reg = new LinkedList<>();

    private CuentaRegresiva cuentaRegresiva;

    private ArrayList<String> m = new ArrayList<>();

    private LinkedHashMap<String, String[]> opMap = new LinkedHashMap<>();

    private ArrayList<String> inv = new ArrayList<>();

    private String descripcion;

    private Timer timer;

    private Timer time;

    private String blanc;

    private String dec;

    //private int modoint;
    private int opc = 0;

    private String desc;
    private String pg = "";

    private List<String> invImborrableElementos = new ArrayList<>();
    private List<String> invImborrableLugares = new ArrayList<>();

    private Thread hiloCuentaRegresiva;

    private String ell = "";

    private Sonidos sonid = new Sonidos();

    private String elec;

    private String opcs;

    private String opss;

    private String elec1;
    private String elec2;

    private String descInfo;

    private int descInt;

    private boolean flagTimer = false;

    private String inputdec;

    private Personaje p = new Personaje();

    private JTextArea chatArea;
    private JTextField inputField;
    private String input2 = "";
    private int inputint2;
    private String input3 = "";

    private boolean dentro = false;

    private String modostr;

    private JLabel label;

    private int ti;

    private JLabel labelTimer;

    private ActionListener initialListener;

    private boolean paso = false;

    public Escenario(String descripcion, ArrayList<Opciones> opcl) {

        //ell= un string que incorpora la eleccion tomada por el usuario o resultado de aleatorizacion (en el modo 2 la info se guarda aca sin conversion de numero a string previa)
//opc= un int que incorpora la eleccion tomada por el usuario (nunca por aleatorizacion). (en el modo 3 se guarda aca y despues se transforma a ell (string)). 
//( en el modo 1, se guarda tambien aca la)
        this.opcl = opcl;

        this.opcll = opcl;
        this.descripcion = descripcion;
        setTitle("Vivencia olvido");

        inputField = new JTextField();

        label = new JLabel("Texto por defecto");

        labelTimer = new JLabel();

        labelTimer.setPreferredSize(new Dimension(400, 300));

        Font labelFont = label.getFont();

        Font labelFontTimer = labelTimer.getFont();

        label.setFont(new Font(labelFont.getName(), Font.PLAIN, 15)); // Tamaño

        labelTimer.setFont(new Font(labelFontTimer.getName(), Font.PLAIN, 60)); // Tamaño

        setResizable(false);

        setLayout(new BorderLayout());

        inputField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
                {

                    System.exit(0); // Cierra la ventana cuando se presiona la tecla "Escape"

                }
            }

        });

        add(label, BorderLayout.NORTH);

        add(labelTimer, BorderLayout.AFTER_LINE_ENDS);

        add(inputField, BorderLayout.SOUTH);

        // Establece el tamaño mínimo de la ventana para evitar que sea demasiado pequeña
        setMinimumSize(new Dimension(800, 600));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

    }

    private void minput() {

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
    }

    private void minputmod1(String key, int modoint) {

        opc = 0;

        System.out.println("estoy en minputmod1");

        //      timer(key, modoint);
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

        } while (!(opc >= 1 && opc <= 3) && (ell.isEmpty()));

        flagTimer = true;

        //    System.out.println("sali de inputmod1");
        inputField.setText("");

        inputField.removeActionListener(initialListener);
    }

    private void elecConySinElem13(int modoint, String key) {

        codMod13(key, modoint);
        sonid.difSonidos(ell);

    }

    private void elecConySinElem2(String opciones) {

        label.setText(opciones);

        ell = "";

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

                                ell = inputField.getText();

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

        } while ((ell.isEmpty()));

        // inputField.setText("");
        inputField.removeActionListener(initialListener);

        sonid.difSonidos(ell);

    }

    private void aleatorizacion(String key, int modoint) {

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

                elec1 = word;
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

                elec2 = word;
                break;
            }
        }

        Random rand = new Random();

        String[] options =
        {
            elec1, elec2
        };

        ell = options[rand.nextInt(options.length)]; // selecciona una opción aleatoria del arreglo

    }

    //--ENTRAMOS EN LA ESCENA DE LA DECISION TOMADA Y CONVIERTE LAS DECISIONES EN NUMEROS A PALABRAS (KEYS)
    private void codMod13(String key, int modoint) {

        ell = "";

        do
        {

            System.out.println("estoy en codmod");

            opcs = "<html> <br/>" + dec + "<br/>" + "INVENTARIO: " + inv + "<br/>" + "<br/>" + "[0pcion 1]    "
                    + opMap.get(key)[0] + "<br/>" + "[Opcion 2]  " + opMap.get(key)[1] + "<br/>" + blanc + opMap.get(key)[2];

            label.setText(opcs);

            if (modoint == 3)
            {

                minput();
            }

            if (modoint == 1)
            {

                minputmod1(key, modoint);

            }

            System.out.println("estoy en codmod luego de modint");

            //   inputField.setText("");
            inputField.removeActionListener(initialListener);

            if (opc == 1)
            {

                ArrayList<String> po = new ArrayList<>();

                ell = opMap.get(key)[0];

                Pattern pat = Pattern.compile("\\b[A-ZÁÉÍÓÚÜ]+\\b");
                Matcher palabr = pat.matcher(ell);

                String pq = "";

                while (palabr.find())
                {

                    pq = palabr.group().toLowerCase();

                    po.add(pq);

                }

                String ppp = String.join(" ", po);

                ell = ppp;

                po.remove(pq);
            }

            if (opc == 2)
            {

                ArrayList<String> po = new ArrayList<>();

                ell = opMap.get(key)[1];

                Pattern pat = Pattern.compile("\\b[A-ZÁÉÍÓÚÜ]+\\b");
                Matcher palabr = pat.matcher(ell);

                String pq = "";

                while (palabr.find())
                {

                    pq = palabr.group().toLowerCase();

                    po.add(pq);

                }

                String ppp = String.join(" ", po);

                ell = ppp;

                po.remove(pq);

            }

            if (opc == 3)
            {

                ArrayList<String> po = new ArrayList<>();

                if ((opMap.get(key)[2].contains(".")))
                {

                    break;
                }

                ell = opMap.get(key)[2];

                Pattern pat = Pattern.compile("\\b[A-ZÁÉÍÓÚÜ]+\\b");
                Matcher palabr = pat.matcher(ell);

                String pq = "";

                while (palabr.find())
                {

                    pq = palabr.group().toLowerCase();

                    po.add(pq);

                }

                String ppp = String.join(" ", po);

                ell = ppp;

                po.remove(pq);

            }

        } while (!(opc >= 1 && opc <= 3) && (ell.isEmpty())); //si no se cumple esto, sale

    }

    private void VentsPresSDentroJuego() {

        System.out.println("dentro");

        cuentaRegresiva.interrumpir();

        iniciarCuentaRegresiva(0);

        time.stop();

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

        inputField.setText("");

        inputField.removeActionListener(initialListener);

    }

    private void ventsPresS() {

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

        inputField.setText("");

        inputField.removeActionListener(initialListener);
    }

    private void PreparHash(String part, String[] opcastr, LinkedHashMap<String, Escenario> h, String[] tit, boolean b) {

        opcll.remove(0);
        //---PREPARACION DE HASHMAP DEFINITIVO CON TITULOS Y EVENTOS
        //tomo las opciones y las voy insertando temporalmente en un string con la descripcion para luego insertarlas en un arraylist
        for (int i = 0; i < opcll.size(); i++)
        {

            part = opcll.get(i).descripcion;

            m.add(part);

        }

        //este arraylist con las opciones pasa a un array comun
        opcastr = new String[m.size()];

        opcastr = m.toArray(opcastr);

        //array de opciones a tomar para comprobar que todo esta bien en consola
        /*
        //opciones en array de string
        for (int i = 0; i < opcastr.length; i++) {

            System.out.println(opcastr[i]);
        }
         */
        //reparte las key del hash map (el nombre de los titulos) al arraylist de titulos
        for (String key : h.keySet())
        {

            titulos.add(key);

        }

        //para evitar errores, se remueve el primer elemento del array
        titulos.remove("INICIO");

        //transformamos el arraylist a un array llamado tit
        tit = new String[titulos.size()];

        tit = titulos.toArray(tit);

        //array de titulos para comprobar que esta todo en orden
        /*
        //titulos en array de string
        for (int i = 0; i < tit.length; i++)
        {

            System.out.println(tit[i]);
        }

        
         */
        //recorro el array tit que es de titulos y los ordeno por suceso para meterlo en otro hashmap
        int x = 0;

        for (int i = 0; i < tit.length; i++)
        {

            String[] val =
            {
                opcastr[x], opcastr[x + 1], opcastr[x + 2]
            };

            opMap.put(tit[i], val);

            x += 3;

        }

        if (b == false)
        {

            elec = tit[0];

            b = true;

        }

    }

    private void invsImborrables() {

        //INVENTARIOS INVISIBLES DE ELEMENTOS POR UN LADO Y POR OTRO DE LUGARES.
        //Cazo la palabra en comillas simples para ingresarla al inventario como un ELEMENTO
        //ademas, me encargo de que si ya agarre ese elemento previamente, me cambie la descripcion del lugar si vuelvo a entrar
        // por uno que diga que ya estuve alli, y esto con la ayuda de un inventario invisible.
        if (desc.contains("'"))
        {

            String[] invv = desc.strip().split("'");

            inv.add(invv[1]);

            if (invImborrableElementos.contains(invv[1]))
            {

                String pienso = "aca ya he estado";

                String repp = desc.replace(desc, pienso);

                desc = repp;

                this.inv.remove(invv[1]);

            }

            invImborrableElementos.add(invv[1]);

            System.out.println("inv inborrable" + invImborrableElementos);

        }

        //voy borrando los elementos repetibles del inventario invisible
        ArrayList<String> resultado = new ArrayList<>(new LinkedHashSet<>(invImborrableElementos));

        invImborrableElementos = resultado;

        // cazo la palabra en mayuscula que representa al LUGAR donde ya estuve.
        if (desc.matches(".*[A-ZÁÉÍÓÚÜ].*"))
        {

            List<String> pm = new ArrayList<>();

            Pattern patron = Pattern.compile("\\b[A-ZÁÉÍÓÚÜ]+\\b");
            Matcher palab = patron.matcher(desc);

//transformo la palabra del LUGAR en minuscula para luego pasarla a un string que contenga espacios y evitar futuros inconvenientes
            while (palab.find())
            {

                pg = palab.group().toLowerCase();

                pm.add(pg);

            }

            String pp = String.join(" ", pm);

            //y la descripcion se va a reemplazar por otra que indica que "ya estuvo alli". Esto lo hago con la ayuda de un inventario invisible que refleja
            //que el elemento lo tengo y ya fue usado en ese lugar
            if (invImborrableLugares.contains(pp))
            {

                String pienso = "aca ya he estado y use un elemento del inventario";

                String repp = desc.replace(desc, pienso);

                desc = repp;

            }
            System.out.println("inv inborrable lugares" + invImborrableLugares);
        }

        //voy borrando los strings repetibles del inventario invisible de lugares
        ArrayList<String> resultadolug = new ArrayList<>(new LinkedHashSet<>(invImborrableLugares));

        invImborrableLugares = resultadolug;

    }

    private int iniciarCuentaRegresiva(int tiempo) {

        cuentaRegresiva = new CuentaRegresiva(tiempo, labelTimer);

        //  hiloCuentaRegresiva = new Thread(cuentaRegresiva);
        //    hiloCuentaRegresiva.start();
        cuentaRegresiva.start();

        return tiempo;
    }

    private void timer(String key, int modoint) {

        //--CONTRARRELOJ EN LAS DECISIONES PARA MODO DE JUEGO 1 Y 2
        //    System.out.println("ENTRAMOS AL RELOJ SIENDO 1 O 2");
        //frame del temporizador el cual podremos modificar a nuestro gusto en el argumento del metodo iniciar cuenta regresiva de la clase miframe
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                /*       
                if(!ell.isEmpty() && flagTimer==true){
                                   cuentaRegresiva.interrumpir();
                    
                }
                 */
                flagTimer = true;

                int ti;

                //el numerito del temporizador que ira en decremento
                ti = iniciarCuentaRegresiva(4);

                //   Timer time = new Timer(ti * 1000, new ActionListener() {
                //tiempo que tardara en cerrarse para luego ejecutar lo que esta dentro del metodo.
                time = new Timer(ti * 1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        //se performa esta accion al cerrarse
                        aleatorizacion(key, modoint);

                    }

                });
                time.setRepeats(false); // El temporizador solo se ejecuta una vez
                time.start();
            }

        });

        //esto declara el delay del timer. Solo eso.  
        //   timer = new Timer(4000, e ->
        //    {
        //al finalizar el tiempo, se cierra el frame que contiene las opciones
        //       labelTimer.setText("");
        //     timer.stop();
        //   });
        //     timer.setRepeats(false); // El temporizador solo se ejecuta una vez
        //    timer.start(); // Iniciar el temporizador
    }

    public void presentar(LinkedHashMap<String, Escenario> h, LinkedHashMap<String, Integer> v) {

        String part = "";

        String[] opcastr =
        {
        };

        String[] tit =
        {
        };

        int modoint = 0;
        boolean error;

        boolean b = false;

        System.out.println('\n');

        //--- PRIMERA VENTANA CON INTRODUCCION DE MODOS DE JUEGOS
        //sistema de eleccion del modo de juego por el usuario en donde podemos elegir entre opciones del 0 al 1 con temporizador o escribir la palabra en mayuscula de dicha opcion tambien con temporizador
        System.out.println("PRIMERA VENTANA");

        setVisible(true);

        modostr = ("<html>BIENVENIDOS A VIVENCIA OLVIDO<br/>"
                + "ES UN JUEGO DE SUPERVIVENCIA A TRAVES DECISIONES QUE VAYAS TOMANDO<br/>"
                + "Y LA PRIMERA DECISION QUE DEBERAS TOMAR ES EL MODO DE JUEGO QUE VAYAS A ELEGIR:<br/><br/>"
                + "OPCION 1: (MODO SENCILLO ESCRIBIENDO NUMEROS)<br/>"
                + "OPCION 2: (MODO ESCRIBIENDO PALABRAS. ORIGINALMENTE PENSADO DE ESTA MANERA)<br/>"
                + "OPCION 3: (SIN TIMER. PUEDES JUGAR DE MANERA TRANQUILA SIN QUE UN TIMER TE ESTE PRESIONANDO Y ESCRIBIENDO NUMEROS<br/>"
                + "SI EN ALGUN MOMENTO DESEAS SALIR: TECLA ESCAPE</html>");

        label.setText(modostr);

        //PRIMERA VENTANA. INPUT PARA ELEGIR EL MODO DE JUEGO 
        minput();

        modoint = opc;

        //FIN PRIMERA VENTANA VENTANA
        //SEGUNDA VENTANA QUE SALDRA SOLO UNA VEZ TRAYENDO LA PRIMERA DESCRIPCION
        label.setText("<html>" + this.descripcion + " <br/> PRESIONE LA TECLA 'S' PARA CONTINUAR  </html> ");

        ventsPresS();

        //FIN SEGUNDA VENTANA
        //si queremos al principio se use el inventario, podemos hacerlo mediante el siguiente condicional e ingresando dicho elemento al inventario.
        /*   
        if (descripcion.contains("'"))
        {

            String[] invent = descripcion.strip().split("'");

            this.inv.add(invent[1]);

        }
        
         */
        //---MENSAJE DE MUERTE DEL JUGADOR
        //vida del jugador que cuando ya no tiene mas, es porque murio
        if (p.getVida() <= 0)
        {

            //   System.out.println("HAS MUERTO..");
            //        JOptionPane.showMessageDialog(this, "HAS MUERTO.." + "\n" + "VIDA : " + p.getVida());
            //      modostr = "HAS MUERTO.." + "\n" + "VIDA : " + p.getVida();
            //    chatArea.setText(modostr);
        }

        //   System.out.println("");
        //   System.out.println("recorriendo lista de opciones ---");
        //PREPARACION DEL HASHMAP
        PreparHash(part, opcastr, h, tit, b);

        //---COMIENZO DEL JUEGO 
        for (String key : opMap.keySet())
        {

            key = elec;

            desc = Main.escenaMap.get(key).descripcion;

            //--CREACION DE INVENTARIO INVISIBLE PARA ELEMENTOS YA USADOS Y DESCRIPCION DE "YA HABER ESTADO" EN DICHO ESCENARIO DEL ELEMENTO USADO
            //cazo con la palabra en comillas simples del ELEMENTO de inventario a guardar en el inventario invisible.
            //si ya agarre ese elemento previamente, me cambia la descripcion del lugar si vuelvo a entrar
            // por uno que diga que ya estuve alli, y esto con la ayuda de un inventario invisible.
            invsImborrables();

            //--FUNCIONAMIENTO DEL INCREMENTO O PERDIDA DE VIDA DEL JUGADOR
            if (p.getVida() > 100)
            {

                p.setVida(100);
            }

            System.out.println("TERCERA VENTANA");

            //--TERCERA VENTANA,EN DONDE SE MUESTRA LA SALUD ACTUAL DEL PERSONAJE, LOS ELEMENTOS DEL INVENTARIO Y LA DESCRIPCION DE LA DECISION TOMADA.(LA PRIMERA VEZ NOS MUESTRA LA PRIMERA POR DEFECTO YA QUE TODAVIA NO EMPEZAMOS A JUGAR). 
            //--LUEGO DE COMENZAR EL JUEGO SE REITERARA ENTRE LA TERCERA Y LA CUARTA.
            descInfo = "<html> SALUD DEL PERSONAJE: " + p.getVida() + "<br/>" + "INVENTARIO: " + inv + "<br/>" + desc + "<br/>" + "PRESIONE LA TECLA 'S' PARA CONTINUAR </html>";

            label.setText(descInfo);

            //                  ventsPresS();
            if (modoint == 3)
            {
                ventsPresS();

            }

            if ((modoint == 2 || modoint == 1) && flagTimer == true)
            {
                VentsPresSDentroJuego();

            } else
            {

                ventsPresS();
            }

            //FIN TERCERA VENTANA
            // forma parte de la cuarta ventana que vendra luego
            if (modoint == 1 || modoint == 3)
            {

                dec = ("DECIDE TU DESTINO ESCRIBIENDO EL NUMERO A ELEGIR" + "\n");

            }

            if (modoint == 2)
            {

                dec = ("DECIDE TU DESTINO ESCRIBIENDO LA PALABRA EN" + " MAYUSCULA" + "\n");

            }

            //flag para que no termine el buble 
            error = true;

            while (error)
            {

                //--ANTES DE MOSTRAR LAS DECISIONES A TOMAR, PREPARAMOS EL CODIGO POR SI HAY UNA TERCERA DECISIONES A ELEGIR
                //si no existe una tercera opcion, el string ira con un punto para no generar problemas. Si no hay un punto y hay texto nos aparecera la tercera opcion a usar
                if (!(opMap.get(key)[2].contains(".")))
                {

                    blanc = "[Opcion 3] ";
                }

                //--SIGUIENDO CON LA CUARTA VENTANA, DECISIONES A TOMAR POR EL JUGADOR.     
                opss = "<html>" + "<br/>" + dec + "<br/>" + "INVENTARIO: " + inv + "<br/>" + "<br/>" + "[0pcion 1]    " + opMap.get(key)[0] + "<br/>" + "[Opcion 2]  " + opMap.get(key)[1] + "<br/>" + blanc + opMap.get(key)[2] + "</html>";

                //--CONTRARRELOJ EN LAS DECISIONES PARA MODO DE JUEGO 1 Y 2
                if (modoint == 1 || modoint == 2)
                {
                    timer(key, modoint);
                }
                //--PROGRESION DEL JUEGO ANUNCIANDO USO DE ELEMENTO NECESARIO PARA AVANZAR A TRAVES DE DICHA DECISION TOMADA
//si en la descripcion se encuentra una palabra en mayuscula, esta palabra sera la de un ELEMENTO que necesitamos tener en el inventario para poder continuar a traves del LUGAR

                if (desc.matches(".*[A-ZÁÉÍÓÚÜ].*"))
                {

                    List<String> pm = new ArrayList<>();

                    Pattern patron = Pattern.compile("\\b[A-ZÁÉÍÓÚÜ]+\\b");
                    Matcher palab = patron.matcher(desc);

                    //meto la palabra del ELEMENTO encontrado en el LUGAR y lo meto en un string con espacios para evitar inconvenientes futuros
                    while (palab.find())
                    {

                        pg = palab.group().toLowerCase();

                        pm.add(pg);

                    }

                    String pp = String.join(" ", pm);

//si el INVENTARIO CONTIENE el ELEMENTO que necesitamos, proseguiremos con el juego, sino  saldra un cartel que no tenemos el elemento. 
                    //      if (this.inv.contains(pp) || invInborrable.contains(pp))
                    if (this.inv.contains(pp))
                    {

                        invImborrableLugares.add(pp);

                        for (String elem : invImborrableLugares)
                        {

                            if (inv.contains(elem))
                            {

                                inv.remove(elem);

                            }

                        }

                        //CUARTA VENTANA PARA USO DE ELEMENTO REQUERIDO. Entramos en las opciones dentro de la escena de la decision tomada 
                        //--INGRESO POR LA DECISION TOMADA USANDO EL ELEMENTO REQUERIDO PARA MODO DE JUEGO 2
                        //entramos en las opciones dentro de la escena a la cual entramos con el elemento de inventario en cuestion
                        if (modoint == 2)
                        {

                            String opciones = "<html>" + dec + "\n" + "INVENTARIO: "
                                    + inv + "\n" + "\n" + "[0pcion 1]    "
                                    + opMap.get(key)[0] + "\n" + "[Opcion 2]  " + opMap.get(key)[1] + "\n" + blanc + opMap.get(key)[2] + "</html>";

                            elecConySinElem2(opciones);

                        }

                        //--INGRESO POR LA DECISION TOMADA USANDO EL ELEMENTO REQUERIDO PARA MODO DE JUEGO 1 Y 3 
                        if (modoint == 1 || modoint == 3)
                        {
                            elecConySinElem13(modoint, key);
                        }
                    } else
                    {  //--MENSAJE DE ERROR POR NO DISPONER DE ELEMENTO REQUERIDO

                        //mensaje de no disponer de elemento requerido para avanzar por la decision tomada.
                        JOptionPane.showMessageDialog(this, "EL INVENTARIO NO POSEE DICHO ELEMENTO PARA PROSEGUIR");

                        //Utilizamos un array con un registro de decisiones tomadas en donde iremos a la penultima para poder proseguir con el juego
                        ell = reg.get(reg.size() - 2);

                        key = ell;

                        //truco pidiendo ventana 2 para ocasionar un error y tener la oportunidad de progresar el juego a traves de decisiones sin uso de elemento requerido 
                        desc = Main.escenaMap.get(key).descripcion;

                    }

                } else

                {

                    //CUARTA VENTANA SIN USO DE ELEMENTO REQUERIDO. Entramos en las opciones dentro de la escena de la decision tomada 
                    //--PROGRESION DEL JUEGO A TRAVES DE DECISIONES SIN USO DE ELEMENTO REQUERIDO EN MODO 1 O 3
                    //si el jugador eligio el modo 1, al finalizar el contrarreloj, este eligira una opcion aleatoriamente.
                    if (modoint == 1 || modoint == 3)
                    {
                        elecConySinElem13(modoint, key);
                    }

                    //--PROGRESION DEL JUEGO A TRAVES DE DECISIONES SIN USO DE ELEMENTO REQUERIDO EN MODO 2
                    if (modoint == 2)
                    {
                        String opciones = "<html>" + opss + "\n" + "\n" + "Escriba su eleccion </html>";
                        elecConySinElem2(opciones);
                    }

                }   //--FIN DE INGRESOS A TRAVES DE DECISIONES SIN Y CON ELEMENTOS REQUERIDOS

                //------------------
                //--DETENCION DEL CONTRARRELOJ PARA MODOS 1 Y 2
                if (modoint == 2 || modoint == 1)
                {

                    //       timer.stop(); // Detener el temporizador 
                }

//se toma la opcion ingresada, y se transforma en mayuscula para que pueda compararse con el array de titulos (palabra de inicio de escena)
//y asi el usuario puede libremente escribir la palabra tanto en mayuscula como en minuscula
                String ellm = ell.toUpperCase();

                elec = ellm;

                //registro de elecciones tomadas las cuales me ayudaran a volver de una opcion en donde no teniamos dicho elemento para proseguir para asi
                // que el usuario pueda continuar con otras opciones posibles.
                reg.add(elec);

                //si el mapa contiene una palabra que no es igual a la palabra ingresada por el usuario no entrada en esta opcion
                // y saltara en la siguiente en donde nos notifica no entender la opcion ingresada.
                if ((opMap.get(key)[0]).contains(ellm) || (opMap.get(key)[1]).contains(ellm) || (opMap.get(key)[2]).contains(ellm))
                {

                    //al ingresarse una opcion correcta, se desactiva el flag para que el loop while vuelva al principio y siga el programa.
                    error = false;

                }

                if (error)

                {

                    if (modoint == 2)
                    {
                        String er = ("continuamos.. ");
                        //      JOptionPane.showMessageDialog(this, er);  

                    }

                } else
                {

                    //toma el numero almacenado en el mapa comparado con la opcion elegida y la suma a la vida o incrementa de acuerdo a la situacion
                    p.setVida(v.get(elec) + p.getVida());
                }

            }

        }

    }

}
