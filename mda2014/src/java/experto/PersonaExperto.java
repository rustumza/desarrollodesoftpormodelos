/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package experto;

import Persistencia.GenericDao;
import Persistencia.PersonaDao;
import entidad.Domicilio;
import entidad.Persona;
import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public static void main(String[] args) {
        new PersonaExperto().guardar2();
    }
    
    public void guardar2(){
        new GenericDao().guardar(new Domicilio());
    }
    
    public boolean guardar(){
        try {
            Field[] a = Persona.class.getDeclaredFields();
            for (Field field : a) {
                
                System.out.println(field.getName());
            }
            Class _class = Class.forName("entidad.Persona");
            Field properties[] = _class.getDeclaredFields();
            for (int i = 0; i < properties.length; i++) {
                Field field = properties[i];
                System.out.println(field.getName() +" > "+field.getType());
            }
            
            return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PersonaExperto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
}
