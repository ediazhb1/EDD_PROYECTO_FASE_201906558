package Principal;

public class Imagenes {
    int img;
    Imagenes siguiente;

    public Imagenes(int img) {
        this.img= img;
    }

    public Imagenes(int img, Imagenes n) {
        this.img = img;
        this.siguiente = n;
    }
}
