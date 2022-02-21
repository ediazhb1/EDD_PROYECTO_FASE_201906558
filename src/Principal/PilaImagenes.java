package Principal;

public class PilaImagenes {
    Imagenes inicio;

    public PilaImagenes() {
        this.inicio = null;
    }

    public void apilar(String id, int img, String tipo){
        Imagenes nuevo = new Imagenes(id,img,tipo);
        if (inicio == null) {
            inicio = nuevo;
        } else {
            Imagenes tmp = inicio;
            while (tmp.siguiente != null){
                tmp = tmp.siguiente;
            }
            tmp.siguiente = nuevo;
        }
    }

    public void mostrar(){
        Imagenes auxiliar = inicio;
        while(auxiliar != null){
            System.out.print("["+auxiliar.id+" "+auxiliar.img+auxiliar.tipo+"]--");
            auxiliar = auxiliar.siguiente;
        }
    }
}
