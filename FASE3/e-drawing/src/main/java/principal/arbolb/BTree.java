package principal.arbolb;

public class BTree {
    String graph ="";
    int orden = 5;
    BLeaf raiz;
    BNode encontrar = null;
    boolean seguirbusqueda = true;
    BNode encontrar2 = null;
    boolean seguirbusqueda2 = true;

    public BTree() {
        this.raiz = null;
    }

    public void insertarB(long dpi,String nombre,String usuario, String correo, String contra, String tel, String dir, String muni){
        BNode nodo = new BNode(dpi,nombre,usuario,correo,contra,tel,dir,muni);
        if (this.raiz == null) {
            this.raiz = new BLeaf();
            this.raiz.insertar(nodo);
        } else {
            BNode temp = this.adds(nodo, this.raiz);
            if (temp != null) {
                this.raiz = new BLeaf();
                this.raiz.insertar(temp);
                this.raiz.hoja = false;
            }

        }
    }

    public BNode adds(BNode nodo, BLeaf rama) {
        if (rama.hoja) {
            rama.insertar(nodo);
            if (rama.contador == this.orden) {
                return this.dividirRama(rama);
            } else {
                return null;
            }
        } else {
            BNode temp = rama.primero;
            do {
                if (nodo.dpi == temp.dpi) {
                    return null;
                } else if (nodo.dpi < temp.dpi) {
                    BNode aux = this.adds(nodo, temp.izquierdo);
                    if (aux != null) {
                        rama.insertar(aux);
                        if (rama.contador == this.orden) {
                            return this.dividirRama(rama);
                        }
                    }
                    return null;

                } else if (temp.siguiente == null) {
                    BNode aux = this.adds(nodo, temp.derecho);
                    if (aux != null) {
                        rama.insertar(aux);
                        if (rama.contador == this.orden) return this.dividirRama(rama);

                    }
                    return null;
                }
                temp = temp.siguiente;
            } while (temp != null);
        }
        return null;
    }

    
    public BNode dividirRama(BLeaf rama) {
        long val = -999;
        String nom= "",usu= "",correo= "",contra= "",tel= "",dir= "",muni = "";
        BNode temp, Nuevito;
        BNode aux = rama.primero;
        BLeaf rderecho = new BLeaf();
        BLeaf rizquierdo = new BLeaf();

        int cont = 0;
        while (aux != null) {
            cont++;
            //implementacion para dividir unicamente ramas de 4 nodos
            if (cont < 3) {
                temp = new BNode(aux.dpi,aux.nombre,aux.usuario,aux.correo,aux.contra,aux.tel,aux.dir,aux.muni);
                temp.izquierdo = aux.izquierdo;
                if (cont == 2) {
                    temp.derecho = aux.siguiente.izquierdo;
                } else {
                    temp.derecho = aux.derecho;
                }
                //si la rama posee ramas deja de ser hoja
                if (temp.derecho != null && temp.izquierdo != null) {
                    rizquierdo.hoja = false;
                }

                rizquierdo.insertar(temp);

            } else if (cont == 3) {
                val = aux.dpi;
                nom = aux.nombre;
                usu = aux.usuario;
                correo = aux.correo;
                contra = aux.contra;
                tel = aux.tel;
                dir = aux.dir;
                muni = aux.muni;
            } else {
                temp = new BNode(aux.dpi, aux.nombre,aux.usuario,aux.correo,aux.contra,aux.tel,aux.dir,aux.muni);
                temp.izquierdo = aux.izquierdo;
                temp.derecho = aux.derecho;
                //si la rama posee ramas deja de ser hoja
                if (temp.derecho != null && temp.izquierdo != null) {
                    rderecho.hoja = false;
                }
                rderecho.insertar(temp);
            }
            aux = aux.siguiente;
        }
        Nuevito = new BNode(val,nom,usu,correo,contra,tel,dir,muni);
        Nuevito.derecho = rderecho;
        Nuevito.izquierdo = rizquierdo;
        return Nuevito;
    }

    public BNode BusquedaUsuario(String usuario){
        seguirbusqueda2 = true;
        encontrar2 = null;
        BNode x = buscarUsuario(usuario, raiz);
        return x;
    }

    public BNode buscarUsuario(String usuario,BLeaf r){
        if(r !=null) {
            BNode aux = r.primero;
            while (aux != null) {
                if (aux.usuario.equals(usuario)) {
                    seguirbusqueda2 = false;
                    encontrar2 = aux;
                    break;
                }
                aux = aux.siguiente;
            }

            if (!Hoja(r) && seguirbusqueda2 == true) {
                BNode aux1 = r.primero;
                while (aux1 != null) {
                    this.buscarUsuario(usuario, aux1.izquierdo);
                    aux1 = aux1.siguiente;
                }
                this.buscarUsuario(usuario, r.ultimo.derecho);
            }
        }
        return encontrar2;
    }

    public BNode iniciarBusquedas(String usuario, String contra){
        seguirbusqueda = true;
        encontrar = null;
        BNode x = buscar(usuario, contra, raiz);
        return x;
    }

    public BNode buscar(String usuario, String pass,BLeaf r){
        if(r !=null) {
            BNode aux = r.primero;
            while (aux != null) {
                if (aux.usuario.equals(usuario) && aux.contra.equals(pass)) {
                    seguirbusqueda = false;
                    encontrar = aux;
                    break;
                }
                aux = aux.siguiente;
            }

            if (!Hoja(r) && seguirbusqueda == true) {
                BNode aux1 = r.primero;
                while (aux1 != null) {
                    this.buscar(usuario, pass, aux1.izquierdo);
                    aux1 = aux1.siguiente;
                }
                this.buscar(usuario, pass, r.ultimo.derecho);
            }
        }
        return encontrar;
    }

    public boolean Hoja(BLeaf pagina){
        return pagina.primero.izquierdo == null; //Determina si el nodo es hoja o no
    }

    public void generargraph(){
        graph = "digraph arbolB{ \n";
        graph += "node [shape=record];\n";
        graph += "graph [rankdir = TB]\n";
        dotnodo(raiz);
        unirnodo(raiz);
        graph+= "} \n";
        System.out.println(graph);
    }

    public void dotnodo(BLeaf r){
        if(Hoja(r)){
            graph += r.primero.dpi + "[label=\"";
            BNode aux = r.primero;
            while(aux != null){
                graph += "|" + aux.dpi +" - "+aux.nombre + "|";
                aux = aux.siguiente;
            }
            graph += "\"]; \n";
        }else{
            graph += r.primero.dpi + "[label=\"";
            BNode aux1 = r.primero;
            while(aux1 != null){
                graph += "|" + aux1.dpi + " - "+ aux1.nombre + "|";
                aux1 = aux1.siguiente;
            }
            graph += "\"]; \n";
            //Hijos del arbol b
            BNode aux2 = r.primero;
            while(aux2 != null){
                dotnodo(aux2.izquierdo);
                aux2 = aux2.siguiente;
            }
            dotnodo(r.ultimo.derecho);
        }
    }

    public void unirnodo(BLeaf r){
        if(Hoja(r)){
            graph += r.primero.dpi + ";\n";
        }else{
            BNode aux1 = r.primero;
            int contador = 0;
            long primeroRaiz = r.primero.dpi;
            while(aux1 != null){
                graph +=  primeroRaiz + "->";
                this.unirnodo(aux1.izquierdo);
                contador++;
                aux1 = aux1.siguiente;
            }
            graph += primeroRaiz + "->";
            this.unirnodo(r.ultimo.derecho);
        }
    }

}
