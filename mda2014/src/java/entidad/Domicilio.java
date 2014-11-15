/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidad;
import com.google.gson.Gson;
/**
 *
 * @author rustu
 */
public class Domicilio {
    
    private int id;
    private String calle;
    private int nro;
    private String dpto;

    public Domicilio(String calle, int nro, String dpto) {
        this.calle = calle;
        this.nro = nro;
        this.dpto = dpto;
    }

    public Domicilio() {
    }
    
    public Domicilio(int id) {
        this.id = id;
    }
    
    
    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }


    public String getDpto() {
        return dpto;
    }

    public void setDpto(String dpto) {
        this.dpto = dpto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
     
}
