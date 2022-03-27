package principal.ArbolBB;

public class ABB {
    Nodobb inicial;
    Nodobb finals = null;
    public ABB(){
        this.inicial = null;
    }

    public void insertarbb(int valor){

        if(this.inicial == null) {
            this.inicial = new Nodobb(valor,0);
        }else{
            this.inicial.insertar(valor);
        }
    }

    public Nodobb IniciarBusquedabb(int capa) {
        Nodobb x = BuscarCapa(this.inicial, capa);
        if (x != null) {
            return x;
        }
        return null;
    }


    public Nodobb BuscarCapa(Nodobb nodo, int capa){

        if(nodo == null){
            finals = null;
            System.out.println("No se encontro el nodo en el abb");
        }else{
            if(nodo.getId() == capa){
                finals = nodo;
                return nodo;
            }else if(nodo.getId() > capa){
                BuscarCapa(nodo.getIzquierda(),capa);
            }else if(nodo.getId() < capa){
                BuscarCapa(nodo.getDerecha(),capa);
            }
        }
        return finals;
    }

    //-------------------RECORRIDO AMPLITUD-------------------
    public void InicioAmplitud(){
        this.Amplitud(this.inicial);
    }

    public void Amplitud(Nodobb nodo){
        Nodobb aux;
        ColaAmplitud ca = new ColaAmplitud();

        if(nodo != null){
            ca.insertarCola(nodo);
            System.out.println(nodo.getId());
            while(ca.inicio.id !=null){

                aux = ca.inicio.id;
                ca.EliminarPrimero();

                if(aux.getIzquierda() !=null) {
                    System.out.print(aux.izquierda.getId() + ", ");
                    ca.insertarCola(aux.getIzquierda());
                }

                if(aux.getDerecha() !=null) {
                    System.out.print(aux.derecha.getId() + ", ");
                    ca.insertarCola(aux.getDerecha());
                    System.out.println("");
                }

                if(ca.inicio == null){
                    break;
                }

        }
       }

    }




    //-------------------PREORDEN----------------------
    public void InicioPreOrden(){
        this.preOrden(this.inicial);
    }

    public void preOrden(Nodobb nodo){
        if(nodo == null){
            return;
        }else{
            System.out.print(nodo.getId() + ", ");
            preOrden(nodo.getIzquierda());
            preOrden(nodo.getDerecha());

        }
    }


    //-------------------INORDEN----------------------
    public void InicioInOrden(){
        this.InOrden(this.inicial);
    }

    public void InOrden(Nodobb nodo){
        if(nodo == null){
            return;
        }else{
            InOrden(nodo.getIzquierda());
            System.out.print(nodo.getId() + ", ");
            InOrden(nodo.getDerecha());

        }
    }


    //-------------------POSTORDEN----------------------
    public void InicioPostOrden(){
        this.PostOrden(this.inicial);
    }

    public void PostOrden(Nodobb nodo){
        if(nodo == null){
            return;
        }else{
            PostOrden(nodo.getIzquierda());
            PostOrden(nodo.getDerecha());
            System.out.print(nodo.getId() + ", ");
        }
    }
}
