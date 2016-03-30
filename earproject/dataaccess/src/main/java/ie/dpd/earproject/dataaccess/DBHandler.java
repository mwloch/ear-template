/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.dpd.earproject.dataaccess;

import ie.dpd.earproject.error.ILKError;
import ie.dpd.earproject.model.ExampleTable;
import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author marcin.wloch
 */
public class DBHandler implements Closeable{
    private final Connection conn;
    
    private final DBQueryExecutor qExecutor;

    private DBHandler(Connection conn) {
        this.conn = conn;
        this.qExecutor = new DBQueryExecutor(conn);
    }
    
    public static DBHandler getDBHandler(String url,String usr,String pass){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Connection conn=DriverManager.getConnection("jdbc:mysql://10.1.2.70/webdb", "root", "root");
            Connection conn=DriverManager.getConnection(url, usr, pass);
            return new DBHandler(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
       
    }

    public static DBHandler getDBHandler(DataSource ds){
        try {
            Connection conn=ds.getConnection();
            return new DBHandler(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
       
    }

    public Connection getConn() {
        return conn;
    }

    public DBQueryExecutor getqExecutor() {
        return qExecutor;
    }
    
    public void beginWrite() throws SQLException{
        conn.setReadOnly(false);
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        conn.setAutoCommit(false);
    }

    public void endWrite(Boolean doCommit) throws SQLException{
        if (doCommit){
            conn.setAutoCommit(true);
        }else{
            conn.rollback();
        }
        
        this.defaultTransactionSettings();
        
    }
    
    public void beginRead() throws SQLException{
        conn.setReadOnly(true);
        conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
        conn.setAutoCommit(false);
    }

    public void endRead(Boolean doCommit) throws SQLException{
        if (doCommit){
            conn.setAutoCommit(true);
        }else{
            conn.rollback();
        }
        
        this.defaultTransactionSettings();
    }

    /** Resets the default settings of the connection.
     * The settings are valid for single read queries: read-only, read committed, auto-commit.
     * @throws SQLException
     */
    protected void defaultTransactionSettings() throws SQLException {
            conn.setReadOnly(true);
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            conn.setAutoCommit(true);
    }
    
    
    public List<ExampleTable> listByBoolean(Boolean myFlag) throws ILKError {
        try {
                beginRead();
                List<ExampleTable> list = qExecutor.listByBoolean(myFlag);
                endRead(true);
                return list;
        } catch(SQLException e) {
            String msg = "Error running method listByBoolean";
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, "Error running method listByBoolean:" + e);
            try {
                    endRead(false);
            } catch(SQLException e1) {
                Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, "Error rolling back method listByBoolean:" + e1);
                msg+=" and rolling back transaction";
            }

            throw new ILKError(ILKError.Code.DB_ACCESS,msg,e);
        }
    }    

    public ExampleTable getByID(int recID) throws ILKError  {
        try {
            beginRead();
            ExampleTable ret = qExecutor.getRecordByID(recID);
            endRead(true);
            return ret;
        } catch(SQLException e) {
            String msg = "Error running method getByID";
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, "Error running method listByBoolean:" + e);
            try {
                    endRead(false);
            } catch(SQLException e1) {
                Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, "Error rolling back method listByBoolean:" + e1);
                msg+=" and rolling back transaction";
            }
            throw new ILKError(ILKError.Code.DB_ACCESS,msg,e);
        }
        
    }    
    
    @Override
    public void close() throws IOException {
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
