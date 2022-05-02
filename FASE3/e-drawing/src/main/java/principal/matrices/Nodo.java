package principal.matrices;

public class Nodo {
    Nodo siguiente,anterior,arriba,abajo; //Apuntadores
    int x,y; //Contenido del nodo
    public int peso;

    public Nodo(int x,int y,int peso) {
        this.siguiente = null;
        this.anterior = null;
        this.arriba = null;
        this.abajo = null;

        this.x = x;
        this.y = y;
        this.peso = peso;
    }
}
