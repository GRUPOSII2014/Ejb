/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Ejb;

import Entidades.Informe;
import Entidades.Tratamiento;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public interface DiagnosticoEjb {
    public void anadeSegOp(Informe i);
    public void anadeInf (Informe i);
    public void actualizaInf(Informe i);
    public void creaTratamiento(Tratamiento t);
    public void actuTratamiento(Tratamiento t);
    public List<Informe> traerInformes(Integer nss);
}
