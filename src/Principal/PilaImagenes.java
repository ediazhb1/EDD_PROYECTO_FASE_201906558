package Principal;

public class PilaImagenes {
    Imagenes inicio,fin;

    public PilaImagenes() {
        this.inicio = null;
        this.fin = null;
    }

    public void apilar(int img){
        inicio = new Imagenes(img, inicio);
        if (fin == null){
            fin = inicio;
        }
    }

    public void mostrar(){
        Imagenes auxiliar = inicio;
        while(auxiliar != null){
            System.out.print(auxiliar.img);
            if(auxiliar.siguiente == null){
                System.out.println("");
            }
            auxiliar = auxiliar.siguiente;
        }
    }
}
