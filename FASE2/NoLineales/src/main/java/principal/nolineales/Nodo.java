package principal.nolineales;

public class Nodo {

    Nodo siguiente,anterior,arriba,abajo; //Apuntadores
    int x,y; //Contenido del nodo
    String color;

    public Nodo(int x,int y,String color) {
        this.siguiente = null;
        this.anterior = null;
        this.arriba = null;
        this.abajo = null;

        this.x = x;
        this.y = y;
        this.color = color;
    }

    public String nodostr(){
        return("(" + x + "," + y + ") = " + color);
    }

}