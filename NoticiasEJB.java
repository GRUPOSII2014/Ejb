/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Ejb;

import Entidades.Noticia;
import java.util.List;

/**
 *
 * @author PyRoS
 */
public interface NoticiasEJB {
    public List<Noticia> allNoticias();
    public void setNoticia(Noticia n, Integer nss);
}
