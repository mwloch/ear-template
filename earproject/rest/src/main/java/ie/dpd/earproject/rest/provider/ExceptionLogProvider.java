/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.dpd.earproject.rest.provider;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author marcin.wloch
 */
@Provider
public class ExceptionLogProvider implements javax.ws.rs.ext.ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception exception) {
        exception.printStackTrace();
        return Response.status(500).build();
    }
}
