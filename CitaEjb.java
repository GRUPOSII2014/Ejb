/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Ejb;

import Entidades.Cita;
import Entidades.Urgencia;
import java.util.List;

/**
 *
 * @author PyRoS
 */
public interface CitaEjb {
    public void cancelarCita(Cita c);
    public int citasPorDelante(Integer nss);
    public List<Cita> citasDePersona(Integer nss);
    public List<Cita> citasNoAtendidas(Integer nss);
    public List<Urgencia> urgenciasEspera(Integer nss);
    public void avanzaAtendiendo(Urgencia u);
    public void avanvaCita(Cita c);
    public void crearCita (Cita c);
    public void crearUrgencia (Urgencia u);
}
