/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Ejb;

import Entidades.Cama;
import java.util.List;

/**
 *
 * @author Fernando
 */
public interface crearCamaEjb {
    void crearCama (Cama c);
    public List <Entidades.Habitacion> todasHabitaciones();
    public List <Entidades.Cama> todasCamas();
    public String comprobarCama(Cama c);
}
