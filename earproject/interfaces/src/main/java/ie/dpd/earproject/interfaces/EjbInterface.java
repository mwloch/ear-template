/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.dpd.earproject.interfaces;

import javax.ejb.Local;

/**
 *
 * @author marcin.wloch
 */
@Local
public interface EjbInterface {
    
    public String myEjbMethod();
    
}
