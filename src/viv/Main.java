package viv;

import java.io.*;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Main {

    private static ArrayList<Opciones> opcionl = new ArrayList<>();

    private static Escenario escc;

    private static Escenario escenAct;

    private static Opciones opciones;

    private static LinkedHashMap<String, Escenario> escenaMap = new LinkedHashMap<>();

    private static LinkedHashMap<String, Integer> vidMap = new LinkedHashMap<>();

    private static int vida;

    private static ArrayList<Integer> vidas = new ArrayList<>();

    private static Escenario[][] escenarioMatrix;

    public static LinkedHashMap<String, Escenario> getEscenaMap() {
        return escenaMap;
    }

    public static void setEscenaMap(LinkedHashMap<String, Escenario> escenaMap) {
        Main.escenaMap = escenaMap;
    }

    public static LinkedHashMap<String, Integer> getVidMap() {
        return vidMap;
    }

    public static void setVidMap(LinkedHashMap<String, Integer> vidMap) {
        Main.vidMap = vidMap;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

        Path path = Paths.get("src/txt/vivencia.txt");

        boolean finalJuego = false;

        String contenido = Files.readString(path);

        String[] partes = contenido.split("#");  // #corta al archivo al ver este caracter y elimina dicho simbolo, copiando el contenido que viene despues del simbolo en un indice del array de partes

        for (int i = 0; i < partes.length; i++)
        {

            if (partes[i].length() > 0)
            {

                String[] escena = partes[i].split("<");

                String[] partesEscen = escena[0].split("/");

                for (int j = 1; j < escena.length; j++)
                {

                    if (escena[j].length() > 0)
                    {

                        String[] partesOpcion = escena[j].split("-");

                        opciones = new Opciones(partesOpcion[1].trim());

                        opcionl.add(opciones);

                    }

                }

                escc = new Escenario(partesEscen[1], opcionl);

                vida = Integer.parseInt(partesEscen[2].strip());

                vidas.add(vida);

                escenaMap.put(partesEscen[0], escc);

                for (int x = 0; x < vidas.size(); x++)
                {

                    vidMap.put(partesEscen[0], vidas.get(x));

                }

            }

        }

        if (escenaMap.containsKey("INICIO"))
        {

            System.out.println("-------");
            escenAct = escenaMap.get("INICIO");

        }

        escenAct.presentar(escenaMap, vidMap);

    }

}
