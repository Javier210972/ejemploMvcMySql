package controlador;

import java.awt.Color;
import vista.frmCliente;
import modelo.Cliente;
import modelo.ClienteDAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//CREATE DATABASE java18_empresax;
//use java18_empresax;
public class ControladorCliente implements ActionListener, KeyListener {

    frmCliente vistaCliente = new frmCliente();
    ClienteDAO modeloCliente = new ClienteDAO();
    Cliente cliente = new Cliente();

    public ControladorCliente(frmCliente vistaCliente, ClienteDAO modeloCliente) {
        this.modeloCliente = modeloCliente;
        this.vistaCliente = vistaCliente;

//   this.vistaCliente.btnInsert.setActionCommand("Insertar");
//        this.vistaCliente.btnSelect.setActionCommand("Seleccionar");
//        this.vistaCliente.btnDelete.setActionCommand("Delete");
//        this.vistaCliente.btnUpdate.setActionCommand("Update");
        LlenarTabla(vistaCliente.tblClientes);
        this.vistaCliente.btnInsert.addActionListener(this);
        this.vistaCliente.btnSelect.addActionListener(this);
        this.vistaCliente.btnDelete.addActionListener(this);
        this.vistaCliente.btnUpdate.addActionListener(this);
        
        this.KeyListener();
        this.deshabilitar();

    }

    public void LlenarTabla(JTable tablaDatos) {
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

    public void insertCliente() {
        //asigno al objeto cliente los valores de la vista
        try {
            cliente.setCodigo(Integer.parseInt(vistaCliente.txtCodigo.getText()));
            cliente.setNombre(vistaCliente.txtNombre.getText());
            cliente.setApellido(vistaCliente.txtApellido.getText());
            cliente.setDireccion(vistaCliente.txtDirecion.getText());
            cliente.setTelefono(Integer.parseInt(vistaCliente.txtTelefono.getText()));
            cliente.setNit(vistaCliente.txtNit.getText());

            String respuestaInsert = this.modeloCliente.registrarCliente(cliente);
            System.out.println(respuestaInsert);
            if (respuestaInsert != null) {
                JOptionPane.showMessageDialog(null, respuestaInsert);
               
                limpiarControles();

            } else {
                JOptionPane.showMessageDialog(null, respuestaInsert);

            }
        } catch (Exception e) {
            this.vistaCliente.btnSelect.setEnabled(false);
            vistaCliente.Jlblrespuesta.setText("");
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void BorrarCliente() {
        try {

            cliente.setCodigo(Integer.parseInt(vistaCliente.txtCodigo.getText()));

            String respuestaInsert = this.modeloCliente.BorrarCliente(cliente);
            System.out.println(respuestaInsert);
            if (respuestaInsert != null) {
                JOptionPane.showMessageDialog(null, respuestaInsert);
                
                limpiarControles();

            } else {
                JOptionPane.showMessageDialog(null, respuestaInsert);

            }
        } catch (Exception e) {
            this.vistaCliente.btnSelect.setEnabled(false);
            vistaCliente.Jlblrespuesta.setText("");
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void actualizarCliente() {

        try {

            cliente.setCodigo(Integer.parseInt(vistaCliente.txtCodigo.getText()));
            cliente.setNombre(vistaCliente.txtNombre.getText());
            cliente.setApellido(vistaCliente.txtApellido.getText());
            cliente.setDireccion(vistaCliente.txtDirecion.getText());
            cliente.setTelefono(Integer.parseInt(vistaCliente.txtTelefono.getText()));
            cliente.setNit(vistaCliente.txtNit.getText());

            String respuestaInsert = this.modeloCliente.actulizarCliente(cliente);
            System.out.println(respuestaInsert);
            if (respuestaInsert != null) {
                JOptionPane.showMessageDialog(null, respuestaInsert);
//                vistaCliente.Jlblrespuesta.setForeground(Color.black);
//                vistaCliente.Jlblrespuesta.setText("");
                limpiarControles();

            } else {
                JOptionPane.showMessageDialog(null, respuestaInsert);

            }
        } catch (Exception e) {
            this.vistaCliente.btnSelect.setEnabled(false);
            vistaCliente.Jlblrespuesta.setText("");
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void limpiarControles() {
        vistaCliente.txtCodigo.setText(null);
        vistaCliente.txtNombre.setText(null);
        vistaCliente.txtApellido.setText(null);
        vistaCliente.txtDirecion.setText(null);
        vistaCliente.txtTelefono.setText(null);
        vistaCliente.txtNit.setText(null);

        vistaCliente.txtCodigo.requestFocus();

    }

    public void deshabilitar() {
        this.vistaCliente.btnDelete.setEnabled(false);
        this.vistaCliente.btnInsert.setEnabled(false);
        this.vistaCliente.btnSelect.setEnabled(false);
        this.vistaCliente.btnUpdate.setEnabled(false);

    }

    public void habilitar() {
        this.vistaCliente.btnDelete.setEnabled(true);
        this.vistaCliente.btnInsert.setEnabled(true);

        this.vistaCliente.btnUpdate.setEnabled(true);

    }

    public void KeyListener() {

        this.vistaCliente.txtApellido.addKeyListener(this);
        this.vistaCliente.txtCodigo.addKeyListener(this);
        this.vistaCliente.txtDirecion.addKeyListener(this);
        this.vistaCliente.txtNit.addKeyListener(this);
        this.vistaCliente.txtNombre.addKeyListener(this);
        this.vistaCliente.txtTelefono.addKeyListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String evento = e.getActionCommand();

        if (e.getSource() == vistaCliente.btnInsert) {

            this.vistaCliente.btnInsert.setEnabled(false);
            this.vistaCliente.btnSelect.setEnabled(true);
            this.vistaCliente.btnUpdate.setEnabled(false);
            insertCliente();
            this.vistaCliente.btnDelete.setEnabled(false);

        }
        if (e.getSource() == vistaCliente.btnSelect) {
            this.vistaCliente.btnInsert.setEnabled(false);
            this.vistaCliente.btnUpdate.setEnabled(false);
            LlenarTabla(vistaCliente.tblClientes);
            vistaCliente.Jlblrespuesta.setText("");
            this.vistaCliente.btnSelect.setEnabled(false);
            this.vistaCliente.btnDelete.setEnabled(false);
        }
        if (e.getSource() == vistaCliente.btnDelete) {
            this.vistaCliente.btnInsert.setEnabled(false);
            this.vistaCliente.btnSelect.setEnabled(true);
            this.vistaCliente.btnUpdate.setEnabled(false);
            BorrarCliente();

        }
        if (e.getSource() == vistaCliente.btnUpdate) {
            this.vistaCliente.btnInsert.setEnabled(false);
            this.vistaCliente.btnSelect.setEnabled(true);
            this.vistaCliente.btnUpdate.setEnabled(false);
            actualizarCliente();
            this.vistaCliente.btnDelete.setEnabled(false);

        }
//        if (evento.equals("Update")) {
//            vistaCliente.btnDelete.setEnabled(true);
//
//        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {

//        if (ke.getSource()==vistaCliente.txtCodigo) {
//             if (ke.VK_A==ke.getKeyCode()) {
//                vocales+=vocales+"1";
//            }
//             if (ke.VK_E==ke.getKeyCode()) {
//                 vocales+=vocales+"2";
//            }
//        }
//        
    }

    @Override
    public void keyReleased(KeyEvent ke) {
           vistaCliente.Jlblrespuesta.setText("");
        if (!vistaCliente.txtNombre.getText().isEmpty() || !vistaCliente.txtApellido.getText().isEmpty() || !vistaCliente.txtDirecion.getText().isEmpty() || !vistaCliente.txtNit.getText().isEmpty() || !vistaCliente.txtTelefono.getText().isEmpty()) {
            vistaCliente.Jlblrespuesta.setText("");
            this.vistaCliente.btnDelete.setEnabled(false);

        }
        if (!vistaCliente.txtCodigo.getText().isEmpty() && !vistaCliente.txtNombre.getText().isEmpty() && !vistaCliente.txtApellido.getText().isEmpty() && !vistaCliente.txtDirecion.getText().isEmpty() && !vistaCliente.txtNit.getText().isEmpty() && !vistaCliente.txtTelefono.getText().isEmpty()) {
            vistaCliente.Jlblrespuesta.setText("");

            habilitar();

        } else {
            vistaCliente.Jlblrespuesta.setForeground(Color.red);
            vistaCliente.Jlblrespuesta.setText("!IMPORTANTE!: siquiere guardar o modificar llene todas las casillas");

            deshabilitar();

        }

        if (!vistaCliente.txtCodigo.getText().isEmpty()) {

            this.vistaCliente.btnDelete.setEnabled(true);
        }
//        DefaultTableModel modelo = (DefaultTableModel)vistaCliente.tblClientes.getModel();
//        if (vistaCliente.txtCodigo.getText().equals(modelo.getColumnName(1))) {
//            System.out.println("sf");
//        }
    }

}
