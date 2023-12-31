package viv;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 *
 * @author samuel ludueña 2021
 */
public class HashMapNuevo {

    private String elec;
    private LinkedList<String> titulos = new LinkedList<>();
    private ArrayList<String> m = new ArrayList<>();

    private Escenario esc;

    public String PreparHash(String part, String[] opcastr, LinkedHashMap<String, Escenario> h, String[] tit, boolean b, ArrayList<Opciones> opcl, ArrayList<Opciones> opcll, String descripcion, LinkedHashMap<String, String[]> opMap, String elecc) {

        opcll.remove(0);

        elec = elecc;

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

        System.out.println("elec en hashmap" + elec);

        return elec;

    }

}
