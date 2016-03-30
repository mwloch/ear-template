/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.dpd.earproject.dataaccess;

import ie.dpd.earproject.model.ExampleTable;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author marcin.wloch
 */
public class DBHandlerTest {
    
    public DBHandlerTest() {
    }

    @Test
    public void testGetDBHandler_3args() {
        System.out.println("getDBHandler");
        String url = "jdbc:mysql://10.1.2.70/webdb"; 
        String usr = "root";
        String pass = "root";
        DBHandler result = DBHandler.getDBHandler(url, usr, pass);
        
        assertThat(result)
                .isNotNull();
                
        assertThat(result.getConn())
                .isNotNull();

        try {
            assertThat(result.getConn().isValid(1))
                    .isTrue();
        } catch (SQLException ex) {
            org.assertj.core.api.Assertions.fail("SQL error while checking connection is Valid");
        }
        
    }
    
    @Test
    public void testGetDBHandler_3args_WrongPass() {
        System.out.println("getDBHandler");
        String url = "jdbc:mysql://10.1.2.70/webdb"; 
        String usr = "root";
        String pass = "ddd";
        DBHandler result = DBHandler.getDBHandler(url, usr, pass);
        
        assertThat(result)
                .isNull();
                        
    }
    
    @Test
    public void testListByBoolean() throws Exception {
        System.out.println("listByBoolean");
        
        String url = "jdbc:mysql://10.1.2.70/webdb"; 
        String usr = "root";
        String pass = "root";
        DBHandler dbh = DBHandler.getDBHandler(url, usr, pass);

        assertThat(dbh).isNotNull();
        
        List<ExampleTable> result = dbh.listByBoolean(true);
        
        assertThat(result)
                .hasSize(1);
        
    }

    @Test
    public void testGetByID() throws Exception {
        System.out.println("getByID");
        String url = "jdbc:mysql://10.1.2.70/webdb"; 
        String usr = "root";
        String pass = "root";
        DBHandler dbh = DBHandler.getDBHandler(url, usr, pass);

        assertThat(dbh).isNotNull();

        ExampleTable result = dbh.getByID(3);
        
        assertThat(result)
                .isNotNull();
        
        assertThat(result.getTableID())
                .isNotNull()
                .isEqualTo(3);

    }

}
