/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.portfolio.portfoliomanger.resources;


import Entity.Transactions;
import ejb.TransactionBeanLocal;
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
@Path("Transaction")
@RequestScoped
public class TransactionResource {

    @Context
    private UriInfo context;

    @EJB TransactionBeanLocal tbl;
    /**
     * Creates a new instance of TransactionResource
     */
    public TransactionResource() {
    }

  
    @POST
    @Path("addTransaction/{oid}/{uid}/{txn_type}/{amount}/{status}")
    public void addTransaction(@PathParam("oid") Integer oid,
            @PathParam("uid") Integer uid,
            @PathParam("txn_type") String txn_type,
            @PathParam("amount") double amount,
            @PathParam("status") String status) {
           
        tbl.addTransaction(oid, uid, txn_type, 0, status);
    }

    @PUT
    @Path("updateTransaction/{tid}/{oid}/{uid}/{amount}/{txn_type}/{status}")
    public void updateTransaction(@PathParam("tid") Integer tid,
            @PathParam("oid") Integer oid ,
            @PathParam("uid") Integer uid ,
            @PathParam("amount") double amount,
            @PathParam("txn_type") String txn_type ,
            @PathParam("status") String status) {
     
        tbl.updateTransaction(tid, oid, uid, 0, txn_type, status);
    }

    
    @DELETE
    @Path("deleteTransaction")
    public void deleteTransaction(Integer tid) {
       tbl.deleteTransaction(tid);
    }
    
    @GET
    @Produces("application/json")
     public Collection<Transactions> getAllTransactions() {
          return tbl.getAllTransactions();
    }
//    @GET
//    @Produces(MediaType.APPLICATION_XML)
//    public String getXml() {
//        //TODO return proper representation object
//        throw new UnsupportedOperationException();
//    }
//
//    /**
//     * PUT method for updating or creating an instance of TransactionResource
//     * @param content representation for the resource
//     */
//    @PUT
//    @Consumes(MediaType.APPLICATION_XML)
//    public void putXml(String content) {
//    }
}
