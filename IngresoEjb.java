/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Ejb;

import Entidades.Cama;
import Entidades.Cita;
import Entidades.Persona;
import Entidades.Tratamiento;

/**
 *
 * @author Fernando
 */
public interface IngresoEjb {

    public void asignarCama(Persona p, Cama c);
    public Cama primeraLibre(Persona p);
    public void terminarTratamiento(Tratamiento t);

}
