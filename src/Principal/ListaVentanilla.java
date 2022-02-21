package Principal;

public class ListaVentanilla {

    public Ventanilla inicio;

    public ListaVentanilla() {
        this.inicio = null;
    }

    public void abrirVentanilla(int nos,String id,String nombre_cliente,int img_color,int img_bw,int estado) {
        Ventanilla nuevo = new Ventanilla(nos, id,nombre_cliente,img_color,img_bw,estado);
        if (inicio == null) {
            inicio = nuevo;
        } else {
            Ventanilla tmp = inicio;
            while (tmp.siguiente != null){
                tmp = tmp.siguiente;
            }
            tmp.siguiente = nuevo;
        }
    }

    public void mostrarVentanilla(){
        Ventanilla tmp1 = inicio;
        System.out.println("---------------------------Lista de Ventanillas (Estado 0 = Libre)---------------------------");
        while ( tmp1 != null){
            if(tmp1.siguiente == null) {
                System.out.print("[No." + tmp1.no + " Estado " + tmp1.estado + " ("+tmp1.nombre_cliente+tmp1.img_color+","+tmp1.img_bw+" )] -> ");
            }else{
                System.out.print("[No." + tmp1.no + " Estado " + tmp1.estado + " ("+tmp1.nombre_cliente+" "+tmp1.img_color+","+tmp1.img_bw+" )] -> ");
            }

            tmp1 = tmp1.siguiente;
        }
        if(tmp1 == null){
            System.out.println("NULL");
        }

        System.out.println("");
    }

    public String gventanilla(){
        String gventan ="";
        Ventanilla tmp1 = inicio;
        int a =0;
        while ( tmp1 != null){
            gventan +="a"+a + "[label=\"CLIENTE: "+tmp1.id+"\n IMGC: "+tmp1.img_color+"\n IMGBW: "+tmp1.img_bw+"\"];\n";
            a++;
            tmp1 = tmp1.siguiente;
        }
        for(int i=1; i<=a;i++){
            gventan +="v"+i + "[label=\"Ventanilla " + i+"\"];\n";
        }
        gventan += "{rank = same;\n";
        for(int c = 0; c<=a-1; c++){
            gventan += "a"+c +"-> v" + (c+1) +";\n";
        }

        gventan += "}\n";

        return  gventan;
    }

    public Ventanilla BuscarNo(int nos){
        Ventanilla aux = inicio;
        while(aux != null) {
            if (aux.no == nos) {
                return aux;
            }
            aux = aux.siguiente;
        }
        return null;
    }

    public void RecibirOrden(){
        Ventanilla aux = inicio;
        while(aux != null) {
            if(aux.estado == 1) {
                aux.estado = 2;
            }else if(aux.estado == 2){
                Ventanilla conexion = BuscarNo(aux.no);
                if(aux.img_color == 0 && aux.img_bw > 0){
                    conexion.apilar(aux.id, 1, "BW");
                    System.out.println("La pila de la ventilla " + aux.no + "apilo imagen de blanco y negro" );
                    aux.img_bw--;
                }

                if (aux.img_color > 0) {
                    conexion.apilar(aux.id,1,"COLOR");
                    System.out.println("La pila de la ventilla " + aux.no + " apilo imagen de color" );
                    aux.img_color--;
                }

                if (aux.img_color == 0 && aux.img_bw == 0) {
                    aux.estado = 0;
                    aux.id = "vacio";
                    aux.nombre_cliente = "Libre";

                }
            }
            aux = aux.siguiente;
        }
    }

    public void atender(String id,String nombre_cliente,int img_color,int img_bw){
        Ventanilla tmp2 = inicio;
        while (tmp2 != null){
            if (tmp2.estado == 0){
                System.out.println("El cliente " + nombre_cliente+" ingresara a la ventanilla "+tmp2.no);
                tmp2.estado = 1;
                tmp2.id = id;
                tmp2.nombre_cliente = nombre_cliente;
                tmp2.img_color = img_color;
                tmp2.img_bw = img_bw;
                break;
            }
            tmp2 = tmp2.siguiente;
        }
    }

    public String llena(){
        Ventanilla tmp3 = inicio;
        while(tmp3 != null){
            if(tmp3.estado ==1 && tmp3.siguiente == null){
                return "VentanillasLlenas";
            }
            tmp3 = tmp3.siguiente;
        }

        return "VentanillasLibres";
    }


}
