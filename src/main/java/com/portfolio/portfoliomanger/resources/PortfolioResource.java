/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.portfolio.portfoliomanger.resources;

import Entity.Assets;
import Entity.Portfolio;
import Entity.Users;
import ejb.PortfolioBeanLocal;
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
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * REST Web Service
 *
 * @author Admin
 */
@Path("portfolio")
@RequestScoped
public class PortfolioResource {

    @Context
    private UriInfo context;
    
    @EJB PortfolioBeanLocal pbl;
    /**
     * Creates a new instance of PortfolioResource
     */
    public PortfolioResource() {
    }

    /**
     * Retrieves representation of an instance of com.portfolio.portfoliomanger.resources.PortfolioResource
     * @return an instance of java.lang.String
     */
//    @GET
//    @Produces(MediaType.APPLICATION_XML)
//    public String getXml() {
//        //TODO return proper representation object
//        throw new UnsupportedOperationException();
//    }

    /**
     * PUT method for updating or creating an instance of PortfolioResource
     * @param content representation for the resource
     */
    
    @PUT
    @Path("updatePortfolio/{uid}/{aid}/{units}/{avgPrice}")
    public void updatePortfolio(
            @PathParam("uid") Integer uid, 
            @PathParam("aid") Integer aid,
            @PathParam("units") double units, 
            @PathParam("avgPrice") double avgPrice) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        pbl.updatePortfolio(uid, aid, units, avgPrice);
    
    }

    @DELETE
    @Path("deletePortfolio/{pf_id}")
    public void removePortfolio(Integer pf_id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            pbl.removePortfolio(pf_id);
    }

    @GET
    @Produces("application/json")
    public Collection<Portfolio> getAllPortfolio() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return  pbl.getAllPortfolio();
    
    
    }

    @GET
    @Path("findPortfolioById/{pf_id}")
    @Produces("application/json")
    public Portfolio findPortfolioById(@PathParam("pf_id") Integer pf_id) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return pbl.findPortfolioById(pf_id);
    }

    @GET
    @Path("findPortfolioByUser/{uid}")
    @Produces("application/json")
    public Collection<Portfolio> findPortfolioByUser(@PathParam("uid") Integer uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return pbl.findPortfolioByUser(uid);
       
    }

    @GET
    @Path("findPortfolioByAsset/{aid}")
    @Produces("application/json")
    public Collection<Portfolio> findPortfolioByAsset(@PathParam("aid")  Integer aid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return pbl.findPortfolioByAsset(aid);
    
    }

    
    
}
