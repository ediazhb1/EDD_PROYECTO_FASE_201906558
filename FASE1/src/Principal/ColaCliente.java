package Principal;

import java.io.FileWriter;
import java.io.PrintWriter;

public class ColaCliente {
    public Cliente inicio;
    String graph ="";

    public ColaCliente() {
        inicio = null;
    }

    public void LlegaCliente(String id_cliente,String nombre_cliente,int img_color,int img_bw) {
        Cliente nuevo = new Cliente(id_cliente, nombre_cliente, img_color,img_bw);

        if (inicio == null) {
            inicio = nuevo;
        } else {
            Cliente tmp = inicio;
            while (tmp.siguiente != null){
                tmp = tmp.siguiente;
            }
            tmp.siguiente = nuevo;
        }
    }
    public void  Buscarid(String id){
        Cliente aux = inicio;
        while(aux != null) {
            if (aux.id_cliente.equals(id)) {
                System.out.println("ID: "+ aux.id_cliente+" Nombre: " + aux.nombre_cliente+" Imagen Color: " + aux.img_color+" Imagen BW: " + aux.img_bw);
            }
            aux = aux.siguiente;
        }
    }

    public String gid(){
        Cliente tmp0 = inicio;
        return tmp0.id_cliente;
    }

    public String gnombre_cliente(){
        Cliente tmp0 = inicio;
        return tmp0.nombre_cliente;
    }

    public int gimgcolor(){
        Cliente tmp0 = inicio;
        return tmp0.img_color;
    }

    public int gimgbw(){
        Cliente tmp0 = inicio;
        return tmp0.img_bw;
    }


    public void PrimeroAtendido(){
        Cliente tmp11 = inicio;
        //Borrando el primero de la cola de clientes
        if(tmp11 != null){
            inicio = tmp11.siguiente;
            tmp11.siguiente = null;
        }


        while ( tmp11 != null){
            tmp11 = tmp11.siguiente;
        }
    }

    public void imprimirCliente(){
        Cliente tmp1 = inicio;
        System.out.println("--------------------------------Cola de Recepción--------------------------------");
        while ( tmp1 != null){
            if(tmp1.siguiente == null) {
                System.out.print("[id " + tmp1.id_cliente + " | nombre " + tmp1.nombre_cliente + " | color "+ tmp1.img_color+" | bw "+tmp1.img_bw+"] -> ");
            }else{
                System.out.print("[id " + tmp1.id_cliente + " | nombre " + tmp1.nombre_cliente + " | color "+ tmp1.img_color+" | bw "+tmp1.img_bw+"] -> ");
            }

            tmp1 = tmp1.siguiente;
        }
        if(tmp1 == null){
            System.out.println("NULL");
        }

        System.out.println("");
    }


    public void grafo(){
        graph += "digraph G { \n";
        graph += "label = \"Cola Recepción\"\n";
        Cliente tmp1 = inicio;
        int i = 0;
        while ( tmp1 != null){
            graph += "a"+i+"[label=\"CLIENTE: "+tmp1.id_cliente+"\n IMGC: "+tmp1.img_color+"\n IMGBW: "+tmp1.img_bw+"\"];\n";
            i++;
            tmp1 = tmp1.siguiente;
        }
        graph += "{rank = same;\n";

        for(int a = 0; a<i-1; a++){
            graph += "a"+a +"-> a" + (a+1) +";\n";
        }
        graph += "}\n";
        graph += "}";
        //System.out.println(graph);
        GenerarGrafo();
    }

    public void GenerarGrafo(){
        FileWriter fichero = null;
        PrintWriter pw = null;
        /*Crea un archivo con extesión .dot con el texto de la variable graph*/
        try{
            fichero = new FileWriter("ColaRecepcion.dot");
            pw = new PrintWriter(fichero);
            pw.write(graph);
            pw.close();
            fichero.close();
        }catch(Exception e){
            System.out.println("Error en generar dot de la cola");
        }finally {
            if(pw!=null){
                pw.close();
            }
        }
        /*Convierte el archivo con extesión .dot a .png */
        try{
            ProcessBuilder proceso;
            proceso = new ProcessBuilder("dot", "-Tpng","-o","GraphRecepcion.png","ColaRecepcion.dot");
            proceso.redirectErrorStream(true);
            proceso.start();
        }catch (Exception e){
            System.out.println("Error en generar png de la cola");
        }
    }


}
