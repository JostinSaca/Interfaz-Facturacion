import javax.swing.*;

public class VentanaSistema {

    private JPanel panel1;
    private JPanel panelPrincipal;

    private JTextField txtNombre;
    private JTextField txtCantidad;
    private JTextField txtPrecio;

    private JButton crearProductoButton;
    private JButton verInventarioButton;
    private JButton venderButton;
    private JButton imprimirFacturaButton;

    private JTextArea areaSalida;


    private Producto inventario[];
    private Producto ventas[];

    private int contInventario=0;
    private int contVentas=0;



    public VentanaSistema() {

        inventario = new Producto[5];
        ventas = new Producto[5];


        crearProductoButton.addActionListener(e -> crearProducto());

        verInventarioButton.addActionListener(e -> mostrarInventario());

        venderButton.addActionListener(e -> venderProducto());

        imprimirFacturaButton.addActionListener(e -> imprimirFactura());
        crearProductoButton.setFocusPainted(false);
        verInventarioButton.setFocusPainted(false);
        venderButton.setFocusPainted(false);

        crearProductoButton.setFocusPainted(false);
        verInventarioButton.setFocusPainted(false);
        venderButton.setFocusPainted(false);
        imprimirFacturaButton.setFocusPainted(false);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }



    public JPanel getPanelPrincipal(){
        return panelPrincipal;
    }




    private void crearProducto(){

        try{

            String nombre= txtNombre.getText();

            int cantidad= Integer.parseInt(txtCantidad.getText());


            String textoPrecio= txtPrecio.getText().replace(",", ".");


            double precio= Double.parseDouble(textoPrecio);


            inventario[contInventario]=
                    new Producto(nombre, cantidad, precio);
            contInventario++;
            areaSalida.append("Producto agregado\n");

        }

        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Datos inválidos");

        }

    }




    private void mostrarInventario(){

        areaSalida.setText("");


        if(contInventario==0){
            areaSalida.setText("Inventario vacío");
            return;
        }

        areaSalida.append("INVENTARIO\n\n");

        for(int i=0; i<contInventario; i++){

            areaSalida.append((i+1)+". " +inventario[i].getNombre()
                            +" | Stock: "
                            +inventario[i].getCantidad()
                            +" | Precio: "
                            +inventario[i].getPrecioUnitario()
                            +"\n"

            );

        }

    }






    private void venderProducto(){

        if(contInventario==0){
            JOptionPane.showMessageDialog(null, "No hay productos");

            return;
        }



        String nombres[]= new String[contInventario];


        for(int i=0; i<contInventario; i++){
            nombres[i]= inventario[i].getNombre();
        }



        JComboBox<String> combo=
                new JComboBox<>(nombres);


        JTextField txtVenta=
                new JTextField();


        Object mensaje[]={"Producto:", combo,
                "Cantidad:", txtVenta
        };


        int opcion= JOptionPane.showConfirmDialog(null, mensaje, "Vender Producto", JOptionPane.OK_CANCEL_OPTION);


        if(opcion== JOptionPane.OK_OPTION){

            try{
                String nombre= combo.getSelectedItem().toString();


                int vendido= Integer.parseInt(txtVenta.getText());



                for(int i=0; i<contInventario; i++){

                    if(inventario[i].getNombre().equalsIgnoreCase(nombre)){

                        if(vendido<= inventario[i].getCantidad()){

                            inventario[i].setCantidad(inventario[i].getCantidad()-vendido);


                            ventas[contVentas]=
                                    new Producto(nombre, vendido, inventario[i].getPrecioUnitario());

                            contVentas++;


                            JOptionPane.showMessageDialog(null, "Venta registrada");

                        }

                        else{

                            JOptionPane.showMessageDialog(null, "Stock insuficiente");

                        }

                    }

                }

            }

            catch(Exception e){

                JOptionPane.showMessageDialog(null, "Cantidad inválida");

            }

        }

    }







    private void imprimirFactura(){

        if(contVentas==0){
            areaSalida.setText("No hay ventas.");
            return;
        }

        Producto vendidos[]=
                new Producto[contVentas];

        for(int i=0;i<contVentas;i++){
            vendidos[i]=ventas[i];
        }

        Factura factura=
                new Factura(vendidos);

        areaSalida.setText("FACTURA\n\n");

        double subtotalBruto=0;
        double descuentoTotal=0;


        for(int i=0;i<contVentas;i++){

            Producto p=ventas[i];

            double bruto= p.calcularSubtotal();

            double descuento= p.calcularDescuento();

            double total= p.calcularTotalProducto();


            subtotalBruto+=bruto;
            descuentoTotal+=descuento;


            areaSalida.append(p.getNombre() +" | Cant: " +p.getCantidad() +" | P.Unit: " +String.format("%.2f", p.getPrecioUnitario()) +" | Total: " +String.format("%.2f", total) +"\nDescuento: " +String.format("%.2f", descuento) +"\n");
        }


        double subtotalNeto= subtotalBruto-descuentoTotal;


        areaSalida.append("\nSubtotal bruto: " +String.format("%.2f", subtotalBruto));

        areaSalida.append("\nDescuentos: " +String.format("%.2f", descuentoTotal));

        areaSalida.append("\nSubtotal neto: " +String.format("%.2f", subtotalNeto));

        areaSalida.append("\nIVA: " +String.format("%.2f", factura.calcularIVA()));

        areaSalida.append("\nTOTAL: " +String.format("%.2f", factura.calcularTotalFinal()));

    }

}