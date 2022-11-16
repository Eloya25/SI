package es.udc.sistemasinteligentes5_12.magicSquare;

import es.udc.sistemasinteligentes5_12.*;
import es.udc.sistemasinteligentes5_12.ejemplo.Nodo;

import java.util.ArrayList;
public class EstrategiaBusquedaAnchura implements EstrategiaBusqueda {

    public EstrategiaBusquedaAnchura() {

    }
    public Nodo[] reconstruye_solucion(Nodo registro) {
        Nodo[] solucion = new Nodo[40];
        Nodo a = registro;
        int i = 0;
        // System.out.println("CAMINO");

        while (a.getPadre() != null) {
            solucion[i] = a;
            a = a.getPadre();
            i++;                                          //reconstruir el camino de la solucion
        }

        return solucion;
    }
    public boolean nocontieneExplorados(ArrayList<Estado> explorados,Estado sc){  //para sustituir a la funcion contains cree esta variante
        for(int i=0;i < explorados.size();i++){
            Estado aux= explorados.get(i);
            if(aux.equals(sc)){
                return false;
            }
        }
        return true;
    }
    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception {
        ArrayList<Estado> explorados = new ArrayList<Estado>();
        ArrayList<Nodo> frontera = new ArrayList<>();
        ArrayList<Estado> estadosGuardados= new ArrayList<>();
        Estado estadoActual=null;
        int nodosexpandidos=0;
        int nodoscreados=0;
        ArrayList<Nodo> sucesores = null;
        Nodo actual = null;
        actual = new Nodo(p.getEstadoInicial(), null, null);
        frontera.add(actual);
        estadoActual=p.getEstadoInicial();
        int i = 1;
        System.out.println((i++) + " - Empezando búsqueda en " + actual.getE());

        while (true) {
            if (frontera.isEmpty()) {
                System.out.println("Error");
                throw new Exception();
            }
            actual=frontera.get(0);
            frontera.remove(0);  //el primero en entrar es el primero en salir, estructura FIFO
            explorados.add(estadoActual);
            System.out.println((i++) + " - " + estadoActual + " no es meta");
            Accion[] accionesDisponibles= p.acciones(actual.getE());   //calculamos las acciones para expandir el nodo
            for(Accion acc: accionesDisponibles){
                Nodo aux = new Nodo(p.result(actual.getE(),acc),actual,acc);  //creamos nodo
                nodoscreados++;
                if (nocontieneExplorados(estadosGuardados,aux.getE())) {    //si el estado producido no ha sido guardado, se añade en la frontera y en esta lista
                    frontera.add(aux);
                    estadosGuardados.add(aux.getE());
                }
            }
            nodosexpandidos++;
            System.out.println((i++) + " - RESULT(" + estadoActual + ","+ actual.getA() + ")=" + actual.getE());
            if(estadoActual.equals(actual.getE())) continue;  //para la primera iteracion
            estadoActual= actual.getE();     //igualamos para comprobar
            boolean modificado=false;
            if(nocontieneExplorados(explorados,estadoActual)){
                System.out.println((i++) + " - " + estadoActual + " NO explorado");
                if(p.esMeta(estadoActual)){
                    break;
                }
                explorados.add(estadoActual);
                modificado=true;
                System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);
            }
            else{
                System.out.println((i++) + " - " + estadoActual + " ya explorado");
            }
            if (!modificado) throw new Exception("No se ha podido encontrar una solución");
        }
        System.out.println((i++) + " - FIN - " + estadoActual);
        System.out.println("nodoscreados:" +    nodoscreados);
        System.out.println("nodosexpandidos:" +    nodosexpandidos);
        return reconstruye_solucion(actual);
    }
}