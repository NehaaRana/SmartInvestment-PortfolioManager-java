/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.portfolio.portfoliomanger.resources;

import Entity.Users;
import ejb.UserBeanLocal;
import jakarta.ejb.EJB;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import java.util.Collection;

/**
 * REST Web Service
 *
 * @author Admin
 */
@Path("User")
@RequestScoped
public class UserResource {

    @Context
    private UriInfo context;
    
    @EJB UserBeanLocal ubl;
    /**
     * Creates a new instance of UserResource
     */
    public UserResource() {
    }
    
    
    @POST
    @Path("registerUser/{fullName}/{email}/{password}/{phone}")
    public void registerUser(
             @PathParam("fullName") String fullName,
             @PathParam("email")  String email,
             @PathParam("password")  String password,
             @PathParam("phone") String phone) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
          ubl.registerUser(fullName, email, password, phone);
    }
    
    
    @GET
    @Produces("application/json")
    @Path("findByEmail/{email}")
    public Users findByEmail(@PathParam("email")  String email) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          return ubl.findByEmail(email);
    }
    
    
    @PUT
    @Path("updateUser/{uid}/{fullName}/{email}/{password}/{phone}")
    public void updateUser(
           @PathParam("uid")  Integer uid, 
            @PathParam("fullName") String fullName,
            @PathParam("email") String email, 
            @PathParam("password") String password,
            @PathParam("phone") String phone) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
           ubl.updateUser(uid, fullName, email, password, phone);
    }
    
    @DELETE
    @Path("deleteUser/{uid}")
    public void deleteUser(@PathParam("uid") Integer uid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
           ubl.deleteUser(uid);
     
    }
    
    @GET
    @Produces("application/json")
    public Collection<Users> getAllUsers() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return ubl.getAllUsers();
    }
    
    /**
     * Retrieves representation of an instance of com.portfolio.portfoliomanger.resources.UserResource
     * @return an instance of java.lang.String
     */
   
}
