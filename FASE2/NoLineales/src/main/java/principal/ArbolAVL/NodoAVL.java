package principal.ArbolAVL;

import principal.ArbolBB.ABB;

public class NodoAVL extends ABB {
    int dato, fe;
    NodoAVL derecha,izquierda;

    public NodoAVL(int d) {
        this.dato =d;
        this.fe = 0;
        this.izquierda = null;
        this.derecha = null;
    }

}
