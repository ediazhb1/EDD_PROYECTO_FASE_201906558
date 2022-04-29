package principal.ListaSimple;

public class NodoRuta {
    NodoRuta siguiente;
    String lugar;
    int tiempo;

    public NodoRuta(String lugar, int tiempo) {
        this.siguiente = null;
        this.lugar = lugar;
        this.tiempo = tiempo;
    }
}
