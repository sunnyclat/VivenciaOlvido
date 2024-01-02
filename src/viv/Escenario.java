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

    private Tiempo tiemp = new Tiempo();

    private int tiempoint;

    private HashMapNuevo hashmapNuev = new HashMapNuevo();

    private ArrayList<Opciones> opcl = new ArrayList<>();

    private ArrayList<Opciones> opcll = (ArrayList<Opciones>) opcl.clone();

    private LinkedList<String> titulos = new LinkedList<>();

    private LinkedList<String> reg = new LinkedList<>();

    private CuentaRegresiva cr;

    private LinkedHashMap<String, String[]> opMap = new LinkedHashMap<>();

    private ArrayList<String> inv = new ArrayList<>();

    private String descripcion;

    private Timer time;

    private String blanc;

    private String dec;

    private int opc = 0;

    private String desc = "";
    private String pg = "";

    private Inventarios objIvent = new Inventarios();

    private List<String> invImborrableElementos = new ArrayList<>();
    private List<String> invImborrableLugares = new ArrayList<>();
    private List<String> invImborrableVidasLugares = new ArrayList<>();

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

    private Ventanas ventanas = new Ventanas();
    private Decisiones decis = new Decisiones();

    private boolean dentro = false;

    private String modostr;

    private JLabel label;

    private int ti;

    public void setTime(Timer time) {
        this.time = time;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setPg(String pg) {
        this.pg = pg;
    }

    public JLabel labelTimer;

    private ActionListener initialListener;

    private boolean paso = false;

    public void setEll(String ell) {
        this.ell = ell;
    }

    public void setElec1(String elec1) {
        this.elec1 = elec1;
    }

    public void setElec2(String elec2) {
        this.elec2 = elec2;
    }

    public void setLabelTimer(JLabel labelTimer) {
        this.labelTimer = labelTimer;
    }

    public void setFlagTimer(boolean flagTimer) {
        this.flagTimer = flagTimer;
    }

    public void setInvImborrableElementos(List<String> invImborrableElementos) {
        this.invImborrableElementos = invImborrableElementos;
    }

    public void setInvImborrableLugares(List<String> invImborrableLugares) {
        this.invImborrableLugares = invImborrableLugares;
    }

    public void setInvImborrableVidasLugares(List<String> invImborrableVidasLugares) {
        this.invImborrableVidasLugares = invImborrableVidasLugares;
    }

    public void setInv(ArrayList<String> inv) {
        this.inv = inv;
    }

    public void setOpcl(ArrayList<Opciones> opcl) {
        this.opcl = opcl;
    }

    public void setOpcll(ArrayList<Opciones> opcll) {
        this.opcll = opcll;
    }

    public void setTiemp(Tiempo tiemp) {
        this.tiemp = tiemp;
    }

    public void setTiempoint(int tiempoint) {
        this.tiempoint = tiempoint;
    }

    public void setCr(CuentaRegresiva cr) {
        this.cr = cr;
    }

    public Tiempo getTiemp() {
        return tiemp;
    }

    public int getTiempoint() {
        return tiempoint;
    }

    public ArrayList<Opciones> getOpcl() {
        return opcl;
    }

    public ArrayList<Opciones> getOpcll() {
        return opcll;
    }

    public CuentaRegresiva getCr() {
        return cr;
    }

    public ArrayList<String> getInv() {
        return inv;
    }

    public Timer getTime() {
        return time;
    }

    public String getDesc() {
        return desc;
    }

    public String getPg() {
        return pg;
    }

    public List<String> getInvImborrableElementos() {
        return invImborrableElementos;
    }

    public List<String> getInvImborrableLugares() {
        return invImborrableLugares;
    }

    public List<String> getInvImborrableVidasLugares() {
        return invImborrableVidasLugares;
    }

    public String getEll() {
        return ell;
    }

    public String getElec1() {
        return elec1;
    }

    public String getElec2() {
        return elec2;
    }

    public JLabel getLabelTimer() {
        return labelTimer;
    }

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

//el metodo "PRESENTAR" dispone de dos parametros en donde en uno tenemos un hashmap de ESCENARIOS y en otro un hashmap con la VIDA que se le incrementara
//o restara al personaje en la escena de la descripcion
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
        modoint = decis.minput(inputField, labelTimer, initialListener, ell, flagTimer,
                opc, sonid, label, opcs, blanc,
                opMap, inv, dec);

        opc = modoint;

        //FIN PRIMERA VENTANA VENTANA
        //SEGUNDA VENTANA QUE SALDRA SOLO UNA VEZ TRAYENDO LA PRIMERA DESCRIPCION
        label.setText("<html>" + this.descripcion + " <br/> PRESIONE LA TECLA 'S' PARA CONTINUAR  </html> ");

        ventanas.ventsPresS(inputField, initialListener);

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

            modostr = "HAS MUERTO.." + "\n" + "VIDA : " + p.getVida();
            label.setText(modostr);
            ventanas.ventsPresS(inputField, initialListener);
        }

        //   System.out.println("");
        //   System.out.println("recorriendo lista de opciones ---");
        //PREPARACION DEL HASHMAP
        elec = hashmapNuev.PreparHash(part, opcastr, h, tit, b, opcl, opcll, descripcion, opMap, elec);

        System.out.println("antes de entrar al if " + elec);

        //---COMIENZO DEL JUEGO 
        for (String key : opMap.keySet())
        {

            key = elec;

            desc = Main.getEscenaMap().get(key).descripcion;

            //--CREACION DE INVENTARIO INVISIBLE PARA ELEMENTOS YA USADOS Y DESCRIPCION DE "YA HABER ESTADO" EN DICHO ESCENARIO DEL ELEMENTO USADO
            //cazo con la palabra en comillas simples del ELEMENTO de inventario a guardar en el inventario invisible.
            //si ya agarre ese elemento previamente, me cambia la descripcion del lugar si vuelvo a entrar
            // por uno que diga que ya estuve alli, y esto con la ayuda de un inventario invisible.
            objIvent.invsImborrables(this);

            //PARA QUE NO SOBREPASE LA VIDA DE 100
            if (p.getVida() > 100)
            {

                p.setVida(100);
            }

            System.out.println("TERCERA VENTANA");

            //--TERCERA VENTANA,EN DONDE SE MUESTRA LA SALUD ACTUAL DEL PERSONAJE, LOS ELEMENTOS DEL INVENTARIO Y LA DESCRIPCION DE LA DECISION TOMADA.(LA PRIMERA VEZ NOS MUESTRA LA PRIMERA POR DEFECTO YA QUE TODAVIA NO EMPEZAMOS A JUGAR). 
            //--LUEGO DE COMENZAR EL JUEGO SE REITERARA ENTRE LA TERCERA Y LA CUARTA.
            descInfo = "<html> SALUD DEL PERSONAJE: " + p.getVida() + "<br/>" + "INVENTARIO: " + inv + "<br/>" + desc + "<br/>" + "PRESIONE LA TECLA 'S' PARA CONTINUAR </html>";

            label.setText(descInfo);

            if (modoint == 3)
            {
                ventanas.ventsPresS(inputField, initialListener);

            }

            if ((modoint == 2 || modoint == 1) && flagTimer == true)
            {
                //   ventanas.VentsPresSDentroJuego(inputField, initialListener, input2, labelTimer, cuentaRegresiva, time, this, opMap, inv, sonid, key,tiempoint);

                ventanas.VentsPresSDentroJuego(inputField, initialListener, input2, cr, time, this, opMap, inv, sonid, key, tiempoint);

            } else
            {

                ventanas.ventsPresS(inputField, initialListener);
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

                    tiemp.timer(this, key, opMap, ell, flagTimer, inputField, label, initialListener, sonid, modoint, modoint, cr);

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
                    if (inv.contains(pp))
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

                            decis.elecConySinElem2(opciones, inputField, label, ell, initialListener, sonid, this);

                        }

                        //--INGRESO POR LA DECISION TOMADA USANDO EL ELEMENTO REQUERIDO PARA MODO DE JUEGO 1 Y 3 
                        if (modoint == 1 || modoint == 3)
                        {
                            ell = decis.elecConySinElem13(modoint, key, inputField, sonid, ell, opcs, dec, opMap, inv, label, initialListener, blanc, labelTimer, flagTimer, modoint, this);
                        }

                    } else
                    {  //--MENSAJE DE ERROR POR NO DISPONER DE ELEMENTO REQUERIDO

                        //mensaje de no disponer de elemento requerido para avanzar por la decision tomada.
                        JOptionPane.showMessageDialog(this, "EL INVENTARIO NO POSEE DICHO ELEMENTO PARA PROSEGUIR");

                        //Utilizamos un array con un registro de decisiones tomadas en donde iremos a la penultima para poder proseguir con el juego
                        ell = reg.get(reg.size() - 2);

                        key = ell;

                        //truco pidiendo ventana 2 para ocasionar un error y tener la oportunidad de progresar el juego a traves de decisiones sin uso de elemento requerido 
                        desc = Main.getEscenaMap().get(key).descripcion;

                    }

                } else

                {

                    //CUARTA VENTANA SIN USO DE ELEMENTO REQUERIDO. Entramos en las opciones dentro de la escena de la decision tomada 
                    //--PROGRESION DEL JUEGO A TRAVES DE DECISIONES SIN USO DE ELEMENTO REQUERIDO EN MODO 1 O 3
                    //si el jugador eligio el modo 1, al finalizar el contrarreloj, este eligira una opcion aleatoriamente.
                    if (modoint == 1 || modoint == 3)
                    {

                        ell = decis.elecConySinElem13(modoint, key, inputField, sonid, ell, opcs, dec, opMap, inv, label, initialListener, blanc, labelTimer, flagTimer, modoint, this);

                    }

                    //--PROGRESION DEL JUEGO A TRAVES DE DECISIONES SIN USO DE ELEMENTO REQUERIDO EN MODO 2
                    if (modoint == 2)
                    {
                        String opciones = "<html>" + opss + "<br/>" + "<br/>" + "Escriba su eleccion </html>";
                        decis.elecConySinElem2(opciones, inputField, label, ell, initialListener, sonid, this);
                    }

                }   //--FIN DE INGRESOS A TRAVES DE DECISIONES SIN Y CON ELEMENTOS REQUERIDOS

                //------------------
//se toma la opcion ingresada, y se transforma en mayuscula para que pueda compararse con el array de titulos (palabra de inicio de escena)
//y asi el usuario puede libremente escribir la palabra tanto en mayuscula como en minuscula
                String ellm = ell.toUpperCase();

                //tenemos en ell la palabra elegida por el usuario en mayuscula y por otro lado ellm con la palabra en minuscula
                // esta ultima en minuscula la guardamos en elec que es variable global
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

                if (!(invImborrableVidasLugares.contains(elec)))
                {

                    System.out.println("entre a la vida");

                    p.setVida(v.get(elec) + p.getVida());

                }

                invImborrableVidasLugares.add(elec);

                ArrayList<String> res = new ArrayList<>(new LinkedHashSet<>(invImborrableVidasLugares));

                invImborrableVidasLugares = res;

            }

        }

    }

}
