/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Ejb;

import Entidades.Persona;
import java.util.List;

/**
 *
 * @author PyRoS
 */
public interface PersonaEjb {
    public static enum Error {
        NO_ERROR,
        ERROR
    };
    
    public void crearPersona(Persona p);
    public Error editarPersona(Persona p);
    public Error eliminarPersona(Persona p);
    public List<Persona> todasPersonas();
}
