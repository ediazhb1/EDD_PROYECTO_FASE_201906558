package principal.nolineales;

public class Matriz {
    public Nodo root;
    int MaxCol;
    int MaxFila;
    public String graph ="";

    public Matriz() {
         root = new Nodo(-1,-1, "white");
    }

    public void mayorcol(){
        Nodo aux = root;

        while (aux != null){
            if(aux.abajo == null && aux.x == -1){
                MaxCol = aux.y;
            }
            aux = aux.abajo;
        }
    }

    public void mayorfila(){
        Nodo aux = root;

        while (aux != null){
            if(aux.siguiente == null && aux.y == -1){
                MaxFila = aux.x;
            }
            aux = aux.siguiente;
        }
    }


    public void graficar(String Capa){
        int x = 0;
        int y = 0;
        graph = "digraph G {\n" +
                "node [shape=plaintext];\n" +
                "label=\"Capa "+Capa+"\";\n" +
                "some_node [\n" +
                "label=<\n" +
                "<table border=\"0\" cellborder=\"0\" cellspacing=\"0\" width=\"100%\" height=\"100%\">\n";
        
        while (y != (MaxCol+1)){
            graph += "<tr>\n";
            while (x != (MaxFila+1)){
                Nodo pixel = buscarpixel(x, y);
                if(pixel != null){
                    graph += "  <td bgcolor=\""+pixel.color+"\" width=\"1\" height=\"1\"></td>\n";
                }else{
                    graph += "  <td bgcolor=\"white\" width=\"1\" height=\"1\"></td>\n";
                }
                x++;
            }
            x =0;
            graph += "</tr>\n";
            y++;
        }

        graph += "</table>>\n" +
                "];\n" +
                "}";
        System.out.println(graph);
    }


    public Nodo buscarpixel(int x, int y){
        Nodo aux = root;
        while (aux != null){
            String txt = "";
            Nodo aux2 = aux;
            while (aux2 != null){
                if(aux2.x == x && aux2.y == y){
                    return aux2;
                }
                aux2 = aux2.siguiente;
            }
            aux = aux.abajo;
        }
        return null;
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
        Nodo nuevo = new Nodo(columna, -1, "white");////////
        return orden_columna(nuevo, nodo_col);
    }

    public Nodo crear_fila(int y) {
        Nodo nodo_fila = root;
        Nodo nuevo = new Nodo(-1, y, "white");////////////
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
