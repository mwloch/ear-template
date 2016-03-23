/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.dpd.earproject.ejb;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author martin
 */
@Stateless
@LocalBean
public class NewSessionBean {

    public String myEjbMethod() {
        return "Hi, I am your session EJB";
    }

}
