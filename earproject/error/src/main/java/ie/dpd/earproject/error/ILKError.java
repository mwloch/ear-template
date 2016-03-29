/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.dpd.earproject.error;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 *
 * @author marcin.wloch
 */
public class ILKError extends Exception {

    // Predefined error codes
    public enum Code {
        UNKNOWN         (0  ,"Unknown error"),
        DB_ACCESS       (10 ,"Database access error"),
        GENERAL         (999,"General error");
        
        private final int value;
        private final String description;

        private Code(int value, String description) {
            this.value = value;
            this.description = description;
        }
    }

    private Code code;
    
    public ILKError() {
        this.code = Code.UNKNOWN;
    }

    public ILKError(Code code) {
        this.code = code;
    }
    
    public ILKError(Code code,String message) {
        super(message);
        this.code = code;
    }

    public ILKError(Code code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    @Override
    public String toString() {
        return "ILKError{" + "code=" + code + super.toString() + '}';
    }

    @Override
    public synchronized Throwable getCause() {
        return super.getCause();
    }

    @Override
    public String getMessage() {
        return 
                "#ERR (#CODE)"
                        .replace("#ERR", this.code.description)
                        .replace("#CODE", Integer.valueOf(this.code.value).toString()) +
                super.getMessage();
    }

    public Code getCode() {
        return code;
    }
    
}
