/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package experto;

import Persistencia.GenericDao;
import entidad.Persona;
import java.util.List;

/**
 *
 * @author rustu
 */
public class PersonaExperto {
    
    GenericDao<Persona> genericDao;

    public PersonaExperto() {
        genericDao = new GenericDao<>();
    }
    
    public List<Persona> getPersonas(){
        List<Persona> lista = genericDao.buscarTodos(Persona.class);
        return lista;
    }
    
    
    public boolean eliminar(Persona persona){
        return genericDao.eliminar(persona);
    }

    public boolean guardar(Persona persona){
        return genericDao.guardar(persona);
    }

    //    public void guardar2(){
//        
//        //Persona dom = (Persona)new GenericDao().buscarPorID(1, Persona.class);
//        //System.out.println(dom);
//        
//        Persona per = new Persona();
//        per.setId(6);
//        per.setDomicilio(new Domicilio());
//        per.getDomicilio().setId(6);
//        new GenericDao().eliminar(per);
////        per.setId(6);
////        per.setApellido("Perez");
////        per.setDocumento("100000");
////        per.setEdad(27);
////        per.setNombre("Agustin");        
////        per.getDomicilio().setCalle("cachantun");
////        per.getDomicilio().setDpto("");
////        per.getDomicilio().setId(6);
////        per.getDomicilio().setNro(313);
//        //new GenericDao().guardar(per);
//    }
    
    
}
