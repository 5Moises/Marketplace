<%@page import="Controlador.CuadrosDialogo"%>
<%@page import="Entidad.EmpresaEntidad"%>
<%@page import="Modelo.Empresa"%>
<%@page import="Entidad.MarcaEntidad"%>
<%@page import="Modelo.Marca"%>
<%@page import="Entidad.PresentacionEntidad"%>
<%@page import="Modelo.Presentacion"%>
<%@page import="Entidad.CategoriaEntidad"%>
<%@page import="Modelo.Categoria"%>
<%@page import="Entidad.TipoPagoEntidad"%>
<%@page import="Entidad.TipoPagoEntidad"%>
<%@page import="Modelo.TipoPago"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Entidad.DistritoEntidad"%>
<%@page import="Modelo.Distrito"%>
<%@page import="Entidad.PersonaEntidad"%>
<%@page import="Modelo.Persona"%>
<%
    int CodPersona = Integer.parseInt(request.getParameter("CodPersona"));
    int AbrirSesion = 0;
    if (session.getAttribute("AbrirSesion") != null) {
        AbrirSesion = Integer.parseInt(session.getAttribute("AbrirSesion").toString());
    }
    Persona persona = new Persona();
    PersonaEntidad personaentidad = (PersonaEntidad) persona.MostrarPersona(CodPersona);

    if (CodPersona == AbrirSesion) {
%>
<div id="SeccionLados">
    <section>
        <hgroup>
            Datos Personales
        </hgroup>
        <form action="PersonaServlet" method="POST" enctype="multipart/form-data">
            <ul>
                <li><img src="Vista/MostrarImagenPersona.jsp?CodPersona=<%= personaentidad.ObtenerCodPersona()%>"/></li>
                <li class="CargarImagen"><input type="file" name="fil_imagen"></li>
                <li>Nombres:</li>
                <li><input type="text" name="txt_nombres" value="<%= personaentidad.ObtenerNombres()%>"></li>
                <li>Apellidos:</li>
                <li><input type="text" name="txt_apellidos" value="<%= personaentidad.ObtenerApellidos()%>"></li>
                <li>E-Mail:</li>
                <li><input type="email" name="txt_email" value="<%= personaentidad.ObtenerEmail()%>"></li>
                <li>Teléfono:</li>
                <li><input type="text" name="txt_telefono" value="<%= personaentidad.ObtenerTelefono()%>"></li>
                <li>Direccion:</li>
                <li><input type="text" name="txt_direccion" value="<%= personaentidad.ObtenerDireccion()%>"></li>
                <li><input type="submit" name="btn_modificar" value="Modificar"></li>
            </ul>
            <input type="hidden" name="Consulta" value="2"/>
        </form>
        <form action="PersonaServlet" method="POST">
            <ul>
                <li><input type="submit" name="btn_dejaradmin" value="Dejar Administración"/></li>
            </ul>
            <input type="hidden" name="Consulta" value="4"/>
        </form>
    </section>

    <section>
        <hgroup>
            Distrito
        </hgroup>
        <form action="DistritoServlet" method="POST">
            <ul>
                <li>Nombre Distrito:</li>
                <li><input type="text" name="txt_nombredistrito"/></li>
                <li><input type="submit" name="btn_registrar" value="Registrar"/></li>
            </ul>
        </form> 
    </section>

    <section>
        <hgroup>
            Tipo de pago
        </hgroup>
        <form action="TipoPagoServlet" method="POST">
            <ul>
                <li>Nombre Tipo de pago:</li>
                <li><input type="text" name="txt_nombretipopago"/></li>
                <li><input type="submit" name="btn_registrar" value="Registrar"/></li>
            </ul>
        </form>  
    </section>

    <section>
        <hgroup>
            Categoría
        </hgroup>
        <form action="CategoriaServlet" method="POST">
            <ul>
                <li>Nombre Categoria:</li>
                <li><input type="text" name="txt_nombrecategoria"/></li>
                <li><input type="submit" name="btn_registrar" value="Registrar"/></li>
            </ul>
        </form>   
    </section>

    <section>
        <hgroup>
            Presentación
        </hgroup>
        <form action="PresentacionServlet" method="POST">
            <ul>
                <li>Nombre Presentación:</li>
                <li><input type="text" name="txt_nombrepresentacion"/></li>
                <li><input type="submit" name="btn_registrar" value="Registrar"/></li>
            </ul>
        </form> 
    </section>

    <section>
        <hgroup>
            Marca
        </hgroup>
        <form action="MarcaServlet" method="POST">
            <ul>
                <li>Nombre Marca:</li>
                <li><input type="text" name="txt_nombremarca"/></li>
                <li><input type="submit" name="btn_registrar" value="Registrar"/></li>
            </ul>
        </form>
    </section>
</div>
<%
    }
%>

<div id="SeccionesCentro" class="SeccionAdministracion">  
    <%
        if (CodPersona != AbrirSesion) {
    %>
    <section class="InformacionPersona">
        <hgroup>
            Datos Personales
        </hgroup>
        <article>
            <img src="Vista/MostrarImagenPersona.jsp?CodPersona=<%= personaentidad.ObtenerCodPersona()%>"/>
        </article>
        <article>
            <ul>
                <li><h2><%= personaentidad.ObtenerNombres() + " " + personaentidad.ObtenerApellidos()%></h2></li>
                <li><%= personaentidad.ObtenerEmail()%></li>
                <li>Teléfono: <%= personaentidad.ObtenerTelefono()%></li>
                <li>Dirección: <%= personaentidad.ObtenerDireccion()%></li>
            </ul>
        </article>
    </section>
    <%
        }
    %>

    <section>
        <hgroup>
            ADMINISTRADOR
        </hgroup>
    </section>
    <%
        if (CodPersona == AbrirSesion) {
    %>
    <section class="SeccionSinFondo">
        <hgroup>
            Personas
        </hgroup>
        <div class="Personas">
            <%
                List<PersonaEntidad> DatosPersona = persona.MostrarPersonas((byte) 0);
                Iterator<PersonaEntidad> personaiterator = DatosPersona.iterator();
                PersonaEntidad datopersona = null;
                while (personaiterator.hasNext()) {
                    datopersona = personaiterator.next();
            %>     
            <a href="Perfil.jsp?CodPersona=<%= datopersona.ObtenerCodPersona()%>" title="<%= datopersona.ObtenerNombres() + " " + datopersona.ObtenerApellidos()%>">
                <article>
                    <img src="Vista/MostrarImagenPersona.jsp?CodPersona=<%= datopersona.ObtenerCodPersona()%>" />
                </article>
            </a>
            <ul>
                <li><a href="PersonaServlet?CodPersona=<%= datopersona.ObtenerCodPersona()%>&Consulta=4" title="Conv. en Admin."></a></li>
                <li><a href="PersonaServlet?CodPersona=<%= datopersona.ObtenerCodPersona()%>&Consulta=3" title="Eliminar"></a></li>
            </ul>
            <%
                }
            %> 
        </div>
    </section>
    <section class="SeccionSinFondo Empresas">
        <hgroup>
            Empresas
        </hgroup>
        <%
            Empresa empresa = new Empresa();
            List<EmpresaEntidad> DatosEmpresa = empresa.MostrarEmpresas();
            Iterator<EmpresaEntidad> empresaiterator = DatosEmpresa.iterator();
            EmpresaEntidad datoempresa = null;
            while (empresaiterator.hasNext()) {
                datoempresa = empresaiterator.next();
        %>
        <a href="Empresa.jsp?CodEmpresa=<%= datoempresa.ObtenerCodEmpresa()%>&Seccion=Prod">
            <article>
                <ul>
                    <li><img src="Vista/MostrarImagenEmpresa.jsp?CodEmpresa=<%= datoempresa.ObtenerCodEmpresa()%>" /></li>
                    <li><%= datoempresa.ObtenerNombre()%></li>
                </ul>                  
            </article> 
        </a>
        <span>
            <ul>
                <li><a href="EmpresaServlet?CodEmpresa=<%= datoempresa.ObtenerCodEmpresa()%>&Consulta=3" title="Eliminar"></a></li>
            </ul>
        </span>
        <%
            }
        %>
    </section>
    <section>
        <hgroup>
            Distritos
        </hgroup>       
        <div class="BarraDesplHorizontal">
            <table>
                <caption>Todos</caption>
                <tr>
                    <th>Acción</th>
                    <th>Nombre</th>
                </tr>
                <%
                    Distrito distrito = new Distrito();
                    List<DistritoEntidad> DatosDistrito = distrito.MostrarDistritos();
                    Iterator<DistritoEntidad> iterator = DatosDistrito.iterator();
                    DistritoEntidad distritoentidad = null;
                    while (iterator.hasNext()) {
                        distritoentidad = iterator.next();
                %>
                <tr>
                    <td>
                        <ul>
                            <li><a href="DistritoServlet?CodDistrito=<%= distritoentidad.ObtenerCodDistrito()%>" title="Eliminar"></a></li>
                        </ul>                            
                    </td>
                    <td>
                        <%= distritoentidad.ObtenerNombre()%>
                    </td>
                    <%
                        }
                    %>
                </tr>
            </table>
        </div>
    </section>
    <section>
        <hgroup>
            Tipos de Pago
        </hgroup>     
        <div class="BarraDesplHorizontal">
            <table>
                <caption>Todos</caption>
                <tr>
                    <th>Acción</th>
                    <th>Nombre</th>
                </tr>
                <%
                    TipoPago tipopago = new TipoPago();
                    List<TipoPagoEntidad> DatosTipoPago = tipopago.MostrarTiposPago();
                    Iterator<TipoPagoEntidad> tipopagoiterator = DatosTipoPago.iterator();
                    TipoPagoEntidad datotipopago = null;
                    while (tipopagoiterator.hasNext()) {
                        datotipopago = tipopagoiterator.next();
                %>
                <tr>
                    <td>
                        <ul>
                            <li><a href="TipoPagoServlet?CodTipoPago=<%= datotipopago.ObtenerCodTipoPago()%>" title="Eliminar"></a></li>
                        </ul>                            
                    </td>
                    <td>
                        <%= datotipopago.ObtenerNombre()%>
                    </td>
                    <%
                        }
                    %>
                </tr>
            </table>
        </div>
    </section>
    <section>
        <hgroup>
            Categorías
        </hgroup>     
        <div class="BarraDesplHorizontal">
            <table>
                <caption>Todos</caption>
                <tr>
                    <th>Acción</th>
                    <th>Nombre</th>
                </tr>
                <%
                    Categoria categoria = new Categoria();
                    List<CategoriaEntidad> DatosCategoria = categoria.MostrarCategorias();
                    Iterator<CategoriaEntidad> categoriaiterator = DatosCategoria.iterator();
                    CategoriaEntidad datocategoria = null;
                    while (categoriaiterator.hasNext()) {
                        datocategoria = categoriaiterator.next();
                %>
                <tr>
                    <td>
                        <ul>
                            <li><a href="CategoriaServlet?CodCategoria=<%= datocategoria.ObtenerCodCategoria()%>" title="Eliminar"></a></li>
                        </ul>                            
                    </td>
                    <td>
                        <%= datocategoria.ObtenerNombre()%>
                    </td>
                    <%
                        }
                    %>
                </tr>
            </table>
        </div>
    </section>
    <section>
        <hgroup>
            Presentaciones
        </hgroup>       
        <div class="BarraDesplHorizontal">
            <table>
                <caption>Todos</caption>
                <tr>
                    <th>Acción</th>
                    <th>Nombre</th>
                </tr>
                <%
                    Presentacion presentacion = new Presentacion();
                    List<PresentacionEntidad> DatosPresentacion = presentacion.MostrarPresentaciones();
                    Iterator<PresentacionEntidad> presentacioniterator = DatosPresentacion.iterator();
                    PresentacionEntidad datopresentacion = null;
                    while (presentacioniterator.hasNext()) {
                        datopresentacion = presentacioniterator.next();
                %>
                <tr>
                    <td>
                        <ul>
                            <li><a href="PresentacionServlet?CodPresentacion=<%= datopresentacion.ObtenerCodPresentacion()%>" title="Eliminar"></a></li>
                        </ul>                            
                    </td>
                    <td>
                        <%= datopresentacion.ObtenerNombre()%>
                    </td>
                    <%
                        }
                    %>
                </tr>
            </table>
        </div>
    </section>
    <section>
        <hgroup>
            Marcas
        </hgroup>              
        <div class="BarraDesplHorizontal">
            <table>
                <caption>Todos</caption>
                <tr>
                    <th>Acción</th>
                    <th>Nombre</th>
                </tr>
                <%
                    Marca marca = new Marca();
                    List<MarcaEntidad> DatosMarca = marca.MostrarMarcas();
                    Iterator<MarcaEntidad> marcaiterator = DatosMarca.iterator();
                    MarcaEntidad datomarca = null;
                    while (marcaiterator.hasNext()) {
                        datomarca = marcaiterator.next();
                %>
                <tr>
                    <td>
                        <ul>
                            <li><a href="MarcaServlet?CodMarca=<%= datomarca.ObtenerCodMarca()%>" title="Eliminar"></a></li>
                        </ul>                            
                    </td>
                    <td>
                        <%= datomarca.ObtenerNombre()%>
                    </td>
                    <%
                        }
                    %>
                </tr>
            </table>
        </div>
    </section>
    <%
        }

        if (request.getAttribute("CuadroMensaje") != null) {
            out.write(request.getAttribute("CuadroMensaje").toString());
        }
    %>
</div>