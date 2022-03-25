package principal.ArbolBB;

import principal.nolineales.Matriz;

public class Nodobb extends Matriz {

    //Valores
    int id;
    Nodobb derecha;
    Nodobb izquierda;

    public Nodobb(int id){
        this.id = id;
        this.derecha = null;
        this.izquierda = null;
    }

    public int getId() {
        return id;
    }

    public Nodobb getDerecha() {
        return derecha;
    }

    public Nodobb getIzquierda() {
        return izquierda;
    }


    public void insertar(int id){
        if(id < this.id){
            if(this.izquierda == null){
                this.izquierda = new Nodobb(id);
            }else{
                this.izquierda.insertar(id);
            }
        }else{
            if(this.derecha == null){
                this.derecha = new Nodobb(id);
            }else{
                this.derecha.insertar(id);
            }
        }

    }

}
