package Modelo;

import Configuracion.Conexion;
import Entidad.DistritoEntidad;
import Entidad.TipoPagoEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoPago {

    Connection conexion = Conexion.getConnection();
    TipoPagoEntidad tipopagoentidad = new TipoPagoEntidad();

    private int ResultadoConsulta;
    private boolean ExitoConsulta;
    ResultSet rs;

    public List MostrarTiposPago() {
        ArrayList<TipoPagoEntidad> DatosTipoPago = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT * FROM TipoPago");
            rs = ps.executeQuery();
            while (rs.next()) {
                TipoPagoEntidad tipopagolista = new TipoPagoEntidad();
                tipopagolista.ColocarCodTipoPago(rs.getInt(1));
                tipopagolista.ColocarNombre(rs.getString(2));
                DatosTipoPago.add(tipopagolista);
            }
        } catch (Exception Ex) {
        }
        return DatosTipoPago;
    }

    public boolean RegistrarTipoPago(TipoPagoEntidad DatosTipoPago) throws SQLException {
        PreparedStatement ps = conexion.prepareStatement("INSERT INTO TipoPago (Nombre) VALUES (?)");
        ps.setString(1, DatosTipoPago.ObtenerNombre());
        ResultadoConsulta = ps.executeUpdate();
        if (ResultadoConsulta > 0) {
            ExitoConsulta = true;
        }
        return ExitoConsulta;
    }

    public boolean EliminarTipoPago(TipoPagoEntidad DatosTipoPago) throws SQLException {
        PreparedStatement ps = conexion.prepareStatement("DELETE FROM TipoPago WHERE CodTipoPago = ? ");
        ps.setInt(1, DatosTipoPago.ObtenerCodTipoPago());
        ResultadoConsulta = ps.executeUpdate();
        if (ResultadoConsulta > 0) {
            ExitoConsulta = true;
        }
        return ExitoConsulta;
    }
}
