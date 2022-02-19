package Principal;

public class Ventanilla {
    int no;
    String id;
    String nombre_cliente;
    int img_color;
    int img_bw;
    int estado;
    Ventanilla siguiente;

    public Ventanilla(int nos,String id,String nombre_cliente,int img_color,int img_bw, int estado) {
        //PilaImagenes PI = new PilaImagenes();
        this.no = nos;
        this.id = id;
        this.nombre_cliente = nombre_cliente;
        this.img_color = img_color;
        this.img_bw = img_bw;
        this.estado = estado;

        this.siguiente = null;
    }

}
