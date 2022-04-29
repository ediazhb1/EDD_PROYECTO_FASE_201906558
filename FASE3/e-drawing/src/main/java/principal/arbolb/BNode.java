package principal.arbolb;

import javafx.stage.Stage;

public class BNode {

     BLeaf izquierdo, derecho;
     BNode anterior, siguiente;
     public long dpi;
     public String nombre,usuario,correo,contra,tel,dir,muni;
     public Stage perfil;

    public BNode(long dpi, String nombre, String usuario, String correo, String contra, String tel, String dir, String muni, Stage perfil)
    {
        this.dpi = dpi;
        this.nombre = nombre;
        this.usuario = usuario;
        this.correo = correo;
        this.contra = contra;
        this.tel = tel;
        this.dir = dir;
        this.muni = muni;
        this.perfil = perfil;
        this.anterior = null;
        this.siguiente = null;
        this.derecho = null;
        this.izquierdo = null;
    }
}
