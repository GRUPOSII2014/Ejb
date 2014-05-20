/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Ejb;

import Entidades.Alerta;
import Entidades.Contacto;
import Entidades.Enfermero;
import Entidades.HistoriaClinica;
import Entidades.Mensaje;
import Entidades.Persona;
import Entidades.Enfermero;
import Entidades.Medico;
import Entidades.Admin;
import Entidades.Trabajador;
import java.util.List;

/**
 *
 * @author PyRoS
 */
public interface PersonaEjb {
    public static enum Error {
        NO_ERROR
    };
    
    
    public void crearPersona(Persona p);
    public void crearMedico (Medico m);
    public void crearAdministrativo (Admin a);
    public void crearEnfermero (Enfermero enf);
    public void actualizaPersona(Persona p);
    public List<Persona> todasPersonas();
    public List<Enfermero> todosEnfermeros();
    public Persona compruebaPersona(Integer nss, String passwd);
    public HistoriaClinica getHistoria(Integer nss);
    public Persona getPersona(Integer nss);
    public List<Alerta> allAlertas(Integer nss);
    public List<Mensaje> allMensajes(Integer nss);
    public void crearFormularioContacto(Contacto c);
    public Trabajador getTrabajador(Integer nss);
    public Trabajador getTrabajador(String dni);
    public void setMensaje(Mensaje m);
    public List<Trabajador> getTrabajadores(String query);
    public String getDiscriminador(Integer nss);
}
