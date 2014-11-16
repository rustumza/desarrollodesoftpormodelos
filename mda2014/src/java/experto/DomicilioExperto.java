/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package experto;

import Persistencia.GenericDao;
import entidad.Domicilio;
import java.util.List;

/**
 *
 * @author rustu
 */
public class DomicilioExperto {
    
        GenericDao<Domicilio> genericDao;

    public DomicilioExperto() {
        genericDao = new GenericDao<>();
    }
    
    public List<Domicilio> getDomicilios(){
        List<Domicilio> lista = genericDao.buscarTodos(Domicilio.class);        
        return lista;
    }
    
    
    public boolean eliminar(Domicilio domicilio){
        return genericDao.eliminar(domicilio);
    }

    public boolean guardar(Domicilio domicilio){
        return genericDao.guardar(domicilio);
    }
    
    
}
