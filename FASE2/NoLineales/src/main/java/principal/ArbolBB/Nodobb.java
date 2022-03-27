package principal.ArbolBB;

import principal.nolineales.Matriz;

public class Nodobb extends Matriz {

    //Valores
    int id;
    int niveles;

    Nodobb derecha;
    Nodobb izquierda;

    public Nodobb(int id, int nivel){
        this.id = id;
        this.niveles = nivel;

        this.derecha = null;
        this.izquierda = null;
    }

    public int getId() {
        return id;
    }

    public Nodobb getDerecha() {
        return derecha;
    }

    public int getNiveles() {
        return niveles;
    }

    public Nodobb getIzquierda() {
        return izquierda;
    }


    public void prueba(){
        System.out.println(niveles);
    }
    public void insertar(int id){
        if(this.izquierda == null && this.derecha == null) {

            this.niveles++;

        }
        if(id < this.id){
            if(this.izquierda == null){
                this.izquierda = new Nodobb(id,niveles);
            }else{
                this.izquierda.insertar(id);
            }
        }else{
            if(this.derecha == null){
                this.derecha = new Nodobb(id,niveles);
            }else{
                this.derecha.insertar(id);
            }
        }


    }

}
