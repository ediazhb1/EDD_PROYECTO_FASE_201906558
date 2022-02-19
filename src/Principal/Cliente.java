package Principal;

public class Cliente {

    String id_cliente;
    String nombre_cliente;
    int img_color;
    int img_bw;
    Cliente siguiente;

    public Cliente(String id_cliente,String nombre_cliente,int img_color,int img_bw) {
        this.id_cliente = id_cliente;
        this.nombre_cliente = nombre_cliente;
        this.img_color = img_color;
        this.img_bw = img_bw;
        this.siguiente = null;
    }
}
