package principal.ListaSimple;

public class Ruta {
    public NodoRuta inicio;

    public Ruta() {
        this.inicio = null;
    }

    public void camino(String nombre,int tiempo) {
        NodoRuta nuevo = new NodoRuta(nombre,tiempo);
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
