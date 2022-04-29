package principal.edrawing;

public class ListaAdyacente {
    private class ENodo {

        int ivex;//posicion del nodo conectado
        int peso;
        ENodo siguiente;//nodo siguiete en la sublista

        //constructor de la clase
        public ENodo(int ivex,int peso) {
            this.ivex = ivex;
            this.peso = peso;
            this.siguiente = null;
        }
    }

    //Vector de la lista de adyacencia
    private class VNodo {

        String data;//valor del nodo
        ENodo inicio = null;//inicio de la sublista enlazada

        //metodo de insertar en la sublista
        public void insert(int ivex, int peso) {
            ENodo nuevo = new ENodo(ivex,peso);
            if (inicio == null) {
                inicio = nuevo;
            } else {
                ENodo aux = inicio;
                while (true) {
                    if (aux.siguiente == null) {
                        aux.siguiente = nuevo;
                        break;
                    }
                    aux = aux.siguiente;
                }
            }
        }

        public void imprimir() {
            ENodo aux = inicio;
            while (aux != null) {
                System.out.print("->" + "[" + aux.ivex + " peso: "+aux.peso+"]");
                aux = aux.siguiente;
            }
        }

        public void dijk(int i, int valor){
            ENodo aux = inicio;
            int cont = 1;
            Dijkstra.cost[valor][valor] = 0;
            while (aux != null) {
                cont++;
                Dijkstra.cost[valor][aux.ivex] = aux.peso;
                System.out.print("->" + "[" + aux.ivex + " peso: "+aux.peso+"]");
                aux = aux.siguiente;
            }
            //System.out.println(cont);
            if(cont != 5){

            }
        }

        int cont = 0;

        public void graphnext(String data) {
            ENodo aux = inicio;
            ENodo aux1 = inicio;
            while (aux1 != null) {
                graph += "   c" + data + "c" + aux1.ivex + "[label =\"" + aux1.ivex + "\"];\n";
                aux1 = aux1.siguiente;
            }
            cont++;
            graph += "        set1:a" + data;
            while (aux != null) {
                graph += "->c" + data + "c" + aux.ivex;
                aux = aux.siguiente;
            }
            graph += "\n";
        }

    }

    //estructura principal de los nodos que conforman el grafo
    VNodo v[];
    //imprimir la lsita de adyacencia del grafo
    public void imprimir() {
        for (int i = 1; i < v.length; i++) {
            System.out.print(i + "[id: " + v[i].data +"]");
            v[i].imprimir();
            System.out.println("");
        }
    }

    public void matrizdijk(){
        for (int i = 1; i < v.length; i++) {
            System.out.print(i + "[id: " + v[i].data +"]");
            v[i].dijk(i,Integer.parseInt(v[i].data));
            System.out.println("");
        }
    }

    public ListaAdyacente(int vlen) {
        v = new VNodo[vlen];
        //inicializar el vector de los nodos del grafo
        for (int i = 0; i < v.length; i++) {
            v[i] = new VNodo();
        }
    }

    //insertar nodos en el grafo
    public void insert(String valor, int pos) {
        if (pos >= 0 && pos < v.length) {
            v[pos].data = valor;
            v[pos].inicio = null;
        }
    }

    //insertar conexiones en el grafo
    public void conexion(int inicio, int fin, int peso) {
        if (inicio >= 0 && inicio < v.length) {
            v[inicio].insert(fin,peso);
        }
    }



    String graph = "";

    public void graph() {
        graph = "digraph G  \n" +
                "{\n" +
                "   node[ shape = none, fontname = \"Arial\" ];\n";

        graph += "set1[ label=<\n" +
                "    <TABLE BORDER=\"0\" CELLBORDER=\"1\" CELLSPACING=\"0\" CELLPADDING=\"6\">\n";

        for (int i = 1; i < v.length; i++) {
            graph += "  <TR>\n";
            graph += ("     <TD>" + v[i].data + "</TD>\n");
            graph += ("     <TD PORT=\"a" + i + "\">■</TD>\n");
            graph += "  </TR>\n";
        }

        graph += "</TABLE>>];\n" +
                "rankdir = LR;\n" +
                "node[shape = square]\n";
        for (int i = 1; i < v.length; i++) {
            v[i].graphnext(v[i].data);
        }
        graph += "nodesep = 1; \n" +
                "}";
    }

    String nograph = "";

    public void nodirigido(String inicio , String finales, String peso){
        nograph += inicio + "--" + finales + "[xlabel = \""+peso+"\"];\n";

    }




}
