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
    public List<Cita> allCitas(String nss);
    public List<Urgencia> allUrgencias(String nss);
    public List<Cita> citasNoAtendidas(String nss);
    public List<Cita> citasAtendidas(String nss);
    public List<Urgencia> urgenciasEspera(String nss);
    public List<Urgencia> urgenciasAtendidas(String nss);
    public void avanzaAtendiendo(Urgencia u);
    public void avanzaTratamiento(Urgencia u);
}
