package Principal;

public class Colaicolor {

    public imprecolor inicio;

    public Colaicolor() {
        this.inicio = null;
    }

    public void agregarcolor(String img, int imgcolor) {
        imprecolor nuevo = new imprecolor(img, imgcolor);
        if (inicio == null) {
            inicio = nuevo;
        } else {
            imprecolor tmp = inicio;
            while (tmp.siguiente != null) {
                tmp = tmp.siguiente;
            }
            tmp.siguiente = nuevo;
        }
    }

    public void mostrarimprecolor(){
        imprecolor tmp1 = inicio;
        System.out.println("---------------------------IMPRESORA DE COLOR---------------------------");
        if(tmp1 == null){
            System.out.println("NULL");
        }
        while ( tmp1 != null){
            System.out.print("[No." + tmp1.imgcolor + " de Color ] ->");

            tmp1 = tmp1.siguiente;
        }


        System.out.println("");
    }
}
