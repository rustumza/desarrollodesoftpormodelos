<%@page import="experto.ExpertoPersona"%>
<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%     
    PrintWriter salida = response.getWriter();
    salida.println(new ExpertoPersona().getPersona());
    salida.close();
%>  
