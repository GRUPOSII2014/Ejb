/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Ejb;

import Entidades.Cita;
import Entidades.Horario;
import Entidades.Medico;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Angel
 */
@Stateless
public class CrearCitaImpl implements CrearCitaEjb{
    @PersistenceContext(unitName = "HospitalEE-ejbPU")
    EntityManager em;
    
    public String obtenerDia() {
        GregorianCalendar cal = new GregorianCalendar();
        
        int dia = cal.get(Calendar.DAY_OF_WEEK);

        switch (dia) {
            case 1:
                return "Domingo";
            case 2:
                return "Lunes";
            case 3:
                return "Martes";
            case 4:
                return "Miercoles";
            case 5:
                return "Jueves";
            case 6:
                return "Viernes";
            case 7:
                return "Sabado";
            default:
                return "NOT VALID";
        }
    }
    
    public String obtenerDiaDeCal(Calendar c) {
        int dia = c.get(Calendar.DAY_OF_WEEK);

        switch (dia) {
            case 1:
                return "Domingo";
            case 2:
                return "Lunes";
            case 3:
                return "Martes";
            case 4:
                return "Miercoles";
            case 5:
                return "Jueves";
            case 6:
                return "Viernes";
            case 7:
                return "Sabado";
            default:
                return "NOT VALID";
        }
    }
    
    public String siguienteDia(String dia){
        int res = -1;
        switch (dia) {
            case "Domigno":
                res = 1;
                break;
            case "Lunes":
                res = 2;
                break;
            case "Martes":
                res = 3;
                break;
            case "Miercoles":
                res = 4;
                break;
            case "Jueves":
                res = 5;
                break;
            case "Viernes":
                res = 6;
                break;
            case "Sabado":
                res = 7;
                break;
        }
        
        res = ((res+1)%7)+1;
        switch (res) {
            case 1:
                return "Domingo";
            case 2:
                return "Lunes";
            case 3:
                return "Martes";
            case 4:
                return "Miercoles";
            case 5:
                return "Jueves";
            case 6:
                return "Viernes";
            case 7:
                return "Sabado";
            default:
                return "NOT VALID";
        }
    }
    
    public boolean MirarHora(Date una, Date salida){
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        Calendar c3 = Calendar.getInstance();
        c3.setTime(una);
        c1.setTime(una);
        c2.setTime(salida);
        c3.set(Calendar.HOUR_OF_DAY, c2.get(Calendar.HOUR_OF_DAY));
        c3.set(Calendar.MINUTE, c2.get(Calendar.MINUTE));
        c1.roll(Calendar.MINUTE, 15);
        
        return c1.before(c3);
        
    }
    
    public boolean trabaja (Medico m, String dia){
        for(Horario h : m.getHorarios()){
            if(h.getDia().equalsIgnoreCase(dia)){
                return true;
            }
        }
        return false;
    }
    
    public Date horaSalida(Medico m, String dia){
        for(Horario h : m.getHorarios()){
            if(h.getDia().equalsIgnoreCase(dia)){
                return h.getHoraSalida();
            }
        }
        return null;
    }
    
    public Date horaEntrada(Medico m, String dia){
        for(Horario h : m.getHorarios()){
            if(h.getDia().equalsIgnoreCase(dia)){
                return h.getHoraEntrada();
            }
        }
        return null;
    }
    
    public Calendar siguienteHora(Calendar hora){
        hora.roll(Calendar.MINUTE, 15-(hora.get(Calendar.MINUTE)%15));
        return hora;
    }
    
    public Date horaEntradaACita(Date cita, Date entrada){
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        Calendar c3 = Calendar.getInstance();
        Calendar res = Calendar.getInstance();
        c3.setTime(entrada);
        c1.setTime(cita);
        c2.set(Calendar.HOUR_OF_DAY, c3.get(Calendar.HOUR_OF_DAY));
        if(c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR)){
            if(res.before(entrada)){
                res.set(Calendar.HOUR_OF_DAY, c2.get(Calendar.HOUR_OF_DAY));
                return res.getTime();
            }else{
                return siguienteHora(res).getTime();
            }
        }else{
            c1.set(Calendar.HOUR_OF_DAY, c2.get(Calendar.HOUR_OF_DAY));
            return res.getTime();
        }
    }
    
    @Override
    public Date asignaCita(Medico m) {
       List<Cita> citas = new ArrayList<>();
       Calendar hoy = Calendar.getInstance();
       Calendar horaCita = Calendar.getInstance();
       Calendar horaSalidaMedico = Calendar.getInstance();
       
       hoy.set(Calendar.HOUR, 0);
       hoy.set(Calendar.MINUTE, 0);
       hoy.set(Calendar.SECOND, 0);
        
       while(!trabaja(m,obtenerDiaDeCal(hoy))){
           hoy.roll(Calendar.DAY_OF_YEAR, 1);
       }
       boolean encontrado=false;
       while (!encontrado){
            citas.addAll(em.createNamedQuery("cita.todas", Cita.class).setParameter("nss", m.getNumSegSocial()).setParameter("fecha", hoy).getResultList());
            horaSalidaMedico.setTime(horaSalida(m, obtenerDiaDeCal(hoy)));
            if(!citas.isEmpty() && MirarHora(citas.get(citas.size()-1).getFecha(),horaSalidaMedico.getTime()))
                hoy.roll(Calendar.DAY_OF_YEAR, 1);
            else
                encontrado = true;
        }
        if(citas.isEmpty()) horaCita.setTime(horaEntradaACita(hoy.getTime(), horaEntrada(m, obtenerDiaDeCal(hoy))));
        else{
            Date d = citas.get(citas.size()-1).getFecha();
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            c.roll(Calendar.MINUTE, 1);
            horaCita.setTime(siguienteHora(c).getTime());
        }
     
        return horaCita.getTime();
    }

    @Override
    public void creaCita(Cita c) {
        em.persist(c);
    }
    
}
