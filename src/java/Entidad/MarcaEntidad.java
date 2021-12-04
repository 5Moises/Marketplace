
package Entidad;

public class MarcaEntidad {
    int CodMarca;
    String Nombre;

    public int ObtenerCodMarca() {
        return CodMarca;
    }

    public void ColocarCodMarca(int CodMarca) {
        this.CodMarca = CodMarca;
    }

    public String ObtenerNombre() {
        return Nombre;
    }

    public void ColocarNombre(String Nombre) {
        this.Nombre = Nombre;
    }
}
