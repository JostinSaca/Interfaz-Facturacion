import java.util.Scanner;

public class Sistema {

    Scanner sc = new Scanner(System.in);

    Producto productos[];
    boolean creados = false;


    public void menu(){

        int opcion;

        do{

            System.out.println("\n--- MENU ---");
            System.out.println("1. Crear productos");
            System.out.println("2. Vender productos");
            System.out.println("3. Imprimir factura");
            System.out.println("4. Salir");

            System.out.print("Opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();


            switch(opcion){

                case 1:
                    if(!creados){
                        crearProductos();
                    }
                    else{
                        System.out.println(
                                "Ya existen productos registrados."
                        );
                    }
                    break;

                case 2:
                    venderProductos();
                    break;

                case 3:
                    imprimirFactura();
                    break;

                case 4:
                    System.out.println("Fin del programa");
                    break;

                default:
                    System.out.println("Opcion invalida");

            }

        }while(opcion!=4);

    }



    public void crearProductos(){

        System.out.print(
                "¿Cuantos productos desea crear?: "
        );

        int n = sc.nextInt();
        sc.nextLine();

        productos = new Producto[n];


        for(int i=0;i<productos.length;i++){

            System.out.println("\nProducto "+(i+1));

            System.out.print("Nombre: ");
            String nombre = sc.nextLine();

            System.out.print("Cantidad: ");
            int cantidad = sc.nextInt();

            System.out.print("Precio unitario: ");
            double precio = sc.nextDouble();

            sc.nextLine();

            productos[i]= new Producto(
                    nombre,
                    cantidad,
                    precio
            );

        }

        creados=true;

        System.out.println("Productos registrados.");

    }



    public void venderProductos(){

        if(!creados){
            System.out.println(
                    "Primero debe crear productos."
            );
            return;
        }


        for(int i=0;i<productos.length;i++){

            System.out.println(
                    "\nProducto: "
                            +productos[i].getNombre()
            );

            System.out.println(
                    "Stock actual: "
                            +productos[i].getCantidad()
            );

            System.out.print(
                    "Cantidad vendida: "
            );

            int venta=sc.nextInt();

            if(venta<=productos[i].getCantidad()){

                int nuevoStock=
                        productos[i].getCantidad()-venta;

                productos[i].setCantidad(
                        nuevoStock
                );

            }

            else{
                System.out.println(
                        "No hay suficiente stock"
                );
            }

        }

        sc.nextLine();

    }



    public void imprimirFactura(){

        if(!creados){
            System.out.println(
                    "No hay productos registrados."
            );
            return;
        }

        Factura factura =
                new Factura(productos);

        factura.imprimirFactura();

    }

}