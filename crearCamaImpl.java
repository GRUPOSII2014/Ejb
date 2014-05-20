/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Ejb;

import Entidades.Cama;
import Entidades.Habitacion;
import Entidades.Persona;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TypedQuery;
import Entidades.Cama;
import Entidades.Habitacion;

/**
 *
 * @author Fernando
 */
@Entity
public class crearCamaImpl implements crearCamaEjb {
     private EntityManager em;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof crearCamaImpl)) {
            return false;
        }
        crearCamaImpl other = (crearCamaImpl) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ejb.crearCamaImpl[ id=" + id + " ]";
    }

    @Override
    public void crearCama(Cama c) {
        Habitacion h = c.getHabitacion();
        List <Cama>  lista = h.getCamas();
        lista.add(c);
        h.setCamas(lista);
        em.persist(h);
    }

    @Override
    public List<Habitacion> todasHabitaciones() {
        TypedQuery<Habitacion> query = em.createNamedQuery("Habitacion.all", Habitacion.class);
        return query.getResultList();
       
    }

    @Override
    public List<Cama> todasCamas() {
         TypedQuery<Cama> query = em.createNamedQuery("Cama.all", Cama.class);
        return query.getResultList();
    }

    
    public String comprobarCama(Cama c) {
        if (c.getHabitacion().getCamas().size()<2)
             crearCama(c);
        else
            return "Demasiadas camas en la misma habitacion";
        return "Cama creada correctamente";
    }

  
    
}
