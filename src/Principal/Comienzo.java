package Principal;

public class Comienzo {
    ListaVentanilla LV = new ListaVentanilla();

    public void trabajo(int NoVentanillas){
        for(int i = 1; i<= NoVentanillas;i++){
            LV.abrirVentanilla(i,"vacio","Libre",0,0,0);
        }
    }

    public String llena;
    public void disponible(){
        llena = LV.llena();
    }

    public void ImprimirVentana(){
        LV.mostrarVentanilla();
    }

    public void atender(String id,String nombre_cliente,int img_color,int img_bw){
        LV.atender(id,nombre_cliente,img_color,img_bw);
    }

}
