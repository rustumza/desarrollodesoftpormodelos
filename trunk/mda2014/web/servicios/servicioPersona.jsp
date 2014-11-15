<%@page import="com.google.gson.Gson"%>
<%@page import="entidad.Persona"%>
<%@page import="java.util.List"%>
<%@page import="experto.PersonaExperto"%>
<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%     
   
    List<Persona> lista = new PersonaExperto().getPersona();
    //salida es la respuesta a la peticion
    PrintWriter salida = response.getWriter();
    //libreria Gson -> genera json a partir de un objeto
    salida.println(new Gson().toJsonTree(lista));
    salida.close();
%>  
