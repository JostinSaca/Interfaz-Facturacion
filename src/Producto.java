public class Producto {

    private String Nombre;
    private int cantidad;
    private double precioUnitario;

    public Producto(String nombre, int cantidad, double precioUnitario) {
        Nombre = nombre;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }


    public double calcularSubtotal(){

        return cantidad*precioUnitario;
    }

    public double calcularDescuento(){

        if(cantidad>6){
            return calcularSubtotal()*0.15;
        }
        return 0;
    }

    public double calcularTotalProducto(){
        return calcularSubtotal()-calcularDescuento();
    }



    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
