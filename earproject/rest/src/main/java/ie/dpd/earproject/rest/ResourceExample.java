/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.dpd.earproject.rest;

import ie.dpd.earproject.interfaces.EjbInterface;
import ie.dpd.earproject.model.ExampleTable;
import ie.dpd.earproject.model.RecordResponse;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
    @Path("dupa")
    public ExampleTable doGetDupa(){
        return new ExampleTable(11,"dupa",55,java.sql.Timestamp.valueOf("2016-01-01 11:11:11"),BigDecimal.ONE,Boolean.TRUE,java.sql.Date.valueOf("2016-01-01"));
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

    /**
     * Method handling POST requests on sub-url: ../example/dbaccess/pool/getrecord{recID}
     * <p>
     * Method uses recID path param as input 
     * <p>
     * Method returns RecordResponse object mapped to XML using JAXB 
     * <p>
     * @param recordID Record ID taken from PathParam
     * @return Returns example string
     */
    @GET
    @Path("dbaccess/pool/getrecord{recID}")
    @Produces(MediaType.APPLICATION_XML)
    public RecordResponse doGetDbPoolRec(@PathParam("recID") int recordID ){
        return new RecordResponse(5);
        // return new ExampleTable(recordID,"aa",55,java.sql.Timestamp.valueOf("2016-01-01 11:11:11"),BigDecimal.ONE,Boolean.TRUE,java.sql.Date.valueOf("2016-01-01"));
    }

    @GET
    @Path("dbaccess/pool/getexample/{recID}")
    @Produces(MediaType.APPLICATION_XML)
    public ExampleTable doGetDbPoolExm(@PathParam("recID") int recordID ){        
        return new ExampleTable(recordID,"aa",55,java.sql.Timestamp.valueOf("2016-01-01 11:11:11"),BigDecimal.ONE,Boolean.TRUE,java.sql.Date.valueOf("2016-01-01"));
    }

}
