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


    public void mostrarVentanilla(){
        Ventanilla tmp1 = inicio;
        System.out.println("---------------------------Lista de Ventanillas (0 = Libre)---------------------------");
        while ( tmp1 != null){
            if(tmp1.siguiente == null) {
                System.out.print("[No." + tmp1.no + " Estado " + tmp1.estado + " ("+tmp1.nombre_cliente+")] -> ");
            }else{
                System.out.print("[No." + tmp1.no + " Estado " + tmp1.estado + " ("+tmp1.nombre_cliente+")] -> ");
            }

            tmp1 = tmp1.siguiente;
        }
        if(tmp1 == null){
            System.out.println("NULL");
        }

        System.out.println("");
    }

    //public void atender(String id,String nombre_cliente,int img_color,int img_bw){
    public void atender(String id,String nombre_cliente,int img_color,int img_bw){
        Ventanilla tmp2 = inicio;
        while (tmp2 != null){
            if (tmp2.estado == 0){
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

}
