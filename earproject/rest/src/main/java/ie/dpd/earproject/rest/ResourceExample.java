/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.dpd.earproject.rest;

import ie.dpd.earproject.interfaces.EjbInterface;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 *
 * @author marcin.wloch
 */
@Path("/example")
public class ResourceExample {
    
    /**
     * Method handling GET requests on root url: ../example
     * <p>
     * @return Returns example string
     */
    @GET
    public String doGet(){
        return "This is GET resource on /example URL";
    }
    
    /**
     * Method handling GET requests on sub-url: ../example/sub
     * <p>
     * @return Returns example string
     */
    @GET
    @Path("sub")
    public String doGetSub(){
        return "This is GET resource on /example/sub URL";
    }

    @GET
    @Path("ejb")
    public String doGetEjb(){
        Object lookObj=null;
        InitialContext ic;
        try {
            ic = new InitialContext();
            lookObj = ic.lookup("java:global/earproject-ear/ejb-1.0-SNAPSHOT/NewSessionBean");

            EjbInterface sessionEjb = (EjbInterface) lookObj;
            
            return sessionEjb.myEjbMethod();
        } catch (NamingException ex) {
            Logger.getLogger(ResourceExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "Failed to call EJB :(";
    }
    
    /**
     * Method handling POST requests on sub-url: ../example/sub
     * <p>
     * @return Returns example string
     */
    @POST
    @Path("sub")
    public String doPostSub(){
        return "This is POST resource on /example/sub URL";
    }
    
}
