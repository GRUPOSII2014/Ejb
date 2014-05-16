/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Ejb;

import Entidades.HistoriaClinica;
import Entidades.Persona;
import java.util.List;

/**
 *
 * @author PyRoS
 */
public interface PersonaEjb {
    
    public void crearPersona(Persona p);
    public List<Persona> todasPersonas();
    public Persona compruebaPersona(Integer nss, String passwd);
    public Persona getPersona(Integer nss);
    public HistoriaClinica getHistoria(Integer nss);
}
