<nav>
    <ul>
        <%
            if (session.getAttribute("AbrirSesion") != null) {
        %>
        <li><a >&nbsp;</a></li>
        <li><a href="index.jsp">&nbsp;</a></li>
        <li><a href="Perfil.jsp?CodPersona=<%=session.getAttribute("AbrirSesion")%>">Perfil</a></li>
        <li><a href="Acceder.jsp">Salir</a></li>
        <li><a href="Acerca.jsp">Acerca de</a></li>
            <%
            } else {
            %>
        <li><a >&nbsp;</a></li>
        <li><a href="index.jsp">&nbsp;</a></li>
        <li><a href="Acceder.jsp">Acceder</a></li>
        <li><a href="Registrarse.jsp">Registrarse</a></li>
        <li><a href="Acerca.jsp">Acerca de</a></li>
            <%
                }
            %>
    </ul>
</nav>