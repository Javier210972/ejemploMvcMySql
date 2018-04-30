package modelo;

//Heredar conexion
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.frmCliente;

public class ClienteDAO extends Conexion {

    private String sql;
    private String variable;
    private PreparedStatement ejecutar;
    private String respuesta;
    //Agregar un nuevo Cliente, Los procesos de eliminar y actualizar son similares
    frmCliente vistaCliente = new frmCliente();
 Cliente cliente = new Cliente();
    public String registrarCliente(Cliente clienten) {
        respuesta = null;
        try {
            this.Conectar();
            sql = "insert into cliente values(?,?,?,?,?,?)";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setInt(1, clienten.getCodigo());
            ejecutar.setString(2, clienten.getNombre());
            ejecutar.setString(3, clienten.getApellido());
            ejecutar.setString(4, clienten.getDireccion());
            ejecutar.setInt(5, clienten.getTelefono());
            ejecutar.setString(6, clienten.getNit());

            ejecutar.executeUpdate();

            respuesta = "Registro Almacenado con Exito";
        } catch (SQLException ex) {
            System.out.println("Error en Statement" + ex);
            respuesta = "No se pudo almacenar el registro";

        } finally {
            this.cerrarConex();
        }
        return respuesta;
    }

    public String actulizarCliente(Cliente clienten) {
      DefaultTableModel modeloTabla = new DefaultTableModel();
        respuesta = null;
        try {
            this.Conectar();
           
            sql = "UPDATE cliente SET nombre=?,apellido=?,direccion=?,telefono=?,nit=? WHERE codigo=?";

            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setInt(6, clienten.getCodigo());
            ejecutar.setString(1, clienten.getNombre());
            ejecutar.setString(2, clienten.getApellido());
            ejecutar.setString(3, clienten.getDireccion());
            ejecutar.setInt(4, clienten.getTelefono());
            ejecutar.setString(5, clienten.getNit());

            ejecutar.executeUpdate();
              
                 respuesta = "Datos Recibidos";
            
           
              
           

        } catch (SQLException ex) {
            System.out.println("Error en Statement" + ex);
            respuesta = "No se pudo actualizar el registro";
        } finally {
            this.cerrarConex();
        }
        return respuesta;
    }

    public String BorrarCliente(Cliente clienten) {
        respuesta = null;
        try {
            this.Conectar();
            sql = "DELETE FROM `cliente` WHERE codigo=?";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            ejecutar.setInt(1, clienten.getCodigo());

            ejecutar.executeUpdate();

            respuesta = "Registro Borrado con exito";
        } catch (SQLException ex) {
            System.out.println("Error en Statement" + ex);
            respuesta = "No se pudo Borrar el registro";
        } finally {
            this.cerrarConex();
        }
        return respuesta;
    }

    //BUSCAR DATOS
    public ArrayList<Cliente> listaClientes() {
        ArrayList<Cliente> lista = null;
        ResultSet resultado;
        try {
            this.Conectar();
            sql = "select * from cliente";
            ejecutar = this.getMiconexion().prepareStatement(sql);
            resultado = ejecutar.executeQuery();
            lista = new ArrayList();
            while (resultado.next()) {
                Cliente clienten = new Cliente();
                clienten.setCodigo(resultado.getInt("codigo"));
                clienten.setNombre(resultado.getString("nombre"));
                clienten.setApellido(resultado.getString("apellido"));
                clienten.setDireccion(resultado.getString("direccion"));
                clienten.setTelefono(resultado.getInt("telefono"));
                clienten.setNit(resultado.getString("nit"));

                lista.add(clienten);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            this.cerrarConex();
        }
        return lista;
    }
}
