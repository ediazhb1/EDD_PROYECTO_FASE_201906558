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

    //-------------------graphbiz------------------------------
    public String graph = "";
    public void Iniciograph(boolean tipograph,String union){
        if(tipograph){
            graph = "digraph G {\n";
            graph += "node [shape=circle]\n" +
                    "label = \"Capas del Arbol Binario de Busqueda\";";
            this.dotgraph(this.inicial);
            graph += "}";
        }else{
            graph = "subgraph G2 {\n";
            graph += "node [shape=circle]\n";
            graph += "V"+union+"->"+inicial.getId()+";\n";
            this.dotgraph(this.inicial);
            graph += "}}";
        }

    }



    public void dotgraph(Nodobb nodo){
        if(nodo != null){
            if(nodo.izquierda != null){
                graph += nodo.getId() + "->" + nodo.izquierda.getId() + ";\n";
            }
            if(nodo.derecha != null){
                graph += nodo.getId() + "->" + nodo.derecha.getId() + ";\n";
            }
            dotgraph(nodo.izquierda);
            dotgraph(nodo.derecha);
        }
    }


    //-------------------RECORRIDO AMPLITUD-------------------
    public String contAmplitud = "";
    public void InicioAmplitud(){
        this.Amplitud(this.inicial);
    }

    public void Amplitud(Nodobb nodo){
        Nodobb aux;
        ColaAmplitud ca = new ColaAmplitud();

        if(nodo != null){
            ca.insertarCola(nodo);
            contAmplitud = nodo.getId() + ",";
            while(ca.inicio.id !=null){

                aux = ca.inicio.id;
                ca.EliminarPrimero();

                if(aux.getIzquierda() !=null) {
                    contAmplitud += aux.izquierda.getId() + ",";
                    ca.insertarCola(aux.getIzquierda());
                }

                if(aux.getDerecha() !=null) {
                    contAmplitud += aux.derecha.getId() + ",";
                    ca.insertarCola(aux.getDerecha());
                }

                if(ca.inicio == null){
                    break;
                }

        }
       }
    }


    //-------------------PREORDEN----------------------
    public String contPre = "";
    public void InicioPreOrden(){
        this.preOrden(this.inicial);
    }

    public void preOrden(Nodobb nodo){
        if(nodo == null){
            return;
        }else{
            contPre += nodo.getId() + ",";
            preOrden(nodo.getIzquierda());
            preOrden(nodo.getDerecha());

        }
    }


    //-------------------INORDEN----------------------
    public String contIn = "";
    public void InicioInOrden(){
        this.InOrden(this.inicial);
    }

    public void InOrden(Nodobb nodo){
        if(nodo == null){
            return;
        }else{
            InOrden(nodo.getIzquierda());
            contIn += nodo.getId() + ",";
            InOrden(nodo.getDerecha());

        }
    }


    //-------------------POSTORDEN----------------------
    public String contPost = "";
    public void InicioPostOrden(){
        this.PostOrden(this.inicial);
    }

    public void PostOrden(Nodobb nodo){
        if(nodo == null){
            return;
        }else{
            PostOrden(nodo.getIzquierda());
            PostOrden(nodo.getDerecha());
            contPost += nodo.getId() + ",";;
        }
    }
}
