public class Factura {

    private Producto productos [];

    private final double iva = 0.15;


    public Factura(Producto[] productos) {
        this.productos = productos;
    }

    public double calcularSubtotalCompra(){
        double subTotal = 0;

        for (int i =0; i< productos.length; i++){
            if (productos[i] != null) {
                subTotal += productos[i].calcularTotalProducto();
            }
        }
        return subTotal;
    }

    public double calcularIVA(){
        return calcularSubtotalCompra()*iva;
    }

    public double calcularTotalFinal() {
        return calcularSubtotalCompra()+ calcularIVA();
    }

    public void imprimirFactura(){

        for (int i = 0; i< productos.length; i++){
            if (productos[i]!=null){
                System.out.println("------------------");
                System.out.println("Producto: "+productos[i].getNombre());
                System.out.println("Cantidad: "+productos[i].getCantidad());
                System.out.println("Precio Unitario: "+productos[i].getPrecioUnitario());
                System.out.println("Descuento: "+productos[i].calcularDescuento());
                System.out.printf("Total: %.2f",productos[i].calcularTotalProducto());
            }
        }

        System.out.println("------------------");
        System.out.printf("Subtotal: %.2f",calcularSubtotalCompra());
        System.out.printf("IVA: %.2f",calcularIVA());
        System.out.printf("Total: %.2f",calcularTotalFinal());

    }
}
