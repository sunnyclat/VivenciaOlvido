package viv;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author samuel ludueña 2021
 */
public class Inventarios {

    public void invsImborrables(Escenario esc) {

        //INVENTARIOS INVISIBLES DE ELEMENTOS POR UN LADO Y POR OTRO DE LUGARES.
        //Cazo la palabra en comillas simples para ingresarla al inventario como un ELEMENTO
        //ademas, me encargo de que si ya agarre ese elemento previamente, me cambie la descripcion del lugar si vuelvo a entrar
        // por uno que diga que ya estuve alli, y esto con la ayuda de un inventario invisible.
        if (esc.getDesc().contains("'"))
        {

            String[] invv = esc.getDesc().strip().split("'");

            esc.getInv().add(invv[1]);

            if (esc.getInvImborrableElementos().contains(invv[1]))
            {

                String pienso = "aca ya he estado";

                String repp = esc.getDesc().replace(esc.getDesc(), pienso);

                esc.setDesc(repp);

                esc.getInv().remove(invv[1]);

            }

            esc.getInvImborrableElementos().add(invv[1]);

       //     System.out.println("inv inborrable" + esc.invImborrableElementos);

        }

        //voy borrando los elementos repetibles del inventario invisible
        ArrayList<String> resultado = new ArrayList<>(new LinkedHashSet<>(esc.getInvImborrableElementos()));

        esc.setInvImborrableElementos(resultado);

        // cazo la palabra en mayuscula que representa al LUGAR donde ya estuve.
        if (esc.getDesc().matches(".*[A-ZÁÉÍÓÚÜ].*"))
        {

            List<String> pm = new ArrayList<>();

            Pattern patron = Pattern.compile("\\b[A-ZÁÉÍÓÚÜ]+\\b");
            Matcher palab = patron.matcher(esc.getDesc());

//transformo la palabra del LUGAR en minuscula para luego pasarla a un string que contenga espacios y evitar futuros inconvenientes
            while (palab.find())
            {

                esc.setPg(palab.group().toLowerCase());

                pm.add(esc.getPg());

            }

            String pp = String.join(" ", pm);

            //y la descripcion se va a reemplazar por otra que indica que "ya estuvo alli". Esto lo hago con la ayuda de un inventario invisible que refleja
            //que el elemento lo tengo y ya fue usado en ese lugar
            if (esc.getInvImborrableLugares().contains(pp))
            {

                String pienso = "aca ya he estado y use un elemento del inventario";

                String repp = esc.getDesc().replace(esc.getDesc(), pienso);

                esc.setDesc(repp);

            }
         //   System.out.println("inv inborrable lugares" + esc.invImborrableLugares);
        }

        //voy borrando los strings repetibles del inventario invisible de lugares
        ArrayList<String> resultadolug = new ArrayList<>(new LinkedHashSet<>(esc.getInvImborrableLugares()));

        esc.setInvImborrableLugares(resultadolug);

    }

}
