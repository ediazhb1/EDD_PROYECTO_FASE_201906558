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

    public void PrimeroColorAtendido(){
        Imagenes tmp11 = inicio;
        //Borrando el primero de la cola de clientes
        if(tmp11 != null && tmp11.tipo.equals("COLOR")){
            inicio = tmp11.siguiente;
            tmp11.siguiente = null;
        }


        while ( tmp11 != null){
            tmp11 = tmp11.siguiente;
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
