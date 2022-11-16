package es.udc.sistemasinteligentes5_12.ejemplo;

import es.udc.sistemasinteligentes5_12.*;

import java.util.ArrayList;
public class Estrategia4 implements EstrategiaBusqueda {

    public Estrategia4() {

    }
    public Nodo[] reconstruye_solucion(Nodo registro) {
        Nodo[] solucion = new Nodo[40];
        Nodo a = registro;
        int i = 0;
        // System.out.println("CAMINO");

        while (a.padre != null) {
            solucion[i] = a;
            //System.out.print("[ESTADO:"    +a.e+     "ACCION"+    a.a);
            a = a.padre;
            i++;
        }

        return solucion;
    }
    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception {
        ArrayList<Estado> explorados = new ArrayList<Estado>();
        Estado estadoActual = p.getEstadoInicial();
        explorados.add(estadoActual);
        Nodo actual= null;
        actual=new Nodo(estadoActual,null,null);
        int i = 1;
        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);
        while (!p.esMeta(estadoActual)){
            System.out.println((i++) + " - " + estadoActual + " no es meta");
            Accion[] accionesDisponibles = p.acciones(estadoActual);
            Nodo aux =  null;
            aux= new Nodo(null,actual,null);
            boolean modificado = false;
            for (Accion acc: accionesDisponibles) {
                Estado sc = p.result(estadoActual, acc);
                aux.setE(sc);
                aux.setA(acc);
                System.out.println((i++) + " - RESULT(" + estadoActual + ","+ acc + ")=" + sc);
                if (!explorados.contains(sc)) {
                    estadoActual = sc;
                    actual=aux;
                    System.out.println((i++) + " - " + sc + " NO explorado");
                    explorados.add(estadoActual);
                    modificado = true;
                    System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);
                    break;
                }
                else
                    System.out.println((i++) + " - " + sc + " ya explorado");
            }
            if (!modificado) throw new Exception("No se ha podido encontrar una solución");
        }
        System.out.println((i++) + " - FIN - " + estadoActual);
        return reconstruye_solucion(actual);
    }
}
