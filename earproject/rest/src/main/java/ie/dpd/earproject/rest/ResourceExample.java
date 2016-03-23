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
    
    @GET
    public String doGet(){
        return "This is GET resource on /example URL";
    }
    
    @GET
    @Path("sub")
    public String doGetSub(){
        return "This is GET resource on /example/sub URL";
    }

    @POST
    @Path("sub")
    public String doPostSub(){
        return "This is POST resource on /example/sub URL";
    }
    
}
