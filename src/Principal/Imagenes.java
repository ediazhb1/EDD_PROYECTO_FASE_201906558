package Principal;

public class Imagenes {
    int img;
    String tipo;
    String id;
    Imagenes siguiente;

    public Imagenes(String id,int img,String tipo) {
        this.id = id;
        this.img = img;
        this.tipo = tipo;
        this.siguiente = null;
    }

}
