/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.portfolio.portfoliomanger.resources;

import Entity.Assets;
import ejb.AssetsBeanLocal;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.Collection;
import jakarta.ejb.EJB;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author Admin
 */
@Path("Assets")
@RequestScoped
public class AssetsResource {

    @Context
    private UriInfo context;
    
    @EJB AssetsBeanLocal abl;

    /**
     * Creates a new instance of AssetsResource
     */
    public AssetsResource() {
    }

    /**
     * Retrieves representation of an instance of com.portfolio.portfoliomanger.resources.AssetsResource
     * @return an instance of java.lang.String
     */
    
    @POST
    @Path("addAsset/{symbol}/{name}/{type}/{current_price}/{status}/{risk}")
    public void addAsset(
            @PathParam("symbol") String symbol,
            @PathParam("name") String name,
            @PathParam("type") String type,
            @PathParam("current_price") double current_price,
            @PathParam("status") String status,
            @PathParam("risk") String risk){
    
        abl.addAsset(symbol, name, type, current_price, status, risk);
    }

    
    @PUT
    @Path("updateAsset/{aid}/{symbol}/{name}/{type}/{current_price}/{status}/{risk}")
    public void updateAsset(
            @PathParam("aid") Integer aid, 
            @PathParam("symbol") String symbol,
            @PathParam("name") String name, 
            @PathParam("type") String type,
            @PathParam("current_price") double current_price, 
            @PathParam("status") String status,
            @PathParam("risk") String risk) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    
        abl.updateAsset(aid, symbol, name, type, current_price, status, risk);
    }

    @DELETE
    @Path("removeAsset/{aid}")
    public void removeAsset(@PathParam("aid") Integer aid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          abl.removeAsset(aid);
    }

    @GET
    @Produces("application/json")
    public Collection<Assets> getAllAssets() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          
        return abl.getAllAssets();
           
        
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @GET
    @Path("findAssetsByName/{name}")
    @Produces("application/json")
    public Collection<Assets> findAssetsByName(String name) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    
          return abl.findAssetsByName(name);
    }

    
    @GET
    @Path("findAssetsBySymbol/{symbol}")
    public Collection<Assets> findAssetsBySymbol(String symbol) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        return abl.findAssetsBySymbol(symbol);
    }

    
    @GET
    @Path("findAssetsByType/{type}")
    public Collection<Assets> findAssetsByType(String type) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        return abl.findAssetsByType(type);
    }
    
    
    
    
 //    @GET
//    @Produces(MediaType.APPLICATION_XML)
//    public String getXml() {
//        //TODO return proper representation object
//        throw new UnsupportedOperationException();
//    }
//
//    /**
//     * PUT method for updating or creating an instance of AssetsResource
//     * @param content representation for the resource
//     */
//    @PUT
//    @Consumes(MediaType.APPLICATION_XML)
//    public void putXml(String content) {
//    }



}
