
package Modelo;

import Configuracion.Conexion;
import Entidad.CategoriaEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Categoria {
    
    Connection conexion = Conexion.getConnection();
    CategoriaEntidad categoriaentidad = new CategoriaEntidad();

    int ResultadoConsulta;
    boolean ExitoConsulta;
    ResultSet rs;

    public List MostrarCategorias() {
        ArrayList<CategoriaEntidad> DatosCategoria = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT * FROM Categoria");
            rs = ps.executeQuery();
            while (rs.next()) {
                CategoriaEntidad categorialista = new CategoriaEntidad();
                categorialista.ColocarCodCategoria(rs.getInt(1));
                categorialista.ColocarNombre(rs.getString(2));
                DatosCategoria.add(categorialista);
            }
        } catch (Exception Ex) {
        }
        return DatosCategoria;
    }

    public boolean RegistrarCategoria(CategoriaEntidad DatosCategoria) throws SQLException {
        PreparedStatement ps = conexion.prepareStatement("INSERT INTO Categoria (Nombre) VALUES (?)");
        ps.setString(1, DatosCategoria.ObtenerNombre());
        ResultadoConsulta = ps.executeUpdate();
        if (ResultadoConsulta > 0) {
            ExitoConsulta = true;
        }
        return ExitoConsulta;
    }

    public boolean EliminarCategoria(CategoriaEntidad DatosCategoria) throws SQLException {
        PreparedStatement ps = conexion.prepareStatement("DELETE FROM Categoria WHERE CodCategoria = ? ");
        ps.setInt(1, DatosCategoria.ObtenerCodCategoria());
        ResultadoConsulta = ps.executeUpdate();
        if (ResultadoConsulta > 0) {
            ExitoConsulta = true;
        }
        return ExitoConsulta;
    }
}
