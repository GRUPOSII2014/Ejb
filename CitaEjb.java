/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Ejb;

import Entidades.Cita;
import Entidades.Trabajador;
import Entidades.Urgencia;
import java.util.List;

/**
 *
 * @author PyRoS
 */
public interface CitaEjb {
<<<<<<< HEAD
    public List<Cita> allCitas(Trabajador t);
    public List<Urgencia> allUrgencias(Trabajador t);
    public List<Cita> citasNoAtendidas(Trabajador t);
    public List<Cita> citasAtendidas(Trabajador t);
    public List<Urgencia> urgenciasEspera(Trabajador t);
    public List<Urgencia> urgenciasAtendidas(Trabajador t);
=======
    public List<Cita> citasNoAtendidas(Integer nss);
    public List<Urgencia> urgenciasEspera(Integer nss);
    public void avanzaAtendiendo(Urgencia u);
    public void avanzaTratamiento(Urgencia u);
    public void crearCita (Cita c);
>>>>>>> ff62d8e512119522e382ecef0b91eacde0f56e1d
}
