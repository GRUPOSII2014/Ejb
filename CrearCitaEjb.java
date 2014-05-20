/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Ejb;

import Entidades.Medico;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Angel
 */

public interface CrearCitaEjb {
   
    public Date asignaCita(Medico m);
    
}
