package controlador;

import vista.frmCliente;
import modelo.Cliente;
import modelo.ClienteDAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//CREATE DATABASE java18_empresax;
//use java18_empresax;
public class ControladorCliente implements ActionListener {
    frmCliente vistaCliente = new frmCliente();
    ClienteDAO modeloCliente = new ClienteDAO();
    Cliente cliente = new Cliente();
    
       

    public ControladorCliente(frmCliente vistaCliente, ClienteDAO modeloCliente) {
        this.modeloCliente = modeloCliente;
        this.vistaCliente = vistaCliente;
        
        this.vistaCliente.btnInsert.setActionCommand("Insertar");
        this.vistaCliente.btnSelect.setActionCommand("Seleccionar");
        this.vistaCliente.btnDelete.setActionCommand("Delete");
        this.vistaCliente.btnUpdate.setActionCommand("Update");
        this.vistaCliente.btnInsert.addActionListener(this);
        this.vistaCliente.btnSelect.addActionListener(this);
        this.vistaCliente.btnDelete.addActionListener(this);
        this.vistaCliente.btnUpdate.addActionListener(this);
    }
    
    public void LlenarTabla (JTable tablaDatos){
        DefaultTableModel modeloTabla = new DefaultTableModel();
        tablaDatos.setModel(modeloTabla);
        
        modeloTabla.addColumn("Código");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellido");
        modeloTabla.addColumn("Dirección");
        modeloTabla.addColumn("Teléfono");
        modeloTabla.addColumn("NIT");
        
        Object[] columna = new Object[6];
        
        int numRegistros = modeloCliente.listaClientes().size();
        
        for (int i = 0; i < numRegistros; i++) {
            columna[0] = modeloCliente.listaClientes().get(i).getCodigo();
            columna[1] = modeloCliente.listaClientes().get(i).getNombre();
            columna[2] = modeloCliente.listaClientes().get(i).getApellido();
            columna[3] = modeloCliente.listaClientes().get(i).getDireccion();
            columna[4] = modeloCliente.listaClientes().get(i).getTelefono();
            columna[5] = modeloCliente.listaClientes().get(i).getNit();
            modeloTabla.addRow(columna);
        }
        
    }
    
    public void insertCliente(){
        //asigno al objeto cliente los valores de la vista
        cliente.setCodigo(Integer.parseInt(vistaCliente.txtCodigo.getText()));
        cliente.setNombre(vistaCliente.txtNombre.getText());
        cliente.setApellido(vistaCliente.txtApellido.getText());
        cliente.setDireccion(vistaCliente.txtDirecion.getText());
        cliente.setTelefono(Integer.parseInt(vistaCliente.txtTelefono.getText()));
        cliente.setNit(vistaCliente.txtNit.getText());
        
       
        
        String respuestaInsert = this.modeloCliente.registrarCliente(cliente);
        System.out.println(respuestaInsert);
        if (respuestaInsert!=null){
            JOptionPane.showMessageDialog(null, respuestaInsert);
            limpiarControles();
                       
        }else{
            JOptionPane.showMessageDialog(null, respuestaInsert);
            
        }
    }
    public void BorrarCliente(){
    
    limpiarControles();
    }
    public void actualizarCliente(){
    
    
    }
     public void limpiarControles(){
        vistaCliente.txtCodigo.setText(null);
        vistaCliente.txtNombre.setText(null);
        vistaCliente.txtApellido.setText(null);
        vistaCliente.txtDirecion.setText(null);
        vistaCliente.txtTelefono.setText(null);
        vistaCliente.txtNit.setText(null);
        
        vistaCliente.txtCodigo.requestFocus();
    }
     
     
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String evento = e.getActionCommand();
        
        if (evento.equals("Insertar")) {
            insertCliente();
            
        }
        if (e.getSource()== vistaCliente.btnSelect){
            LlenarTabla(vistaCliente.tblClientes);
        }
        if (evento.equals("Delete")) {
           BorrarCliente();
        }
        if (evento.equals("Update")) {
            actualizarCliente();
        }
    }
    
   
}
