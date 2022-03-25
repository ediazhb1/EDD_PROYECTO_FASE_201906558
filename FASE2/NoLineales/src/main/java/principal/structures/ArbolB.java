package principal.structures;

public class ArbolB {

    int orden_arbol = 5;
    boolean primernodo = true;
    RamaB raiz;
    String cadena="";
    public ArbolB() {
        this.raiz = null;
    }



    public void insertar(int id) {
        NodoAB nodo = new NodoAB(id);
        if (raiz == null) {
            raiz = new RamaB();
            raiz.insertar(nodo);
        } else {
            NodoAB obj = insertar_en_rama(nodo, raiz);
            if (obj != null) {
                //si devuelve algo el metodo de insertar en rama quiere decir que creo una nueva rama, y se debe insertar en el arbol
                raiz = new RamaB();
                raiz.insertar(obj);
                raiz.hoja = false;
            }
        }
    }

    private NodoAB insertar_en_rama(NodoAB nodo, RamaB rama) {
        if (rama.hoja) {
            rama.insertar(nodo);
            if (rama.contador == orden_arbol) {
                //si ya se insertaron todos los elementos posibles se debe dividir la rama
                return dividir(rama);
            } else {
                return null;
            }
        } else {
            NodoAB temp = rama.primero;
            do {
                if (nodo.id == temp.id) {
                    return null;
                } else if (nodo.id < temp.id) {
                    NodoAB obj = insertar_en_rama(nodo, temp.izquierda);
                    if (obj instanceof NodoAB) {
                        rama.insertar((NodoAB) obj);
                        if (rama.contador == orden_arbol) {
                            return dividir(rama);
                        }
                    }
                    return null;
                } else if (temp.siguiente == null) {
                    NodoAB obj = insertar_en_rama(nodo, temp.derecha);
                    if (obj instanceof NodoAB) {
                        rama.insertar((NodoAB) obj);
                        if (rama.contador == orden_arbol) {
                            return dividir(rama);
                        }
                    }
                    return null;
                }
                temp = (NodoAB) temp.siguiente;
            } while (temp != null);
        }
        return null;
    }

    private NodoAB dividir(RamaB rama) {
        int val = -999;
        NodoAB temp, Nuevito;
        NodoAB aux = rama.primero;
        RamaB rderecha = new RamaB();
        RamaB rizquierda = new RamaB();

        int cont = 0;
        while (aux != null) {
            cont++;
            //implementacion para dividir unicamente ramas de 4 nodos
            if (cont < 3) {
                temp = new NodoAB(aux.id);
                temp.izquierda = aux.izquierda;
                if (cont == 2) {
                    temp.derecha = aux.siguiente.izquierda;
                } else {
                    temp.derecha = aux.derecha;
                }
                //si la rama posee ramas deja de ser hoja
                if (temp.derecha != null && temp.izquierda != null) {
                    rizquierda.hoja = false;
                }

                rizquierda.insertar(temp);

            } else if (cont == 3) {
                val = aux.id;
            } else {
                temp = new NodoAB(aux.id);
                temp.izquierda = aux.izquierda;
                temp.derecha = aux.derecha;
                //si la rama posee ramas deja de ser hoja
                if (temp.derecha != null && temp.izquierda != null) {
                    rderecha.hoja = false;
                }
                rderecha.insertar(temp);
            }
            aux = aux.siguiente;
        }
        Nuevito = new NodoAB(val);
        Nuevito.derecha = rderecha;
        Nuevito.izquierda = rizquierda;
        return Nuevito;
    }

    public void imprimir(){
        cadena +="digraph arbolB{\n";
        cadena+="rankr=TB;\n";
        cadena+="node[shape = record,fillcolor=\"azure2\" color=\"black\" style=\"filled\"];\n";

        NodoAB tmp2 = raiz.primero;//siguientes
        RamaB tmp1 = tmp2.izquierda; //izquiedas
        RamaB tmp3 = tmp2.derecha;// derechas

        int limit = 0;

        while((tmp1 != null  && tmp3 !=null) || limit != 20){
            limit++;
            gnodos(tmp2);
            primernodo = true;

            tmp2 = tmp1.primero;//Rama izquierda
            if(tmp2.izquierda == null){
                //gnodos(tmp2);
                tmp1 = tmp3;
                tmp3 = null;
            }else{
                tmp1 = tmp2.izquierda;
                tmp3 = tmp2.derecha;
            }

        }

        cadena+="}\n";

    }

    public void gnodos(NodoAB tmp2){
        int c = 1;
        while(tmp2 != null){
            if(primernodo){
                cadena+= tmp2.hashCode()+ " [label=\"<C" +c+">|"+ tmp2.id ;
                primernodo = false;
            }else{
                cadena += "|<C"+c+">|"+tmp2.id;
            }

            c++;
            if(tmp2.siguiente == null){
                cadena +="|<C"+c+">\"];\n";
            }

            tmp2 = tmp2.siguiente;
        }
        System.out.println(cadena);
    }

}