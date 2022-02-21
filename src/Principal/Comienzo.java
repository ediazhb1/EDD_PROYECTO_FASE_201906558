package Principal;

import java.io.FileWriter;
import java.io.PrintWriter;

public class Comienzo {
    ListaVentanilla LV = new ListaVentanilla();
    public String llena;
    String graph ="";
    public int TotalVentana;

    public void trabajo(int NoVentanillas){
        for(int i = 1; i<= NoVentanillas;i++){
            LV.abrirVentanilla(i,"vacio","Libre",0,0,0);
        }
        TotalVentana = NoVentanillas;
    }

    public void disponible(){
        llena = LV.llena();
    }

    public void ImprimirVentana(){
        LV.mostrarVentanilla();
    }

    public void imprimirPila(){
        System.out.println("--------------------------------Pila de imagenes--------------------------------");
        for(int i = 1; i<= TotalVentana;i++) {
            Ventanilla conexion = LV.BuscarNo(i);
            Imagenes inicio = conexion.inicio;
            if (inicio == null){
                System.out.println("-Pila de la ventanilla "+ i +" Null");
            }
            while (inicio != null) {
                System.out.print("-Pila de la ventanilla "+ i +" ");
                System.out.println("[id: " + inicio.id + " (" + inicio.img + inicio.tipo + ")]");
                inicio = inicio.siguiente;
            }
        }
    }

    public void grafo(){
        String StrinVent = "";
        int p = 0,reten;
        graph ="";
        graph += "digraph G { \n";
        graph += "label = \"Lista Ventanillas\"\n";
        StrinVent = LV.gventanilla();
        graph += StrinVent;

        for(int i = 1; i<= TotalVentana;i++) {
            Ventanilla conexion = LV.BuscarNo(i);
            Imagenes inicio = conexion.inicio;
            reten = p;
            if (inicio == null) {
                graph += "v" + i + "->" + "Null" + " [penwidth=2, arrowhead=none];\n";
            }else{
                while (inicio != null) {
                    graph += "p" +p+"[label=\"" + inicio.tipo+"\"];\n";
                    p++;
                    inicio = inicio.siguiente;
                }

                graph += "v" + i + "->" + "p" + reten + " [penwidth=2, arrowhead=none];\n";

                for(int a = reten; a < p-1; a++){
                    graph += "p"+a +"-> p" +(a+1)+ " [penwidth=2, arrowhead=none];\n";
                }
            }
        }

        graph += "}";

        GenerarGrafo();
    }

    public void GenerarGrafo(){
        FileWriter fichero = null;
        PrintWriter pw = null;
        /*Crea un archivo con extesión .dot con el texto de la variable graph*/
        try{
            fichero = new FileWriter("Ventanillas.dot");
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
        try{
            ProcessBuilder proceso;
            proceso = new ProcessBuilder("dot", "-Tpng","-o","GraphVentanilla.png","Ventanillas.dot");
            proceso.redirectErrorStream(true);
            proceso.start();
        }catch (Exception e){
            System.out.println("Error en generar png de la cola");
        }
    }

    public void atender(String id,String nombre_cliente,int img_color,int img_bw){
        LV.atender(id,nombre_cliente,img_color,img_bw); //Los datos y las impresiones son entregadas a la ventanilla
        LV.RecibirOrden();

    }
}
