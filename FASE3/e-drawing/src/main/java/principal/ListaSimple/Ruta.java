package principal.ListaSimple;

import principal.arbolb.BNode;
import principal.tablahash.NodoHash;

public class Ruta {
    public NodoRuta inicio;

    public Ruta() {
        this.inicio = null;
    }

    public void entrega(String direccion, String fecha, String id, BNode cliente, NodoHash Mensajero) {
        NodoRuta nuevo = new NodoRuta(direccion,fecha,id,cliente,Mensajero);
        if (inicio == null) {
            inicio = nuevo;
        } else {
            NodoRuta tmp = inicio;
            while (tmp.siguiente != null){
                tmp = tmp.siguiente;
            }
            tmp.siguiente = nuevo;
        }
    }

}
