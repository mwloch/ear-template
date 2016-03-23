/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.dpd.earproject.rest;

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
