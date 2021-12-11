package Modelo;

import Configuracion.Conexion;
import Entidad.DetallePedidoEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DetallePedido {

    Connection conexion = Conexion.getConnection();
    DetallePedidoEntidad detallepedidoentidad = new DetallePedidoEntidad();
    Random random = new Random();

    private int ResultadoConsulta;
    private boolean ExitoConsulta = false;
    ResultSet rs;

    public List MostrarDetallePedidos(int CodEmpresa, int CodCliente) {
        ArrayList<DetallePedidoEntidad> DatosDetallePedido = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT CodDetalle, Cantidad,(SELECT Nombre FROM Producto WHERE CodProducto = DetallePedido.CodProducto), Precio, Subtotal FROM DetallePedido WHERE CodPedido IS null AND CodEmpresa = ? AND CodCliente = ? ");
            ps.setInt(1, CodEmpresa);
            ps.setInt(2, CodCliente);
            rs = ps.executeQuery();
            while (rs.next()) {
                DetallePedidoEntidad detallepedidoentidadlista = new DetallePedidoEntidad();
                detallepedidoentidadlista.ColocarCodDetalle(rs.getString(1));
                detallepedidoentidadlista.ColocarCantidad(rs.getString(2));
                detallepedidoentidadlista.ColocarNombreProducto(rs.getString(3));
                detallepedidoentidadlista.ColocarPrecio(rs.getString(4));
                detallepedidoentidadlista.ColocarSubtotal(rs.getString(5));
                DatosDetallePedido.add(detallepedidoentidadlista);
            }
        } catch (Exception Ex) {
        }
        return DatosDetallePedido;
    }

    public List MostrarDetallePedidosPerfil(String CodPedido) {
        ArrayList<DetallePedidoEntidad> DatosDetallePedidosPerfil = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT DISTINCT CodDetalle, Cantidad, (SELECT Nombre FROM Producto WHERE CodProducto = DetallePedido.CodProducto), Precio, Subtotal FROM DetallePedido WHERE CodPedido = ? ");
            ps.setString(1, CodPedido);
            rs = ps.executeQuery();
            while (rs.next()) {
                DetallePedidoEntidad detallepedidoentidadlista = new DetallePedidoEntidad();
                detallepedidoentidadlista.ColocarCodDetalle(rs.getString(1));
                detallepedidoentidadlista.ColocarCantidad(rs.getString(2));
                detallepedidoentidadlista.ColocarNombreProducto(rs.getString(3));
                detallepedidoentidadlista.ColocarPrecio(rs.getString(4));
                detallepedidoentidadlista.ColocarSubtotal(rs.getString(5));
                DatosDetallePedidosPerfil.add(detallepedidoentidadlista);
            }
        } catch (Exception Ex) {
        }
        return DatosDetallePedidosPerfil;
    }

    public DetallePedidoEntidad TotalDetallePedido(int CodEmpresa, int CodCliente) {
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT Sum(Subtotal) FROM DetallePedido WHERE CodPedido IS null AND CodEmpresa = ? AND CodCliente = ? ");
            ps.setInt(1, CodEmpresa);
            ps.setInt(2, CodCliente);
            rs = ps.executeQuery();
            if (rs.next()) {
                detallepedidoentidad.ColocarTotal(rs.getDouble(1));
            }
        } catch (Exception Ex) {
        }
        return detallepedidoentidad;
    }

    public int CantidadProductosCarrito(int CodEmpresa, int CodCliente) {
        int CantidadProdutos = 0;
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT Count(*) FROM DetallePedido WHERE CodPedido IS null AND CodEmpresa = ? AND CodCliente = ? ");
            ps.setInt(1, CodEmpresa);
            ps.setInt(2, CodCliente);
            rs = ps.executeQuery();
            if (rs.next()) {
                CantidadProdutos = rs.getInt(1);
            }
        } catch (Exception Ex) {
        }
        return CantidadProdutos;
    }

    public DetallePedidoEntidad TotalDetallePedidoPerfil(String CodPedido) {
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT Sum(Subtotal) FROM DetallePedido WHERE CodPedido = ? ");
            ps.setString(1, CodPedido);
            rs = ps.executeQuery();
            if (rs.next()) {
                detallepedidoentidad.ColocarTotal(rs.getDouble(1));
            }
        } catch (Exception Ex) {
        }
        return detallepedidoentidad;
    }

    public boolean RegistrarDetallePedido(DetallePedidoEntidad DatosDetallePedido) throws SQLException {
        PreparedStatement ps = conexion.prepareStatement("INSERT INTO DetallePedido (CodDetalle, CodCliente, Cantidad, CodProducto, Precio, Subtotal, CodEmpresa) SELECT (?),(?),(?), CodProducto, Precio, (? * Precio), CodEmpresa FROM Producto WHERE COdProducto = ? ");
        ps.setString(1, "Car" + DatosDetallePedido.ObtenerCodCliente() + "-" + random.nextInt(99999));
        ps.setInt(2, DatosDetallePedido.ObtenerCodCliente());
        ps.setString(3, DatosDetallePedido.ObtenerCantidad());
        ps.setString(4, DatosDetallePedido.ObtenerCantidad());
        ps.setInt(5, DatosDetallePedido.ObtenerCodProducto());
        ResultadoConsulta = ps.executeUpdate();
        if (ResultadoConsulta > 0) {
            ExitoConsulta = true;
        }
        return ExitoConsulta;
    }

    public boolean ModificarDetallePedido(DetallePedidoEntidad DatosDetallePedido) throws SQLException {
        PreparedStatement ps = conexion.prepareStatement("UPDATE DetallePedido SET Cantidad = ? ,SubTotal = (? * Precio) WHERE CodDetalle = ?");
        ps.setString(1, DatosDetallePedido.ObtenerCantidad());
        ps.setString(2, DatosDetallePedido.ObtenerCantidad());
        ps.setString(3, DatosDetallePedido.ObtenerCodDetalle());
        ResultadoConsulta = ps.executeUpdate();
        if (ResultadoConsulta > 0) {
            ExitoConsulta = true;
        }
        return ExitoConsulta;
    }

    public boolean EliminarDetallePedido(DetallePedidoEntidad DatosDetallePedido) throws SQLException {
        PreparedStatement ps = conexion.prepareStatement("DELETE FROM DetallePedido WHERE CodDetalle = ? ");
        ps.setString(1, DatosDetallePedido.ObtenerCodDetalle());
        ResultadoConsulta = ps.executeUpdate();
        if (ResultadoConsulta > 0) {
            ExitoConsulta = true;
        }
        return ExitoConsulta;
    }
}
