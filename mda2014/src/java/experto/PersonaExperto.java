/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package experto;

import Persistencia.PersonaDao;
import entidad.Persona;
import java.util.List;

/**
 *
 * @author rustu
 */
public class PersonaExperto {
    
    PersonaDao personaDao;

    public PersonaExperto() {
        personaDao = new PersonaDao();
    }
    
        
    public List<Persona> getPersona(){
        return personaDao.findAll();
    }
    
}
