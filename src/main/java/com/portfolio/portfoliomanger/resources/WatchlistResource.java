/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.portfolio.portfoliomanger.resources;

import Entity.Assets;
import Entity.Users;
import Entity.Watchlist;
import ejb.WatchlistBeanLocal;
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
import java.util.Collection;

/**
 * REST Web Service
 *
 * @author Admin
 */
@Path("Watchlist")
@RequestScoped
public class WatchlistResource {
    
    @EJB WatchlistBeanLocal wbl;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WatchlistResource
     */
    public WatchlistResource() {
    }
    
    
    
    @POST
    @Path("addWatchlist/{uid}/{aid}")
    public void addWatchlist(
            @PathParam("uid") Integer uid,
            @PathParam("aid") Integer aid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        wbl.addWatchlist(uid, aid);
    }   

    
    @DELETE
    @Path("removeWatchlist/{wid}")
    public void removeWatchlist(Integer wid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        wbl.removeWatchlist(wid);
    }

    @GET
    @Produces("application/json")
    public Collection<Watchlist> getAllWatchlist() {
//      throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return wbl.getAllWatchlist();
    }

    @GET
    @Produces("application/json")
    @Path("findWatchlistByWid/{wid}")
    public Watchlist findWatchlistByWid(Integer wid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            return wbl.findWatchlistByWid(wid);
    }

    @GET
    @Produces("application/json")
    @Path("findWatchlistByUser/{uid}")
    public Collection<Watchlist> findWatchlistByUser(Integer uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
           return wbl.findWatchlistByUser(uid);
    }

    @GET
    @Produces("application/json")
    @Path("findWatchlistByAsset/{aid}")
    public Collection<Watchlist> findWatchlistByAsset(Integer aid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return wbl.findWatchlistByAsset(aid);
    }

    /**
     * Retrieves representation of an instance of com.portfolio.portfoliomanger.resources.WatchlistResource
     * @return an instance of java.lang.String
     */
   
}
