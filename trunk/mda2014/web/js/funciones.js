$(function(){
  var url = "http://localhost:8084/mda2014/servicios/servicioPersona.jsp";
  $.ajax({ 
      type: 'GET',
      url: url,
      dataType: 'json',
        success: function(resp) {
            armarTabla(resp);
        },
        error:function (){
            alert("error al buscar datos");
        }
  });
    
    function armarTabla(data){
        var contenidoTabla = "";
        $.each(data,function (clave,persona){
            contenidoTabla+="<tr><td>"+persona.nombre+"</td><td>"+persona.apellido+"</td>\n\
<td> <a href='controladorBackend/eliminar.php?id="+persona.id+"'>Eliminar</a>"+
"<a>Editar</a></td></tr>";                    
        });
    $("#cuerpoTabla").html(contenidoTabla);
    };
  
});

