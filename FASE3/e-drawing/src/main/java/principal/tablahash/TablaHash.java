package principal.tablahash;

public class TablaHash {
    public static NodoHash[] tabla;
    NodoHash primero,ultimo;

    int contador;
    int nodoReserva;

    public TablaHash() {
        tabla  = new NodoHash[37];
        nodoReserva = 1;
        contador = 0;
        primero = ultimo = null;
    }

    public void insertar(long DPI,String nombre,String apellido, String licencia, String genero, String tel, String dire){
        NodoHash nuevo = new NodoHash(DPI,nombre,apellido,licencia,genero,tel,dire);
        contador++;
        long indice = ObtenerLlave(DPI);
        if(existeLlave((int) indice)){
            indice = colision((int) indice,contador);
        }

        tabla[(int) indice] = nuevo;

        if( (double) contador/tabla.length>0.75){
            tabla = reHashs(tabla);
        }
    }

    public void imprimir(){
        for (int i = 0; i < tabla.length; i++) {
            if(tabla[i] == null){
                System.out.println(i+" | null");
            }else{
                System.out.println(i+" | DPI: "+tabla[i].DPI + " | Nombre: " + tabla[i].nombre+" | Apellido:" + tabla[i].apellido);
            }
        }
    }

    public String nombres(){
        String nombre ="";
        for (int i = 0; i < tabla.length; i++) {
            if(tabla[i] == null){
            }else{
                nombre += "\""+tabla[i].nombre+ "\",\n";
            }
        }
        return nombre;
    }

    public NodoHash[] reHashs(NodoHash[] tabla){
        NodoHash[] nuevatabla = new NodoHash[valsig(tabla.length)];
        for (int i = 0; i < tabla.length ; i++) {
            nuevatabla[i] = tabla[i];
        }
        return  nuevatabla;
    }

    public int valsig(int actual){
        int sig = actual;
        int contador=2;
        boolean primo = false;

        while(!primo){
            sig++;
            while (sig > contador){
                primo = true;
                if(sig % contador == 0){
                    primo = false;
                    break;
                }
                contador++;
            }
            contador =2;
        }

        return sig;
    }

    public int colision(int llave,int i){
        do {
            llave = ((llave % 7) + 1)*i;
        } while (existeLlave(llave));
        return llave;
    }

    public boolean existeLlave(int llave){
        if(tabla[llave] == null){
            return false;
        }else{
            return true;
        }
    }

    public long ObtenerLlave(long DPI){
        long llave = 0;
        llave = DPI % tabla.length;
        return llave;
    }
}
