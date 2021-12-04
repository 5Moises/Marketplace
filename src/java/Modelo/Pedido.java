package Modelo;

import Configuracion.Conexion;
import Entidad.PedidoEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pedido {

    Connection conexion = Conexion.getConnection();
    PedidoEntidad pedidoentidad = new PedidoEntidad();
    Random random = new Random();

    int ResultadoConsulta;
    boolean ExitoConsulta;
    ResultSet rs;

    public List MostrarPedidos(int CodCliente) {
        ArrayList<PedidoEntidad> DatosPedidos = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT DISTINCT Pedido.CodPedido, Empresa.Nombre, FechaEmision, DireccionEntrega, HoraEntrega, TipoPago.Nombre FROM Pedido INNER JOIN DetallePedido ON DetallePedido.CodPedido = Pedido.CodPedido INNER JOIN Empresa ON Empresa.CodEmpresa = DetallePedido.CodEmpresa INNER JOIN TipoPago ON TipoPago.CodTipoPago = Pedido.CodTipoPago WHERE Pedido.CodCliente = ? ");
            ps.setInt(1, CodCliente);
            rs = ps.executeQuery();
            while (rs.next()) {
                PedidoEntidad pedidoentidadlista = new PedidoEntidad();
                pedidoentidadlista.ColocarCodPedido(rs.getString(1));
                pedidoentidadlista.ColocarNombreEmpresa(rs.getString(2));
                pedidoentidadlista.ColocarFechaEmision(rs.getString(3));
                pedidoentidadlista.ColocarDireccionEntrega(rs.getString(4));
                pedidoentidadlista.ColocarHoraEntrega(rs.getString(5));
                pedidoentidadlista.ColocarTipoPago(rs.getString(6));
                DatosPedidos.add(pedidoentidadlista);
            }
        } catch (Exception Ex) {
        }
        return DatosPedidos;
    }
    
    public List MostrarPedidosEmpresa(int CodEmpresa) {
        ArrayList<PedidoEntidad> DatosPedidos = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT DISTINCT Pedido.CodPedido,(SELECT Nombres FROM Persona WHERE CodPersona = Pedido.CodCliente), FechaEmision, DireccionEntrega, HoraEntrega, TipoPago.Nombre FROM Pedido INNER JOIN DetallePedido ON DetallePedido.CodPedido = Pedido.CodPedido INNER JOIN TipoPago ON TipoPago.CodTipoPago = Pedido.CodTipoPago WHERE DetallePedido.CodEmpresa = ? ");
            ps.setInt(1, CodEmpresa);
            rs = ps.executeQuery();
            while (rs.next()) {
                PedidoEntidad pedidoentidadlista = new PedidoEntidad();
                pedidoentidadlista.ColocarCodPedido(rs.getString(1));
                pedidoentidadlista.ColocarNombresCliente(rs.getString(2));
                pedidoentidadlista.ColocarFechaEmision(rs.getString(3));
                pedidoentidadlista.ColocarDireccionEntrega(rs.getString(4));
                pedidoentidadlista.ColocarHoraEntrega(rs.getString(5));
                pedidoentidadlista.ColocarTipoPago(rs.getString(6));
                DatosPedidos.add(pedidoentidadlista);
            }
        } catch (Exception Ex) {
        }
        return DatosPedidos;
    }

    public boolean RegistrarPedido(PedidoEntidad DatosPedido) throws SQLException {
        String CodigoPedido = "Ped" + DatosPedido.ObtenerCodCliente() + "-" + random.nextInt(99999);

        PreparedStatement ps = conexion.prepareStatement("INSERT INTO Pedido (CodPedido, CodCliente, FechaEmision, DireccionEntrega, HoraEntrega, CodTipoPago) VALUES (?,?,CurDate(),?,?,(SELECT CodTipoPago FROM TipoPago WHERE Nombre = ? ) )");
        ps.setString(1, CodigoPedido);
        ps.setInt(2, DatosPedido.ObtenerCodCliente());
        ps.setString(3, DatosPedido.ObtenerDireccionEntrega());
        ps.setString(4, DatosPedido.ObtenerHoraEntrega());
        ps.setString(5, DatosPedido.ObtenerTipoPago());
        ps.executeUpdate();

        PreparedStatement ps2 = conexion.prepareStatement("UPDATE DetallePedido SET CodPedido = ? WHERE CodPedido IS null AND CodEmpresa = ? AND CodCliente = ? ");
        ps2.setString(1, CodigoPedido);
        ps2.setInt(2, DatosPedido.ObtenerCodEmpresa());
        ps2.setInt(3, DatosPedido.ObtenerCodCliente());
        ResultadoConsulta = ps2.executeUpdate();
        if (ResultadoConsulta > 0) {
            ExitoConsulta = true;
        }
        return ExitoConsulta;
    }
}
