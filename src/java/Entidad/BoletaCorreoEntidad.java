package Entidad;

public class BoletaCorreoEntidad {

    private String CodPedido, CodEmpresa, FechaConfirmada, NombreRepartidor;

    public String ObtenerCodPedido() {
        return CodPedido;
    }

    public void ColocarCodPedido(String CodPedido) {
        this.CodPedido = CodPedido;
    }

    public String ObtenerCodEmpresa() {
        return CodEmpresa;
    }

    public void ColocarCodEmpresa(String CodEmpresa) {
        this.CodEmpresa = CodEmpresa;
    }

    public String ObtenerFechaConfirmada() {
        return FechaConfirmada;
    }

    public void ColocarFechaConfirmada(String FechaConfirmada) {
        this.FechaConfirmada = FechaConfirmada;
    }

    public String ObtenerNombreRepartidor() {
        return NombreRepartidor;
    }

    public void ColocarNombreRepartidor(String NombreRepartidor) {
        this.NombreRepartidor = NombreRepartidor;
    }

}
