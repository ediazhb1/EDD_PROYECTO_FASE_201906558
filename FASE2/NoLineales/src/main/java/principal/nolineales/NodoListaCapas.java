package principal.nolineales;

public class NodoListaCapas extends Matriz{
    String capa;
    NodoListaCapas siguiente;

    public NodoListaCapas() {
        super();
    }

    public NodoListaCapas(String capa){
        this.capa = capa;
        this.siguiente = null;
    }

}
