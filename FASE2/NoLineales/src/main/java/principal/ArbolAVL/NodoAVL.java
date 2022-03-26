package principal.ArbolAVL;

public class NodoAVL {
    int dato, fe;
    NodoAVL derecha,izquierda;

    public NodoAVL(int d) {
        this.dato =d;
        this.fe = 0;
        this.izquierda = null;
        this.derecha = null;
    }

    public int getDato() {
        return dato;
    }
}
