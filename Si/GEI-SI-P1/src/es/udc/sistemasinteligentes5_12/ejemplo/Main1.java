package es.udc.sistemasinteligentes5_12.ejemplo;

import es.udc.sistemasinteligentes5_12.EstrategiaBusqueda;
import es.udc.sistemasinteligentes5_12.ProblemaBusqueda;

public class Main1 {

    public static void main(String[] args) throws Exception {
        ProblemaAspiradora.EstadoAspiradora estadoInicial = new ProblemaAspiradora.EstadoAspiradora(ProblemaAspiradora.EstadoAspiradora.PosicionRobot.IZQ,
                ProblemaAspiradora.EstadoAspiradora.PosicionBasura.AMBAS);
        ProblemaBusqueda aspiradora = new ProblemaAspiradora(estadoInicial);

        EstrategiaBusqueda buscador = new EstrategiaBusquedaGrafo();
        Nodo[] nodo = buscador.soluciona(aspiradora);
        int i=0;
        System.out.println("ESTADOS ATRAVESADOS");
        while (nodo[i] != null) {
            System.out.println("");
            System.out.println(nodo[i].getE());            //imprime los estados en orden inverso(primero el ultimo...)
            i++;
        }
    }
}
