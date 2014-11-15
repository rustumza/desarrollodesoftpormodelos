/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

import entidad.Persona;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rustu
 */
public class GenericDao<E> {
    
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
    
    
    public List<E> buscarTodos(Class E){
        System.out.println("buscarporid");
        try {
            String[] nombreClaseCompleto = E.getName().replace('.', ',').split(",");
            String nombreClase = nombreClaseCompleto[nombreClaseCompleto.length-1].toLowerCase();

            PreparedStatement ps = ConectorBD.estableceConexion().prepareStatement("select * from "+ nombreClase);            
            ResultSet rs;     
            rs = ps.executeQuery();
            E obj =null;
            List<E> lista = new ArrayList<E>();
            while(rs.next()){
                for (java.lang.reflect.Constructor con : E.getConstructors()) {
                    if (con.getParameterTypes().length == 0) {
                        obj = (E)con.newInstance();
                        break;
                    }
                }
                for (Field field : obj.getClass().getDeclaredFields()) {                
                    if(field.getType().isPrimitive() || field.getType().getName().equals("java.lang.String")){    

                        String setterName = "set" + String.valueOf(field.getName().charAt(0)).toUpperCase() + field.getName().substring(1);
                        Method setter = obj.getClass().getMethod(setterName, field.getType());
                        String[] nombreTipoCompleto = field.getType().getName().replace('.', ',').split(",");
                        String nombreTipo = nombreTipoCompleto[nombreTipoCompleto.length-1].toLowerCase();
                        Method getter = rs.getClass().getMethod("get" +
                            String.valueOf(nombreTipo.charAt(0)).toUpperCase() +
                                nombreTipo.substring(1), String.class);
                        Object aux = getter.invoke(rs, new Object[]{field.getName()});
                        setter.invoke(obj, aux);
                    }else{
                        String setterName = "set" + String.valueOf(field.getName().charAt(0)).toUpperCase() + field.getName().substring(1);
                        Method setter = obj.getClass().getMethod(setterName, field.getType());
                        //String[] nombreTipoCompleto = field.getType().getName().replace('.', ',').split(",");
                        //String nombreTipo = nombreTipoCompleto[nombreTipoCompleto.length-1].toLowerCase();
    //                    Method getter = rs.getClass().getMethod("get" +
    //                        String.valueOf(nombreTipo.charAt(0)).toUpperCase() +
    //                            nombreTipo.substring(1), int.class);
                        Method getter = rs.getClass().getMethod("getInt",String.class);
                        int a = (int)getter.invoke(rs, new Object[]{field.getName()+"Id"});
                        Object aux = (buscarPorID(a,field.getType()));
                        setter.invoke(obj, aux);
                    }
                }
             lista.add(obj);
            }
            return lista;               
        } catch (Exception ex) {
            ex.printStackTrace();
        }            
        return null;
    }
    
    
    public E buscarPorID(int id, Class E){
        System.out.println("buscarporid");
        try {
            String[] nombreClaseCompleto = E.getName().replace('.', ',').split(",");
            String nombreClase = nombreClaseCompleto[nombreClaseCompleto.length-1].toLowerCase();

            PreparedStatement ps = ConectorBD.estableceConexion().prepareStatement("select * from "+ nombreClase +" where id = ?");
            ps.setObject(1, id);
            ResultSet rs;     
            rs = ps.executeQuery();

            E obj =null;

            for (java.lang.reflect.Constructor con : E.getConstructors()) {
                if (con.getParameterTypes().length == 0) {
                    obj = (E)con.newInstance();
                    break;
                }
            }
            rs.next();
            for (Field field : obj.getClass().getDeclaredFields()) {                
                if(field.getType().isPrimitive() || field.getType().getName().equals("java.lang.String")){    

                    String setterName = "set" + String.valueOf(field.getName().charAt(0)).toUpperCase() + field.getName().substring(1);
                    Method setter = obj.getClass().getMethod(setterName, field.getType());
                    String[] nombreTipoCompleto = field.getType().getName().replace('.', ',').split(",");
                    String nombreTipo = nombreTipoCompleto[nombreTipoCompleto.length-1].toLowerCase();
                    Method getter = rs.getClass().getMethod("get" +
                        String.valueOf(nombreTipo.charAt(0)).toUpperCase() +
                            nombreTipo.substring(1), String.class);
                    Object aux = getter.invoke(rs, new Object[]{field.getName()});
                    setter.invoke(obj, aux);
                }else{
                    String setterName = "set" + String.valueOf(field.getName().charAt(0)).toUpperCase() + field.getName().substring(1);
                    Method setter = obj.getClass().getMethod(setterName, field.getType());
                    //String[] nombreTipoCompleto = field.getType().getName().replace('.', ',').split(",");
                    //String nombreTipo = nombreTipoCompleto[nombreTipoCompleto.length-1].toLowerCase();
//                    Method getter = rs.getClass().getMethod("get" +
//                        String.valueOf(nombreTipo.charAt(0)).toUpperCase() +
//                            nombreTipo.substring(1), int.class);
                    Method getter = rs.getClass().getMethod("getInt",String.class);
                    int a = (int)getter.invoke(rs, new Object[]{field.getName()+"Id"});
                    Object aux = (buscarPorID(a,field.getType()));
                    setter.invoke(obj, aux);
                }
            }

            return obj;               
        } catch (Exception ex) {
            ex.printStackTrace();
        }            
        return null;
    }
    
}
