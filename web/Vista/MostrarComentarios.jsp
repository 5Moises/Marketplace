<%@page import="java.util.Iterator"%>
<%@page import="Entidad.ComentarioEntidad"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Comentario"%>
<%
    int CodEmpresa = Integer.parseInt(request.getParameter("CodEmpresa"));
    if (session.getAttribute("AbrirSesion") != null) {
%>
<section class="Comentarios">
    <hgroup>
        Déjanos tus opiniones
    </hgroup>
    <form action="ComentarioServlet" method="POST">
        <ul>
            <li>Calificación:</li>
        </ul>
        <ul>
            <input id="Radio1" type="radio" name="rdo_calificar" value="5"/>
            <label for="Radio1"></label>
            <input id="Radio2" type="radio" name="rdo_calificar" value="4"/>
            <label for="Radio2"></label>
            <input id="Radio3" type="radio" name="rdo_calificar" value="3"/>
            <label for="Radio3"></label>
            <input id="Radio4" type="radio" name="rdo_calificar" value="2"/>
            <label for="Radio4"></label>
            <input id="Radio5" type="radio" name="rdo_calificar" value="1"/>
            <label for="Radio5"></label>
        </ul>
        <ul>
            <li>Comentario:</li>
            <li><textarea name="txt_comentario"></textarea></li>
            <li><input type="submit" name="btn_registrar" value="Comentar y Calificar"></li>
        </ul>
        <input type="hidden" name="Consulta" value="1"/>
        <input type="hidden" name="txt_codempresa" value="<%= CodEmpresa%>"/>
    </form>
</section>
<%
    }
%>
<section class="SeccionSinFondo Comentarios">
    <hgroup>
        Comentarios
    </hgroup>
    <ul>
        <%
            Comentario comentario = new Comentario();
            List<ComentarioEntidad> DatosComentario = comentario.MostrarComentarios(CodEmpresa);
            Iterator<ComentarioEntidad> comentarioiterator = DatosComentario.iterator();
            ComentarioEntidad datocomentario = null;
            while (comentarioiterator.hasNext()) {
                datocomentario = comentarioiterator.next();
        %>
        <li>                        
            <article>
                <img src="Vista/MostrarImagenPersona.jsp?CodPersona=<%= datocomentario.ObtenerCodCliente()%>"/>
            </article>
            <article>
                <ul>
                    <li><% out.write("<b>" + datocomentario.ObtenerNombreApellidoCliente() + "</b> &#8226; " + datocomentario.ObtenerFechaEmision() + " &#8226; Puntos: " + datocomentario.ObtenerCalificacion());%></li>
                    <li><%= datocomentario.ObtenerComentario()%></li>
                </ul>
            </article> 
        </li>
        <%
            }
        %>
    </ul>
</section>