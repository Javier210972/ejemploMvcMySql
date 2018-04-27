package modelo;

public class Cliente extends Persona {
    private Integer codigo;
    private String nit;

    public Cliente() {
        //super llama al constructor de la clase padre
        super();
        this.codigo = null;
        this.nit=null;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer idcliente) {
        this.codigo = idcliente;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
          
}
