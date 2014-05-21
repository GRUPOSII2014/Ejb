/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Ejb;

import Entidades.Alerta;
import Entidades.Persona;
import java.util.List;


/**
 *
 * @autshor Ricardo
 */
public interface EventosEjb {
    
      
   
    
    public void crearEventos(Alerta a, Persona p);
    public List<Persona> getPersonas(String bus);
    public void crearEventos(Alerta a);
    
}
