/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *
 * @author rustu
 */
public class GenericDao {
    
    public boolean guardar(Object objeto){
        try{
            System.out.println(objeto.getClass().getName()+": " + ((objeto.getClass().isPrimitive() || objeto.getClass().getName().equals("java.lang.String"))?"primitivo":objeto.getClass().getName()));

            for (Field field : objeto.getClass().getDeclaredFields()) {                
                if(field.getType().isPrimitive() || field.getType().getName().equals("java.lang.String"))    
                    System.out.println(field.getName()+": " + ((field.getType().isPrimitive() || field.getType().getName().equals("java.lang.String"))?"primitivo":field.getType().getName()));
                else{

                     Method getter = objeto.getClass().getMethod("get" +
                            String.valueOf(field.getName().charAt(0)).toUpperCase() +
                            field.getName().substring(1));

                    //Llamo al Metodo especificado de este Objeto
                    //con un array con los respectivos Parametros
                    //En este caso al ser un Getter no recibe parametros
                    guardar(getter.invoke(objeto, new Object[0]));                 
            }


            }

            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
}
