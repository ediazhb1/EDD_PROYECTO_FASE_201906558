package principal.ArbolAVL;

public class AVL {

    public NodoAVL raiz;

    public AVL() {
        raiz = null;
    }

    public NodoAVL IniciarBusqueda(int img) {
        NodoAVL x = busqueda(this.raiz, img);
        if (x != null) {
            return x;
        }
        return null;
    }

    public NodoAVL busqueda(NodoAVL r ,int d){
        if(r == null){
            return null;
        }else if(r.dato ==d ){
            return r;
        }else if(r.dato < d){
            return  busqueda(r.derecha,d);
        }else{
            return  busqueda(r.izquierda,d);
        }
    }

    //EQUILIBRIO

    public int FactorEquilibrio(NodoAVL x){
        if(x == null){
            return -1;
        }else{
            return x.fe;
        }
    }

    //ROTACION SIMPLE IZQUIERDA

    public NodoAVL RotacionIzq(NodoAVL x){
        NodoAVL aux = x.izquierda;
        x.izquierda = aux.derecha;
        aux.derecha = x;
        x.fe=Math.max(FactorEquilibrio(x.izquierda),FactorEquilibrio(x.derecha))+1;
        aux.fe=Math.max(FactorEquilibrio(aux.izquierda),FactorEquilibrio(aux.derecha))+1;

        return aux;
    }

    //ROTACION SIMPLE DERECHA

    public NodoAVL RotacionDer(NodoAVL x){
        NodoAVL aux = x.derecha;
        x.derecha = aux.izquierda;
        aux.izquierda = x;
        x.fe=Math.max(FactorEquilibrio(x.izquierda),FactorEquilibrio(x.derecha))+1;
        aux.fe=Math.max(FactorEquilibrio(aux.izquierda),FactorEquilibrio(aux.derecha))+1;

        return aux;
    }

    //ROTACION DOBLE DERECHA

    public NodoAVL RotacionDobleIzq(NodoAVL x){
        NodoAVL aux;
        x.izquierda = RotacionDer(x.izquierda);
        aux = RotacionIzq(x);

        return aux;
    }

    //ROTACION DOBLE IZQUIERDA

    public NodoAVL RotacionDobleDer(NodoAVL x){
        NodoAVL aux;
        x.derecha = RotacionIzq(x.derecha);
        aux = RotacionDer(x);

        return aux;
    }

    public NodoAVL insertarAVL(NodoAVL nuevo, NodoAVL subarbol){
        NodoAVL nuevoPadre = subarbol;
        if(nuevo.dato < subarbol.dato){
            if(subarbol.izquierda == null){
                subarbol.izquierda = nuevo;
            }else{
                subarbol.izquierda = insertarAVL(nuevo, subarbol.izquierda);
                if((FactorEquilibrio(subarbol.izquierda) - FactorEquilibrio(subarbol.derecha)) == 2){
                    if(nuevo.dato < subarbol.izquierda.dato){
                        nuevoPadre = RotacionIzq(subarbol);
                    }else{
                        nuevoPadre = RotacionDobleIzq(subarbol);
                    }
                }
            }
        }else if(nuevo.dato >subarbol.dato){
            if(subarbol.derecha == null){
                subarbol.derecha = nuevo;
            }else{
                subarbol.derecha = insertarAVL(nuevo, subarbol.derecha);
                if((FactorEquilibrio(subarbol.derecha) - FactorEquilibrio(subarbol.izquierda)) == 2){
                    if(nuevo.dato > subarbol.derecha.dato){
                        nuevoPadre = RotacionDer(subarbol);
                    }else{
                        nuevoPadre = RotacionDobleDer(subarbol);
                    }
                }
            }

        }else{
            System.out.println("Duplicados");
        }
        // Nuevo Factor Equilibrio
        if((subarbol.izquierda == null)&&(subarbol.derecha != null)){
            subarbol.fe = subarbol.derecha.fe+1;

        }else if((subarbol.derecha == null)&& (subarbol.izquierda != null)){
            subarbol.fe = subarbol.izquierda.fe+1;

        }else{
            subarbol.fe = Math.max(FactorEquilibrio(subarbol.izquierda),FactorEquilibrio(subarbol.derecha))+1;
        }
        return nuevoPadre;
    }

    public void insertar(int dato){
        NodoAVL nuevo = new NodoAVL(dato);
        if(raiz == null){
            raiz =  nuevo;
        }else{
            raiz = insertarAVL(nuevo,raiz);
        }
    }

    //-------------------graphbiz------------------------------
    public String graph = "";
    public void Iniciograph(boolean tipograph){
        if(tipograph){
            graph = "digraph G {\n";
            graph += "node [shape=box]\n" +
                    "label = \"Imagenes del Arbol AVL\";\n";
            graph += "V"+raiz.dato+"[label = \""+raiz.dato+"\"]\n";
            this.dotgraph(this.raiz);
            graph += "}";
        }else{
            graph = "digraph G {\n";
            graph += "node [shape=box]\n" +
                    "label = \"Imagenes y Arbol de Capas \";\n";
            graph += "V"+raiz.dato+"[label = \""+raiz.dato+"\"]\n";
            this.dotgraph(this.raiz);
        }

    }

    public void dotgraph(NodoAVL nodo){
        if(nodo != null){
            if(nodo.izquierda != null){
                graph += "V"+nodo.izquierda.dato+"[label = \""+nodo.izquierda.dato+"\"]\n";
                graph += "V"+nodo.dato + "->" + "V"+nodo.izquierda.dato + ";\n";
            }
            if(nodo.derecha != null){
                graph += "V"+nodo.derecha.dato+"[label = \""+nodo.derecha.dato+"\"]\n";
                graph += "V"+nodo.dato + "->" +"V"+ nodo.derecha.dato + ";\n";
            }
            dotgraph(nodo.izquierda);
            dotgraph(nodo.derecha);
        }

    }

    public void iniciarin(){
        InOrden(this.raiz);
    }

    public void InOrden(NodoAVL r){
        if(r != null){
            InOrden(r.izquierda);
            System.out.print(r.dato + ", ");
            InOrden(r.derecha);
        }
    }

    public void iniciarpre(){
        PreOrden(this.raiz);
    }

    public void PreOrden(NodoAVL r){
        if(r != null){
            System.out.print(r.dato + ", ");
            InOrden(r.izquierda);
            InOrden(r.derecha);
        }
    }

    public void iniciarpost(){
        PostOrden(this.raiz);
    }

    public void PostOrden(NodoAVL r){
        if(r != null){
            InOrden(r.izquierda);
            InOrden(r.derecha);
            System.out.print(r.dato + ", ");
        }
    }


}
