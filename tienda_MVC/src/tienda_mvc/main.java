package tienda_mvc;
import vista.frmCliente;
import modelo.*;
import vista.*;
import controlador.*;


public class main {

    public static void main(String[] args) {
        frmCliente vistaCli = new frmCliente();
        ClienteDAO modeloCli = new ClienteDAO();
        ControladorCliente controlaCli = new ControladorCliente(vistaCli, modeloCli);
        
        vistaCli.setTitle("Clientes");
        vistaCli.setLocationRelativeTo(null);
        vistaCli.setVisible(true);
        
    }
    
}
