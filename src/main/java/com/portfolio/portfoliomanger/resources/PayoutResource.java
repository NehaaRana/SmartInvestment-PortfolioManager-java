/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.portfolio.portfoliomanger.resources;

import Entity.Payouts;
import Entity.Redemptions;
import Entity.Users;
import ejb.PayoutBeanLocal;
import jakarta.ejb.EJB;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.NoResultException;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * REST Web Service
 *
 * @author Admin
 */
@Path("Payout")
@RequestScoped
public class PayoutResource {
    
    @EJB PayoutBeanLocal pbl;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PayoutResource
     */
    public PayoutResource() {
    }

    
    @POST
    @Path("addPayout/{rid}/{uid}/{amount}/{referenceNo}/{status}")
    public void addPayout(
            @PathParam("rid") Integer rid, 
            @PathParam("uid") Integer uid, 
            @PathParam("amount") double amount, 
            @PathParam("referenceNo") String referenceNo,
            @PathParam("status") String status) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
         
        pbl.addPayout(rid, uid, amount, referenceNo, status);
    
    }

    @PUT
    @Path("updatePayout/{payId}/{rid}/{uid}/{amount}/{referenceNo}/{status}")
    public void updatePayout(
             @PathParam("payId") Integer payId,
             @PathParam("rid") Integer rid,
             @PathParam("uid") Integer uid, 
             @PathParam("amount") double amount,
             @PathParam("referenceNo") String referenceNo,
             @PathParam("status")String status) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
           pbl.updatePayout(payId, rid, uid, amount, referenceNo, status);
    
    }

    @DELETE
    @Path("removePayout/{payId}")
    public void removePayout(Integer payId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        pbl.removePayout(payId);
    }

  
    @GET
    @Produces("application/json")
    public Collection<Payouts> getAllPayouts() {
//      throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return pbl.getAllPayouts();
    }

    @GET
    @Produces("application/json")
    @Path("findPayoutByPayId/{payId}")
    public Payouts findPayoutByPayId(@PathParam("payId") Integer payId) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return  pbl.findPayoutByPayId(payId);
    }

    @GET
    @Produces("application/json")
    @Path("findPayoutsByStatus/{status}")
    public Collection<Payouts> findPayoutsByStatus(String status) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return pbl.findPayoutsByStatus(status);
    }

    @GET
    @Produces("application/json")
    @Path("findPayoutsByUser/{uid}")
    public Collection<Payouts> findPayoutsByUser(Integer uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        return pbl.findPayoutsByUser(uid);
    }
    
    
    /**
     * Retrieves representation of an instance of com.portfolio.portfoliomanger.resources.PayoutResource
     * @return an instance of java.lang.String
     */
   
}
