package es.udc.sistemasinteligentes5_12.magicSquare;

import es.udc.sistemasinteligentes5_12.EstrategiaBusqueda;
import es.udc.sistemasinteligentes5_12.ProblemaBusqueda;
import es.udc.sistemasinteligentes5_12.ejemplo.Nodo;

public class Main2a {
    public static void main(String[] args) throws Exception {
        int[][] matriz={{4,9,2},{3,5,0},{0,1,0}};
        ProblemaCuadradoMagico.EstadoCuadrado estadoInicial = new ProblemaCuadradoMagico.EstadoCuadrado(matriz);
        ProblemaBusqueda cuadrado = new ProblemaCuadradoMagico(estadoInicial);

        EstrategiaBusqueda buscador = new EstrategiaBusquedaProfundidad();
        Nodo[] nodo= buscador.soluciona(cuadrado);
        for(int i=0;i< nodo.length && nodo[i]!=null;i++)
            System.out.println(nodo[i].getE());  //imprime los estados en orden inverso(primero el ultimo...)
    }
}
