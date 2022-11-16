package es.udc.sistemasinteligentes5_12;
import es.udc.sistemasinteligentes5_12.ejemplo.Nodo;
public interface EstrategiaBusqueda {
    /**
     * Soluciona el problema de búsqueda, obteniendo un estado meta o arrojando una Excepcion si no encuentra una
     * @param p Problema a solucionar
     * @return Estado meta obtenido
     */
    public abstract Nodo[] soluciona(ProblemaBusqueda p) throws Exception;
}
