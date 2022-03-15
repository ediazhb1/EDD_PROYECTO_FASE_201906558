package principal.nolineales;

public class ListaCapas {

    public NodoListaCapas inicio;

    public ListaCapas() {
        this.inicio =  null;
    }

    public void insertar(String capa){
        NodoListaCapas nuevo = new NodoListaCapas(capa);

        if (inicio == null) {
            inicio = nuevo;
        } else {
            NodoListaCapas tmp = inicio;
            while (tmp.siguiente != null){
                tmp = tmp.siguiente;
            }
            tmp.siguiente = nuevo;
        }
    }

    public void mostrarcapas(){
        NodoListaCapas tmp1 = inicio;
        System.out.println("---------------------------Lista Capas---------------------------");
        while ( tmp1 != null){
            if(tmp1.siguiente == null) {
                System.out.print("[No." + tmp1.capa +" )] -> NULL");
            }else{
                System.out.print("[No." + tmp1.capa +" )] -> ");
            }

            tmp1 = tmp1.siguiente;
        }

        System.out.println("");
    }

    public NodoListaCapas BuscarCapa(String capa){
        NodoListaCapas aux = inicio;
        while(aux != null) {
            if (aux.capa.equals(capa)) {
                return aux;
            }
            aux = aux.siguiente;
        }
        return null;
    }

}
