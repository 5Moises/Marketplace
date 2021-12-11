package Entidad;

public class TipoPagoEntidad {

    private int CodTipoPago;
    private String Nombre;

    public int ObtenerCodTipoPago() {
        return CodTipoPago;
    }

    public void ColocarCodTipoPago(int CodTipoPago) {
        this.CodTipoPago = CodTipoPago;
    }

    public String ObtenerNombre() {
        return Nombre;
    }

    public void ColocarNombre(String Nombre) {
        this.Nombre = Nombre;
    }
}
