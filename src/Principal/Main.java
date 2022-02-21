package Principal;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Massive massive = new Massive();
        Comienzo comienzo = new Comienzo();
        int pasos = 1;

        boolean preparativo = false;
        while (true){
            menu();
            try{
                System.out.println("Seleccione una opcion (número): ");
                int opcion=0;
                String Srutasou;
                String idcliente;
                int Iventanillasou;
                Scanner ingreso = new Scanner(System.in);
                opcion = ingreso.nextInt();

                if(opcion>0 && opcion <9){
                    if(opcion == 7) {
                        System.out.println("Ingrese el id del cliente");
                        Scanner id = new Scanner(System.in);
                        idcliente = id.nextLine();
                        massive.busqueda(idcliente);

                    }else if(opcion == 1){
                        System.out.println("Carga masiva de clientes");
                        System.out.println("Ingrese la ruta del archivo: ");
                        Scanner rutasou = new Scanner(System.in);
                        Srutasou = rutasou.nextLine();

                        massive.Analyzer(Srutasou);

                    }else if(opcion == 2){
                        System.out.println("Cantidad de ventanillas");
                        System.out.println("Ingrese la cantidad de ventanillas: ");
                        Scanner ventanillasou = new Scanner(System.in);
                        Iventanillasou = ventanillasou.nextInt();
                        comienzo.trabajo(Iventanillasou); //Abre n cantidad de ventanillas con datos vacios y libres
                        preparativo = true;

                    }else if(opcion == 3){
                        if(preparativo) {
                            System.out.println("Ejecutar paso");
                            System.out.println("---------------------------PASO "+pasos+"---------------------------");
                            massive.primeros(); //Encuentra los primeros clientes de la cola que fueron cargados
                            massive.realizarPaso(); //Agrega Aleatoriamente clientes a la cola.
                            comienzo.disponible(); //Comprueba si estan ocupadas las ventanillas
                            if(comienzo.llena.equals("VentanillasLlenas")){
                                System.out.println("Las Ventanillas estan llenas, espere turno");
                            }else{
                                massive.PaseAdelantes(); //Se borra de la lista clientes el primero de la lista
                                comienzo.atender(massive.pid, massive.pnombre, massive.pimgcolor, massive.pimgbw); //Pasa el cliente a ventanilla
                            }
                            pasos ++;
                        }else{
                            System.out.println("No a ingresado la cantidad ventanillas");
                        }

                    }else if(opcion == 4){
                        System.out.println("Estado en memoria de las estructuras");
                        massive.OrdenImprimir();
                        comienzo.ImprimirVentana();
                        comienzo.imprimirPila();
                        comienzo.imprimircolores();

                    }else if(opcion == 5){
                        System.out.println("Reportes");
                        System.out.println("Visualizar estructuras");
                        massive.generardot();
                        comienzo.grafo();

                    }else if(opcion == 6){
                        System.out.println("Eddy Fernando Díaz Galindo");
                        System.out.println("201906558");
                        System.out.println("Estructura de Datos");
                        System.out.println("Ingenieria en Ciencias y Sistemas");
                        System.out.println("5to Semestre");
                    }else{
                        System.out.println("Adios! Vuelva pronto");
                        break;
                    }
                }else{
                    System.out.println("Error, solo de aceptan numeros del 1 al 8");
                }
            }catch(Exception e){
                System.out.println("Error de menu, ingrese solamente numeros" + e);
            }
        }
    }

    private static void menu(){
        System.out.println("1. Carga masiva de clientes");
        System.out.println("2. Cantidad de ventanillas");
        System.out.println("3. Ejecutar paso");
        System.out.println("4. Estado en memoria de las estructuras");
        System.out.println("5. Reportes");
        System.out.println("6. Acerca de");
        System.out.println("7. Busqueda de cliente");
        System.out.println("8. Salir");
    }

}
