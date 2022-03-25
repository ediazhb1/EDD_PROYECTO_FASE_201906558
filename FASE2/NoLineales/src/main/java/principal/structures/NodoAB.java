package principal.structures;

public class NodoAB {

    //Valores
    int id;
    //Apuntadores
    NodoAB siguiente;
    NodoAB anterior;
    RamaB derecha;
    RamaB izquierda;

    public NodoAB(int id) {
        this.id = id;
        this.anterior = null;
        this.siguiente = null;
        this.derecha = null;
        this.izquierda = null;
    }

}
