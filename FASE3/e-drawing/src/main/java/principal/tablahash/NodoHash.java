package principal.tablahash;

public class NodoHash {
    long DPI;
    public String nombre;
    String apellido;
    String licencia;
    String genero;
    String tel;
    String dire;
    NodoHash siguiente;

    public NodoHash(long DPI,String nombre,String apellido, String licencia, String genero, String tel, String dire) {
        this.DPI = DPI;
        this.nombre = nombre;
        this.apellido = apellido;
        this.licencia = licencia;
        this.genero = genero;
        this.tel = tel;
        this.dire = dire;
        this.siguiente = null;
    }



}
