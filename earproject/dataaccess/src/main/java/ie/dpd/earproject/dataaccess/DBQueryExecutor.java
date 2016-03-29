/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.dpd.earproject.dataaccess;

import ie.dpd.earproject.model.ExampleTable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author marcin.wloch
 */
public class DBQueryExecutor {
    private final Connection conn;

    public DBQueryExecutor(Connection conn) {
        this.conn = conn;
    }
    
    public List<ExampleTable> listByBoolean(Boolean myFlag) throws SQLException{
        final String q1 = "SELECT *"
                        + " FROM exampleTable WHERE exampleBooleanField = ?";
        List<ExampleTable> list = new LinkedList<>();
        try(PreparedStatement stmt = conn.prepareStatement(q1)) {
            
                stmt.setBoolean(1, myFlag);
                try(ResultSet rs = stmt.executeQuery()) {
                    while(rs.next()) {
                        list.add(new ExampleTable(
                                    rs.getInt("exampleTableID"), 
                                    rs.getString("exampleCharField"), 
                                    rs.getInt("exampleIntField"), 
                                    rs.getTimestamp("exampleDateTimeField"), 
                                    rs.getBigDecimal("exampleMoneyField"), 
                                    rs.getBoolean("exampleBooleanField"), 
                                    rs.getDate("exampleDateField")
                            )
                        );
                    }
                }
        }
        return list;
    }

    public ExampleTable getRecordByID(int recID ) throws SQLException{
        
        final String q1 = "SELECT *"
                        + " FROM exampleTable WHERE exampleTableID = ?";
        
        try(PreparedStatement stmt = conn.prepareStatement(q1)) {
                // Set PreparedStatement input parameter
                stmt.setInt(1, recID);
                
                try(ResultSet rs = stmt.executeQuery()) {
                    if(rs.next()) {
                        return new ExampleTable(
                                    rs.getInt("exampleTableID"), 
                                    rs.getString("exampleCharField"), 
                                    rs.getInt("exampleIntField"), 
                                    rs.getTimestamp("exampleDateTimeField"), 
                                    rs.getBigDecimal("exampleMoneyField"), 
                                    rs.getBoolean("exampleBooleanField"), 
                                    rs.getDate("exampleDateField")
                                    );
                    }
                    
                }
        }
        
        // If record not found return empty object
        return new ExampleTable();
    }
    
}
