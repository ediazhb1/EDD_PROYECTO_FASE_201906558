package principal.matrices;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Paths;

public class Matriz {
    public Nodo root;
    int MaxCol;
    int MaxFila;
    public String graph ="";
    public String dotgraph ="";

    public Matriz() {
        root = new Nodo(0,0, 0);
    }

    public void mayorcol(){
        Nodo aux = root;

        while (aux != null){
            if(aux.abajo == null && aux.x == 0){
                MaxCol = aux.y;
            }
            aux = aux.abajo;
        }
    }

    public void mayorfila(){
        Nodo aux = root;

        while (aux != null){
            if(aux.siguiente == null && aux.y == 0){
                MaxFila = aux.x;
            }
            aux = aux.siguiente;
        }
    }

    public void dot(String Capa){
        dotgraph = """
                digraph G {
                node [shape=box]
                """;
        dotgraph += "label = \"Matriz de la Capa "+ Capa +"\"\n";
        dotgraph += "fontsize  = " + (MaxCol*3);
        for(int i = 0; i<= MaxCol;i++){
            for(int j = 0; j<= MaxFila;j++){
                Nodo pixel = buscarpixel(j, i);
                if(pixel != null){
                    dotgraph += "Q"+i+"o"+j+"[fillcolor =\""+pixel.peso+"\", style=\"filled\", label =\"\""+"];\n";
                }else{
                    dotgraph += "Q"+i+"o"+j+"[label =\"\""+"];\n";
                }

            }
        }
        for(int i = 0; i<= MaxCol;i++){
            dotgraph += "{rank = same; ";
            for(int j = 0; j<= MaxFila;j++){
                dotgraph += "Q"+i+"o"+j+"; ";
            }
            dotgraph += "}\n";
        }

        for(int i = 0; i<= MaxCol;i++){
            for(int j = 0; j<MaxFila;j++){
                dotgraph += "Q"+i+"o"+j+" -> Q"+i+"o"+(j+1)+"[dir=both,arrowsize=0.5]\n";
            }
            dotgraph += "\n";
        }

        for(int i = 0; i<= MaxFila;i++){
            for(int j = 0; j< MaxCol;j++){
                dotgraph += "Q"+j+"o"+i+" -> Q"+(j+1)+"o"+i+"[dir=both]\n";
            }
            dotgraph += "\n";
        }
        dotgraph += "}";
    }


    public void graficar(String Capa){
        int x = 0;
        int y = 0;
        graph = "graph G {\n" +
                "graph [dpi = 300.00 ];\n"+
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
                    graph += "  <td bgcolor=\""+pixel.peso+"\" width=\"1\" height=\"1\"></td>\n";
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
        GenerarGrafo(Capa);
        //System.out.println(graph);
    }

    public void GenerarGrafo(String Capa){
        FileWriter fichero = null;
        PrintWriter pw = null;
        /*Crea un archivo con extesión .dot con el texto de la variable graph*/
        try{
            fichero = new FileWriter("Capas.dot");
            pw = new PrintWriter(fichero);
            pw.write(graph);
            pw.close();
            fichero.close();
        }catch(Exception e){
            System.out.println("Error en generar dot de la capa");
        }finally {
            if(pw!=null){
                pw.close();
            }
        }
        try{

            ProcessBuilder proceso;
            String dir = Paths.get("")
                    .toAbsolutePath()
                    .toString();
            proceso = new ProcessBuilder("dot", "-Tpng", "-o", dir+"/"+"Capa"+Capa+".png","Capas.dot");
            proceso.redirectErrorStream(true);
            proceso.start();
        }catch (Exception e){
            System.out.println("Error en generar png de la capa");
        }
    }


    public Nodo buscarpixel(int x, int y){
        Nodo aux = root;
        while (aux != null){
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
                txt += "["+ aux2.x+","+aux2.y+" | "+aux2.peso+"] -> ";
                aux2 = aux2.siguiente;
            }
            System.out.println(txt);
            aux = aux.abajo;
        }
    }




    public void insertarNodo(int x,int y,int peso){
        Nodo nuevo = new Nodo(x,y,peso);
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
        Nodo nuevo = new Nodo(columna, 0, 0);////////
        return orden_columna(nuevo, nodo_col);
    }

    public Nodo crear_fila(int y) {
        Nodo nodo_fila = root;
        Nodo nuevo = new Nodo(0, y, 0);////////////
        return orden_fila(nuevo, nodo_fila);
    }

    public Nodo orden_columna(Nodo nuevo, Nodo tmp){
        Nodo aux = tmp;
        boolean insertado = false;

        while(true){
            if(nuevo.x == aux.x){
                aux.y = nuevo.y;
                aux.peso = nuevo.peso;
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
                aux.peso = nuevo.peso;
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
