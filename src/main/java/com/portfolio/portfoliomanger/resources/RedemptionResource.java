/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.portfolio.portfoliomanger.resources;

import Entity.Assets;
import Entity.Redemptions;
import Entity.Users;
import ejb.RedemptionBeanLocal;
import jakarta.ejb.EJB;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * REST Web Service
 *
 * @author User
 */
@Path("Redemption")
@RequestScoped
public class RedemptionResource {

    @Context
    private UriInfo context;
    @EJB RedemptionBeanLocal rbl;
    /**
     * Creates a new instance of RedemptionResource
     */
    public RedemptionResource() {
    }

    @POST
    @Path("addRedemption/{uid}/{aid}/{units}/{status}/{exit_fee_amt}")
    public void addRedemption(@PathParam("uid") Integer uid,
            @PathParam("aid") Integer aid, 
            @PathParam("units") double units, 
            @PathParam("status") String status, 
            @PathParam("exit_fee_amt") double exit_fee_amt) {
        rbl.addRedemption(uid, aid, units, status, exit_fee_amt);
    }

    @PUT
    @Path("approveRedemption/{rid}")
    public void approveRedemption(@PathParam("rid") Integer rid) {
        rbl.approveRedemption(rid);
    }

    @PUT
    @Path("markAsPaid/{rid}")
    public void markAsPaid(@PathParam("rid") Integer rid) {
        rbl.markAsPaid(rid);
    }

    @DELETE
    @Path("deleteRedemption/{rid}")
    public void deleteRedemption(Integer rid) {
        rbl.deleteRedemption(rid);
    }

    @GET
    @Path("getAllRedemptions")
    @Produces("application/json")
    public Collection<Redemptions> getAllRedemptions() {
        return rbl.getAllRedemptions();
    }

    @GET
    @Path("getRedemptionsByUser/{uid}")
    @Produces("application/json")
    public Collection<Redemptions> getRedemptionsByUser(Integer uid) {
        return rbl.getRedemptionsByUser(uid);
    }

    @GET
    @Path("findRedemptionById")
    @Produces("application/json")
    public Redemptions findRedemptionById(Integer rid) {
        return rbl.findRedemptionById(rid);
    }

}
