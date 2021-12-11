package Modelo;

import Configuracion.Conexion;
import Entidad.ProductoEntidad;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Producto {

    Connection conexion = Conexion.getConnection();
    ProductoEntidad productoentidad = new ProductoEntidad();

    private int ResultadoConsulta;
    private boolean ExitoConsulta = false;
    ResultSet rs;

    public List MostrarProductos(int CodEmpresa, String Nombre, String Categoria) {
        ArrayList<ProductoEntidad> DatosProducto = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT CodProducto, Nombre, Precio FROM Producto WHERE CodEmpresa = ? AND Nombre LIKE ? AND CodCategoria LIKE ? ");
            ps.setInt(1, CodEmpresa);
            ps.setString(2, "%" + Nombre + "%");
            ps.setString(3, Categoria + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                ProductoEntidad productoentidadlista = new ProductoEntidad();
                productoentidadlista.ColocarCodProducto(rs.getInt(1));
                productoentidadlista.ColocarNombre(rs.getString(2));
                productoentidadlista.ColocarPrecio(rs.getString(3));
                DatosProducto.add(productoentidadlista);
            }
        } catch (Exception Ex) {
        }
        return DatosProducto;
    }

    public ProductoEntidad MostrarProducto(int CodProducto) {
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT CodProducto, Producto.Nombre, Precio, Producto.Descripcion, Disponible, "
                    + "(SELECT Nombre FROM Presentacion WHERE CodPresentacion = Producto.CodPresentacion), "
                    + "(SELECT Nombre FROM Marca WHERE CodMarca = Producto.CodMarca), "
                    + "(SELECT Nombre FROM Categoria WHERE CodCategoria = Producto.CodCategoria), Producto.CodEmpresa, Persona.CodPersona FROM Producto "
                    + "INNER JOIN Empresa ON Empresa.CodEmpresa = Producto.CodEmpresa INNER JOIN Persona ON Persona.CodPersona = Empresa.CodPersona WHERE CodProducto = ? ");
            ps.setInt(1, CodProducto);
            rs = ps.executeQuery();
            if (rs.next()) {
                productoentidad.ColocarCodProducto(rs.getInt(1));
                productoentidad.ColocarNombre(rs.getString(2));
                productoentidad.ColocarPrecio(rs.getString(3));
                productoentidad.ColocarDescripcion(rs.getString(4));
                productoentidad.ColocarDisponible(rs.getBoolean(5));
                productoentidad.ColocarPresentacion(rs.getString(6));
                productoentidad.ColocarMarca(rs.getString(7));
                productoentidad.ColocarCategoria(rs.getString(8));
                productoentidad.ColocarCodEmpresa(rs.getInt(9));
                productoentidad.ColocarCodPersona(rs.getInt(10));
            }
        } catch (Exception Ex) {
        }
        return productoentidad;
    }

    public List BuscarProductos(String Nombre, String Presentacion, String Marca, String Categotia) {
        ArrayList<ProductoEntidad> DatosProducto = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement("SELECT CodProducto, Nombre, Precio FROM Producto WHERE Disponible = true AND Nombre LIKE ? AND CodPresentacion LIKE ? AND CodMarca LIKE ? AND CodCategoria LIKE ? ");
            ps.setString(1, "%" + Nombre + "%");
            ps.setString(2, Presentacion + "%");
            ps.setString(3, Marca + "%");
            ps.setString(4, Categotia + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                ProductoEntidad productoentidadlista = new ProductoEntidad();
                productoentidadlista.ColocarCodProducto(rs.getInt(1));
                productoentidadlista.ColocarNombre(rs.getString(2));
                productoentidadlista.ColocarPrecio(rs.getString(3));
                DatosProducto.add(productoentidadlista);
            }
        } catch (Exception Ex) {
        }
        return DatosProducto;
    }

    public boolean RegistrarProducto(ProductoEntidad DatosProducto) throws SQLException, IOException {
        PreparedStatement ps = conexion.prepareStatement("INSERT INTO Producto (Nombre, Precio, Descripcion, Disponible, CodPresentacion, CodMarca, CodCategoria, Imagen, CodEmpresa) VALUES (?,?,?,true,?,?,?,?,?)");
        ps.setString(1, DatosProducto.ObtenerNombre());
        ps.setString(2, DatosProducto.ObtenerPrecio());
        ps.setString(3, DatosProducto.ObtenerDescripcion());
        ps.setString(4, DatosProducto.ObtenerPresentacion());
        ps.setString(5, DatosProducto.ObtenerMarca());
        ps.setString(6, DatosProducto.ObtenerCategoria());
        ps.setBlob(7, DatosProducto.ObtenerImagen());
        ps.setInt(8, DatosProducto.ObtenerCodEmpresa());
        ResultadoConsulta = ps.executeUpdate();
        if (ResultadoConsulta > 0) {
            ExitoConsulta = true;
        }
        return ExitoConsulta;
    }

    public boolean ModificarProducto(ProductoEntidad DatosProducto) throws SQLException, IOException {
        if (DatosProducto.ObtenerImagen() != null) {
            PreparedStatement ps = conexion.prepareStatement("UPDATE Producto SET Nombre = ? ,Precio = ? ,Descripcion = ? ,Disponible = ? ,CodPresentacion = ? ,CodMarca = ? ,CodCategoria = ? ,Imagen = ? WHERE CodProducto = ? ");
            ps.setString(1, DatosProducto.ObtenerNombre());
            ps.setString(2, DatosProducto.ObtenerPrecio());
            ps.setString(3, DatosProducto.ObtenerDescripcion());
            ps.setBoolean(4, DatosProducto.ObtenerDisponible());
            ps.setString(5, DatosProducto.ObtenerPresentacion());
            ps.setString(6, DatosProducto.ObtenerMarca());
            ps.setString(7, DatosProducto.ObtenerCategoria());
            ps.setBlob(8, DatosProducto.ObtenerImagen());
            ps.setInt(9, DatosProducto.ObtenerCodProducto());
            ResultadoConsulta = ps.executeUpdate();
        } else {
            PreparedStatement ps = conexion.prepareStatement("UPDATE Producto SET Nombre = ? ,Precio = ? ,Descripcion = ? ,Disponible = ? ,CodPresentacion = ? ,CodMarca = ? ,CodCategoria = ? WHERE CodProducto = ? ");
            ps.setString(1, DatosProducto.ObtenerNombre());
            ps.setString(2, DatosProducto.ObtenerPrecio());
            ps.setString(3, DatosProducto.ObtenerDescripcion());
            ps.setBoolean(4, DatosProducto.ObtenerDisponible());
            ps.setString(5, DatosProducto.ObtenerPresentacion());
            ps.setString(6, DatosProducto.ObtenerMarca());
            ps.setString(7, DatosProducto.ObtenerCategoria());
            ps.setInt(8, DatosProducto.ObtenerCodProducto());
            ResultadoConsulta = ps.executeUpdate();
        }
        if (ResultadoConsulta > 0) {
            ExitoConsulta = true;
        }
        return ExitoConsulta;
    }

    public boolean EliminarProducto(ProductoEntidad DatosProducto) throws SQLException {
        PreparedStatement ps = conexion.prepareStatement("DELETE FROM Producto WHERE CodProducto = ? ");
        ps.setInt(1, DatosProducto.ObtenerCodProducto());
        ResultadoConsulta = ps.executeUpdate();
        if (ResultadoConsulta > 0) {
            ExitoConsulta = true;
        }
        return ExitoConsulta;
    }
}
