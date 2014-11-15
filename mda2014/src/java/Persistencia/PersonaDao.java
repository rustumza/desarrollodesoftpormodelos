/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

import entidad.Persona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author rustu
 */
public class PersonaDao {
    
    DomicilioDao domicilioDao;
    
    public PersonaDao(){
        domicilioDao = new DomicilioDao();
    }

    
    public List<Persona> findAll(){
        List<Persona> lista = new ArrayList<Persona>();
        System.out.println("findall");
        try {
            ResultSet rs = ConectorBD.estableceConexion().createStatement().executeQuery(Sentencias.consulta1);
            Persona per = null;            
            while(rs.next()){
                per = new Persona();
                per.setId(rs.getInt("id"));
                per.setNombre(rs.getString("nombre"));
                per.setApellido(rs.getString("apellido"));
                per.setDomicilio(domicilioDao.findById(rs.getInt("domicilioid")));
                per.setEdad(rs.getInt("edad"));
                per.setDocumento(rs.getString("documento"));
                lista.add(per);
            }        
            System.out.println("findall " + lista.size());
            return lista;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }        
    }

    
      public Persona findById(int id){
        try {
            PreparedStatement ps = ConectorBD.estableceConexion().prepareStatement(Sentencias.consulta2);
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();            
            rs.next();
            Persona per = new Persona();
            per.setId(rs.getInt("id"));
            per.setNombre(rs.getString("nombre"));
            per.setApellido(rs.getString("apellido"));
            per.setDomicilio(domicilioDao.findById(rs.getInt("domicilioid")));
            per.setEdad(rs.getInt("edad"));
            per.setDocumento(rs.getString("documento"));                
            return per;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    
    
}
