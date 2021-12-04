package Entidad;

public class AccesoEntidad {

    String IdAcceso, Contrasena;

    public String ObtenerIdAcceso() {
        return IdAcceso;
    }

    public void ColocarIdAcceso(String IdAcceso) {
        this.IdAcceso = IdAcceso;
    }
    
    public String ObtenerContrasena() {
        return Contrasena;
    }

    public void ColocarContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }

}
