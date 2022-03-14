package principal.nolineales;

public class Matriz {
    public Nodo root;

    public Matriz() {
         root = new Nodo(-1,-1, "#ffffff");
    }

    public void imprimir(){
        Nodo aux = root;

        while (aux != null){
            String txt = "";
            Nodo aux2 = aux;
            while (aux2 != null){
                txt += "["+ aux2.x+","+aux2.y+"] -> ";
                aux2 = aux2.siguiente;
            }
            System.out.println(txt);
            aux = aux.abajo;
        }
    }

    public void insertarNodo(int x,int y,String color){
        Nodo nuevo = new Nodo(x,y, color);
        Nodo NodoColumna = buscarColumna(x);
        Nodo NodoFila = buscarFila(y);

        if(NodoColumna == null && NodoFila == null){
            NodoColumna = crear_columna(x);
            NodoFila = crear_fila(y);

            nuevo = orden_columna(nuevo,NodoFila);
            nuevo = orden_fila(nuevo,NodoColumna);

        }else if(NodoColumna != null && NodoFila == null){
            NodoFila = crear_fila(y);

            nuevo = orden_columna(nuevo,NodoFila);
            nuevo = orden_fila(nuevo,NodoColumna);

        }else if(NodoColumna == null && NodoFila != null){
            NodoColumna = crear_columna(x);

            nuevo = orden_columna(nuevo,NodoFila);
            nuevo = orden_fila(nuevo,NodoColumna);

        }else if(NodoColumna != null && NodoFila != null){

            nuevo = orden_columna(nuevo,NodoFila);
            nuevo = orden_fila(nuevo,NodoColumna);
        }

    }

    public Nodo buscarColumna(int x){
        Nodo aux = root;
        while (aux != null){
            if(aux.x == x){
                return aux;
            }
            aux = aux.siguiente;
        }
        return null;
    }

    public Nodo buscarFila(int y){
        Nodo aux = root;
        while (aux != null){
            if(aux.y== y){
                return aux;
            }
            aux = aux.abajo;
        }
        return null;
    }

    public Nodo crear_columna(int columna) {
        Nodo nodo_col = root;
        Nodo nuevo = new Nodo(columna, -1, "COL");////////
        return orden_columna(nuevo, nodo_col);
    }

    public Nodo crear_fila(int y) {
        Nodo nodo_fila = root;
        Nodo nuevo = new Nodo(-1, y, "FILA");////////////
        return orden_fila(nuevo, nodo_fila);
    }

    public Nodo orden_columna(Nodo nuevo, Nodo tmp){
        Nodo aux = tmp;
        boolean insertado = false;

        while(true){
            if(nuevo.x == aux.x){
                aux.y = nuevo.y;
                aux.color = nuevo.color;
                return aux;
            }else if(aux.x > nuevo.x){
                insertado = true;
                break;
            }
            if(aux.siguiente != null){
                aux = aux.siguiente;
            }else{
                insertado = false;
                break;
            }
        }
        if(insertado){
            nuevo.siguiente = aux;
            aux.anterior.siguiente = nuevo;
            nuevo.anterior = aux.anterior;
            aux.anterior = nuevo;
        }else{
            aux.siguiente = nuevo;
            nuevo.anterior = aux;
        }
        return nuevo;
    }



    public Nodo orden_fila(Nodo nuevo, Nodo tmp){
        Nodo aux = tmp;
        boolean insertado = false;

        while(true){
            if(nuevo.y == aux.y){
                aux.x = nuevo.x;
                aux.color = nuevo.color;
                return aux;
            }else if(aux.y > nuevo.y){
                insertado = true;
                break;
            }
            if(aux.abajo != null){
                aux = aux.abajo;
            }else{
                insertado = false;
                break;
            }
        }

        if(insertado){
            nuevo.abajo = aux;
            aux.arriba.abajo = nuevo;
            nuevo.arriba = aux.arriba;
            aux.arriba = nuevo;
        }else{
            aux.abajo = nuevo;
            nuevo.arriba = aux;
        }
        return nuevo;
    }

}
