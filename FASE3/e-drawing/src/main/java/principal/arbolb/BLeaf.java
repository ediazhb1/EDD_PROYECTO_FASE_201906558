package principal.arbolb;

public class BLeaf {

    int contador;
    boolean hoja;
    BNode primero,ultimo;  //every tree has at least a root node

    public BLeaf() {
        this.contador = 0;//contador de elementos insertados
        this.hoja = true;//Si es hoja el nodo que vamos a insertar
        this.primero = null;//El inicio de la lista
        this.ultimo = null;
    }

    public void insertar(BNode nodo){
        if (this.primero == null) {
            this.primero = nodo;
            this.ultimo = nodo;
            this.contador++;
        } else {
            BNode temp = this.primero;
            if(nodo.dpi< this.primero.dpi){
                nodo.siguiente = this.primero;
                this.primero.anterior = nodo;
                // punteros de la página
                this.primero.izquierdo = nodo.derecho;
                this.primero = nodo;
                this.contador++;
                // si el dato nodo es mayor que el último
            }else if(nodo.dpi > this.ultimo.dpi){
                nodo.anterior = this.ultimo;
                this.ultimo.siguiente = nodo;
                // punteros de la página
                this.ultimo.derecho = nodo.izquierdo;
                this.ultimo = nodo;
                this.contador++;
                // si no es menor que el primero o mayor que el último entonces se recorre la lista
            }else{
                BNode aux = this.primero;
                while(aux != null){
                    // si el dato es menor que el auxiliar entonces se inserta una posición antes
                    if(nodo.dpi < aux.dpi){
                        nodo.siguiente = aux;
                        nodo.anterior = aux.anterior;
                        // punteros de la página
                        aux.izquierdo = nodo.derecho;
                        aux.anterior.derecho = nodo.izquierdo;
                        aux.anterior.siguiente = nodo;
                        aux.anterior = nodo;
                        this.contador++;
                    }else if(nodo.dpi == aux.dpi){
                        // si el dato nodo es igual al dato de la lista no se insertará
                       break;
                    }
                    aux = aux.siguiente;
                }
            }

        }
    }
}
