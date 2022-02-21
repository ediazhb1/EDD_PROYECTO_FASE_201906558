package Principal;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Random;

/* Cola de Recepción y analisis de carga masiva*/
public class Massive {
    ColaCliente CC = new ColaCliente();
    String id_cliente;
    String nombre_cliente;
    int img_color;
    int img_bw;

    public Massive(){
    }

    public void Analyzer(String path){
        JSONParser parser = new JSONParser();

        try{
            Object obj = parser.parse(new FileReader(path));
            JSONObject jsonObject = (JSONObject) obj;

            jsonObject.keySet().forEach(keyStr
                    ->{
                if(((JSONObject) jsonObject.get(keyStr)).keySet() != null){
                    ((JSONObject) jsonObject.get(keyStr)).keySet().forEach(keyStr2
                            -> {
                        Object keyvalue2 = ((JSONObject) jsonObject.get(keyStr)).get(keyStr2);
                        if (keyStr2.equals("nombre_cliente")){
                            nombre_cliente = keyvalue2.toString();
                        }else if(keyStr2.equals("img_bw")){
                            img_bw = Integer.parseInt(keyvalue2.toString());
                        }else if(keyStr2.equals("img_color")){
                            img_color = Integer.parseInt(keyvalue2.toString());
                        }else if(keyStr2.equals("id_cliente")){
                            id_cliente = keyvalue2.toString();
                        }
                    });
                }
                NuevoCliente(id_cliente,nombre_cliente,img_color,img_bw);
            });

        }catch (Exception e){
            System.out.println("Error en parsear json "+e);
        }
    }

    public void NuevoCliente(String id, String nombre, int color, int bw){
        CC.LlegaCliente(id,nombre,color,bw);
    }

    public void OrdenImprimir(){
        CC.imprimirCliente();
    }
    public void generardot(){
        CC.grafo();
    }
    int id = 0;
    public void realizarPaso(){
        int MinCliente = 0;
        int MaxCliente = 3;

        String ids;
        int MinNombre = 0;
        int MaxNombre = 18;
        int MinImagenes = 0;
        int MaxImagenes = 4;
        String[] nombres = {"Aguilar", "Alonso", "Álvarez", "Arias", "Benítez", "Blesa", "Bravo", "Caballero", "Cabrera", "Calvo", "Cambil", "Campos", "Cano", "Carmona", "Carrasco", "Castillo", "Castro", "Díaz"};

        Random random = new Random();

        int IngresaCLiente = random.nextInt(MaxCliente - MinCliente +1) + MinCliente;
        System.out.println("Ingresaron a la cola: " + IngresaCLiente+" nuevos clientes");
        for(int i = 1; i<=IngresaCLiente; i++){
            id++;
            ids = "ran"+id;
            int NombreCliente = random.nextInt(MaxNombre + MinNombre) + MinNombre;
            int ImageColor = random.nextInt(MaxImagenes + MinImagenes) + MinImagenes;
            int ImageBW = random.nextInt(MaxImagenes + MinImagenes) + MinImagenes;
            NuevoCliente(ids,nombres[NombreCliente],ImageColor,ImageBW);
        }

    }

    public void PaseAdelantes(){
        CC.PrimeroAtendido();
    }

    public String pid;
    public  String pnombre;
    public int pimgcolor;
    public int pimgbw;

    public void primeros(){
        pid = CC.gid();
        pnombre = CC.gnombre_cliente();
        pimgcolor = CC.gimgcolor();
        pimgbw = CC.gimgbw();
    }

    public void busqueda(String ids){
        CC.Buscarid(ids);
    }
}