/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viv;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


public class Escenario {


        
    
        
        
    private ArrayList<Opciones> opcl = new ArrayList<>();

    private ArrayList<Opciones> opcll = (ArrayList<Opciones>) opcl.clone();

    private LinkedList<String> titulos = new LinkedList<>();

    private LinkedList<String> reg = new LinkedList<>();

    private ArrayList<String> m = new ArrayList<>();

    private LinkedHashMap<String, String[]> opMap = new LinkedHashMap<>();

    private ArrayList<String> inv = new ArrayList<>();

    private String descripcion;

    private Timer timer;

    private String blanc;

    private String dec;
    
    private int mmodo;
    


    private int opc = 0;



    private String desc = "";

    private String pg = "";

    private List<String> invInborrable = new ArrayList<>();

    private List<String> invInborrableLugares = new ArrayList<>();

    private String ell;

    
    private Sonidos sonid= new Sonidos();

    private String elec;

    private String opcs;



    private String elec1;
    private String elec2;

    private Personaje p = new Personaje();

    public Escenario(String descripcion, ArrayList<Opciones> opcl) {

        this.opcl = opcl;

        this.opcll = opcl;
        this.descripcion = descripcion;

    }


    
    
    public void presentar(LinkedHashMap<String, Escenario> h, LinkedHashMap<String, Integer>  v) {

        String part;

        String[] opcastr;

        String[] tit;

        boolean error;


        boolean b = false;

    

        System.out.println('\n');

        String modo;

        //sistema de eleccion del modo de juego por el usuario en donde podemos elegir entre opciones del 0 al 1 con temporizador o escribir la palabra en mayuscula de dicha opcion tambien con temporizador
        do
        {

            modo = (JOptionPane.showInputDialog("BIENVENIDOS A VIVENCIA OLVIDO" + "\n" + "ES UN JUEGO DE SUPERVIVENCIA A TRAVES DECISIONES QUE VAYAS TOMANDO " + "\n"
                    + "Y LA PRIMERA DECISION QUE DEBERAS TOMAR ES EL MODO DE JUEGO QUE VAYAS A ELEGIR: " + "\n" + "\n" + "OPCION 1: (MODO SENCILLO ESCRIBIENDO NUMEROS) " + "\n"
                    + "OPCION 2 : (MODO ESCRIBIENDO PALABRAS. ORIGINALMENTE PENSADO DE ESTA MANERA)" 
                    + "\n" + "OPCION 3 : (SIN TIMER. PUEDES JUGAR DE MANERA TRANQUILA SIN QUE UN TIMER TE ESTE PRESIONANDO Y ESCRIBIENDO NUMEROS"));

            try
            {
                mmodo = Integer.parseInt(modo);

            } catch (NumberFormatException e)
            {
                mmodo = 0; // Valor inválido para continuar el bucle

                JOptionPane.showMessageDialog(null, "Valor no válido. Por favor, ingrese un número entero.");
            }

        } while (mmodo != 1 && mmodo != 2 && mmodo != 3  );

        System.out.println('\n');
        System.out.println("--------descripcion (escenario presentar)-------------------------");
        String descripcion = this.descripcion;

        JOptionPane.showMessageDialog(null, descripcion);

        //si queremos al principio se use el inventario, podemos hacerlo mediante el siguiente condicional e ingresando dicho elemento al inventario.
        /*   
        if (descripcion.contains("'"))
        {

            String[] invent = descripcion.strip().split("'");

            this.inv.add(invent[1]);

        }
         */
        if (p.getVida() <= 0)
        {

            System.out.println("HAS MUERTO..");

            JOptionPane.showMessageDialog(null, "HAS MUERTO.." + "\n" + "VIDA : " + p.getVida());

        }

        System.out.println("");
        System.out.println("recorriendo lista de opciones ---");

        opcll.remove(0);

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
        for (String key : h.keySet())
        {

            titulos.add(key);

        }

        titulos.remove("INICIO");
        
        

        
        
        

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

        for (String key : opMap.keySet())
        {

            key = elec;

            desc = Main.escenaMap.get(key).descripcion;

            //en esta parte cazo la palabra en comillas simples para ingresarla al inventario como un elemento
            //ademas, me encargo de que si ya agarre ese elemento previamente, me cambie la descripcion del lugar si vuelvo a entrar
            // por uno que diga que ya estuve alli, y esto con la ayuda de un inventario invisible.
            if (desc.contains("'"))
            {

                String[] invv = desc.strip().split("'");

                inv.add(invv[1]);

                if (invInborrable.contains(invv[1]))
                {

                    String pienso = "aca ya he estado";

                    String repp = desc.replace(desc, pienso);

                    desc = repp;

                    this.inv.remove(invv[1]);

                }

                invInborrable.add(invv[1]);

                System.out.println("inv inborrable" + invInborrable);

            }
            
            //voy borrando los elementos repetibles del inventario invisible
            ArrayList<String> resultado = new ArrayList<>(new LinkedHashSet<>(invInborrable));
            
            invInborrable = resultado;
            
            
            //en esta parte cazo la palabra en mayuscula que representa al lugar que contiene un lugar al que ya fui para
            //que me lo reemplace por una descripcion de que ya estuve. Esto lo hago con la ayuda de un inventario invisible que refleja
            //que el elemento lo tengo y ya fue usado en ese lugar

            if (desc.matches(".*[A-ZÁÉÍÓÚÜ].*"))
            {

                List<String> pm = new ArrayList<>();

                Pattern patron = Pattern.compile("\\b[A-ZÁÉÍÓÚÜ]+\\b");
                Matcher palab = patron.matcher(desc);

                while (palab.find())
                {

                    pg = palab.group().toLowerCase();

                    pm.add(pg);

                }

                String pp = String.join(" ", pm);

                if (invInborrableLugares.contains(pp))
                {

                    String pienso = "aca ya he estado y use un elemento del inventario";

                    String repp = desc.replace(desc, pienso);

                    desc = repp;

                }
   System.out.println("inv inborrable lugares" + invInborrableLugares);
            }
            
            
            //voy borrando los strings repetibles del inventario invisible de lugares
                ArrayList<String> resultadolug = new ArrayList<>(new LinkedHashSet<>(invInborrableLugares));
            
            invInborrableLugares = resultadolug; 
            
            
            

            if (p.getVida() > 100)
            {

                p.setVida(100);
            }

            JOptionPane.showMessageDialog(null, "SALUD DEL PERSONAJE: " + p.getVida() + "\n" + "INVENTARIO: " + inv + "\n" + desc);

            if (mmodo == 1 || mmodo ==3)
            {

                dec = ("DECIDE TU DESTINO ESCRIBIENDO EL NUMERO A ELEGIR" + "\n");

            }

            if (mmodo == 2)
            {

                dec = ("DECIDE TU DESTINO ESCRIBIENDO LA PALABRA EN" + " MAYUSCULA" + "\n");

            }

            error = true;

            while (error)
            {

                //si no existe una tercera opcion, el string ira con un punto para no generar problemas. Si no hay un punto y hay texto nos aparecera la tercera opcion a usar
                if (!(opMap.get(key)[2].contains(".")))
                {

                    blanc = "[Opcion 3] ";
                }
                
                
                
                
                
                
                
                

                String opss = "\n" + dec + "\n" + "INVENTARIO: " + inv + "\n" + "\n" + "[0pcion 1]    " + opMap.get(key)[0] + "\n" + "[Opcion 2]  " + opMap.get(key)[1] + "\n" + blanc + opMap.get(key)[2];

          
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                System.out.println("---");

                
                    if(mmodo ==1 || mmodo==2){  
                
                //frame del temporizador el cual podremos modificar a nuestro gusto en el argumento del metodo iniciar cuenta regresiva de la clase miframe
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {

                        int ti;
                        MiFrame1 miFrame = new MiFrame1();
                        miFrame.setVisible(true);

                        ti = miFrame.iniciarCuentaRegresiva(8);

                        Timer time = new Timer(ti * 1000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                // al finalizar el tiempo del frame que contiene el temporizador, este lo cerrara liberando recursos
                                miFrame.dispose();
                            }

                        });
                        time.setRepeats(false); // El temporizador solo se ejecuta una vez
                        time.start();
                    }

                });
                
          
                    
                      timer = new Timer(8000, e ->
                {

                    //al finalizar el tiempo, se cierra el frame que contiene las opciones
                    JOptionPane.getRootFrame().dispose();

                });

                timer.setRepeats(false); // El temporizador solo se ejecuta una vez
                timer.start(); // Iniciar el temporizador
                    
                    
                }

              

                //si en la descripcion se encuentra una palabra en mayuscula, quiere decir que se trata de un escenario que necesitara de ese elemento en el inventario para
                // que el usuario pueda continuar con la aventura y aparezcan las opciones siguientes.
                if (desc.matches(".*[A-ZÁÉÍÓÚÜ].*"))
                {

                    List<String> pm = new ArrayList<>();

                    Pattern patron = Pattern.compile("\\b[A-ZÁÉÍÓÚÜ]+\\b");
                    Matcher palab = patron.matcher(desc);

                    while (palab.find())
                    {

                        pg = palab.group().toLowerCase();

                        pm.add(pg);

                    }

                    String pp = String.join(" ", pm);

//si el inventario contiene el elemento que necesitamos, apareceran las siguientes opciones, sino  saldra un cartel que no tenemos el elemento y 
//se usara un array con un registro de decisiones tomadas en donde iremos a la penultima para poder proseguir con el juego
                    if (this.inv.contains(pp) || invInborrable.contains(pp))

                    {

                        invInborrableLugares.add(pp);

                        for (String elem : invInborrable)
                        {

                            if (inv.contains(elem))
                            {

                                inv.remove(elem);

                            }

                        }

                        if (mmodo == 2)
                        {
//entramos en las opciones dentro de la escena a la cual entramos con el elemento de inventario en cuestion
                            ell = JOptionPane.showInputDialog("\n" + dec + "\n" + "INVENTARIO: "
                                    + inv + "\n" + "\n" + "[0pcion 1]    "
                                    + opMap.get(key)[0] + "\n" + "[Opcion 2]  " + opMap.get(key)[1] + "\n" + blanc + opMap.get(key)[2]);

                        }

                        if (mmodo == 1 || mmodo==3 )
                        {

//entramos en las opciones dentro de la escena a la cual entramos con el elemento de inventario en cuestion
                            codMod1(key);

                        }

                    } else
                    {

                        JOptionPane.showMessageDialog(null, "EL INVENTARIO NO POSEE DICHO ELEMENTO PARA PROSEGUIR");

                        ell = reg.get(reg.size() - 2);

                        key = ell;

                        desc = Main.escenaMap.get(key).descripcion;

                    }

                } //si no existe una palabra en mayuscula en la descripcion el programa seguira normalmente 
                else

                {

                    if (mmodo == 1 || mmodo == 3)
                    {
                        //estando en el modo 1 elegido por el usuario en donde vamos a ingresar ls opciones mediante numeros del 1 al 3, al finalizar el temporizador,
// este saldra del loop e ingresaremos a uno siguiente en el cual el programa decidira aleatoriamente la opcion a ingresar

                
     
                        
                        
                        codMod1(key);
                      
                        
                       sonid.difSonidos(ell);

                    }
                    
                    

                    if (mmodo == 2)
                    {
      
                
                        ell = JOptionPane.showInputDialog("\n" + opss + "\n" + "\n" + "Escriba su eleccion ");
                   
                        
                        
                          sonid.difSonidos(ell);
                        
                        
                        

                    }

                }

                
                
                    if (mmodo == 2 || mmodo ==1) {
                        
                        
                           timer.stop(); // Detener el temporizador 
                        
                    }
             

                //condicional en donde se encierra depende del modo elegido, el sistema aleatorio de la opcion a elegir  
                if (mmodo == 1)
                {

                    if (!(timer.isRunning()) && opcs == null)
                    {
                        aleatorizacion(key);

                    }
                }

                if (mmodo == 2)
                {

                    if (!(timer.isRunning()) && ell == null)

                    {

                        aleatorizacion(key);

                    }
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

                    String er = ("no entiendo tu eleccion! ");

                    JOptionPane.showMessageDialog(null, er);

                } else
                {

                    //toma el numero almacenado en el mapa comparado con la opcion elegida y la suma a la vida o incrementa de acuerdo a la situacion
                    p.setVida(v.get(elec) + p.getVida());
                }

            }
        }
    }

    private void aleatorizacion(String key) {

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

    private void codMod1(String key) {

        do
        {
              if(mmodo==1){
                  
                      if (!(timer.isRunning()) && opcs == null)
            {

                break;
            }
                  
                  
                  
              }
        

            try
            {

                opcs = JOptionPane.showInputDialog("\n" + dec + "\n" + "INVENTARIO: " + inv + "\n" + "\n" + "[0pcion 1]    "
                        + opMap.get(key)[0] + "\n" + "[Opcion 2]  " + opMap.get(key)[1] + "\n" + blanc + opMap.get(key)[2]);

                opc = Integer.parseInt(opcs);

            } catch (NumberFormatException e)
            {

                opc = 0; // Valor inválido para continuar el bucle

                JOptionPane.showMessageDialog(null, "Valor no válido. Por favor, ingrese un número entero.");

            }

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

        } while (!(opc >= 1 && opc <= 3)); //si no se cumple esto, sale

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
