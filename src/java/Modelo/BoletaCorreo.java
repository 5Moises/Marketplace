package Modelo;

import Configuracion.Conexion;
import Entidad.BoletaCorreoEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoletaCorreo {

    Connection conexion = Conexion.getConnection();
    BoletaCorreoEntidad boletacorreoentidad = new BoletaCorreoEntidad();

    ResultSet rs;
    String CuerpoCorreo, NombreEmpresa;
    String EstiloTabla = "style=\"border: 1px solid #000; padding: 5px;\" ";
    String EstiloArticulo = "style=\"display:block; padding:10px; margin:10px; border:1px solid #c1c1c1; border-radius:10px; background:#e7f0fe;\" ";

    public String MostrarBoletaCorreo(BoletaCorreoEntidad DatosBoletaCorreo) throws SQLException {
        PreparedStatement ps = conexion.prepareStatement("SELECT Pedido.CodPedido, DireccionEntrega, TipoPago.Nombre FROM Pedido INNER JOIN DetallePedido ON DetallePedido.CodPedido = Pedido.CodPedido INNER JOIN Empresa ON Empresa.CodEmpresa = DetallePedido.CodEmpresa INNER JOIN TipoPago ON TipoPago.CodTipoPago = Pedido.CodTipoPago WHERE Pedido.CodPedido = ? ");
        ps.setString(1, DatosBoletaCorreo.ObtenerCodPedido());
        rs = ps.executeQuery();
        if (rs.next()) {
            CuerpoCorreo = ""
                    + "Estimado Cliente, le presentamos los detalles de su pedido, su comprobante de pago:"
                    + "<article " + EstiloArticulo + ">"
                    + "<b>Código de Pedido:</b> " + rs.getString(1) + "<br>"
                    + "<b>Dirección de entrega:</b> " + rs.getString(2) + "<br>"
                    + "<b>Tipo de Pago:</b> " + rs.getString(3) + "<br>"
                    + "<b>Nombre Repartidor:</b> " + DatosBoletaCorreo.ObtenerNombreRepartidor() + "<br>"
                    + "<b>Hora y Fecha confirmada:</b> " + DatosBoletaCorreo.ObtenerFechaConfirmada()
                    + "</article>";
        }

        PreparedStatement ps2 = conexion.prepareStatement("SELECT Cantidad,(SELECT Nombre FROM Producto WHERE CodProducto = DetallePedido.CodProducto), Precio, Subtotal FROM DetallePedido WHERE CodPedido = ?");
        ps2.setString(1, DatosBoletaCorreo.ObtenerCodPedido());
        rs = ps2.executeQuery();
        CuerpoCorreo += ""
                + "<article " + EstiloArticulo + ">"
                + "<table style=\"border-collapse: collapse;\">"
                + "<tr>"
                + "<th " + EstiloTabla + ">Cantidad</th>"
                + "<th " + EstiloTabla + ">Producto</th>"
                + "<th " + EstiloTabla + ">Precio</th>"
                + "<th " + EstiloTabla + ">Subtotal</th>"
                + "</tr>";
        while (rs.next()) {
            CuerpoCorreo += ""
                    + "<tr>"
                    + "<td " + EstiloTabla + ">" + rs.getString(1) + "</td>"
                    + "<td " + EstiloTabla + ">" + rs.getString(2) + "</td>"
                    + "<td " + EstiloTabla + ">" + rs.getString(3) + "</td>"
                    + "<td " + EstiloTabla + ">" + rs.getString(4) + "</td>"
                    + "</tr>";
        }
        PreparedStatement ps3 = conexion.prepareStatement("SELECT Sum(Subtotal) FROM DetallePedido WHERE CodPedido = ? ");
        ps3.setString(1, DatosBoletaCorreo.ObtenerCodPedido());
        rs = ps3.executeQuery();
        if (rs.next()) {
            CuerpoCorreo += ""
                    + "<tr>"
                    + "<td></td>"
                    + "<td></td>"
                    + "<td>Total S/</td>"
                    + "<td " + EstiloTabla + ">" + rs.getString(1) + "</td>"
                    + "</tr>"
                    + "</table>"
                    + "</article>"
                    + "Gracias por su compra...";
        }
        return CuerpoCorreo;
    }

    public String MostrarNombreEmpresa(BoletaCorreoEntidad DatosEmpresa) throws SQLException {
        PreparedStatement ps = conexion.prepareStatement("SELECT Nombre FROM Empresa WHERE CodEmpresa = ? ");
        ps.setString(1, DatosEmpresa.ObtenerCodEmpresa());
        rs = ps.executeQuery();
        if (rs.next()) {
            NombreEmpresa = rs.getString(1);
        }
        return NombreEmpresa;
    }
}
