package es.udc.sistemasinteligentes5_12.ejemplo;

import es.udc.sistemasinteligentes5_12.Accion;
import es.udc.sistemasinteligentes5_12.Estado;

import java.util.Objects;

public class Nodo {
    Estado e;
    Nodo padre;
    Accion a;
    public Nodo(Estado e, Nodo p, Accion a){
        this.e=e;
        this.padre=p;
        this.a=a;
    }

    public Accion getA() {
        return a;
    }

    public Estado getE() {
        return e;
    }

    public Nodo getPadre() {
        return padre;
    }  //getters

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public void setE(Estado e) {
        this.e = e;
    }

    public void setA(Accion a) {
        this.a = a;
    }            //Setters

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return ((Nodo)o).a == a && ((Nodo)o).e.equals(e) && ((Nodo)o).padre==padre;             //equals donde comparo las acciones y los estados, ademas de los padres
    }

    @Override
    public int hashCode() {
        return Objects.hash(e, padre, a);
    }
}
