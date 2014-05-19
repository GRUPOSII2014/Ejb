/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Ejb;

import Entidades.Persona;

/**
 *
 * @author Angel
 */

public interface CrearCitaEjb {
   
    Persona buscarPersona(Integer nss);
    void crearCita ();
    
}
