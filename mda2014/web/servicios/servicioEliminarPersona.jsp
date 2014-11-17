<%@page import="java.util.Collection"%>
<%@page import="entidad.Domicilio"%>
<%@page import="java.util.Map"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="entidad.Persona"%>
<%@page import="java.util.List"%>
<%@page import="experto.PersonaExperto"%>
<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Persona persona = new Persona();
    
    if(!request.getParameterValues("id")[0].isEmpty()){
        persona.setId(Integer.parseInt(request.getParameterValues("id")[0]));
    }    
    persona.setNombre(request.getParameterValues("nombre")[0]);
    persona.setApellido(request.getParameterValues("apellido")[0]);
    persona.setDocumento(request.getParameterValues("documento")[0]);

    
    persona.setDomicilio(new Domicilio());
    if(!request.getParameterValues("domicilio[id]")[0].isEmpty()){
        persona.getDomicilio().setId(Integer.parseInt(request.getParameterValues("domicilio[id]")[0]));
    }    
    persona.getDomicilio().setCalle(request.getParameterValues("domicilio[calle]")[0]);
    persona.getDomicilio().setNro(Integer.parseInt(request.getParameterValues("domicilio[nro]")[0]));

    new PersonaExperto().eliminar(persona);

    //salida es la respuesta a la peticion
    PrintWriter salida = response.getWriter();
    //libreria Gson -> genera json a partir de un objeto
    salida.println(new Gson().toJsonTree(persona));
    salida.close();
%>  