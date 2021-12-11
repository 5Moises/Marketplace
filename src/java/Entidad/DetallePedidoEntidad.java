package Entidad;

public class DetallePedidoEntidad {

    private int CodCliente, CodEmpresa, CodProducto;
    private String CodDetalle, CodPedido, NombreProducto, Cantidad, Precio, SubTotal;
    private double Total = 0.0;

    public int ObtenerCodCliente() {
        return CodCliente;
    }

    public void ColocarCodCliente(int CodCliente) {
        this.CodCliente = CodCliente;
    }

    public int ObtenerCodEmpresa() {
        return CodEmpresa;
    }

    public void ColocarCodEmpresa(int CodEmpresa) {
        this.CodEmpresa = CodEmpresa;
    }

    public int ObtenerCodProducto() {
        return CodProducto;
    }

    public void ColocarCodProducto(int CodProducto) {
        this.CodProducto = CodProducto;
    }

    public String ObtenerCodDetalle() {
        return CodDetalle;
    }

    public void ColocarCodDetalle(String CodDetalle) {
        this.CodDetalle = CodDetalle;
    }

    public String ObtenerCodPedido() {
        return CodPedido;
    }

    public void ColocarCodPedido(String CodPedido) {
        this.CodPedido = CodPedido;
    }

    public String ObtenerNombreProducto() {
        return NombreProducto;
    }

    public void ColocarNombreProducto(String NombreProducto) {
        this.NombreProducto = NombreProducto;
    }

    public String ObtenerCantidad() {
        return Cantidad;
    }

    public void ColocarCantidad(String Cantidad) {
        this.Cantidad = Cantidad;
    }

    public String ObtenerPrecio() {
        return Precio;
    }

    public void ColocarPrecio(String Precio) {
        this.Precio = Precio;
    }

    public String ObtenerSubtotal() {
        return SubTotal;
    }

    public void ColocarSubtotal(String Subtotal) {
        this.SubTotal = Subtotal;
    }

    public Double ObtenerTotal() {
        return Total;
    }

    public void ColocarTotal(Double Total) {
        this.Total = Total;
    }
}
