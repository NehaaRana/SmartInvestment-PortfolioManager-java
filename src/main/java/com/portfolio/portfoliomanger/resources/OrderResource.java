/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.portfolio.portfoliomanger.resources;

import Entity.Assets;
import Entity.Orders;
import Entity.Users;
import ejb.OrderBeanLocal;
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
@Path("Order")
@RequestScoped
public class OrderResource {

    @Context
    private UriInfo context;
    @EJB OrderBeanLocal obl;
    /**
     * Creates a new instance of OrderResource
     */
    public OrderResource() {
    }

    @POST
    @Path("addOrder/{uid}/{aid}/{order_type}/{units}/{amount}/{status}")
    public void addOrder(
            @PathParam("uid") Integer uid, 
            @PathParam("aid") Integer aid, 
            @PathParam("order_type") String order_type, 
            @PathParam("units") double units, 
            @PathParam("amount") double amount, 
            @PathParam("status") String status) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            obl.addOrder(uid, aid, order_type, units, amount, status);
    }

    @PUT
    @Path("updateOrder/{oid}/{uid}/{aid}/{order_type}/{units}/{amount}/{status}")
    public void updateOrder(
            @PathParam("oid") Integer oid, 
             @PathParam("uid") Integer uid, 
              @PathParam("aid") Integer aid, 
               @PathParam("order_type") String order_type, 
                @PathParam("units") double units, 
                 @PathParam("amount") double amount, 
                  @PathParam("status")String status) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
         obl.updateOrder(oid, uid, aid, order_type, units, amount, status);
    }

    @DELETE
    @Path("removeOrder/{oid}")
    public void removeOrder(@PathParam("oid") Integer oid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          obl.removeOrder(oid);
    }

    @GET
    @Produces("application/json")
    public Collection<Orders> getAllOrders() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            return obl.getAllOrders();
    
    }

    
    

   

    @GET
    @Path("findOrderById/{oid}")
    @Produces("application/json")
    public Orders findOrderById(@PathParam("oid") Integer oid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            return obl.findOrderById(oid);
    
    }

    
    @GET
    @Path("findOrderByUser/{uid}")
    @Produces("application/json")
    public Collection<Orders> findOrdersByUser(@PathParam("uid")Integer uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return obl.findOrdersByUser(uid);
        
    
    }

    @GET
    @Path("findOrderByUser/{aid}")
    @Produces("application/json")
    public Collection<Orders> findOrdersByAsset( @PathParam("aid") Integer aid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    
       return obl.findOrdersByAsset(aid);
    }
    
    
    
    /**
     * Retrieves representation of an instance of com.portfolio.portfoliomanger.resources.OrderResource
     * @return an instance of java.lang.String
     */
//    @GET
//    @Produces(MediaType.APPLICATION_XML)
//    public String getXml() {
//        //TODO return proper representation object
//        throw new UnsupportedOperationException();
//    }
//
//    /**
//     * PUT method for updating or creating an instance of OrderResource
//     * @param content representation for the resource
//     */
//    @PUT
//    @Consumes(MediaType.APPLICATION_XML)
//    public void putXml(String content) {
//    }
}
