/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejb;

import Entidades.Admin;
import Entidades.Alerta;
import Entidades.Contacto;
import Entidades.Enfermero;
import Entidades.HistoriaClinica;
import Entidades.Horario;
import Entidades.Medico;
import Entidades.Mensaje;
import Entidades.Persona;
import Entidades.Trabajador;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class PersonaImpl implements PersonaEjb {

    @PersistenceContext(unitName = "HospitalEE-ejbPU")
    private EntityManager em;

    @Override
    public void crearPersona(Persona p) {
        em.persist(p);
    }

    @Override
    public List<Persona> todasPersonas() {
        TypedQuery<Persona> query = em.createNamedQuery("Persona.all", Persona.class);
        return query.getResultList();
    }
    
    @Override
    public List<Enfermero> todosEnfermeros(){
        TypedQuery<Enfermero> query = em.createNamedQuery("Enfermero.all", Enfermero.class);
        return query.getResultList();
    }

    @Override
    public Persona compruebaPersona(Integer nss, String passwd) {
        Persona p;

        try {
            p = em.createNamedQuery("Login.Comprueba", Persona.class)
                    .setParameter("nss", nss)
                    .setParameter("passwd", passwd)
                    .getSingleResult();
        } catch (NoResultException ex) {
            p = null;
        }

        return p;
    }

    @Override
    public Persona getPersona(Integer nss) {
        return em.createNamedQuery("Persona", Persona.class)
                .setParameter("nss", nss)
                .getSingleResult();
    }

    @Override
    public HistoriaClinica getHistoria(Integer nss) {
        return getPersona(nss).getHistoriaclinica();
    }
    
    @Override
    public List<Alerta> allAlertas(Integer nss) {
        return em.createNamedQuery("listaAlertas", Alerta.class)
                .setParameter("nss", nss)
                .setParameter("hora", new Date())
                .getResultList();
    }
    
    @Override
    public List<Mensaje> allMensajes(Integer nss) {
        Trabajador t = getTrabajador(nss);
        return em.createNamedQuery("listaMensajes", Mensaje.class)
                .setParameter("nss", t)
                .getResultList();
    }

    @Override
    public void crearFormularioContacto(Contacto c) {
        em.persist(c);
    }

    @Override
    public void actualizaPersona(Persona p) {
       em.merge(p);
     }

    @Override
    public Trabajador getTrabajador(Integer nss) {
        return em.createQuery("select t from Trabajador t where t.numSegSocial = :nss", Trabajador.class)
                .setParameter("nss", nss)
                .getSingleResult();
    }
    
    @Override
    public Trabajador getTrabajador(String nombre) {
        return em.createNamedQuery("trabajador.nombre", Trabajador.class)
                .setParameter("nombre", nombre)
                .getSingleResult();
    }
    
    @Override
    public void setMensaje(Mensaje m) {
        em.persist(m);
    }
    
    @Override
    public List<String> getTrabajadores(String query) {
        return em.createQuery("select p.nombre from Persona p where p.nombre like :buscado")
                .setParameter("buscado", query+"%")
                .getResultList();
    }

    @Override
    public void crearMedico(Medico m) {
        em.persist(m);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void crearAdministrativo(Admin a) {
        em.persist(a);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void crearEnfermero(Enfermero enf) {
        em.persist(enf);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String getDiscriminador(Integer nss) {
        Trabajador t = getTrabajador(nss);
        String disc = "P";
        Calendar ahora = new GregorianCalendar();
        ahora.setTime(new Date());
        Calendar entrada = new GregorianCalendar();
        Calendar salida = new GregorianCalendar();
        
        for (Horario h : t.getHorarios()) {
            entrada.setTime(h.getHoraEntrada());
            salida.setTime(h.getHoraSalida());
            
            if (ahora.get(Calendar.HOUR_OF_DAY) < salida.get(Calendar.HOUR_OF_DAY) && ahora.get(Calendar.HOUR_OF_DAY) > entrada.get(Calendar.HOUR_OF_DAY)) {
                disc = t.getDisc();
                break;
            }
        }
       
        return disc;
    }
}
