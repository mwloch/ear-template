/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.dpd.earproject.rest;

import ie.dpd.earproject.dataaccess.DBHandler;
import ie.dpd.earproject.interfaces.EjbInterface;
import ie.dpd.earproject.model.ExampleTable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
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

    private static DataSource exDSource;
    
    static{
        try {        
            exDSource = (DataSource) new InitialContext().lookup("webdb-ds");
        } catch (NamingException ex) {
            Logger.getLogger(ResourceExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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
     * Method calling EJB
     * <p>
     * @return Example string obtained from EJB
     */
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
     * Method accessing record for ID given as PathParam, 
     * connecting to DB using url/user/pass
     * <p>
     * @param recordID - PathParam which is ID of the record to return
     * <p>
     * @return - If record found then returns that record as XML, 
     *           if not found then returns empty  XML, 
     *           if error when running DB query, returns null
     */
    @GET
    @Path("dbaccess/url/getexample/{recID}")
    @Produces(MediaType.APPLICATION_XML)
    public ExampleTable doGetDbUrlExm(@PathParam("recID") int recordID ){     
        
        try(DBHandler exDB = DBHandler.getDBHandler("jdbc:mysql://10.1.2.70/webdb", "root", "root")){
            return exDB.getByID(recordID);
        } catch (IOException ex) {
            Logger.getLogger(ResourceExample.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
        // return new ExampleTable(recordID,"aa",55,java.sql.Timestamp.valueOf("2016-01-01 11:11:11"),BigDecimal.ONE,Boolean.TRUE,java.sql.Date.valueOf("2016-01-01"));
    }

    /**
     * Method accessing record for ID given as PathParam, 
     * database connection obtained from application server DB connection pool
     * <p>
     * @param recordID - PathParam which is ID of the record to return
     * <p>
     * @return - If record found then returns that record as XML, 
     *           if not found then returns empty  XML, 
     *           if error when running DB query, returns null
     */
    @GET
    @Path("dbaccess/pool/getexample/{recID}")
    @Produces(MediaType.APPLICATION_XML)
    public ExampleTable doGetDbPoolExm(@PathParam("recID") int recordID ){     
        
        try(DBHandler exDB = DBHandler.getDBHandler(exDSource)){
            return exDB.getByID(recordID);
        } catch (IOException ex) {
            Logger.getLogger(ResourceExample.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
        // return new ExampleTable(recordID,"aa",55,java.sql.Timestamp.valueOf("2016-01-01 11:11:11"),BigDecimal.ONE,Boolean.TRUE,java.sql.Date.valueOf("2016-01-01"));
    }
    
    /**
     * Method returning list of records matching the logical flag given in PathParam, 
     * database connection obtained from application server DB connection pool
     * <p>
     * @param myFlag - PathParam which is checked against exampleTable.exampleBooleanField
     * <p>
     * @return - If record found then returns that record as XML, 
     *           if not found then returns empty  XML, 
     *           if error when running DB query, returns null
     */
    @GET
    @Path("dbaccess/pool/getlist/{bool}")
    @Produces(MediaType.APPLICATION_XML)
    public List<ExampleTable> doGetDbPoolList(@PathParam("bool") Boolean myFlag ){     
        
        try(DBHandler exDB = DBHandler.getDBHandler(exDSource)){
            return exDB.listByBoolean(myFlag);
        } catch (IOException ex) {
            Logger.getLogger(ResourceExample.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
        // return new ExampleTable(recordID,"aa",55,java.sql.Timestamp.valueOf("2016-01-01 11:11:11"),BigDecimal.ONE,Boolean.TRUE,java.sql.Date.valueOf("2016-01-01"));
    }
    
}
