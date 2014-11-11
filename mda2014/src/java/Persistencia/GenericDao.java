/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

import java.lang.reflect.Field;

/**
 *
 * @author rustu
 */
public class GenericDao {
    
    public boolean guardar(Object objeto){
        
        for (Field field : objeto.getClass().getDeclaredFields()) {                
                
            System.out.println(field.getName()+": " + ((field.getType().getClass().isPrimitive() || field.getClass().getName().equals("String"))?"primitivo":field.getClass().getName()));
        }
        
        return true;
        
    }
    
}
