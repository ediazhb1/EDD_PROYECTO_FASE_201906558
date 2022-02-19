package Principal;

public class ColaCliente {
    public Cliente inicio;
    Comienzo comienzo = new Comienzo();

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
        System.out.println("---------------------------Cola de Recepción---------------------------");
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



}
