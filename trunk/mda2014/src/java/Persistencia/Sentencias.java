/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

/**
 *
 * @author rustu
 */
public interface Sentencias {
 
    public final String consulta1 = "select * from persona";
    public final String consulta2 = "select * from persona where id = ?";
    public final String consulta3 = "select * from domicilio";
    public final String consulta4 = "select * from domicilio where id = ?";
}
