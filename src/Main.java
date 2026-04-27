import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame ventana= new JFrame("Sistema Facturacion");

        VentanaSistema form= new VentanaSistema();


        ventana.setContentPane(form.getPanelPrincipal());

        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ventana.setSize(800,600);

        ventana.setLocationRelativeTo(null);

        ventana.setVisible(true);



        /*Sistema sis = new Sistema();
        sis.menu();*/

    }

}