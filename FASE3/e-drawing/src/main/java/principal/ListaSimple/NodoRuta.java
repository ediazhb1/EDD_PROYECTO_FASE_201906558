package principal.ListaSimple;

import principal.arbolb.BNode;
import principal.tablahash.NodoHash;

public class NodoRuta {
    NodoRuta siguiente;
    String direccion;
    String id;
    String fecha;
    BNode cliente;
    NodoHash Mensajero;
    public NodoRuta(String direccion, String fecha, String id, BNode cliente, NodoHash Mensajero) {
        this.siguiente = null;
        this.direccion = direccion;
        this.id = id;
        this.fecha = fecha;
        this.cliente = cliente;
        this.Mensajero = Mensajero;
    }
}
