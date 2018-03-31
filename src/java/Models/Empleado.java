package Models;

public class Empleado {

    private String Codigo, DNI, Nombre, Apellido, Usuario, Password, Estado, Privilegio;

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPrivilegioPintado() {
        if (Privilegio != null) {
            switch (Integer.valueOf(Privilegio)) {
            case 1:
                return "Administrador";
            case 2:
                return "Vendedor";
            default:
                return "Desconocido";
        }
        }else{
            return Privilegio;
        }
    }

    public String getPrivilegio() {
        return Privilegio;
    }

    public void setPrivilegio(String Privilegio) {
        this.Privilegio = Privilegio;
    }

}
