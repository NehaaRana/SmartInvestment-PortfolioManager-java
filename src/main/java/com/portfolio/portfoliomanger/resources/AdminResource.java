/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.portfolio.portfoliomanger.resources;

import Entity.Assets;
import Entity.Orders;
import Entity.Payments;
import Entity.Payouts;
import Entity.Redemptions;
import Entity.Transactions;
import Entity.Users;
import ejb.AdminBeanLocal;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJB;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PathParam;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * REST Web Service
 *
 * @author User
 */
@DeclareRoles({"ADMIN","USER"})
@Path("Admin")
@RequestScoped
public class AdminResource {

    @Context
    private UriInfo context;
    @EJB AdminBeanLocal abl;

    /**
     * Creates a new instance of AdminResource
     */
    public AdminResource() {
    }
    
    @RolesAllowed("ADMIN")
    @POST
    @Path("addUser/{fullName}/{email}/{password}/{phone}/{gid}")
    public void addUser(@PathParam("fullName") String fullName,
            @PathParam("email") String email,
            @PathParam("password") String password,
            @PathParam("phone") String phone, 
            @PathParam("gid") int gid) {
        abl.addUser(fullName, email, password, phone, gid);
    }

//    @PUT
//    @Path("updateUser")
//    public void updateUser(int uid, String fullName, String phone) {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

    @RolesAllowed("ADMIN")
    @DELETE
    @Path("deleteUser/{uid}")
    public void deleteUser(@PathParam("uid") int uid) {
        abl.deleteUser(uid);
    }

    @RolesAllowed("ADMIN")
    @GET
    @Produces("application/json")
    public Collection<Users> getAllUsers() {
        return abl.getAllUsers();
    }

    @RolesAllowed("ADMIN")
    @GET
    @Produces("application/json")
    @Path("findUserById/{uid}")
    public Users findUserById(@PathParam("uid") int uid) {
        return abl.findUserById(uid);
    }

    @RolesAllowed("ADMIN")
    @POST
    @Path("addAsset/{symbol}/{name}/{type}/{currentPrice}/{status}/{risk}")
    public void addAsset(@PathParam("symbol") String symbol,
            @PathParam("name") String name, 
            @PathParam("type") String type,
            @PathParam("currentPrice") BigDecimal currentPrice,
            @PathParam("status") String status, 
            @PathParam("risk") String risk) {
        abl.addAsset(symbol, name, type, currentPrice, status, risk);
    }

    
    
    @RolesAllowed("ADMIN")
    @PUT
    @Path("updateAssetPrice/{aid}/{newPrice}")
    public void updateAssetPrice(@PathParam("aid") int aid, 
            @PathParam("newPrice") BigDecimal newPrice) {
        abl.updateAssetPrice(aid, newPrice);
    }
    
    @RolesAllowed("ADMIN")
    @PUT
    @Path("updateAsset/{aid}/{symbol}/{name}/{type}/{currentPrice}/{status}/{risk}")
    public void updateAsset(@PathParam("aid") int aid,
                            @PathParam("symbol") String symbol,
                            @PathParam("name") String name,
                            @PathParam("type") String type,
                            @PathParam("currentPrice") BigDecimal currentPrice,
                            @PathParam("status") String status,
                            @PathParam("risk") String risk) {

        abl.updateAsset(aid, symbol, name, type, currentPrice, status, risk);
    }

    
    @RolesAllowed("ADMIN")
    @PUT
    @Path("updateAssetStatus/{aid}/{status}")
    public void updateAssetStatus(@PathParam("aid") int aid, 
            @PathParam("status") String status) {
        abl.updateAssetStatus(aid, status);
    }

    @RolesAllowed("ADMIN")
    @GET
    @Path("getAllAssets")
    @Produces("application/json")
    public Collection<Assets> getAllAssets() {
        return abl.getAllAssets();
    }
    
    @RolesAllowed("ADMIN")
    @DELETE
    @Path("deleteAsset/{aid}")
    public void deleteAsset(@PathParam("aid") int aid) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            abl.deleteAsset(aid);
    
    }

    @RolesAllowed("ADMIN")
    @GET
    @Path("findAssetById/{aid}")
    @Produces("application/json")
    public Assets findAssetById(@PathParam("aid") int aid) {
        return abl.findAssetById(aid);
    }

    @RolesAllowed("ADMIN")
    @GET
    @Path("getAllOrders")
    @Produces("application/json")
    public Collection<Orders> getAllOrders() {
        return abl.getAllOrders();
    }

    @RolesAllowed("ADMIN")
    @GET
    @Path("getAllTransactions")
    @Produces("application/json")
    public Collection<Transactions> getAllTransactions() {
        return abl.getAllTransactions();
    }

    @RolesAllowed("ADMIN")
    @PUT
    @Path("updateOrderStatus/{oid}/{status}")
    public void updateOrderStatus(@PathParam("oid") int oid,
            @PathParam("status") String status) {
        abl.updateOrderStatus(oid, status);
    }

    @RolesAllowed("ADMIN")
    @PUT
    @Path("updateTransactionStatus/{tid}/{status}")
    public void updateTransactionStatus(@PathParam("tid") int tid,
            @PathParam("status") String status) {
        abl.updateTransactionStatus(tid, status);
    }

    @RolesAllowed("ADMIN")
    @GET
    @Path("getAllPayments")
    @Produces("application/json")
    public Collection<Payments> getAllPayments() {
        return abl.getAllPayments();
    }

    @RolesAllowed("ADMIN")
    @PUT
    @Path("updatePaymentStatus/{pid}/{status}")
    public void updatePaymentStatus(@PathParam("pid") int pid,
            @PathParam("status") String status) {
        abl.updatePaymentStatus(pid, status);
    }

    @RolesAllowed("ADMIN")
    @GET
    @Path("getAllRedemptions")
    @Produces("application/json")
    public Collection<Redemptions> getAllRedemptions() {
        return abl.getAllRedemptions();
    }

    @RolesAllowed("ADMIN")
    @PUT
    @Path("approveRedemption/{rid}")
    public void approveRedemption(@PathParam("rid") int rid) {
        abl.approveRedemption(rid);
    }

    @RolesAllowed("ADMIN")
    @PUT
    @Path("rejectRedemption/{rid}")
    public void rejectRedemption(@PathParam("rid") int rid) {
        abl.rejectRedemption(rid);
    }

    @RolesAllowed("ADMIN")
    @POST
    @Path("addPayout/{rid}/{uid}/{amount}/{referenceNo}/{status}/")
    public void addPayout(@PathParam("rid") int rid, 
            @PathParam("uid") int uid,
            @PathParam("amount") BigDecimal amount,
            @PathParam("referenceNo") String referenceNo,
            @PathParam("status") String status) {
        abl.addPayout(rid, uid, amount, referenceNo, status);
    }

    @RolesAllowed("ADMIN")
    @GET
    @Path("getAllPayouts")
    @Produces("application/json")
    public Collection<Payouts> getAllPayouts() {
        return abl.getAllPayouts();
    }

    @RolesAllowed("ADMIN")
    @PUT
    @Path("updatePayoutStatus/{payId}/{status}")
    public void updatePayoutStatus(@PathParam("payId") int payId,
            @PathParam("status") String status) {
        abl.updatePayoutStatus(payId, status);
    }

    @RolesAllowed("ADMIN")
    @GET
    @Path("getTotalInvestedAmount")
    public BigDecimal getTotalInvestedAmount() {
        return abl.getTotalInvestedAmount();
    }

    @RolesAllowed("ADMIN")
    @GET
    @Path("getTotalPayouts")
    public BigDecimal getTotalPayouts() {
        return abl.getTotalPayouts();
    }

    @RolesAllowed("ADMIN")
    @GET
    @Path("getTotalUsers")
    public long getTotalUsers() {
        return abl.getTotalUsers();
    }

    @RolesAllowed("ADMIN")
    @GET
    @Path("getTotalAssets")
    public long getTotalAssets() {
        return abl.getTotalAssets();
    }

}
