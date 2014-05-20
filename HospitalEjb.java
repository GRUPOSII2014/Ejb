/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Ejb;

import Entidades.Habitacion;
import Entidades.Hospital;
import Entidades.Planta;
import java.util.List;

/**
 *
 * @author Angel
 */
public interface HospitalEjb {
    public void crearHospital(Hospital h);
    public void crearPlanta(Planta p);
    public List<Hospital> getHospitales();
    public List<Planta> getPlantas();
    public void crearHabitacion(Habitacion h);
}
