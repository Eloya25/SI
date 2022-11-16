package es.udc.sistemasinteligentes5_12.magicSquare;


import es.udc.sistemasinteligentes5_12.Accion;
import es.udc.sistemasinteligentes5_12.Estado;
import es.udc.sistemasinteligentes5_12.ProblemaBusqueda;

import java.util.ArrayList;
import java.util.Arrays;

public class ProblemaCuadradoMagico extends ProblemaBusqueda {
    private final int n = 3;//número NxN de la matriz

    public static class EstadoCuadrado extends Estado {
        private int[][] estadoMatriz;

        public EstadoCuadrado(int[][] estadoMatriz) {
            this.estadoMatriz = estadoMatriz;
        }

        @Override
        public String toString() {
            return "(" + Arrays.deepToString(estadoMatriz) + "," + ')';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;


            EstadoCuadrado that = (EstadoCuadrado) o;
            if(that.estadoMatriz.length != estadoMatriz.length) return false;
            if(that.estadoMatriz[0].length != estadoMatriz[0].length) return false;
            for(int i=0;i< that.estadoMatriz.length;i++){
                for(int j=0;j<that.estadoMatriz.length;j++){
                    if(that.estadoMatriz[i][j]!=estadoMatriz[i][j]){
                        return false;
                    }
                }
            }
            return true;
        }    //override del equals para que devuelva falso si hay un numero en la matriz que sea distinto

        @Override
        public int hashCode() {
            int result = Arrays.deepHashCode(estadoMatriz);
            result = 31 * result;
            return result;
        }
    }

    public static class AccionCuadrado extends Accion {

        private final int n = 3;
        private int fila;
        private int columna;
        private int numero;

        public AccionCuadrado(int numero) {
            this.numero = numero;
        }

        @Override
        public String toString() {
            return "" + numero;
        }

        @Override
        public boolean esAplicable(Estado es) {
            EstadoCuadrado as = (EstadoCuadrado) es;
            int aux = 0;
            for (int i = 0; i < as.estadoMatriz.length; i++) {
                for (int j = 0; j < as.estadoMatriz.length; j++) {
                    if (numero == as.estadoMatriz[i][j]) {
                        aux = 1;
                    }
                }
            }              //si no existe ningun numero en la matriz que sea igual al que queremos añadir, esAplicable
            return aux == 0;
        }

        public static int[][] cloneMatrix(int[][] a) {
            int[][] b = new int[a.length][];
            for (int i = 0; i < a.length; i++) {
                b[i] = a[i].clone();
            }
            return b;          //metodo para clonar la matriz
        }

        @Override
        public Estado aplicaA(Estado es) {
            EstadoCuadrado as = ((EstadoCuadrado) es);
            int[][] matriz = cloneMatrix(as.estadoMatriz);
            if(esAplicable(as))
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (matriz[i][j] == 0) {
                        matriz[i][j] = numero;
                        return new EstadoCuadrado(matriz);                //añadir el numero en el primer 0 que encontremos y crear un nuevo estado con la matriz clonada
                    }
                }
            }
            return new EstadoCuadrado(matriz);
        }
    }


        private Accion[] listaAcciones;
        public ProblemaCuadradoMagico(EstadoCuadrado estadoInicial) {
            super(estadoInicial);
            ArrayList<Integer> auxiliar = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!(estadoInicial.estadoMatriz[i][j] == 0)) {
                        auxiliar.add(estadoInicial.estadoMatriz[i][j]);                     //añado los numeros que están en la matriz
                    }
                }
            }
            int aux = 0;
            ArrayList<Accion>lista = new ArrayList<Accion>();
                        for (int valor = 1; valor <= n*n; valor++) {
                            if (!auxiliar.contains(valor)) {
                                lista.add(new AccionCuadrado(valor));                //del uno al n*n añado los numeros que no esten como acciones pendientes
                                aux++;
                            }

            }
            listaAcciones=lista.toArray(new Accion[0]);
        }

        public int sumaTotal() {
            return n * (n * n + 1) / 2;
        }

        public boolean sumanFila(Estado es) {
            EstadoCuadrado as = (EstadoCuadrado) es;
            boolean cumpleSuma = true;
            int sumaFila = 0;
            int constanteMagica = sumaTotal();
            int f = 0;
            while (f < n && cumpleSuma) {
                for (int c = 0; c < n; c++)
                    sumaFila += as.estadoMatriz[f][c];

                if (sumaFila != constanteMagica) {
                    cumpleSuma = false;
                }
                f++;
                sumaFila = 0;
            }
            return cumpleSuma;
        }

        public boolean sumanColumna(Estado es) {
            EstadoCuadrado as = (EstadoCuadrado) es;
            boolean cumpleSuma = true;
            int sumaColumna = 0;
            int constanteMagica = sumaTotal();
            int c = 0;
            while (c < n && cumpleSuma) {
                for (int f = 0; f < n; f++)
                    sumaColumna += as.estadoMatriz[f][c];

                if (sumaColumna != constanteMagica) {
                    cumpleSuma = false;
                }
                c++;
                sumaColumna = 0;
            }
            return cumpleSuma;
        }

        public boolean sumanDiagonal(Estado es) {
            EstadoCuadrado as = (EstadoCuadrado) es;
            boolean cumpleSuma = true;
            int constanteMagica = sumaTotal();
            int sumaDiag1 = 0;
            int f = 0, c = 0;
            while (f < n && c < n) {
                sumaDiag1 += as.estadoMatriz[f][c];
                f++;
                c++;
            }
            if (sumaDiag1 != constanteMagica)
                cumpleSuma = false;
            else {
                int sumaDiag2 = 0;
                f = 0;
                c = n - 1;
                while (f < n && c >= 0) {
                    sumaDiag2 += as.estadoMatriz[f][c];
                    f++;
                    c--;
                }
                if (sumaDiag2 != constanteMagica)
                    cumpleSuma = false;
            }
            return cumpleSuma;                                                                //funciones que sirven para es meta
        }

        @Override
        public boolean esMeta(Estado es) {
            return sumanFila(es) && sumanColumna(es) && sumanDiagonal(es);  //si todas las filas columnas y diagonales suman lo mismo. es meta
        }

        @Override
        public Accion[] acciones(Estado es) {
            return listaAcciones;
        }
    }