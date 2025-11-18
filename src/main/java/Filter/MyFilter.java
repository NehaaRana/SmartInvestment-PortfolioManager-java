/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package Filter;

import jakarta.inject.Inject;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import record.keepRecord;

/**
 *
 * @author Admin
 */
//@WebFilter(filterName = "MyFilter", urlPatterns = {"/*"})
@Provider
@PreMatching
public class MyFilter implements ClientRequestFilter  {
    
    public static String mytoken;
    @Inject keepRecord keepRecord;
   

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured.
    
    
    public MyFilter(String token) {
        mytoken = token;
    }

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        System.out.println(" In form Auth Client Filter " + mytoken);
        requestContext.getHeaders().add(HttpHeaders.AUTHORIZATION ,"Bearer " + mytoken);
    }
    
   
    
}
