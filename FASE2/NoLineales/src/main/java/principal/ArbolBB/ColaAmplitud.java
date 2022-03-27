package principal.ArbolBB;

public class ColaAmplitud {
    public Amplitud inicio;

    public ColaAmplitud() {
        inicio = null;
    }

    public void insertarCola(Nodobb id_cliente) {
        Amplitud nuevo = new Amplitud(id_cliente);

        if (inicio == null) {
            inicio = nuevo;
        } else {
            Amplitud tmp = inicio;
            while (tmp.siguiente != null){
                tmp = tmp.siguiente;
            }
            tmp.siguiente = nuevo;
        }
    }





    public void EliminarPrimero(){
        Amplitud tmp11 = inicio;
        //Borrando el primero de la cola de clientes
        if(tmp11 != null){
            inicio = tmp11.siguiente;
            tmp11.siguiente = null;
        }


        while ( tmp11 != null){
            tmp11 = tmp11.siguiente;
        }
    }


}
