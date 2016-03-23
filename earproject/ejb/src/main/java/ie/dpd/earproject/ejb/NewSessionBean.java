/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.dpd.earproject.ejb;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import ie.dpd.earproject.interfaces.EjbInterface;

/**
 *
 * @author martin
 */
@Stateless
@LocalBean
public class NewSessionBean implements EjbInterface{

    public String myEjbMethod() {
        return "Hi, I am your session EJB";
    }

}
