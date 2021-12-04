package Entidad;

public class DistritoEntidad {

    int CodDistrito;
    String Nombre;

    public int ObtenerCodDistrito() {
        return CodDistrito;
    }

    public void ColocarCodDistrito(int CodDistrito) {
        this.CodDistrito = CodDistrito;
    }

    public String ObtenerNombre() {
        return Nombre;
    }

    public void ColocarNombre(String Nombre) {
        this.Nombre = Nombre;
    }
}
