/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

import entidad.Domicilio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author rustu
 */
public class DomicilioDao {
    
    public List<Domicilio> findAll(){
        List<Domicilio> lista = new ArrayList<>();
        try {
            ResultSet rs = ConectorBD.estableceConexion().createStatement().executeQuery(Sentencias.consulta3);
            Domicilio dom = null;
            while(rs.next()){
                dom = new Domicilio();
                dom.setId(rs.getInt("id"));
                dom.setCalle(rs.getString("calle"));
                dom.setNumero(rs.getInt("numero"));
                dom.setDpto(rs.getString("dpto"));                
                lista.add(dom);
            }            
            return lista;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
        
    }
    
    
     public Domicilio findById(int id){
        
        try {
            PreparedStatement ps = ConectorBD.estableceConexion().prepareStatement(Sentencias.consulta4);
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();            
            rs.next();
            Domicilio dom = new Domicilio();
            dom.setId(rs.getInt("id"));
            dom.setCalle(rs.getString("calle"));
            dom.setNumero(rs.getInt("nro"));
            dom.setDpto(rs.getString("dpto"));                
            return dom;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }        
    }
    
    
}
