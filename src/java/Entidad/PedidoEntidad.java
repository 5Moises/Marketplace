package Entidad;

public class PedidoEntidad {

    private String CodPedido, DireccionEntrega, FechaEmision, HoraEntrega, TipoPago, NombreEmpresa, NombresCliente;
    private int CodCliente, CodEmpresa;

    public String ObtenerCodPedido() {
        return CodPedido;
    }

    public void ColocarCodPedido(String CodPedido) {
        this.CodPedido = CodPedido;
    }

    public int ObtenerCodCliente() {
        return CodCliente;
    }

    public void ColocarCodCliente(int CodCliente) {
        this.CodCliente = CodCliente;
    }

    public String ObtenerFechaEmision() {
        return FechaEmision;
    }

    public void ColocarFechaEmision(String FechaEmision) {
        this.FechaEmision = FechaEmision;
    }

    public String ObtenerDireccionEntrega() {
        return DireccionEntrega;
    }

    public void ColocarDireccionEntrega(String DireccionEntrega) {
        this.DireccionEntrega = DireccionEntrega;
    }

    public String ObtenerHoraEntrega() {
        return HoraEntrega;
    }

    public void ColocarHoraEntrega(String HoraEntrega) {
        this.HoraEntrega = HoraEntrega;
    }

    public String ObtenerTipoPago() {
        return TipoPago;
    }

    public void ColocarTipoPago(String TipoPago) {
        this.TipoPago = TipoPago;
    }

    public int ObtenerCodEmpresa() {
        return CodEmpresa;
    }

    public void ColocarCodEmpresa(int CodEmpresa) {
        this.CodEmpresa = CodEmpresa;
    }

    public String ObtenerNombreEmpresa() {
        return NombreEmpresa;
    }

    public void ColocarNombreEmpresa(String NombreEmpresa) {
        this.NombreEmpresa = NombreEmpresa;
    }

    public String ObtenerNombresCliente() {
        return NombresCliente;
    }

    public void ColocarNombresCliente(String NombreCliente) {
        this.NombresCliente = NombreCliente;
    }
}
