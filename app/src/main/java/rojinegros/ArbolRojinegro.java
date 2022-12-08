/*
Deisy Viviana Ruiz Ochoa (2060202)
Juan Sebastian Grajales (2059897)
Diego Fernando Llanos (2060029)
*/

package rojinegros;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.Queue;

public class ArbolRojinegro {
    @Getter
    @Setter
    private ArbolRojinegro izq;

    @Getter
    @Setter
    private ArbolRojinegro der; 
    @Getter
    @Setter
    private ArbolRojinegro father;

    @Getter
    @Setter
    private int valor;

    @Getter
    @Setter
    private boolean black; //Si es negro True, en otro caso rojo

    public ArbolRojinegro(ArbolRojinegro izq,
                          ArbolRojinegro der,
                          int valor,
                          boolean black) {
        this.izq = izq;
        this.der = der;
        this.valor = valor;
        this.black = black;
    }

    public ArbolRojinegro() {
        this.izq = null;
        this.der = null;
        this.black = true;
    }
    /*
     * Metodos a implementar
     */

    public void insertar(int x) throws Exception {
            if (x >= this.valor) {
            if (this.der == null) {
                this.der = new ArbolRojinegro(null, null, x, black);
            } else {
                this.der.insertar(x);
            }
        } else {
            if (this.izq == null) {
                this.izq = new ArbolRojinegro(null, null, x, black);
            } else {
                this.izq.insertar(x);
            }
        }
    }

    public int maximo() throws Exception {
        ArbolRojinegro nodoActual = this;
        while (nodoActual.getDer() != null) {
            nodoActual = nodoActual.getDer();
        }
        return nodoActual.getValor();
    }

    public int minimo() throws Exception {
        ArbolRojinegro nodoActual = this;
        while (nodoActual.getIzq() != null) {
            nodoActual = nodoActual.getIzq();
        }
        return nodoActual.getValor();
    }

    public ArbolRojinegro search(int x) throws Exception {
        if (this.valor == x) {
            return this;
        } else {
            if (x > this.valor) {
                if (this.getDer() != null) {
                    return this.getDer().search(x);
                } else {
                    return null;
                }
            } else {
                if (this.getIzq() != null) {
                    return this.getIzq().search(x);
                } else {
                    return null;
                }
            }
        }
    }

    public void rotacionIzquierda(int x) throws Exception {
        ArbolRojinegro arbolRotar = search(x);
        ArbolRojinegro padre = arbolRotar.getFather();
        ArbolRojinegro nuevaRaiz = arbolRotar.getDer();
        if (padre == null) {
            ArbolRojinegro nuevoArbol = new ArbolRojinegro(arbolRotar.getIzq(),
                    arbolRotar.getDer(),
                    arbolRotar.getValor(),
                    arbolRotar.isBlack());
            nuevoArbol.setDer(nuevaRaiz.getIzq());
            nuevaRaiz.setIzq(nuevoArbol);
            nuevoArbol.setFather(nuevaRaiz);
            nuevaRaiz.setFather(padre);
            arbolRotar.setValor(nuevaRaiz.getValor());
            arbolRotar.setDer(nuevaRaiz.getDer());
            arbolRotar.setIzq(nuevaRaiz.getIzq());
            arbolRotar.setBlack(nuevaRaiz.isBlack());
        } else {
            arbolRotar.setDer(nuevaRaiz.getIzq());
            arbolRotar.setFather(nuevaRaiz);
            nuevaRaiz.setIzq(arbolRotar);
            if (nuevaRaiz.getValor() < padre.getValor()) {
                padre.setDer(nuevaRaiz);
            } else {
                padre.setIzq(nuevaRaiz);
            }
        }
    }

    public void rotacionDerecha(int x) throws  Exception {
        ArbolRojinegro arbolRotar = search(x);
        ArbolRojinegro padre = arbolRotar.getFather();
        ArbolRojinegro nuevaRaiz = arbolRotar.getIzq();
        if (padre == null) {
            ArbolRojinegro nuevoArbol = new ArbolRojinegro(arbolRotar.getIzq(),
                    arbolRotar.getDer(),
                    arbolRotar.getValor(),
                    arbolRotar.isBlack());
            nuevoArbol.setIzq(nuevaRaiz.getDer());
            nuevaRaiz.setDer(nuevoArbol);
            nuevoArbol.setFather(nuevaRaiz);
            nuevaRaiz.setFather(padre);
            arbolRotar.setValor(nuevaRaiz.getValor());
            arbolRotar.setDer(nuevaRaiz.getDer());
            arbolRotar.setIzq(nuevaRaiz.getIzq());
            arbolRotar.setBlack(nuevaRaiz.isBlack());
        } else {
            arbolRotar.setIzq(nuevaRaiz.getDer());
            arbolRotar.setFather(nuevaRaiz);
            nuevaRaiz.setDer(arbolRotar);
            if (nuevaRaiz.getValor() < padre.getValor()) {
                padre.setIzq(nuevaRaiz);
            } else {
                padre.setDer(nuevaRaiz);
            }
        }
    }

    /*
     *  Area de pruebas, no modificar.
     */
    //Verificaciones
    /*
     * Busqueda por amplitud para verificar arbol.
     */
    public String bfs() {
        String salida = "";
        String separador = "";
        Queue<ArbolRojinegro> cola = new LinkedList<>();
        cola.add(this);
        while (cola.size() > 0) {
            ArbolRojinegro nodo = cola.poll();
            salida += separador + String.valueOf(nodo.getValor());
            separador = " ";
            if (nodo.getIzq() != null) {
                cola.add(nodo.getIzq());
            }
            if (nodo.getDer() != null) {
                cola.add(nodo.getDer());
            }
        }
        return salida;
    }

    /*
     * Recorrido inorder.
     * Verifica propiedad de orden.
     */
    public String inorden() {
        String recorrido = "";
        String separador = "";
        if (this.getIzq() != null) {
            recorrido += this.getIzq().inorden();
            separador = " ";
        }
        recorrido += separador + String.valueOf(this.getValor());
        if (this.getDer() != null) {
            recorrido += " " + this.getDer().inorden();
        }
        return recorrido;
    }

}
